package tn.esprit.stageete.service;

import tn.esprit.stageete.Domain.*;
import tn.esprit.stageete.dto.ReservationRequest;
import tn.esprit.stageete.repositories.DestinationRepository;
import tn.esprit.stageete.repositories.HotelRepository;
import tn.esprit.stageete.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("reservationService")
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private DestinationRepository destinationRepository;
    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation findById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Réservation non trouvée avec l'id : " + id));
    }

    @Override
    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation createReservation(ReservationRequest request, User user) {
        Hotel hotel = hotelRepository.findById(request.getHotelId())
                .orElseThrow(() ->
                        new RuntimeException("Hotel introuvable"));
        Destination destination = destinationRepository.findById(request.getDestinationId())
                .orElseThrow(() -> new RuntimeException("Destination non trouvée avec l'id : " + request.getDestinationId()));

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setDestination(destination);
        reservation.setDateDebut(request.getDateDebut());
        reservation.setDateFin(request.getDateFin());
        reservation.setNombrePersonnes(request.getNombrePersonnes());
        reservation.setStatut(StatutReservation.PENDING);
        reservation.setHotel(hotel);

        return reservationRepository.save(reservation);

    }

    @Override
    public Reservation updateStatut(Long id, StatutReservation statut) {
        Reservation reservation = findById(id);
        reservation.setStatut(statut);
        return reservationRepository.save(reservation);
    }

    @Override
    public void delete(Long id) {
        findById(id);
        reservationRepository.deleteById(id);
    }

    @Override
    public List<Reservation> findByUserId(Long userId) {
        return reservationRepository.findByUserId(userId);
    }

    @Override
    public List<Reservation> findByDestinationId(Long destinationId) {
        return reservationRepository.findByDestinationId(destinationId);
    }

    @Override
    public List<Reservation> findByStatut(StatutReservation statut) {
        return reservationRepository.findByStatut(statut);
    }

    @Override
    public boolean isOwner(Long reservationId, String email) {
        return reservationRepository.findById(reservationId)
                .map(r -> r.getUser().getEmail().equals(email))
                .orElse(false);
    }
}