package com.test.events.repository;

import com.test.events.model.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Create the EventRepository interface for interacting with the database and Spring Data.
 * This repository will work with the entity class Event.
 *
 * @author Shakhov Yevhen
 */
@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
    /**
     * Create a method findByEventType with which will select according to the type of events.
     *
     * @param eventType
     * @return
     */
    List<Event> findEventsByEventType(String eventType);
}
