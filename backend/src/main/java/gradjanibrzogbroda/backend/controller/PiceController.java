package gradjanibrzogbroda.backend.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import gradjanibrzogbroda.backend.domain.Pice;
import gradjanibrzogbroda.backend.dto.PiceDTO;
import gradjanibrzogbroda.backend.service.PiceService;

@CrossOrigin(origins = {"http://localhost:4200/" })
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
	
	@GetMapping("/page")
	public ResponseEntity<Map<String, Object>> getPicaPage(
			@RequestParam("first") Integer first,
			@RequestParam("rows") Integer rows,
			@RequestParam(value="naziv", defaultValue="", required=false) String naziv,
			@RequestParam("sortField") Optional<String> sortField,
			@RequestParam("sortOrder") Integer sortOrder){
		
		Page<Pice> pagePica = piceService.findPage(first, rows, naziv, sortField, sortOrder);
		List<Pice> pica = pagePica.getContent();
        ArrayList<PiceDTO> dtos=new ArrayList<PiceDTO>();

        for(Pice p:pica) {
            dtos.add(new PiceDTO(p));
        }
		
        Map<String, Object> response = new HashMap<>();
        response.put("pica", dtos);
        response.put("currentPage", pagePica.getNumber());
        response.put("totalItems", pagePica.getTotalElements());
        response.put("totalPages", pagePica.getTotalPages());
		
		
		return new ResponseEntity<>(response,HttpStatus.OK);
		
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
		if(pice.getNaziv()==null || pice.getTrenutnaCena()==null) {
			return new ResponseEntity<PiceDTO>( HttpStatus.BAD_REQUEST);
		}
		Pice p = piceService.addPice(pice);

		return new ResponseEntity<PiceDTO>(new PiceDTO(p), HttpStatus.OK);
	}
	
	@PutMapping()
	public ResponseEntity<PiceDTO> updatePice(@RequestBody Pice pice){
		if(pice.getNaziv()==null || pice.getTrenutnaCena()==null) {
			return new ResponseEntity<PiceDTO>( HttpStatus.BAD_REQUEST);
		}
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
