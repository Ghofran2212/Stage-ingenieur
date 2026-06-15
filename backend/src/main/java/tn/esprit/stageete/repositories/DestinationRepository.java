package tn.esprit.stageete.repositories;

import tn.esprit.stageete.Domain.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {
    List<Destination> findByRegion(String region);
    List<Destination> findByCategorie(String categorie);
    List<Destination> findByPrixBetween(Double min, Double max);
}