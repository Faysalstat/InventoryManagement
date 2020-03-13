/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Faysal
 */
public class User {
    private String empId;
    private String email;
    private String name;
    private String pass;
    private String designation;
    private double salary;
    private String bankAc;

    public String getEmpId() {
        return empId;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPass() {
        return pass;
    }

    public String getDesignation() {
        return designation;
    }

    public double getSalary() {
        return salary;
    }

    public String getBankAc() {
        return bankAc;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setBankAc(String bankAc) {
        this.bankAc = bankAc;
    }
    public User setEmployee(String id, String mail,String na,String p,String desi,String ac, String sal) {
        User user = new User();
        user.empId= id;
        user.email= mail;
        user.name= na;
        user.pass=p;
        user.designation=desi;
        user.bankAc = ac;
        user.salary= Double.parseDouble(sal);
        return user;
    }
    
    
}
