package gradjanibrzogbroda.backend.domain;

import gradjanibrzogbroda.backend.dto.StoDTO;
import gradjanibrzogbroda.backend.dto.ZoneDTO;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;
import java.util.function.Function;

@Entity
@Table(name="zone")
@SQLDelete(sql
		= "UPDATE zone "
		+ "SET obrisan = true "
		+ "WHERE id = ?")
@Where(clause = "obrisan = false")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Zone {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "obrisan", nullable = false)
	private boolean obrisan;

	@Column(name = "identification_number")
	private String identificationNumber;

	@Column(name = "naziv")
	private String naziv;

	@Column(name = "template_path")
	private String templatePath;

	@OneToMany(fetch = FetchType.LAZY)
	private Set<Sto> stolovi;

	public void updateFields(ZoneDTO zoneDTO) {
		identificationNumber = zoneDTO.getId();
		naziv = zoneDTO.getNaziv();
		templatePath = generateTemplatePicName();
	}

	public String generateTemplatePicName(){
		return this.identificationNumber + ".jpg";
	}
}
