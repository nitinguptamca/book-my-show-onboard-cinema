package book.my.show.cinema.mgmt.service.cinema.service.impl;

import book.my.show.cinema.mgmt.service.cinema.Entity.Cinema;
import book.my.show.cinema.mgmt.service.cinema.repository.CinemaRepository;
import book.my.show.cinema.mgmt.service.cinema.service.CinemaService;
import org.springframework.stereotype.Service;

@Service
public class CinemaServiceImpl implements CinemaService {
    private final CinemaRepository cinemaRepository;

    public CinemaServiceImpl(CinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    @Override
    public void create(Cinema cinema) {
        cinemaRepository.save(cinema);
    }
}
