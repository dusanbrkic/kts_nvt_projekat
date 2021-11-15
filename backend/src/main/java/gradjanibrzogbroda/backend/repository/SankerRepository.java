package gradjanibrzogbroda.backend.repository;

import gradjanibrzogbroda.backend.domain.Sanker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SankerRepository extends JpaRepository<Sanker, Integer> {
}
