package gradjanibrzogbroda.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artikli")
@Inheritance(strategy= InheritanceType.JOINED)
@SQLDelete(sql
        = "UPDATE artikli "
        + "SET obrisan = true "
        + "WHERE id = ?")
@Where(clause = "obrisan = false")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Artikal {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "naziv", nullable = false)
    private String naziv;
    @Column(name = "trenutna_cena", nullable = false)
    private Double trenutnaCena;
    @Column(name = "pic_name")
    private String picName;

    @OneToMany(mappedBy = "artikal", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<StavkaCenovnika> ceneArtikla=new ArrayList<StavkaCenovnika>();


    @Column(name = "obrisan", nullable = false)
    private boolean obrisan;
    
    public Artikal(int id) {
    	this.setId(id);
    }
}
