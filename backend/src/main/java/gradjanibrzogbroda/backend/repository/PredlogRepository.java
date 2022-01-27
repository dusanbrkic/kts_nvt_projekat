package gradjanibrzogbroda.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gradjanibrzogbroda.backend.domain.Predlog;
import gradjanibrzogbroda.backend.domain.PredlogTip;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@Repository
public interface PredlogRepository extends JpaRepository<Predlog, Integer> {
	
	@Query("SELECT p FROM Predlog p WHERE (p.tipIzmene = :tip OR null = :tip) AND p.status = 0")
	Page<Predlog> getAllPaged(Pageable page,@Param("tip") Optional<PredlogTip> tip);

}
