package cvoperator.cvoperator.controller;

import cvoperator.cvoperator.entity.Candidato;
import cvoperator.cvoperator.service.CandidatoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {

    private final CandidatoService candidatoService;

    public AdminController(CandidatoService candidatoService) {
        this.candidatoService = candidatoService;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Candidato>> detall() {
        List<Candidato> candidati = candidatoService.getAllCandidatos();
        return ResponseEntity.ok(candidati);
    }

    @GetMapping("/getby")
    public ResponseEntity<List<Candidato>> cercaPerCompetenze(String competenze) {
        List<Candidato> candidati = candidatoService.getCandidatoByCompetenze(competenze);
        return ResponseEntity.ok(candidati);
    }

}
