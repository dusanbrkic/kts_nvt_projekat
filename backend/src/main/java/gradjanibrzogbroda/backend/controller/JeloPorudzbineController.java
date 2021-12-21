package gradjanibrzogbroda.backend.controller;

import gradjanibrzogbroda.backend.domain.JeloPorudzbine;
import gradjanibrzogbroda.backend.domain.PicePorudzbine;
import gradjanibrzogbroda.backend.dto.JeloPorudzbineDTO;
import gradjanibrzogbroda.backend.dto.PicePorudzbineDTO;
import gradjanibrzogbroda.backend.exceptions.*;
import gradjanibrzogbroda.backend.service.JeloPorudzbineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jelo-porudzbine")
public class JeloPorudzbineController {

    @Autowired
    JeloPorudzbineService jeloPorudzbineService;

    @PostMapping()
    public ResponseEntity<JeloPorudzbineDTO> dodajJeloPorudzbine(@RequestBody JeloPorudzbineDTO dto) {
        JeloPorudzbine jelo = null;
        try {
            jelo = jeloPorudzbineService.dodajJeloPorudzbine(dto);
        } catch (PorudzbinaNotFoundException e) {
            return new ResponseEntity<JeloPorudzbineDTO>(new JeloPorudzbineDTO(jelo), HttpStatus.NOT_FOUND);
        } catch (JeloNotFoundException e) {
            return new ResponseEntity<JeloPorudzbineDTO>(new JeloPorudzbineDTO(jelo), HttpStatus.NOT_FOUND);
        } catch (PorudzbinaNaplacenaException e) {
            return new ResponseEntity<JeloPorudzbineDTO>(new JeloPorudzbineDTO(jelo), HttpStatus.BAD_REQUEST);
        } catch (NepozitivnaKolicinaException e) {
            return new ResponseEntity<JeloPorudzbineDTO>(new JeloPorudzbineDTO(jelo), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<JeloPorudzbineDTO>(new JeloPorudzbineDTO(jelo), HttpStatus.OK);
    }


    @PutMapping()
    public ResponseEntity<JeloPorudzbineDTO> izmeniJeloPorudzbine(@RequestBody JeloPorudzbineDTO dto) {
        JeloPorudzbine jelo = null;
        try {
            jelo = jeloPorudzbineService.izmeniJeloPorudzbine(dto);
        } catch (JeloPorudzbineNotFoundException e) {
            return new ResponseEntity<JeloPorudzbineDTO>(new JeloPorudzbineDTO(jelo), HttpStatus.NOT_FOUND);
        } catch (JeloPorudzbineVecPreuzetoException e) {
            return new ResponseEntity<JeloPorudzbineDTO>(new JeloPorudzbineDTO(jelo), HttpStatus.BAD_REQUEST);
        } catch (NepozitivnaKolicinaException e) {
            return new ResponseEntity<JeloPorudzbineDTO>(new JeloPorudzbineDTO(jelo), HttpStatus.BAD_REQUEST);
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

    @PutMapping("/preuzmi/{id}")
    public ResponseEntity<Object> preuzmiJeloPorudzbine(@PathVariable("id") Integer id) {
        try {
            boolean uspeh = jeloPorudzbineService.preuzmiJelo(id);
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
    public ResponseEntity<Object> pripremiJeloPorudzbine(@PathVariable("id") Integer id) {
        try {
            boolean uspeh = jeloPorudzbineService.pripremiJelo(id);
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

    @PutMapping("/dostavi/{id}")
    public ResponseEntity<Object> dostaviJeloPorudzbine(@PathVariable("id") Integer id) {
        try {
            boolean uspeh = jeloPorudzbineService.dostaviJelo(id);
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
