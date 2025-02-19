package cvoperator.cvoperator.service;

import cvoperator.cvoperator.entity.Candidato;
import cvoperator.cvoperator.repository.CandidatoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidatoService {

    private CandidatoRepository candidatoRepository;

    public CandidatoService(CandidatoRepository candidatoRepository) {
        this.candidatoRepository = candidatoRepository;
    }

    @Transactional
    public boolean addCandidato(Candidato candidato) {          //da migliorare
        candidatoRepository.save(candidato);
        if(candidatoRepository.findById(candidato.getId()).isPresent()) {
            return true;
        }else return false;
    }

    @Transactional
    public boolean updateCandidato(Candidato candidato) {       //da migliorare
        candidatoRepository.save(candidato);
        if(candidatoRepository.findById(candidato.getId()).isPresent()) {
            return true;
        }else return false;
    }

    public Candidato getCandidato(Long id) {
        return candidatoRepository.findById(id).get();
    }

    public List<Candidato> getAllCandidatos() {
        return candidatoRepository.findAll();
    }

    public List<Candidato> getCandidatoByCompetenze(String competenza) {
        return candidatoRepository.findByCompetenzeContaining(competenza);
    }

    public boolean deleteCandidato(Long id) {
        candidatoRepository.deleteById(id);
        if(candidatoRepository.findById(id).isPresent()) {
            return false;
        }else return true;
    }

}
