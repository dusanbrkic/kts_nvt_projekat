package gradjanibrzogbroda.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="plate")
@Getter
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

    @ManyToOne(fetch = FetchType.EAGER)
    private Zaposleni zaposleni;

}
