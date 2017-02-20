package model;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

/**
 * Created by Student on 2/7/2017.
 */
public class Person {
    private Long id;
    private Address address;
    private String lastname;
    private Date dob;
    private String gender;
    private String picture;
    private List<Phone> phone;
    private LibrarySubscription librarySubscription;
    private String firstname;
    private Student student;
    private Profesor profesor;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Phone> getPhone() {
        return phone;
    }

    public void setPhone(List<Phone> phone) {
        this.phone = phone;
    }

    public LibrarySubscription getLibrarySubscription() {
        return librarySubscription;
    }

    public void setLibrarySubscription(LibrarySubscription librarySubscription) {
        this.librarySubscription = librarySubscription;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }


    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
}
