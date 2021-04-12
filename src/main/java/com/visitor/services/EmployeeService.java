package com.visitor.services;

import com.visitor.entities.Employee;
import com.visitor.payload.response.EmployeeResponse;
import com.visitor.payload.response.GeneralHistoryStats;
import com.visitor.payload.response.GraphStat;
import com.visitor.payload.response.PresenceHistory;
import com.visitor.repositories.EmployeeRepository;
import com.visitor.service_interfaces.EmployeeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormatSymbols;
import java.util.*;

import static com.visitor.services.PunchHistoryServiceImpl.*;

@Service("employeeServiceInterface")
public class EmployeeService implements EmployeeServiceInterface {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }


    /* RECUPERER LA LISTE DES EMPLOYEES ET LES DEPARTEMENT*/
    @Override
    public List<EmployeeResponse> getListEmployeeAndDepartment() {
        List<EmployeeResponse> employeeResponseList = new ArrayList<>();
        List<Object[]> employeeList =  employeeRepository.getListEmployeeAndDepartment();
        for(Object[] rs : employeeList){
            EmployeeResponse emp  = new EmployeeResponse();
            emp.setFirstName(rs[0]+"");
            emp.setLastName(rs[1]+"");
            emp.setDepartment(rs[2]+"");
            employeeResponseList.add(emp);
        }
        return employeeResponseList;
    }


    /* RECUPERER LA LISTE DES EMPLOYEES*/
    @Override
    public List<EmployeeResponse> getAllEmployee() {
        List<EmployeeResponse> employeeResponseList = new ArrayList<>();
        List<Object[]> employeeList =  employeeRepository.getAllEmployee();
        for(Object[] rs : employeeList){
            EmployeeResponse emp  = new EmployeeResponse();
            emp.setEmpCode(rs[0]+"");
            emp.setFirstName(rs[1]+"");
            emp.setLastName(rs[2]+"");
            emp.setDepartment(rs[3]+"");
            emp.setFonction(rs[4]+"");
            emp.setGender(rs[5]+"");
            emp.setMobile(rs[6]+"");
            emp.setCity(rs[7]+"");
            employeeResponseList.add(emp);
        }
        return employeeResponseList;
    }


    /* RECHERCHE EMPLOYEE PAR EMPLOYEE CODE*/
    @Override
    public List<EmployeeResponse> getEmployeeByCode(String empCode) {
        List<EmployeeResponse> employeeResponseList = new ArrayList<>();
        List<Object[]> employeeList =  employeeRepository.getEmployeeByCode(empCode);
        for(Object[] rs : employeeList){
            EmployeeResponse emp  = new EmployeeResponse();
            emp.setEmpCode(rs[0]+"");
            emp.setFirstName(rs[1]+"");
            emp.setLastName(rs[2]+"");
            emp.setDepartment(rs[3]+"");
            emp.setFonction(rs[4]+"");
            emp.setGender(rs[5]+"");
            emp.setMobile(rs[6]+"");
            emp.setCity(rs[7]+"");
            employeeResponseList.add(emp);
        }
        return employeeResponseList;
    }

    /* RECHERCHE EMPLOYEE PAR MOT CLE*/
    @Override
    public List<EmployeeResponse> searchEmployee(String motcle) {
        List<EmployeeResponse> employeeResponseList = new ArrayList<>();
        List<Object[]> employeeList =  employeeRepository.searchEmployee(motcle);
        for(Object[] rs : employeeList){
            EmployeeResponse emp  = new EmployeeResponse();
            emp.setEmpCode(rs[0]+"");
            emp.setFirstName(rs[1]+"");
            emp.setLastName(rs[2]+"");
            emp.setGender(rs[3]+"");
            emp.setMobile(rs[4]+"");
            employeeResponseList.add(emp);
        }
        return employeeResponseList;
    }


    /*LISTE DES GRAPHE PAR EMPLOYE*/

    @Override
    public List<GraphStat> employeGraphStats(String periode,String empCode) {
        // TODO Auto-generated method stub

        List<GraphStat> graphStats = new ArrayList<>();

        switch (periode){

            case "jour":
                graphStats = graphStatsJour(empCode);
                break;
            case "semaine":
                graphStats = graphStatsSemaine(empCode);
                break;
            case "mois":
                graphStats = graphStatsMois(empCode);
                break;
            case "annee":
                graphStats = graphStatsAnnee(empCode);
                break;
        }

        return graphStats;
    }


    public List<GraphStat> graphStatsJour(String empCode){

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
        early = employeeRepository.countArrivalByState(startDate, endDate, "1",empCode);
        ontime = employeeRepository.countArrivalByState(startDate, endDate, "2",empCode);
        late = employeeRepository.countArrivalByState(startDate, endDate, "3",empCode);
        absent = employeeRepository.countArrivalByState(startDate, endDate, "0",empCode);

        arrivees = new GeneralHistoryStats(early, ontime, late, absent);

        early = employeeRepository.countDepatureByState(startDate, endDate, "1",empCode);
        ontime = employeeRepository.countDepatureByState(startDate, endDate, "2",empCode);
        late = employeeRepository.countDepatureByState(startDate, endDate, "3",empCode);
        absent = employeeRepository.countDepatureByState(startDate, endDate, "0",empCode);

        departs = new GeneralHistoryStats(early, ontime, late, absent);

        below = employeeRepository.countPresenceByState(startDate, endDate, "BELOW",empCode);
        normal = employeeRepository.countPresenceByState(startDate, endDate, "NORMAL",empCode);
        over = employeeRepository.countPresenceByState(startDate, endDate, "OVER",empCode);
        absent = employeeRepository.countPresenceByState(startDate, endDate, "NON DISPONIBLE",empCode);

        presences = new PresenceHistory(below, normal, over, absent);
        absences = employeeRepository.countAbsent(startDate, endDate, Boolean.TRUE,empCode);

        String title = startDate.toString();
        GraphStat graphStat = new  GraphStat(title, arrivees, departs, absences,presences);
        graphStats.add(graphStat);
        return graphStats;
    }


    public List<GraphStat> graphStatsSemaine(String empCode){

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


            early = employeeRepository.countArrivalByState(startDate, endDate, "1",empCode);
            ontime = employeeRepository.countArrivalByState(startDate, endDate, "2",empCode);
            late = employeeRepository.countArrivalByState(startDate, endDate, "3",empCode);
            absent = employeeRepository.countArrivalByState(startDate, endDate, "0",empCode);

            arrivees = new GeneralHistoryStats(early, ontime, late, absent);

            early = employeeRepository.countDepatureByState(startDate, endDate, "1",empCode);
            ontime = employeeRepository.countDepatureByState(startDate, endDate, "2",empCode);
            late = employeeRepository.countDepatureByState(startDate, endDate, "3",empCode);
            absent = employeeRepository.countDepatureByState(startDate, endDate, "0",empCode);

            departs = new GeneralHistoryStats(early, ontime, late, absent);

            below = employeeRepository.countPresenceByState(startDate, endDate, "BELOW",empCode);
            normal = employeeRepository.countPresenceByState(startDate, endDate, "NORMAL",empCode);
            over = employeeRepository.countPresenceByState(startDate, endDate, "OVER",empCode);
            absent = employeeRepository.countPresenceByState(startDate, endDate, "NON DISPONIBLE",empCode);

            presences = new PresenceHistory(below, normal, over, absent);

            absences = employeeRepository.countAbsent(startDate, endDate, Boolean.TRUE,empCode);


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

    public List<GraphStat> graphStatsMois(String empCode){

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


            early = employeeRepository.countArrivalByState(startDate, endDate, "1",empCode);
            ontime = employeeRepository.countArrivalByState(startDate, endDate, "2",empCode);
            late = employeeRepository.countArrivalByState(startDate, endDate, "3",empCode);
            absent = employeeRepository.countArrivalByState(startDate, endDate, "0",empCode);

            arrivees = new GeneralHistoryStats(early, ontime, late, absent);

            early = employeeRepository.countDepatureByState(startDate, endDate, "1",empCode);
            ontime = employeeRepository.countDepatureByState(startDate, endDate, "2",empCode);
            late = employeeRepository.countDepatureByState(startDate, endDate, "3",empCode);
            absent = employeeRepository.countDepatureByState(startDate, endDate, "0",empCode);

            departs = new GeneralHistoryStats(early, ontime, late, absent);

            below = employeeRepository.countPresenceByState(startDate, endDate, "BELOW",empCode);
            normal = employeeRepository.countPresenceByState(startDate, endDate, "NORMAL",empCode);
            over = employeeRepository.countPresenceByState(startDate, endDate, "OVER",empCode);
            absent = employeeRepository.countPresenceByState(startDate, endDate, "NON DISPONIBLE",empCode);

            presences = new PresenceHistory(below, normal, over, absent);

            absences = employeeRepository.countAbsent(startDate, endDate, Boolean.TRUE,empCode);

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


    public List<GraphStat> graphStatsAnnee(String empCode){
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


            early = employeeRepository.countArrivalByState(startDate, endDate, "1",empCode);
            ontime = employeeRepository.countArrivalByState(startDate, endDate, "2",empCode);
            late = employeeRepository.countArrivalByState(startDate, endDate, "3",empCode);
            absent = employeeRepository.countArrivalByState(startDate, endDate, "0",empCode);

            arrivees = new GeneralHistoryStats(early, ontime, late, absent);

            early = employeeRepository.countDepatureByState(startDate, endDate, "1",empCode);
            ontime = employeeRepository.countDepatureByState(startDate, endDate, "2",empCode);
            late = employeeRepository.countDepatureByState(startDate, endDate, "3",empCode);
            absent = employeeRepository.countDepatureByState(startDate, endDate, "0",empCode);

            departs = new GeneralHistoryStats(early, ontime, late, absent);

            below = employeeRepository.countPresenceByState(startDate, endDate, "BELOW",empCode);
            normal = employeeRepository.countPresenceByState(startDate, endDate, "NORMAL",empCode);
            over = employeeRepository.countPresenceByState(startDate, endDate, "OVER",empCode);
            absent = employeeRepository.countPresenceByState(startDate, endDate, "0",empCode);

            presences = new PresenceHistory(below, normal, over, absent);

            absences = employeeRepository.countAbsent(startDate, endDate, Boolean.TRUE,empCode);

            System.out.println("========================================== startDate : "+startDate);
            title = moisCourtsFR[i];
            GraphStat graphStat = new  GraphStat(title, arrivees, departs, absences,presences);

            graphStats.add(graphStat);

            startDate = endDate;
            endDate = addDay(startDate, 1);
        }

        return graphStats;
    }




}
