package gradjanibrzogbroda.backend.controller;

import gradjanibrzogbroda.backend.dto.ZoneDTO;
import gradjanibrzogbroda.backend.exceptions.StoImaPorudzbinuException;
import gradjanibrzogbroda.backend.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/zone")
@CrossOrigin(origins = {"http://localhost:4200/" })
public class ZoneController {
	@Autowired
	private ZoneService zoneService;

	@GetMapping()
	public ResponseEntity<Object> getAllZones(){

		List<ZoneDTO> zoneDTOS = zoneService.getAll();

		return new ResponseEntity<Object>(zoneDTOS, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping()
	public ResponseEntity<String> updateZone(@RequestBody ZoneDTO zoneDTO){

		try {
			zoneService.update(zoneDTO);
		} catch (StoImaPorudzbinuException e){
			return new ResponseEntity<String>("Sto ima aktivnih porudzbina.", HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<String>("Uspesno.", HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping()
	public ResponseEntity<String> addZone(@RequestBody ZoneDTO zoneDTO){

		zoneService.add(zoneDTO);

		return new ResponseEntity<String>("Uspesno.", HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("{zoneId}")
	public ResponseEntity<String> deleteZone(@PathVariable String zoneId){

		try {
			zoneService.delete(zoneId);
		} catch (StoImaPorudzbinuException e){
			return new ResponseEntity<String>("Sto ima aktivnih porudzbina.", HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<String>("Uspesno.", HttpStatus.OK);
	}
}
