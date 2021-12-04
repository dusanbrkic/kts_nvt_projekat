package gradjanibrzogbroda.backend.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="plate")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Plata {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "pocetak_vazenja")
    private LocalDate pocetakVazenja;

    @Column(name = "kraj_vazenja")
    private LocalDate krajVazenja;

    @Column(name = "visina_plate")
    private Double visinaPlate;

    @ManyToOne(fetch = FetchType.EAGER)
    private Zaposleni zaposleni;

}
