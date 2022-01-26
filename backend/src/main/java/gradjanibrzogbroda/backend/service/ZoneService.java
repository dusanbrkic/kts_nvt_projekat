package gradjanibrzogbroda.backend.service;

import gradjanibrzogbroda.backend.config.StorageProperties;
import gradjanibrzogbroda.backend.domain.Zone;
import gradjanibrzogbroda.backend.dto.ZoneDTO;
import gradjanibrzogbroda.backend.repository.ZoneRepository;
import gradjanibrzogbroda.backend.util.StorageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ZoneService {
	@Autowired
	ZoneRepository zoneRepository;

	public List<ZoneDTO> getAll(){
		return zoneRepository.findAll().stream().map(new Function<Zone, ZoneDTO>() {
			@Override
			public ZoneDTO apply(Zone zone) {
				return new ZoneDTO(zone, StorageUtil.loadAsString(StorageProperties.ZONE_LOCATION, zone.getTemplatePath()));
			}
		}).collect(Collectors.toList());
	}

}
