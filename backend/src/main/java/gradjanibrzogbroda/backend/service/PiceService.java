package gradjanibrzogbroda.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gradjanibrzogbroda.backend.domain.Pice;
import gradjanibrzogbroda.backend.repository.PiceRepository;

@Service
public class PiceService {
	
	@Autowired
	private PiceRepository piceRep;
	
	public List<Pice> findAll(){
		return piceRep.findAll();
	}
	
	public Pice findOne(Integer id) {
		return piceRep.findOneById(id);
	}
	
	public Pice findOneByNaziv(String naziv) {
		return piceRep.findOneByNaziv(naziv);
	}
	
	public Pice addPice(Pice p) {
		return piceRep.save(p);
	}
	
	public void deletePice(Integer id) {
		piceRep.deleteById(id);
	}
	
	public Pice updatePice(Pice p) {
		return piceRep.save(p);
	}

}
