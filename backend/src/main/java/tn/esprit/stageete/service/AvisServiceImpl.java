package tn.esprit.stageete.service;

import tn.esprit.stageete.Domain.Avis;
import tn.esprit.stageete.repositories.AvisRepository;
import tn.esprit.stageete.service.AvisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AvisServiceImpl implements AvisService {

    @Autowired
    private AvisRepository avisRepository;

    @Override
    public List<Avis> findAll() {
        return avisRepository.findAll();
    }

    @Override
    public Avis findById(Long id) {
        return avisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avis non trouvé avec l'id : " + id));
    }

    @Override
    public Avis save(Avis avis) {
        if (avisRepository.existsByUserIdAndDestinationId(
                avis.getUser().getId(), avis.getDestination().getId())) {
            throw new RuntimeException("Vous avez déjà soumis un avis pour cette destination");
        }
        return avisRepository.save(avis);
    }

    @Override
    public Avis update(Long id, Avis avis) {
        Avis existing = findById(id);
        existing.setNote(avis.getNote());
        existing.setCommentaire(avis.getCommentaire());
        return avisRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        findById(id);
        avisRepository.deleteById(id);
    }

    @Override
    public List<Avis> findByDestinationId(Long destinationId) {
        return avisRepository.findByDestinationId(destinationId);
    }

    @Override
    public List<Avis> findByUserId(Long userId) {
        return avisRepository.findByUserId(userId);
    }
}