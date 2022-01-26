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
	private Double pozicijaX;
	private Double pozicijaY;
	private Integer brojMesta;
	private String nazivStola;
	private String identificationNumber;

	public StoDTO(Sto astal){
		zauzet = astal.getZauzet();
		pozicijaX = astal.getPozicijaX();
		pozicijaY = astal.getPozicijaY();
		brojMesta = astal.getBrojMesta();
		nazivStola = astal.getNazivStola();
		identificationNumber = astal.getIdentificationNumber();
	}
}
