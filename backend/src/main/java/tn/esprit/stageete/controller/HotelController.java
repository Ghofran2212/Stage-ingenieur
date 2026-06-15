package tn.esprit.stageete.controller;
import tn.esprit.stageete.dto.HotelRequest;
import tn.esprit.stageete.Domain.Destination;
import tn.esprit.stageete.repositories.DestinationRepository;
import tn.esprit.stageete.Domain.Hotel;
import tn.esprit.stageete.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {
    @Autowired
    private DestinationRepository destinationRepository;
    @Autowired
    private HotelService hotelService;

    @GetMapping
    public ResponseEntity<List<Hotel>> findAll() {
        return ResponseEntity.ok(
                hotelService.findAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> findById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                hotelService.findById(id)
        );
    }

    @GetMapping("/destination/{destinationId}")
    public ResponseEntity<List<Hotel>> findByDestination(
            @PathVariable Long destinationId) {

        return ResponseEntity.ok(
                hotelService.findByDestinationId(destinationId)
        );
    }

    @PostMapping
    public ResponseEntity<Hotel> save(
            @RequestBody HotelRequest request) {

        Destination destination =
                destinationRepository.findById(
                                request.getDestinationId())
                        .orElseThrow(() ->
                                new RuntimeException("Destination introuvable"));

        Hotel hotel = new Hotel();

        hotel.setNom(request.getNom());
        hotel.setEtoiles(request.getEtoiles());
        hotel.setPrixParNuit(request.getPrixParNuit());
        hotel.setImageUrl(request.getImageUrl());
        hotel.setDestination(destination);

        return ResponseEntity.ok(
                hotelService.save(hotel)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hotel> update(
            @PathVariable Long id,
            @RequestBody Hotel hotel) {

        return ResponseEntity.ok(
                hotelService.update(id, hotel)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        hotelService.delete(id);

        return ResponseEntity.noContent().build();
    }
}