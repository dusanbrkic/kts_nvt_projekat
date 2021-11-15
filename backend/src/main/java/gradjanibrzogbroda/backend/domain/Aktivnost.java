package gradjanibrzogbroda.backend.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="aktivnosti")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Aktivnost {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "vreme_aktivnosti")
    private LocalDate vremeAktivnosti;

    @Column(name = "tip_aktivnosti")
    private TipAktivnosti tipAktivnosti;

    @ManyToOne(fetch = FetchType.EAGER)
    private Zaposleni zaposleni;
}
