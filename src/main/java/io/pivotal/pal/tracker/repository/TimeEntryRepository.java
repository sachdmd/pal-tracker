package io.pivotal.pal.tracker.repository;

import io.pivotal.pal.tracker.pojo.TimeEntry;

import java.util.List;

public interface TimeEntryRepository {
void delete(long id);
TimeEntry create(TimeEntry timeEntry);
TimeEntry find(long id);
List<TimeEntry> list();
TimeEntry update(long id, TimeEntry timeEntryupdated);

}
