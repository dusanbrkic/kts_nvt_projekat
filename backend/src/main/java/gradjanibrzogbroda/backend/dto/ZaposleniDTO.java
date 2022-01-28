package gradjanibrzogbroda.backend.dto;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import gradjanibrzogbroda.backend.config.StorageProperties;
import gradjanibrzogbroda.backend.domain.Plata;
import gradjanibrzogbroda.backend.domain.Pol;
import gradjanibrzogbroda.backend.domain.Zaposleni;
import gradjanibrzogbroda.backend.util.StorageUtil;
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
    private String roleName;
    private String slikaString;
    private String username;

    private List<PlataDTO> plate;

    public ZaposleniDTO(Zaposleni zaposleni) {
        ime = zaposleni.getIme();
        prezime = zaposleni.getPrezime();
        pol = zaposleni.getPol();
        datumRodjenja = zaposleni.getDatumRodjenja();
        trenutnaPlata = zaposleni.getTrenutnaPlata();
        roleName = zaposleni.getRoles().get(0).getRole();
        slikaString = StorageUtil.loadAsString(StorageProperties.ZAPOSLENI_LOCATION, zaposleni.getNazivSlike());
        username = zaposleni.getUsername();
    }
}
