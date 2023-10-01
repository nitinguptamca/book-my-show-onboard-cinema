package book.my.show.cinema.mgmt.service.cinema.rest;

import book.my.show.cinema.mgmt.service.cinema.Entity.Movie;
import book.my.show.cinema.mgmt.service.cinema.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    private void  create(@Valid @RequestBody Movie movie){
        movieService.create(movie);
    }
}
