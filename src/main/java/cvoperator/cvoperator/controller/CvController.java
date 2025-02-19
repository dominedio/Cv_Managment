package cvoperator.cvoperator.controller;

import cvoperator.cvoperator.entity.Candidato;
import cvoperator.cvoperator.entity.Cv;
import cvoperator.cvoperator.service.CvService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("candidato/cv")
public class CvController {

    private final CvService cvService;

    public CvController(CvService cvService) {
        this.cvService = cvService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("candidatoId") Long candidatoId) throws IOException {
        try {
            Candidato candidato = new Candidato();
            candidato.setId(candidatoId);
            if (cvService.addCv(file, candidato)){
                return ResponseEntity.ok("File salvato con ID" + candidatoId);
            }else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Error al salvar el arquivo");
            }

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore nel salvataggio del file");
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFile(@PathVariable("id") Long id){
        boolean isDelete = cvService.deleteCv(id);
        if (isDelete){
            return ResponseEntity.ok("File salvato con ID" + id);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Errore nell'eliminare il file");
        }
    }

    @Transactional
    @PutMapping("/edit/{id}")
    public ResponseEntity<String> editFile(@PathVariable("id") Long id,@RequestParam("file") MultipartFile file, @RequestParam("candidatoId") Long candidatoId){
        Candidato candidato = new Candidato();
        candidato.setId(candidatoId);
        boolean isDelete = cvService.deleteCv(id);
        if (isDelete){
            try {
                if (cvService.addCv(file, candidato)){
                    return ResponseEntity.ok("File modificato con ID" + candidatoId);
                }else {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("Errore nel modificare il file");
                }

            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore nel salvataggio del file");
            }
        }else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Errore nel cancellare ed editare il file");
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable("id") Long id){
        Optional<Cv> documento = Optional.ofNullable(cvService.getCv(id));
        return documento.map(cv ->ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=cv_" + cv.getId() + ".pdf")
                .body(cv.getCv()))
                .orElse(ResponseEntity.notFound().build());
    }
}