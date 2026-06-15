package tn.esprit.stageete.Domain;  // ← même package que Destination

import jakarta.persistence.*;
import java.time.LocalDate;

// Pas d'import pour User ni Destination ← même package !

@Entity
@Table(name = "avis")
public class Avis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;               // ← même package, pas besoin d'import

    @ManyToOne
    @JoinColumn(name = "destination_id", nullable = false)
    private Destination destination; // ← même package, pas besoin d'import

    @Column(nullable = false)
    private Integer note;

    private String commentaire;

    private LocalDate dateAvis = LocalDate.now();

    public Avis() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Destination getDestination() { return destination; }
    public void setDestination(Destination destination) { this.destination = destination; }

    public Integer getNote() { return note; }
    public void setNote(Integer note) { this.note = note; }

    public String getCommentaire() { return commentaire; }
    public void setCommentaire(String commentaire) { this.commentaire = commentaire; }

    public LocalDate getDateAvis() { return dateAvis; }
    public void setDateAvis(LocalDate dateAvis) { this.dateAvis = dateAvis; }
}