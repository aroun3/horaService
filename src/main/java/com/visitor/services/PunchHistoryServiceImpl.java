package com.visitor.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.visitor.entities.Area;
import com.visitor.entities.IArea;
import com.visitor.entities.PunchHistory;
import com.visitor.payload.response.AreaHistoryStats;
import com.visitor.payload.response.AreaPresenceHistory;
import com.visitor.payload.response.GeneralHistoryStats;
import com.visitor.payload.response.HistoryStats;
import com.visitor.payload.response.PresenceHistory;
import com.visitor.repositories.PunchHistoryRepository;
import com.visitor.service_interfaces.PunchHistoryService;

@Service
public class PunchHistoryServiceImpl implements PunchHistoryService{
	
	@Autowired
	private PunchHistoryRepository punchHistoryRepository; 

	@Override
	public HistoryStats historyStats(Date startDate, Date endDate) {
		
		GeneralHistoryStats arrivees;
		GeneralHistoryStats departs;
		PresenceHistory presences;
		Integer absences;
		
		Integer early;
		Integer ontime;
		Integer late;
		Integer absent;
		
		Integer below;
		Integer normal;
		Integer over;
		
		
		System.out.println("============================================== debut");
		
		early = punchHistoryRepository.countArrivalByState(startDate, endDate, "EARLY");
		ontime = punchHistoryRepository.countArrivalByState(startDate, endDate, "ONTIME");
		late = punchHistoryRepository.countArrivalByState(startDate, endDate, "LATE");
		absent = punchHistoryRepository.countArrivalByState(startDate, endDate, "ABSENT");
		
		arrivees = new GeneralHistoryStats(early, ontime, late, absent);
		System.out.println("============================================== arrivees");
		
		early = punchHistoryRepository.countDepatureByState(startDate, endDate, "EARLY");
		ontime = punchHistoryRepository.countDepatureByState(startDate, endDate, "ONTIME");
		late = punchHistoryRepository.countDepatureByState(startDate, endDate, "LATE");
		absent = punchHistoryRepository.countDepatureByState(startDate, endDate, "ABSENT");
		
		departs = new GeneralHistoryStats(early, ontime, late, absent);
		System.out.println("============================================== departs");
		
		below = punchHistoryRepository.countPresenceByState(startDate, endDate, "BELOW");
		normal = punchHistoryRepository.countPresenceByState(startDate, endDate, "NORMAL");
		over = punchHistoryRepository.countPresenceByState(startDate, endDate, "OVER");
		absent = punchHistoryRepository.countPresenceByState(startDate, endDate, "ABSENT");
		
		presences = new PresenceHistory(below, normal, over, absent);
		
		absences = punchHistoryRepository.countAbsent(startDate, endDate, "NO");
		System.out.println("============================================== absences");
		
		HistoryStats historyStats = new HistoryStats(arrivees, departs, presences, absences);
		
		return historyStats;
	}

	@Override
	public List<AreaHistoryStats> areaHistoryStats(Date startDate, Date endDate) {
		
		GeneralHistoryStats arrivees;
		GeneralHistoryStats departs;
		PresenceHistory presences;
		Integer absences;
		
		List<IArea> areas = punchHistoryRepository.findAreaList();
		List<Integer> terminalId = new ArrayList<>();
		List<AreaHistoryStats> areaHistoryStats = new ArrayList<>();
		
		Integer early;
		Integer ontime;
		Integer late;
		Integer absent;
		
		Integer below;
		Integer normal;
		Integer over;
		
		
		for (IArea area : areas) {
			
			System.out.println("================================ area.getId() : "+area.getId());
			
			terminalId = punchHistoryRepository.findDepartmentByAreaId(area.getId());
			
			System.out.println("====================================== terminalId : "+terminalId);
			
			early = punchHistoryRepository.countArrivalByAreaAndState(terminalId, startDate, endDate, "EARLY");
			ontime = punchHistoryRepository.countArrivalByAreaAndState(terminalId, startDate, endDate, "ONTIME");
			late = punchHistoryRepository.countArrivalByAreaAndState(terminalId, startDate, endDate, "LATE");
			absent = punchHistoryRepository.countArrivalByAreaAndState(terminalId, startDate, endDate, "ABSENT");
			
			arrivees = new GeneralHistoryStats(early, ontime, late, absent);
			
			System.out.println("===================================== arrivees : "+arrivees);
			
			early = punchHistoryRepository.countDepatureByAreaAndState(terminalId, startDate, endDate, "EARLY");
			ontime = punchHistoryRepository.countDepatureByAreaAndState(terminalId, startDate, endDate, "ONTIME");
			late = punchHistoryRepository.countDepatureByAreaAndState(terminalId, startDate, endDate, "LATE");
			absent = punchHistoryRepository.countDepatureByAreaAndState(terminalId, startDate, endDate, "ABSENT");
			
			departs = new GeneralHistoryStats(early, ontime, late, absent);
			
			System.out.println("===================================== departs : "+departs);
			
			below = punchHistoryRepository.countPresenceByAreaAndState(terminalId, startDate, endDate, "BELOW");
			normal = punchHistoryRepository.countPresenceByAreaAndState(terminalId, startDate, endDate, "NORMAL");
			over = punchHistoryRepository.countPresenceByAreaAndState(terminalId, startDate, endDate, "OVER");
			absent = punchHistoryRepository.countPresenceByAreaAndState(terminalId, startDate, endDate, "ABSENT");
			
			presences = new PresenceHistory(below, normal, over, absent);
			
			System.out.println("===================================== presences : "+presences);
			
			absences = punchHistoryRepository.countAbsentByArea(terminalId, startDate, endDate, "NO");
			
			System.out.println("===================================== absences : "+absences);
			areaHistoryStats.add(new AreaHistoryStats(area.getArea(), null, null, arrivees, departs, presences, absences));
			
		}
		
		return areaHistoryStats;
	}

	@Override
	public PunchHistory arrivalMin(Date startDate, Date endDate) {

		PunchHistory punchHistory = punchHistoryRepository.arrivalMin(startDate, endDate);
		return punchHistory;
	}

	@Override
	public PunchHistory arrivalMax(Date startDate, Date endDate) {
		PunchHistory punchHistory = punchHistoryRepository.arrivalMax(startDate, endDate);
		return punchHistory;
	}

	@Override
	public Date arrivalMean(Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return punchHistoryRepository.arrivalMean(startDate, endDate);
	}

	@Override
	public PunchHistory departureMin(Date startDate, Date endDate) {
		PunchHistory punchHistory = punchHistoryRepository.departureMin(startDate, endDate);
		return punchHistory;
	}

	@Override
	public PunchHistory departureMax(Date startDate, Date endDate) {
		PunchHistory punchHistory = punchHistoryRepository.departureMax(startDate, endDate);
		return punchHistory;
	}

	@Override
	public Date departureMean(Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return punchHistoryRepository.departureMean(startDate, endDate);
	}

}
