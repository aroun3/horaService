package com.visitor.service_interfaces;

import java.util.List;

import com.visitor.entities.ArrivalPunch;
import com.visitor.entities.RefreshArrival;
import com.visitor.payload.response.AreaStat;
import com.visitor.payload.response.GeneralStat;

public interface AccueilService {

		List<ArrivalPunch> top5();
		List<ArrivalPunch> last5();
		List<ArrivalPunch> absent();
		GeneralStat generalStat();
		List<AreaStat> areaStats();
		List<ArrivalPunch> arriveEnTempsReel();
		RefreshArrival refreshArrival();
		List<ArrivalPunch> getAllTop();
		List<ArrivalPunch> getAllLast();
		List<ArrivalPunch> getAllAbsent();
}
