package gradjanibrzogbroda.backend.service;

import java.util.List;

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
}