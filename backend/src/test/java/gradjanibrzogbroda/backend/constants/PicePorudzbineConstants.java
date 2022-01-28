package gradjanibrzogbroda.backend.constants;

import gradjanibrzogbroda.backend.domain.StatusJela;
import gradjanibrzogbroda.backend.domain.StatusPica;
import gradjanibrzogbroda.backend.domain.StatusPorudzbine;
import gradjanibrzogbroda.backend.dto.PicePorudzbineDTO;

public class PicePorudzbineConstants {
    public static final Integer NEW_PICE_PORUDZBINE_ID = 5;
    public static final Double NEW_PICE_PORUDZBINE_KOLICINA = 3.0;
    public static final String NEW_PICE_PORUDZBINE_NAPOMENA = "Bez sira.";
    public static final Integer NEW_PICE_PORUDZBINE_PICE = 7;
    public static final Integer NEW_PICE_PORUDZBINE_PORUDZBINA = 1;
    public static final PicePorudzbineDTO NEW_PICE_PORUDZBINE = PicePorudzbineDTO.builder()
            .id(NEW_PICE_PORUDZBINE_ID)
            .kolicina(NEW_PICE_PORUDZBINE_KOLICINA)
            .napomena(NEW_PICE_PORUDZBINE_NAPOMENA)
//            .piceId(NEW_PICE_PORUDZBINE_PICE)
            .porudzbinaId(NEW_PICE_PORUDZBINE_PORUDZBINA)
            .statusPica(StatusPica.KREIRANO)
            .build();

    public static final Integer UPDATED_PICE_PORUDZBINE_ID = 2;
    public static final Double UPDATED_PICE_PORUDZBINE_KOLICINA = 3.0;
    public static final String UPDATED_PICE_PORUDZBINE_NAPOMENA = "Stavite mi majoneza i kecapa.";
    public static final Integer UPDATED_PICE_PORUDZBINE_PICE = 6;
    public static final Integer UPDATED_PICE_PORUDZBINE_PORUDZBINA = 2;
    public static final PicePorudzbineDTO UPDATED_PICE_PORUDZBINE = PicePorudzbineDTO.builder()
            .id(UPDATED_PICE_PORUDZBINE_ID)
            .kolicina(UPDATED_PICE_PORUDZBINE_KOLICINA)
            .napomena(UPDATED_PICE_PORUDZBINE_NAPOMENA)
//            .piceId(UPDATED_PICE_PORUDZBINE_PICE)
            .porudzbinaId(UPDATED_PICE_PORUDZBINE_PORUDZBINA)
            .statusPica(StatusPica.KREIRANO)
            .build();

    public static final Integer DELETED_PICE_PORUDZBINE_ID = 1;

    public static final Integer DB_PICE_PORUDZBINE_ID = 1;
    public static final String DB_PICE_PORUDZBINE_NAPOMENA = "Sa svezim sastojcima.";
    public static final Double DB_PICE_PORUDZBINE_KOLICINA = 2.0;


    public static final Integer KREIRANO_PICE_PORUDZBINE_ID = 2;
    public static final Integer PRIPREMLJENO_PICE_PORUDZBINE_ID = 3;


}
