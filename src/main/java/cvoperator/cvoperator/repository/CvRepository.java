package cvoperator.cvoperator.repository;

import cvoperator.cvoperator.entity.Cv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CvRepository extends JpaRepository<Cv, Long>, JpaSpecificationExecutor<Cv> {
}
