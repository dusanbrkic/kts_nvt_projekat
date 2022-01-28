package gradjanibrzogbroda.backend.repository;

import gradjanibrzogbroda.backend.domain.Porudzbina;
import gradjanibrzogbroda.backend.domain.StatusPorudzbine;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PorudzbinaRepository extends JpaRepository<Porudzbina, Integer> {
    List<Porudzbina> findAll();

    Porudzbina findOneById(Integer id);
    
    @Query("SELECT p FROM Porudzbina p WHERE p.statusPorudzbine = 4 AND p.datumVreme >= ?1 AND p.datumVreme < ?2")
    List<Porudzbina> findAllNaplaceneInPeriod(LocalDateTime start, LocalDateTime end);
    
    @Query("SELECT p FROM Porudzbina p WHERE p.statusPorudzbine = 4 AND p.datumVreme = ?1")
    List<Porudzbina> findAllNaplaceneByDay(LocalDate day);

    @Query("SELECT p FROM Porudzbina p WHERE p.statusPorudzbine = 0 OR p.statusPorudzbine = 1")
    List<Porudzbina> findAllZaSankera();
    
    List<Porudzbina> findAllByStatusPorudzbine(StatusPorudzbine statusPorudzbine);

}
