package gradjanibrzogbroda.backend.domain;

import gradjanibrzogbroda.backend.dto.PiceDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="pice")
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Pice extends Artikal{
	public Pice(int id) {
    	this.setId(id);
    }
	public Pice(PiceDTO piceDTO){
		this.setNaziv(piceDTO.getNaziv());
		this.setTrenutnaCena(piceDTO.getTrenutnaCena());
		this.setPicName(generatePicName());
		this.setId(piceDTO.getId());
	}

	public String generatePicName(){
		return this.getNaziv() + ".jpg";
	}
}
