package gradjanibrzogbroda.backend.domain;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import gradjanibrzogbroda.backend.dto.PredlogDTO;
import gradjanibrzogbroda.backend.util.JeloConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@Entity
@Table(name = "predlozi")
@Inheritance(strategy=InheritanceType.JOINED)
@SQLDelete(sql
        = "UPDATE predlozi "
        + "SET obrisan = true "
        + "WHERE id = ?")
@Where(clause = "obrisan = false")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
public class Predlog {
	
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
	
	@Column(name = "tipIzmene", nullable = false)
    private PredlogTip tipIzmene;
	
	@Column(name = "status", nullable = false)
    private PredlogStatus status;
	
	@Convert(converter = JeloConverter.class)
    private Jelo novoJelo;
	
	@ManyToOne(fetch = FetchType.EAGER)
    private Jelo staroJelo;
	
	@Column(name = "obrisan", nullable = false)
    private boolean obrisan;
	
	public Predlog(PredlogDTO p,Jelo novoJelo,Jelo staroJelo) {
		this.id=p.getId();
		this.status=p.getStatus();
		this.tipIzmene=p.getTipIzmene();
		this.novoJelo=novoJelo;
		this.staroJelo=staroJelo;
		this.obrisan=false;
		
	}

}
