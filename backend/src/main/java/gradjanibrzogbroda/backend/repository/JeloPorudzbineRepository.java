package gradjanibrzogbroda.backend.repository;

import gradjanibrzogbroda.backend.domain.JeloPorudzbine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JeloPorudzbineRepository extends JpaRepository<JeloPorudzbine, Integer> {
    JeloPorudzbine findOneById(Integer id);
}
