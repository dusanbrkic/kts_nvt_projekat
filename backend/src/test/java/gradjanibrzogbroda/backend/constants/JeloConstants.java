package gradjanibrzogbroda.backend.constants;

import gradjanibrzogbroda.backend.domain.Jelo;
import gradjanibrzogbroda.backend.domain.KategorijaJela;
import gradjanibrzogbroda.backend.domain.TipJela;
import gradjanibrzogbroda.backend.dto.JeloDTO;

public class JeloConstants {
	
	public static final long FIND_ALL_NUMBER_OF_ITEMS = 5;
	
	public static final Integer DB_JELO_ID = 1;
	public static final String DB_JELO_NAZIV = "Pljeskavica";
	public static final Double DB_JELO_CENA = 240.0;
	
	public static final Integer FAIL_JELO_ID = 342;
	
	public static final JeloDTO NEW_JELO_DTO = new JeloDTO(12, "Jelo test", 200.0, (long) 300000, "Opis test", KategorijaJela.DEZERT, TipJela.BASIC);
	public static final Jelo NEW_JELO = new Jelo();
	public static final Integer NEW_JELO_ID = 12;
	public static final String NEW_JELO_NAZIV = "Jelo test";
	public static final Double NEW_JELO_CENA =  200.0;
	public static final Long NEW_JELO_VREME_PRIPREME =  (long) 300000;
	public static final String NEW_JELO_OPIS = "Opis test";
	public static final KategorijaJela NEW_JELO_KATEGORIJA=KategorijaJela.DEZERT;
	public static final TipJela NEW_JELO_TIP=TipJela.BASIC;
	
	public static final JeloDTO UPDATED_JELO_DTO = new JeloDTO(1, "Jelo update", 67.0, (long) 300000, "Opis test", KategorijaJela.DEZERT, TipJela.BASIC);
	public static final Jelo UPDATED_JELO = new Jelo();
	public static final Integer UPDATED_JELO_ID = 1;
	public static final String UPDATED_JELO_NAZIV = "Jelo update";
	public static final Double UPDATED_JELO_CENA =  67.0;
	
	public static final Integer DELETED_JELO_ID = 5;
	
	public static final Integer PRICE_CHANGE_JELO_ID = 3;
	public static final Double PRICE_CHANGE_JELO_CENA =  22.0;
	
	public static final String FIND_JELO_NAZIV = "Pljeskavica";
	
	public static final TipJela FIND_JELO_TIP=TipJela.BASIC;
	public static final long FIND_TIP_NUMBER_OF_ITEMS = 3;
	
	public static final KategorijaJela FIND_JELO_KATEGORIJA=KategorijaJela.GLAVNO_JELO;
	public static final long FIND_KATEGORIJA_NUMBER_OF_ITEMS = 3;

}
