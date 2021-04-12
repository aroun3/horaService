package com.visitor.payload.response;

import com.visitor.entities.EmployeTop;

import java.util.List;

public class TotalTopLast {
    private List<EmployeTop> listEmployeTop;
    private List<EmployeTop> listEmployeLast;

    public List<EmployeTop> getListEmployeTop() {
        return listEmployeTop;
    }

    public void setListEmployeTop(List<EmployeTop> listEmployeTop) {
        this.listEmployeTop = listEmployeTop;
    }

    public List<EmployeTop> getListEmployeLast() {
        return listEmployeLast;
    }

    public void setListEmployeLast(List<EmployeTop> listEmployeLast) {
        this.listEmployeLast = listEmployeLast;
    }
}
