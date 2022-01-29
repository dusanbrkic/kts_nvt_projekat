package gradjanibrzogbroda.backend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import gradjanibrzogbroda.backend.domain.Jelo;
import gradjanibrzogbroda.backend.domain.StavkaCenovnika;
import gradjanibrzogbroda.backend.exceptions.PiceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import gradjanibrzogbroda.backend.domain.Pice;
import gradjanibrzogbroda.backend.repository.PiceRepository;
import gradjanibrzogbroda.backend.repository.StavkaCenovnikaRepository;

@Service
public class PiceService {
	
	@Autowired
	private PiceRepository piceRep;
	
	@Autowired
	private StavkaCenovnikaRepository stCenRepo;
	
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
		p.setObrisan(false);
		Pice pi = piceRep.save(p);
		StavkaCenovnika st= new StavkaCenovnika(999, LocalDateTime.now(), null, pi.getTrenutnaCena(), pi);
		stCenRepo.save(st);
		return pi;
	}
	
	public void deletePice(Integer id) {
		Pice p = piceRep.findOneById(id);
		p.setObrisan(true);
		piceRep.save(p);
	}
	
	public Pice updatePice(Pice p) throws PiceNotFoundException {
		Pice st = piceRep.findOneById(p.getId());
		if(st==null) {
			throw new PiceNotFoundException("Nema pica");
		}
		if(p.getTrenutnaCena()!=st.getTrenutnaCena()) {
			izmeniCenu(p.getId(), p.getTrenutnaCena());
		}
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
	}

	public Page<Pice> findPage(Integer first, Integer rows, String naziv, Optional<String> sortField, Integer sortOrder) {
		Sort.Direction sd = Sort.Direction.ASC;
		if(sortOrder < 0) {
			sd = Sort.Direction.DESC;
			}
		if(sortField.isPresent()) {
			if(sortField.get().equals("cena")) {
				sortField = Optional.of("trenutnaCena");
			}
			if(sortField.get().equals("undefined")) {
				sortField = Optional.of("trenutnaCena");
			}
		}
		int pageIndex = first/rows;
		return piceRep.findAllFilter(naziv, (PageRequest.of(pageIndex, rows).withSort(sd, sortField.orElse("id"))));
	};

}
