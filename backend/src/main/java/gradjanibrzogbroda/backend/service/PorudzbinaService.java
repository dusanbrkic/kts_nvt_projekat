package gradjanibrzogbroda.backend.service;

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
                .datumVreme(dto.getDatumVreme())
                .napomena(dto.getNapomena())
                .statusPorudzbine(StatusPorudzbine.KREIRANO)
                .jelaPorudzbine(new ArrayList<JeloPorudzbine>())
                .picePorudzbine(new ArrayList<PicePorudzbine>())
                .sto(stoRepository.findOneById(dto.getStoId()))
                .konobar(konobarRepository.findOneById(dto.getKonobarId()))
                .obrisan(false)
                .build();

        Porudzbina kreirana = porudzbinaRepository.save(porudzbina);

        for (JeloPorudzbineDTO j: dto.getJelaPorudzbine()) {
            JeloPorudzbine jp = JeloPorudzbine.builder()
                    .kolicina(j.getKolicina())
                    .napomena(j.getNapomena())
                    .statusJela(StatusJela.KREIRANO)
                    .jelo(jeloRepository.findOneById(j.getJeloId()))
                    .porudzbina(kreirana)
                    .build();
            porudzbina.getJelaPorudzbine().add(jp);
        }

        for (PicePorudzbineDTO p: dto.getPicePorudzbine()) {
            PicePorudzbine pp = PicePorudzbine.builder()
                    .kolicina(p.getKolicina())
                    .napomena(p.getNapomena())
                    .statusPica(StatusPica.KREIRANO)
                    .pice(piceRepository.findOneById(p.getPiceId()))
                    .porudzbina(kreirana)
                    .build();
            porudzbina.getPicePorudzbine().add(pp);
        }

        return porudzbinaRepository.save(porudzbina);
    }

    public Porudzbina izmeniPorudzbinu(PorudzbinaDTO dto){
        Porudzbina porudzbina = porudzbinaRepository.findOneById(dto.getId());
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
        JeloPorudzbine jelo = JeloPorudzbine.builder()
                .kolicina(dto.getKolicina())
                .napomena(dto.getNapomena())
                .statusJela(StatusJela.KREIRANO)
                .jelo(jeloRepository.findOneById(dto.getJeloId()))
                .porudzbina(porudzbina)
                .build();
        return jeloPorudzbineRepository.save(jelo);
    }

    public PicePorudzbine dodajPicePorudzbine(PicePorudzbineDTO dto){
        Porudzbina porudzbina = porudzbinaRepository.findOneById(dto.getPorudzbinaId());
        PicePorudzbine pice = PicePorudzbine.builder()
                .kolicina(dto.getKolicina())
                .napomena(dto.getNapomena())
                .statusPica(StatusPica.KREIRANO)
                .pice(piceRepository.findOneById(dto.getPiceId()))
                .porudzbina(porudzbina)
                .build();
        return picePorudzbineRepository.save(pice);
    }

    public JeloPorudzbine izmeniJeloPorudzbine(JeloPorudzbineDTO dto){
        JeloPorudzbine jelo = jeloPorudzbineRepository.findOneById(dto.getId());
        jelo.setKolicina(dto.getKolicina());
        jelo.setNapomena(dto.getNapomena());
        return jeloPorudzbineRepository.save(jelo);
    }

    public PicePorudzbine izmeniPicePorudzbine(PicePorudzbineDTO dto){
        PicePorudzbine pice = picePorudzbineRepository.findOneById(dto.getId());
        pice.setKolicina(dto.getKolicina());
        pice.setNapomena(dto.getNapomena());
        return picePorudzbineRepository.save(pice);
    }

    public void obrisiJeloPorudzbine(Integer id){
        jeloPorudzbineRepository.deleteById(id);
    }

    public void obrisiPicePorudzbine(Integer id){
        picePorudzbineRepository.deleteById(id);
    }
}
