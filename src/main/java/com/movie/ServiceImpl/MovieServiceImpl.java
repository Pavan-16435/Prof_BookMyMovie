package com.movie.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.Entity.Movie;
import com.movie.Entity.Ticket;
import com.movie.Repository.MovieRepository;
import com.movie.Repository.TicketRepository;
import com.movie.Service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public Movie addNewMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll().stream()
                .filter(m -> m.getTickets() > 0)
                .collect(Collectors.toList());
    }

    @Override
    public boolean bookTicket(String movieName, int quantity) {
        Movie movie = movieRepository.findByNameIgnoreCase(movieName)
                .orElseThrow(() -> new IllegalArgumentException("Movie not found"));

        if (movie.getTickets() < quantity) {
            throw new RuntimeException("Not enough tickets available");
        }

        movie.setTickets(movie.getTickets() - quantity);
        movieRepository.save(movie);

        Ticket ticket = new Ticket(movie.getName(), quantity);
        ticketRepository.save(ticket);
        return true;
    }
    
    
    @Override
    public void deleteMovie(Long id) {
        if (!movieRepository.existsById(id)) {
            throw new RuntimeException("Movie not found with ID: " + id);
        }
        movieRepository.deleteById(id);
    }

}
