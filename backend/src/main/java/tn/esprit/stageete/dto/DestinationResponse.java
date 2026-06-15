package tn.esprit.stageete.dto;

public class DestinationResponse {
    private Long id;
    private String nom;
    private String description;
    private String region;
    private String categorie;
    private Double prix;
    private String imageUrl;

    public DestinationResponse() {}

    public DestinationResponse(Long id, String nom, String description, String region,
                               String categorie, Double prix, String imageUrl) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.region = region;
        this.categorie = categorie;
        this.prix = prix;
        this.imageUrl = imageUrl;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }
    public String getCategorie() { return categorie; }
    public void setCategorie(String categorie) { this.categorie = categorie; }
    public Double getPrix() { return prix; }
    public void setPrix(Double prix) { this.prix = prix; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}