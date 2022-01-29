package gradjanibrzogbroda.backend.service;

import java.util.List;
import java.util.Optional;

import gradjanibrzogbroda.backend.config.StorageProperties;
import gradjanibrzogbroda.backend.util.StorageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import gradjanibrzogbroda.backend.domain.Jelo;
import gradjanibrzogbroda.backend.domain.Predlog;
import gradjanibrzogbroda.backend.domain.PredlogTip;
import gradjanibrzogbroda.backend.dto.PredlogDTO;
import gradjanibrzogbroda.backend.exceptions.JeloNotFoundException;
import gradjanibrzogbroda.backend.exceptions.PredlogWrongFormatException;
import gradjanibrzogbroda.backend.repository.JeloRepository;
import gradjanibrzogbroda.backend.repository.PredlogRepository;

@Service
public class PredlogService {
	
	@Autowired
	private PredlogRepository predlogRepository;
	
	@Autowired
	private JeloRepository jeloRepository;
	
	public Predlog addPredlog(PredlogDTO dto) throws PredlogWrongFormatException, JeloNotFoundException {
		if(dto.getNovoJelo()==null && dto.getStaroJeloId()==null) {
			throw new PredlogWrongFormatException("Loš format predloga!");
		}
		if(dto.getTipIzmene()==PredlogTip.BRISANJE && dto.getStaroJeloId()==null) {
			throw new PredlogWrongFormatException("Loš format predloga!");
		}
		if(dto.getTipIzmene()==PredlogTip.DODAVANJE && dto.getNovoJelo()==null) {
			throw new PredlogWrongFormatException("Loš format predloga!");
		}
		Jelo novoJelo;
		if(dto.getNovoJelo()!=null) {
			novoJelo=new Jelo(dto.getNovoJelo());
			StorageUtil.store(dto.getNovoJelo().getPicBase64(), StorageProperties.JELA_LOCATION, novoJelo.getPicName());
		}else {
			novoJelo=null;
		}
		
		Jelo staroJelo;
		if(dto.getStaroJeloId()!=null) {
			staroJelo=jeloRepository.findOneById(dto.getStaroJeloId());
			if(staroJelo==null) {
				throw new JeloNotFoundException("Jelo sa id: "+dto.getStaroJeloId()+" ne postoji!");
			}
		}else {
			staroJelo=null;
		}


		Predlog p=new Predlog(dto,novoJelo,staroJelo);
		return predlogRepository.save(p);
	}
	
	public Page<Predlog> getAllPaged(int page, int size,Optional<PredlogTip> tip){
		Pageable pageable = PageRequest.of(page, size);
		return predlogRepository.getAllPaged(pageable, tip);
	}
	
	public List<Predlog> getAll(){
		return predlogRepository.findAll();
	}

}
