package gradjanibrzogbroda.backend.dto;

import gradjanibrzogbroda.backend.domain.*;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PorudzbinaDTO {
    private Integer id;
    private StatusPorudzbine statusPorudzbine;
    private LocalDateTime datumVreme;
    private String napomena;
    private Double ukupnaCena;
    private String stoId;
    private List<JeloPorudzbineDTO> jelaPorudzbine=new ArrayList<JeloPorudzbineDTO>();
    private List<PicePorudzbineDTO> picaPorudzbine=new ArrayList<PicePorudzbineDTO>();

    public PorudzbinaDTO(Porudzbina p){
        this.id = p.getId();
        this.statusPorudzbine = p.getStatusPorudzbine();
        this.datumVreme = p.getDatumVreme();
        this.napomena = p.getNapomena();
        this.ukupnaCena = p.getUkupnaCena();
        this.stoId = p.getSto().getIdentificationNumber();

        this.jelaPorudzbine = new ArrayList<>();
        for (JeloPorudzbine jp: p.getJelaPorudzbine()) {
            this.jelaPorudzbine.add(new JeloPorudzbineDTO(jp));
        }

        this.picaPorudzbine = new ArrayList<>();
        for (PicePorudzbine pp: p.getPicePorudzbine()) {
            this.picaPorudzbine.add(new PicePorudzbineDTO(pp));
        }
    }
}
