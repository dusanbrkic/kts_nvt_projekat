package gradjanibrzogbroda.backend.constants;

import gradjanibrzogbroda.backend.domain.*;
import gradjanibrzogbroda.backend.dto.JeloPorudzbineDTO;
import gradjanibrzogbroda.backend.dto.PicePorudzbineDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class PorudzbineUnitTestConstants {
    public static final Integer UT_JELO_ID = 1;
    public static final Double UT_JELO_CENA = 240.0;
    public static final String UT_JELO_NAZIV = "Pljeskavica";
    public static final TipJela UT_JELO_TIP = TipJela.BASIC;
    public static final KategorijaJela UT_JELO_KAT = KategorijaJela.GLAVNO_JELO;

    public static final Integer UT_PICE_ID = 2;
    public static final Double UT_PICE_CENA = 240.0;
    public static final String UT_PICE_NAZIV = "Limunada";

    public static final Integer UT_STO_ID = 1;
    public static final String UT_STO_IDENTIF_NUM = "11111";

    public static final Integer UT_STO_ID_2 = 2;
    public static final String UT_STO_IDENTIF_NUM_2 = "22222";


    public static final Integer UT_JELO_PORUDZBINE_ID_1 = 1;
    public static final Integer UT_JELO_PORUDZBINE_ID_2 = 2;
    public static final Integer UT_JELO_PORUDZBINE_ID_3 = 1;
    public static final Integer UT_JELO_PORUDZBINE_ID_4 = 2;

    public static final Integer UT_PICE_PORUDZBINE_ID_1 = 1;
    public static final Integer UT_PICE_PORUDZBINE_ID_2 = 2;
    public static final Integer UT_PICE_PORUDZBINE_ID_3 = 3;
    public static final Integer UT_PICE_PORUDZBINE_ID_4 = 4;

    public static final Double UT_JELO_PORUDZBINE_KOLICINA = 2.0;
    public static final String UT_JELO_PORUDZBINE_NAPOMENA = "Da bude lepo.";
    public static final StatusJela UT_JELO_PORUDZBINE_STATUS = StatusJela.KREIRANO;
//    public static final Double UT_IZMENJENO_JELO_PORUDZBINE_KOLICINA = 3.0;
//    public static final String UT_IZMENJENO_JELO_PORUDZBINE_NAPOMENA = "Da bude lepo i fino.";

    public static final Double UT_PICE_PORUDZBINE_KOLICINA = 2.0;
    public static final String UT_PICE_PORUDZBINE_NAPOMENA = "Da bude lepo.";
    public static final StatusPica UT_PICE_PORUDZBINE_STATUS = StatusPica.KREIRANO;
//    public static final Double UT_IZMENJENO_PICE_PORUDZBINE_KOLICINA = 3.0;
//    public static final String UT_IZMENJENO_PICE_PORUDZBINE_NAPOMENA = "Da bude lepo i fino.";
//
    public static final Integer UT_NOVO_JELO_PORUDZBINE_ID = 5;
    public static final Integer UT_NOVO_JELO_PORUDZBINE_PORUDZBINA = 1;
    public static final Double UT_NOVO_JELO_PORUDZBINE_KOLICINA = 3.0;
    public static final String UT_NOVO_JELO_PORUDZBINE_NAPOMENA = "Bez sira.";
    public static final StatusJela UT_NOVO_JELO_PORUDZBINE_STATUS = StatusJela.KREIRANO;
    public static final JeloPorudzbineDTO UT_NOVO_JELO_PORUDZBINE_DTO = JeloPorudzbineDTO.builder()
            .id(UT_NOVO_JELO_PORUDZBINE_ID)
            .kolicina(UT_NOVO_JELO_PORUDZBINE_KOLICINA)
            .napomena(UT_NOVO_JELO_PORUDZBINE_NAPOMENA)
            .statusJela(UT_NOVO_JELO_PORUDZBINE_STATUS)
            .porudzbinaId(UT_NOVO_JELO_PORUDZBINE_PORUDZBINA)
            .build();
//
    public static final Integer UT_NOVO_PICE_PORUDZBINE_ID = 2;
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

    public static final Integer UT_PREUZETO_JELO_PORUDZBINE_ID = 3;
    public static final Double UT_PREUZETO_JELO_PORUDZBINE_KOLICINA = 3.0;
    public static final String UT_PREUZETO_JELO_PORUDZBINE_NAPOMENA = "Sa puno sira.";
    public static final StatusJela UT_PREUZETO_JELO_PORUDZBINE_STATUS = StatusJela.PREUZETO;

    public static final Integer UT_PRIPREMLJENO_JELO_PORUDZBINE_ID = 4;
    public static final Double UT_PRIPREMLJENO_JELO_PORUDZBINE_KOLICINA = 3.0;
    public static final String UT_PRIPREMLJENO_JELO_PORUDZBINE_NAPOMENA = "Sa puno kecapa.";
    public static final StatusJela UT_PRIPREMLJENO_JELO_PORUDZBINE_STATUS = StatusJela.PRIPREMLJENO;

    public static final Integer UT_PRIPREMLJENO_PICE_PORUDZBINE_ID = 3;
    public static final Double UT_PRIPREMLJENO_PICE_PORUDZBINE_KOLICINA = 3.0;
    public static final String UT_PRIPREMLJENO_PICE_PORUDZBINE_NAPOMENA = "Sa puno kecapa.";
    public static final StatusPica UT_PRIPREMLJENO_PICE_PORUDZBINE_STATUS = StatusPica.PRIPREMLJENO;

    public static final Integer UT_KREIRANA_PORUDZBINA_ID_1 = 1;
    public static final Integer UT_KREIRANA_PORUDZBINA_ID_2 = 2;
    public static final Double UT_KREIRANA_PORUDZBINA_CENA = 480.0;

    public static final Integer UT_DOSTAVLJENA_PORUDZBINA_ID = 3;


    public static final Integer UT_NAPLACENA_PORUDZBINA_ID = 4;

    public static final Integer UT_NOVA_PORUDZBINA_ID = 5;
    public static final Integer UT_DODATO_JELO_PORUDZBINE_ID = 6;
    public static final Integer UT_DODATO_PICE_PORUDZBINE_ID = 7;

    public static final JeloPorudzbine JP1 = JeloPorudzbine.builder()
            .id(UT_JELO_PORUDZBINE_ID_1)
            .kolicina(UT_JELO_PORUDZBINE_KOLICINA)
            .napomena(UT_JELO_PORUDZBINE_NAPOMENA)
            .statusJela(UT_JELO_PORUDZBINE_STATUS)
            .obrisan(false)
            .build();

    public static final PicePorudzbine PP1 = PicePorudzbine.builder()
            .id(UT_PICE_PORUDZBINE_ID_1)
            .kolicina(UT_PICE_PORUDZBINE_KOLICINA)
            .napomena(UT_PICE_PORUDZBINE_NAPOMENA)
            .statusPica(UT_PICE_PORUDZBINE_STATUS)
            .obrisan(false)
            .build();



    public static final Integer UT_NON_EXISTANT_ID = 100;
}
