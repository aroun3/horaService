package com.visitor.service_interfaces;

import java.util.Date;
import java.util.List;

import com.visitor.entities.PunchHistory;
import com.visitor.payload.response.AreaHistoryStats;
import com.visitor.payload.response.HistoryStats;

public interface PunchHistoryService {

	HistoryStats historyStats(Date startDate, Date endDate);
	List<AreaHistoryStats> areaHistoryStats(Date startDate, Date endDate);
	PunchHistory arrivalMin(Date startDate, Date endDate);
	PunchHistory arrivalMax(Date startDate, Date endDate);
	Date arrivalMean(Date startDate, Date endDate);
	PunchHistory departureMin(Date startDate, Date endDate);
	PunchHistory departureMax(Date startDate, Date endDate);
	Date departureMean(Date startDate, Date endDate);
}
