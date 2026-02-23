package com.movie.Service;

import java.util.List;

import com.movie.Entity.Movie;

public interface MovieService {
    Movie addNewMovie(Movie movie);
    List<Movie> getAllMovies();
    boolean bookTicket(String movieName, int quantity);
    
    void deleteMovie(Long id);
    public Movie updateMovie(Long id, Movie newMovie);
}