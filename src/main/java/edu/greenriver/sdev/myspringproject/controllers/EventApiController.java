package edu.greenriver.sdev.myspringproject.controllers;

import edu.greenriver.sdev.myspringproject.models.Event;
import edu.greenriver.sdev.myspringproject.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controller for the Overlander Event API
 * @author Ryan Rivera
 * @version 1.0
 */
@RestController
@RequestMapping("overlander/api/v1/events")
@CrossOrigin("*")
public class EventApiController
{
    private EventService service;

    /**
     * Constructor
     * @param service Event service layer
     */
    public EventApiController(EventService service)
    {
        this.service = service;
    }

    /**
     * API endpoint that returns all Events in the database
     * @return ResponseEntity<List<Event>> all events in the database
     */
    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents()
    {
        return new ResponseEntity<>(service.allEvents(), HttpStatus.OK);
    }

    /**
     * API endpoint that returns all Events in the database that match the given id
     * @param id Event Id
     * @return ResponseEntity<Event> Event matching the id
     */
    @GetMapping("{id}")
    public ResponseEntity<Event> getEventById(@PathVariable("id") int id)
    {
        if(!service.eventExists(id)){
            return new ResponseEntity<>(service.findEventById(id), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(service.findEventById(id), HttpStatus.OK);
    }

    /**
     * API endpoint that adds a given Event to the database
     * @param event Event object
     * @return ResponseEntity<Event> the Event that was saved
     */
    @PostMapping
    public ResponseEntity<Event> addEvent(@RequestBody Event event)
    {
        if(event == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(service.saveEvent(event), HttpStatus.CREATED);
    }

    /**
     * API endpoint that edits an existing Event object in the database
     * @param event Event object
     * @return ResponseEntity<Event> Event with edits
     */
    @PutMapping
    public ResponseEntity<Event> editEvent(@RequestBody Event event)
    {
        if(event == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(service.editEvent(event), HttpStatus.OK);
    }

    /**
     * API endpoint that deletes an existing Event object in the database
     * @param id int id of Event to delete
     * @return ResponseEntity object with HttpStatus code
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Event> deleteEvent(@PathVariable int id)
    {
        if(!service.eventExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.deleteEvent(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}