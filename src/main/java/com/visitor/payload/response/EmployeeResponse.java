package com.visitor.payload.response;


public class EmployeeResponse {

    private String empCode;

    private String firstName;

    private String lastName;

    private String photo;

    private String gender;

    private String contactTel;

    private String mobile;

    private String city;

    private Integer status;

    private String address;

    private String department;

    private String fonction;

    public EmployeeResponse() {
    }


    public EmployeeResponse(String empCode, String firstName, String lastName, String department, String fonction, String gender, String mobile, String city) {
        this.empCode = empCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.fonction = fonction;
        this.gender = gender;
        this.mobile = mobile;
        this.city = city;

    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    @Override
    public String toString() {
        return "EmployeeResponse{" +
                "empCode='" + empCode + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", photo='" + photo + '\'' +
                ", gender='" + gender + '\'' +
                ", contactTel='" + contactTel + '\'' +
                ", mobile='" + mobile + '\'' +
                ", city='" + city + '\'' +
                ", status=" + status +
                ", address='" + address + '\'' +
                ", department='" + department + '\'' +
                ", fonction='" + fonction + '\'' +
                '}';
    }
}
