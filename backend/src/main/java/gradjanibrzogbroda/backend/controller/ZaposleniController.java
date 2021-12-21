package gradjanibrzogbroda.backend.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import gradjanibrzogbroda.backend.domain.*;
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
import gradjanibrzogbroda.backend.service.ZaposleniService;
import gradjanibrzogbroda.exception.UserAlreadyExistsException;
import gradjanibrzogbroda.exception.UserNotFoundException;

@RestController
@RequestMapping("/zaposleni")
public class ZaposleniController {

	@Autowired
	private ZaposleniService zaposleniService;

	@GetMapping(value = "/all")
	public ResponseEntity<List<ZaposleniDTO>> getAllZaposleni() {

		List<ZaposleniDTO> zaposleniDTOs = zaposleniService.getAllZaposleni();

		return new ResponseEntity<List<ZaposleniDTO>>(zaposleniDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/id/{id}")
	public ResponseEntity<ZaposleniDTO> getOneByIdentificationNumber(@PathVariable("id") String id) {
		try {
			Zaposleni z = zaposleniService.findOneByIdentificationNumber(id);
			return new ResponseEntity<ZaposleniDTO>(new ZaposleniDTO(z), HttpStatus.OK);

		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<ZaposleniDTO>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "/update")
	public ResponseEntity<ZaposleniDTO> updateZaposleni(
			@RequestParam String ime,
			@RequestParam String prezime,
			@RequestParam String pol,
			@RequestParam String datumRodjenja,
			@RequestParam Double trenutnaPlata,
			@RequestParam String tipZaposlenja,
			@RequestParam String slikaString,
			@RequestParam String identificationNumber) {

		try {
			ZaposleniDTO zaposleniDTO = new ZaposleniDTO(zaposleniService.updateZaposleni(new ZaposleniDTO(ime, prezime, Pol.valueOf(pol),
			LocalDate.parse(datumRodjenja, DateTimeFormatter.ofPattern("yyyy-MM-dd")), trenutnaPlata,
			TipZaposlenja.valueOf(tipZaposlenja), slikaString, identificationNumber, null)));

			return new ResponseEntity<ZaposleniDTO>(zaposleniDTO, HttpStatus.OK);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<ZaposleniDTO>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "/add")
	public ResponseEntity<ZaposleniDTO> addZaposleni(
			@RequestParam String ime,
			@RequestParam String prezime,
			@RequestParam String pol,
			@RequestParam String datumRodjenja,
			@RequestParam Double trenutnaPlata,
			@RequestParam String tipZaposlenja,
			@RequestParam String slikaString,
			@RequestParam String identificationNumber) {

		try {
			ZaposleniDTO zaposleniDTO = new ZaposleniDTO(zaposleniService.addZaposleni(new ZaposleniDTO(ime, prezime, Pol.valueOf(pol),
			LocalDate.parse(datumRodjenja, DateTimeFormatter.ofPattern("yyyy-MM-dd")), trenutnaPlata,
			TipZaposlenja.valueOf(tipZaposlenja), slikaString, identificationNumber, null)));

			return new ResponseEntity<ZaposleniDTO>(zaposleniDTO, HttpStatus.OK);

		} catch (UserAlreadyExistsException e) {
			return new ResponseEntity<ZaposleniDTO>(HttpStatus.BAD_REQUEST);
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

		return new ResponseEntity<ZaposleniDTO>(new ZaposleniDTO(z), HttpStatus.OK);
	}
}
