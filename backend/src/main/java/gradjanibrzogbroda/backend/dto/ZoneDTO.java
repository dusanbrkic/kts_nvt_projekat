package gradjanibrzogbroda.backend.dto;

import gradjanibrzogbroda.backend.config.StorageProperties;
import gradjanibrzogbroda.backend.domain.Sto;
import gradjanibrzogbroda.backend.domain.Zone;
import gradjanibrzogbroda.backend.util.StorageUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ZoneDTO {
	private String naziv;
	private String templateBase64;
	private String id;

	private Set<StoDTO> stolovi;

	public ZoneDTO(Zone zone){
		naziv = zone.getNaziv();
		templateBase64 = StorageUtil.loadAsString(StorageProperties.ZONE_LOCATION, zone.getTemplatePath());
		id = zone.getIdentificationNumber();

		stolovi = zone.getStolovi().stream().map(new Function<Sto, StoDTO>() {
			@Override
			public StoDTO apply(Sto sto) {
				return new StoDTO(sto);
			}
		}).collect(Collectors.toSet());
	}
}
