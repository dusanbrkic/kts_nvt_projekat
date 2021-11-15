package gradjanibrzogbroda.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

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
@Builder
@AllArgsConstructor
//@NoArgsConstructor
public class Sanker extends Zaposleni{
}
