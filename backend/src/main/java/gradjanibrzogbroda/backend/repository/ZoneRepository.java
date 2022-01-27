package gradjanibrzogbroda.backend.repository;

import gradjanibrzogbroda.backend.domain.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Integer> {

	List<Zone> findAll();

	Zone findOneByIdentificationNumber(String identificationNumber);

	@Transactional
	void deleteByIdentificationNumber(String id);
}
