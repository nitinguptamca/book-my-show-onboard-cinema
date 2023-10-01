package book.my.show.cinema.mgmt.service.cinema.rest;

import book.my.show.cinema.mgmt.service.cinema.Entity.Cinema;
import book.my.show.cinema.mgmt.service.cinema.Entity.CinemaOwner;
import book.my.show.cinema.mgmt.service.cinema.service.CinemaOwnerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cinema/owner")
public class CinemaOwnerController {
    private final CinemaOwnerService cinemaOwnerService;

    public CinemaOwnerController(CinemaOwnerService cinemaOwnerService) {
        this.cinemaOwnerService = cinemaOwnerService;
    }

    @PostMapping
    public void create(@Valid @RequestBody CinemaOwner cinemaOwner){
        cinemaOwnerService.create(cinemaOwner);
    }

}
