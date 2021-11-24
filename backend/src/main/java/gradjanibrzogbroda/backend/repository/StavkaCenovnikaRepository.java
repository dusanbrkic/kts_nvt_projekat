package gradjanibrzogbroda.backend.repository;

import gradjanibrzogbroda.backend.domain.StavkaCenovnika;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StavkaCenovnikaRepository extends JpaRepository<StavkaCenovnika, Integer> {
    StavkaCenovnika findOneById(Integer id);
}
