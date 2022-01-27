package gradjanibrzogbroda.backend.controller;

import gradjanibrzogbroda.backend.dto.ZoneDTO;
import gradjanibrzogbroda.backend.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<List<ZoneDTO>> getAllZones(){

		List<ZoneDTO> zoneDTOS = zoneService.getAll();

		return new ResponseEntity<List<ZoneDTO>>(zoneDTOS, HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<String> updateZone(@RequestBody ZoneDTO zoneDTO){

		zoneService.update(zoneDTO);

		return new ResponseEntity<String>("Uspesno.", HttpStatus.OK);
	}

	@PutMapping()
	public ResponseEntity<String> addZone(@RequestBody ZoneDTO zoneDTO){

		zoneService.add(zoneDTO);

		return new ResponseEntity<String>("Uspesno.", HttpStatus.OK);
	}

	@DeleteMapping("{zoneId}")
	public ResponseEntity<String> deleteZone(@PathVariable String zoneId){

		zoneService.delete(zoneId);

		return new ResponseEntity<String>("Uspesno.", HttpStatus.OK);
	}
}
