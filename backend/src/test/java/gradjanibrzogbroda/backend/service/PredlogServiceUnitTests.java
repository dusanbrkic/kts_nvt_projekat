package gradjanibrzogbroda.backend.service;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import gradjanibrzogbroda.backend.constants.PredlogConstants;
import gradjanibrzogbroda.backend.domain.Jelo;
import gradjanibrzogbroda.backend.domain.Predlog;
import gradjanibrzogbroda.backend.dto.PredlogDTO;
import gradjanibrzogbroda.backend.exceptions.JeloNotFoundException;
import gradjanibrzogbroda.backend.exceptions.PredlogWrongFormatException;
import gradjanibrzogbroda.backend.repository.JeloRepository;
import gradjanibrzogbroda.backend.repository.PredlogRepository;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.MockitoAnnotations.openMocks;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PredlogServiceUnitTests extends AbstractTestNGSpringContextTests {
	
	@Mock
    private PredlogRepository predlogRepositoryMock;
	
	@Mock
	private JeloRepository jeloRepositoryMock;
	
	@InjectMocks
	@Autowired
	private PredlogService predlogService;
	
	@BeforeMethod
	public void initMocks() {
		openMocks(this);
		
		//izmena mock
		Jelo staroJeloIzmena=new Jelo();
		staroJeloIzmena.setId(PredlogConstants.PREDLOG_DTO_STARO_JELO_ID_IZMENA);
		staroJeloIzmena.setNaziv(PredlogConstants.PREDLOG_DTO_STARO_JELO_IZMENA_NAZIV);
		staroJeloIzmena.setOpis(PredlogConstants.PREDLOG_DTO_STARO_JELO_OPIS);
		staroJeloIzmena.setObrisan(false);
		staroJeloIzmena.setKategorijaJela(PredlogConstants.PREDLOG_DTO_STARO_JELO_IZMENA_KATEGORIJA);
		staroJeloIzmena.setTipJela(PredlogConstants.PREDLOG_DTO_STARO_JELO_IZMENA_TIP);
		staroJeloIzmena.setVremePripremeMils(PredlogConstants.PREDLOG_DTO_STARO_JELO_IZMENA_VREME_PRIPREME);
		staroJeloIzmena.setTrenutnaCena(PredlogConstants.PREDLOG_DTO_STARO_JELO_IZMENA_TRENUTNA_CENA);
		
		given(jeloRepositoryMock.findOneById(PredlogConstants.PREDLOG_DTO_STARO_JELO_ID_IZMENA)).willReturn(staroJeloIzmena);
		
		//brisanje mock
		
		Jelo staroJeloBrisanje=new Jelo();
		staroJeloIzmena.setId(PredlogConstants.PREDLOG_DTO_STARO_JELO_ID_BRISANJE);
		staroJeloIzmena.setNaziv(PredlogConstants.PREDLOG_DTO_STARO_JELO_BRISANJE_NAZIV);
		staroJeloIzmena.setOpis(PredlogConstants.PREDLOG_DTO_STARO_JELO_BRISANJE_OPIS);
		staroJeloIzmena.setObrisan(false);
		staroJeloIzmena.setKategorijaJela(PredlogConstants.PREDLOG_DTO_STARO_JELO_BRISANJE_KATEGORIJA);
		staroJeloIzmena.setTipJela(PredlogConstants.PREDLOG_DTO_STARO_JELO_BRISANJE_TIP);
		staroJeloIzmena.setVremePripremeMils(PredlogConstants.PREDLOG_DTO_STARO_JELO_BRISANJE_VREME_PRIPREME);
		staroJeloIzmena.setTrenutnaCena(PredlogConstants.PREDLOG_DTO_STARO_JELO_BRISANJE_TRENUTNA_CENA);
		
		given(jeloRepositoryMock.findOneById(PredlogConstants.PREDLOG_DTO_STARO_JELO_ID_BRISANJE)).willReturn(staroJeloBrisanje);
		
		//non existing staro jelo
		
		given(jeloRepositoryMock.findOneById(PredlogConstants.NON_EXISTING_STARO_JELO_ID)).willReturn(null);
		
		when(predlogRepositoryMock.save(any(Predlog.class))).thenAnswer(i -> i.getArguments()[0]);
		
		//paginacija
		
		Predlog p1=new Predlog().builder()
				.id(PredlogConstants.PREDLOG_IZMENA_1_ID)
				.tipIzmene(PredlogConstants.PREDLOG_IZMENA_1_TIP)
				.status(PredlogConstants.PREDLOG_IZMENA_1_STATUS)
				.obrisan(false)
				.build();
		
		Predlog p2=new Predlog().builder()
				.id(PredlogConstants.PREDLOG_IZMENA_2_ID)
				.tipIzmene(PredlogConstants.PREDLOG_IZMENA_2_TIP)
				.status(PredlogConstants.PREDLOG_IZMENA_2_STATUS)
				.obrisan(false)
				.build();
		
		Predlog p3=new Predlog().builder()
				.id(PredlogConstants.PREDLOG_IZMENA_3_ID)
				.tipIzmene(PredlogConstants.PREDLOG_IZMENA_3_TIP)
				.status(PredlogConstants.PREDLOG_IZMENA_3_STATUS)
				.obrisan(false)
				.build();
		
		Predlog p4=new Predlog().builder()
				.id(PredlogConstants.PREDLOG_IZMENA_4_ID)
				.tipIzmene(PredlogConstants.PREDLOG_IZMENA_4_TIP)
				.status(PredlogConstants.PREDLOG_IZMENA_4_STATUS)
				.obrisan(false)
				.build();
		
		Predlog p5=new Predlog().builder()
				.id(PredlogConstants.PREDLOG_IZMENA_5_ID)
				.tipIzmene(PredlogConstants.PREDLOG_IZMENA_5_TIP)
				.status(PredlogConstants.PREDLOG_IZMENA_5_STATUS)
				.obrisan(false)
				.build();
		
		Predlog p6=new Predlog().builder()
				.id(PredlogConstants.PREDLOG_BRISANJE_1_ID)
				.tipIzmene(PredlogConstants.PREDLOG_BRISANJE_1_TIP)
				.status(PredlogConstants.PREDLOG_BRISANJE_1_STATUS)
				.obrisan(false)
				.build();
		
		Predlog p7=new Predlog().builder()
				.id(PredlogConstants.PREDLOG_DODAVANJE_1_ID)
				.tipIzmene(PredlogConstants.PREDLOG_DODAVANJE_1_TIP)
				.status(PredlogConstants.PREDLOG_DODAVANJE_1_STATUS)
				.obrisan(false)
				.build();
		
		// dobavi sve
		
		ArrayList<Predlog> list1=new ArrayList<>();
		list1.add(p1);
		list1.add(p2);
		list1.add(p3);
		list1.add(p6);
		list1.add(p7);
		
		Pageable pageable1=PageRequest.of(PredlogConstants.PAGE_1, PredlogConstants.PAGE_SIZE_1);
		final Page<Predlog> page1=new PageImpl<Predlog>(list1);
		
		
		given(predlogRepositoryMock.getAllPaged(pageable1, Optional.empty())).willReturn(page1);
		
		//dobavi sve 2 strane
		
		ArrayList<Predlog> list2=new ArrayList<>();
		list2.add(p1);
		list2.add(p2);
		list2.add(p3);
		
		Pageable pageable2=PageRequest.of(PredlogConstants.PAGE_2, PredlogConstants.PAGE_SIZE_2);
		final Page<Predlog> page2=new PageImpl<Predlog>(list2);
		
		
		given(predlogRepositoryMock.getAllPaged(pageable2, Optional.empty())).willReturn(page2);
		
		//dobavi sve 2 strane, druga strana
		
		ArrayList<Predlog> list3=new ArrayList<>();
		list3.add(p6);
		list3.add(p7);
				
		Pageable pageable3=PageRequest.of(PredlogConstants.PAGE_3, PredlogConstants.PAGE_SIZE_3);
		final Page<Predlog> page3=new PageImpl<Predlog>(list3);
				
				
		given(predlogRepositoryMock.getAllPaged(pageable3, Optional.empty())).willReturn(page3);
		
		//dobavi samo sa tipom IZMENA
		
		ArrayList<Predlog> list4=new ArrayList<>();
		list4.add(p1);
		list4.add(p2);
		list4.add(p3);
					
		Pageable pageable4=PageRequest.of(PredlogConstants.PAGE_4, PredlogConstants.PAGE_SIZE_4);
		final Page<Predlog> page4=new PageImpl<Predlog>(list4);
						
						
		given(predlogRepositoryMock.getAllPaged(pageable4, Optional.of(PredlogConstants.PAGE_TIP_4))).willReturn(page4);
		
		
	}
	
	@Test(expectedExceptions=PredlogWrongFormatException.class)
	public void testAddPredlogWrongFormatIzmena() throws PredlogWrongFormatException, JeloNotFoundException {
		PredlogDTO dto=new PredlogDTO();
		dto.setId(PredlogConstants.WRONT_FORMAT_PREDLOG_DTO_ID);
		dto.setNovoJelo(PredlogConstants.WRONT_FORMAT_PREDLOG_DTO_NOVO_JELO);
		dto.setStaroJeloId(PredlogConstants.WRONG_FORMAT_PREDLOG_DTO_STARO_JELO_ID);
		dto.setStatus(PredlogConstants.WRONG_FORMAT_PREDLOG_DTO_STATUS);
		dto.setTipIzmene(PredlogConstants.WRONG_FORMAT_PREDLOG_DTO_TIP_IZMENE);
		
		predlogService.addPredlog(dto);
	}
	
	@Test(expectedExceptions=PredlogWrongFormatException.class)
	public void testAddPredlogWrongFormatBrisanje() throws PredlogWrongFormatException, JeloNotFoundException {
		PredlogDTO dto=new PredlogDTO();
		dto.setId(PredlogConstants.WRONT_FORMAT_PREDLOG_DTO_ID_BRISANJE);
		dto.setNovoJelo(PredlogConstants.WRONT_FORMAT_PREDLOG_DTO_NOVO_JELO_BRISANJE);
		dto.setStaroJeloId(PredlogConstants.WRONG_FORMAT_PREDLOG_DTO_STARO_JELO_ID_BRISANJE);
		dto.setStatus(PredlogConstants.WRONG_FORMAT_PREDLOG_DTO_STATUS_BRISANJE);
		dto.setTipIzmene(PredlogConstants.WRONG_FORMAT_PREDLOG_DTO_TIP_IZMENE_BRISANJE);
		
		predlogService.addPredlog(dto);
	}
	
	@Test(expectedExceptions=PredlogWrongFormatException.class)
	public void testAddPredlogWrongFormatDodavanje() throws PredlogWrongFormatException, JeloNotFoundException {
		PredlogDTO dto=new PredlogDTO();
		dto.setId(PredlogConstants.WRONT_FORMAT_PREDLOG_DTO_ID_DODAVANJE);
		dto.setNovoJelo(PredlogConstants.WRONT_FORMAT_PREDLOG_DTO_NOVO_JELO_DODAVANJE);
		dto.setStaroJeloId(PredlogConstants.WRONG_FORMAT_PREDLOG_DTO_STARO_JELO_ID_DODAVANJE);
		dto.setStatus(PredlogConstants.WRONG_FORMAT_PREDLOG_DTO_STATUS_DODAVANJE);
		dto.setTipIzmene(PredlogConstants.WRONG_FORMAT_PREDLOG_DTO_TIP_IZMENE_DODAVANJE);
		
		predlogService.addPredlog(dto);
	}
	
	@Test
	public void testAddPredlogTipIzmena() throws PredlogWrongFormatException, JeloNotFoundException {
		PredlogDTO dto=new PredlogDTO();
		dto.setId(PredlogConstants.PREDLOG_DTO_ID_IZMENA);
		dto.setNovoJelo(PredlogConstants.PREDLOG_DTO_NOVO_JELO_DTO_IZMENA);
		dto.setStaroJeloId(PredlogConstants.PREDLOG_DTO_STARO_JELO_ID_IZMENA);
		dto.setStatus(PredlogConstants.PREDLOG_DTO_STATUS_IZMENA);
		dto.setTipIzmene(PredlogConstants.PREDLOG_DTO_TIP_IZMENE_IZMENA);
		
		Predlog p=predlogService.addPredlog(dto);
		
		verify(jeloRepositoryMock,times(1)).findOneById(PredlogConstants.PREDLOG_DTO_STARO_JELO_ID_IZMENA);
		verify(predlogRepositoryMock,times(1)).save(any(Predlog.class));
		
		Assert.assertEquals(p.getId(), PredlogConstants.PREDLOG_DTO_ID_IZMENA);
	}
	
	@Test
	public void testAddPredlogTipDodavanje() throws PredlogWrongFormatException, JeloNotFoundException {
		PredlogDTO dto=new PredlogDTO();
		dto.setId(PredlogConstants.PREDLOG_DTO_ID_DODAVANJE);
		dto.setNovoJelo(PredlogConstants.PREDLOG_DTO_NOVO_JELO_DTO_DODAVANJE);
		dto.setStaroJeloId(PredlogConstants.PREDLOG_DTO_STARO_JELO_ID_DODAVANJE);
		dto.setStatus(PredlogConstants.PREDLOG_DTO_STATUS_DODAVANJE);
		dto.setTipIzmene(PredlogConstants.PREDLOG_DTO_TIP_IZMENE_DODAVANJE);
		
		Predlog p=predlogService.addPredlog(dto);
		
		verify(jeloRepositoryMock,times(0)).findOneById(any());
		verify(predlogRepositoryMock,times(1)).save(any(Predlog.class));
		
		Assert.assertEquals(p.getId(), PredlogConstants.PREDLOG_DTO_ID_DODAVANJE);
	}
	
	@Test
	public void testAddPredlogTipBrisanje() throws PredlogWrongFormatException, JeloNotFoundException {
		PredlogDTO dto=new PredlogDTO();
		dto.setId(PredlogConstants.PREDLOG_DTO_ID_BRISANJE);
		dto.setNovoJelo(PredlogConstants.PREDLOG_DTO_NOVO_JELO_BRISANJE);
		dto.setStaroJeloId(PredlogConstants.PREDLOG_DTO_STARO_JELO_ID_BRISANJE);
		dto.setStatus(PredlogConstants.PREDLOG_DTO_STATUS_BRISANJE);
		dto.setTipIzmene(PredlogConstants.PREDLOG_DTO_TIP_IZMENE_BRISANJE);
		
		Predlog p=predlogService.addPredlog(dto);
		
		verify(jeloRepositoryMock,times(1)).findOneById(PredlogConstants.PREDLOG_DTO_STARO_JELO_ID_BRISANJE);
		verify(predlogRepositoryMock,times(1)).save(any(Predlog.class));
		
		Assert.assertEquals(p.getId(), PredlogConstants.PREDLOG_DTO_ID_BRISANJE);
	}
	
	@Test(expectedExceptions=JeloNotFoundException.class)
	public void testAddPredlogTipBrisanjeWithNonExistingId() throws PredlogWrongFormatException, JeloNotFoundException {
		PredlogDTO dto=new PredlogDTO();
		dto.setId(PredlogConstants.PREDLOG_DTO_ID_BRISANJE);
		dto.setNovoJelo(PredlogConstants.PREDLOG_DTO_NOVO_JELO_BRISANJE);
		dto.setStaroJeloId(PredlogConstants.NON_EXISTING_STARO_JELO_ID);
		dto.setStatus(PredlogConstants.PREDLOG_DTO_STATUS_BRISANJE);
		dto.setTipIzmene(PredlogConstants.PREDLOG_DTO_TIP_IZMENE_BRISANJE);
		
		Predlog p=predlogService.addPredlog(dto);
		
		verify(jeloRepositoryMock,times(1)).findOneById(PredlogConstants.NON_EXISTING_STARO_JELO_ID);
	}
	
	@Test
	public void testGetAllPagedNoType() {
		
		Page<Predlog> result=predlogService.getAllPaged(PredlogConstants.PAGE_1, PredlogConstants.PAGE_SIZE_1, Optional.empty());
		
		Assert.assertEquals(result.getContent().size(),5);
	}
	
	@Test
	public void testGetAllPagedNoType2Pages() {
		
		Page<Predlog> result=predlogService.getAllPaged(PredlogConstants.PAGE_2, PredlogConstants.PAGE_SIZE_2, Optional.empty());
		
		Assert.assertEquals(result.getContent().size(),3);
	}
	
	@Test
	public void testGetAllPagedNoType2PagesPage2() {
		
		Page<Predlog> result=predlogService.getAllPaged(PredlogConstants.PAGE_3, PredlogConstants.PAGE_SIZE_3, Optional.empty());
		
		Assert.assertEquals(result.getContent().size(),2);
	}
	
	@Test
	public void testGetAllPagedTypeIzmena() {
		
		Page<Predlog> result=predlogService.getAllPaged(PredlogConstants.PAGE_4, PredlogConstants.PAGE_SIZE_4, Optional.of(PredlogConstants.PAGE_TIP_4));
		
		Assert.assertEquals(result.getContent().size(),3);
		
		for(Predlog p: result.getContent()) {
			Assert.assertEquals(p.getTipIzmene(),PredlogConstants.PAGE_TIP_4);
		}
	}

}
