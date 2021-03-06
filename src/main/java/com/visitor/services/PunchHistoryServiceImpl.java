package com.visitor.services;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.visitor.entities.*;
import com.visitor.payload.response.*;
import com.visitor.repositories.AreaGpsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import com.visitor.repositories.PunchHistoryRepository;
import com.visitor.service_interfaces.PunchHistoryService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PunchHistoryServiceImpl implements PunchHistoryService{
	
	@Autowired
	private PunchHistoryRepository punchHistoryRepository;

	@Autowired
	private AreaGpsRepository areaGpsRepository;

	@Override
	public HistoryStats historyStats(String periode) {
		
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
		
		Date startDate = new Date();
		Date endDate = new Date();
		getStartAndEndDate(periode, startDate, endDate);
		
		System.out.println("=====================================");
		
		early = punchHistoryRepository.countArrivalByState(startDate, endDate, "1");
		ontime = punchHistoryRepository.countArrivalByState(startDate, endDate, "2");
		late = punchHistoryRepository.countArrivalByState(startDate, endDate, "3");
		absent = punchHistoryRepository.countArrivalByState(startDate, endDate, "0");
		
		arrivees = new GeneralHistoryStats(early, ontime, late, absent);
		System.out.println("=====================================");
		early = punchHistoryRepository.countDepatureByState(startDate, endDate, "1");
		ontime = punchHistoryRepository.countDepatureByState(startDate, endDate, "2");
		late = punchHistoryRepository.countDepatureByState(startDate, endDate, "3");
		absent = punchHistoryRepository.countDepatureByState(startDate, endDate, "0");
		
		departs = new GeneralHistoryStats(early, ontime, late, absent);
		System.out.println("=====================================");
		below = punchHistoryRepository.countPresenceByState(startDate, endDate, "BELOW");
		normal = punchHistoryRepository.countPresenceByState(startDate, endDate, "NORMAL");
		over = punchHistoryRepository.countPresenceByState(startDate, endDate, "OVER");
		absent = punchHistoryRepository.countPresenceByState(startDate, endDate, "0");
		
		presences = new PresenceHistory(below, normal, over, absent);
		
		System.out.println("=====================================");
		absences = punchHistoryRepository.countAbsent(startDate, endDate, Boolean.TRUE);
		
		HistoryStats historyStats = new HistoryStats(arrivees, departs, presences, absences);
		
		return historyStats;
	}

	@Override
	public List<AreaHistoryStats> areaHistoryStats(String periode) {
		
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
		
		Date startDate = new Date();
		Date endDate = new Date();
		getStartAndEndDate(periode, startDate, endDate);
		
		for (IArea area : areas) {
			
			
			terminalId = punchHistoryRepository.findDepartmentByAreaId(area.getId());
			System.out.println("=====================================================");
			early = punchHistoryRepository.countArrivalByAreaAndState(area.getId(), startDate, endDate, "1");
			ontime = punchHistoryRepository.countArrivalByAreaAndState(area.getId(), startDate, endDate, "2");
			late = punchHistoryRepository.countArrivalByAreaAndState(area.getId(), startDate, endDate, "3");
			absent = punchHistoryRepository.countArrivalByAreaAndState(area.getId(), startDate, endDate, "0");
			
			arrivees = new GeneralHistoryStats(early, ontime, late, absent);
			System.out.println("=====================================================");
			early = punchHistoryRepository.countDepatureByAreaAndState(area.getId(), startDate, endDate, "1");
			ontime = punchHistoryRepository.countDepatureByAreaAndState(area.getId(), startDate, endDate, "2");
			late = punchHistoryRepository.countDepatureByAreaAndState(area.getId(), startDate, endDate, "3");
			absent = punchHistoryRepository.countDepatureByAreaAndState(area.getId(), startDate, endDate, "0");
			
			departs = new GeneralHistoryStats(early, ontime, late, absent);
			System.out.println("=====================================================");
			below = punchHistoryRepository.countPresenceByAreaAndState(area.getId(), startDate, endDate, "BELOW");
			normal = punchHistoryRepository.countPresenceByAreaAndState(area.getId(), startDate, endDate, "NORMAL");
			over = punchHistoryRepository.countPresenceByAreaAndState(area.getId(), startDate, endDate, "OVER");
			absent = punchHistoryRepository.countPresenceByAreaAndState(area.getId(), startDate, endDate, "0");
			
			presences = new PresenceHistory(below, normal, over, absent);
			System.out.println("=====================================================");
			absences = punchHistoryRepository.countAbsentByArea(area.getId(), startDate, endDate, Boolean.TRUE);
			areaHistoryStats.add(new AreaHistoryStats(area.getArea(), area.getLongitude(), area.getLatitude(), arrivees, departs, presences, absences));
			
		}
		
		return areaHistoryStats;
	}

	@Override
	public List<PunchHistory> arrivalMin5(String periode) {

		Date startDate = new Date();
		Date endDate = new Date();
		getStartAndEndDate(periode, startDate, endDate);
		
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
	public List<PunchHistory> arrivalMax5(String periode) {
		
		Date startDate = new Date();
		Date endDate = new Date();
		getStartAndEndDate(periode, startDate, endDate);
		
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
	public List<PunchHistory> present5(String periode) {
		// TODO Auto-generated method stub
		
		Date startDate = new Date();
		Date endDate = new Date();
		getStartAndEndDate(periode, startDate, endDate);
		
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
	public List<PunchHistory> departureMin5(String periode) {
		
		Date startDate = new Date();
		Date endDate = new Date();
		getStartAndEndDate(periode, startDate, endDate);
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
	public List<PunchHistory> departureMax5(String periode) {
		
		Date startDate = new Date();
		Date endDate = new Date();
		getStartAndEndDate(periode, startDate, endDate);
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
	public List<PunchHistory> absent(String periode) {


		Date startDate = new Date();
		Date endDate = new Date();
		getStartAndEndDate(periode, startDate, endDate);
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

	@Override
	public List<PunchHistory> absentLimit10(String periode) {
		Date startDate = new Date();
		Date endDate = new Date();
		getStartAndEndDate(periode, startDate, endDate);
		List<IPunchHistory> iPunchHistories = punchHistoryRepository.absentLimit10(startDate, endDate);

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
	public List<GraphStat> graphStats(String periode) {
		// TODO Auto-generated method stub
		
		List<GraphStat> graphStats = new ArrayList<>();
		
		switch (periode){
		
			case "jour":
				graphStats = graphStatsJour();
				break;
			case "semaine":
				graphStats = graphStatsSemaine();
				break;
			case "mois":
				graphStats = graphStatsMois();
				break;
			case "annee":
				graphStats = graphStatsAnnee();
				break;
		}
		
		
		
		return graphStats;
	}


	public List<GraphStat> graphStatsJour(){
		
		List<GraphStat> graphStats = new ArrayList<>(); 
		
		Date endDate = getDateWithoutTimeUsingCalendar();
		Date startDate = addDay(endDate, -1);
		
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
		
		String absent1 = null;
		early = punchHistoryRepository.countArrivalByState(startDate, endDate, "1");
		ontime = punchHistoryRepository.countArrivalByState(startDate, endDate, "2");
		late = punchHistoryRepository.countArrivalByState(startDate, endDate, "3");
		absent = punchHistoryRepository.countArrivalByState(startDate, endDate, "0");
		
		arrivees = new GeneralHistoryStats(early, ontime, late, absent);
		
		early = punchHistoryRepository.countDepatureByState(startDate, endDate, "1");
		ontime = punchHistoryRepository.countDepatureByState(startDate, endDate, "2");
		late = punchHistoryRepository.countDepatureByState(startDate, endDate, "3");
		absent = punchHistoryRepository.countDepatureByState(startDate, endDate, "0");
		
		departs = new GeneralHistoryStats(early, ontime, late, absent);
		
		below = punchHistoryRepository.countPresenceByState(startDate, endDate, "BELOW");
		normal = punchHistoryRepository.countPresenceByState(startDate, endDate, "NORMAL");
		over = punchHistoryRepository.countPresenceByState(startDate, endDate, "OVER");
		absent = punchHistoryRepository.countPresenceByState(startDate, endDate, "NON DISPONIBLE");
		
		presences = new PresenceHistory(below, normal, over, absent);
		absences = punchHistoryRepository.countAbsent(startDate, endDate, Boolean.TRUE);
		
		String title = startDate.toString();
		GraphStat graphStat = new  GraphStat(title, arrivees, departs, absences,presences);
		graphStats.add(graphStat);
		return graphStats;
	}
	
	
	public List<GraphStat> graphStatsSemaine(){
		
		List<GraphStat> graphStats = new ArrayList<>(); 
		
		DateFormatSymbols dfsFR = new DateFormatSymbols(Locale.FRENCH);
		String[] joursSemaineFR = dfsFR.getWeekdays();
		Date start = firstDayOfWeek();
		GeneralHistoryStats arrivees;
		GeneralHistoryStats departs;
		PresenceHistory presences;
		Integer absences;
		
		Integer early;
		Integer ontime;
		Integer late;
		Integer absent;
		Date startDate;
		Date endDate;
		
		Integer below;
		Integer normal;
		Integer over;
		String title;
		startDate = start;
		endDate = addDay(startDate, 1);
		int test = 7;
		
		for (int i = 1; i < joursSemaineFR.length; i++) {
			
			
			early = punchHistoryRepository.countArrivalByState(startDate, endDate, "1");
			ontime = punchHistoryRepository.countArrivalByState(startDate, endDate, "2");
			late = punchHistoryRepository.countArrivalByState(startDate, endDate, "3");
			absent = punchHistoryRepository.countArrivalByState(startDate, endDate, "0");
			
			arrivees = new GeneralHistoryStats(early, ontime, late, absent);
			
			early = punchHistoryRepository.countDepatureByState(startDate, endDate, "1");
			ontime = punchHistoryRepository.countDepatureByState(startDate, endDate, "2");
			late = punchHistoryRepository.countDepatureByState(startDate, endDate, "3");
			absent = punchHistoryRepository.countDepatureByState(startDate, endDate, "0");
			
			departs = new GeneralHistoryStats(early, ontime, late, absent);
			
			below = punchHistoryRepository.countPresenceByState(startDate, endDate, "BELOW");
			normal = punchHistoryRepository.countPresenceByState(startDate, endDate, "NORMAL");
			over = punchHistoryRepository.countPresenceByState(startDate, endDate, "OVER");
			absent = punchHistoryRepository.countPresenceByState(startDate, endDate, "NON DISPONIBLE");
			
			presences = new PresenceHistory(below, normal, over, absent);
			
			absences = punchHistoryRepository.countAbsent(startDate, endDate, Boolean.TRUE);
			
			
			if(i == test)
				title = joursSemaineFR[1];
			else
				title = joursSemaineFR[i+1];
			System.out.println("========================================== startDate : "+startDate);
			GraphStat graphStat = new  GraphStat(title, arrivees, departs, absences,presences);
			
			graphStats.add(graphStat);
			
			startDate = endDate;
			endDate = addDay(startDate, 1);
		}
		
		return graphStats;
	}
	
	public List<GraphStat> graphStatsMois(){
		
		List<GraphStat> graphStats = new ArrayList<>();
		Calendar c = Calendar.getInstance();
		int monthMaxDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		Date start = firstDayOfMonth();
		GeneralHistoryStats arrivees;
		GeneralHistoryStats departs;
		PresenceHistory presences;
		Integer absences;
		
		Integer early;
		Integer ontime;
		Integer late;
		Integer absent;
		Date startDate;
		Date endDate;
		Integer below;
		Integer normal;
		Integer over;
		String title;
		startDate = start;
		endDate = addDay(startDate, 1);
		
		for (int i = 1; i <= monthMaxDays; i++){
			
			
			early = punchHistoryRepository.countArrivalByState(startDate, endDate, "1");
			ontime = punchHistoryRepository.countArrivalByState(startDate, endDate, "2");
			late = punchHistoryRepository.countArrivalByState(startDate, endDate, "3");
			absent = punchHistoryRepository.countArrivalByState(startDate, endDate, "0");
			
			arrivees = new GeneralHistoryStats(early, ontime, late, absent);
			
			early = punchHistoryRepository.countDepatureByState(startDate, endDate, "1");
			ontime = punchHistoryRepository.countDepatureByState(startDate, endDate, "2");
			late = punchHistoryRepository.countDepatureByState(startDate, endDate, "3");
			absent = punchHistoryRepository.countDepatureByState(startDate, endDate, "0");
			
			departs = new GeneralHistoryStats(early, ontime, late, absent);
			
			below = punchHistoryRepository.countPresenceByState(startDate, endDate, "BELOW");
			normal = punchHistoryRepository.countPresenceByState(startDate, endDate, "NORMAL");
			over = punchHistoryRepository.countPresenceByState(startDate, endDate, "OVER");
			absent = punchHistoryRepository.countPresenceByState(startDate, endDate, "NON DISPONIBLE");
			
			presences = new PresenceHistory(below, normal, over, absent);
			
			absences = punchHistoryRepository.countAbsent(startDate, endDate, Boolean.TRUE);
			
			title = i+"";
			
			System.out.println("====================================== title : "+title);
			System.out.println("====================================== startDate : "+startDate);
			GraphStat graphStat = new  GraphStat(title, arrivees, departs, absences,presences);
			
			graphStats.add(graphStat);
			
			startDate = endDate;
			endDate = addDay(startDate, 1);
		}
		return graphStats;
	}
	
	
	public List<GraphStat> graphStatsAnnee(){
		List<GraphStat> graphStats = new ArrayList<>();
		
		DateFormatSymbols dfsFR = new DateFormatSymbols(Locale.FRENCH);
		String[] moisCourtsFR = dfsFR.getShortMonths();
		
		Calendar calendar = Calendar.getInstance();
		
		Date start = toDate(calendar.get(Calendar.YEAR)+"-01-01", "EN");
		GeneralHistoryStats arrivees;
		GeneralHistoryStats departs;
		PresenceHistory presences;
		Integer absences;
		
		Integer early;
		Integer ontime;
		Integer late;
		Integer absent;
		Date startDate;
		Date endDate;
		
		Integer below;
		Integer normal;
		Integer over;
		
		startDate = start;
		endDate = addMonth(startDate, 1);
		String title;
		
		for (int i = 0; i < 12; i++){
			
			
			early = punchHistoryRepository.countArrivalByState(startDate, endDate, "1");
			ontime = punchHistoryRepository.countArrivalByState(startDate, endDate, "2");
			late = punchHistoryRepository.countArrivalByState(startDate, endDate, "3");
			absent = punchHistoryRepository.countArrivalByState(startDate, endDate, "0");
			
			arrivees = new GeneralHistoryStats(early, ontime, late, absent);
			
			early = punchHistoryRepository.countDepatureByState(startDate, endDate, "1");
			ontime = punchHistoryRepository.countDepatureByState(startDate, endDate, "2");
			late = punchHistoryRepository.countDepatureByState(startDate, endDate, "3");
			absent = punchHistoryRepository.countDepatureByState(startDate, endDate, "0");
			
			departs = new GeneralHistoryStats(early, ontime, late, absent);
			
			below = punchHistoryRepository.countPresenceByState(startDate, endDate, "BELOW");
			normal = punchHistoryRepository.countPresenceByState(startDate, endDate, "NORMAL");
			over = punchHistoryRepository.countPresenceByState(startDate, endDate, "OVER");
			absent = punchHistoryRepository.countPresenceByState(startDate, endDate, "NON DISPONIBLE");
			
			presences = new PresenceHistory(below, normal, over, absent);
			
			absences = punchHistoryRepository.countAbsent(startDate, endDate, Boolean.TRUE);
			
			System.out.println("========================================== startDate : "+startDate);
			title = moisCourtsFR[i];
			GraphStat graphStat = new  GraphStat(title, arrivees, departs, absences,presences);
			
			graphStats.add(graphStat);
			
			startDate = endDate;
			endDate = addDay(startDate, 1);
		}
		
		return graphStats;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static Date getDateWithoutTimeUsingCalendar() {
	    Calendar calendar = Calendar.getInstance();
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);

	    return calendar.getTime();
	}
	
	public static Date addDay(Date date, int i) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, i);
        return cal.getTime();
    }
	
	public static Date firstDayOfWeek(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
		cal.clear(Calendar.MINUTE);
		cal.clear(Calendar.SECOND);
		cal.clear(Calendar.MILLISECOND);

		// get start of this week in milliseconds
		cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
		return cal.getTime();
	};
	
	
	public static Date firstDayOfMonth(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
		cal.clear(Calendar.MINUTE);
		cal.clear(Calendar.SECOND);
		cal.clear(Calendar.MILLISECOND);

		// get start of this week in milliseconds
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	};
	
	public static Date addMonth(Date date, int i) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, i);
        return cal.getTime();
    }
	
	public static Date toDate(String date, String lang){

        try{

            date = date.split("T")[0];
            String pattern = "dd/MM/yyyy";
            switch(lang){
                case "fr":
                case "FR":
                    String[] T = date.split("/");
                    if(T.length == 3){
                        date = T[2] + "-" + T[1] + "-" + T[0];
                        //date += "T"+ hour +":00.0Z";
                    }
                    break;
                case "en":
                case "EN":
                    pattern = "yyyy-MM-dd";
                    break;
            }


            System.out.println("XXXX =======>>>  ____  " + date);
            System.out.println(new SimpleDateFormat(pattern).parse(date));
            return new SimpleDateFormat(pattern).parse(date);

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
	
	public void getStartAndEndDate(String periode, Date startDate, Date endDate){
	
		switch (periode){
		
		case "jour":
			endDate = getDateWithoutTimeUsingCalendar();
			startDate = addDay(endDate, -1);
			break;
		case "semaine":
			startDate = firstDayOfWeek();
			endDate = addDay(startDate, 6);
			break;
		case "mois":
			startDate = firstDayOfMonth();
			endDate = addMonth(startDate, 1);
			break;
		case "annee":
			
			Calendar calendar = Calendar.getInstance();
			startDate = toDate(calendar.get(Calendar.YEAR)+"-01-01", "EN");
			endDate = toDate(calendar.get(Calendar.YEAR)+1+"-01-01", "EN");
			break;
	}
	}



	/*===========================
		DETAIL PAR EMPLOYEE
	=========================== */

	@Override
	public HistoryStats areaHistoryStatsByEmployee(String periode, String empCode) {

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

		Date startDate = new Date();
		Date endDate = new Date();
		getStartAndEndDate(periode, startDate, endDate);

		System.out.println("=====================================");

		early = punchHistoryRepository.countArrivalByStateAndEmpCode(startDate, endDate, "1", empCode);
		ontime = punchHistoryRepository.countArrivalByStateAndEmpCode(startDate, endDate, "2", empCode);
		late = punchHistoryRepository.countArrivalByStateAndEmpCode(startDate, endDate, "3", empCode);
		absent = punchHistoryRepository.countArrivalByStateAndEmpCode(startDate, endDate, "0",empCode);

		arrivees = new GeneralHistoryStats(early, ontime, late, absent);
		System.out.println("=====================================");
		early = punchHistoryRepository.countDepatureByStateAndEmpCode(startDate, endDate, "1", empCode);
		ontime = punchHistoryRepository.countDepatureByStateAndEmpCode(startDate, endDate, "2",empCode);
		late = punchHistoryRepository.countDepatureByStateAndEmpCode(startDate, endDate, "3", empCode);
		absent = punchHistoryRepository.countDepatureByStateAndEmpCode(startDate, endDate, "0", empCode);

		departs = new GeneralHistoryStats(early, ontime, late, absent);
		System.out.println("=====================================");
		below = punchHistoryRepository.countPresenceByStateAndEmpCode(startDate, endDate, "BELOW", empCode);
		normal = punchHistoryRepository.countPresenceByStateAndEmpCode(startDate, endDate, "NORMAL", empCode);
		over = punchHistoryRepository.countPresenceByStateAndEmpCode(startDate, endDate, "OVER", empCode);
		absent = punchHistoryRepository.countPresenceByStateAndEmpCode(startDate, endDate, "0", empCode);

		presences = new PresenceHistory(below, normal, over, absent);

		System.out.println("=====================================");
		absences = punchHistoryRepository.countAbsentAndEmpCode(startDate, endDate, Boolean.TRUE,empCode);

		HistoryStats historyStats = new HistoryStats(arrivees, departs, presences, absences);

		return historyStats;
	}

	@Override
	public List<PersonnelArea> getAllEmployeArea() {
		List<PersonnelArea> personnelAreaList = new ArrayList<>();
		List<Object[]> listArea = areaGpsRepository.getAllEmployeArea();
		for(Object rs []: listArea){
			PersonnelArea personnelArea = new PersonnelArea();
			personnelArea.setId(Integer.valueOf(rs[0]+""));
			personnelArea.setAreaCode(rs[1]+"");
			personnelArea.setAreaName(rs[2]+"");
			//personnelArea.isDefault();
			personnelArea.setParentAreaId(rs[3]+"");
			personnelAreaList.add(personnelArea);

		}
		return personnelAreaList;
	}

	/*LISTE DES POINTAGES DE L'EMPLOYE PAR SON CODE*/

	@Override
	public List<HistoryPointage> historyPointageByEmpCode(String empCode) {
		return punchHistoryRepository.historyPointageByEmpCode(empCode);
	}

	@Override
	public List<HistoryAllPointage> historyAllPointage(String empCode) {
		return punchHistoryRepository.historyAllPointage(empCode);
	}

	@Override
	public List<EmployeTop> employeTop5ByPresencePeriode(String periode) {
		Date startDate = new Date();
		Date endDate = new Date();
		getStartAndEndDate(periode, startDate, endDate);
		return punchHistoryRepository.employeTop5ByPresencePeriode(startDate,endDate);
	}

	@Override
	public List<EmployeTop> employeLast5ByPresencePeriode(String periode) {
		Date startDate = new Date();
		Date endDate = new Date();
		getStartAndEndDate(periode, startDate, endDate);
		return punchHistoryRepository.employeLast5ByPresencePeriode(startDate,endDate);
	}

	@Override
	@Transactional
	public TotalTopLast totalEmployeTopLastByPresencePeriode(String periode) {
		Date startDate = new Date();
		Date endDate = new Date();
		getStartAndEndDate(periode, startDate, endDate);
		List<EmployeTop> listEmployeTop = punchHistoryRepository.totalEmployeTopByPresencePeriode(startDate,endDate);
		List<EmployeTop> listEmployeLast = punchHistoryRepository.totalEmployeLastByPresencePeriode(startDate,endDate);
		TotalTopLast totalTopLast = new TotalTopLast();
		totalTopLast.setListEmployeTop(listEmployeTop);
		totalTopLast.setListEmployeLast(listEmployeLast);
		return totalTopLast;
	}

	@Override
	public List<IPunchHistory> totalArrivalMin(String periode) {
		Date startDate = new Date();
		Date endDate = new Date();
		getStartAndEndDate(periode, startDate, endDate);
		return punchHistoryRepository.totalArrivalMin(startDate, endDate);
	}

	@Override
	public List<IPunchHistory> totalArrivalMax(String periode) {
		Date startDate = new Date();
		Date endDate = new Date();
		getStartAndEndDate(periode, startDate, endDate);
		return punchHistoryRepository.totalArrivalMax(startDate, endDate);
	}

	@Override
	public List<IPunchHistory> totalDepartureMin(String periode) {
		Date startDate = new Date();
		Date endDate = new Date();
		getStartAndEndDate(periode, startDate, endDate);
		return punchHistoryRepository.totalDepartureMin(startDate, endDate);
	}

	@Override
	public List<IPunchHistory> totalDepartureMax(String periode) {
		Date startDate = new Date();
		Date endDate = new Date();
		getStartAndEndDate(periode, startDate, endDate);
		return punchHistoryRepository.totalDepartureMax(startDate, endDate);
	}


}
