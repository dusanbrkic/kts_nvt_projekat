package gradjanibrzogbroda.backend.dto;

import gradjanibrzogbroda.backend.config.StorageProperties;
import gradjanibrzogbroda.backend.domain.Pice;
import gradjanibrzogbroda.backend.util.StorageUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PiceDTO {
	
	private Integer id;
	private String naziv;
	private Double trenutnaCena;
	private String picBase64;
	
	public PiceDTO(Pice p) {
		this.id=p.getId();
		this.naziv=p.getNaziv();
		this.trenutnaCena=p.getTrenutnaCena();
		this.picBase64= StorageUtil.loadAsString(StorageProperties.PICA_LOCATION, p.getPicName());

	}

}
