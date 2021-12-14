package gradjanibrzogbroda.backend.constants;

import java.util.ArrayList;

import gradjanibrzogbroda.backend.domain.Pice;
import gradjanibrzogbroda.backend.domain.StavkaCenovnika;
import gradjanibrzogbroda.backend.dto.PiceDTO;

public class PiceConstants {
	public static final Integer DB_PICE_ID = 6;
	public static final String DB_PICE_NAZIV = "Coca Cola 0.33l";
	public static final Double DB_PICE_CENA = 125.0;
	
	public static final Integer FAIL_PICE_ID = 555333;
	
	public static final PiceDTO NEW_PICE_DTO = new PiceDTO(11, "Sok od zove 0.33", 250.0);
	public static final Pice NEW_PICE = new Pice();
	public static final Integer NEW_PICE_ID = 11;
	public static final String NEW_PICE_NAZIV = "Sok od zove 0.33";
	public static final Double NEW_PICE_CENA =  250.0;

	public static final PiceDTO UPDATED_PICE_DTO = new PiceDTO(6, "Coca Cola 0.5l", 200.0);
	public static final Integer UPDATED_PICE_ID = 6;
	public static final String UPDATED_PICE_NAZIV = "Coca Cola 0.5l";
	public static final Double UPDATED_PICE_CENA = 200.0;
	
	public static final Integer DELETED_PICE_ID = 8;
}
