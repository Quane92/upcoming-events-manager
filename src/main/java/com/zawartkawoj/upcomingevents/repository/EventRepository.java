package com.zawartkawoj.upcomingevents.repository;

import com.zawartkawoj.upcomingevents.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {


}
