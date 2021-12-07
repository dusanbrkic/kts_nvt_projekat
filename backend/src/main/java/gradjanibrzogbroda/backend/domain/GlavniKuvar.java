package gradjanibrzogbroda.backend.domain;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import gradjanibrzogbroda.backend.dto.ZaposleniDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="glavni_kuvari")
@SQLDelete(sql
        = "UPDATE zaposleni "
        + "SET obrisan = true "
        + "WHERE id = ?")
@Where(clause = "obrisan = false")
@Getter
@Setter
//@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GlavniKuvar extends Kuvar{
    @Column(name = "korisnicko_ime", nullable = false)
    private String korisnicko_ime;
    @Column(name = "lozinka", nullable = false)
    private String lozinka;

    public GlavniKuvar(ZaposleniDTO zaposleniDTO) {
        super(zaposleniDTO);
    }
}
