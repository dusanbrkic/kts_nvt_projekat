package gradjanibrzogbroda.backend.repository;

import gradjanibrzogbroda.backend.domain.JeloPorudzbine;
import gradjanibrzogbroda.backend.domain.Porudzbina;
import gradjanibrzogbroda.backend.domain.StatusJela;
import gradjanibrzogbroda.backend.domain.StatusPorudzbine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JeloPorudzbineRepository extends JpaRepository<JeloPorudzbine, Integer> {
    JeloPorudzbine findOneById(Integer id);

    List<JeloPorudzbine> findAllByStatusJela(StatusJela statusJela);
}
