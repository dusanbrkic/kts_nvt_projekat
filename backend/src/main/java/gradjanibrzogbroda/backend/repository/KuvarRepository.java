package gradjanibrzogbroda.backend.repository;

import gradjanibrzogbroda.backend.domain.Kuvar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KuvarRepository extends JpaRepository<Kuvar, Integer> {
}
