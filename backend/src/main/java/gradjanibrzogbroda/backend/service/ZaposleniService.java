package gradjanibrzogbroda.backend.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import gradjanibrzogbroda.backend.config.StorageProperties;
import gradjanibrzogbroda.backend.domain.Plata;
import gradjanibrzogbroda.backend.domain.Role;
import gradjanibrzogbroda.backend.dto.PlataDTO;
import gradjanibrzogbroda.backend.dto.ZaposleniDTO;
import gradjanibrzogbroda.backend.exceptions.UserAlreadyExistsException;
import gradjanibrzogbroda.backend.exceptions.UserNotFoundException;

import gradjanibrzogbroda.backend.pages.sortFields.ZaposleniSortFields;
import gradjanibrzogbroda.backend.repository.RoleRepository;
import gradjanibrzogbroda.backend.util.StorageUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import gradjanibrzogbroda.backend.domain.Zaposleni;
import gradjanibrzogbroda.backend.repository.ZaposleniRepository;

@Service
public class ZaposleniService {

	@Autowired
	private ZaposleniRepository zaposleniRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<Zaposleni> findAll() {
		return zaposleniRepository.findAll();
	}

	public ZaposleniDTO findOneByUsername(String username) throws UserNotFoundException {
		Zaposleni zaposleni = zaposleniRepository.findOneByUsername(username);

		if (zaposleni == null) {
			throw new UserNotFoundException();
		}

		ZaposleniDTO zaposleniDTO = new ZaposleniDTO(zaposleni);

		return zaposleniDTO;
	}

	public Map<String, Object> getAllPaged(Integer page, Integer size, String sortByString, Boolean sortDesc, String pretragaIme, String pretragaPrezime, String filterTipZaposlenjaString) {
		ZaposleniSortFields sortBy = null;
		if (sortByString.isEmpty()) {
			sortBy = ZaposleniSortFields.PREZIME;
		} else {
			sortBy = ZaposleniSortFields.valueOf(sortByString);
		}

		//get the corresponding Zaposleni Property
		String sortByAttr = Zaposleni.Fields.prezime;
		if (sortBy == ZaposleniSortFields.IME) {
			sortByAttr = Zaposleni.Fields.ime;
		} else if (sortBy == ZaposleniSortFields.PREZIME) {
			sortByAttr = Zaposleni.Fields.prezime;
		} else if (sortBy == ZaposleniSortFields.DATUMRODJENJA) {
			sortByAttr = Zaposleni.Fields.datumRodjenja;
		} else if (sortBy == ZaposleniSortFields.TRENUTNAPLATA) {
			sortByAttr = Zaposleni.Fields.trenutnaPlata;
		}


		Set<String> filterTipZaposlenja = null;
		Set<Role> allRoles = new HashSet<>(roleRepository.findAll());

		if (filterTipZaposlenjaString.isEmpty()) {
			filterTipZaposlenja = allRoles.stream().map(new Function<Role, String>() {
				@Override
				public String apply(Role role) {
					return role.getRole();
				}
			}).collect(Collectors.toSet());
		} else {
			filterTipZaposlenja = new HashSet<>(Arrays.asList(filterTipZaposlenjaString.split(",")));
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

		List<ZaposleniDTO> zaposleniDTOS = queryResult.getContent().stream().map(new Function<Zaposleni, ZaposleniDTO>() {
			@SneakyThrows
			@Override
			public ZaposleniDTO apply(Zaposleni zaposleni) {
				return new ZaposleniDTO(zaposleni);
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

	public void deleteZaposleni(String username) throws UserNotFoundException {
		try {
			zaposleniRepository.deleteById(zaposleniRepository.findOneByUsername(username).getId());
		} catch (EmptyResultDataAccessException e) {
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
				return new ZaposleniDTO(zaposleni);
			}
		}).collect(Collectors.toList());

		return zaposleniDTOs;
	}

	public Zaposleni updateZaposleni(ZaposleniDTO zaposleniDTO)
			throws UserNotFoundException {

		Zaposleni zaposleni = zaposleniRepository.findOneByUsername(zaposleniDTO.getUsername());

		if (zaposleni == null) {
			throw new UserNotFoundException();
		}

		zaposleni.updateFields(zaposleniDTO, roleRepository.findByRole(zaposleniDTO.getRoleName()));
		zaposleni = zaposleniRepository.save(zaposleni);

		StorageUtil.store(zaposleniDTO.getSlikaString(), StorageProperties.ZAPOSLENI_LOCATION, zaposleni.getNazivSlike());

		return zaposleni;
	}

	public Zaposleni addZaposleni(ZaposleniDTO zaposleniDTO) throws UserAlreadyExistsException {

		Zaposleni zaposleni = zaposleniRepository.findOneByUsername(zaposleniDTO.getUsername());


		if (zaposleni != null) {
			throw new UserAlreadyExistsException();
		} else {
			String psw;
			if (zaposleniDTO.getRoleName().equals("ROLE_GLAVNI_KUVAR") ||
					zaposleniDTO.getRoleName().equals("ROLE_MANAGER") ||
					zaposleniDTO.getRoleName().equals("ROLE_ADMIN")
			) {
				psw = "passje";
			} else {
				psw = "identification";
			}

			zaposleni = new Zaposleni();
			zaposleni.updateFields(zaposleniDTO, roleRepository.findByRole(zaposleniDTO.getRoleName()));
			zaposleni.setPassword(passwordEncoder.encode(psw));
			zaposleni = zaposleniRepository.save(zaposleni);
		}

		StorageUtil.store(zaposleniDTO.getSlikaString(), StorageProperties.ZAPOSLENI_LOCATION, zaposleni.getNazivSlike());
		return zaposleni;
	}

	public Zaposleni findOneById(Integer zaposleniId) throws UserNotFoundException {
		Zaposleni zaposleni = zaposleniRepository.findOneById(zaposleniId);

		if (zaposleni == null) {
			throw new UserNotFoundException();
		}
		return zaposleni;
	}

	;
}