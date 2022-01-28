package gradjanibrzogbroda.backend.controller;

import gradjanibrzogbroda.backend.domain.PicePorudzbine;
import gradjanibrzogbroda.backend.dto.JeloPorudzbineDTO;
import gradjanibrzogbroda.backend.dto.PicePorudzbineDTO;
import gradjanibrzogbroda.backend.exceptions.*;
import gradjanibrzogbroda.backend.service.PicePorudzbineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pice-porudzbine")
public class PicePorudzbineController {
    @Autowired
    PicePorudzbineService picePorudzbineService;

    @PostMapping()
    public ResponseEntity<PicePorudzbineDTO> dodajPicePorudzbine(@RequestBody PicePorudzbineDTO dto) {
        PicePorudzbine pice = null;
        try {
            pice = picePorudzbineService.dodajPicePorudzbine(dto);
        } catch (PorudzbinaNotFoundException e) {
            return new ResponseEntity<PicePorudzbineDTO>(new PicePorudzbineDTO(), HttpStatus.NOT_FOUND);
        } catch (PorudzbinaNaplacenaException e) {
            return new ResponseEntity<PicePorudzbineDTO>(new PicePorudzbineDTO(), HttpStatus.BAD_REQUEST);
        } catch (PiceNotFoundException e) {
            return new ResponseEntity<PicePorudzbineDTO>(new PicePorudzbineDTO(), HttpStatus.NOT_FOUND);
        } catch (NepozitivnaKolicinaException e) {
            return new ResponseEntity<PicePorudzbineDTO>(new PicePorudzbineDTO(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<PicePorudzbineDTO>(new PicePorudzbineDTO(pice), HttpStatus.OK);
    }



    @PutMapping()
    public ResponseEntity<PicePorudzbineDTO> izmeniPicePorudzbine(@RequestBody PicePorudzbineDTO dto) {
        PicePorudzbine pice = null;
        try {
            pice = picePorudzbineService.izmeniPicePorudzbine(dto);
        } catch (PicePorudzbineNotFoundException e) {
            return new ResponseEntity<PicePorudzbineDTO>(new PicePorudzbineDTO(), HttpStatus.NOT_FOUND);
        } catch (NepozitivnaKolicinaException e) {
            return new ResponseEntity<PicePorudzbineDTO>(new PicePorudzbineDTO(), HttpStatus.BAD_REQUEST);
        } catch (NeodgovarajuciStatusException e) {
            return new ResponseEntity<PicePorudzbineDTO>(new PicePorudzbineDTO(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<PicePorudzbineDTO>(new PicePorudzbineDTO(pice), HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Object> obrisiPicePorudzbine(@PathVariable("id") Integer id) {
        try {
            boolean uspeh = picePorudzbineService.obrisiPicePorudzbine(id);;
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

    @PutMapping("/pripremi/{id}")
    public ResponseEntity<Object> pripremiPicePorudzbine(@PathVariable("id") Integer id) {
        try {
            PicePorudzbine pice = picePorudzbineService.pripremiPice(id);
        }catch(EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/dostavi/{id}")
    public ResponseEntity<Object> dostaviPicePorudzbine(@PathVariable("id") Integer id) {
        try {
            PicePorudzbine pice  = picePorudzbineService.dostaviPice(id);
        }catch(EmptyResultDataAccessException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
