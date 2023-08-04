package com.zawartkawoj.upcomingevents.service;

import com.zawartkawoj.upcomingevents.dto.EventDto;
import com.zawartkawoj.upcomingevents.entity.Account;
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
                () -> new UsernameNotFoundException("Event with id: " + id + " does not exist."));
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public void addOrUpdateEvent(EventDto eventDto, Account account) {
        Event event;
        Integer id = eventDto.getId();

        if(id != null && id > 0) {
            event = eventRepository.findById(id).orElseThrow(
                    () -> new UsernameNotFoundException("Event with id: " + id + " does not exist."));
            event.setName(eventDto.getName());
            event.setNote(eventDto.getNote());
            event.setDate(eventDto.getDate());
        } else {
            event = new Event(eventDto.getName(), eventDto.getNote(), eventDto.getDate());
        }
        event.setAccount(account);
        eventRepository.save(event);
    }

    public void deleteEventById(int id) {
        Event event = findById(id);
        eventRepository.delete(event);
    }

}
