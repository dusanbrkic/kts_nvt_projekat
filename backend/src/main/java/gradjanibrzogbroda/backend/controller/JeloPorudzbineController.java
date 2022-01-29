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
import org.springframework.security.access.prepost.PreAuthorize;
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


    @PreAuthorize("hasRole('KUVAR') or hasRole('GLAVNI_KUVAR')")
    @PostMapping("/pripremi/{id}")
    public ResponseEntity<Object> pripremiJeloPorudzbine(@PathVariable("id") Integer id) {
        try {
            JeloPorudzbine j = jeloPorudzbineService.pripremiJelo(id);
            this.notificationService.spremiJeloNotifiation(j.getJelo().getNaziv(), j.getKolicina(), j.getPorudzbina().getId());

        } catch (NeodgovarajuciStatusException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (JeloPorudzbineNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('KONOBAR')")
    @PostMapping("/dostavi/{id}")
    public ResponseEntity<Object> dostaviJeloPorudzbine(@PathVariable("id") Integer id) {
        try {
            JeloPorudzbine j  = jeloPorudzbineService.dostaviJelo(id);

        } catch (NeodgovarajuciStatusException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (JeloPorudzbineNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
