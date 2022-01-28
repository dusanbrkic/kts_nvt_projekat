package gradjanibrzogbroda.backend.service;

import gradjanibrzogbroda.backend.domain.*;
import gradjanibrzogbroda.backend.dto.JeloPorudzbineDTO;
import gradjanibrzogbroda.backend.dto.PorudzbinaDTO;
import gradjanibrzogbroda.backend.exceptions.*;
import gradjanibrzogbroda.backend.repository.*;
import gradjanibrzogbroda.backend.util.PorudzbinaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JeloPorudzbineService {
    @Autowired
    private PorudzbinaRepository porudzbinaRepository;
    @Autowired
    private JeloPorudzbineRepository jeloPorudzbineRepository;
    @Autowired
    private JeloRepository jeloRepository;

    PorudzbinaUtil porudzbinaUtil = new PorudzbinaUtil();

    public List<JeloPorudzbine> findAll(){
        return jeloPorudzbineRepository.findAll();
    }

    public List<JeloPorudzbine> getAllPreuzeto(){
        return jeloPorudzbineRepository.findAllByStatusJela(StatusJela.PREUZETO);
    }

    public JeloPorudzbine findOne(Integer id) throws JeloPorudzbineNotFoundException {
        JeloPorudzbine pronadjeno = jeloPorudzbineRepository.findOneById(id);
        if (pronadjeno == null){
            throw new JeloPorudzbineNotFoundException("Nije pronadjeno jelo porudzbine sa zadatim id.");
        }
        return pronadjeno;
    }


    public JeloPorudzbine dodajJeloPorudzbine(JeloPorudzbineDTO dto) throws PorudzbinaNotFoundException, JeloNotFoundException, PorudzbinaNaplacenaException, NepozitivnaKolicinaException {
        Porudzbina porudzbina = porudzbinaRepository.findOneById(dto.getPorudzbinaId());
        if (porudzbina == null){
            throw new PorudzbinaNotFoundException("Nije pronadjena porudzbina sa zadatim id.");
        }
        if (porudzbina.getStatusPorudzbine().equals(StatusPorudzbine.NAPLACENO)){
            throw new PorudzbinaNaplacenaException("Porudzbina je vec naplacena.");
        }

        Jelo jelo = jeloRepository.findOneById(dto.getJeloId());
        if (jelo == null){
            throw new JeloNotFoundException("Nije pronadjeno jelo sa zadatim id.");
        }

        if(dto.getKolicina() <= 0){
            throw new NepozitivnaKolicinaException("Kolicina mora biti pozitivan broj");
        }

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

    public JeloPorudzbine izmeniJeloPorudzbine(JeloPorudzbineDTO dto) throws JeloPorudzbineNotFoundException, NepozitivnaKolicinaException, NeodgovarajuciStatusException {
        JeloPorudzbine jeloPorudzbine = jeloPorudzbineRepository.findOneById(dto.getId());
        if (jeloPorudzbine == null){
            throw new JeloPorudzbineNotFoundException("Nije pronadjeno jelo sa zadatim id.");
        }
        else if (!jeloPorudzbine.getStatusJela().equals(StatusJela.KREIRANO)){
            throw new NeodgovarajuciStatusException("Jelo porudzbine je vec preuzeto - nemoguca izmena.");
        }
        if(dto.getKolicina() <= 0){
            throw new NepozitivnaKolicinaException("Kolicina mora biti pozitivan broj");
        }
        Double razlikaKolicine = jeloPorudzbine.getKolicina() - dto.getKolicina();
        Porudzbina porudzbina = jeloPorudzbine.getPorudzbina();
        porudzbina.setUkupnaCena(porudzbina.getUkupnaCena()-jeloPorudzbine.getJelo().getTrenutnaCena()*razlikaKolicine);

        jeloPorudzbine.setKolicina(dto.getKolicina());
        jeloPorudzbine.setNapomena(dto.getNapomena());
        return jeloPorudzbineRepository.save(jeloPorudzbine);
    }

    public boolean obrisiJeloPorudzbine(Integer id) throws JeloPorudzbineNotFoundException, NeodgovarajuciStatusException {
        JeloPorudzbine jeloPorudzbine = jeloPorudzbineRepository.findOneById(id);
        if (jeloPorudzbine == null){
            throw new JeloPorudzbineNotFoundException("Nije pronadjeno jelo sa zadatim id.");
        }
        else if (!jeloPorudzbine.getStatusJela().equals(StatusJela.KREIRANO)){
            throw new NeodgovarajuciStatusException("Jelo porudzbine je vec preuzeto - nemoguca izmena.");
        }
        Porudzbina porudzbina = jeloPorudzbine.getPorudzbina();
        porudzbina.setUkupnaCena(porudzbina.getUkupnaCena()-jeloPorudzbine.getJelo().getTrenutnaCena()*jeloPorudzbine.getKolicina());
        jeloPorudzbineRepository.deleteById(id);
        return true;
    }

    public boolean preuzmiJelo(Integer id) throws JeloPorudzbineNotFoundException, NeodgovarajuciStatusException {
        JeloPorudzbine jeloPorudzbine = jeloPorudzbineRepository.findOneById(id);
        if (jeloPorudzbine == null){
            throw new JeloPorudzbineNotFoundException("Nije pronadjeno jelo sa zadatim id.");
        }
        else if (!jeloPorudzbine.getStatusJela().equals(StatusJela.KREIRANO)){
            throw new NeodgovarajuciStatusException("Neodgovarajuci status - jelo nije u statusu kreirano.");
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

    public boolean pripremiJelo(Integer id) throws JeloPorudzbineNotFoundException, NeodgovarajuciStatusException {
        JeloPorudzbine jeloPorudzbine = jeloPorudzbineRepository.findOneById(id);
        if (jeloPorudzbine == null){
            throw new JeloPorudzbineNotFoundException("Nije pronadjeno jelo sa zadatim id.");
        }
        else if (!jeloPorudzbine.getStatusJela().equals(StatusJela.PREUZETO)){
            throw new NeodgovarajuciStatusException("Neodgovarajuci status - jelo nije u statusu preuzeto.");
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

    public boolean dostaviJelo(Integer id) throws JeloPorudzbineNotFoundException, NeodgovarajuciStatusException {
        JeloPorudzbine jeloPorudzbine = jeloPorudzbineRepository.findOneById(id);
        if (jeloPorudzbine == null){
            throw new JeloPorudzbineNotFoundException("Nije pronadjeno jelo sa zadatim id.");
        }
        else if (!jeloPorudzbine.getStatusJela().equals(StatusJela.PRIPREMLJENO)){
            throw new NeodgovarajuciStatusException("Neodgovarajuci status - jelo nije u statusu pripremljeno.");
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
