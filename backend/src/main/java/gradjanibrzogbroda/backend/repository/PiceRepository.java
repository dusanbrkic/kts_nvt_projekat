package gradjanibrzogbroda.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gradjanibrzogbroda.backend.domain.Pice;

@Repository
public interface PiceRepository extends JpaRepository<Pice, Integer>{
	
	List<Pice> findAll();
	
	@SuppressWarnings("unchecked")
	Pice save(Pice pice);
	
	Pice findOneById(Integer id);
	
	Pice findOneByNaziv(String naziv);
	
	

}
