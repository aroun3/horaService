package com.visitor.service_interfaces;

import java.util.Date;
import java.util.List;

import com.visitor.entities.PunchHistory;
import com.visitor.payload.response.AreaHistoryStats;
import com.visitor.payload.response.HistoryStats;

public interface PunchHistoryService {

	HistoryStats historyStats(Date startDate, Date endDate);
	List<AreaHistoryStats> areaHistoryStats(Date startDate, Date endDate);
	List<PunchHistory> arrivalMin5(Date startDate, Date endDate);
	List<PunchHistory> arrivalMax5(Date startDate, Date endDate);
	List<PunchHistory> absent(Date startDate, Date endDate);
	List<PunchHistory> departureMin5(Date startDate, Date endDate);
	List<PunchHistory> departureMax5(Date startDate, Date endDate);
	List<PunchHistory> present5(Date startDate, Date endDate);
}
