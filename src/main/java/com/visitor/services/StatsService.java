package com.visitor.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.visitor.entities.Departement;
import com.visitor.entities.Employee;
import com.visitor.entities.Phantom;
import com.visitor.entities.RealTimeTransaction;
import com.visitor.payload.response.StatEmploye;
import com.visitor.repositories.EmployeeRepository;
import com.visitor.repositories.RealTimeTransactionRepository;
import com.visitor.repositories.StatsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("statsService")
public class StatsService { //implements StatsServiceInterface

    @Autowired
    StatsRepository statsRepository;

    @Autowired
    RealTimeTransactionRepository realTimeTransactionRepository;
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private PhantomService phantomService;

    @Autowired
    private DepartementService departementService;
    /**
     * Liste des employés
     * @return
     */
    public Integer countEmployee(){
        return statsRepository.countEmployee();
    }

    /**
     * load list current punchs
     * @return
     */
    public List<RealTimeTransaction> loadOnTimePunch(Date date){
        return realTimeTransactionRepository.findByPunchDate(date);
    }
    
    
    public StatEmploye statsEmploye(String codeEmploye){
    	
    	Optional<Employee> employeeOptional = employeeRepository.findByEmpCode(codeEmploye);
    	StatEmploye statEmploye = new StatEmploye();
    	
    	if (employeeOptional.isPresent()) {
    		Departement departement = departementService.findById(employeeOptional.get().getDepId());
			List<Phantom> phantoms = phantomService.findByEmpCodeOderByPunchDateDesc(codeEmploye);
			
			System.out.println("========================== phantoms : "+phantoms);
			statEmploye = new StatEmploye(employeeOptional.get(), phantoms, departement);
		}
    	
    	return statEmploye;
    }

    /*
    public List<Transactions> getListHearlyCheckIn(){
        return statsRepository.findAll(Sort.by("username").ascending());
    }*/
    
    /*@Override
    public List<Transactions> getListPunch() {
        
        return statsRepository.getListPunch();
    }*/
    
    

    /*public List<Transactions> findPunchByStatus(Date date,String status,Integer limit){
    
        return statsRepository.findPunchByStatus(date,status,limit);
       
    }*/

    /*public Integer countPunch(Date date,String status){
        return statsRepository.countPunch(date,status);
    }*/

    
}
