package com.visitor.controller.hora;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import com.visitor.config.Const;
import com.visitor.entities.Phantom;
import com.visitor.entities.RealTimeTransaction;
import com.visitor.entities.Transactions;
import com.visitor.payload.ApiResponse;
import com.visitor.payload.AppConstants;
import com.visitor.services.StatsService;
import com.visitor.utils.CurrentDate;

@RestController
@RequestMapping("/api/hora/stats")
public class StatsController {

    @Autowired
    StatsService statsService;

    private DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * List presence : dont le pointage est complete
     * @return
     */
    @PostMapping("/getListAttendance")
    public ResponseEntity<?> getListAttendance(){
        return ResponseEntity.ok().body(new ApiResponse(true, "Vous etes sur la page d'accueil"));
    }
    
    /**
     * List depart
     * @return
     */
    public ResponseEntity<?> getListCheckOut (){
        return ResponseEntity.ok().body(new ApiResponse(true, "Vous etes sur la page d'accueil"));
    }

    /**
     * List arrivée
     * @return
     */
    public ResponseEntity<?> getListCheckIn (){
        return ResponseEntity.ok().body(new ApiResponse(true, "Vous etes sur la page d'accueil"));
    }

    /**
     * List current punchs
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

    /**
     * Liste des pointages : tout type de pointage confondu
     * @return
     * @throws ParseException
     */
    @GetMapping("/getListPunch")
    public ResponseEntity<?> getListPunch (
        @RequestParam("date") 
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        String date,
        @RequestParam (defaultValue = "5") Integer limit, 
        @RequestParam( )  String status) throws ParseException{

        Date dateSelected = this.format.parse(date);
        List<Transactions> data = statsService.findPunchByStatus(dateSelected,status,limit);
        
        return ResponseEntity.ok(new ApiResponse("v1",true, AppConstants.STATUS_CODE_SUCCESS[1], data));
    }

    
    /**
     * Total pointage
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
        
        countEarlyCheckIn = statsService.countByEarlyCheckin(dateSelected);
        countOnTimeCheckIn = statsService.countByOntimeCheckin(dateSelected);
        countLateCheckIn = statsService.countByLateCheckin(dateSelected);

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
     * Total absent
     * @return
     */
    public ResponseEntity<?> getCountAbsent (){
        return ResponseEntity.ok().body(new ApiResponse(true, "Vous etes sur la page d'accueil"));
    }

    @GetMapping("/getListStatusPunch")
    public ResponseEntity<?> listPunchByStatus(
        @RequestParam("date") 
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        String date
    )throws ParseException{
        Date dateSelected = this.format.parse(date);

        List<Phantom> result = statsService.listPunchByStatus(dateSelected);
        
        return ResponseEntity.ok().body(new ApiResponse("v1",true, AppConstants.STATUS_CODE_SUCCESS[1], result));
    }

    /**
     * Liste des premiers arrivés
     * @return
     */
    @GetMapping("/getListFirstAttendance")
    public ResponseEntity<?> listFirstAttendance(
        @RequestParam("date") 
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        String date
    )throws ParseException{

        Date dateSelected = this.format.parse(date);

        List<Phantom> result = statsService.findByPunchDateAndCheckinStatusOrderByFirstPunchAsc(dateSelected);
        
        return ResponseEntity.ok().body(new ApiResponse("v1",true, AppConstants.STATUS_CODE_SUCCESS[1], result));
    }

    /**
     * Liste des premiers arrivés
     * @return
     */
    @GetMapping("/getListLastAttendance")
    public ResponseEntity<?> listLastAttendance(
        @RequestParam("date") 
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        String date
    )throws ParseException{

        Date dateSelected = this.format.parse(date);

        List<Phantom> result = statsService.findByPunchDateAndCheckinStatusOrderByFirstPunchDesc(dateSelected);
        
        return ResponseEntity.ok().body(new ApiResponse("v1",true, AppConstants.STATUS_CODE_SUCCESS[1], result));
    }

}
