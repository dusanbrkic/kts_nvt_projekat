package gradjanibrzogbroda.backend.constants;

import gradjanibrzogbroda.backend.domain.Jelo;
import gradjanibrzogbroda.backend.domain.KategorijaJela;
import gradjanibrzogbroda.backend.domain.StatusJela;
import gradjanibrzogbroda.backend.domain.TipJela;
import gradjanibrzogbroda.backend.dto.JeloPorudzbineDTO;

import javax.persistence.criteria.CriteriaBuilder;

public class JeloPorudzbineUnitTestConstants {
    public static final Integer UT_JELO_ID = 1;
    public static final Double UT_JELO_CENA = 240.0;
    public static final String UT_JELO_NAZIV = "Pljeskavica";
    public static final TipJela UT_JELO_TIP = TipJela.BASIC;
    public static final KategorijaJela UT_JELO_KAT = KategorijaJela.GLAVNO_JELO;


    public static final Integer UT_JELO_PORUDZBINE_ID_1 = 1;
    public static final Integer UT_JELO_PORUDZBINE_ID_2 = 2;

    public static final Double UT_JELO_PORUDZBINE_KOLICINA = 2.0;
    public static final String UT_JELO_PORUDZBINE_NAPOMENA = "Da bude lepo.";
    public static final StatusJela UT_JELO_PORUDZBINE_STATUS = StatusJela.KREIRANO;
    public static final Double UT_IZMENJENO_JELO_PORUDZBINE_KOLICINA = 3.0;
    public static final String UT_IZMENJENO_JELO_PORUDZBINE_NAPOMENA = "Da bude lepo i fino.";

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

    public static final Integer UT_PREUZETO_JELO_PORUDZBINE_ID = 3;
    public static final Double UT_PREUZETO_JELO_PORUDZBINE_KOLICINA = 3.0;
    public static final String UT_PREUZETO_JELO_PORUDZBINE_NAPOMENA = "Sa puno sira.";
    public static final StatusJela UT_PREUZETO_JELO_PORUDZBINE_STATUS = StatusJela.PREUZETO;

    public static final Integer UT_PRIPREMLJENO_JELO_PORUDZBINE_ID = 4;
    public static final Double UT_PRIPREMLJENO_JELO_PORUDZBINE_KOLICINA = 3.0;
    public static final String UT_PRIPREMLJENO_JELO_PORUDZBINE_NAPOMENA = "Sa puno kecapa.";
    public static final StatusJela UT_PRIPREMLJENO_JELO_PORUDZBINE_STATUS = StatusJela.PRIPREMLJENO;



    public static final Integer UT_KREIRANA_PORUDZBINA_ID = 1;
    public static final Double UT_KREIRANA_PORUDZBINA_CENA = 480.0;

    public static final Integer UT_PRIPREMLJENA_PORUDZBINA_ID = 3;
    public static final Double UT_PRIPREMLJENA_PORUDZBINA_CENA = 480.0;

    public static final Integer UT_NAPLACENA_PORUDZBINA_ID = 2;



    public static final Integer UT_NON_EXISTANT_JELO_PORUDZBINE_ID = 100;
    public static final Integer UT_NON_EXISTANT_PORUDZBINA_ID = 100;
    public static final Integer UT_NON_EXISTANT_JELO_ID = 100;
}
