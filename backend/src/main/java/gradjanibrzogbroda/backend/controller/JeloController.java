package gradjanibrzogbroda.backend.controller;

import java.util.ArrayList;
import java.util.List;
import lombok.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
		
		for(Jelo j:jela) {
			System.out.println(j.getNaziv());
		}
		
		return null;
		
	}
	
}
