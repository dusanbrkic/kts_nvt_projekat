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

import gradjanibrzogbroda.backend.domain.Jelo;
import gradjanibrzogbroda.backend.domain.KategorijaJela;
import gradjanibrzogbroda.backend.domain.TipJela;
import gradjanibrzogbroda.backend.dto.JeloDTO;
import gradjanibrzogbroda.backend.service.JeloService;

@CrossOrigin(origins = {"http://localhost:4200/" })
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
	
	@GetMapping("/page/{sankerIdNum}/")
	public ResponseEntity<Map<String, Object>> getAllJelaPage(
			@PathVariable("sankerIdNum") String sId,
			@RequestParam("first") Integer first,
			@RequestParam("rows") Integer rows,
			@RequestParam(value="naziv", defaultValue="", required=false) String naziv,
			@RequestParam("sortField") Optional<String> sortField,
			@RequestParam("sortOrder") Integer sortOrder,
			@RequestParam(value="kategorijaJela") Optional<KategorijaJela> kategorijaJela,
			@RequestParam(value="tipJela" ) Optional<TipJela> tipJela
			){
		
		Page<Jelo> pageJela=jeloService.findPage(first, rows, naziv, sortField, sortOrder, kategorijaJela, tipJela);
		
		List<Jelo> jela = pageJela.getContent();
        ArrayList<JeloDTO> dtos=new ArrayList<JeloDTO>();

        for(Jelo j:jela) {
            dtos.add(new JeloDTO(j));
        }
		
        Map<String, Object> response = new HashMap<>();
        response.put("jela", dtos);
        response.put("currentPage", pageJela.getNumber());
        response.put("totalItems", pageJela.getTotalElements());
        response.put("totalPages", pageJela.getTotalPages());
		
		
		return new ResponseEntity<>(response,HttpStatus.OK);
		
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
	
	@PutMapping("/update")
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
