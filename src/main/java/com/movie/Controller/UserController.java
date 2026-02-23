package com.movie.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movie.Entity.Ticket;
import com.movie.Model.ResponseMessage;
import com.movie.Service.MovieService;
import com.movie.Service.TicketService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private TicketService ticketService;

    @GetMapping("/movies")
    public ResponseEntity<ResponseMessage> getAllMovies() {
        return ResponseEntity.ok(
            new ResponseMessage(200, "Movies fetched successfully", movieService.getAllMovies())
        );
    }

    @PutMapping("/book/{movieName}")
    public ResponseEntity<ResponseMessage> bookTicket(
            @PathVariable String movieName,
            @RequestParam int quantity) {

        try {
            // 1️⃣ reduce tickets from movie
            movieService.bookTicket(movieName, quantity);

            // 2️⃣ save ticket record
            Ticket ticket = ticketService.bookTicket(movieName, quantity);

            return ResponseEntity.ok(
                new ResponseMessage(200, "Ticket booked successfully", ticket)
            );

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(new ResponseMessage(400, e.getMessage(), null));
        }
    }
}