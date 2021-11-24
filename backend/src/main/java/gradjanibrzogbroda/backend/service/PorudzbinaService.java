package gradjanibrzogbroda.backend.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import gradjanibrzogbroda.backend.domain.*;
import gradjanibrzogbroda.backend.dto.JeloPorudzbineDTO;
import gradjanibrzogbroda.backend.dto.PicePorudzbineDTO;
import gradjanibrzogbroda.backend.dto.PorudzbinaDTO;
import gradjanibrzogbroda.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PorudzbinaService {
    @Autowired
    private PorudzbinaRepository porudzbinaRepository;
    @Autowired
    private StoRepository stoRepository;
    @Autowired
    private PicePorudzbineRepository picePorudzbineRepository;
    @Autowired
    private JeloPorudzbineRepository jeloPorudzbineRepository;
    @Autowired
    private JeloRepository jeloRepository;
    @Autowired
    private PiceRepository piceRepository;
    @Autowired
    private KonobarRepository konobarRepository;

    public List <Porudzbina> findAll(){
        return porudzbinaRepository.findAll();
    }

    public Porudzbina findOne(Integer id){
        return porudzbinaRepository.findOneById(id);
    }

    public List<Porudzbina> findAllByStatusPorudzbine(StatusPorudzbine statusPorudzbine){
        return porudzbinaRepository.findAllByStatusPorudzbine(statusPorudzbine);
    }

    public List<Porudzbina> findAllByKonobarId(Integer konobarId){
        return porudzbinaRepository.findAllByKonobarId(konobarId);
    }

    public Porudzbina napraviPorudzbinu(PorudzbinaDTO dto){
        Porudzbina porudzbina = Porudzbina.builder()
                .datumVreme(LocalDateTime.now())
                .napomena(dto.getNapomena())
                .statusPorudzbine(StatusPorudzbine.KREIRANO)
                .jelaPorudzbine(new ArrayList<JeloPorudzbine>())
                .picePorudzbine(new ArrayList<PicePorudzbine>())
                .ukupnaCena(0.0)
                .sto(stoRepository.findOneById(dto.getStoId()))
                .konobar(konobarRepository.findOneById(dto.getKonobarId()))
                .obrisan(false)
                .build();

        Porudzbina kreirana = porudzbinaRepository.save(porudzbina);
        Double ukupnaCena = 0.0;

        for (JeloPorudzbineDTO j: dto.getJelaPorudzbine()) {
            Jelo jelo = jeloRepository.findOneById(j.getJeloId());
            JeloPorudzbine jp = JeloPorudzbine.builder()
                    .kolicina(j.getKolicina())
                    .napomena(j.getNapomena())
                    .statusJela(StatusJela.KREIRANO)
                    .jelo(jelo)
                    .porudzbina(kreirana)
                    .build();
            kreirana.getJelaPorudzbine().add(jp);
            ukupnaCena += jelo.getTrenutnaCena()*j.getKolicina();

        }

        for (PicePorudzbineDTO p: dto.getPicePorudzbine()) {
            Pice pice =piceRepository.findOneById(p.getPiceId());
            PicePorudzbine pp = PicePorudzbine.builder()
                    .kolicina(p.getKolicina())
                    .napomena(p.getNapomena())
                    .statusPica(StatusPica.KREIRANO)
                    .pice(pice)
                    .porudzbina(kreirana)
                    .build();
            kreirana.getPicePorudzbine().add(pp);
            ukupnaCena += pice.getTrenutnaCena()*p.getKolicina();
        }
        porudzbina.setUkupnaCena(ukupnaCena);
        return porudzbinaRepository.save(porudzbina);
    }

    public Porudzbina izmeniPorudzbinu(PorudzbinaDTO dto){
        Porudzbina porudzbina = porudzbinaRepository.findOneById(dto.getId());
        if (porudzbina.getStatusPorudzbine().equals(StatusPorudzbine.NAPLACENO)){
            return null;
        }
        porudzbina.setKonobar(konobarRepository.findOneById(dto.getKonobarId()));
        porudzbina.setNapomena(dto.getNapomena());
        porudzbina.setSto(stoRepository.findOneById(dto.getStoId()));

        return porudzbinaRepository.save(porudzbina);
    }

    public void obrisiPorudzbinuPoId(Integer id){
        porudzbinaRepository.deleteById(id);
    }

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
