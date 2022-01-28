package gradjanibrzogbroda.backend.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import gradjanibrzogbroda.backend.domain.*;
import gradjanibrzogbroda.backend.dto.JeloPorudzbineDTO;
import gradjanibrzogbroda.backend.dto.PicePorudzbineDTO;
import gradjanibrzogbroda.backend.dto.PorudzbinaDTO;
import gradjanibrzogbroda.backend.exceptions.PorudzbinaNotFoundException;
import gradjanibrzogbroda.backend.repository.*;
import gradjanibrzogbroda.backend.util.PorudzbinaUtil;
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

    @Autowired
    private JeloPorudzbineService jeloPorudzbineService;

    public List <Porudzbina> findAll(){
        return porudzbinaRepository.findAll();
    }
    
    public List <Porudzbina> findAllZaSankera(){
    	ArrayList<Porudzbina> porudzbine = new ArrayList<Porudzbina>();
    	for(Porudzbina p : porudzbinaRepository.findAllZaSankera()) {
    		for(PicePorudzbine pp : p.getPicePorudzbine()) {
    			if(pp.getStatusPica()==StatusPica.KREIRANO) {
    				porudzbine.add(p);
    			}
    		}
    	}
    	
        return porudzbine;
    }

    //vraca porudzbine koje kuvar treba da vidi
    public List <Porudzbina> findAllZaKuvara(){
        ArrayList<Porudzbina> porudzbine = new ArrayList<Porudzbina>();
        for(Porudzbina p : porudzbinaRepository.findAllByStatusPorudzbine(StatusPorudzbine.KREIRANO)) {
            if (!p.getJelaPorudzbine().isEmpty()){
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

    
    public void spremiPica(int porudzbinaId) throws PorudzbinaNotFoundException {
    	Porudzbina p = porudzbinaRepository.findOneById(porudzbinaId);
    	if(p==null) {
    		throw new PorudzbinaNotFoundException("Ne postoji takva porudzbina");
    	}
    	for(PicePorudzbine pp : p.getPicePorudzbine()) {
    		if(pp.getStatusPica()==StatusPica.KREIRANO) {
    			pp.setStatusPica(StatusPica.PRIPREMLJENO);
    		}
    	}
    	boolean all = true;
    	for(JeloPorudzbine jp : p.getJelaPorudzbine()) {
    		if(jp.getStatusJela()==StatusJela.KREIRANO) {
    			all = false;
    		}
    	}
    	if(all) {
    		p.setStatusPorudzbine(StatusPorudzbine.PRIPREMLJENO);
    	}
    	porudzbinaRepository.save(p);
    	
    	
    }

    public void preuzmiPorudzbinu(int porudzbinaId) throws PorudzbinaNotFoundException {
        Porudzbina p = porudzbinaRepository.findOneById(porudzbinaId);
        if(p==null) {
            throw new PorudzbinaNotFoundException("Ne postoji takva porudzbina");
        }
        for (JeloPorudzbine j: p.getJelaPorudzbine()) {
            jeloPorudzbineService.preuzmiJelo(j.getId());
        }
    }


    public Porudzbina napraviPorudzbinu(PorudzbinaDTO dto){
        Sto sto = stoRepository.findOneByIdentificationNumber(dto.getStoId());
        Porudzbina porudzbina = Porudzbina.builder()
                .datumVreme(LocalDateTime.now())
                .napomena(dto.getNapomena())
                .statusPorudzbine(StatusPorudzbine.KREIRANO)
                .jelaPorudzbine(new ArrayList<JeloPorudzbine>())
                .picePorudzbine(new ArrayList<PicePorudzbine>())
                .ukupnaCena(0.0)
                .sto(sto)
                .obrisan(false)
                .build();
        sto.setZauzet(true);
        sto.setPorudzbina(porudzbina);

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

        for (PicePorudzbineDTO p: dto.getPicaPorudzbine()) {
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
        porudzbina.setNapomena(dto.getNapomena());
        porudzbina.setSto(stoRepository.findOneByIdentificationNumber(dto.getStoId()));

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
        porudzbina.getSto().setZauzet(false);
        porudzbina.getSto().setPorudzbina(null);
        return false;
    }

}
