package controller;

import dao.DisciplineDao;
import dao.MarksDao;
import dao.ProfesorDao;
import dao.StudentsDao;
import model.Discipline;
import model.Mark;
import model.Profesor;
import model.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Student on 2/15/2017.
 */
@WebServlet(name = "addMark")
public class addMark extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Profesor profesor = new Profesor();
        Student student = new Student();
        Discipline discipline = new Discipline();
        Mark mark = new Mark();
        Date date = new Date();
        student.setId(Long.valueOf(request.getParameter("id")));
        profesor.setId(Long.valueOf(request.getParameter("person.profesor")));
        discipline.setId(Long.valueOf(request.getParameter("discipline")));
        mark.setStudent(student);
        mark.setProfesor(profesor);
        mark.setDiscipline(discipline);
//        mark.setDate(java.sql.Date.valueOf(String.valueOf(date.getDate())));
        mark.setMark(Double.valueOf(request.getParameter("mark")));
        MarksDao marksDao = new MarksDao();
        try {
            marksDao.create(mark);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/StudentPage");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProfesorDao profesorDao = new ProfesorDao();
        Profesor profesor = new Profesor();
        Student student = new Student();
        StudentsDao studentsDao = new StudentsDao();
        int id = Integer.parseInt(request.getParameter("id"));
        student.setId((long) id);
        List<Profesor> profesors = new ArrayList<>();
        DisciplineDao disciplineDao = new DisciplineDao();
        List<Discipline> disciplines = new ArrayList<>();
        try {
            student = studentsDao.getById((long) id);
            disciplines = disciplineDao.getAllDisciplines();
            profesors = profesorDao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("student", student);
        request.setAttribute("profesors", profesors);
        request.setAttribute("disciplines",disciplines);
        try{

            RequestDispatcher rd = request.getRequestDispatcher("/addMark.jsp");
            rd.forward(request, response);
        } catch (NullPointerException e){
            e.printStackTrace();
            System.out.println("Eraore la cale ");
        }
    }
}
