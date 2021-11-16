package gradjanibrzogbroda.backend.dto;

import gradjanibrzogbroda.backend.domain.Pice;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PiceDTO {
	
	private Integer id;
	private String naziv;
	private Double trenutnaCena;
	
	public PiceDTO(Pice p) {
		this.id=p.getId();
		this.naziv=p.getNaziv();
		this.trenutnaCena=p.getTrenutnaCena();
	}

}
