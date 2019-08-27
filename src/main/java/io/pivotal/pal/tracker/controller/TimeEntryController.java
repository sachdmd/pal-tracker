package io.pivotal.pal.tracker.controller;

import io.pivotal.pal.tracker.pojo.TimeEntry;
import io.pivotal.pal.tracker.repository.TimeEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class TimeEntryController {

    @Autowired
    TimeEntryRepository timeEntryRepository;
   public  TimeEntryController(TimeEntryRepository timeEntryRepository){
        this.timeEntryRepository=timeEntryRepository;
    }


    public ResponseEntity create(TimeEntry timeEntry){
       TimeEntry timeentryCreated= timeEntryRepository.create(timeEntry);
       return new ResponseEntity(timeentryCreated,HttpStatus.CREATED);
    }

    public ResponseEntity read(long id){
        TimeEntry timeEntryRead= timeEntryRepository.find(id);
        if(timeEntryRead==null)
        {
            return new ResponseEntity(timeEntryRead,HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity(timeEntryRead, HttpStatus.OK);
        }
    }
    public ResponseEntity list(){
        List<TimeEntry> timeEntrylist= timeEntryRepository.list();
        return new ResponseEntity(timeEntrylist,HttpStatus.OK);
    }

    public ResponseEntity update(long id,TimeEntry timeEntry){
        TimeEntry timeEntryupdate= timeEntryRepository.update(id, timeEntry);

        return new ResponseEntity(timeEntryupdate,HttpStatus.OK);
    }

    public ResponseEntity delete(long timeEntryId){
         timeEntryRepository.delete(timeEntryId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
