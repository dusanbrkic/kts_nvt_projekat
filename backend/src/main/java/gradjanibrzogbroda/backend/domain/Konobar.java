package gradjanibrzogbroda.backend.domain;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import gradjanibrzogbroda.backend.dto.ZaposleniDTO;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="konobari")
@SQLDelete(sql
		= "UPDATE zaposleni "
		+ "SET obrisan = true "
		+ "WHERE id = ?")
@Where(clause = "obrisan = false")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Konobar extends Zaposleni{
//	public Konobar(ZaposleniDTO zaposleniDTO){
//		super(zaposleniDTO);
//	}

	@OneToMany(fetch = FetchType.LAZY)
	private Set<Porudzbina> porudzbine;
}
