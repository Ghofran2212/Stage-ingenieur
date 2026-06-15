package tn.esprit.stageete.controller;

import tn.esprit.stageete.Domain.Avis;
import tn.esprit.stageete.service.AvisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/avis")
public class AvisController {

    @Autowired
    private AvisService avisService;

    @GetMapping
    public ResponseEntity<List<Avis>> findAll() {
        return ResponseEntity.ok(avisService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Avis> findById(@PathVariable Long id) {
        return ResponseEntity.ok(avisService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Avis> save(@RequestBody Avis avis) {
        return ResponseEntity.ok(avisService.save(avis));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Avis> update(@PathVariable Long id, @RequestBody Avis avis) {
        return ResponseEntity.ok(avisService.update(id, avis));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        avisService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/destination/{destinationId}")
    public ResponseEntity<List<Avis>> findByDestinationId(@PathVariable Long destinationId) {
        return ResponseEntity.ok(avisService.findByDestinationId(destinationId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Avis>> findByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(avisService.findByUserId(userId));
    }
}