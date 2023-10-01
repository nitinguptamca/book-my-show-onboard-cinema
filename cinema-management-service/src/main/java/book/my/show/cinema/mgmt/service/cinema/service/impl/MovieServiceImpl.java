package book.my.show.cinema.mgmt.service.cinema.service.impl;

import book.my.show.cinema.mgmt.service.cinema.Entity.Movie;
import book.my.show.cinema.mgmt.service.cinema.repository.MovieRepository;
import book.my.show.cinema.mgmt.service.cinema.service.MovieService;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void create(Movie movie) {
        movieRepository.save(movie);
    }
}
