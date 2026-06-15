package tn.esprit.stageete.repositories;

import tn.esprit.stageete.Domain.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    List<Hotel> findByDestinationId(Long destinationId);

}