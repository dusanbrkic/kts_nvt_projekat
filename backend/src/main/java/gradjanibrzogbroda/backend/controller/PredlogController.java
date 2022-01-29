package gradjanibrzogbroda.backend.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gradjanibrzogbroda.backend.domain.Predlog;
import gradjanibrzogbroda.backend.domain.PredlogStatus;
import gradjanibrzogbroda.backend.domain.PredlogTip;
import gradjanibrzogbroda.backend.dto.PredlogDTO;
import gradjanibrzogbroda.backend.exceptions.JeloNotFoundException;
import gradjanibrzogbroda.backend.exceptions.PredlogWrongFormatException;
import gradjanibrzogbroda.backend.service.PredlogService;

@RestController
@RequestMapping("/predlog")
@CrossOrigin(origins = {"http://localhost:4200/" })
public class PredlogController {
	
	@Autowired
	private PredlogService predlogService;
	
	@PreAuthorize("hasRole('GLAVNI_KUVAR')")
	@PostMapping()
    public ResponseEntity<PredlogDTO> dodajPredlog(@RequestBody PredlogDTO dto) {
        Predlog predlog;
		try {
			predlog = predlogService.addPredlog(dto);
		} catch (PredlogWrongFormatException | JeloNotFoundException e) {
			predlog=null;
			e.printStackTrace();
			return new ResponseEntity<PredlogDTO>(new PredlogDTO(),HttpStatus.INTERNAL_SERVER_ERROR);
		}

        return new ResponseEntity<PredlogDTO>(new PredlogDTO(predlog), HttpStatus.OK);
    }
	
	@PreAuthorize("hasRole('MANAGER')")
	@GetMapping()
	public ResponseEntity<Map<String, Object>> getAllPredloziPage(
			@RequestParam("page") Integer page,
			@RequestParam("size") Integer size,
			@RequestParam(value="tip") Optional<PredlogTip> tip
			){
		
		Page<Predlog> pagePredlozi=predlogService.getAllPaged(page, size, tip);
		
		
		List<Predlog> predlozi = pagePredlozi.getContent();
        ArrayList<PredlogDTO> dtos=new ArrayList<PredlogDTO>();

        for(Predlog p: predlozi) {
            dtos.add(new PredlogDTO(p));
        }
		
        Map<String, Object> response = new HashMap<>();
        response.put("predlozi", dtos);
        response.put("currentPage", pagePredlozi.getNumber());
        response.put("totalItems", pagePredlozi.getTotalElements());
        response.put("totalPages", pagePredlozi.getTotalPages());
		
		
		return new ResponseEntity<>(response,HttpStatus.OK);
		
	}
	
	@PreAuthorize("hasRole('MANAGER')")
	@PutMapping()
    public ResponseEntity<PredlogDTO> izmeniPredlog(@RequestBody PredlogDTO dto) {
        Predlog predlog;
		try {
			predlog = predlogService.addPredlog(dto);
		} catch (PredlogWrongFormatException | JeloNotFoundException e) {
			predlog=null;
			e.printStackTrace();
			return new ResponseEntity<PredlogDTO>(new PredlogDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

        return new ResponseEntity<PredlogDTO>(new PredlogDTO(predlog), HttpStatus.OK);
    }

}
