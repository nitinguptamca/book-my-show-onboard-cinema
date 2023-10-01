package book.my.show.cinema.mgmt.service.cinema.service.impl;

import book.my.show.cinema.mgmt.service.cinema.Entity.CinemaOwner;
import book.my.show.cinema.mgmt.service.cinema.repository.CinemaOwnerRepository;
import book.my.show.cinema.mgmt.service.cinema.service.CinemaOwnerService;
import org.springframework.stereotype.Service;

@Service
public class CinemaOwnerServiceImpl implements CinemaOwnerService {
    private final CinemaOwnerRepository cinemaOwnerRepository;

    public CinemaOwnerServiceImpl(CinemaOwnerRepository cinemaOwnerRepository) {
        this.cinemaOwnerRepository = cinemaOwnerRepository;
    }

    @Override
    public void create(CinemaOwner cinemaOwner) {
        cinemaOwnerRepository.save(cinemaOwner);
    }
}
