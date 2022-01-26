package gradjanibrzogbroda.backend.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="zone")
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

	@Column(name = "identification_number")
	private String identificationNumber;

	@Column(name = "naziv")
	private String naziv;

	@Column(name = "template_path")
	private String templatePath;

	@OneToMany(fetch = FetchType.LAZY)
	private Set<Sto> stolovi;

}
