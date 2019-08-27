package io.pivotal.pal.tracker.repository;

import io.pivotal.pal.tracker.pojo.TimeEntry;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    private TimeEntry timeEntry;
    private Map<Long,TimeEntry> timeEntryMap= new HashMap();
    private long id=0;

    public TimeEntry create(TimeEntry timeEntry){
        this.timeEntry=timeEntry;
        if(timeEntry.getId()!=null) {
            timeEntryMap.put(timeEntry.getId(),timeEntry);
        }
        else{
            id=id+1;
            this.timeEntry.setId(id);
            timeEntryMap.put(id,timeEntry);
        }
         return this.timeEntry;
    }

    public TimeEntry find(long id){

        if(timeEntryMap.containsKey(id)){
            return timeEntryMap.get(id);
        }
        return null;
    }

    public List<TimeEntry> list(){
       return timeEntryMap.values().stream().collect(Collectors.toList());
    }

    public TimeEntry update(long id, TimeEntry timeEntryupdated){
        if(timeEntryMap.containsKey(id)){
            timeEntryupdated.setId(id);
            timeEntryMap.put(id,timeEntryupdated);
        }
        return timeEntryMap.get(id);
    }

    public void delete(long id){
        if(timeEntryMap.containsKey(id)){
            timeEntryMap.remove(id);
        }
    }


}
