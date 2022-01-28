package gradjanibrzogbroda.backend.repository;

import gradjanibrzogbroda.backend.domain.StavkaCenovnika;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StavkaCenovnikaRepository extends JpaRepository<StavkaCenovnika, Integer> {
    StavkaCenovnika findOneById(Integer id);
    
    @Query("SELECT s FROM StavkaCenovnika s WHERE s.pocetakVazenja < ?1 AND (s.krajVazenja=NULL OR s.krajVazenja > ?1)")
    List<StavkaCenovnika> findCenovnik(LocalDateTime time);
}
