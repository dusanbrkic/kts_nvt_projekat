package gradjanibrzogbroda.backend.constants;

import gradjanibrzogbroda.backend.domain.JeloPorudzbine;
import gradjanibrzogbroda.backend.domain.StatusJela;
import gradjanibrzogbroda.backend.domain.StatusPorudzbine;
import gradjanibrzogbroda.backend.dto.JeloPorudzbineDTO;

public class JeloPorudzbineConstants {
    public static final Integer NEW_JELO_PORUDZBINE_ID = 6;
    public static final Double NEW_JELO_PORUDZBINE_KOLICINA = 3.0;
    public static final String NEW_JELO_PORUDZBINE_NAPOMENA = "Bez sira.";
    public static final Integer NEW_JELO_PORUDZBINE_JELO = 1;
    public static final Integer NEW_JELO_PORUDZBINE_PORUDZBINA = 1;
    public static final JeloPorudzbineDTO NEW_JELO_PORUDZBINE = JeloPorudzbineDTO.builder()
            .id(NEW_JELO_PORUDZBINE_ID)
            .kolicina(NEW_JELO_PORUDZBINE_KOLICINA)
            .napomena(NEW_JELO_PORUDZBINE_NAPOMENA)
//            .jeloId(NEW_JELO_PORUDZBINE_JELO)
            .porudzbinaId(NEW_JELO_PORUDZBINE_PORUDZBINA)
            .statusJela(StatusJela.KREIRANO)
            .build();

    public static final Integer UPDATED_JELO_PORUDZBINE_ID = 2;
    public static final Double UPDATED_JELO_PORUDZBINE_KOLICINA = 3.0;
    public static final String UPDATED_JELO_PORUDZBINE_NAPOMENA = "Stavite mi majoneza i kecapa.";
    public static final Integer UPDATED_JELO_PORUDZBINE_JELO = 3;
    public static final Integer UPDATED_JELO_PORUDZBINE_PORUDZBINA = 2;
    public static final JeloPorudzbineDTO UPDATED_JELO_PORUDZBINE = JeloPorudzbineDTO.builder()
            .id(UPDATED_JELO_PORUDZBINE_ID)
            .kolicina(UPDATED_JELO_PORUDZBINE_KOLICINA)
            .napomena(UPDATED_JELO_PORUDZBINE_NAPOMENA)
//            .jeloId(UPDATED_JELO_PORUDZBINE_JELO)
            .porudzbinaId(UPDATED_JELO_PORUDZBINE_PORUDZBINA)
            .statusJela(StatusJela.KREIRANO)
            .build();

    public static final Integer DELETED_JELO_PORUDZBINE_ID = 1;


    public static final Integer DB_JELO_ID = 1;
    public static final String DB_JELO_NAZIV = "Pljeskavica";
    public static final Double DB_JELO_CENA = 240.0;



    public static final Integer DB_PORUDZBINA_ID = 1;
    public static final String DB_PORUDZBINA_NAPOMENA = "Posluziti hranu dok je topla.";
    public static final Integer DB_PORUDZBINA_STO_ID = 1;
    public static final StatusPorudzbine DB_STATUS_PORUDZBINE = StatusPorudzbine.KREIRANO;

    public static final Integer NAPLACENA_PORUDZBINA_ID = 2;


    public static final Integer DB_JELO_PORUDZBINE_COUNT = 5;
    public static final Integer DB_JELO_PORUDZBINE_ID = 1;
    public static final String DB_JELO_PORUDZBINE_NAPOMENA = "Sa svezim sastojcima.";
    public static final Double DB_JELO_PORUDZBINE_KOLICINA = 2.0;
    public static final StatusJela DB_JELO_PORUDZBINE_STATUS = StatusJela.KREIRANO;
    public static final Integer DB_JELO_PORUDZBINE_JELO = 1;
    public static final Integer DB_JELO_PORUDZBINE_PORUDZBINA = 1;

    public static final Integer NON_EXISTANT_JELO_PORUDZBINE_ID = 100;
    public static final Integer NON_EXISTANT_PORUDZBINA_ID = 100;
    public static final Integer NON_EXISTANT_JELO_ID = 100;

    public static final Double NEGATIVNA_KOLICINA = -2.0;



    public static final Integer KREIRANO_JELO_PORUDZBINE_ID = 2;
    public static final Integer PREUZETO_JELO_PORUDZBINE_ID = 3;
    public static final Integer PRIPREMLJENO_JELO_PORUDZBINE_ID = 4;





}
