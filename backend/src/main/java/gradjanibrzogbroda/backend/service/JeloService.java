package gradjanibrzogbroda.backend.service;

import java.util.List;

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
}
