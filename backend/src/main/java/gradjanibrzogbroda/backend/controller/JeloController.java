package gradjanibrzogbroda.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import gradjanibrzogbroda.backend.domain.Jelo;
import gradjanibrzogbroda.backend.dto.JeloDTO;
import gradjanibrzogbroda.backend.service.JeloService;

@RestController
@RequestMapping("/jela")
public class JeloController {
	
	@Autowired
	private JeloService jeloService;
	
	@GetMapping("/all")
	public ResponseEntity<List<JeloDTO>> getAllJela(){
		
		ArrayList<Jelo> jela=(ArrayList<Jelo>) jeloService.findAll();
		
		System.out.println(jela.size());
		
		ArrayList<JeloDTO> dtos=new ArrayList<JeloDTO>();
		
		for(Jelo j:jela) {
			dtos.add(new JeloDTO(j));
		}
		
		return new ResponseEntity<List<JeloDTO>>(dtos,HttpStatus.OK);
		
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<JeloDTO> getOneById(@PathVariable("id") Integer id){
		
		Jelo j=jeloService.findOne(id);
		
		if(j!=null) {
			return new ResponseEntity<JeloDTO>(new JeloDTO(j),HttpStatus.OK);
		}else {
			return new ResponseEntity<JeloDTO>(HttpStatus.NOT_FOUND);
		}
		
		
	}
	
	@GetMapping("/naziv/{naziv}")
	public ResponseEntity<JeloDTO> getOneByNaziv(@PathVariable String naziv){
		
		Jelo j=jeloService.findOneByNaziv(naziv);
		
		if(j!=null) {
			return new ResponseEntity<JeloDTO>(new JeloDTO(j),HttpStatus.OK);
		}else {
			return new ResponseEntity<JeloDTO>(HttpStatus.NOT_FOUND);
		}
		
		
	}
	
	@PostMapping()
	public ResponseEntity<JeloDTO> addJelo(@RequestBody Jelo jelo) { 
		Jelo j = jeloService.addJelo(jelo);

		return new ResponseEntity<JeloDTO>(new JeloDTO(j), HttpStatus.OK);
	}
	
	@PutMapping()
	public ResponseEntity<JeloDTO> updateJelo(@RequestBody Jelo jelo){
		Jelo j = jeloService.updateJelo(jelo);
		
		return new ResponseEntity<JeloDTO>(new JeloDTO(j), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteJelo(@PathVariable("id") Integer id) {
		try {
			jeloService.deleteJelo(id);
			return new ResponseEntity<Object>(HttpStatus.OK);
		}catch(EmptyResultDataAccessException e) {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/izmeni-cenu/{id}")
	public ResponseEntity<JeloDTO> izmeniCenuJela(@PathVariable("id") Integer id, @RequestParam Double novaCena){
		Jelo j = jeloService.izmeniCenu(id, novaCena);

		return new ResponseEntity<JeloDTO>(new JeloDTO(j), HttpStatus.OK);
	}
	
}
