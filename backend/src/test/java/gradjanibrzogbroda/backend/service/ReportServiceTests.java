package gradjanibrzogbroda.backend.service;


import static org.testng.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class ReportServiceTests extends AbstractTestNGSpringContextTests{

		@Autowired
		private ReportService repSer;
		
		
	@Test
	public void testGetFinancialReportTest() {
		String start="2022-01-09T13:30:00.000Z";
		String end="2022-01-27T13:30:00.000Z";
		
		LocalDateTime startTime = LocalDateTime.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));
		LocalDateTime endTime = LocalDateTime.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));
		
		Map<String, Object> report = repSer.getFinancialReport(startTime, endTime);
		
		assertTrue(report.containsKey("dates"));
		assertTrue(report.containsKey("gains"));
		assertTrue(report.containsKey("losses"));
		assertTrue(report.containsKey("profits"));
	}
	
	@Test
	public void testGetFinancialReportTestFromLastYear() {
		String start="2021-01-09T13:30:00.000Z";
		String end="2021-01-27T13:30:00.000Z";
		
		LocalDateTime startTime = LocalDateTime.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));
		LocalDateTime endTime = LocalDateTime.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));
		
		Map<String, Object> report = repSer.getFinancialReport(startTime, endTime);
		
		assertTrue(report.containsKey("dates"));
		assertTrue(report.containsKey("gains"));
		assertTrue(report.containsKey("losses"));
		assertTrue(report.containsKey("profits"));
	}
}
