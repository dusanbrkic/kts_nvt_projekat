package gradjanibrzogbroda.backend.dto;

import gradjanibrzogbroda.backend.domain.*;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
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
    private LocalDate datumVreme;
    private String napomena;
    private Integer konobarId;
    private Integer stoId;
    private List<JeloPorudzbineDTO> jelaPorudzbine=new ArrayList<JeloPorudzbineDTO>();
    private List<PicePorudzbineDTO> picePorudzbine=new ArrayList<PicePorudzbineDTO>();

    public PorudzbinaDTO(Porudzbina p){
        this.id = p.getId();
        this.statusPorudzbine = p.getStatusPorudzbine();
        this.datumVreme = p.getDatumVreme();
        this.napomena = p.getNapomena();
        this.konobarId = p.getKonobar().getId();
        this.stoId = p.getSto().getId();

        this.jelaPorudzbine = new ArrayList<>();
        for (JeloPorudzbine jp: p.getJelaPorudzbine()) {
            this.jelaPorudzbine.add(new JeloPorudzbineDTO(jp));
        }

        this.picePorudzbine = new ArrayList<>();
        for (PicePorudzbine pp: p.getPicePorudzbine()) {
            this.picePorudzbine.add(new PicePorudzbineDTO(pp));
        }
    }
}
