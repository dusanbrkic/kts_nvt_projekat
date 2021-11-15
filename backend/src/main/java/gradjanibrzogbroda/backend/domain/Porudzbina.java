package gradjanibrzogbroda.backend.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="porudzbine")
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
    private LocalDate datumVreme;

    @Column(name = "napomena")
    private String napomena;

    @ManyToOne(fetch = FetchType.EAGER)
    private Konobar konobar;

    @ManyToOne(fetch = FetchType.EAGER)
    private Sto sto;

    @OneToMany(mappedBy = "porudzbina", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<JeloPorudzbine> jelaPorudzbine=new ArrayList<JeloPorudzbine>();

    @OneToMany(mappedBy = "porudzbina", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PicePorudzbine> picePorudzbine=new ArrayList<PicePorudzbine>();
}
