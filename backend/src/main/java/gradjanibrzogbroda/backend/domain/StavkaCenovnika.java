package gradjanibrzogbroda.backend.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private LocalDateTime pocetakVazenja;

    @Column(name = "kraj_vazenja")
    private LocalDateTime krajVazenja;

    @Column(name = "cena")
    private Double cena;

    @ManyToOne(fetch = FetchType.EAGER)
    private Artikal artikal;
}
