package gradjanibrzogbroda.backend.service;

import gradjanibrzogbroda.backend.domain.*;
import gradjanibrzogbroda.backend.dto.PicePorudzbineDTO;
import gradjanibrzogbroda.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PicePorudzbineService {
    @Autowired
    private PorudzbinaRepository porudzbinaRepository;
    @Autowired
    private PicePorudzbineRepository picePorudzbineRepository;
    @Autowired
    private PiceRepository piceRepository;

    public PicePorudzbine dodajPicePorudzbine(PicePorudzbineDTO dto){
        Porudzbina porudzbina = porudzbinaRepository.findOneById(dto.getPorudzbinaId());
        if (porudzbina.getStatusPorudzbine().equals(StatusPorudzbine.NAPLACENO)){
            return null;
        }

        Pice pice = piceRepository.findOneById(dto.getPiceId());
        PicePorudzbine pp = PicePorudzbine.builder()
                .kolicina(dto.getKolicina())
                .napomena(dto.getNapomena())
                .statusPica(StatusPica.KREIRANO)
                .pice(pice)
                .porudzbina(porudzbina)
                .build();

        porudzbina.setUkupnaCena(porudzbina.getUkupnaCena() + pice.getTrenutnaCena()*dto.getKolicina());
        porudzbina.setStatusPorudzbine(StatusPorudzbine.KREIRANO);
        return picePorudzbineRepository.save(pp);
    }
    public PicePorudzbine izmeniPicePorudzbine(PicePorudzbineDTO dto){
        PicePorudzbine picePorudzbine = picePorudzbineRepository.findOneById(dto.getId());
        if (!picePorudzbine.getStatusPica().equals(StatusPica.KREIRANO)){
            return null;
        }
        Double razlikaKolicine = picePorudzbine.getKolicina() - dto.getKolicina();
        Porudzbina porudzbina = picePorudzbine.getPorudzbina();
        porudzbina.setUkupnaCena(porudzbina.getUkupnaCena()-picePorudzbine.getPice().getTrenutnaCena()*razlikaKolicine);

        picePorudzbine.setKolicina(dto.getKolicina());
        picePorudzbine.setNapomena(dto.getNapomena());
        return picePorudzbineRepository.save(picePorudzbine);
    }
    public boolean obrisiPicePorudzbine(Integer id){
        PicePorudzbine picePorudzbine = picePorudzbineRepository.findOneById(id);
        if (!picePorudzbine.getStatusPica().equals(StatusPica.KREIRANO)){
            return false;
        }
        Porudzbina porudzbina = picePorudzbine.getPorudzbina();
        porudzbina.setUkupnaCena(porudzbina.getUkupnaCena()-picePorudzbine.getPice().getTrenutnaCena()*picePorudzbine.getKolicina());
        picePorudzbineRepository.deleteById(id);
        return true;
    }
}
