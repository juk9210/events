package com.test.events.controller;

import com.test.events.model.Event;
import com.test.events.repository.EventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Describe the controller for the entity event that will handle REST requests.
 *
 * @author Shakhov Yevhen
 */
@Slf4j
@RestController
@RequestMapping("/api/events")
public class EventRestController {

    @Autowired
    private EventRepository eventRepository;

    /**
     * Handler for a GET request. Get the event by id.
     *
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public ResponseEntity<Event> getEvent(@PathVariable Long id) {
        Optional<Event> event = eventRepository.findById( id );
        log.info( "Get event by id " + id + " : " + event.toString() );
        return new ResponseEntity<>( eventRepository.findById( id ).orElse( null ), HttpStatus.OK );
    }

    /**
     * The handler for the POST request. Will save a new event.
     *
     * @param event
     * @return
     */
    @PostMapping
    public ResponseEntity<Event> saveEvent(@RequestBody @Valid Event event) {
        Event savedBook = eventRepository.save( event );
        log.info( "Save event: " + savedBook.toString() );
        return new ResponseEntity<>( savedBook, HttpStatus.CREATED );
    }

    /**
     * The handler for the PUT request. Will update the event by id.
     *
     * @param event
     * @return
     */
    @PutMapping("{id}")
    public ResponseEntity<Event> updateEvent(@RequestBody @Valid Event event) {
        Event savedBook = eventRepository.save( event );
        log.info( "Update event: " + savedBook.toString() );
        return new ResponseEntity<>( savedBook, HttpStatus.OK );
    }

    /**
     * The handler for the DELETE request. Delete the event by id.
     *
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Event> removeEvent(@PathVariable Long id) {
        eventRepository.deleteById( id );
        log.info( "Remove event by id: " + id );
        return new ResponseEntity<>( HttpStatus.OK );
    }

    /**
     * Handler for a GET request. Get the whole list of our events.
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<Iterable<Event>> getAllEvents() {
        log.info( "Get all events" );
        return new ResponseEntity<>( eventRepository.findAll(), HttpStatus.OK );
    }

    /**
     * Handler for a GET request.Get events by their type.
     *
     * @param eventType
     * @return
     */
    @GetMapping("/search")
    public ResponseEntity<List<Event>> findEventsByEventType(@RequestParam("eventType") String eventType) {
        List<Event> events = eventRepository.findEventsByEventType( eventType );
        log.info( "Find events by event type - " + eventType + " : " + events.toString() );
        return new ResponseEntity<>( events, HttpStatus.OK );
    }
}
