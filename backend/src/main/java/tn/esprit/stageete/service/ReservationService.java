package tn.esprit.stageete.service;

import tn.esprit.stageete.Domain.Reservation;
import tn.esprit.stageete.Domain.StatutReservation;
import tn.esprit.stageete.Domain.User;
import tn.esprit.stageete.dto.ReservationRequest;

import java.util.List;

public interface ReservationService {
    List<Reservation> findAll();
    Reservation findById(Long id);
    Reservation save(Reservation reservation);
    Reservation createReservation(ReservationRequest request, User user);
    Reservation updateStatut(Long id, StatutReservation statut);
    void delete(Long id);
    List<Reservation> findByUserId(Long userId);
    List<Reservation> findByDestinationId(Long destinationId);
    List<Reservation> findByStatut(StatutReservation statut);
    boolean isOwner(Long reservationId, String email);
}