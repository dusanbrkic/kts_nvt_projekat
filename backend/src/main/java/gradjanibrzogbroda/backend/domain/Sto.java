package gradjanibrzogbroda.backend.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="stolovi")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Sto {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "zauzet")
    private Boolean zauzet;

    @Column(name = "pozicija_x")
    private Double pozicijaX;

    @Column(name = "pozicija_y")
    private Double pozicijaY;

    @Column(name = "broj_mesta")
    private Integer brojMesta;

    @Column(name = "naziv_stola")
    private String nazivStola;

    @Column(name = "identification_number")
    private String identificationNumber;


    @OneToOne(fetch = FetchType.LAZY)
    private Porudzbina porudzbina;

    @ManyToOne(fetch = FetchType.LAZY)
    private Konobar konobar;



}
