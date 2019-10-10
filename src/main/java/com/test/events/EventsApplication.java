package com.test.events;

import com.test.events.model.Event;
import com.test.events.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.time.LocalDate;

@SpringBootApplication
public class EventsApplication {
    @Autowired
    private EventRepository eventRepository;

    /**
     * Create the main method in which we launch our application.
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run( EventsApplication.class, args );
    }

    /**
     * Create the run method; mark this method with annotation
     * EventListener in order to be launched along with the launch of the application.
     */
    @EventListener(ApplicationReadyEvent.class)
    public void run() {
        addEvent();
    }

    /**
     * Create the addEvent method in which the event will be created
     */
    private void addEvent() {
        Event event = new Event();
        event.setId( 1L );
        event.setName( "Встреча с начальством" );
        event.setEventType( "встреча" );
        event.setDate( LocalDate.of( 2019, 9, 25 ) );
        event = eventRepository.save( event );
    }
}
