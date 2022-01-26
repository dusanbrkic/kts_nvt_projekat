package gradjanibrzogbroda.backend.controller;

import gradjanibrzogbroda.backend.dto.ZoneDTO;
import gradjanibrzogbroda.backend.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/zone")
public class ZoneController {
	@Autowired
	private ZoneService zoneService;

	@GetMapping()
	public ResponseEntity<List<ZoneDTO>> getAllZones(){

		List<ZoneDTO> zoneDTOS = zoneService.getAll();

		return new ResponseEntity<List<ZoneDTO>>(zoneDTOS, HttpStatus.OK);
	}
}
