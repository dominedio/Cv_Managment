package cvoperator.cvoperator.service;

import cvoperator.cvoperator.entity.Candidato;
import cvoperator.cvoperator.entity.Cv;
import cvoperator.cvoperator.repository.CvRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Service
public class CvService {

    private final CvRepository cvRepository;

    public CvService(CvRepository cvRepository) {
        this.cvRepository = cvRepository;
    }

    @Transactional
    public boolean addCv(MultipartFile file, Candidato candidato)throws IOException {
            Cv documento = new Cv();
            documento.setCandidato(candidato);
            documento.setCv(file.getBytes());
            documento.setDataCreazione(new Date());
            cvRepository.save(documento);
        if(cvRepository.findById(documento.getId()).isPresent()) {
            return true;
        }else return false;
    }

    public Cv getCv(Long id) {
        return cvRepository.findById(id).get();
    }

    @Transactional
    public boolean deleteCv(Long id) {
        cvRepository.deleteById(id);
        if(cvRepository.findById(id).isPresent()) {
            return false;
        }else return true;
    }
}
