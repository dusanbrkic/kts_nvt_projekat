package gradjanibrzogbroda.backend.dto;

import gradjanibrzogbroda.backend.domain.JeloPorudzbine;
import gradjanibrzogbroda.backend.domain.PicePorudzbine;
import gradjanibrzogbroda.backend.domain.StatusPica;
import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PicePorudzbineDTO {

    private Integer id;
    private Double kolicina;
    private String napomena;
    private StatusPica statusPica;
    private PiceDTO pice;
    private Integer porudzbinaId;

    public PicePorudzbineDTO(PicePorudzbine pp){
        this.id = pp.getId();
        this.kolicina = pp.getKolicina();
        this.napomena = pp.getNapomena();
        this.statusPica = pp.getStatusPica();
        this.pice = new PiceDTO(pp.getPice());
        this.porudzbinaId = pp.getPorudzbina().getId();
    }
    
    public int getPiceId() {
    	return this.getPice().getId();
    }

}
