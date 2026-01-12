package com.movie.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movie.Entity.Movie;
import com.movie.Model.ResponseMessage;
import com.movie.Service.MovieService;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movies")
    public ResponseEntity<ResponseMessage> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();
        return ResponseEntity.ok(new ResponseMessage(200, "Movies fetched successfully", movies));
    }

    @PutMapping("/book/{movieName}")
    public ResponseEntity<ResponseMessage> bookTicket(
            @PathVariable String movieName,
            @RequestParam int quantity) {

        try {
            movieService.bookTicket(movieName, quantity);
            return ResponseEntity.ok(new ResponseMessage(200, "Ticket booked successfully", null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage(400, e.getMessage(), null));
        }
    }
}
