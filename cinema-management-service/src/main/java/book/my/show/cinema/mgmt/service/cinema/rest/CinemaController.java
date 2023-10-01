package book.my.show.cinema.mgmt.service.cinema.rest;

import book.my.show.cinema.mgmt.service.cinema.Entity.Cinema;

import book.my.show.cinema.mgmt.service.cinema.service.CinemaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cinema")
public class CinemaController {
    private final CinemaService cinemaService;

    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @PostMapping
    public void create(@Valid @RequestBody Cinema cinema){
        cinemaService.create(cinema);
    }
}
