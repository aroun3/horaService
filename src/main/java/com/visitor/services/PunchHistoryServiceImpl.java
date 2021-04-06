package com.visitor.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.visitor.entities.Area;
import com.visitor.entities.IArea;
import com.visitor.entities.IPunchHistory;
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
		
		early = punchHistoryRepository.countArrivalByState(startDate, endDate, "1");
		ontime = punchHistoryRepository.countArrivalByState(startDate, endDate, "2");
		late = punchHistoryRepository.countArrivalByState(startDate, endDate, "3");
		absent = punchHistoryRepository.countArrivalByState(startDate, endDate, "NON DIPONIBLE");
		
		arrivees = new GeneralHistoryStats(early, ontime, late, absent);
		System.out.println("============================================== arrivees");
		
		early = punchHistoryRepository.countDepatureByState(startDate, endDate, "1");
		ontime = punchHistoryRepository.countDepatureByState(startDate, endDate, "2");
		late = punchHistoryRepository.countDepatureByState(startDate, endDate, "3");
		absent = punchHistoryRepository.countDepatureByState(startDate, endDate, "NON DIPONIBLE");
		
		departs = new GeneralHistoryStats(early, ontime, late, absent);
		System.out.println("============================================== departs");
		
		below = punchHistoryRepository.countPresenceByState(startDate, endDate, "BELOW");
		normal = punchHistoryRepository.countPresenceByState(startDate, endDate, "NORMAL");
		over = punchHistoryRepository.countPresenceByState(startDate, endDate, "OVER");
		absent = punchHistoryRepository.countPresenceByState(startDate, endDate, "NON DIPONIBLE");
		
		presences = new PresenceHistory(below, normal, over, absent);
		
		
		absences = punchHistoryRepository.countAbsent(startDate, endDate, Boolean.TRUE);
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
			
			early = punchHistoryRepository.countArrivalByAreaAndState(area.getId(), startDate, endDate, "EARLY");
			ontime = punchHistoryRepository.countArrivalByAreaAndState(area.getId(), startDate, endDate, "ONTIME");
			late = punchHistoryRepository.countArrivalByAreaAndState(area.getId(), startDate, endDate, "LATE");
			absent = punchHistoryRepository.countArrivalByAreaAndState(area.getId(), startDate, endDate, "ABSENT");
			
			arrivees = new GeneralHistoryStats(early, ontime, late, absent);
			
			System.out.println("===================================== arrivees : "+arrivees);
			
			early = punchHistoryRepository.countDepatureByAreaAndState(area.getId(), startDate, endDate, "EARLY");
			ontime = punchHistoryRepository.countDepatureByAreaAndState(area.getId(), startDate, endDate, "ONTIME");
			late = punchHistoryRepository.countDepatureByAreaAndState(area.getId(), startDate, endDate, "LATE");
			absent = punchHistoryRepository.countDepatureByAreaAndState(area.getId(), startDate, endDate, "ABSENT");
			
			departs = new GeneralHistoryStats(early, ontime, late, absent);
			
			System.out.println("===================================== departs : "+departs);
			
			below = punchHistoryRepository.countPresenceByAreaAndState(area.getId(), startDate, endDate, "BELOW");
			normal = punchHistoryRepository.countPresenceByAreaAndState(area.getId(), startDate, endDate, "NORMAL");
			over = punchHistoryRepository.countPresenceByAreaAndState(area.getId(), startDate, endDate, "OVER");
			absent = punchHistoryRepository.countPresenceByAreaAndState(area.getId(), startDate, endDate, "ABSENT");
			
			presences = new PresenceHistory(below, normal, over, absent);
			
			System.out.println("===================================== presences : "+presences);
			
			absences = punchHistoryRepository.countAbsentByArea(area.getId(), startDate, endDate, Boolean.TRUE);
			
			System.out.println("===================================== absences : "+absences);
			areaHistoryStats.add(new AreaHistoryStats(area.getArea(), null, null, arrivees, departs, presences, absences));
			
		}
		
		return areaHistoryStats;
	}

	@Override
	public List<PunchHistory> arrivalMin5(Date startDate, Date endDate) {

		List<IPunchHistory> iPunchHistories = punchHistoryRepository.arrivalMin5(startDate, endDate);

		PunchHistory punchHistory;
		List<PunchHistory> punchHistories = new ArrayList<>();
		for (IPunchHistory iPunchHistory : iPunchHistories) {
			
			punchHistories.add(new PunchHistory(iPunchHistory.getLogDate(), iPunchHistory.getEmpCode(), iPunchHistory.getFirstName(), iPunchHistory.getLastName(), 
					iPunchHistory.getPosition(), iPunchHistory.getDepartment(), iPunchHistory.getArrivalTime(), iPunchHistory.getDepartureTime(),
					iPunchHistory.getPresencePeriode(), iPunchHistory.getArrivalState(), iPunchHistory.getDepartureState(), 
					iPunchHistory.getPresenceState(), iPunchHistory.isAbsent()
					)
					);
		}
		
		return punchHistories;
	}

	@Override
	public List<PunchHistory> arrivalMax5(Date startDate, Date endDate) {
		List<IPunchHistory> iPunchHistories  = punchHistoryRepository.arrivalMax5(startDate, endDate);
		
		PunchHistory punchHistory;
		List<PunchHistory> punchHistories = new ArrayList<>();
		for (IPunchHistory iPunchHistory : iPunchHistories) {
			
			punchHistories.add(new PunchHistory(iPunchHistory.getLogDate(), iPunchHistory.getEmpCode(), iPunchHistory.getFirstName(), iPunchHistory.getLastName(), 
					iPunchHistory.getPosition(), iPunchHistory.getDepartment(), iPunchHistory.getArrivalTime(), iPunchHistory.getDepartureTime(),
					iPunchHistory.getPresencePeriode(), iPunchHistory.getArrivalState(), iPunchHistory.getDepartureState(), 
					iPunchHistory.getPresenceState(), iPunchHistory.isAbsent()
					)
					);
		}
		
		
		return punchHistories;
	}

	@Override
	public List<PunchHistory> present5(Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		

		List<IPunchHistory> iPunchHistories = punchHistoryRepository.present5(startDate, endDate);
		
		PunchHistory punchHistory;
		List<PunchHistory> punchHistories = new ArrayList<>();
		for (IPunchHistory iPunchHistory : iPunchHistories) {
			
			punchHistories.add(new PunchHistory(iPunchHistory.getLogDate(), iPunchHistory.getEmpCode(), iPunchHistory.getFirstName(), iPunchHistory.getLastName(), 
					iPunchHistory.getPosition(), iPunchHistory.getDepartment(), iPunchHistory.getArrivalTime(), iPunchHistory.getDepartureTime(),
					iPunchHistory.getPresencePeriode(), iPunchHistory.getArrivalState(), iPunchHistory.getDepartureState(), 
					iPunchHistory.getPresenceState(), iPunchHistory.isAbsent()
					)
					);
		}
		
		return punchHistories;
	}

	@Override
	public List<PunchHistory> departureMin5(Date startDate, Date endDate) {
		
		List<IPunchHistory> iPunchHistories = punchHistoryRepository.departureMin5(startDate, endDate);
		
		PunchHistory punchHistory;
		List<PunchHistory> punchHistories = new ArrayList<>();
		for (IPunchHistory iPunchHistory : iPunchHistories) {
			
			punchHistories.add(new PunchHistory(iPunchHistory.getLogDate(), iPunchHistory.getEmpCode(), iPunchHistory.getFirstName(), iPunchHistory.getLastName(), 
					iPunchHistory.getPosition(), iPunchHistory.getDepartment(), iPunchHistory.getArrivalTime(), iPunchHistory.getDepartureTime(),
					iPunchHistory.getPresencePeriode(), iPunchHistory.getArrivalState(), iPunchHistory.getDepartureState(), 
					iPunchHistory.getPresenceState(), iPunchHistory.isAbsent()
					)
					);
		}
		
		return punchHistories;
	}

	@Override
	public List<PunchHistory> departureMax5(Date startDate, Date endDate) {
		
		List<IPunchHistory> iPunchHistories = punchHistoryRepository.departureMax5(startDate, endDate);
		
		PunchHistory punchHistory;
		List<PunchHistory> punchHistories = new ArrayList<>();
		for (IPunchHistory iPunchHistory : iPunchHistories) {
			
			punchHistories.add(new PunchHistory(iPunchHistory.getLogDate(), iPunchHistory.getEmpCode(), iPunchHistory.getFirstName(), iPunchHistory.getLastName(), 
					iPunchHistory.getPosition(), iPunchHistory.getDepartment(), iPunchHistory.getArrivalTime(), iPunchHistory.getDepartureTime(),
					iPunchHistory.getPresencePeriode(), iPunchHistory.getArrivalState(), iPunchHistory.getDepartureState(), 
					iPunchHistory.getPresenceState(), iPunchHistory.isAbsent()
					)
					);
		}
		
		return punchHistories;
	}

	@Override
	public List<PunchHistory> absent(Date startDate, Date endDate) {
		
		// TODO Auto-generated method stub
		List<IPunchHistory> iPunchHistories = punchHistoryRepository.absent(startDate, endDate);
		
		PunchHistory punchHistory;
		List<PunchHistory> punchHistories = new ArrayList<>();
		for (IPunchHistory iPunchHistory : iPunchHistories) {
			
			punchHistories.add(new PunchHistory(iPunchHistory.getLogDate(), iPunchHistory.getEmpCode(), iPunchHistory.getFirstName(), iPunchHistory.getLastName(), 
					iPunchHistory.getPosition(), iPunchHistory.getDepartment(), iPunchHistory.getArrivalTime(), iPunchHistory.getDepartureTime(),
					iPunchHistory.getPresencePeriode(), iPunchHistory.getArrivalState(), iPunchHistory.getDepartureState(), 
					iPunchHistory.getPresenceState(), iPunchHistory.isAbsent()
					)
					);
		}
		
		return punchHistories;
	}

}
