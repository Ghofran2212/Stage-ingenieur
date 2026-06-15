package tn.esprit.stageete.service;

import tn.esprit.stageete.Domain.Hotel;
import tn.esprit.stageete.repositories.HotelRepository;
import tn.esprit.stageete.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public List<Hotel> findAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel findById(Long id) {
        return hotelRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Hotel introuvable"));
    }

    @Override
    public Hotel save(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel update(Long id, Hotel hotel) {

        Hotel existing = findById(id);

        existing.setNom(hotel.getNom());
        existing.setEtoiles(hotel.getEtoiles());
        existing.setPrixParNuit(hotel.getPrixParNuit());
        existing.setImageUrl(hotel.getImageUrl());

        return hotelRepository.save(existing);
    }

    @Override
    public void delete(Long id) {

        if (reservationRepository.existsByHotelId(id)) {
            throw new RuntimeException(
                    "Impossible de supprimer cet hôtel car il possède des réservations."
            );
        }

        hotelRepository.deleteById(id);
    }

    @Override
    public List<Hotel> findByDestinationId(Long destinationId) {
        return hotelRepository.findByDestinationId(destinationId);
    }
}