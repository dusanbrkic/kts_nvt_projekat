package gradjanibrzogbroda.backend.service;

import gradjanibrzogbroda.backend.domain.*;
import gradjanibrzogbroda.backend.dto.JeloPorudzbineDTO;
import gradjanibrzogbroda.backend.repository.*;
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
        if (!jeloPorudzbine.getStatusJela().equals(StatusJela.KREIRANO)){
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
        if (!jeloPorudzbine.getStatusJela().equals(StatusJela.KREIRANO)){
            return false;
        }
        Porudzbina porudzbina = jeloPorudzbine.getPorudzbina();
        porudzbina.setUkupnaCena(porudzbina.getUkupnaCena()-jeloPorudzbine.getJelo().getTrenutnaCena()*jeloPorudzbine.getKolicina());
        jeloPorudzbineRepository.deleteById(id);
        return true;
    }
}
