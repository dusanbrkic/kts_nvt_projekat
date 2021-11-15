package gradjanibrzogbroda.backend.repository;

import gradjanibrzogbroda.backend.domain.Konobar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KonobarRepository extends JpaRepository<Konobar, Integer> {
}
