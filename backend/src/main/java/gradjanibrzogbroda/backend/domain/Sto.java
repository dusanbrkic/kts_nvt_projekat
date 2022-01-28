package gradjanibrzogbroda.backend.domain;

import gradjanibrzogbroda.backend.dto.StoDTO;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name="stolovi")
@SQLDelete(sql
        = "UPDATE stolovi "
        + "SET obrisan = true "
        + "WHERE id = ?")
@Where(clause = "obrisan = false")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Sto {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "zauzet")
    private Boolean zauzet;

    @Column(name = "pozicija_x")
    private Double pozicijaX;

    @Column(name = "pozicija_y")
    private Double pozicijaY;

    @Column(name = "broj_mesta")
    private Integer brojMesta;

    @Column(name = "naziv_stola")
    private String nazivStola;

    @Column(name = "identification_number")
    private String identificationNumber;

    @Column(name = "obrisan", nullable = false)
    private boolean obrisan;


    @OneToOne(fetch = FetchType.LAZY)
    private Porudzbina porudzbina;

    @ManyToOne(fetch = FetchType.LAZY)
    private Konobar konobar;

    @ManyToOne(fetch = FetchType.LAZY)
    private Zone zone;


    public Sto updateFields(StoDTO stoDTO) {
        zauzet = stoDTO.getZauzet();
        pozicijaX = stoDTO.getX();
        pozicijaY = stoDTO.getY();
        brojMesta = stoDTO.getBrojMesta();
        nazivStola = stoDTO.getNaziv();
        identificationNumber = stoDTO.getId();
        return this;
	}
}
