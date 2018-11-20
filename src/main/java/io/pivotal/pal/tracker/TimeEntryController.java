package io.pivotal.pal.tracker;

import io.pivotal.pal.tracker.pojo.TimeEntry;
import io.pivotal.pal.tracker.repository.TimeEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;
   public  TimeEntryController(TimeEntryRepository timeEntryRepository){
        this.timeEntryRepository=timeEntryRepository;
    }


    @PostMapping
    public ResponseEntity create(@RequestBody TimeEntry timeEntry){
       TimeEntry timeentryCreated= timeEntryRepository.create(timeEntry);
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
            return new ResponseEntity(timeEntryRead, HttpStatus.OK);
        }
    }
    @GetMapping
    public ResponseEntity list(){
        List<TimeEntry> timeEntrylist= timeEntryRepository.list();
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
            return new ResponseEntity(timeEntryupdate, HttpStatus.OK);
        }

    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long timeEntryId){
         timeEntryRepository.delete(timeEntryId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
