package com.azeem.demo.services;

import com.azeem.demo.entity.Events;
import com.azeem.demo.repository.EventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventsServiceImplementation implements EventsService{
    private EventsRepository eventsRepository;

    @Autowired
    public EventsServiceImplementation(EventsRepository eventsRepository){

        this.eventsRepository = eventsRepository;
    }

    @Override
    public List<Events> listEvents() {

        return eventsRepository.findAll();
    }

    @Override
    public Events getEventById(int id) {
        Optional<Events> result = Optional.of(eventsRepository.getById(id));

        Events theEvent = null;

        if (result.isPresent()) {
            theEvent = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find event id - " + id);
        }

        return theEvent;
    }

    @Override
    public void saveEvent(Events event) {

        eventsRepository.save(event);
    }

    @Override
    public void deleteEvent(int id) {

        eventsRepository.deleteById(id);
    }
}
