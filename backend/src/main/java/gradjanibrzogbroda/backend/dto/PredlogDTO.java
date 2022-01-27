package gradjanibrzogbroda.backend.dto;

import gradjanibrzogbroda.backend.domain.Predlog;
import gradjanibrzogbroda.backend.domain.PredlogStatus;
import gradjanibrzogbroda.backend.domain.PredlogTip;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PredlogDTO {

	Integer id,staroJeloId;
	JeloDTO novoJelo;
	PredlogTip tipIzmene;
	PredlogStatus status;
	
	public PredlogDTO(Predlog p) {
		this.id=p.getId();
		if(p.getStaroJelo()!=null) {
			this.staroJeloId=p.getStaroJelo().getId();
		}else {
			this.staroJeloId=null;
		}
		if(p.getNovoJelo()!=null) {
			this.novoJelo=new JeloDTO(p.getNovoJelo());
		}else {
			this.novoJelo=null;
		}
		this.status=p.getStatus();
		this.tipIzmene=p.getTipIzmene();
	}

	@Override
	public String toString() {
		return "PredlogDTO [id=" + id + ", staroJeloId=" + staroJeloId + ", novoJelo=" + novoJelo.toString() + ", tipIzmene="
				+ tipIzmene + ", status=" + status + "]";
	}
}
