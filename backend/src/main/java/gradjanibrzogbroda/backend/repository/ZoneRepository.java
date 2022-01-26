package gradjanibrzogbroda.backend.repository;

import gradjanibrzogbroda.backend.domain.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Integer> {

	List<Zone> findAll();

	Zone findByIdentificationNumber(String identificationNumber);
}
