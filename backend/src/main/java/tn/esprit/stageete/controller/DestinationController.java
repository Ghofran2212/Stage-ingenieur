package tn.esprit.stageete.controller;

import tn.esprit.stageete.Domain.Destination;
import tn.esprit.stageete.dto.DestinationRequest;
import tn.esprit.stageete.dto.DestinationResponse;
import tn.esprit.stageete.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/destinations")
public class DestinationController {

    @Autowired
    private DestinationService destinationService;

    private DestinationResponse mapToResponse(Destination d) {
        return new DestinationResponse(
                d.getId(),
                d.getNom(),
                d.getDescription(),
                d.getRegion(),
                d.getCategorie(),
                d.getPrix(),
                d.getImageUrl()
        );
    }

    private Destination mapToEntity(DestinationRequest req) {
        Destination d = new Destination();
        d.setNom(req.getNom());
        d.setDescription(req.getDescription());
        d.setRegion(req.getRegion());
        d.setCategorie(req.getCategorie());
        d.setPrix(req.getPrix());
        d.setImageUrl(req.getImageUrl());
        return d;
    }

    @GetMapping
    public ResponseEntity<List<DestinationResponse>> findAll() {
        List<DestinationResponse> responses = destinationService.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DestinationResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(mapToResponse(destinationService.findById(id)));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DestinationResponse> save(@RequestBody DestinationRequest request) {
        Destination saved = destinationService.save(mapToEntity(request));
        return ResponseEntity.ok(mapToResponse(saved));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DestinationResponse> update(@PathVariable Long id,
                                                      @RequestBody DestinationRequest request) {
        Destination updated = destinationService.update(id, mapToEntity(request));
        return ResponseEntity.ok(mapToResponse(updated));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        destinationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/region/{region}")
    public ResponseEntity<List<DestinationResponse>> findByRegion(@PathVariable String region) {
        List<DestinationResponse> responses = destinationService.findByRegion(region)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/categorie/{categorie}")
    public ResponseEntity<List<DestinationResponse>> findByCategorie(@PathVariable String categorie) {
        List<DestinationResponse> responses = destinationService.findByCategorie(categorie)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/prix")
    public ResponseEntity<List<DestinationResponse>> findByPrix(@RequestParam Double min,
                                                                @RequestParam Double max) {
        List<DestinationResponse> responses = destinationService.findByPrixBetween(min, max)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }
}