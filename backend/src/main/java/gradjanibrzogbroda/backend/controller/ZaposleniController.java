package gradjanibrzogbroda.backend.controller;

import java.io.IOException;
import java.util.*;

import gradjanibrzogbroda.backend.domain.*;
import gradjanibrzogbroda.backend.util.StorageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import gradjanibrzogbroda.backend.dto.PlataDTO;
import gradjanibrzogbroda.backend.dto.ZaposleniDTO;
import gradjanibrzogbroda.backend.exceptions.UserAlreadyExistsException;
import gradjanibrzogbroda.backend.exceptions.UserNotFoundException;
import gradjanibrzogbroda.backend.service.ZaposleniService;

@RestController
@RequestMapping("/zaposleni")
@CrossOrigin(origins = {"http://localhost:4200/" })
public class ZaposleniController {

	@Autowired
	private ZaposleniService zaposleniService;

	@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
	@GetMapping(value = "/all")
	public ResponseEntity<List<ZaposleniDTO>> getAllZaposleni() {

		List<ZaposleniDTO> zaposleniDTOs = zaposleniService.getAllZaposleni();

		return new ResponseEntity<List<ZaposleniDTO>>(zaposleniDTOs, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
	@GetMapping(value = "/allPaged")
	public ResponseEntity<Map<String, Object>> getAllZaposleniPaged(
			@RequestParam("page") Integer page,
			@RequestParam("size") Integer size,
			@RequestParam("sortBy") String sortByString,
			@RequestParam("sortDesc") Boolean sortDesc,
			@RequestParam("pretragaIme") String pretragaIme,
			@RequestParam("pretragaPrezime") String pretragaPrezime,
			@RequestParam("filterTipZaposlenja") String filterTipZaposlenjaString

	) {

		Map<String, Object> response = zaposleniService.getAllPaged(page, size, sortByString, sortDesc, pretragaIme, pretragaPrezime, filterTipZaposlenjaString);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
	@GetMapping(value = "/username/{username}")
	public ResponseEntity<ZaposleniDTO> getOneByUsername(@PathVariable("username") String username) {
		try {
			ZaposleniDTO zaposleniDTO = zaposleniService.findOneByUsername(username);

			return new ResponseEntity<ZaposleniDTO>(zaposleniDTO, HttpStatus.OK);

		} catch (UserNotFoundException e) {
			return new ResponseEntity<ZaposleniDTO>(HttpStatus.NOT_FOUND);
		}
	}


	@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
	@PostMapping(value = "/update")
	public ResponseEntity<String> updateZaposleni(
			@RequestBody ZaposleniDTO zaposleniDTO) {

		try {
			Zaposleni z = zaposleniService.updateZaposleni(zaposleniDTO);
			return new ResponseEntity<String>("Zaposleni " + z.getIme() + " " + z.getPrezime() + " uspesno azuriran!", HttpStatus.OK);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<String>("Greska u sistemu, pokusajte ponovo...", HttpStatus.NOT_FOUND);
		}
	}

	@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
	@PostMapping(value = "/add")
	public ResponseEntity<String> addZaposleni(
			@RequestBody ZaposleniDTO zaposleniDTO) {

		try {
			Zaposleni z = zaposleniService.addZaposleni(zaposleniDTO);

			return new ResponseEntity<String>("Zaposleni " + z.getIme() + " " + z.getPrezime() + " uspesno dodat!", HttpStatus.OK);

		} catch (UserAlreadyExistsException e) {
			return new ResponseEntity<String>("Korisnicko ime zauzeto...", HttpStatus.BAD_REQUEST);
		}

	}

	@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
	@DeleteMapping(value = "/delete/{username}")
	public ResponseEntity<String> deleteZaposleni(@PathVariable("username") String username) {
		try {
			zaposleniService.deleteZaposleni(username);
			return new ResponseEntity<String>("Zaposleni uspesno obrisan!", HttpStatus.OK);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<String>("Greska u sistemu, pokusajte ponovo...", HttpStatus.NOT_FOUND);
		} 
	}

	@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
	@PutMapping("izmeni-platu")
	public ResponseEntity<ZaposleniDTO> izmeniPlatu(@RequestBody PlataDTO dto) {
		Zaposleni z = zaposleniService.izmeniPlatu(dto);

		return new ResponseEntity<ZaposleniDTO>(HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('GLAVNI_KUVAR')")
	@PostMapping("/new-password/{username}")
	public ResponseEntity<String> newPassword(@PathVariable("username") String username,@RequestBody String newPass){
		
		if(!zaposleniService.changeZaposleniPassword(username, newPass)) {
			return new ResponseEntity<String>("Error",HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<String>("Uspeh",HttpStatus.OK);
	}
}
