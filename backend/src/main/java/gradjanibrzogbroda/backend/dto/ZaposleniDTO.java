package gradjanibrzogbroda.backend.dto;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import gradjanibrzogbroda.backend.domain.Plata;
import gradjanibrzogbroda.backend.domain.Pol;
import gradjanibrzogbroda.backend.domain.TipZaposlenja;
import gradjanibrzogbroda.backend.domain.Zaposleni;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.io.Resource;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ZaposleniDTO {
    private String ime;
    private String prezime;
    private Pol pol;
    private LocalDate datumRodjenja;
    private Double trenutnaPlata;
    private TipZaposlenja tipZaposlenja;
    private String slikaString;
    private String identificationNumber;

    private List<PlataDTO> plate;

    public ZaposleniDTO(Zaposleni zaposleni, String _slikaString) throws IOException {
        ime = zaposleni.getIme();
        prezime = zaposleni.getPrezime();
        pol = zaposleni.getPol();
        datumRodjenja = zaposleni.getDatumRodjenja();
        trenutnaPlata = zaposleni.getTrenutnaPlata();
        tipZaposlenja = zaposleni.getTipZaposlenja();
        slikaString = _slikaString;
        identificationNumber = zaposleni.getIdentificationNumber();
    }
}
