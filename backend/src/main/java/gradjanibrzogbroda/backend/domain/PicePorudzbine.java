package gradjanibrzogbroda.backend.domain;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name="pice_porudzbine")
@SQLDelete(sql
        = "UPDATE pice_porudzbine "
        + "SET obrisan = true "
        + "WHERE id = ?")
@Where(clause = "obrisan = false")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PicePorudzbine {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "kolicina")
    private Double kolicina;

    @Column(name = "napomena")
    private String napomena;

    @Column(name = "status_pica")
    private StatusPica statusPica;

    @ManyToOne(fetch = FetchType.EAGER)
    private Pice pice;

    @ManyToOne(fetch = FetchType.EAGER)
    private Porudzbina porudzbina;

    @Column(name = "obrisan", nullable = false)
    private boolean obrisan;
}
