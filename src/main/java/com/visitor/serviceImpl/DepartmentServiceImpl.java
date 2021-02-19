package com.visitor.serviceImpl;

import com.visitor.entities.Department;
import com.visitor.repositories.DepartmentRepository;
import com.visitor.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Department add(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department update(Department department) {
        if(department.getId() ==0) {
            return departmentRepository.save(department);
        }
        return  departmentRepository.saveAndFlush(department);
    }

    @Override
    public Department getOneById(Integer id) {
        return departmentRepository.getOne(id);
    }

    @Override
    public void delete(Integer id) {
        departmentRepository.deleteById(id);
    }
}
