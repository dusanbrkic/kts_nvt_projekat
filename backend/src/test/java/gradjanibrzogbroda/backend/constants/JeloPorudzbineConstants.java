package gradjanibrzogbroda.backend.constants;

import gradjanibrzogbroda.backend.domain.*;
import gradjanibrzogbroda.backend.dto.JeloPorudzbineDTO;

public class JeloPorudzbineConstants {
    public static final Integer DB_JELO_ID = 1;
    public static final Double DB_JELO_CENA = 240.0;
    public static final String DB_JELO_NAZIV = "Pljeskavica";
    public static final TipJela DB_JELO_TIP = TipJela.BASIC;
    public static final KategorijaJela DB_JELO_KAT = KategorijaJela.GLAVNO_JELO;


    public static final Integer DB_JELO_PORUDZBINE_ID_1 = 6;
    public static final Integer DB_JELO_PORUDZBINE_ID_2 = 7;

    public static final Double DB_JELO_PORUDZBINE_KOLICINA = 2.0;
    public static final String DB_JELO_PORUDZBINE_NAPOMENA = "Da bude lepo.";
    public static final StatusJela DB_JELO_PORUDZBINE_STATUS = StatusJela.KREIRANO;
    public static final Double DB_IZMENJENO_JELO_PORUDZBINE_KOLICINA = 3.0;
    public static final String DB_IZMENJENO_JELO_PORUDZBINE_NAPOMENA = "Da bude lepo i fino.";

    public static final Integer DB_NOVO_JELO_PORUDZBINE_ID = 5;
    public static final Integer DB_NOVO_JELO_PORUDZBINE_PORUDZBINA = 4;
    public static final Double DB_NOVO_JELO_PORUDZBINE_KOLICINA = 3.0;
    public static final String DB_NOVO_JELO_PORUDZBINE_NAPOMENA = "Bez sira.";
    public static final StatusJela DB_NOVO_JELO_PORUDZBINE_STATUS = StatusJela.KREIRANO;
    public static final JeloPorudzbineDTO DB_NOVO_JELO_PORUDZBINE_DTO = JeloPorudzbineDTO.builder()
            .id(DB_NOVO_JELO_PORUDZBINE_ID)
            .kolicina(DB_NOVO_JELO_PORUDZBINE_KOLICINA)
            .napomena(DB_NOVO_JELO_PORUDZBINE_NAPOMENA)
            .statusJela(DB_NOVO_JELO_PORUDZBINE_STATUS)
            .porudzbinaId(DB_NOVO_JELO_PORUDZBINE_PORUDZBINA)
            .build();

    public static final Integer DB_PREUZETO_JELO_PORUDZBINE_ID = 8;
    public static final Double DB_PREUZETO_JELO_PORUDZBINE_KOLICINA = 3.0;
    public static final String DB_PREUZETO_JELO_PORUDZBINE_NAPOMENA = "Sa puno sira.";
    public static final StatusJela DB_PREUZETO_JELO_PORUDZBINE_STATUS = StatusJela.PREUZETO;

    public static final Integer DB_PRIPREMLJENO_JELO_PORUDZBINE_ID = 9;
    public static final Double DB_PRIPREMLJENO_JELO_PORUDZBINE_KOLICINA = 3.0;
    public static final String DB_PRIPREMLJENO_JELO_PORUDZBINE_NAPOMENA = "Sa puno kecapa.";
    public static final StatusJela DB_PRIPREMLJENO_JELO_PORUDZBINE_STATUS = StatusJela.PRIPREMLJENO;



    public static final Integer DB_KREIRANA_PORUDZBINA_ID = 4;
    public static final Double DB_KREIRANA_PORUDZBINA_CENA = 480.0;

    public static final Integer DB_PRIPREMLJENA_PORUDZBINA_ID = 3;
    public static final Double DB_PRIPREMLJENA_PORUDZBINA_CENA = 480.0;

    public static final Integer DB_NAPLACENA_PORUDZBINA_ID = 12;



    public static final Integer DB_NON_EXISTANT_ID = 100;





}
