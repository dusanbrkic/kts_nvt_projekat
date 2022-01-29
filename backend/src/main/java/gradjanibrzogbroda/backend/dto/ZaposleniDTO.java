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

import org.apache.catalina.mbeans.RoleMBean;
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

	public ZaposleniDTO(String newZaposleniIme, String newZaposleniPrezime, Pol pol, LocalDate now, double d,
			String string, String string2, String newZaposleniUsername) {
		this.ime = newZaposleniIme;
		this.prezime = newZaposleniPrezime;
		this.pol = pol;
		this.datumRodjenja = now;
		this.trenutnaPlata = d;
		this.roleName = string;
		this.slikaString = string2;
		this.username = newZaposleniUsername;
	}
}
