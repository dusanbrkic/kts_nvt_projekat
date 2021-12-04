package gradjanibrzogbroda.backend.dto;

import java.time.LocalDate;

import gradjanibrzogbroda.backend.domain.Plata;
import gradjanibrzogbroda.backend.domain.Zaposleni;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlataDTO {

    private Integer id;
    private Double visinaPlate;
    private LocalDate pocetakVazenja;
    private LocalDate krajVazenja;
    private Integer zaposleniId;

    public PlataDTO(Plata p){
        id = p.getId();
        visinaPlate = p.getVisinaPlate();
        pocetakVazenja = p.getPocetakVazenja();
        krajVazenja = p.getKrajVazenja();
        zaposleniId = p.getZaposleni().getId();
    }
}
