package com.movie.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.Entity.Movie;
import com.movie.Model.ResponseMessage;
import com.movie.Service.MovieService;


@RestController
@RequestMapping("/admin/movies")
public class AdminController {

    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity<ResponseMessage> addMovie(@RequestBody Movie movie) {
        Movie savedMovie = movieService.addNewMovie(movie);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseMessage(201, "Movie added successfully", savedMovie));
    }

    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> deleteMovie(@PathVariable Long id) {
        try {
            movieService.deleteMovie(id);
            return ResponseEntity.ok(new ResponseMessage(200, "Movie deleted successfully", null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage(400, e.getMessage(), null));
        }
    }
}

