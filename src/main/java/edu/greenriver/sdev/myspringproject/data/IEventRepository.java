package edu.greenriver.sdev.myspringproject.data;

import edu.greenriver.sdev.myspringproject.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Interface for the Event model that creates a repository with CRUD access to Event database records
 * @author Ryan Rivera
 * @version 1.0
 */
@Repository
public interface IEventRepository extends JpaRepository<Event, Integer>
{
    /**
     * Returns an Event object from the repo if it matches the id
     * @param id int Event id
     * @return Event object matching id param
     */
    Event findEventById(int id);

    /**
     * Returns all Event objects from the repo that match the state
     * @param state String Event state
     * @return List<String> List of Event objects matching state
     */
    List<Event> findEventByState(String state);

    /**
     * Returns all Event objects from the repo that match the city
     * @param city String Event state
     * @return List<String> List of Event objects matching city
     */
    List<Event> findEventByCity(String city);
}