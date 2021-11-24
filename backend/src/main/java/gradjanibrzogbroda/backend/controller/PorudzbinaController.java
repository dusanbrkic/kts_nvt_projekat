package gradjanibrzogbroda.backend.controller;

import gradjanibrzogbroda.backend.domain.*;
import gradjanibrzogbroda.backend.dto.JeloDTO;
import gradjanibrzogbroda.backend.dto.JeloPorudzbineDTO;
import gradjanibrzogbroda.backend.dto.PicePorudzbineDTO;
import gradjanibrzogbroda.backend.dto.PorudzbinaDTO;
import gradjanibrzogbroda.backend.service.PorudzbinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/porudzbine")
public class PorudzbinaController {
    @Autowired
    private PorudzbinaService porudzbinaService;

    @GetMapping()
    public ResponseEntity<List<PorudzbinaDTO>> getAll(){
        ArrayList<Porudzbina> porudzbine = (ArrayList<Porudzbina>) porudzbinaService.findAll();
        ArrayList<PorudzbinaDTO> dtos = new ArrayList<PorudzbinaDTO>();
        for (Porudzbina p: porudzbine) {
            dtos.add(new PorudzbinaDTO(p));
        }
        return new ResponseEntity<List<PorudzbinaDTO>>(dtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PorudzbinaDTO> getOne(@PathVariable("id") Integer id){
        Porudzbina porudzbina = porudzbinaService.findOne(id);
        if (porudzbina!=null){
            return new ResponseEntity<PorudzbinaDTO>(new PorudzbinaDTO(porudzbina), HttpStatus.OK);
        }
        return new ResponseEntity<PorudzbinaDTO>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<PorudzbinaDTO>> getAllStatus(@PathVariable("status") StatusPorudzbine status){
        ArrayList<Porudzbina> porudzbine = (ArrayList<Porudzbina>) porudzbinaService.findAllByStatusPorudzbine(status);
        ArrayList<PorudzbinaDTO> dtos = new ArrayList<PorudzbinaDTO>();
        for (Porudzbina p: porudzbine) {
            dtos.add(new PorudzbinaDTO(p));
        }
        return new ResponseEntity<List<PorudzbinaDTO>>(dtos, HttpStatus.OK);
    }

    @GetMapping("/konobar/{konobarId}")
    public ResponseEntity<List<PorudzbinaDTO>> getAllKonobar(@PathVariable("konobarId") Integer konobarId){
        ArrayList<Porudzbina> porudzbine = (ArrayList<Porudzbina>) porudzbinaService.findAllByKonobarId(konobarId);
        ArrayList<PorudzbinaDTO> dtos = new ArrayList<PorudzbinaDTO>();
        for (Porudzbina p: porudzbine) {
            dtos.add(new PorudzbinaDTO(p));
        }
        return new ResponseEntity<List<PorudzbinaDTO>>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<PorudzbinaDTO> napraviPorudzbinu(@RequestBody PorudzbinaDTO dto) {
        Porudzbina porudzbina = porudzbinaService.napraviPorudzbinu(dto);

        return new ResponseEntity<PorudzbinaDTO>(new PorudzbinaDTO(porudzbina), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<PorudzbinaDTO> izmeniPorudzbinu(@RequestBody PorudzbinaDTO dto){
        Porudzbina porudzbina = porudzbinaService.izmeniPorudzbinu(dto);

        return new ResponseEntity<PorudzbinaDTO>(new PorudzbinaDTO(porudzbina), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> obrisiPorudzbinu(@PathVariable("id") Integer id) {
        try {
            porudzbinaService.obrisiPorudzbinuPoId(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/naplati/{id}")
    public ResponseEntity<Object> naplatiPorudzbinu(@PathVariable("id") Integer id) {
        try {
            boolean uspeh = porudzbinaService.naplatiPorudzbinu(id);
            if(uspeh){
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch(EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
