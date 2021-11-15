package gradjanibrzogbroda.backend.domain;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name="admini")
@SQLDelete(sql
        = "UPDATE admini "
        + "SET obrisan = true "
        + "WHERE id = ?")
@Where(clause = "obrisan = false")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "korisnicko_ime", nullable = false)
    private String korisnicko_ime;

    @Column(name = "lozinka", nullable = false)
    private String lozinka;

    @Column(name = "obrisan", nullable = false)
    private boolean obrisan;
}
