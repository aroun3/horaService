package com.visitor.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.visitor.entities.PunchHistory;
import com.visitor.payload.response.AreaHistoryStats;
import com.visitor.payload.response.HistoryStats;
import com.visitor.service_interfaces.PunchHistoryService;

@RestController
@RequestMapping("/api/v1")
public class HistoryController {
	
	@Autowired
	private PunchHistoryService punchHistoryService;
	
	

	@GetMapping("/history/stats")
	public HistoryStats historyStats( @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, 
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate){
		HistoryStats historyStats = punchHistoryService.historyStats(startDate, endDate);
		return historyStats;
	};
	
	@GetMapping("/history/stats/area")
	public List<AreaHistoryStats> areaHistoryStats(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, 
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate){
		
		System.out.println("================================== startDate : "+startDate);
		System.out.println("================================== endDate : "+endDate);
		List<AreaHistoryStats> areaHistoryStats = punchHistoryService.areaHistoryStats(startDate, endDate);
		return areaHistoryStats;
	};
	
	@GetMapping("/history/stats/arrival/min")
	public List<PunchHistory> arrivalMin(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, 
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate){
		List<PunchHistory> punchHistories = punchHistoryService.arrivalMin5(startDate, endDate);
		return punchHistories;
	};
	
	@GetMapping("/history/stats/arrival/max")
	public List<PunchHistory> arrivalMax(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, 
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate){
		List<PunchHistory> punchHistories = punchHistoryService.arrivalMax5(startDate, endDate);
		return punchHistories;
	};
	
	@GetMapping("/history/stats/present")
	public List<PunchHistory> present(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, 
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate){
		return punchHistoryService.present5(startDate, endDate);
	};
	
	@GetMapping("/history/stats/departure/min")
	public List<PunchHistory> departureMin(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, 
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate){
		List<PunchHistory> punchHistories = punchHistoryService.departureMin5(startDate, endDate);
		return punchHistories;
	};
	
	@GetMapping("/history/stats/departure/max")
	public List<PunchHistory> departureMax(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, 
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate){
		List<PunchHistory> punchHistories = punchHistoryService.departureMax5(startDate, endDate);
		return punchHistories;
	};
	
	@GetMapping("/history/stats/absent")
	public List<PunchHistory> adsent(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, 
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate){
		return punchHistoryService.absent(startDate, endDate);
	};

}
