package gradjanibrzogbroda.backend.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import gradjanibrzogbroda.backend.constants.PredlogConstants;
import gradjanibrzogbroda.backend.domain.Predlog;
import gradjanibrzogbroda.backend.domain.PredlogStatus;
import gradjanibrzogbroda.backend.domain.PredlogTip;
import gradjanibrzogbroda.backend.dto.PredlogDTO;
import gradjanibrzogbroda.backend.exceptions.JeloNotFoundException;
import gradjanibrzogbroda.backend.exceptions.PredlogWrongFormatException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class PredlogServiceTests extends AbstractTestNGSpringContextTests {
	
	@Autowired
	private PredlogService predlogService;
	
	@Test(expectedExceptions=PredlogWrongFormatException.class)
	public void testAddPredlogWrongFormatIzmena() throws PredlogWrongFormatException, JeloNotFoundException {
		PredlogDTO dto=new PredlogDTO();
		dto.setId(PredlogConstants.WRONG_FORMAT_PREDLOG_DTO_ID);
		dto.setNovoJelo(PredlogConstants.WRONG_FORMAT_PREDLOG_DTO_NOVO_JELO);
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
	
	@Test(expectedExceptions=JeloNotFoundException.class)
	public void testAddPredlogTipBrisanjeWithNonExistingId() throws PredlogWrongFormatException, JeloNotFoundException {
		PredlogDTO dto=new PredlogDTO();
		dto.setNovoJelo(PredlogConstants.PREDLOG_DTO_NOVO_JELO_BRISANJE);
		dto.setStaroJeloId(PredlogConstants.NON_EXISTING_STARO_JELO_ID_DB);
		dto.setStatus(PredlogConstants.PREDLOG_DTO_STATUS_BRISANJE);
		dto.setTipIzmene(PredlogConstants.PREDLOG_DTO_TIP_IZMENE_BRISANJE);
		
		Predlog p=predlogService.addPredlog(dto);
		
	}
	
	@Test(priority = 0)
	public void testAddPredlogTipIzmena() throws PredlogWrongFormatException, JeloNotFoundException {
		PredlogDTO dto=new PredlogDTO();
		dto.setNovoJelo(PredlogConstants.PREDLOG_DTO_NOVO_JELO_DTO_IZMENA);
		dto.setStaroJeloId(PredlogConstants.PREDLOG_DTO_STARO_JELO_ID_IZMENA);
		dto.setStatus(PredlogStatus.NOV);
		dto.setTipIzmene(PredlogConstants.PREDLOG_DTO_TIP_IZMENE_IZMENA);
		
		Predlog p=predlogService.addPredlog(dto);
		
		Assert.assertEquals(p.getId(), Integer.valueOf(1));
	}
	
	@Test(priority = 1)
	public void testAddPredlogTipDodavanje() throws PredlogWrongFormatException, JeloNotFoundException {
		PredlogDTO dto=new PredlogDTO();
		dto.setNovoJelo(PredlogConstants.PREDLOG_DTO_NOVO_JELO_DTO_DODAVANJE);
		dto.setStaroJeloId(PredlogConstants.PREDLOG_DTO_STARO_JELO_ID_DODAVANJE);
		dto.setStatus(PredlogStatus.NOV);
		dto.setTipIzmene(PredlogConstants.PREDLOG_DTO_TIP_IZMENE_DODAVANJE);
		
		Predlog p=predlogService.addPredlog(dto);
		
		Assert.assertEquals(p.getId(), Integer.valueOf(2));
	}
	
	@Test(priority = 2)
	public void testAddPredlogTipBrisanje() throws PredlogWrongFormatException, JeloNotFoundException {
		PredlogDTO dto=new PredlogDTO();
		dto.setNovoJelo(PredlogConstants.PREDLOG_DTO_NOVO_JELO_BRISANJE);
		dto.setStaroJeloId(PredlogConstants.PREDLOG_DTO_STARO_JELO_ID_BRISANJE);
		dto.setStatus(PredlogStatus.NOV);
		dto.setTipIzmene(PredlogConstants.PREDLOG_DTO_TIP_IZMENE_BRISANJE);
		
		Predlog p=predlogService.addPredlog(dto);
		
		Assert.assertEquals(p.getId(), Integer.valueOf(3));
		
	}
	
	@Test(priority = -1)
	public void testGetAllPagedNoTypeBeforeAdding() {
		
		Page<Predlog> result=predlogService.getAllPaged(PredlogConstants.PAGE_1, PredlogConstants.PAGE_SIZE_1, Optional.empty());
		
		Assert.assertEquals(result.getContent().size(),0);
	}
	
	@Test(priority = 3)
	public void testGetAllPagedNoTypeAfterAdding() {
		
		Page<Predlog> result=predlogService.getAllPaged(PredlogConstants.PAGE_1, PredlogConstants.PAGE_SIZE_1, Optional.empty());
		
		Assert.assertEquals(result.getContent().size(),3);
	}
	
	@Test(priority = 3)
	public void testGetAllPagedNoType2PagesAfterAdding() {
		
		Page<Predlog> result=predlogService.getAllPaged(0, 2, Optional.empty());
		
		Assert.assertEquals(result.getContent().size(),2);
		Assert.assertEquals(result.getTotalElements(),3);
		Assert.assertEquals(result.getTotalPages(),2);
	}
	
	@Test(priority = 3)
	public void testGetAllPagedNoType2PagesPage2AfterAdding() {
		
		Page<Predlog> result=predlogService.getAllPaged(1, 2, Optional.empty());
		
		Assert.assertEquals(result.getContent().size(),1);
		Assert.assertEquals(result.getTotalElements(),3);
		Assert.assertEquals(result.getTotalPages(),2);
	}
	
	@Test(priority = 3)
	public void testGetAllPagedTypeIzmenaAfterAdding() {
		
		Page<Predlog> result=predlogService.getAllPaged(PredlogConstants.PAGE_1, PredlogConstants.PAGE_SIZE_1, Optional.of(PredlogTip.IZMENA));
		
		Assert.assertEquals(result.getContent().size(),1);
	}
	
	@Test(priority = 4)
	public void testAddPredlogTipBrisanjeUpdateStatus() throws PredlogWrongFormatException, JeloNotFoundException {
		PredlogDTO dto=new PredlogDTO();
		dto.setId(3);
		dto.setNovoJelo(PredlogConstants.PREDLOG_DTO_NOVO_JELO_BRISANJE);
		dto.setStaroJeloId(PredlogConstants.PREDLOG_DTO_STARO_JELO_ID_BRISANJE);
		dto.setStatus(PredlogStatus.ODBIJEN);
		dto.setTipIzmene(PredlogConstants.PREDLOG_DTO_TIP_IZMENE_BRISANJE);
		
		Predlog p=predlogService.addPredlog(dto);
		
		Assert.assertEquals(p.getId(), Integer.valueOf(3));
		Assert.assertEquals(p.getStatus(), PredlogStatus.ODBIJEN);
	}
	
	@Test(priority = 5)
	public void testGetAllPagedNoTypeAfterUpdating() {
		
		Page<Predlog> result=predlogService.getAllPaged(PredlogConstants.PAGE_1, PredlogConstants.PAGE_SIZE_1, Optional.empty());
		
		Assert.assertEquals(result.getContent().size(),2);
	}
	
	@Test(priority = 5)
	public void testGetAllPagedTypeBrisanjeAfterUpdating() {
		
		Page<Predlog> result=predlogService.getAllPaged(PredlogConstants.PAGE_1, PredlogConstants.PAGE_SIZE_1, Optional.of(PredlogTip.BRISANJE));
		
		Assert.assertEquals(result.getContent().size(),0);
	}
	
	

}
