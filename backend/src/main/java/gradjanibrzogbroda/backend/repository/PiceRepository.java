package gradjanibrzogbroda.backend.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gradjanibrzogbroda.backend.domain.Pice;

@Repository
public interface PiceRepository extends JpaRepository<Pice, Integer>{
	
	List<Pice> findAll();
	
	@Query("SELECT p FROM Pice p WHERE lower(p.naziv) LIKE %?1%")
	Page<Pice> findAllFilter(String naziv, Pageable pageRequest);
	
	@SuppressWarnings("unchecked")
	Pice save(Pice pice);
	
	Pice findOneById(Integer id);
	
	Pice findOneByNaziv(String naziv);
	
	

}
