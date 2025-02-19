package cvoperator.cvoperator;

import cvoperator.cvoperator.entity.Candidato;
import cvoperator.cvoperator.entity.User;
import cvoperator.cvoperator.repository.CandidatoRepository;
import cvoperator.cvoperator.repository.CvRepository;
import cvoperator.cvoperator.repository.UserRepository;
import cvoperator.cvoperator.service.CandidatoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CvOperatorApplicationTests {

    @Mock
    private CandidatoRepository candidatoRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CvRepository cvRepository;

    @InjectMocks
    private CandidatoService candidatoService;

    private static User user;
    private static Candidato candidato;

    @BeforeEach
    void setUpEach() {
        candidato = Candidato.builder()
                .id(1L)
                .nome("Paolo")
                .telefono("3898986711")
                .esperienza("poca")
                .istruzione("laurea")
                .build();
    }

    @Test
    void CreateCandidato() {
        when(candidatoRepository.findById(any(long.class))).thenReturn(Optional.of(candidato));

        boolean result = candidatoService.addCandidato(candidato);

        assertTrue(result);
        verify(candidatoRepository).findById(candidato.getId());
    }

    @Test
    void UpdateCandidato() {
        when(candidatoRepository.findById(anyLong())).thenReturn(Optional.of(candidato));

        boolean result = candidatoService.updateCandidato(candidato);

        assertTrue(result);
        verify(candidatoRepository).findById(candidato.getId());
    }

    @Test
    void getCandidato() {
        when(candidatoRepository.findById(anyLong())).thenReturn(Optional.of(candidato));

        Candidato candidatoTrovato = candidatoService.getCandidato("1L");

        assertEquals(candidatoTrovato.getNome(), candidato.getNome());
        verify(candidatoRepository).findById(anyLong());
    }

    @Test
    void deleteCandidato() {
        when(candidatoRepository.findById(anyLong())).thenReturn(Optional.of(candidato));

        boolean result = candidatoService.deleteCandidato("1L");

        assertTrue(result);
        verify(candidatoRepository).findById(anyLong());
    }



}
