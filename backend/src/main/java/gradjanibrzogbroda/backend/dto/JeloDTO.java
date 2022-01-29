package gradjanibrzogbroda.backend.dto;

import java.util.Set;

import gradjanibrzogbroda.backend.config.StorageProperties;
import gradjanibrzogbroda.backend.domain.Jelo;
import gradjanibrzogbroda.backend.domain.KategorijaJela;
import gradjanibrzogbroda.backend.domain.TipJela;
import gradjanibrzogbroda.backend.util.StorageUtil;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JeloDTO {
	
	private Integer id;
	private String naziv;
	private Double trenutnaCena;
	private Long vremePripremeMils;
	private String opis;
	private String picBase64;
	private KategorijaJela kategorijaJela;
	private TipJela tipJela;
	
	public JeloDTO(Jelo j) {
		this.id=j.getId();
		this.naziv=j.getNaziv();
		this.trenutnaCena=j.getTrenutnaCena();
		this.vremePripremeMils=j.getVremePripremeMils();
		this.opis=j.getOpis();
		this.kategorijaJela=j.getKategorijaJela();
		this.tipJela=j.getTipJela();
		this.picBase64= StorageUtil.loadAsString(StorageProperties.JELA_LOCATION, j.getPicName());
	}

	@Override
	public String toString() {
		return "JeloDTO [id=" + id + ", naziv=" + naziv + ", trenutnaCena=" + trenutnaCena + ", vremePripremeMils="
				+ vremePripremeMils + ", opis=" + opis + ", kategorijaJela=" + kategorijaJela + ", tipJela=" + tipJela
				+ "]";
	}
	
}
