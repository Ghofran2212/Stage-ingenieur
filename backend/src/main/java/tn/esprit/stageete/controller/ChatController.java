package tn.esprit.stageete.controller;

import tn.esprit.stageete.Domain.Destination;
import tn.esprit.stageete.Domain.Hotel;
import tn.esprit.stageete.dto.ChatRequest;
import tn.esprit.stageete.repositories.DestinationRepository;
import tn.esprit.stageete.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin("*")
public class ChatController {

    @Autowired
    private DestinationRepository destinationRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @PostMapping
    public String chat(@RequestBody ChatRequest request) {

        String msg = request.getMessage().toLowerCase().trim();
        System.out.println("QUESTION = " + msg);

        // ── Salutations ──────────────────────────────────────────────────
        if (msg.contains("bonjour") || msg.contains("salut")
                || msg.contains("bonsoir") || msg.equals("hello") || msg.equals("hi")) {
            return "👋 Bonjour ! Je suis votre assistant touristique TourismeTN.\n\n"
                    + "Je peux vous aider à trouver :\n"
                    + "📍 Des destinations\n"
                    + "🏨 Des hôtels\n"
                    + "🏖️ Des plages\n"
                    + "🐪 Le désert\n\n"
                    + "Posez-moi une question !";
        }

        // ── Hôtel pas cher (AVANT "hotel" pour éviter le mauvais match) ──
        if (msg.contains("pas cher") || msg.contains("moins cher")
                || msg.contains("economique") || msg.contains("économique")
                || msg.contains("budget")) {

            List<Hotel> hotels = hotelRepository.findAll();
            if (hotels.isEmpty()) return "Aucun hôtel disponible.";

            Hotel moinsCher = hotels.stream()
                    .min((a, b) -> Double.compare(a.getPrixParNuit(), b.getPrixParNuit()))
                    .orElse(hotels.get(0));

            return "💰 Hôtel le moins cher : **" + moinsCher.getNom()
                    + "** à " + moinsCher.getPrixParNuit() + " DT/nuit";
        }

        // ── Hôtels ───────────────────────────────────────────────────────
        if (msg.contains("hotel") || msg.contains("hôtel")
                || msg.contains("hébergement") || msg.contains("hebergement")
                || msg.contains("loger") || msg.contains("nuit")) {

            List<Hotel> hotels = hotelRepository.findAll();
            if (hotels.isEmpty()) return "Aucun hôtel disponible pour le moment.";

            StringBuilder rep = new StringBuilder("🏨 Hôtels disponibles :\n\n");
            for (Hotel h : hotels) {
                rep.append("🏨 ").append(h.getNom())
                        .append(" — ").append(h.getPrixParNuit()).append(" DT/nuit\n");
            }
            return rep.toString();
        }

        // ── Plage ────────────────────────────────────────────────────────
        if (msg.contains("plage") || msg.contains("mer") || msg.contains("baignade")
                || msg.contains("balnéaire") || msg.contains("balneaire")) {

            List<Destination> destinations = destinationRepository.findByCategorie("Plage");
            if (destinations.isEmpty())
                return "Aucune destination plage disponible pour le moment.";

            StringBuilder rep = new StringBuilder("🏖️ Destinations plage recommandées :\n\n");
            for (Destination d : destinations) {
                rep.append("📍 ").append(d.getNom())
                        .append(" — ").append(d.getRegion())
                        .append(" | ").append(d.getPrix()).append(" DT\n");
            }
            return rep.toString();
        }

        // ── Désert ───────────────────────────────────────────────────────
        if (msg.contains("desert") || msg.contains("désert")
                || msg.contains("sahara") || msg.contains("tozeur")
                || msg.contains("douz")) {
            return "🐪 Destinations désertiques :\n\n"
                    + "📍 Tozeur\n📍 Douz\n\n"
                    + "Découvrez le Sahara tunisien, ses dunes et ses oasis !";
        }

        // ── Famille (AVANT "destination" pour éviter mauvais match) ──────
        if (msg.contains("famille") || msg.contains("enfant")
                || msg.contains("enfants") || msg.contains("kids")) {
            return "👨‍👩‍👧‍👦 Destinations idéales pour une famille :\n\n"
                    + "📍 Djerba — plages calmes, parcs aquatiques\n"
                    + "📍 Hammamet — stations balnéaires familiales\n"
                    + "📍 Sousse — corniche et activités variées\n\n"
                    + "Toutes ces destinations offrent de nombreuses activités pour les enfants.";
        }

        // ── Montagne / Nature ─────────────────────────────────────────────
        if (msg.contains("montagne") || msg.contains("nature")
                || msg.contains("randonnée") || msg.contains("randonnee")) {
            List<Destination> destinations = destinationRepository.findByCategorie("Montagne");
            if (destinations.isEmpty())
                return "Pas encore de destinations montagne. Essayez : plage, désert, culture !";

            StringBuilder rep = new StringBuilder("⛰️ Destinations montagne :\n\n");
            for (Destination d : destinations) {
                rep.append("📍 ").append(d.getNom()).append(" — ").append(d.getRegion()).append("\n");
            }
            return rep.toString();
        }

        // ── Culture / Histoire ────────────────────────────────────────────
        if (msg.contains("culture") || msg.contains("histoire")
                || msg.contains("historique") || msg.contains("musée")
                || msg.contains("musee") || msg.contains("carthage")
                || msg.contains("tunis")) {
            List<Destination> destinations = destinationRepository.findByCategorie("Culture");
            if (destinations.isEmpty())
                return "🏛️ Destinations culturelles : Tunis, Carthage, Kairouan — bientôt disponibles !";

            StringBuilder rep = new StringBuilder("🏛️ Destinations culturelles :\n\n");
            for (Destination d : destinations) {
                rep.append("📍 ").append(d.getNom()).append(" — ").append(d.getRegion()).append("\n");
            }
            return rep.toString();
        }

        // ── Vacances / Voyage ─────────────────────────────────────────────
        if (msg.contains("vacances") || msg.contains("voyage")
                || msg.contains("voyager") || msg.contains("partir")
                || msg.contains("séjour") || msg.contains("sejour")) {

            List<Destination> destinations = destinationRepository.findAll();
            if (destinations.isEmpty())
                return "Aucune destination disponible pour le moment.";

            StringBuilder rep = new StringBuilder("🌴 Meilleures destinations pour vos vacances :\n\n");
            int count = 0;
            for (Destination d : destinations) {
                if (count++ >= 5) break;
                rep.append("📍 ").append(d.getNom())
                        .append(" — ").append(d.getRegion())
                        .append(" | ").append(d.getPrix()).append(" DT\n");
            }
            return rep.toString();
        }

        // ── Liste toutes destinations ─────────────────────────────────────
        if (msg.contains("destination") || msg.contains("destinations")
                || msg.contains("endroit") || msg.contains("visiter")
                || msg.contains("voir")) {

            List<Destination> destinations = destinationRepository.findAll();
            if (destinations.isEmpty()) return "Aucune destination disponible.";

            StringBuilder rep = new StringBuilder("📍 Toutes nos destinations :\n\n");
            for (Destination d : destinations) {
                rep.append("📍 ").append(d.getNom())
                        .append(" — ").append(d.getRegion())
                        .append(" | ").append(d.getPrix()).append(" DT\n");
            }
            return rep.toString();
        }

        // ── Merci / Au revoir ─────────────────────────────────────────────
        if (msg.contains("merci") || msg.contains("au revoir")
                || msg.contains("bye") || msg.contains("bonne journée")) {
            return "😊 Avec plaisir ! Bonne visite en Tunisie 🇹🇳";
        }

        // ── Réponse par défaut ────────────────────────────────────────────
        return "Je n'ai pas compris votre question 😊\n\n"
                + "Essayez l'une de ces questions :\n"
                + "• Je cherche une destination plage\n"
                + "• Je voyage en famille\n"
                + "• Je veux visiter le désert\n"
                + "• Recommande-moi un hôtel\n"
                + "• Hôtel pas cher\n"
                + "• Quelles sont les destinations disponibles ?";
    }
}