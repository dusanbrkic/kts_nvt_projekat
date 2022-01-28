package gradjanibrzogbroda.backend.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gradjanibrzogbroda.backend.service.ReportService;

@RestController
@RequestMapping("/report")
@CrossOrigin(origins = {"http://localhost:4200/" })
public class ReportController {
	
	@Autowired
	private ReportService repoService;

	
	@GetMapping("/{type}")
	public ResponseEntity<Double> getReport(@PathVariable("type")int type,
			@RequestParam("start") String start,
			@RequestParam("end") String end
			){
		LocalDateTime startTime;
		LocalDateTime endTime;
		
		startTime = LocalDateTime.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));
		endTime = LocalDateTime.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));
		
		double value = repoService.getFinancialReport(type, startTime, endTime);
		
		return new ResponseEntity<Double>(value, HttpStatus.OK);
	}
}
