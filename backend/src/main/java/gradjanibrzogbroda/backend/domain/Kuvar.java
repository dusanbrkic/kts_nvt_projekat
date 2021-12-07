package gradjanibrzogbroda.backend.domain;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import gradjanibrzogbroda.backend.dto.ZaposleniDTO;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="kuvari")
@Inheritance(strategy= InheritanceType.JOINED)
@SQLDelete(sql
        = "UPDATE zaposleni "
        + "SET obrisan = true "
        + "WHERE id = ?")
@Where(clause = "obrisan = false")
@Getter
@Setter
@Builder
@AllArgsConstructor
//@NoArgsConstructor
public class Kuvar extends Zaposleni{
        
    public Kuvar(ZaposleniDTO zaposleniDTO) {
        super(zaposleniDTO);
    }
}
