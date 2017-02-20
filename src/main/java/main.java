import dao.*;
import model.*;
import org.omg.PortableInterceptor.ACTIVE;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Student on 2/8/2017.
 */
public class main {
    public static void main(String[] args) throws SQLException {
//        Address address = new Address();
//        address.setAddress("Stefan cel Mare");
//        address.setCountry("Moldova");
//        address.setCity("Balti");
//        AddressDao addressDao = new AddressDao();
//
//        long idAddress = addressDao.create(address);
//        System.out.println(idAddress);
//        Group group = new Group();
//        group.setName("MI11Z");
//        GroupDao groupDao = new GroupDao();
//        long idGroup = groupDao.create(group);
//        System.out.println(idGroup);
//        Phone phone = new Phone();
//        PhoneType phoneType = new PhoneType();
//        PhoneDao phoneDao = new PhoneDao();
//
//        phone.setNumber("067354119");
//        phoneType.setName("LUCRU");
//        phone.setPhoneType(phoneType);
//        long k = phoneDao.create(phone);
//        System.out.println(k);

        Person person = new Person();
        person.setFirstname("Jekia");
        person.setLastname("Sergheev");
        person.setDob(Date.valueOf("1994-08-11"));
        person.setGender("Male");
        person.setPicture("0");


        //Student
        Student student = new Student();

//        Discipline discipline = new Discipline();
//        discipline.setScholarshipThreshold(5.5);
//        discipline.setTitle("OOP");
//        ArrayList<Discipline> disciplines = new ArrayList<Discipline>();
        student.setCalculateScholarship(0);

        //Group
        Group group = new Group();
        group.setName("IS31Z");
        student.setGroup(group);
        //ADD Group to student
        person.setStudent(student);
        //Adress
        Address address = new Address();
        address.setCity("Balti");
        address.setCountry("Moldova");
        address.setAddress("Koneva 55");
        //Add addresss to person
        person.setAddress(address);

        //Library subscription
        LibrarySubscription librarySubscription = new LibrarySubscription();
        librarySubscription.setStatus(Status.ACTIVE);

        person.setLibrarySubscription(librarySubscription);
        Phone phone = new Phone();
        PhoneType phoneType = new PhoneType();
        phoneType.setId((long) 1);
        phone.setNumber("0673655598");
        phone.setPhoneType(phoneType);
        ArrayList<Phone> phones = new ArrayList<Phone>();
        phones.add(phone);
        person.setPhone(phones);
        PersonDao personDao = new PersonDao();
        personDao.create(person);
    }

}
