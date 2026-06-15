package tn.esprit.stageete.repositories;

import tn.esprit.stageete.Domain.Avis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AvisRepository extends JpaRepository<Avis, Long> {
    List<Avis> findByDestinationId(Long destinationId);
    List<Avis> findByUserId(Long userId);
    boolean existsByUserIdAndDestinationId(Long userId, Long destinationId);
}