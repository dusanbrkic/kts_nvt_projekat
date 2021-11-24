package gradjanibrzogbroda.backend.service;

import gradjanibrzogbroda.backend.domain.*;
import gradjanibrzogbroda.backend.dto.JeloPorudzbineDTO;
import gradjanibrzogbroda.backend.repository.*;
import gradjanibrzogbroda.backend.util.PorudzbinaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JeloPorudzbineService {
    @Autowired
    private PorudzbinaRepository porudzbinaRepository;
    @Autowired
    private JeloPorudzbineRepository jeloPorudzbineRepository;
    @Autowired
    private JeloRepository jeloRepository;

    PorudzbinaUtil porudzbinaUtil = new PorudzbinaUtil();

    public JeloPorudzbine dodajJeloPorudzbine(JeloPorudzbineDTO dto){
        Porudzbina porudzbina = porudzbinaRepository.findOneById(dto.getPorudzbinaId());
        if (porudzbina.getStatusPorudzbine().equals(StatusPorudzbine.NAPLACENO)){
            return null;
        }

        Jelo jelo = jeloRepository.findOneById(dto.getJeloId());
        JeloPorudzbine jp = JeloPorudzbine.builder()
                .kolicina(dto.getKolicina())
                .napomena(dto.getNapomena())
                .statusJela(StatusJela.KREIRANO)
                .jelo(jelo)
                .porudzbina(porudzbina)
                .build();
        porudzbina.setUkupnaCena(porudzbina.getUkupnaCena() + jelo.getTrenutnaCena()*dto.getKolicina());
        porudzbina.setStatusPorudzbine(StatusPorudzbine.KREIRANO);
        return jeloPorudzbineRepository.save(jp);
    }

    public JeloPorudzbine izmeniJeloPorudzbine(JeloPorudzbineDTO dto){
        JeloPorudzbine jeloPorudzbine = jeloPorudzbineRepository.findOneById(dto.getId());
        if (jeloPorudzbine == null){
            return null;
        }
        else if (!jeloPorudzbine.getStatusJela().equals(StatusJela.KREIRANO)){
            return null;
        }
        Double razlikaKolicine = jeloPorudzbine.getKolicina() - dto.getKolicina();
        Porudzbina porudzbina = jeloPorudzbine.getPorudzbina();
        porudzbina.setUkupnaCena(porudzbina.getUkupnaCena()-jeloPorudzbine.getJelo().getTrenutnaCena()*razlikaKolicine);

        jeloPorudzbine.setKolicina(dto.getKolicina());
        jeloPorudzbine.setNapomena(dto.getNapomena());
        return jeloPorudzbineRepository.save(jeloPorudzbine);
    }

    public boolean obrisiJeloPorudzbine(Integer id){
        JeloPorudzbine jeloPorudzbine = jeloPorudzbineRepository.findOneById(id);
        if (jeloPorudzbine == null){
            return false;
        }
        else if (!jeloPorudzbine.getStatusJela().equals(StatusJela.KREIRANO)){
            return false;
        }
        Porudzbina porudzbina = jeloPorudzbine.getPorudzbina();
        porudzbina.setUkupnaCena(porudzbina.getUkupnaCena()-jeloPorudzbine.getJelo().getTrenutnaCena()*jeloPorudzbine.getKolicina());
        jeloPorudzbineRepository.deleteById(id);
        return true;
    }

    public boolean preuzmiJelo(Integer id){
        JeloPorudzbine jeloPorudzbine = jeloPorudzbineRepository.findOneById(id);
        if (jeloPorudzbine == null){
            return false;
        }
        else if (!jeloPorudzbine.getStatusJela().equals(StatusJela.KREIRANO)){
            return false;
        }
        jeloPorudzbine.setStatusJela(StatusJela.PREUZETO);
        jeloPorudzbineRepository.save(jeloPorudzbine);
        Porudzbina porudzbina = jeloPorudzbine.getPorudzbina();
        if (porudzbinaUtil.promeniStatusPorudzbine(porudzbina, StatusJela.PREUZETO, StatusPica.KREIRANO)){
            porudzbina.setStatusPorudzbine(StatusPorudzbine.PREUZETO);
            porudzbinaRepository.save(porudzbina);
        }
        return true;
    }

    public boolean pripremiJelo(Integer id){
        JeloPorudzbine jeloPorudzbine = jeloPorudzbineRepository.findOneById(id);
        if (jeloPorudzbine == null){
            return false;
        }
        else if (!jeloPorudzbine.getStatusJela().equals(StatusJela.PREUZETO)){
            return false;
        }
        jeloPorudzbine.setStatusJela(StatusJela.PRIPREMLJENO);
        jeloPorudzbineRepository.save(jeloPorudzbine);
        Porudzbina porudzbina = jeloPorudzbine.getPorudzbina();
        if (porudzbinaUtil.promeniStatusPorudzbine(porudzbina, StatusJela.PRIPREMLJENO, StatusPica.PRIPREMLJENO)){
            porudzbina.setStatusPorudzbine(StatusPorudzbine.PRIPREMLJENO);
            porudzbinaRepository.save(porudzbina);
        }
        return true;
    }

    public boolean dostaviJelo(Integer id){
        JeloPorudzbine jeloPorudzbine = jeloPorudzbineRepository.findOneById(id);
        if (jeloPorudzbine == null){
            return false;
        }
        else if (!jeloPorudzbine.getStatusJela().equals(StatusJela.PRIPREMLJENO)){
            return false;
        }
        jeloPorudzbine.setStatusJela(StatusJela.DOSTAVLJENO);
        jeloPorudzbineRepository.save(jeloPorudzbine);
        Porudzbina porudzbina = jeloPorudzbine.getPorudzbina();
        if (porudzbinaUtil.promeniStatusPorudzbine(porudzbina, StatusJela.DOSTAVLJENO, StatusPica.DOSTAVLJENO)){
            porudzbina.setStatusPorudzbine(StatusPorudzbine.DOSTAVLJENO);
            porudzbinaRepository.save(porudzbina);
        }
        return true;
    }


}
