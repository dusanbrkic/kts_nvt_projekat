package gradjanibrzogbroda.backend.domain;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="menadzeri")
@SQLDelete(sql
        = "UPDATE menadzeri "
        + "SET obrisan = true "
        + "WHERE id = ?")
@Where(clause = "obrisan = false")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Menadzer extends Zaposleni{
    @Column(name = "korisnicko_ime", nullable = false)
    private String korisnicko_ime;
    @Column(name = "lozinka", nullable = false)
    private String lozinka;
}
