package gradjanibrzogbroda.backend.domain;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import gradjanibrzogbroda.backend.dto.ZaposleniDTO;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="konobari")
@SQLDelete(sql
        = "UPDATE konobari "
        + "SET obrisan = true "
        + "WHERE id = ?")
@Where(clause = "obrisan = false")
@Getter
@Setter
@Builder
@AllArgsConstructor
//@NoArgsConstructor
public class Konobar extends Zaposleni{
        public Konobar(ZaposleniDTO zaposleniDTO){
                super(zaposleniDTO);
        }
}
