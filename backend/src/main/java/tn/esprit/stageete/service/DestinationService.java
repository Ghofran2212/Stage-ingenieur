package tn.esprit.stageete.service;

import tn.esprit.stageete.Domain.Destination;
import java.util.List;

public interface DestinationService {
    List<Destination> findAll();
    Destination findById(Long id);
    Destination save(Destination destination);
    Destination update(Long id, Destination destination);
    void delete(Long id);
    List<Destination> findByRegion(String region);
    List<Destination> findByCategorie(String categorie);
    List<Destination> findByPrixBetween(Double min, Double max);
}