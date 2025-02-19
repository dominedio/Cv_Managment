package cvoperator.cvoperator.repository;

import cvoperator.cvoperator.entity.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CandidatoRepository extends JpaRepository<Candidato, Long>, JpaSpecificationExecutor<Candidato> {

    @Query("SELECT c FROM Candidato c WHERE LOWER(c.competenze) LIKE LOWER(CONCAT('%', :competenza, '%'))")
    List<Candidato> findByCompetenzeContaining(@Param("competenza") String competenza);

}
