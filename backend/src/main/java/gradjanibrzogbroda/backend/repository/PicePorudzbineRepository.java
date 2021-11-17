package gradjanibrzogbroda.backend.repository;

import gradjanibrzogbroda.backend.domain.PicePorudzbine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PicePorudzbineRepository extends JpaRepository<PicePorudzbine, Integer> {
    PicePorudzbine findOneById(Integer id);
}
