package gradjanibrzogbroda.backend.controller;

import java.util.ArrayList;
import java.util.List;

import gradjanibrzogbroda.backend.domain.Jelo;
import gradjanibrzogbroda.backend.dto.JeloDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import gradjanibrzogbroda.backend.domain.Pice;
import gradjanibrzogbroda.backend.dto.PiceDTO;
import gradjanibrzogbroda.backend.service.PiceService;

@RestController
@RequestMapping("/pice")
public class PiceController {
	
	@Autowired
	private PiceService piceService;
	
	@GetMapping("/all")
	public ResponseEntity<List<PiceDTO>> getAllPica(){
		
		ArrayList<Pice> pica=(ArrayList<Pice>) piceService.findAll();
		
		
		ArrayList<PiceDTO> dtos=new ArrayList<PiceDTO>();
		
		for(Pice p: pica) {
			dtos.add(new PiceDTO(p));
		}
		
		return new ResponseEntity<List<PiceDTO>>(dtos,HttpStatus.OK);
		
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<PiceDTO> getOneById(@PathVariable("id") Integer id){
		Pice p=piceService.findOne(id);
		
		if(p!=null) {
			return new ResponseEntity<PiceDTO>(new PiceDTO(p),HttpStatus.OK);
		}else {
			return new ResponseEntity<PiceDTO>(HttpStatus.NOT_FOUND);
		}
		
		
	}
	
	@GetMapping("/naziv/{naziv}")
	public ResponseEntity<PiceDTO> getOneByNaziv(@PathVariable String naziv){
		
		Pice p=piceService.findOneByNaziv(naziv);
		
		if(p!=null) {
			return new ResponseEntity<PiceDTO>(new PiceDTO(p),HttpStatus.OK);
		}else {
			return new ResponseEntity<PiceDTO>(HttpStatus.NOT_FOUND);
		}
		
		
	}
	
	@PostMapping()
	public ResponseEntity<PiceDTO> addPice(@RequestBody Pice pice) { 
		Pice p = piceService.addPice(pice);

		return new ResponseEntity<PiceDTO>(new PiceDTO(p), HttpStatus.OK);
	}
	
	@PutMapping()
	public ResponseEntity<PiceDTO> updatePice(@RequestBody Pice pice){
		Pice p = piceService.updatePice(pice);
		
		return new ResponseEntity<PiceDTO>(new PiceDTO(p), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletePice(@PathVariable("id") int id) {
		try {
			piceService.deletePice(id);
			return new ResponseEntity<Object>(HttpStatus.OK);
		}catch(EmptyResultDataAccessException e) {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("izmeni-cenu/{id}")
	public ResponseEntity<PiceDTO> izmeniCenuPica(@PathVariable("id") Integer id, @RequestParam Double novaCena){
		Pice p = piceService.izmeniCenu(id, novaCena);

		return new ResponseEntity<PiceDTO>(new PiceDTO(p), HttpStatus.OK);
	}
	
	

}
