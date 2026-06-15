package tn.esprit.stageete.service;

import tn.esprit.stageete.Domain.Avis;
import java.util.List;

public interface AvisService {
    List<Avis> findAll();
    Avis findById(Long id);
    Avis save(Avis avis);
    Avis update(Long id, Avis avis);
    void delete(Long id);
    List<Avis> findByDestinationId(Long destinationId);
    List<Avis> findByUserId(Long userId);
}