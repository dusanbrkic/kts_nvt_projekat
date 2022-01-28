package gradjanibrzogbroda.backend.controller;

import gradjanibrzogbroda.backend.domain.JeloPorudzbine;
import gradjanibrzogbroda.backend.domain.PicePorudzbine;
import gradjanibrzogbroda.backend.domain.Porudzbina;
import gradjanibrzogbroda.backend.dto.JeloPorudzbineDTO;
import gradjanibrzogbroda.backend.dto.PicePorudzbineDTO;
import gradjanibrzogbroda.backend.dto.PorudzbinaDTO;
import gradjanibrzogbroda.backend.exceptions.*;
import gradjanibrzogbroda.backend.service.JeloPorudzbineService;
import gradjanibrzogbroda.backend.service.NotificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/jelo-porudzbine")
public class JeloPorudzbineController {

    @Autowired
    JeloPorudzbineService jeloPorudzbineService;
    
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/preuzeta")
    public ResponseEntity<List<JeloPorudzbineDTO>> getAllPreuzeta(){
        ArrayList<JeloPorudzbine> jela = (ArrayList<JeloPorudzbine>) jeloPorudzbineService.getAllPreuzeto();
        ArrayList<JeloPorudzbineDTO> dtos = new ArrayList<JeloPorudzbineDTO>();
        for (JeloPorudzbine p: jela) {
            dtos.add(new JeloPorudzbineDTO(p));
        }
        return new ResponseEntity<List<JeloPorudzbineDTO>>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<JeloPorudzbineDTO> dodajJeloPorudzbine(@RequestBody JeloPorudzbineDTO dto) {
        JeloPorudzbine jelo = null;
        try {
            jelo = jeloPorudzbineService.dodajJeloPorudzbine(dto);
        } catch (PorudzbinaNotFoundException e) {
            return new ResponseEntity<JeloPorudzbineDTO>(new JeloPorudzbineDTO(), HttpStatus.NOT_FOUND);
        } catch (JeloNotFoundException e) {
            return new ResponseEntity<JeloPorudzbineDTO>(new JeloPorudzbineDTO(), HttpStatus.NOT_FOUND);
        } catch (PorudzbinaNaplacenaException e) {
            return new ResponseEntity<JeloPorudzbineDTO>(new JeloPorudzbineDTO(), HttpStatus.BAD_REQUEST);
        } catch (NepozitivnaKolicinaException e) {
            return new ResponseEntity<JeloPorudzbineDTO>(new JeloPorudzbineDTO(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<JeloPorudzbineDTO>(new JeloPorudzbineDTO(jelo), HttpStatus.OK);
    }


    @PutMapping()
    public ResponseEntity<JeloPorudzbineDTO> izmeniJeloPorudzbine(@RequestBody JeloPorudzbineDTO dto) {
        JeloPorudzbine jelo = null;
        try {
            jelo = jeloPorudzbineService.izmeniJeloPorudzbine(dto);
        } catch (JeloPorudzbineNotFoundException e) {
            return new ResponseEntity<JeloPorudzbineDTO>(new JeloPorudzbineDTO(), HttpStatus.NOT_FOUND);
        }  catch (NepozitivnaKolicinaException e) {
            return new ResponseEntity<JeloPorudzbineDTO>(new JeloPorudzbineDTO(), HttpStatus.BAD_REQUEST);
        } catch (NeodgovarajuciStatusException e) {
            return new ResponseEntity<JeloPorudzbineDTO>(new JeloPorudzbineDTO(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<JeloPorudzbineDTO>(new JeloPorudzbineDTO(jelo), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> obrisiJeloPorudzbine(@PathVariable("id") Integer id) {
        try {
            boolean uspeh = jeloPorudzbineService.obrisiJeloPorudzbine(id);
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

    @PostMapping("/preuzmi/{id}")
    public ResponseEntity<Object> preuzmiJeloPorudzbine(@PathVariable("id") Integer id) {
        try {
            JeloPorudzbine jelo = jeloPorudzbineService.preuzmiJelo(id);

        }catch(EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/pripremi/{id}")
    public ResponseEntity<Object> pripremiJeloPorudzbine(@PathVariable("id") Integer id) {
        try {
            JeloPorudzbine j = jeloPorudzbineService.pripremiJelo(id);
            this.notificationService.spremiJeloNotifiation(j.getJelo().getNaziv(), j.getKolicina(), j.getPorudzbina().getId());

        }catch(EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/dostavi/{id}")
    public ResponseEntity<Object> dostaviJeloPorudzbine(@PathVariable("id") Integer id) {
        try {
            JeloPorudzbine j  = jeloPorudzbineService.dostaviJelo(id);

        }catch(EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
