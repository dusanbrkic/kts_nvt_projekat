package gradjanibrzogbroda.backend.repository;

import gradjanibrzogbroda.backend.domain.Zaposleni;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZaposleniRepository extends JpaRepository<Zaposleni, Integer> {
}
