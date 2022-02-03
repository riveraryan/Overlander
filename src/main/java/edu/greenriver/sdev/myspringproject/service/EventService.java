package edu.greenriver.sdev.myspringproject.service;

import edu.greenriver.sdev.myspringproject.data.IEventRepository;
import edu.greenriver.sdev.myspringproject.models.Event;
import edu.greenriver.sdev.myspringproject.models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Service layer for Event class objects
 * @author Ryan Rivera
 * @version 1.0
 */
@Service
public class EventService
{
    private IEventRepository eventRepository;

    /**
     * Constructor
     * @param eventRepository Event repository
     */
    public EventService(IEventRepository eventRepository)
    {
        this.eventRepository = eventRepository;
    }

    /**
     * Returns a list of all Events in the repository
     * @return List<Event> list of all Events in the repository
     */
    public List<Event> allEvents()
    {
        return eventRepository.findAll();
    }

    /**
     * Returns an Event object from the repo if it matches the id
     * @param id int Event id
     * @return Event object matching id param
     */
    public Event findEventById(int id)
    {
        return eventRepository.findEventById(id);
    }

    /**
     * Returns a boolean true if a result is found matching the id, false otherwise
     * @param id String id
     * @return Event results matching the id
     */
    public boolean eventExists(int id)
    {
        return eventRepository.findEventById(id) != null;
    }

    /**
     * Returns all Event objects from the repo that match the state
     * @param state String Event state
     * @return List<String> List of Event objects matching state
     */
    public List<Event> findEventsByState(String state)
    {
        return eventRepository.findEventByState(state);
    }

    /**
     * Returns all Event objects from the repo that match the state
     * @param state String Event state
     * @return List<String> List of Event objects matching state
     */
    public List<Event> findEventsByCity(String state)
    {
        return eventRepository.findEventByCity(state);
    }

    /**
     * Saves the Event parameter to the database
     * @param event Event to be saved to the database
     * @return Event that was saved
     */
    public Event saveEvent(Event event)
    {
        eventRepository.save(event);
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        event.setUser(user);
        System.out.println("saveEvent - " + event.getUser());
        return eventRepository.save(event);
    }

    /**
     * Edits an existing Event in the database if it exists, otherwise an exception is thrown
     * @param event Event with edits
     * @return the Event with edits
     */
    public Event editEvent(Event event)
    {
        if(!eventExists(event.getId())){
            throw new NoSuchElementException("Missing event");
        }
        return eventRepository.save(event);
    }

    /**
     * Deletes an event from the database by the id parameter
     * @param id int event id
     */
    public void deleteEvent(int id)
    {
        if(!eventExists(id)){
            throw new NoSuchElementException("Event not found");
        }
        eventRepository.deleteById(id);
    }
}