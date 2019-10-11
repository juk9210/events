package com.test.events;

import com.test.events.model.Event;
import com.test.events.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Class to run the application
 *
 * @author Shakhov Yevhen
 */

@EnableScheduling
@SpringBootApplication
public class EventsApplication {
    private static final Logger log = Logger.getLogger( EventsApplication.class.getName() );
    @Autowired
    private EventRepository eventRepository;

    /**
     * Create the main method in which we launch our application.
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run( EventsApplication.class, args );
        /**
         *Connect the settings to the logger. Otherwise, it will give an error
         */
        try {
            LogManager.getLogManager().readConfiguration( EventsApplication.class.
                    getResourceAsStream( "/logging.properties" ) );
        } catch (IOException e) {
            System.err.println( "Could not setup logger configuration: " + e.toString() );
        }
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
        event.setName( "Конференция с начальством" );
        event.setEventType( "конференция" );
        event.setDate( LocalDate.of( 2019, 9, 25 ) );
        event = eventRepository.save( event );

        Event event1 = new Event();
        event1.setId( 2L );
        event1.setName( "Встреча с друзьями" );
        event1.setEventType( "встреча" );
        event1.setDate( LocalDate.of( 2019, 10, 11 ) );
        event1 = eventRepository.save( event1 );
    }

    /**
     * Create a method remindEvent in which will search for events by the current date and log into a file log.
     * The method will fire every day at 0:00.
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void remindEvent() {
        LocalDate nowDate = LocalDate.now();
        List<Event> events = eventRepository.findEventsByDate( nowDate );
        for (Event event : events) {
            log.info( "Event have come : " + event.toString() );
        }
    }
}
