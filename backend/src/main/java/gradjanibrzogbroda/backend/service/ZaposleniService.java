package gradjanibrzogbroda.backend.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import gradjanibrzogbroda.backend.domain.GlavniKuvar;
import gradjanibrzogbroda.backend.domain.Konobar;
import gradjanibrzogbroda.backend.domain.Kuvar;
import gradjanibrzogbroda.backend.domain.Menadzer;
import gradjanibrzogbroda.backend.domain.Pice;
import gradjanibrzogbroda.backend.domain.Plata;
import gradjanibrzogbroda.backend.domain.Pol;
import gradjanibrzogbroda.backend.domain.Sanker;
import gradjanibrzogbroda.backend.domain.StavkaCenovnika;
import gradjanibrzogbroda.backend.domain.TipZaposlenja;
import gradjanibrzogbroda.backend.dto.PlataDTO;
import gradjanibrzogbroda.backend.dto.ZaposleniDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import gradjanibrzogbroda.backend.domain.Zaposleni;
import gradjanibrzogbroda.backend.repository.ZaposleniRepository;
import gradjanibrzogbroda.exception.UserAlreadyExistsException;
import gradjanibrzogbroda.exception.UserNotFoundException;

@Service
public class ZaposleniService {

	@Autowired
	private ZaposleniRepository zaposleniRepository;

	public List<Zaposleni> findAll() {
		return zaposleniRepository.findAll();
	}

	public Zaposleni findOneByIdentificationNumber(String id) throws UserNotFoundException {
		Zaposleni zaposleni = zaposleniRepository.findOneByIdentificationNumber(id);

		if (zaposleni == null) {
			throw new UserNotFoundException();
		}
		return zaposleni;
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
			@Override
			public ZaposleniDTO apply(Zaposleni zaposleni) {
				return new ZaposleniDTO(zaposleni);
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
		return zaposleni;
	}

	public Zaposleni addZaposleni(ZaposleniDTO zaposleniDTO) throws UserAlreadyExistsException {

		Zaposleni zaposleni = zaposleniRepository.findOneByIdentificationNumber(zaposleniDTO.getIdentificationNumber());

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