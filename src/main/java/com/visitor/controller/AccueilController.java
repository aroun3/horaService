package com.visitor.controller;

import java.util.List;

import com.visitor.entities.RefreshArrival;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.visitor.entities.ArrivalPunch;
import com.visitor.payload.response.AreaStat;
import com.visitor.payload.response.GeneralStat;
import com.visitor.service_interfaces.AccueilService;

@RestController
@RequestMapping("/api/v1")
public class AccueilController {
	
	@Autowired
	private AccueilService accueilService;
	
	
	@GetMapping("/accueil/top5")
    public List<ArrivalPunch> top5(){
		List<ArrivalPunch> arrivalPunchs = accueilService.top5();
        return arrivalPunchs;
    }

	@GetMapping("/accueil/last5")
    public List<ArrivalPunch> last5(){
		List<ArrivalPunch> arrivalPunchs = accueilService.last5();
        return arrivalPunchs;
    }
	
	
	@GetMapping("/accueil/absent")
    public List<ArrivalPunch> absent(){
		List<ArrivalPunch> arrivalPunchs = accueilService.absent();
        return arrivalPunchs;
    }

	
	@GetMapping("/accueil/stats/general")
    public GeneralStat generalStats(){
		GeneralStat generalStat = accueilService.generalStat();
        return generalStat;
    }

	@GetMapping("/accueil/stats/area")
    public List<AreaStat> areaStats(){
		List<AreaStat> areaStats= accueilService.areaStats();
        return areaStats;
    }
	
	@GetMapping("/accueil/temps-reel")
    public List<ArrivalPunch> arriveEnTempsReel(){
		List<ArrivalPunch> arrivalPunchs = accueilService.arriveEnTempsReel();
        return arrivalPunchs;
    }

    @GetMapping("/accueil/refreshArrival")
    public RefreshArrival refreshArrival(){
	    return accueilService.refreshArrival();
    }
}
