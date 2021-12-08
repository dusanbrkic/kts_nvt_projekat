package gradjanibrzogbroda.backend.constants;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import gradjanibrzogbroda.backend.domain.Menadzer;
import gradjanibrzogbroda.backend.domain.Pol;
import gradjanibrzogbroda.backend.domain.TipZaposlenja;
import gradjanibrzogbroda.backend.domain.Zaposleni;
import gradjanibrzogbroda.backend.dto.PlataDTO;
import gradjanibrzogbroda.backend.dto.ZaposleniDTO;

public class ZaposleniConstants {
    public static final Integer NEW_ZAPOSLENI_ID = 4;
    public static final String NEW_ZAPOSLENI_IME = "Dusan";
    public static final String NEW_ZAPOSLENI_PREZIME = "Hajduk";

    public static final Integer UPDATED_ZAPOSLENI_ID = 1;
    public static final String UPDATED_ZAPOSLENI_IME = "Luka";
    public static final String UPDATED_ZAPOSLENI_PREZIME = "Stanisavljevic";
    
    public static final Double NEW_VISINA_PLATE = 98000.0;
    public static final PlataDTO NEW_PLATA_DTO = new PlataDTO(10, NEW_VISINA_PLATE, LocalDate.parse("2021-11-27", DateTimeFormatter.ofPattern("yyyy-MM-dd")), 
    null, UPDATED_ZAPOSLENI_ID);

    public static final Menadzer UPDATED_ZAPOSLENI = new Menadzer(new ZaposleniDTO(UPDATED_ZAPOSLENI_ID, "", "",
            null, null, 52000.0, null, null,  new ArrayList<>()));

    public static final Integer DELETED_ZAPOSLENI_ID = 3;

    public static final Integer DB_ZAPOSLENI_COUNT = 3;

    public static final Integer DB_ZAPOSLENI_ID = 2;
    public static final String DB_ZAPOSLENI_IME = "Marko";
    public static final String DB_ZAPOSLENI_PREZIME = "Maric";
    public static final LocalDate DB_ZAPOSLENI_DATUM_RODJENJA = LocalDate.parse("1990-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    
}

