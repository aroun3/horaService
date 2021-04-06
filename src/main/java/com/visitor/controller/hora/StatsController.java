package com.visitor.controller.hora;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.visitor.entities.Phantom;
import com.visitor.entities.RealTimeTransaction;
import com.visitor.payload.ApiResponse;
import com.visitor.payload.AppConstants;
import com.visitor.payload.response.StatEmploye;
import com.visitor.services.PhantomService;
import com.visitor.services.StatsService;

@RestController
@RequestMapping("/api/hora/stats")
public class StatsController {

    @Autowired
    StatsService statsService;

    @Autowired
    PhantomService phantomService;

    
    private DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @GetMapping("/test")
    public ResponseEntity<?> test(){

        List<Phantom> data = phantomService.test();
        return ResponseEntity.ok().body(new ApiResponse("v1",true, AppConstants.STATUS_CODE_SUCCESS[1], data));
    }

    /**
     * Liste des pointage en temps réel : basé sur la table iclock_transaction
     * @return
     */
    @GetMapping("/getListCurrentPunch")
    public ResponseEntity<?> getListCurrentPunch(
        @RequestParam("date") 
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        String date
    ) throws ParseException{

        Date dateSelected = this.format.parse(date);

        List<RealTimeTransaction> listCurrentsPunchs = statsService.loadOnTimePunch(dateSelected);
        
        return ResponseEntity.ok().body(new ApiResponse("v1",true, AppConstants.STATUS_CODE_SUCCESS[1], listCurrentsPunchs));
    }

    //========================== PHANTOM ================================
    
    /**
     * Total pointage : early - ontime - late
     * @return
     */
    @GetMapping("/getCountAttendance")
    public ResponseEntity<?> getCountPunch(
        @RequestParam("date") 
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        String date
        )throws ParseException{

        Integer countEarlyCheckIn = 0;
        Integer countOnTimeCheckIn = 0;
        Integer countLateCheckIn = 0;

        Map<String,Integer> map = new HashMap<>();
        Map<String,Object> data = new HashMap<>();

        Date dateSelected = this.format.parse(date);
        
        countEarlyCheckIn = phantomService.countByEarlyCheckinAndPunchDate(dateSelected);
        countOnTimeCheckIn = phantomService.countByOntimeCheckinAndPunchDate(dateSelected);
        countLateCheckIn = phantomService.countByLateCheckinAndPunchDate(dateSelected);

        map.put("early_checkin",countEarlyCheckIn);
        map.put("ontime_checkin",countOnTimeCheckIn);
        map.put("late_check",countLateCheckIn);
        
    
        data.put("total_employee",statsService.countEmployee());
        data.put("details",map);

        return ResponseEntity.ok().body(
            new ApiResponse(
                "v1",
                true, 
                AppConstants.STATUS_CODE_SUCCESS[1],
                data
                )
            );
    }
    

    /**
     * Liste des arrive et des departs (status)
     * @param date
     * @return
     * @throws ParseException
     */
    @GetMapping("/getListStatusPunch")
    public ResponseEntity<?> listPunchByStatus(
        @RequestParam("date") 
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        String date
    )throws ParseException{
        Date dateSelected = this.format.parse(date);

        List<Phantom> result = phantomService.listPunchByPunchDate(dateSelected);
        
        return ResponseEntity.ok().body(new ApiResponse("v1",true, AppConstants.STATUS_CODE_SUCCESS[1], result));
    }

    /**
     * Liste des premiers arrivés
     * @return
     */
    @GetMapping("/getListFirstAttendance")
    public ResponseEntity<?> listFirstAttendance(
        @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date
    )throws ParseException{

        /*Date dateSelected = this.format.parse(date);
        
        System.out.println("=============================== dateSelected : "+dateSelected);*/

        List<Phantom> result = phantomService.listFirstCheckinPunchAsc(date);
        
        return ResponseEntity.ok().body(new ApiResponse("v1",true, AppConstants.STATUS_CODE_SUCCESS[1], result));
    }

    /**
     * Liste des premiers arrivés
     * @return
     */
    @GetMapping("/getListLastAttendance")
    public ResponseEntity<?> listLastAttendance(
        @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date
    )throws ParseException{

        /*Date dateSelected = this.format.parse(date);

        System.out.println("=============================== dateSelected : "+dateSelected);*/
        
        List<Phantom> result = phantomService.listFirstCheckinPunchDesc(date);
        
        return ResponseEntity.ok().body(new ApiResponse("v1",true, AppConstants.STATUS_CODE_SUCCESS[1], result));
    }
    
    @GetMapping("/employe/{code_emp}")
    public ResponseEntity<?> statsEmploye(@PathVariable("code_emp") String code_emp)throws ParseException{

        
        StatEmploye statEmploye = statsService.statsEmploye(code_emp);
        
        return ResponseEntity.ok().body(new ApiResponse("v1",true, AppConstants.STATUS_CODE_SUCCESS[1], statEmploye));
    }
}
