package gradjanibrzogbroda.backend.repository;

import gradjanibrzogbroda.backend.domain.Sto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Repository
public interface StoRepository extends JpaRepository<Sto, Integer> {
    Sto findOneById(Integer id);

	Sto findOneByIdentificationNumber(String id);

	@Query(value = "select s.identificationNumber from Sto s where s.zone.identificationNumber = :zoneId and s.identificationNumber not in :stoloviId")
	List<String> selectDeletedTables(@Param("zoneId") String id, @Param("stoloviId") List<String> stoloviId);

	@Transactional
	void deleteAllByIdentificationNumberIn(Collection<String> identificationNumbers);
}
