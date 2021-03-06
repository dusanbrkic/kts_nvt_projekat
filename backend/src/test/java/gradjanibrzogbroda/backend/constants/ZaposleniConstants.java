package gradjanibrzogbroda.backend.constants;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;

import gradjanibrzogbroda.backend.domain.Pol;
import gradjanibrzogbroda.backend.domain.Role;
import gradjanibrzogbroda.backend.domain.Zaposleni;
import gradjanibrzogbroda.backend.dto.PlataDTO;
import gradjanibrzogbroda.backend.dto.ZaposleniDTO;

public class ZaposleniConstants {
        public static final String FAKE_IDENTIFICATION_NUMBER = "123132131231312";

        public static final Integer NEW_ZAPOSLENI_ID = 4;
        public static final String NEW_ZAPOSLENI_IME = "Dusan";
        public static final String NEW_ZAPOSLENI_PREZIME = "Hajduk";
        public static final String NEW_ZAPOSLENI_USERNAME = "KSNAGA";
        public static final String NEW_ZAPOSLENI_ROLE = "ROLE_MANAGER";

        public static final Integer UPDATED_ZAPOSLENI_ID = 1;
        public static final String UPDATED_ZAPOSLENI_IME = "Luka";
        public static final String UPDATED_ZAPOSLENI_PREZIME = "Stanisavljevic";
        public static final String UPDATED_ZAPOSLENI_USERNAME = "Tasminal";
        public static final String UPDATED_ZAPOSLENI_ROLE = "ROLE_ADMIN";

        public static final Double NEW_VISINA_PLATE = 98000.0;
        public static final PlataDTO NEW_PLATA_DTO = new PlataDTO(10, NEW_VISINA_PLATE,
                        LocalDate.parse("2021-11-27", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        null, UPDATED_ZAPOSLENI_ID);

       public static final ZaposleniDTO UPDATED_ZAPOSLENI_DTO = new ZaposleniDTO(UPDATED_ZAPOSLENI_IME, UPDATED_ZAPOSLENI_PREZIME,
    		   Pol.MUSKI, LocalDate.now(), 52000.0,UPDATED_ZAPOSLENI_ROLE,"", UPDATED_ZAPOSLENI_USERNAME);

        public static final ZaposleniDTO NEW_ZAPOSLENI_DTO = new ZaposleniDTO(NEW_ZAPOSLENI_IME, NEW_ZAPOSLENI_PREZIME,
                        Pol.MUSKI, LocalDate.now(), 52000.0, NEW_ZAPOSLENI_ROLE, "slika", NEW_ZAPOSLENI_USERNAME);
        
        public static final String FAKE_ZAPOSLENI_USERNAME = "Hajducka snaga";
        public static final ZaposleniDTO UPDATE_FAKE_ZAPOSLENI_DTO = new ZaposleniDTO(NEW_ZAPOSLENI_IME, NEW_ZAPOSLENI_PREZIME,
                Pol.MUSKI, LocalDate.now(), 52000.0, "ROLE_MANAGER", "slika", FAKE_ZAPOSLENI_USERNAME);

        public static final Integer DELETED_ZAPOSLENI_ID = 3;
        public static final String DELETED_ZAPOSLENI_IDENTIFICATION_NUMBER = "13245";
        public static final String DELETED_ZAPOSLENI_USERNAME = NEW_ZAPOSLENI_USERNAME;
        
        public static final String DELETE_ZAPOSLENI_UNIT_IME = "Del";
        public static final String DELETE_ZAPOSLENI_UNIT_PREZIME = "Del";
        public static final String DELETE_ZAPOSLENI_UNIT_USERNAME = "deleteUnit";
        public static final ZaposleniDTO DELETE_ZAPOSLENI_UNIT_DTO = new ZaposleniDTO(DELETE_ZAPOSLENI_UNIT_IME, DELETE_ZAPOSLENI_UNIT_PREZIME,
                Pol.MUSKI, LocalDate.now(), 52000.0, NEW_ZAPOSLENI_ROLE, "slika", DELETE_ZAPOSLENI_UNIT_USERNAME);
        
        public static final Zaposleni ZAPOSLENI_TO_DELETE=new Zaposleni().builder().id(ZaposleniConstants.DELETED_ZAPOSLENI_ID).build();
        

        public static final String NEW_ZAPOSLENI_SIFRA = "jkli";
        
        public static final Integer DB_ZAPOSLENI_COUNT = 6;

        public static final String DB_ZAPOSLENI_IDENTIFICATION_NUMBER = "54321";
        public static final Integer DB_ZAPOSLENI_ID = 2;
        public static final String DB_ZAPOSLENI_IME = "Marko";
        public static final String DB_ZAPOSLENI_PREZIME = "Maric";
        public static final String DB_ZAPOSLENI_USERNAME = "user2";
        public static final LocalDate DB_ZAPOSLENI_DATUM_RODJENJA = LocalDate.parse("1990-01-01",
                        DateTimeFormatter.ofPattern("yyyy-MM-dd"));

}
