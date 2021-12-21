package gradjanibrzogbroda.backend.domain;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import gradjanibrzogbroda.backend.dto.PlataDTO;
import gradjanibrzogbroda.backend.dto.ZaposleniDTO;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "zaposleni")
@Inheritance(strategy=InheritanceType.JOINED)
@SQLDelete(sql
        = "UPDATE zaposleni "
        + "SET obrisan = true "
        + "WHERE id = ?")
@Where(clause = "obrisan = false")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Zaposleni {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ime", nullable = false)
    private String ime;
    @Column(name = "prezime", nullable = false)
    private String prezime;
    @Column(name = "pol", nullable = false)
    private Pol pol;
    @Column(name = "datum_rodjenja", nullable = false)
    private LocalDate datumRodjenja;
    @Column(name = "trenutna_plata", nullable = false)
    private Double trenutnaPlata;
    @Column(name = "zaposlenje", nullable = false)
    private TipZaposlenja tipZaposlenja;
    @Column(name = "putanja_slike", nullable = false)
    private String putanjaSlike;
    @Column(name = "identification_number", nullable = false, unique = true)
    private String identificationNumber;

    @OneToMany(mappedBy = "zaposleni", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Plata> plate=new ArrayList<Plata>();


    @Column(name = "obrisan", nullable = false)
    private boolean obrisan;

    public Zaposleni(ZaposleniDTO zDto){
        updateFields(zDto);
    }

    public void updateFields(ZaposleniDTO zDto){
        ime = zDto.getIme();
        prezime = zDto.getPrezime();
        pol = zDto.getPol();
        datumRodjenja = zDto.getDatumRodjenja();
        trenutnaPlata = zDto.getTrenutnaPlata();
        tipZaposlenja = zDto.getTipZaposlenja();
        putanjaSlike = sacuvajSliku(zDto.getSlikaString());
        identificationNumber = zDto.getIdentificationNumber();
    }

    public String sacuvajSliku(String slika){
        // TODO: logika cuvanja slike iz string-a u filesistem i cuvanje u path-u
        return "";
    }
}
