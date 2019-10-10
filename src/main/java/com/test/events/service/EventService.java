package com.test.events.service;

import com.test.events.model.Event;
import com.test.events.repository.EventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create a service class in which will describe the basic logic and work not directly with entities, but with
 * repositories
 *
 * @author Shakhov Yevhen
 */
@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    /**
     * Create a method findByEventType through which we will select events by type.
     *
     * @param eventType
     * @return
     */
    public List<Event> findEventsByEventType(String eventType) {
        return eventRepository.findEventsByEventType( eventType );
    }
}
