package gradjanibrzogbroda.backend.repository;

import gradjanibrzogbroda.backend.domain.Porudzbina;
import gradjanibrzogbroda.backend.domain.StatusPorudzbine;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PorudzbinaRepository extends JpaRepository<Porudzbina, Integer> {
    List<Porudzbina> findAll();

    Porudzbina findOneById(Integer id);

    List<Porudzbina> findAllByStatusPorudzbine(StatusPorudzbine statusPorudzbine);

    List<Porudzbina> findAllByKonobarId(Integer konobarId);
}
