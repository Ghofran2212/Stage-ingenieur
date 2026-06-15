package tn.esprit.stageete.service;

import tn.esprit.stageete.Domain.Hotel;

import java.util.List;

public interface HotelService {

    List<Hotel> findAll();

    Hotel findById(Long id);

    Hotel save(Hotel hotel);

    Hotel update(Long id, Hotel hotel);

    void delete(Long id);

    List<Hotel> findByDestinationId(Long destinationId);
}