package gradjanibrzogbroda.backend.constants;

import gradjanibrzogbroda.backend.domain.Porudzbina;
import gradjanibrzogbroda.backend.domain.StatusPorudzbine;
import gradjanibrzogbroda.backend.dto.JeloPorudzbineDTO;
import gradjanibrzogbroda.backend.dto.PicePorudzbineDTO;
import gradjanibrzogbroda.backend.dto.PorudzbinaDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PorudzbinaConstants {
    public static final Integer NEW_PORUDZBINA_ID = 4;
    public static final String NEW_PORUDZBINA_NAPOMENA = "Turi senfa.";
    public static final Integer NEW_PORUDZBINA_STO_ID = 1;
//    public static final PorudzbinaDTO NEW_PORUDZBINA = new PorudzbinaDTO(
//            NEW_PORUDZBINA_ID,  StatusPorudzbine.KREIRANO, LocalDateTime.parse("2021-11-27 12:32", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
//            NEW_PORUDZBINA_NAPOMENA, 990.00, 3, NEW_PORUDZBINA_STO_ID, new ArrayList<JeloPorudzbineDTO>(),
//            new ArrayList<PicePorudzbineDTO>());


    public static final Integer UPDATED_PORUDZBINA_ID = 2;
    public static final String UPDATED_PORUDZBINA_NAPOMENA = "Donesite mi jos jedno sirce";
    public static final Integer UPDATED_PORUDZBINA_STO_ID= 1;
//    public static final PorudzbinaDTO UPDATED_PORUDZBINA = new PorudzbinaDTO(
//            UPDATED_PORUDZBINA_ID,  StatusPorudzbine.PREUZETO, LocalDateTime.parse("2021-11-27 12:32", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
//            UPDATED_PORUDZBINA_NAPOMENA, 990.00, 3, UPDATED_PORUDZBINA_STO_ID, new ArrayList<JeloPorudzbineDTO>(),
//            new ArrayList<PicePorudzbineDTO>());


    public static final Integer DELETED_PORUDZBINA_ID = 3;

    public static final Integer DB_PORUDZBINA_COUNT = 3;
    public static final Integer DB_PORUDZBINA_ID = 1;
    public static final String DB_PORUDZBINA_NAPOMENA = "Posluziti hranu dok je topla.";
    public static final Integer DB_PORUDZBINA_STO_ID = 1;

    public static final StatusPorudzbine DB_STATUS_PORUDZBINE = StatusPorudzbine.KREIRANO;
    public static final Integer DB_PORUDZBINA_STATUS_COUNT = 1;

    public static final Integer DB_PORUDZBINA_KONOBAR_ID = 3;
    public static final Integer DB_PORUDZBINA_KONOBAR_COUNT = 3;


    public static final Integer NAPLACENA_PORUDZBINA_ID = 3;
    public static final StatusPorudzbine NAPLACENA_PORUDZBINA_STATUS = StatusPorudzbine.NAPLACENO;

    public static final StatusPorudzbine FIND_PORUDZBINA_STATUS = StatusPorudzbine.KREIRANO;
    public static final long FIND_STATUS_NUMBER_OF_ITEMS = 1;
    public static final long FIND_PORUDZBINA_KONOBAR_COUNT = 3;
}
