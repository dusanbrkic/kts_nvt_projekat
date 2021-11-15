package gradjanibrzogbroda.backend.repository;

import gradjanibrzogbroda.backend.domain.GlavniKuvar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GlavniKuvarRepository extends JpaRepository<GlavniKuvar, Integer> {
}
