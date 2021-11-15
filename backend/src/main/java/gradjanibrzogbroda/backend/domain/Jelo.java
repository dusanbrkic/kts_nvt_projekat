package gradjanibrzogbroda.backend.domain;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="jela")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Jelo extends Artikal{
    @Column(name = "vreme_pripreme_mils")
    private Long vremePripremeMils;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "jela_alergeni", joinColumns = @JoinColumn(name = "jelo_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "alergen_id", referencedColumnName = "id"))
    private Set<Alergen> alergeni=new HashSet<Alergen>();

    @Column(name = "opis")
    private String opis;

    @Column(name = "kategorija_jela")
    private KategorijaJela kategorijaJela;

    @Column(name = "tip_jela")
    private TipJela tipJela;


}
