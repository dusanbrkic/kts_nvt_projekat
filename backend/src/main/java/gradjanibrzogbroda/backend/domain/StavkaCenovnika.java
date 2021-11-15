package gradjanibrzogbroda.backend.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="cenovnik")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StavkaCenovnika {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "pocetak_vazenja")
    private LocalDate pocetakVazenja;

    @Column(name = "kraj_vazenja")
    private LocalDate krajVazenja;

    @ManyToOne(fetch = FetchType.EAGER)
    private Artikal artikal;
}
