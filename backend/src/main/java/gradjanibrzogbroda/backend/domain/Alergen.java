package gradjanibrzogbroda.backend.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="alergeni")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Alergen {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "naziv", nullable = false)
    private String naziv;
}
