package gradjanibrzogbroda.backend.domain;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="porudzbine")
@SQLDelete(sql
        = "UPDATE porudzbine "
        + "SET obrisan = true "
        + "WHERE id = ?")
@Where(clause = "obrisan = false")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Porudzbina {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "status_porudzbine")
    private StatusPorudzbine statusPorudzbine;

    @Column(name = "datum_vreme")
    private LocalDateTime datumVreme;

    @Column(name = "napomena")
    private String napomena;

    @Column(name = "ukupna_cena")
    private Double ukupnaCena;


    @ManyToOne(fetch = FetchType.EAGER)
    private Sto sto;

    @OneToMany(mappedBy = "porudzbina", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<JeloPorudzbine> jelaPorudzbine=new ArrayList<JeloPorudzbine>();

    @OneToMany(mappedBy = "porudzbina", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PicePorudzbine> picePorudzbine=new ArrayList<PicePorudzbine>();

    @Column(name = "obrisan", nullable = false)
    private boolean obrisan;
}
