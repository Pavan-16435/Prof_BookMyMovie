	package com.movie.ServiceImpl;
	
	
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;
	
	import com.movie.Entity.Ticket;
	import com.movie.Repository.TicketRepository;
	import com.movie.Service.TicketService;
	
	@Service
	public class TicketServiceImpl implements TicketService {
	
	    @Autowired
	    private TicketRepository ticketRepository;
	
	    @Override
	    public Ticket bookTicket(String movieName, int quantity) {
	        Ticket ticket = new Ticket(movieName, quantity);
	        return ticketRepository.save(ticket);
	    }
	}
