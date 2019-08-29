package io.pivotal.pal.tracker;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import io.pivotal.pal.tracker.pojo.TimeEntry;
import io.pivotal.pal.tracker.repository.TimeEntryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    private final DistributionSummary timeEntrySummary;
    private final Counter actionCounter;
    private TimeEntryRepository timeEntryRepository;
   public  TimeEntryController(TimeEntryRepository timeEntryRepository, MeterRegistry meterRegistry){
       timeEntrySummary = meterRegistry.summary("timeEntry.summary");
       actionCounter = meterRegistry.counter("timeEntry.actionCounter");
        this.timeEntryRepository=timeEntryRepository;
    }


    @PostMapping
    public ResponseEntity create(@RequestBody TimeEntry timeEntry){
       TimeEntry timeentryCreated= timeEntryRepository.create(timeEntry);
        actionCounter.increment();
       return new ResponseEntity(timeentryCreated,HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity read(@PathVariable Long id){
        TimeEntry timeEntryRead= timeEntryRepository.find(id);
        if(timeEntryRead==null)
        {
          return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else {
            actionCounter.increment();
            return new ResponseEntity(timeEntryRead, HttpStatus.OK);
        }
    }
    @GetMapping
    public ResponseEntity list(){
        List<TimeEntry> timeEntrylist= timeEntryRepository.list();
        actionCounter.increment();
        return new ResponseEntity(timeEntrylist,HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable Long id,@RequestBody TimeEntry timeEntry){
        TimeEntry timeEntryupdate= timeEntryRepository.update(id, timeEntry);
        if(timeEntryupdate==null)
        {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else {
            actionCounter.increment();
            return new ResponseEntity(timeEntryupdate, HttpStatus.OK);
        }

    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id){
         timeEntryRepository.delete(id);
        actionCounter.increment();
        timeEntrySummary.record(timeEntryRepository.list().size());
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
