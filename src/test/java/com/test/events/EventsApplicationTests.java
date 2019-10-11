package com.test.events;

import com.test.events.controller.EventRestController;
import com.test.events.model.Event;
import com.test.events.service.EventService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventsApplicationTests {
    @Autowired
    EventService eventService;
    @Autowired
    EventRestController eventRestController;

    @Test
    public void eventTest() {
        Event event = new Event();
        event.setId( 1L );
        event.setName( "конференция с коллегами" );
        event.setEventType( "конференция" );
        event.setDate( LocalDate.of( 2019, 9, 15 ) );
        Event event1 = new Event();
        event1.setId( 2L );
        event1.setName( "конференция с начальством" );
        event1.setEventType( "конференция" );
        event1.setDate( LocalDate.of( 2019, 10, 20 ) );
        Event event3 = new Event();
        Assert.assertNotNull( event );
        Assert.assertNotEquals( event, event1 );
        Assert.assertNotEquals( event.getId(), event1.getId() );
        Assert.assertNotEquals( event.getName(), event1.getName() );
        Assert.assertEquals( event.getEventType(), event1.getEventType() );
        Assert.assertEquals( event.getEventType(), event1.getEventType() );
        Assert.assertNotEquals( event.getDate(), event1.getDate() );
        Assert.assertNull( event3.getDate() );
    }

    @Test
    public void EventServiceTest() {
        Assert.assertNotNull( eventService.findEventsByEventType( "конференция" ) );
        Assert.assertNotNull( eventService.findEventsByEventType( "встреча" ) );
    }

    @Test
    public void EventRestControllerTest() {
        Assert.assertNotNull( eventRestController.getEvent( 1L ));
        Assert.assertNotNull( eventRestController.removeEvent( 1L ) );
    }
}
