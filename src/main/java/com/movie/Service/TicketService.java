package com.movie.Service;

import com.movie.Entity.Ticket;

public interface TicketService {
    Ticket bookTicket(String movieName, int quantity);
}
