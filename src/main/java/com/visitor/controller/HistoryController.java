package com.visitor.controller;

import java.util.List;

import com.visitor.entities.HistoryAllPointage;
import com.visitor.entities.HistoryPointage;
import com.visitor.entities.IArea;
import com.visitor.payload.response.PersonnelArea;
import com.visitor.repositories.PunchHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.visitor.entities.PunchHistory;
import com.visitor.payload.response.AreaHistoryStats;
import com.visitor.payload.response.GraphStat;
import com.visitor.payload.response.HistoryStats;
import com.visitor.service_interfaces.PunchHistoryService;

@RestController
@RequestMapping("/api/v1")
public class HistoryController {
	
	@Autowired
	private PunchHistoryService punchHistoryService;

	@Autowired
	private PunchHistoryRepository punchHistoryRepository;

	@GetMapping("/history/stats")
	public HistoryStats historyStats(@RequestParam("periode") String periode){
		HistoryStats historyStats = punchHistoryService.historyStats(periode);
		return historyStats;
	};

	@GetMapping("/history/stats/area")
	public List<AreaHistoryStats> areaHistoryStats(@RequestParam("periode") String periode){
		List<AreaHistoryStats> areaHistoryStats = punchHistoryService.areaHistoryStats(periode);
		return areaHistoryStats;
	};
	
	@GetMapping("/history/stats/arrival/min")
	public List<PunchHistory> arrivalMin(@RequestParam("periode") String periode){
		List<PunchHistory> punchHistories = punchHistoryService.arrivalMin5(periode);
		return punchHistories;
	};
	
	@GetMapping("/history/stats/arrival/max")
	public List<PunchHistory> arrivalMax(@RequestParam("periode") String periode){
		List<PunchHistory> punchHistories = punchHistoryService.arrivalMax5(periode);
		return punchHistories;
	};
	
	@GetMapping("/history/stats/present")
	public List<PunchHistory> present(@RequestParam("periode") String periode){
		return punchHistoryService.present5(periode);
	};
	
	@GetMapping("/history/stats/departure/min")
	public List<PunchHistory> departureMin(@RequestParam("periode") String periode){
		List<PunchHistory> punchHistories = punchHistoryService.departureMin5(periode);
		return punchHistories;
	};
	
	@GetMapping("/history/stats/departure/max")
	public List<PunchHistory> departureMax(@RequestParam("periode") String periode){
		List<PunchHistory> punchHistories = punchHistoryService.departureMax5(periode);
		return punchHistories;
	};
	
	@GetMapping("/history/stats/absent")
	public List<PunchHistory> absent(@RequestParam("periode") String periode){
		return punchHistoryService.absent(periode);
	};
	
	@GetMapping("/history/stats/graph")
	public List<GraphStat> graphStats(@RequestParam("periode") String periode){
		return punchHistoryService.graphStats(periode);
	};


	@GetMapping("/listArea")
	public List<IArea> getListArea(){
		return  punchHistoryRepository.findAreaList();
	}

	/*DETAIL*/

	@GetMapping("/history/statsByEmployee")
	public HistoryStats historyStatsByEmployeeCode(@RequestParam("periode") String periode, @RequestParam("empCode")String empCode){
		HistoryStats historyStats = punchHistoryService.areaHistoryStatsByEmployee(periode, empCode);
		return historyStats;
	};


	@GetMapping("/history/personnelArea")
	public List<PersonnelArea> getPersonnelArea(){
		return punchHistoryService.getAllEmployeArea();
	};


	/*ARRIVER ET DEPART DE L'EMPLOYE PAR SON CODE*/
	@GetMapping("/history/historyPointage")
	public List<HistoryPointage> getHistoryPointageByEmpcode(@RequestParam("empCode") String empCode){
		return punchHistoryService.historyPointageByEmpCode(empCode);
	};

	/*LISTE DE TOUS LES POINTAGES DE L'EMPLOYE PAR SON CODE*/
	@GetMapping("/history/historyAllPointage")
	public List<HistoryAllPointage> historyAllPointage(@RequestParam("empCode") String empCode){
		return punchHistoryService.historyAllPointage(empCode);
	};
}
