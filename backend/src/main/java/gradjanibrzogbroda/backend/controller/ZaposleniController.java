package gradjanibrzogbroda.backend.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

import gradjanibrzogbroda.backend.domain.GlavniKuvar;
import gradjanibrzogbroda.backend.domain.Konobar;
import gradjanibrzogbroda.backend.domain.Kuvar;
import gradjanibrzogbroda.backend.domain.Menadzer;
import gradjanibrzogbroda.backend.domain.Pol;
import gradjanibrzogbroda.backend.domain.Sanker;
import gradjanibrzogbroda.backend.domain.TipZaposlenja;
import gradjanibrzogbroda.backend.domain.Zaposleni;
import gradjanibrzogbroda.backend.dto.PlataDTO;
import gradjanibrzogbroda.backend.dto.ZaposleniDTO;
import gradjanibrzogbroda.backend.repository.ZaposleniRepository;
import gradjanibrzogbroda.backend.service.ZaposleniService;

@RestController
@RequestMapping("/zaposleni")
public class ZaposleniController {

    @Autowired
    private ZaposleniService zaposleniService;

    @GetMapping(value = "/all")
	public ResponseEntity<List<ZaposleniDTO>> getAllZaposleni(){
		
		List<Zaposleni> zaposleni = zaposleniService.findAll();
		
		List<ZaposleniDTO> zaposleniDTOs = 
            zaposleni.stream().map(new Function<Zaposleni,ZaposleniDTO>() {
                @Override
                public ZaposleniDTO apply(Zaposleni zaposleni) {
                    return new ZaposleniDTO(zaposleni);
                }
            }).collect(Collectors.toList());
		
		return new ResponseEntity<List<ZaposleniDTO>>(zaposleniDTOs, HttpStatus.OK);
	}

    @GetMapping(value = "/id/{id}")
	public ResponseEntity<ZaposleniDTO> getOneById(@PathVariable("id") Integer id){
		
		Zaposleni z = zaposleniService.findOneById(id);
		
		if(z!=null) {
			return new ResponseEntity<ZaposleniDTO>(new ZaposleniDTO(z),HttpStatus.OK);
		}else {
			return new ResponseEntity<ZaposleniDTO>(HttpStatus.NOT_FOUND);
		}
		
		
	}
	
	@PostMapping(value = "/updateAdd")
	public ResponseEntity<String> updateAddZaposleni(@RequestParam Integer id,
													@RequestParam String ime,
													@RequestParam String prezime,
													@RequestParam String pol,
													@RequestParam String datumRodjenja,
													@RequestParam Double trenutnaPlata,
													@RequestParam String tipZaposlenja,
													@RequestParam String slikaString ) {

		ZaposleniDTO zaposleniDTO = new ZaposleniDTO(id, ime, prezime, Pol.valueOf(pol), LocalDate.parse(datumRodjenja, DateTimeFormatter.ofPattern("yyyy-MM-dd")), trenutnaPlata, TipZaposlenja.valueOf(tipZaposlenja), slikaString, null);

        Zaposleni zaposleni = zaposleniService.findOneById(zaposleniDTO.getId());

		if (zaposleni == null) {
			if (zaposleniDTO.getTipZaposlenja()==TipZaposlenja.GLAVNI_KUVAR)
				zaposleni = zaposleniService.updateAddZaposleni(new GlavniKuvar(zaposleniDTO));
			if (zaposleniDTO.getTipZaposlenja()==TipZaposlenja.KONOBAR)
				zaposleni = zaposleniService.updateAddZaposleni(new Konobar(zaposleniDTO));
			if (zaposleniDTO.getTipZaposlenja()==TipZaposlenja.KUVAR)
				zaposleni = zaposleniService.updateAddZaposleni(new Kuvar(zaposleniDTO));
			if (zaposleniDTO.getTipZaposlenja()==TipZaposlenja.MENADZER)
				zaposleni = zaposleniService.updateAddZaposleni(new Menadzer(zaposleniDTO));
			if (zaposleniDTO.getTipZaposlenja()==TipZaposlenja.SANKER) 
				zaposleni = zaposleniService.updateAddZaposleni(new Sanker(zaposleniDTO));
		} else {
			zaposleni.updateFields(zaposleniDTO);
		}

		zaposleniService.updateAddZaposleni(zaposleni);

        if (zaposleni!=null)
		    return new ResponseEntity<String>("Succesful!", HttpStatus.OK);
        else 
            return new ResponseEntity<String>("Something went wrong!", HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping(value = "/delete")
	public ResponseEntity<Object> deleteZaposleni(@RequestParam("id") Integer id) {
		try {
			zaposleniService.deleteZaposleni(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(EmptyResultDataAccessException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
