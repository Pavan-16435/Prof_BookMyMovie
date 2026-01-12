package com.movie.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.Entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
