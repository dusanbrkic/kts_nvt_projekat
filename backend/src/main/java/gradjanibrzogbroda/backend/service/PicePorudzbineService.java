package gradjanibrzogbroda.backend.service;

import gradjanibrzogbroda.backend.domain.*;
import gradjanibrzogbroda.backend.dto.PicePorudzbineDTO;
import gradjanibrzogbroda.backend.exceptions.*;
import gradjanibrzogbroda.backend.repository.*;
import gradjanibrzogbroda.backend.util.PorudzbinaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PicePorudzbineService {
    @Autowired
    private PorudzbinaRepository porudzbinaRepository;
    @Autowired
    private PicePorudzbineRepository picePorudzbineRepository;
    @Autowired
    private PiceRepository piceRepository;

    PorudzbinaUtil porudzbinaUtil = new PorudzbinaUtil();

    public List<PicePorudzbine> findAll(){
        return picePorudzbineRepository.findAll();
    }

    public PicePorudzbine findOne(Integer id) throws PicePorudzbineNotFoundException {
        PicePorudzbine pronadjeno = picePorudzbineRepository.findOneById(id);
        if (pronadjeno == null){
            throw new PicePorudzbineNotFoundException("Nije pronadjeno pice porudzbine sa zadatim id.");
        }
        return pronadjeno;
    }


    public PicePorudzbine dodajPicePorudzbine(PicePorudzbineDTO dto) throws PorudzbinaNotFoundException, PorudzbinaNaplacenaException, PiceNotFoundException, NepozitivnaKolicinaException {
        Porudzbina porudzbina = porudzbinaRepository.findOneById(dto.getPorudzbinaId());
        if (porudzbina == null){
            throw new PorudzbinaNotFoundException("Nije pronadjena porudzbina sa zadatim id.");
        }
        if (porudzbina.getStatusPorudzbine().equals(StatusPorudzbine.NAPLACENO)){
            throw new PorudzbinaNaplacenaException("Porudzbina je vec naplacena.");
        }

        Pice pice = piceRepository.findOneById(dto.getPiceId());
        if (pice == null){
            throw new PiceNotFoundException("Nije pronadjeno pice sa zadatim id.");
        }

        if(dto.getKolicina() <= 0){
            throw new NepozitivnaKolicinaException("Kolicina mora biti pozitivan broj");
        }
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
    public PicePorudzbine izmeniPicePorudzbine(PicePorudzbineDTO dto) throws PicePorudzbineNotFoundException, NepozitivnaKolicinaException, NeodgovarajuciStatusException {
        PicePorudzbine picePorudzbine = picePorudzbineRepository.findOneById(dto.getId());
        if (picePorudzbine == null){
            throw new PicePorudzbineNotFoundException("Nije pronadjeno pice sa zadatim id.");
        }
        else if (!picePorudzbine.getStatusPica().equals(StatusPica.KREIRANO)){
            throw new NeodgovarajuciStatusException("Pice porudzbine je vec pripremljeno - nemoguca izmena.");
        }
        if(dto.getKolicina() <= 0){
            throw new NepozitivnaKolicinaException("Kolicina mora biti pozitivan broj");
        }
        Double razlikaKolicine = picePorudzbine.getKolicina() - dto.getKolicina();
        Porudzbina porudzbina = picePorudzbine.getPorudzbina();
        porudzbina.setUkupnaCena(porudzbina.getUkupnaCena()-picePorudzbine.getPice().getTrenutnaCena()*razlikaKolicine);

        picePorudzbine.setKolicina(dto.getKolicina());
        picePorudzbine.setNapomena(dto.getNapomena());
        return picePorudzbineRepository.save(picePorudzbine);
    }
    public boolean obrisiPicePorudzbine(Integer id) throws PicePorudzbineNotFoundException, NeodgovarajuciStatusException {
        PicePorudzbine picePorudzbine = picePorudzbineRepository.findOneById(id);

        if (picePorudzbine == null){
            throw new PicePorudzbineNotFoundException("Nije pronadjeno pice sa zadatim id.");
        }
        else if (!picePorudzbine.getStatusPica().equals(StatusPica.KREIRANO)){
            throw new NeodgovarajuciStatusException("Pice porudzbine je vec pripremljeno - nemoguce brisanje.");
        }
        Porudzbina porudzbina = picePorudzbine.getPorudzbina();
        porudzbina.setUkupnaCena(porudzbina.getUkupnaCena()-picePorudzbine.getPice().getTrenutnaCena()*picePorudzbine.getKolicina());
        picePorudzbineRepository.deleteById(id);
        return true;
    }

    public boolean pripremiPice(Integer id) throws PicePorudzbineNotFoundException, NeodgovarajuciStatusException {
        PicePorudzbine picePorudzbine = picePorudzbineRepository.findOneById(id);
        if (picePorudzbine == null){
            throw new PicePorudzbineNotFoundException("Nije pronadjeno pice sa zadatim id.");
        }
        else if (!picePorudzbine.getStatusPica().equals(StatusPica.KREIRANO)){
            throw new NeodgovarajuciStatusException("Neodgovarajuci status - jelo nije u statusu kreirano.");
        }
        picePorudzbine.setStatusPica(StatusPica.PRIPREMLJENO);
        picePorudzbineRepository.save(picePorudzbine);
        Porudzbina porudzbina = picePorudzbine.getPorudzbina();
        if (porudzbinaUtil.promeniStatusPorudzbine(porudzbina, StatusJela.PRIPREMLJENO, StatusPica.PRIPREMLJENO)){
            porudzbina.setStatusPorudzbine(StatusPorudzbine.PRIPREMLJENO);
            porudzbinaRepository.save(porudzbina);
        }
        return true;
    }

    public boolean dostaviPice(Integer id) throws NeodgovarajuciStatusException, PicePorudzbineNotFoundException {
        PicePorudzbine picePorudzbine = picePorudzbineRepository.findOneById(id);
        if (picePorudzbine == null){
            throw new PicePorudzbineNotFoundException("Nije pronadjeno pice sa zadatim id.");
        }
        else if (!picePorudzbine.getStatusPica().equals(StatusPica.PRIPREMLJENO)){
            throw new NeodgovarajuciStatusException("Neodgovarajuci status - jelo nije u statusu pripremljeno.");
        }
        picePorudzbine.setStatusPica(StatusPica.DOSTAVLJENO);
        picePorudzbineRepository.save(picePorudzbine);
        Porudzbina porudzbina = picePorudzbine.getPorudzbina();
        if (porudzbinaUtil.promeniStatusPorudzbine(porudzbina, StatusJela.DOSTAVLJENO, StatusPica.DOSTAVLJENO)){
            porudzbina.setStatusPorudzbine(StatusPorudzbine.DOSTAVLJENO);
            porudzbinaRepository.save(porudzbina);
        }
        return true;
    }


}
