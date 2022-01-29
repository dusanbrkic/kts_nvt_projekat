package gradjanibrzogbroda.backend.constants;

import gradjanibrzogbroda.backend.domain.Jelo;
import gradjanibrzogbroda.backend.domain.KategorijaJela;
import gradjanibrzogbroda.backend.domain.PredlogStatus;
import gradjanibrzogbroda.backend.domain.PredlogTip;
import gradjanibrzogbroda.backend.domain.TipJela;
import gradjanibrzogbroda.backend.dto.JeloDTO;

public class PredlogConstants {
	
	public static final Integer WRONT_FORMAT_PREDLOG_DTO_ID=1;
	public static final PredlogTip WRONG_FORMAT_PREDLOG_DTO_TIP_IZMENE=PredlogTip.IZMENA;
	public static final PredlogStatus WRONG_FORMAT_PREDLOG_DTO_STATUS=PredlogStatus.NOV;
	public static final Integer WRONG_FORMAT_PREDLOG_DTO_STARO_JELO_ID= null;
	public static final JeloDTO WRONT_FORMAT_PREDLOG_DTO_NOVO_JELO=null;
	
	public static final Integer WRONT_FORMAT_PREDLOG_DTO_ID_BRISANJE=2;
	public static final PredlogTip WRONG_FORMAT_PREDLOG_DTO_TIP_IZMENE_BRISANJE=PredlogTip.BRISANJE;
	public static final PredlogStatus WRONG_FORMAT_PREDLOG_DTO_STATUS_BRISANJE=PredlogStatus.NOV;
	public static final Integer WRONG_FORMAT_PREDLOG_DTO_STARO_JELO_ID_BRISANJE= null;
	public static final JeloDTO WRONT_FORMAT_PREDLOG_DTO_NOVO_JELO_BRISANJE=new JeloDTO();
	
	public static final Integer WRONT_FORMAT_PREDLOG_DTO_ID_DODAVANJE=3;
	public static final PredlogTip WRONG_FORMAT_PREDLOG_DTO_TIP_IZMENE_DODAVANJE=PredlogTip.DODAVANJE;
	public static final PredlogStatus WRONG_FORMAT_PREDLOG_DTO_STATUS_DODAVANJE=PredlogStatus.NOV;
	public static final Integer WRONG_FORMAT_PREDLOG_DTO_STARO_JELO_ID_DODAVANJE= 1;
	public static final JeloDTO WRONT_FORMAT_PREDLOG_DTO_NOVO_JELO_DODAVANJE=null;
	
	public static final Integer PREDLOG_DTO_ID_IZMENA=4;
	public static final PredlogTip PREDLOG_DTO_TIP_IZMENE_IZMENA=PredlogTip.IZMENA;
	public static final PredlogStatus PREDLOG_DTO_STATUS_IZMENA=PredlogStatus.NOV;
	public static final Integer PREDLOG_DTO_STARO_JELO_ID_IZMENA= 2;
	
	public static final String PREDLOG_DTO_NOVO_JELO_IZMENA_NAZIV="Naziv 1";
	public static final String PREDLOG_DTO_NOVO_JELO_OPIS="OPIS 1";
	public static final Double PREDLOG_DTO_NOVO_JELO_IZMENA_TRENUTNA_CENA=220.00;
	public static final Long PREDLOG_DTO_NOVO_JELO_IZMENA_VREME_PRIPREME=30000l;
	public static final KategorijaJela PREDLOG_DTO_NOVO_JELO_IZMENA_KATEGORIJA= KategorijaJela.DEZERT;
	public static final TipJela PREDLOG_DTO_NOVO_JELO_IZMENA_TIP=TipJela.BASIC;
	public static final JeloDTO PREDLOG_DTO_NOVO_JELO_DTO_IZMENA=new JeloDTO().builder()
			.naziv(PREDLOG_DTO_NOVO_JELO_IZMENA_NAZIV)
			.opis(PREDLOG_DTO_NOVO_JELO_OPIS)
			.trenutnaCena(PREDLOG_DTO_NOVO_JELO_IZMENA_TRENUTNA_CENA)
			.vremePripremeMils(PREDLOG_DTO_NOVO_JELO_IZMENA_VREME_PRIPREME)
			.kategorijaJela(PREDLOG_DTO_NOVO_JELO_IZMENA_KATEGORIJA)
			.tipJela(PREDLOG_DTO_NOVO_JELO_IZMENA_TIP)
			.picBase64("")
			.build();
	
	public static final String PREDLOG_DTO_STARO_JELO_IZMENA_NAZIV="Naziv Staro 1";
	public static final String PREDLOG_DTO_STARO_JELO_OPIS="OPIS staro 1";
	public static final Double PREDLOG_DTO_STARO_JELO_IZMENA_TRENUTNA_CENA=220.00;
	public static final Long PREDLOG_DTO_STARO_JELO_IZMENA_VREME_PRIPREME=30000l;
	public static final KategorijaJela PREDLOG_DTO_STARO_JELO_IZMENA_KATEGORIJA= KategorijaJela.DEZERT;
	public static final TipJela PREDLOG_DTO_STARO_JELO_IZMENA_TIP=TipJela.BASIC;
	
	public static final Integer PREDLOG_DTO_ID_BRISANJE=5;
	public static final PredlogTip PREDLOG_DTO_TIP_IZMENE_BRISANJE=PredlogTip.BRISANJE;
	public static final PredlogStatus PREDLOG_DTO_STATUS_BRISANJE=PredlogStatus.ODBIJEN;
	public static final Integer PREDLOG_DTO_STARO_JELO_ID_BRISANJE= 3;
	public static final JeloDTO PREDLOG_DTO_NOVO_JELO_BRISANJE=null;
	
	public static final String PREDLOG_DTO_STARO_JELO_BRISANJE_NAZIV="Naziv Staro 2";
	public static final String PREDLOG_DTO_STARO_JELO_BRISANJE_OPIS="OPIS staro 2";
	public static final Double PREDLOG_DTO_STARO_JELO_BRISANJE_TRENUTNA_CENA=220.00;
	public static final Long PREDLOG_DTO_STARO_JELO_BRISANJE_VREME_PRIPREME=30000l;
	public static final KategorijaJela PREDLOG_DTO_STARO_JELO_BRISANJE_KATEGORIJA= KategorijaJela.PREDJELO;
	public static final TipJela PREDLOG_DTO_STARO_JELO_BRISANJE_TIP=TipJela.LUX;
	
	public static final Integer PREDLOG_DTO_ID_DODAVANJE=6;
	public static final PredlogTip PREDLOG_DTO_TIP_IZMENE_DODAVANJE=PredlogTip.DODAVANJE;
	public static final PredlogStatus PREDLOG_DTO_STATUS_DODAVANJE=PredlogStatus.ODOBREN;
	public static final Integer PREDLOG_DTO_STARO_JELO_ID_DODAVANJE= null;
	
	public static final String PREDLOG_DTO_NOVO_JELO_DODAVANJE_NAZIV="Naziv 2";
	public static final String PREDLOG_DTO_NOVO_JELO_DODAVANJE_OPIS="OPIS 2";
	public static final Double PREDLOG_DTO_NOVO_JELO_DODAVANJE_TRENUTNA_CENA=220.00;
	public static final Long PREDLOG_DTO_NOVO_JELO_DODAVANJE_VREME_PRIPREME=30000l;
	public static final KategorijaJela PREDLOG_DTO_NOVO_JELO_DODAVANJE_KATEGORIJA= KategorijaJela.GLAVNO_JELO;
	public static final TipJela PREDLOG_DTO_NOVO_JELO_DODAVANJE_TIP=TipJela.BUDGET;
	public static final JeloDTO PREDLOG_DTO_NOVO_JELO_DTO_DODAVANJE=new JeloDTO().builder()
			.naziv(PREDLOG_DTO_NOVO_JELO_DODAVANJE_NAZIV)
			.opis(PREDLOG_DTO_NOVO_JELO_DODAVANJE_OPIS)
			.trenutnaCena(PREDLOG_DTO_NOVO_JELO_DODAVANJE_TRENUTNA_CENA)
			.vremePripremeMils(PREDLOG_DTO_NOVO_JELO_DODAVANJE_VREME_PRIPREME)
			.kategorijaJela(PREDLOG_DTO_NOVO_JELO_DODAVANJE_KATEGORIJA)
			.tipJela(PREDLOG_DTO_NOVO_JELO_DODAVANJE_TIP)
			.picBase64("")
			.build();
	
	public static final Integer NON_EXISTING_STARO_JELO_ID=55;
	
	public static final Integer PREDLOG_IZMENA_1_ID=1;
	public static final PredlogTip PREDLOG_IZMENA_1_TIP=PredlogTip.IZMENA; 
	public static final PredlogStatus PREDLOG_IZMENA_1_STATUS=PredlogStatus.NOV;
	
	public static final Integer PREDLOG_IZMENA_2_ID=2;
	public static final PredlogTip PREDLOG_IZMENA_2_TIP=PredlogTip.IZMENA; 
	public static final PredlogStatus PREDLOG_IZMENA_2_STATUS=PredlogStatus.NOV;
	
	public static final Integer PREDLOG_IZMENA_3_ID=3;
	public static final PredlogTip PREDLOG_IZMENA_3_TIP=PredlogTip.IZMENA;
	public static final PredlogStatus PREDLOG_IZMENA_3_STATUS=PredlogStatus.NOV;
	
	public static final Integer PREDLOG_IZMENA_4_ID=4;
	public static final PredlogTip PREDLOG_IZMENA_4_TIP=PredlogTip.IZMENA;
	public static final PredlogStatus PREDLOG_IZMENA_4_STATUS=PredlogStatus.ODBIJEN;
	
	public static final Integer PREDLOG_IZMENA_5_ID=5;
	public static final PredlogTip PREDLOG_IZMENA_5_TIP=PredlogTip.IZMENA;
	public static final PredlogStatus PREDLOG_IZMENA_5_STATUS=PredlogStatus.ODOBREN;
	
	public static final Integer PREDLOG_BRISANJE_1_ID=6;
	public static final PredlogTip PREDLOG_BRISANJE_1_TIP=PredlogTip.BRISANJE;
	public static final PredlogStatus PREDLOG_BRISANJE_1_STATUS=PredlogStatus.NOV;
	
	public static final Integer PREDLOG_DODAVANJE_1_ID=7;
	public static final PredlogTip PREDLOG_DODAVANJE_1_TIP=PredlogTip.DODAVANJE; 
	public static final PredlogStatus PREDLOG_DODAVANJE_1_STATUS=PredlogStatus.NOV;
	
	public static final int PAGE_SIZE_1=5;
	public static final int PAGE_1=0;
	
	public static final int PAGE_SIZE_2=3;
	public static final int PAGE_2=0;
	
	public static final int PAGE_SIZE_3=3;
	public static final int PAGE_3=1;
	
	public static final int PAGE_SIZE_4=5;
	public static final int PAGE_4=0;
	public static final PredlogTip PAGE_TIP_4=PredlogTip.IZMENA;

}
