package com.zawartkawoj.upcomingevents.service;

import com.zawartkawoj.upcomingevents.dto.EventDto;
import com.zawartkawoj.upcomingevents.entity.Event;
import com.zawartkawoj.upcomingevents.repository.EventRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event findById(int id) {
        return eventRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User with id: " + id + " does not exist."));
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event addOrUpdateEvent(EventDto eventDto) {
        Event event = new Event(
                eventDto.getName(),
                eventDto.getNote(),
                eventDto.getDate());
        eventRepository.save(event);
        return event;
    }

    public boolean eventExists(int id) {
        for (Event event : getAllEvents()) {
            if(event.getId() == id) return true;
        }
        return false;
    }
}
