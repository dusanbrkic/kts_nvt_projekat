package gradjanibrzogbroda.backend.service;

import java.time.LocalDateTime;
import java.util.List;

import gradjanibrzogbroda.backend.domain.StavkaCenovnika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gradjanibrzogbroda.backend.domain.Jelo;
import gradjanibrzogbroda.backend.domain.KategorijaJela;
import gradjanibrzogbroda.backend.domain.TipJela;
import gradjanibrzogbroda.backend.repository.JeloRepository;

@Service
public class JeloService {
	
	@Autowired
	private JeloRepository jeloRep;
	
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
		return jeloRep.save(j);
	}
	
	public void deleteJelo(Integer id) {
		jeloRep.deleteById(id);
	}
	
	public Jelo updateJelo(Jelo j) {
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
