package gradjanibrzogbroda.backend.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import gradjanibrzogbroda.backend.config.StorageProperties;
import gradjanibrzogbroda.backend.domain.GlavniKuvar;
import gradjanibrzogbroda.backend.domain.Konobar;
import gradjanibrzogbroda.backend.domain.Kuvar;
import gradjanibrzogbroda.backend.domain.Menadzer;
import gradjanibrzogbroda.backend.domain.Plata;
import gradjanibrzogbroda.backend.domain.Sanker;
import gradjanibrzogbroda.backend.domain.TipZaposlenja;
import gradjanibrzogbroda.backend.dto.PlataDTO;
import gradjanibrzogbroda.backend.dto.ZaposleniDTO;
import gradjanibrzogbroda.backend.exceptions.UserAlreadyExistsException;
import gradjanibrzogbroda.backend.exceptions.UserNotFoundException;

import gradjanibrzogbroda.backend.pages.sortFields.ZaposleniSortFields;
import gradjanibrzogbroda.backend.util.StorageUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import gradjanibrzogbroda.backend.domain.Zaposleni;
import gradjanibrzogbroda.backend.repository.ZaposleniRepository;

@Service
public class ZaposleniService {

	@Autowired
	private ZaposleniRepository zaposleniRepository;

	public List<Zaposleni> findAll() {
		return zaposleniRepository.findAll();
	}

	public ZaposleniDTO findOneByIdentificationNumber(String id) throws UserNotFoundException{
		Zaposleni zaposleni = zaposleniRepository.findOneByIdentificationNumber(id);

		if (zaposleni == null) {
			throw new UserNotFoundException();
		}

		ZaposleniDTO zaposleniDTO = new ZaposleniDTO(zaposleni, StorageUtil.loadAsString(StorageProperties.ZAPOSLENI_LOCATION, zaposleni.getNazivSlike()));

		return zaposleniDTO;
	}

	public Map<String, Object> getAllPaged(Integer page, Integer size, String sortByString, Boolean sortDesc, String pretragaIme, String pretragaPrezime, String filterTipZaposlenjaString) {
		ZaposleniSortFields sortBy = null;
		if(sortByString.isEmpty()){
			sortBy = ZaposleniSortFields.PREZIME;
		} else {
			sortBy = ZaposleniSortFields.valueOf(sortByString);
		}

		//get the corresponding Zaposleni Property
		String sortByAttr = Zaposleni.Fields.prezime;
		if (sortBy == ZaposleniSortFields.IME){
			sortByAttr = Zaposleni.Fields.ime;
		} else if(sortBy == ZaposleniSortFields.PREZIME){
			sortByAttr = Zaposleni.Fields.prezime;
		} else if (sortBy == ZaposleniSortFields.DATUMRODJENJA){
			sortByAttr = Zaposleni.Fields.datumRodjenja;
		} else if(sortBy == ZaposleniSortFields.TRENUTNAPLATA){
			sortByAttr = Zaposleni.Fields.trenutnaPlata;
		}


		Set<TipZaposlenja> filterTipZaposlenja = null;
		if (filterTipZaposlenjaString.isEmpty()) {
			filterTipZaposlenja = new HashSet<>();
			filterTipZaposlenja.add(TipZaposlenja.KONOBAR);
			filterTipZaposlenja.add(TipZaposlenja.GLAVNI_KUVAR);
			filterTipZaposlenja.add(TipZaposlenja.KUVAR);
			filterTipZaposlenja.add(TipZaposlenja.MENADZER);
			filterTipZaposlenja.add(TipZaposlenja.SANKER);
		}
		else {
			filterTipZaposlenja = Arrays.asList(filterTipZaposlenjaString.split(",")).stream().map(new Function<String, TipZaposlenja>() {
				@Override
				public TipZaposlenja apply(String tipZaposlenjaString) {
					return TipZaposlenja.valueOf(tipZaposlenjaString);
				}
			}).collect(Collectors.toSet());
		}

		pretragaIme = "%" + pretragaIme + "%";
		pretragaPrezime = "%" + pretragaPrezime + "%";

		Sort sort = Sort.by(sortByAttr);
		if (sortDesc)
			sort = sort.descending();
		else
			sort = sort.ascending();

		Pageable pageable = PageRequest.of(page, size, sort);
		Page<Zaposleni> queryResult = zaposleniRepository.getAllPaged(pageable, pretragaIme, pretragaPrezime, filterTipZaposlenja);

		List<ZaposleniDTO> zaposleniDTOS =  queryResult.getContent().stream().map(new Function<Zaposleni, ZaposleniDTO>() {
			@SneakyThrows
			@Override
			public ZaposleniDTO apply(Zaposleni zaposleni) {
				String slikaString = StorageUtil.loadAsString(StorageProperties.ZAPOSLENI_LOCATION, zaposleni.getNazivSlike());
				return new ZaposleniDTO(zaposleni, slikaString);
			}
		}).collect(Collectors.toList());

		Map<String, Object> mapResult = new HashMap<>();
		mapResult.put("zaposleni", zaposleniDTOS);
		mapResult.put("currentPage", queryResult.getNumber());
		mapResult.put("totalItems", queryResult.getTotalElements());
		mapResult.put("totalPages", queryResult.getTotalPages());

		return mapResult;

	}

	public Zaposleni updateAddZaposleni(Zaposleni z) {
		return zaposleniRepository.save(z);
	}

	public void deleteZaposleni(String idNum) throws UserNotFoundException {
		try{
			zaposleniRepository.deleteById(zaposleniRepository.findOneByIdentificationNumber(idNum).getId());
		} catch(EmptyResultDataAccessException e) {
			throw new UserNotFoundException();
		}
	}

	public Zaposleni izmeniPlatu(PlataDTO dto) {
		Zaposleni zaposleni = zaposleniRepository.findOneById(dto.getZaposleniId());
		zaposleni.setTrenutnaPlata(dto.getVisinaPlate());
		for (Plata p : zaposleni.getPlate()) {
			if (p.getKrajVazenja() == null) {
				// p.setKrajVazenja(dto.getPocetakVazenja().minusDays(1));
				p.setKrajVazenja(LocalDate.now());
			}
		}

		Plata plata = Plata.builder()
				.zaposleni(zaposleni)
				.visinaPlate(dto.getVisinaPlate())
				.pocetakVazenja(LocalDate.now().plusDays(1))
				.build();
		zaposleni.getPlate().add(plata);
		return zaposleniRepository.save(zaposleni);
	}

	public List<ZaposleniDTO> getAllZaposleni() {
		List<Zaposleni> zaposleni = zaposleniRepository.findAll();

		List<ZaposleniDTO> zaposleniDTOs = zaposleni.stream().map(new Function<Zaposleni, ZaposleniDTO>() {
			@SneakyThrows
			@Override
			public ZaposleniDTO apply(Zaposleni zaposleni) {
				String slikaString = StorageUtil.loadAsString(StorageProperties.ZAPOSLENI_LOCATION, zaposleni.getNazivSlike());
				return new ZaposleniDTO(zaposleni, slikaString);
			}
		}).collect(Collectors.toList());

		return zaposleniDTOs;
	}

	public Zaposleni updateZaposleni(ZaposleniDTO zaposleniDTO)
			throws UserNotFoundException {
				
		Zaposleni zaposleni = zaposleniRepository.findOneByIdentificationNumber(zaposleniDTO.getIdentificationNumber());

		if (zaposleni == null) {
			throw new UserNotFoundException();
		}

		zaposleni.updateFields(zaposleniDTO);
		zaposleni = zaposleniRepository.save(zaposleni);

		StorageUtil.store(zaposleniDTO.getSlikaString(), StorageProperties.ZAPOSLENI_LOCATION, zaposleni.getNazivSlike());

		return zaposleni;
	}

	public Zaposleni addZaposleni(ZaposleniDTO zaposleniDTO) throws UserAlreadyExistsException {

		Zaposleni zaposleni = zaposleniRepository.findOneByIdentificationNumber(zaposleniDTO.getIdentificationNumber());

		StorageUtil.store(zaposleniDTO.getSlikaString(), StorageProperties.ZAPOSLENI_LOCATION, zaposleni.getNazivSlike());

		if (zaposleni != null) {
			throw new UserAlreadyExistsException();
		} else {
			if (zaposleniDTO.getTipZaposlenja() == TipZaposlenja.GLAVNI_KUVAR)
				zaposleni = zaposleniRepository.save(new GlavniKuvar(zaposleniDTO));
			if (zaposleniDTO.getTipZaposlenja() == TipZaposlenja.KONOBAR)
				zaposleni = zaposleniRepository.save(new Konobar(zaposleniDTO));
			if (zaposleniDTO.getTipZaposlenja() == TipZaposlenja.KUVAR)
				zaposleni = zaposleniRepository.save(new Kuvar(zaposleniDTO));
			if (zaposleniDTO.getTipZaposlenja() == TipZaposlenja.MENADZER)
				zaposleni = zaposleniRepository.save(new Menadzer(zaposleniDTO));
			if (zaposleniDTO.getTipZaposlenja() == TipZaposlenja.SANKER)
				zaposleni = zaposleniRepository.save(new Sanker(zaposleniDTO));
			return zaposleni;
		}
	}

    public Zaposleni findOneById(Integer zaposleniId) throws UserNotFoundException {
        Zaposleni zaposleni = zaposleniRepository.findOneById(zaposleniId);

		if (zaposleni == null) {
			throw new UserNotFoundException();
		}
		return zaposleni;
    };
}