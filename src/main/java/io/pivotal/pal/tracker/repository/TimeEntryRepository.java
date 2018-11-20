package io.pivotal.pal.tracker.repository;

import io.pivotal.pal.tracker.pojo.TimeEntry;

import java.util.List;

public interface TimeEntryRepository {
void delete(Long id);
TimeEntry create(TimeEntry timeEntry);
TimeEntry find(Long id);
List<TimeEntry> list();
TimeEntry update(Long id, TimeEntry timeEntryupdated);

}
