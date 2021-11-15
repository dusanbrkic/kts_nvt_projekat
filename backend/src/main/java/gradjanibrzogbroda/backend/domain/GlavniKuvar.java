package gradjanibrzogbroda.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="glavni_kuvari")
@SQLDelete(sql
        = "UPDATE glavni_kuvari "
        + "SET obrisan = true "
        + "WHERE id = ?")
@Where(clause = "obrisan = false")
@Getter
//@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GlavniKuvar extends Kuvar{
    @Column(name = "korisnicko_ime", nullable = false)
    private String korisnicko_ime;
    @Column(name = "lozinka", nullable = false)
    private String lozinka;
}
