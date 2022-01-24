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
    
    public List <Porudzbina> findAllZaSankera(){
    	ArrayList<Porudzbina> porudzbine = new ArrayList<Porudzbina>();
    	for(Porudzbina p : porudzbinaRepository.findAllZaSankera()) {
    		if(p.getPicePorudzbine().size()>0) {
    			porudzbine.add(p);
    		}
    	}
    	
        return porudzbine;
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

    public boolean naplatiPorudzbinu(Integer id){
        Porudzbina porudzbina = porudzbinaRepository.findOneById(id);
        if (porudzbina.getStatusPorudzbine().equals(StatusPorudzbine.DOSTAVLJENO)){
            porudzbina.setStatusPorudzbine(StatusPorudzbine.NAPLACENO);
            porudzbinaRepository.save(porudzbina);
            return true;
        }
        return false;
    }

}
