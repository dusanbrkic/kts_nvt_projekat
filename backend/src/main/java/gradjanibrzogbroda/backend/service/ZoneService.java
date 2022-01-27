package gradjanibrzogbroda.backend.service;

import gradjanibrzogbroda.backend.config.StorageProperties;
import gradjanibrzogbroda.backend.domain.Sto;
import gradjanibrzogbroda.backend.domain.Zone;
import gradjanibrzogbroda.backend.dto.StoDTO;
import gradjanibrzogbroda.backend.dto.ZoneDTO;
import gradjanibrzogbroda.backend.repository.StoRepository;
import gradjanibrzogbroda.backend.repository.ZoneRepository;
import gradjanibrzogbroda.backend.util.StorageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ZoneService {
	@Autowired
	ZoneRepository zoneRepository;

	@Autowired
	StoRepository stoRepository;

	public List<ZoneDTO> getAll() {
		return zoneRepository.findAll().stream().map(new Function<Zone, ZoneDTO>() {
			@Override
			public ZoneDTO apply(Zone zone) {
				return new ZoneDTO(zone, StorageUtil.loadAsString(StorageProperties.ZONE_LOCATION, zone.getTemplatePath()));
			}
		}).collect(Collectors.toList());
	}

	public void update(ZoneDTO zoneDTO) {
		Zone zone = zoneRepository.findOneByIdentificationNumber(zoneDTO.getId());
		zone.updateFields(zoneDTO);

		//delete stolovi that have been deleted
		List<String> ids = stoRepository.selectDeletedTables(zoneDTO.getId(), zoneDTO.getStolovi().stream().map(new Function<StoDTO, String>() {
			@Override
			public String apply(StoDTO stoDTO) {
				return stoDTO.getId();
			}
		}).collect(Collectors.toList()));

		stoRepository.deleteAllByIdentificationNumberIn(ids);
		zone.setStolovi(new HashSet<>());
		//add new stolovi
		if (zoneDTO.getStolovi()!=null) {
			zoneDTO.getStolovi().stream().map(new Function<StoDTO, Object>() {
				@Override
				public Object apply(StoDTO stoDTO) {

					Sto sto = stoRepository.findOneByIdentificationNumber(stoDTO.getId());
					if (sto == null) {
						sto = new Sto().updateFields(stoDTO);
						sto.setZone(zone);
					} else {
						sto.updateFields(stoDTO);
						sto.setZone(zone);
					}
					stoRepository.save(sto);
					zone.getStolovi().add(sto);
					return null;
				}
			}).collect(Collectors.toList());
		}

		zoneRepository.save(zone);
	}

	public void add(ZoneDTO zoneDTO) {
		Zone zone = new Zone();
		zone.updateFields(zoneDTO);

		zone.setStolovi(new HashSet<>());

		StorageUtil.store(zoneDTO.getTemplateBase64(), StorageProperties.ZONE_LOCATION, zone.getTemplatePath());

		zoneRepository.save(zone);
	}

	public void delete(String zoneId) {
		zoneRepository.deleteByIdentificationNumber(zoneId);
	}


}
