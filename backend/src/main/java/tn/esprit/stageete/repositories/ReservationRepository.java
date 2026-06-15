package tn.esprit.stageete.repositories;

import tn.esprit.stageete.Domain.Reservation;
import tn.esprit.stageete.Domain.StatutReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUserId(Long userId);
    List<Reservation> findByDestinationId(Long destinationId);
    List<Reservation> findByStatut(StatutReservation statut);
    boolean existsByHotelId(Long hotelId);
}