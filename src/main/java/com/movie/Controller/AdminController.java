	package com.movie.Controller;
	
	import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.Entity.Movie;
import com.movie.Model.ResponseMessage;
import com.movie.Service.MovieService;
@CrossOrigin(origins = "*")
	@RestController
	@RequestMapping("/admin/movies")
	public class AdminController {
	
	    @Autowired
	    private MovieService movieService;
	    
	    private void checkAdminRole(String role) {
	        if (!"ADMIN".equals(role)) {
	            throw new RuntimeException("Access denied: Admins only");
	        }
	    }
	
	    @PostMapping
	    public ResponseEntity<ResponseMessage> addMovie(
	            @RequestHeader("Role") String role,
	            @RequestBody Movie movie) {

	        checkAdminRole(role);

	        Movie savedMovie = movieService.addNewMovie(movie);
	        return ResponseEntity.status(HttpStatus.CREATED)
	                .body(new ResponseMessage(201, "Movie added successfully", savedMovie));
	    }
	    
	    @DeleteMapping("/{id}")
	    public ResponseEntity<ResponseMessage> deleteMovie(
	            @RequestHeader("Role") String role,
	            @PathVariable Long id) {

	        checkAdminRole(role);

	        movieService.deleteMovie(id);
	        return ResponseEntity.ok(
	            new ResponseMessage(200, "Movie deleted successfully", null)
	        );
	    }
	    
	    @GetMapping
	    public ResponseEntity<ResponseMessage> getAllMovies() {
	        return ResponseEntity.ok(
	            new ResponseMessage(200, "Movies fetched successfully", movieService.getAllMovies())
	        );
	    }
	    
	    
	    @PutMapping("/{id}")
	    public ResponseEntity<ResponseMessage> updateMovie(
	            @RequestHeader("Role") String role,
	            @PathVariable Long id,
	            @RequestBody Movie movie) {

	        checkAdminRole(role);

	        Movie updated = movieService.updateMovie(id, movie);
	        return ResponseEntity.ok(
	            new ResponseMessage(200, "Movie updated successfully", updated)
	        );
	    }
	}
	
