package gradjanibrzogbroda.backend.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import gradjanibrzogbroda.backend.domain.Jelo;
import gradjanibrzogbroda.backend.domain.KategorijaJela;
import gradjanibrzogbroda.backend.domain.TipJela;

@Converter
public class JeloConverter implements AttributeConverter<Jelo, String> {
	
	private static final String SEPARATOR = ", ";

	@Override
	public String convertToDatabaseColumn(Jelo jelo) {
		if (jelo == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(jelo.getNaziv());
        sb.append(SEPARATOR);
        sb.append(jelo.getOpis());
        sb.append(SEPARATOR);
        sb.append(jelo.getKategorijaJela().toString());
        sb.append(SEPARATOR);
        sb.append(jelo.getTipJela().toString());
        sb.append(SEPARATOR);
        sb.append(jelo.getVremePripremeMils().toString());
        sb.append(SEPARATOR);
        sb.append(jelo.getTrenutnaCena().toString());

        return sb.toString();
	}

	@Override
	public Jelo convertToEntityAttribute(String dbJelo) {
		if(dbJelo == null || dbJelo.isEmpty()) {
            return null;
        }

        String[] pieces = dbJelo.split(SEPARATOR);

        if (pieces == null || pieces.length == 0) {
            return null;
        }
        
        Jelo jelo=new Jelo();
        
        System.out.println(dbJelo);

        jelo.setNaziv(pieces[0]);
        jelo.setOpis(pieces[1]);
        jelo.setKategorijaJela(KategorijaJela.valueOf(pieces[2]));
        jelo.setTipJela(TipJela.valueOf(pieces[3]));
        jelo.setVremePripremeMils(Long.parseLong(pieces[4]));
        jelo.setTrenutnaCena(Double.parseDouble(pieces[5]));

        return jelo;
	}

}
