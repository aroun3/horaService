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
	public PunchHistory arrivalMin(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, 
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate){
		PunchHistory punchHistory = punchHistoryService.arrivalMin(startDate, endDate);
		return punchHistory;
	};
	
	@GetMapping("/history/stats/arrival/max")
	public PunchHistory arrivalMax(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, 
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate){
		PunchHistory punchHistory = punchHistoryService.arrivalMax(startDate, endDate);
		return punchHistory;
	};
	
	@GetMapping("/history/stats/arrival/mean")
	public Date arrivalMean(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, 
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate){
		return punchHistoryService.arrivalMean(startDate, endDate);
	};
	
	@GetMapping("/history/stats/departure/min")
	public PunchHistory departureMin(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, 
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate){
		PunchHistory punchHistory = punchHistoryService.departureMin(startDate, endDate);
		return punchHistory;
	};
	
	@GetMapping("/history/stats/departure/max")
	public PunchHistory departureMax(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, 
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate){
		PunchHistory punchHistory = punchHistoryService.departureMax(startDate, endDate);
		return punchHistory;
	};
	
	@GetMapping("/history/stats/departure/mean")
	public Date departureMean(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, 
			@RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate){
		return punchHistoryService.departureMean(startDate, endDate);
	};

}
