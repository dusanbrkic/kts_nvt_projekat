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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pice-porudzbine")
public class PicePorudzbineController {
    @Autowired
    PicePorudzbineService picePorudzbineService;

    @PreAuthorize("hasRole('KONOBAR')")
    @PostMapping("/dostavi/{id}")
    public ResponseEntity<Object> dostaviPicePorudzbine(@PathVariable("id") Integer id) {
        try {
            PicePorudzbine pice  = picePorudzbineService.dostaviPice(id);
        } catch (PicePorudzbineNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (NeodgovarajuciStatusException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
