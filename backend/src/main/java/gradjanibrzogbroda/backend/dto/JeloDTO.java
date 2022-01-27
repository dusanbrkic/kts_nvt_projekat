package gradjanibrzogbroda.backend.dto;

import java.util.Set;

import gradjanibrzogbroda.backend.domain.Alergen;
import gradjanibrzogbroda.backend.domain.Jelo;
import gradjanibrzogbroda.backend.domain.KategorijaJela;
import gradjanibrzogbroda.backend.domain.TipJela;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JeloDTO {
	
	private Integer id;
	private String naziv;
	private Double trenutnaCena;
	private Long vremePripremeMils;
	private String opis;
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
	}

	@Override
	public String toString() {
		return "JeloDTO [id=" + id + ", naziv=" + naziv + ", trenutnaCena=" + trenutnaCena + ", vremePripremeMils="
				+ vremePripremeMils + ", opis=" + opis + ", kategorijaJela=" + kategorijaJela + ", tipJela=" + tipJela
				+ "]";
	}
	
}
