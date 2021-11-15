package gradjanibrzogbroda.backend.repository;

import gradjanibrzogbroda.backend.domain.Menadzer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenadzerRepository extends JpaRepository<Menadzer, Integer> {
}
