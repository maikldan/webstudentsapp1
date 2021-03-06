package controller;

import dao.DisciplineDao;
import dao.GroupDao;
import dao.StudentsDao;
import model.Discipline;
import model.Group;
import model.Student;
import services.searchService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Student on 2/9/2017.
 */
@WebServlet(name = "StudentPage", value = "/StudentPage")

public class StudentPage extends HttpServlet {
    StudentsDao studentsDao = new StudentsDao();
    Student student = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] idStudents = request.getParameterMap().get("delete[]");
        for (int i = 0; i < idStudents.length; i++) {
            Student student = new Student();
            student.setId(Long.valueOf(idStudents[i]));
            try {
                studentsDao.updateStatus(student);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println();

        response.sendRedirect("/StudentPage");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DisciplineDao disciplineDao = new DisciplineDao();
        List<Discipline> disciplines = new ArrayList<Discipline>();
        List<Student> students = null;
        GroupDao groupDao = new GroupDao();
        List<Group> groups = new ArrayList<Group>();
        StudentsDao studentsDao = new StudentsDao();
        if (request.getParameter("search") != null) {
            searchService searchForm = new searchService();
            if (request.getParameter("person.name") != null) {
                searchForm.setName(request.getParameter("person.name"));
            }
            if (request.getParameter("person.address") != null){

                searchForm.setAddress(request.getParameter("person.address"));
            }

            if (!request.getParameter("person.dobStart").isEmpty() && !request.getParameter("person.dobEnd").isEmpty()) {
                searchForm.setDobStart(Date.valueOf(request.getParameter("person.dobStart")));
                searchForm.setDobEnd(Date.valueOf(request.getParameter("person.dobEnd")));
            }else if(!request.getParameter("person.dobStart").isEmpty()){
                searchForm.setDobStart(Date.valueOf(request.getParameter("person.dobStart")));
            }
            if (request.getParameter("discipline") != null) {
                searchForm.setDiscipline(Long.parseLong(request.getParameter("discipline")));
            }
            if (request.getParameter("groups") != null) {
                searchForm.setGroup(Long.parseLong(request.getParameter("groups")));
            }
            if (!request.getParameter("total_average").isEmpty()) {
                searchForm.setTotalAverage(Double.parseDouble(request.getParameter("total_average")));
            }
//            if (request.getParameter("discipline_average") != null) {
//                searchForm.setTotalAverage(Double.valueOf(request.getParameter("discipline_average")));
//            }
            if (!request.getParameter("gender").isEmpty()){
                searchForm.setGender(request.getParameter("gender"));
            }

            try {
                students = studentsDao.getAllStudentsByFilter(searchForm);
            } catch (SQLException e) {
//                e.printStackTrace();
                try {
                    students = studentsDao.getAllStudents();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            System.out.println("working");
        } else {
            try {
                students = studentsDao.getAllStudents();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            groups = groupDao.getAllGroups();
            disciplines = disciplineDao.getAllDisciplines();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Contriller StudentController running");

        request.setAttribute("students", students);
        request.setAttribute("disciplines", disciplines);
        request.setAttribute("groups", groups);
        try {
            RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Eraore la cale ");
        }

    }
}
