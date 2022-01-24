package gradjanibrzogbroda.backend.controller;

import java.io.IOException;
import java.util.*;

import gradjanibrzogbroda.backend.domain.*;
import gradjanibrzogbroda.backend.util.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import gradjanibrzogbroda.backend.dto.PlataDTO;
import gradjanibrzogbroda.backend.dto.ZaposleniDTO;
import gradjanibrzogbroda.backend.exceptions.UserAlreadyExistsException;
import gradjanibrzogbroda.backend.exceptions.UserNotFoundException;
import gradjanibrzogbroda.backend.service.ZaposleniService;

@RestController
@RequestMapping("/zaposleni")
public class ZaposleniController {

	@Autowired
	private ZaposleniService zaposleniService;

	@Autowired
	private StorageService storageService;

	@GetMapping(value = "/all")
	public ResponseEntity<List<ZaposleniDTO>> getAllZaposleni() {

		List<ZaposleniDTO> zaposleniDTOs = zaposleniService.getAllZaposleni();

		return new ResponseEntity<List<ZaposleniDTO>>(zaposleniDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/allPaged")
	public ResponseEntity<List<ZaposleniDTO>> getAllZaposleniPaged(
			@RequestParam("page") Integer page,
			@RequestParam("size") Integer size,
			@RequestParam("sortBy") String sortByString,
			@RequestParam("sortDesc") Boolean sortDesc,
			@RequestParam("pretragaIme") String pretragaIme,
			@RequestParam("pretragaPrezime") String pretragaPrezime,
			@RequestParam("filterTipZaposlenja") String filterTipZaposlenjaString

	) {

		List<ZaposleniDTO> zaposleniDTOs = zaposleniService.getAllPaged(page, size, sortByString, sortDesc, pretragaIme, pretragaPrezime, filterTipZaposlenjaString);

		return new ResponseEntity<List<ZaposleniDTO>>(zaposleniDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/id/{id}")
	public ResponseEntity<ZaposleniDTO> getOneByIdentificationNumber(@PathVariable("id") String id) {
		try {
			Zaposleni z = zaposleniService.findOneByIdentificationNumber(id);

			return new ResponseEntity<ZaposleniDTO>(new ZaposleniDTO(z, storageService.loadAsString(z.getNazivSlike())), HttpStatus.OK);

		} catch (UserNotFoundException | IOException e) {
			return new ResponseEntity<ZaposleniDTO>(HttpStatus.NOT_FOUND);
		}
	}



	@PostMapping(value = "/update")
	public ResponseEntity<ZaposleniDTO> updateZaposleni(
			@RequestBody ZaposleniDTO zaposleniDTO) {

		try {
			Zaposleni z = zaposleniService.updateZaposleni(zaposleniDTO);

			storageService.store(zaposleniDTO.getSlikaString(), z.getNazivSlike());

			return new ResponseEntity<ZaposleniDTO>(HttpStatus.OK);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<ZaposleniDTO>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "/add")
	public ResponseEntity<String> addZaposleni(
			@RequestBody ZaposleniDTO zaposleniDTO) {

		try {
			Zaposleni z = zaposleniService.addZaposleni(zaposleniDTO);

			storageService.store(zaposleniDTO.getSlikaString(), z.getNazivSlike());

			return new ResponseEntity<String>("Success!", HttpStatus.OK);

		} catch (UserAlreadyExistsException e) {
			return new ResponseEntity<String>("Failure!", HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Object> deleteZaposleni(@PathVariable("id") String id) {
		try {
			zaposleniService.deleteZaposleni(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} 
	}

	@PutMapping("izmeni-platu")
	public ResponseEntity<ZaposleniDTO> izmeniPlatu(@RequestBody PlataDTO dto) {
		Zaposleni z = zaposleniService.izmeniPlatu(dto);

		return new ResponseEntity<ZaposleniDTO>(HttpStatus.OK);
	}
}
