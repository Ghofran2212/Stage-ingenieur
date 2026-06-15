package tn.esprit.stageete.dto;

import tn.esprit.stageete.Domain.StatutReservation;
import java.time.LocalDate;

public class ReservationResponse {

    private Long id;

    private String destinationNom;
    private String destinationImageUrl;

    private String userEmail;

    private LocalDate dateDebut;
    private LocalDate dateFin;

    private Integer nombrePersonnes;

    private StatutReservation statut;

    // HOTEL
    private String hotelNom;
    private Double hotelPrix;

    public ReservationResponse() {
    }

    public ReservationResponse(
            Long id,
            String destinationNom,
            String destinationImageUrl,
            String userEmail,
            LocalDate dateDebut,
            LocalDate dateFin,
            Integer nombrePersonnes,
            StatutReservation statut,
            String hotelNom,
            Double hotelPrix
    ) {
        this.id = id;
        this.destinationNom = destinationNom;
        this.destinationImageUrl = destinationImageUrl;
        this.userEmail = userEmail;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nombrePersonnes = nombrePersonnes;
        this.statut = statut;
        this.hotelNom = hotelNom;
        this.hotelPrix = hotelPrix;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDestinationNom() {
        return destinationNom;
    }

    public void setDestinationNom(String destinationNom) {
        this.destinationNom = destinationNom;
    }

    public String getDestinationImageUrl() {
        return destinationImageUrl;
    }

    public void setDestinationImageUrl(String destinationImageUrl) {
        this.destinationImageUrl = destinationImageUrl;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public Integer getNombrePersonnes() {
        return nombrePersonnes;
    }

    public void setNombrePersonnes(Integer nombrePersonnes) {
        this.nombrePersonnes = nombrePersonnes;
    }

    public StatutReservation getStatut() {
        return statut;
    }

    public void setStatut(StatutReservation statut) {
        this.statut = statut;
    }

    public String getHotelNom() {
        return hotelNom;
    }

    public void setHotelNom(String hotelNom) {
        this.hotelNom = hotelNom;
    }

    public Double getHotelPrix() {
        return hotelPrix;
    }

    public void setHotelPrix(Double hotelPrix) {
        this.hotelPrix = hotelPrix;
    }
}