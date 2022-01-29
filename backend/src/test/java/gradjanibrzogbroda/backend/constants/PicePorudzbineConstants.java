package gradjanibrzogbroda.backend.constants;

import gradjanibrzogbroda.backend.domain.StatusJela;
import gradjanibrzogbroda.backend.domain.StatusPica;
import gradjanibrzogbroda.backend.domain.StatusPorudzbine;
import gradjanibrzogbroda.backend.dto.PicePorudzbineDTO;

public class PicePorudzbineConstants {
    public static final Integer DB_PICE_ID = 6;
    public static final Double DB_PICE_CENA = 125.0;
    public static final String DB_PICE_NAZIV = "Coca Cola 0.33l";


    public static final Integer DB_PICE_PORUDZBINE_ID_1 = 5;
    public static final Integer DB_PICE_PORUDZBINE_ID_2 = 6;

    public static final Double DB_PICE_PORUDZBINE_KOLICINA = 2.0;
    public static final String DB_PICE_PORUDZBINE_NAPOMENA = "Da bude lepo.";
    public static final StatusPica DB_PICE_PORUDZBINE_STATUS = StatusPica.KREIRANO;
    public static final Double DB_IZMENJENO_PICE_PORUDZBINE_KOLICINA = 3.0;
    public static final String DB_IZMENJENO_PICE_PORUDZBINE_NAPOMENA = "Da bude lepo i fino.";

    public static final Integer DB_NOVO_PICE_PORUDZBINE_ID = 4;
    public static final Integer DB_NOVO_PICE_PORUDZBINE_PORUDZBINA = 5;
    public static final Double DB_NOVO_PICE_PORUDZBINE_KOLICINA = 3.0;
    public static final String DB_NOVO_PICE_PORUDZBINE_NAPOMENA = "Bez sira.";
    public static final StatusPica DB_NOVO_PICE_PORUDZBINE_STATUS = StatusPica.KREIRANO;
    public static final PicePorudzbineDTO DB_NOVO_PICE_PORUDZBINE_DTO = PicePorudzbineDTO.builder()
            .id(DB_NOVO_PICE_PORUDZBINE_ID)
            .kolicina(DB_NOVO_PICE_PORUDZBINE_KOLICINA)
            .napomena(DB_NOVO_PICE_PORUDZBINE_NAPOMENA)
            .statusPica(DB_NOVO_PICE_PORUDZBINE_STATUS)
            .porudzbinaId(DB_NOVO_PICE_PORUDZBINE_PORUDZBINA)
            .build();


    public static final Integer DB_PRIPREMLJENO_PICE_PORUDZBINE_ID = 7;
    public static final Double DB_PRIPREMLJENO_PICE_PORUDZBINE_KOLICINA = 2.0;
    public static final String DB_PRIPREMLJENO_PICE_PORUDZBINE_NAPOMENA = "Puno leda.";
    public static final StatusPica DB_PRIPREMLJENO_PICE_PORUDZBINE_STATUS = StatusPica.PRIPREMLJENO;

    public static final Integer DB_KREIRANA_PORUDZBINA_ID = 7;
    public static final Double DB_KREIRANA_PORUDZBINA_CENA = 480.0;

    public static final Integer DB_PRIPREMLJENA_PORUDZBINA_ID = 8;
    public static final Double DB_PRIPREMLJENA_PORUDZBINA_CENA = 480.0;

    public static final Integer DB_NAPLACENA_PORUDZBINA_ID = 12;



    public static final Integer DB_NON_EXISTANT_ID = 100;


}
