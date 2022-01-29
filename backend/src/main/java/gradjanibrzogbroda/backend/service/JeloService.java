package gradjanibrzogbroda.backend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import gradjanibrzogbroda.backend.domain.StavkaCenovnika;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import gradjanibrzogbroda.backend.domain.Jelo;
import gradjanibrzogbroda.backend.domain.KategorijaJela;
import gradjanibrzogbroda.backend.domain.Pice;
import gradjanibrzogbroda.backend.domain.TipJela;
import gradjanibrzogbroda.backend.repository.JeloRepository;
import gradjanibrzogbroda.backend.repository.StavkaCenovnikaRepository;

@Service
public class JeloService {
	
	@Autowired
	private JeloRepository jeloRep;
	
	@Autowired
	private StavkaCenovnikaRepository stCenRepo;
	
	public Page<Jelo> findPage(Integer first, Integer rows, String naziv, Optional<String> sortField, int sortOrder, Optional<KategorijaJela> kategorijaJela, Optional<TipJela> tipJela){
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
		
		
		return jeloRep.findAllByKategorijaJelaAndTipJela(kategorijaJela, tipJela, naziv.toLowerCase(), (PageRequest.of(pageIndex, rows).withSort(sd, sortField.orElse("id"))));
	}
	
	public List<Jelo> findAll(){
		return jeloRep.findAll();
	}
	
	public Jelo findOne(Integer id) {
		return jeloRep.findOneById(id);
	}
	
	public Jelo findOneByNaziv(String naziv) {
		return jeloRep.findOneByNaziv(naziv);
	}
	
	public Jelo addJelo(Jelo j) {
		j.setObrisan(false);
		Jelo je = jeloRep.save(j);
		StavkaCenovnika st = new StavkaCenovnika(999, LocalDateTime.now(), null, je.getTrenutnaCena(), je);
		stCenRepo.save(st);
		return je;
	}
	
	public void deleteJelo(Integer id) {
		Jelo j = jeloRep.findOneById(id);
		j.setObrisan(true);
		jeloRep.save(j);
	}
	
	public Jelo updateJelo(Jelo j) {
		Jelo st = jeloRep.findOneById(j.getId());
		//System.out.println(j.getTrenutnaCena()+" poredi sa "+st.getTrenutnaCena());
		if(j.getTrenutnaCena()!=st.getTrenutnaCena()) {
			izmeniCenu(j.getId(), j.getTrenutnaCena());
		}
		return jeloRep.save(j);
	}
	
	public List<Jelo> findAllByKategorija(KategorijaJela kat){
		return jeloRep.findAllByKategorijaJela(kat);
	}
	
	public List<Jelo> findAllByTip(TipJela tip){
		return jeloRep.findAllByTipJela(tip);
	}

	public Jelo izmeniCenu(Integer jeloId, Double novaCena){
		Jelo jelo = jeloRep.findOneById(jeloId);
		jelo.setTrenutnaCena(novaCena);
		for (StavkaCenovnika s: jelo.getCeneArtikla()) {
			if (s.getKrajVazenja() == null){
				s.setKrajVazenja(LocalDateTime.now());
			}
		}

		StavkaCenovnika stavkaCenovnika = StavkaCenovnika.builder()
				.artikal(jelo)
				.cena(novaCena)
				.pocetakVazenja(LocalDateTime.now())
				.build();
		jelo.getCeneArtikla().add(stavkaCenovnika);
		return jeloRep.save(jelo);
	};
}
