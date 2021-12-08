package gradjanibrzogbroda.backend.domain;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name="jela_porudzbine")
@SQLDelete(sql
        = "UPDATE jela_porudzbine "
        + "SET obrisan = true "
        + "WHERE id = ?")
@Where(clause = "obrisan = false")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JeloPorudzbine {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "kolicina")
    private Double kolicina;

    @Column(name = "napomena")
    private String napomena;

    @Column(name = "status_jela")
    private StatusJela statusJela;

    @ManyToOne(fetch = FetchType.EAGER)
    private Jelo jelo;

    @ManyToOne(fetch = FetchType.EAGER)
    private Porudzbina porudzbina;

    @Column(name = "obrisan", nullable = false)
    private boolean obrisan;
}
