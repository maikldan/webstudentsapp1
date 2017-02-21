package services;

import model.Address;

import java.sql.Date;

/**
 * Created by Student on 2/17/2017.
 */
public class searchService {

    private String address;
    private String name;
    private Date dobStart;
    private Date dobEnd;
    private Long group;
    private String gender;
    private Long discipline;
    private double mark;
    private Long id;
    private Double totalAverage;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDobStart() {
        return dobStart;
    }

    public void setDobStart(Date dobStart) {
        this.dobStart = dobStart;
    }

    public Date getDobEnd() {
        return dobEnd;
    }

    public void setDobEnd(Date dobEnd) {
        this.dobEnd = dobEnd;
    }

    public Long getGroup() {
        return group;
    }

    public void setGroup(Long group) {
        this.group = group;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Long discipline) {
        this.discipline = discipline;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotalAverage() {
        return totalAverage;
    }

    public void setTotalAverage(Double totalAverage) {
        this.totalAverage = totalAverage;
    }

    public searchService(){

    }


}
