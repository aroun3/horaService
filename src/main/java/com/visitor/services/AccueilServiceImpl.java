package com.visitor.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.visitor.entities.AreaGps;
import com.visitor.entities.ArrivalPunch;
import com.visitor.payload.response.AreaStat;
import com.visitor.payload.response.GeneralStat;
import com.visitor.repositories.AreaGpsRepository;
import com.visitor.repositories.ArrivalPunchRepository;
import com.visitor.service_interfaces.AccueilService;

@Service
public class AccueilServiceImpl implements AccueilService{

	@Autowired
	private ArrivalPunchRepository arrivalPunchRepository;
	
	@Autowired
	private AreaGpsRepository areaGpsRepository;
	
	@Override
	public List<ArrivalPunch> top5() {
		return arrivalPunchRepository.top5();
	}

	@Override
	public List<ArrivalPunch> last5() {
		// TODO Auto-generated method stub
		return arrivalPunchRepository.last5();
	}

	@Override
	public List<ArrivalPunch> absent() {
		// TODO Auto-generated method stub
		return arrivalPunchRepository.findByPunchStatus("ABSENT");
	}

	@Override
	public GeneralStat generalStat() {
		// TODO Auto-generated method stub
		
		Integer early = arrivalPunchRepository.count("EARLY");
		Integer ontime = arrivalPunchRepository.count("ONTIME");
		Integer late = arrivalPunchRepository.count("LATE");
		Integer absent = arrivalPunchRepository.count("ABSENT");
		
		GeneralStat generalStat = new GeneralStat(early, ontime, late, absent);
		return generalStat;
	}

	@Override
	public List<AreaStat> areaStats() {
		
		List<String> areaList = arrivalPunchRepository.getArea();
		AreaStat areaStat;
		List<AreaStat> areaStats = new ArrayList<>();
		Double longitude;
		Double latitude;
		
		for (String area : areaList) {
			Integer early = arrivalPunchRepository.countByArea(area,"EARLY");
			Integer ontime = arrivalPunchRepository.countByArea(area,"ONTIME");
			Integer late = arrivalPunchRepository.countByArea(area,"LATE");
			Integer absent = arrivalPunchRepository.countByArea(area,"ABSENT");
			
			List<AreaGps> areaGps = areaGpsRepository.findByArea(area);
			
			if (areaGps.isEmpty()) {
				longitude = null;
				latitude = null;
			}else{
				longitude = areaGps.get(0).getLongitude();
				latitude = areaGps.get(0).getLatitude();
			}
			areaStats.add(new AreaStat(early, ontime, late, absent, area, longitude, latitude));
		}


		return areaStats;
	}

	@Override
	public List<ArrivalPunch> arriveEnTempsReel() {
		// TODO Auto-generated method stub
		return arrivalPunchRepository.arriveEnTempsReel();
	}

}
