package gradjanibrzogbroda.backend.repository;

import gradjanibrzogbroda.backend.domain.Sto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoRepository extends JpaRepository<Sto, Integer> {
    Sto findOneById(Integer id);
}
