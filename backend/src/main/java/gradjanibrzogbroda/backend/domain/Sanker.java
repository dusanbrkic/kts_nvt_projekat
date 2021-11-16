package gradjanibrzogbroda.backend.domain;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import gradjanibrzogbroda.backend.dto.ZaposleniDTO;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="sankeri")
@SQLDelete(sql
        = "UPDATE sankeri "
        + "SET obrisan = true "
        + "WHERE id = ?")
@Where(clause = "obrisan = false")
@Getter
@Setter
@Builder
@AllArgsConstructor
//@NoArgsConstructor
public class Sanker extends Zaposleni{
    public Sanker(ZaposleniDTO zaposleniDTO) {
        super(zaposleniDTO);
    }
}
