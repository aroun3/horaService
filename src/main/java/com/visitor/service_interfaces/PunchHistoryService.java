package com.visitor.service_interfaces;

import java.util.Date;
import java.util.List;

import com.visitor.entities.*;
import com.visitor.payload.response.*;
import org.springframework.data.repository.query.Param;

public interface PunchHistoryService {

	HistoryStats historyStats(String periode);
	List<AreaHistoryStats> areaHistoryStats(String periode);
	List<PunchHistory> arrivalMin5(String periode);
	List<PunchHistory> arrivalMax5(String periode);
	List<PunchHistory> absent(String periode);
	List<PunchHistory> departureMin5(String periode);
	List<PunchHistory> departureMax5(String periode);
	List<PunchHistory> present5(String periode);
	List<GraphStat> graphStats(String periode);
	HistoryStats areaHistoryStatsByEmployee(String periode, String empCode);
	public List<PersonnelArea> getAllEmployeArea();
	List<HistoryPointage>historyPointageByEmpCode(String empCode);
	List<HistoryAllPointage>historyAllPointage(String empCode);
	List<EmployeTop>employeTop5ByPresencePeriode(String periode);
	List<EmployeTop>employeLast5ByPresencePeriode(String periode);
	TotalTopLast totalEmployeTopLastByPresencePeriode(String periode);

	List<IPunchHistory> totalArrivalMin(String periode);
	List<IPunchHistory> totalArrivalMax(String periode);
	List<IPunchHistory> totalDepartureMin(String periode);
	List<IPunchHistory> totalDepartureMax(String periode);

}
