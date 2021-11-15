package gradjanibrzogbroda.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "zaposleni")
@Inheritance(strategy=InheritanceType.JOINED)
@SQLDelete(sql
        = "UPDATE zaposleni "
        + "SET obrisan = true "
        + "WHERE id = ?")
@Where(clause = "obrisan = false")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Zaposleni {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ime", nullable = false)
    private String ime;
    @Column(name = "prezime", nullable = false)
    private String prezime;
    @Column(name = "pol", nullable = false)
    private Pol pol;
    @Column(name = "datum_rodjenja", nullable = false)
    private LocalDate datumRodjenja;
    @Column(name = "trenutna_plata", nullable = false)
    private Double trenutnaPlata;
    @Column(name = "zaposlenje", nullable = false)
    private TipZaposlenja tipZaposlenja;
    @Column(name = "putanja_slike", nullable = false)
    private String putanjaSlike;

    @OneToMany(mappedBy = "zaposleni", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Plata> plate=new ArrayList<Plata>();


    @Column(name = "obrisan", nullable = false)
    private boolean obrisan;

}
