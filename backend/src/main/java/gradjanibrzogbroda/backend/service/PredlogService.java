package gradjanibrzogbroda.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import gradjanibrzogbroda.backend.domain.Jelo;
import gradjanibrzogbroda.backend.domain.Predlog;
import gradjanibrzogbroda.backend.domain.PredlogTip;
import gradjanibrzogbroda.backend.dto.PredlogDTO;
import gradjanibrzogbroda.backend.repository.JeloRepository;
import gradjanibrzogbroda.backend.repository.PredlogRepository;

@Service
public class PredlogService {
	
	@Autowired
	private PredlogRepository predlogRepository;
	
	@Autowired
	private JeloRepository jeloRepository;
	
	public Predlog addPredlog(PredlogDTO dto) {
		Jelo novoJelo;
		if(dto.getNovoJelo()!=null) {
			novoJelo=new Jelo(dto.getNovoJelo());
		}else {
			novoJelo=null;
		}
		
		Jelo staroJelo;
		if(dto.getStaroJeloId()!=null) {
			staroJelo=jeloRepository.findOneById(dto.getStaroJeloId());
		}else {
			staroJelo=null;
		}
		
		return predlogRepository.save(new Predlog(dto,novoJelo,staroJelo));
	}
	
	public Page<Predlog> getAllPaged(int page, int size,Optional<PredlogTip> tip){
		Pageable pageable = PageRequest.of(page, size);
		return predlogRepository.getAllPaged(pageable, tip);
	}

}
