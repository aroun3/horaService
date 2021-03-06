package com.visitor.controller;

import java.util.List;

import com.visitor.entities.*;
import com.visitor.payload.response.*;
import com.visitor.repositories.PunchHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

	@GetMapping("/history/stats/absentLimit10")
	public List<PunchHistory> absentLimit10(@RequestParam("periode") String periode){
		return punchHistoryService.absentLimit10(periode);
	}
	
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

	/*LISTE DES AREA OU SITES*/
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

	/*LES EMPLOYEE TOP5 QUI FONT 8H OU PLUS DE TRAVAIL*/
	@GetMapping("/history/employee/top5")
	public List<EmployeTop> employeTop5ByPresencePeriode(@RequestParam("periode")String periode){
		return punchHistoryService.employeTop5ByPresencePeriode(periode);
	};

	/*LES EMPLOYEE LAST5 QUI FONT MOINS DE 8H DE TRAVAIL*/
	@GetMapping("/history/employee/last5")
	public List<EmployeTop> employeLast5ByPresencePeriode(@RequestParam("periode")String periode){
		return punchHistoryService.employeLast5ByPresencePeriode(periode);
	};

	/*LISTE TOTAL EMPLOYEE TOP ET LAST AVEC LES TEMPS DE TRAVAIL*/
	@GetMapping("/history/employee/totalTopLast")
	public TotalTopLast totalEmployeTopLastByPresencePeriode(@RequestParam("periode")String periode){
		return punchHistoryService.totalEmployeTopLastByPresencePeriode(periode);
	};



	/*LISTE TOTAL DES ARRIVAL MIN, MAX ET DEPART MIN? MAX*/
	@GetMapping("/history/totalArrivalMin")
	public List<IPunchHistory> totalArrivalMin(@RequestParam("periode")String periode){
		return punchHistoryService.totalArrivalMin(periode);
	};

	@GetMapping("/history/totalArrivalMax")
	public List<IPunchHistory> totalArrivalMax(@RequestParam("periode")String periode) {
		return punchHistoryService.totalArrivalMax(periode);
	};

	@GetMapping("/history/totalDepartureMin")
	public List<IPunchHistory> totalDepartureMin(@RequestParam("periode")String periode){
		return punchHistoryService.totalDepartureMin(periode);
	}

	@GetMapping("/history/totalDepartureMax")
	public List<IPunchHistory> totalDepartureMax(@RequestParam("periode")String periode) {
		return punchHistoryService.totalDepartureMax(periode);
	}


}
