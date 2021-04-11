package com.visitor.services;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.visitor.entities.RefreshArrival;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.visitor.entities.ArrivalPunch;
import com.visitor.entities.IArea;
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
	
	@PersistenceContext
    private EntityManager em;
	
	@Override
	public List<ArrivalPunch> top5() {
		
		/*Date date1 = new Date();
		java.sql.Date date = new java.sql.Date(date1.getTime());
		arrivalPunchRepository.refreshArrival(date);*/
		//arrivalPunchRepository.refreshArrival();
		return arrivalPunchRepository.top5();
	}

	@Override
	public List<ArrivalPunch> last5() {
		
		
		return arrivalPunchRepository.last5();
	}

	@Override
	public List<ArrivalPunch> absent() {
		
		return arrivalPunchRepository.findAbsent();
	}

	@Override
	public GeneralStat generalStat() {
		
		Integer early = arrivalPunchRepository.count("1");
		Integer ontime = arrivalPunchRepository.count("2");
		Integer late = arrivalPunchRepository.count("3");
		Integer absent = arrivalPunchRepository.count("NON DISPONIBLE");
		
		GeneralStat generalStat = new GeneralStat(early, ontime, late, absent);
		
		System.out.println("================================= generalStat : "+generalStat);
		return generalStat;
	}

	@Override
	public List<AreaStat> areaStats() {
		
		//arrivalPunchRepository.refreshArrival();
		List<String> areaList = arrivalPunchRepository.getArea();
		//AreaStat areaStat;
		List<AreaStat> areaStats = new ArrayList<>();
		Double longitude;
		Double latitude;
		String area;
		for (String area1 : areaList) {
			
			area = area1.replace("TOUS LES SITES,", "");
			Integer early = arrivalPunchRepository.countByArea(area,"1");

			String absent1 = null;
			Integer ontime = arrivalPunchRepository.countByArea(area,"2");
			Integer late = arrivalPunchRepository.countByArea(area,"3");
			Integer absent = arrivalPunchRepository.countByArea(area,"NON DISPONIBLE");

			List<IArea> areaGps = areaGpsRepository.findByArea(area);

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
	
		return arrivalPunchRepository.arriveEnTempsReel();
	}

	@Override
	public RefreshArrival refreshArrival() {
		return arrivalPunchRepository.refreshArrival();
	}


	/*LISTE TOTAL DES TOP, LAST ET ABSCENT*/
	@Override
	public List<ArrivalPunch> getAllTop() {
		return arrivalPunchRepository.getAllTop();
	}

	@Override
	public List<ArrivalPunch> getAllLast() {
		return arrivalPunchRepository.getAllLast();
	}

	@Override
	public List<ArrivalPunch> getAllAbsent() {
		return arrivalPunchRepository.getAllAbsent();
	}

	public String refresh() {
		
		
		Query query = em.createNativeQuery("select * from doArrivalRefresh(?)")           
                .setParameter(1, Instant.now());

		String result = (String) query.getSingleResult();
		
		return result;
    }

}
