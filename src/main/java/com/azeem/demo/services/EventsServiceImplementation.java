package com.azeem.demo.services;

import com.azeem.demo.dto.EventsDTO;
import com.azeem.demo.entity.Events;
import com.azeem.demo.repository.EventsRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventsServiceImplementation implements EventsService{
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EventsRepository eventsRepository;

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
    public Events saveEvent(Events event) {

        eventsRepository.save(event);

        return event;
    }

    @Override
    public void deleteEvent(int id) {

        eventsRepository.deleteById(id);
    }

    @Override
    public List<EventsDTO> getAllEvents() {
        return eventsRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    private EventsDTO convertEntityToDto(Events events){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        EventsDTO eventsDTO;
        eventsDTO = modelMapper.map(events, EventsDTO.class);
        return eventsDTO;
    }

    private Events convertDtoToEntity(EventsDTO eventsDTO){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        Events events;
        events = modelMapper.map(eventsDTO, Events.class);
        return events;
    }
}
