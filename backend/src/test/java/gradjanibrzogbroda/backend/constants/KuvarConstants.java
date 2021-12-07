package gradjanibrzogbroda.backend.constants;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import gradjanibrzogbroda.backend.domain.Pol;

public class KuvarConstants {
    public static final Long DB_KUVAR_ID = 2L;

    public static final String NEW_KUVAR_IME = "Dusan";
    public static final String NEW_KUVAR_PREZIME = "Hajduk";
    public static final Pol NEW_KUVAR_POL = Pol.MUSKI;
    public static final LocalDate NEW_KUVAR_DATUM_RODJENJA = LocalDate.parse("1999-3-10", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    public static final Double NEW_KUVAR_TRENUTNA_PLATA = 65000D;
    public static final String NEW_KUVAR_PUTANJA_SLIKE = "hajduk.jpeg";
    
}

