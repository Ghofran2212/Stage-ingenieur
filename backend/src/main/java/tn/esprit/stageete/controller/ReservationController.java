package tn.esprit.stageete.controller;

import tn.esprit.stageete.Domain.Reservation;
import tn.esprit.stageete.Domain.StatutReservation;
import tn.esprit.stageete.Domain.User;
import tn.esprit.stageete.dto.ReservationRequest;
import tn.esprit.stageete.dto.ReservationResponse;
import tn.esprit.stageete.repositories.UserRepository;
import tn.esprit.stageete.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reservations")
@CrossOrigin(origins = "http://localhost:4200")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private UserRepository userRepository;

    private ReservationResponse mapToResponse(Reservation r) {

        String hotelNom = null;
        Double hotelPrix = null;

        if (r.getHotel() != null) {
            hotelNom = r.getHotel().getNom();
            hotelPrix = r.getHotel().getPrixParNuit();
        }

        return new ReservationResponse(
                r.getId(),
                r.getDestination().getNom(),
                r.getDestination().getImageUrl(),
                r.getUser().getEmail(),
                r.getDateDebut(),
                r.getDateFin(),
                r.getNombrePersonnes(),
                r.getStatut(),
                hotelNom,
                hotelPrix
        );
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ReservationResponse>> findAll() {

        List<ReservationResponse> responses = reservationService.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @reservationService.isOwner(#id, authentication.name)")
    public ResponseEntity<ReservationResponse> findById(@PathVariable Long id) {

        return ResponseEntity.ok(
                mapToResponse(
                        reservationService.findById(id)
                )
        );
    }

    @PostMapping
    public ResponseEntity<ReservationResponse> save(
            @RequestBody ReservationRequest request,
            Authentication authentication) {

        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() ->
                        new RuntimeException("Utilisateur non trouvé"));

        Reservation reservation =
                reservationService.createReservation(request, user);

        return ResponseEntity.ok(
                mapToResponse(reservation)
        );
    }

    @PatchMapping("/{id}/statut")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ReservationResponse> updateStatut(
            @PathVariable Long id,
            @RequestParam StatutReservation statut) {

        return ResponseEntity.ok(
                mapToResponse(
                        reservationService.updateStatut(id, statut)
                )
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @reservationService.isOwner(#id, authentication.name)")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        reservationService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/mes-reservations")
    public ResponseEntity<List<ReservationResponse>> getMesReservations(
            Authentication authentication) {

        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() ->
                        new RuntimeException("Utilisateur non trouvé"));

        List<ReservationResponse> responses =
                reservationService.findByUserId(user.getId())
                        .stream()
                        .map(this::mapToResponse)
                        .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/destination/{destinationId}")
    public ResponseEntity<List<ReservationResponse>> findByDestinationId(
            @PathVariable Long destinationId) {

        List<ReservationResponse> responses =
                reservationService.findByDestinationId(destinationId)
                        .stream()
                        .map(this::mapToResponse)
                        .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/statut/{statut}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ReservationResponse>> findByStatut(
            @PathVariable StatutReservation statut) {

        List<ReservationResponse> responses =
                reservationService.findByStatut(statut)
                        .stream()
                        .map(this::mapToResponse)
                        .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }
}