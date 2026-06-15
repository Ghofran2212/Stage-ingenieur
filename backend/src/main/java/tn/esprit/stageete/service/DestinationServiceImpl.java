package tn.esprit.stageete.service;

import tn.esprit.stageete.Domain.Destination;
import tn.esprit.stageete.repositories.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinationServiceImpl implements DestinationService {

    @Autowired
    private DestinationRepository destinationRepository;

    @Override
    public List<Destination> findAll() {
        return destinationRepository.findAll();
    }

    @Override
    public Destination findById(Long id) {
        return destinationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Destination non trouvée avec l'id : " + id));
    }

    @Override
    public Destination save(Destination destination) {
        return destinationRepository.save(destination);
    }

    @Override
    public Destination update(Long id, Destination destination) {
        Destination existing = findById(id);
        existing.setNom(destination.getNom());
        existing.setDescription(destination.getDescription());
        existing.setRegion(destination.getRegion());
        existing.setCategorie(destination.getCategorie());
        existing.setPrix(destination.getPrix());
        existing.setImageUrl(destination.getImageUrl());
        return destinationRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        findById(id);
        destinationRepository.deleteById(id);
    }

    @Override
    public List<Destination> findByRegion(String region) {
        return destinationRepository.findByRegion(region);
    }

    @Override
    public List<Destination> findByCategorie(String categorie) {
        return destinationRepository.findByCategorie(categorie);
    }

    @Override
    public List<Destination> findByPrixBetween(Double min, Double max) {
        return destinationRepository.findByPrixBetween(min, max);
    }
}