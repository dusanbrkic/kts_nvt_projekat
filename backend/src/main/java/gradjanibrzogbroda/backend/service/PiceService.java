package gradjanibrzogbroda.backend.service;

import java.time.LocalDateTime;
import java.util.List;

import gradjanibrzogbroda.backend.domain.Jelo;
import gradjanibrzogbroda.backend.domain.StavkaCenovnika;
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

	public Pice izmeniCenu(Integer piceId, Double novaCena){
		Pice pice = piceRep.findOneById(piceId);
		pice.setTrenutnaCena(novaCena);
		for (StavkaCenovnika s: pice.getCeneArtikla()) {
			if (s.getKrajVazenja() == null){
				s.setKrajVazenja(LocalDateTime.now());
			}
		}

		StavkaCenovnika stavkaCenovnika = StavkaCenovnika.builder()
				.artikal(pice)
				.cena(novaCena)
				.pocetakVazenja(LocalDateTime.now())
				.build();
		pice.getCeneArtikla().add(stavkaCenovnika);
		return piceRep.save(pice);
	};

}
