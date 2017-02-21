package controller;

import dao.GroupDao;
import dao.PersonDao;
import dao.PhoneTypeDao;
import model.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

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
import java.util.Iterator;
import java.util.List;

/**
 * Created by Student on 2/13/2017.
 */
@MultipartConfig
@WebServlet(name = "addStudentServlet")
public class addStudentServlet extends HttpServlet {
    private final String path = "C:/Users/Student/IdeaProjects/webappstudents/src/main/webapp/resources/image/";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Person person = new Person();
        Student student = new Student();
        Address address = new Address();
        Phone phone = new Phone();
        PhoneType phoneType = new PhoneType();
        PhoneTypeDao phoneTypeDao = new PhoneTypeDao();
        try {
            phoneType = phoneTypeDao.getById(Long.valueOf(request.getParameter("phone.phoneType")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        phone.setNumber(request.getParameter("person.phone.number"));
        phone.setPhoneType(phoneType);
        ArrayList<Phone> phones = new ArrayList<Phone>();
        phones.add(phone);
        person.setPhone(phones);
        Group group = new Group();
        GroupDao groupDao = new GroupDao();
        try {
            group = groupDao.getById(Long.valueOf(request.getParameter("groups")));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        final Part filePart = request.getPart("file");

            final PrintWriter writer = response.getWriter();
            long millis = System.currentTimeMillis() % 1000;
            OutputStream out = new FileOutputStream(new File(path + File.separator + millis + ".jpg"));
            InputStream filecontent  = filePart.getInputStream();
            int read = 0;
            final byte[] bytes = new byte[1024];
            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
                writer.println("New file " + millis + ".jpg" + " created at " + path);
                person.setPicture(millis + ".jpg");
            }
            out.close();
        student.setGroup(group);
        student.setCalculateScholarship(0);
        person.setStudent(student);
        person.setFirstname(request.getParameter("person.firstname"));
        person.setLastname(request.getParameter("person.lastname"));
        person.setDob(Date.valueOf(request.getParameter("person.dob")));
        address.setCountry(request.getParameter("person.address.country"));
        address.setCity(request.getParameter("person.address.city"));
        address.setAddress(request.getParameter("person.address.address"));
        person.setDob(Date.valueOf(request.getParameter("person.dob")));
        person.setGender(request.getParameter("gender"));
        person.setAddress(address);

        PersonDao personDao = new PersonDao();
        try {
            personDao.create(person);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Phone phone = new Phone();
        PhoneTypeDao phoneTypeDao = new PhoneTypeDao();
        List<PhoneType> phonesType = new ArrayList<PhoneType>();
        GroupDao groupDao = new GroupDao();
        List<Group> groups = new ArrayList<Group>();

        try {
            phonesType= phoneTypeDao.getAll();
            groups = groupDao.getAllGroups();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        request.setAttribute("phonesType", phonesType);
        request.setAttribute("groups", groups);
        try{
            RequestDispatcher rd = request.getRequestDispatcher("/addstudent.jsp");
            rd.forward(request, response);
        } catch (NullPointerException e){
            e.printStackTrace();
            System.out.println("Eraore la cale ");
        }

    }
}