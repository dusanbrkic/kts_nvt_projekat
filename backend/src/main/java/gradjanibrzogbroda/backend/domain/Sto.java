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
    private boolean zauzet;
}
