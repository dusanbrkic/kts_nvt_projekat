package gradjanibrzogbroda.backend.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import gradjanibrzogbroda.backend.domain.Pice;
import gradjanibrzogbroda.backend.domain.Plata;
import gradjanibrzogbroda.backend.domain.StavkaCenovnika;
import gradjanibrzogbroda.backend.dto.PlataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gradjanibrzogbroda.backend.domain.Zaposleni;
import gradjanibrzogbroda.backend.repository.ZaposleniRepository;

@Service
public class ZaposleniService {
	
	@Autowired
	private ZaposleniRepository zaposleniRepository;
	
	public List<Zaposleni> findAll(){
		return zaposleniRepository.findAll();
	}
	
	public Zaposleni findOneById(Integer id) {
		return zaposleniRepository.findOneById(id);
	}
	
	public Zaposleni updateAddZaposleni(Zaposleni z) {
		return zaposleniRepository.save(z);
	}
	
	public void deleteZaposleni(Integer id) {
		zaposleniRepository.deleteById(id);
	}

	public Zaposleni izmeniPlatu(PlataDTO dto){
		Zaposleni zaposleni = zaposleniRepository.findOneById(dto.getZaposleniId());
		zaposleni.setTrenutnaPlata(dto.getVisinaPlate());
		for (Plata p: zaposleni.getPlate()) {
			if (p.getKrajVazenja() == null){
				//p.setKrajVazenja(dto.getPocetakVazenja().minusDays(1));
				p.setKrajVazenja(LocalDate.now());
			}
		}

		Plata plata = Plata.builder()
				.zaposleni(zaposleni)
				.visinaPlate(dto.getVisinaPlate())
				.pocetakVazenja(LocalDate.now().plusDays(1))
				.build();
		zaposleni.getPlate().add(plata);
		return zaposleniRepository.save(zaposleni);
	};
}