package gradjanibrzogbroda.backend.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gradjanibrzogbroda.backend.domain.JeloPorudzbine;
import gradjanibrzogbroda.backend.domain.PicePorudzbine;
import gradjanibrzogbroda.backend.domain.Porudzbina;
import gradjanibrzogbroda.backend.domain.StavkaCenovnika;
import gradjanibrzogbroda.backend.domain.TipJela;
import gradjanibrzogbroda.backend.repository.PorudzbinaRepository;
import gradjanibrzogbroda.backend.repository.StavkaCenovnikaRepository;

@Service
public class ReportService {
	
	private static double PICE_TROSAK=0.5;
	private static double BUDGET_TROSAK=0.75;
	private static double BASIC_TROSAK=0.7;
	private static double BUSINESS_TROSAK=0.65;
	private static double LUX_TROSAK=0.6;
	
	@Autowired
	private PorudzbinaRepository poruRep;
	
	@Autowired
	private StavkaCenovnikaRepository stCenRepo;
	
	public double getFinancialReport(int type, LocalDateTime start, LocalDateTime end) {
		double gain = 0;
		double loss = 0;
		double profit = 0;
		ArrayList<Porudzbina> porudzbine = (ArrayList<Porudzbina>) poruRep.findAllNaplaceneInPeriod(start, end);
		double tempGain;
		
		for(Porudzbina p : porudzbine) {
			ArrayList<StavkaCenovnika> stavke = (ArrayList<StavkaCenovnika>) stCenRepo.findCenovnik(p.getDatumVreme());
			HashMap<Integer, Double> cenovnik = new HashMap<Integer, Double>();
			for(StavkaCenovnika st : stavke) {
				cenovnik.put(st.getArtikal().getId(), st.getCena());
				
			}
			
			for(PicePorudzbine pp : p.getPicePorudzbine()) {
				tempGain = cenovnik.get(pp.getPice().getId());
				loss+=(tempGain*pp.getKolicina())* PICE_TROSAK;
				gain+=tempGain*pp.getKolicina();
				
			}
			for(JeloPorudzbine jp : p.getJelaPorudzbine()) {
				double lossCoef = 0.75;
				switch(jp.getJelo().getTipJela()) {
					case BUDGET : lossCoef = BUDGET_TROSAK;
						break;
					case BASIC : lossCoef = BASIC_TROSAK;
						break;
					case BUSINESS : lossCoef = BUSINESS_TROSAK;
						break;
					case LUX : lossCoef = LUX_TROSAK;
						break;
				}
				tempGain = cenovnik.get(jp.getJelo().getId());
				loss+=(tempGain*jp.getKolicina())* lossCoef;
				gain+=tempGain*jp.getKolicina();
			}
			
			//gain+= p.getUkupnaCena();
		}
		profit = gain - loss;
		switch(type) {
			case 1: return gain;
			case 2: return loss;
			default: return profit;
		}
	} 
}
