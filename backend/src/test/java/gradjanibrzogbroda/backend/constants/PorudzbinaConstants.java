package gradjanibrzogbroda.backend.constants;

import gradjanibrzogbroda.backend.domain.*;
import gradjanibrzogbroda.backend.dto.JeloPorudzbineDTO;
import gradjanibrzogbroda.backend.dto.PicePorudzbineDTO;


public class PorudzbinaConstants {
    public static final Integer DB_JELO_ID = 1;
    public static final Double DB_JELO_CENA = 240.0;
    public static final String DB_JELO_NAZIV = "Pljeskavica";
    public static final TipJela DB_JELO_TIP = TipJela.BASIC;
    public static final KategorijaJela DB_JELO_KAT = KategorijaJela.GLAVNO_JELO;

    public static final Integer DB_PICE_ID = 6;
    public static final Double DB_PICE_CENA = 125.0;
    public static final String DB_PICE_NAZIV = "Coca Cola 0.33l";

    public static final Integer DB_STO_ID = 1;
    public static final String DB_STO_IDENTIF_NUM = "11111";

    public static final Integer DB_STO_ID_2 = 2;
    public static final String DB_STO_IDENTIF_NUM_2 = "22222";


    public static final Integer DB_JELO_PORUDZBINE_ID_1 = 10;
    public static final Integer DB_JELO_PORUDZBINE_ID_2 = 11;
    public static final Integer DB_JELO_PORUDZBINE_ID_3 = 12;
    public static final Integer DB_JELO_PORUDZBINE_ID_4 = 13;

    public static final Integer DB_PICE_PORUDZBINE_ID_1 = 8;

    public static final Double DB_JELO_PORUDZBINE_KOLICINA = 2.0;
    public static final String DB_JELO_PORUDZBINE_NAPOMENA = "Da bude lepo.";
    public static final StatusJela DB_JELO_PORUDZBINE_STATUS = StatusJela.KREIRANO;
//    public static final Double DB_IZMENJENO_JELO_PORUDZBINE_KOLICINA = 3.0;
//    public static final String DB_IZMENJENO_JELO_PORUDZBINE_NAPOMENA = "Da bude lepo i fino.";

    public static final Double DB_PICE_PORUDZBINE_KOLICINA = 2.0;
    public static final String DB_PICE_PORUDZBINE_NAPOMENA = "Da bude lepo.";
    public static final StatusPica DB_PICE_PORUDZBINE_STATUS = StatusPica.KREIRANO;
    //    public static final Double DB_IZMENJENO_PICE_PORUDZBINE_KOLICINA = 3.0;
//    public static final String DB_IZMENJENO_PICE_PORUDZBINE_NAPOMENA = "Da bude lepo i fino.";
//
    public static final Integer DB_NOVO_JELO_PORUDZBINE_ID = 14;
    public static final Integer DB_NOVO_JELO_PORUDZBINE_PORUDZBINA = 9;
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
    //
    public static final Integer DB_NOVO_PICE_PORUDZBINE_ID = 10;
    public static final Integer DB_NOVO_PICE_PORUDZBINE_PORUDZBINA = 9;
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


    public static final Integer DB_KREIRANA_PORUDZBINA_ID_1 = 9;
    public static final Integer DB_KREIRANA_PORUDZBINA_ID_2 = 10;
    public static final Double DB_KREIRANA_PORUDZBINA_CENA = 2650.0;

    public static final Integer DB_DOSTAVLJENA_PORUDZBINA_ID = 11;


    public static final Integer DB_NAPLACENA_PORUDZBINA_ID = 12;

    public static final Integer DB_NOVA_PORUDZBINA_ID = 13;
    public static final Integer DB_DODATO_JELO_PORUDZBINE_ID = 6;
    public static final Integer DB_DODATO_PICE_PORUDZBINE_ID = 7;

    public static final JeloPorudzbine JP1 = JeloPorudzbine.builder()
            .id(DB_JELO_PORUDZBINE_ID_1)
            .kolicina(DB_JELO_PORUDZBINE_KOLICINA)
            .napomena(DB_JELO_PORUDZBINE_NAPOMENA)
            .statusJela(DB_JELO_PORUDZBINE_STATUS)
            .obrisan(false)
            .build();

    public static final PicePorudzbine PP1 = PicePorudzbine.builder()
            .id(DB_PICE_PORUDZBINE_ID_1)
            .kolicina(DB_PICE_PORUDZBINE_KOLICINA)
            .napomena(DB_PICE_PORUDZBINE_NAPOMENA)
            .statusPica(DB_PICE_PORUDZBINE_STATUS)
            .obrisan(false)
            .build();



    public static final Integer DB_NON_EXISTANT_ID = 100;
}
