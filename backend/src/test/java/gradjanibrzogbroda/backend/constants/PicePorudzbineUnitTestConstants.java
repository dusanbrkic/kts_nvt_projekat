package gradjanibrzogbroda.backend.constants;

import gradjanibrzogbroda.backend.domain.KategorijaJela;
import gradjanibrzogbroda.backend.domain.StatusJela;
import gradjanibrzogbroda.backend.domain.StatusPica;
import gradjanibrzogbroda.backend.domain.TipJela;
import gradjanibrzogbroda.backend.dto.JeloPorudzbineDTO;
import gradjanibrzogbroda.backend.dto.PicePorudzbineDTO;

public class PicePorudzbineUnitTestConstants {
    public static final Integer UT_PICE_ID = 1;
    public static final Double UT_PICE_CENA = 240.0;
    public static final String UT_PICE_NAZIV = "Pljeskavica";


    public static final Integer UT_PICE_PORUDZBINE_ID_1 = 1;
    public static final Integer UT_PICE_PORUDZBINE_ID_2 = 2;

    public static final Double UT_PICE_PORUDZBINE_KOLICINA = 2.0;
    public static final String UT_PICE_PORUDZBINE_NAPOMENA = "Da bude lepo.";
    public static final StatusPica UT_PICE_PORUDZBINE_STATUS = StatusPica.KREIRANO;
    public static final Double UT_IZMENJENO_PICE_PORUDZBINE_KOLICINA = 3.0;
    public static final String UT_IZMENJENO_PICE_PORUDZBINE_NAPOMENA = "Da bude lepo i fino.";

    public static final Integer UT_NOVO_PICE_PORUDZBINE_ID = 4;
    public static final Integer UT_NOVO_PICE_PORUDZBINE_PORUDZBINA = 1;
    public static final Double UT_NOVO_PICE_PORUDZBINE_KOLICINA = 3.0;
    public static final String UT_NOVO_PICE_PORUDZBINE_NAPOMENA = "Bez sira.";
    public static final StatusPica UT_NOVO_PICE_PORUDZBINE_STATUS = StatusPica.KREIRANO;
    public static final PicePorudzbineDTO UT_NOVO_PICE_PORUDZBINE_DTO = PicePorudzbineDTO.builder()
            .id(UT_NOVO_PICE_PORUDZBINE_ID)
            .kolicina(UT_NOVO_PICE_PORUDZBINE_KOLICINA)
            .napomena(UT_NOVO_PICE_PORUDZBINE_NAPOMENA)
            .statusPica(UT_NOVO_PICE_PORUDZBINE_STATUS)
            .porudzbinaId(UT_NOVO_PICE_PORUDZBINE_PORUDZBINA)
            .build();


    public static final Integer UT_PRIPREMLJENO_PICE_PORUDZBINE_ID = 3;
    public static final Double UT_PRIPREMLJENO_PICE_PORUDZBINE_KOLICINA = 3.0;
    public static final String UT_PRIPREMLJENO_PICE_PORUDZBINE_NAPOMENA = "Sa puno kecapa.";
    public static final StatusPica UT_PRIPREMLJENO_PICE_PORUDZBINE_STATUS = StatusPica.PRIPREMLJENO;

    public static final Integer UT_KREIRANA_PORUDZBINA_ID = 1;
    public static final Double UT_KREIRANA_PORUDZBINA_CENA = 480.0;

    public static final Integer UT_PRIPREMLJENA_PORUDZBINA_ID = 3;
    public static final Double UT_PRIPREMLJENA_PORUDZBINA_CENA = 480.0;

    public static final Integer UT_NAPLACENA_PORUDZBINA_ID = 2;



    public static final Integer UT_NON_EXISTANT_ID = 100;
}
