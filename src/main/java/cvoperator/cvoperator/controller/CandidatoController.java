package cvoperator.cvoperator.controller;

import cvoperator.cvoperator.entity.Candidato;
import cvoperator.cvoperator.service.CandidatoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("candidato")
public class CandidatoController {

    private final CandidatoService candidatoService;

    public CandidatoController(CandidatoService candidatoService) {
        this.candidatoService = candidatoService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody Candidato candidato) {
        if (candidatoService.addCandidato(candidato)) {
            return ResponseEntity.ok("Candidato salvato con ID" + candidato.getId());
        }else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Errore nell'aggiunta del candidato");
        }

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Candidato> get(@PathVariable("id") Long id) {
        Candidato candidato = candidatoService.getCandidato(id);
        return ResponseEntity.ok(candidato);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        if (candidatoService.deleteCandidato(id)) {
            return ResponseEntity.ok("Candidato salvato con ID" + id);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Candidato non trovato");
        }
    }
}
