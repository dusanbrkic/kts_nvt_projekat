package gradjanibrzogbroda.backend.dto;

import gradjanibrzogbroda.backend.domain.Konobar;
import gradjanibrzogbroda.backend.domain.Porudzbina;
import gradjanibrzogbroda.backend.domain.Sto;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoDTO {
	private Boolean zauzet;
	private Double x;
	private Double y;
	private Integer brojMesta;
	private String naziv;
	private String id;

	private Integer porudzbinaId;

	public StoDTO(Sto astal){
		zauzet = astal.getZauzet();
		x = astal.getPozicijaX();
		y = astal.getPozicijaY();
		brojMesta = astal.getBrojMesta();
		naziv = astal.getNazivStola();
		id = astal.getIdentificationNumber();
		if (astal.getPorudzbina()!=null) {
			porudzbinaId = astal.getPorudzbina().getId();
		}
	}
}
