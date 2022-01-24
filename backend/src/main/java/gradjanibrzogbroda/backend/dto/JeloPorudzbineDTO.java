package gradjanibrzogbroda.backend.dto;

import gradjanibrzogbroda.backend.domain.Jelo;
import gradjanibrzogbroda.backend.domain.JeloPorudzbine;
import gradjanibrzogbroda.backend.domain.StatusJela;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JeloPorudzbineDTO {

    private Integer id;
    private Double kolicina;
    private String napomena;
    private StatusJela statusJela;
    private JeloDTO jelo;
    private Integer porudzbinaId;

    public JeloPorudzbineDTO(JeloPorudzbine jp){
        this.id = jp.getId();
        this.kolicina = jp.getKolicina();
        this.napomena = jp.getNapomena();
        this.statusJela = jp.getStatusJela();
        this.jelo = new JeloDTO(jp.getJelo());
        this.porudzbinaId = jp.getPorudzbina().getId();
    }
    
    public int getJeloId() {
    	return this.getJelo().getId();
    }

}
