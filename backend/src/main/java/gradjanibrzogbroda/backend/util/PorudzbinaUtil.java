package gradjanibrzogbroda.backend.util;

import gradjanibrzogbroda.backend.domain.*;

public class PorudzbinaUtil {

    public boolean promeniStatusPorudzbine(Porudzbina porudzbina, StatusJela statusJela, StatusPica statusPica){
        for (JeloPorudzbine jp:porudzbina.getJelaPorudzbine()) {
            if (jp.getStatusJela().ordinal() < statusJela.ordinal()){
                return false;
            }
        }

        for (PicePorudzbine pp:porudzbina.getPicePorudzbine()) {
            if (pp.getStatusPica().ordinal() < statusPica.ordinal()){
                return false;
            }
        }
        return true;
    }

}
