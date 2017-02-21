package controller;

import dao.*;
import model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Student on 2/14/2017.
 */
@MultipartConfig
@WebServlet(name = "editStudent")
public class editStudent extends HttpServlet {
    private final String path = "C:/Users/Student/IdeaProjects/webappstudents/src/main/webapp/resources/image/";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Person person = new Person();
        PersonDao personDao = new PersonDao();
        person.setId(personDao.findByStudentId(Long.parseLong(request.getParameter("id"))));
        Student student = new Student();
        student.setId(Long.valueOf(request.getParameter("id")));
        Address address = new Address();
        AddressDao addressDao = new AddressDao();
        PhoneTypeDao phoneTypeDao = new PhoneTypeDao();
        String[] idPhone = request.getParameterMap().get("person.phone.id[]");
        String[] phoneNumbers = request.getParameterMap().get("person.phone.number[]");
        String[] phoneTypes = request.getParameterMap().get("phone.phoneType[]");

        ArrayList<Phone> phones = new ArrayList<Phone>();


        for (int i = 0; i < idPhone.length; i++) {
            Phone phone = new Phone();
            PhoneType phoneType = new PhoneType();

            try {
                phoneType = phoneTypeDao.getById(Long.valueOf(phoneTypes[i]));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            phone.setNumber(phoneNumbers[i]);
            phone.setPhoneType(phoneType);
            phone.setId(Long.valueOf(idPhone[i]));
            phone.setPerson(person);
            phones.add(phone);

        }
        final Part filePart = request.getPart("file");
        if(filePart.getSize() > 0){
            final PrintWriter writer = response.getWriter();
            OutputStream out = new FileOutputStream(new File(path + File.separator + student.getId() + ".jpg"));
            InputStream filecontent  = filePart.getInputStream();
            int read = 0;
            final byte[] bytes = new byte[1024];
            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
                writer.println("New file " + student.getId() + ".jpg" + " created at " + path);
                person.setPicture(student.getId() + ".jpg");
            }
            out.close();
        }else {
            student.setPicture(request.getParameter("existsImage"));
        }

        person.setPhone(phones);
        Group group = new Group();
        GroupDao groupDao = new GroupDao();
        try {
            group = groupDao.getById(Long.valueOf(request.getParameter("groups")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        student.setGroup(group);
        student.setCalculateScholarship(0);
        person.setStudent(student);
        person.setFirstname(request.getParameter("person.firstname"));
        person.setLastname(request.getParameter("person.lastname"));
        person.setDob(Date.valueOf(request.getParameter("person.dob")));
        address.setCountry(request.getParameter("person.address.country"));
        address.setCity(request.getParameter("person.address.city"));
        address.setAddress(request.getParameter("person.address.address"));
        if (Long.valueOf(request.getParameter("address_id")) == null) {

            try {
                address.setId(addressDao.create(address));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            address.setId(Long.valueOf(request.getParameter("address_id")));
        }

        person.setDob(Date.valueOf(request.getParameter("person.dob")));
        person.setGender(request.getParameter("gender"));
        person.setAddress(address);


        try {
            personDao.update(person);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/StudentPage");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Person person = new Person();
        PhoneTypeDao phoneTypeDao = new PhoneTypeDao();
        List<PhoneType> phonesType = new ArrayList<PhoneType>();
        PhoneDao phoneDao = new PhoneDao();

        List<Phone> phones = new ArrayList<Phone>();
        GroupDao groupDao = new GroupDao();
        List<Group> groups = new ArrayList<Group>();
        StudentsDao studentsDao = new StudentsDao();
        PersonDao personDao = new PersonDao();
        person.getId();
        Student student = null;
        try {
            phones = phoneDao.getStudentPhonesById(personDao.findByStudentId(id));
            student = studentsDao.getById((long) id);
            phonesType= phoneTypeDao.getAll();
            groups = groupDao.getAllGroups();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        request.setAttribute("phonesType", phonesType);
        request.setAttribute("phones", phones);
        request.setAttribute("groups", groups);
        request.setAttribute("student", student);
        request.setAttribute("person", person);
        try{
            RequestDispatcher rd = request.getRequestDispatcher("/editStudent.jsp");
            rd.forward(request, response);
        } catch (NullPointerException e){
            e.printStackTrace();
            System.out.println("Eraore la cale ");
        }

    }
    }

