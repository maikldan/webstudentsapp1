package controller;

import dao.LibrarySubscriptionDao;
import dao.StudentsDao;
import model.LibrarySubscription;
import model.Person;
import model.Status;
import model.Student;

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
 * Created by Student on 2/14/2017.
 */
@WebServlet(name = "editStatus")
public class editStatus extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        LibrarySubscription librarySubscription = new LibrarySubscription();
        LibrarySubscriptionDao librarySubscriptionDao = new LibrarySubscriptionDao();
        StudentsDao studentsDao = new StudentsDao();
        try {
            Student student = studentsDao.getById((long) id);
            librarySubscription.setId((long) id);
            librarySubscription.setStatus(Status.valueOf(request.getParameter("status")));
            librarySubscription.setStartdate(Date.valueOf(request.getParameter("startdate")));
            librarySubscription.setEnddate(Date.valueOf(request.getParameter("enddate")));
            librarySubscriptionDao.update(librarySubscription);
        } catch (SQLException e) {
            e.printStackTrace();
        }






    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        LibrarySubscription librarySubscription = new LibrarySubscription();
        request.setAttribute("Status", Status.values());

       StudentsDao studentsDao = new StudentsDao();
        Student student = null;
        try {
            student = studentsDao.getById((long) id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("student", student);
        try{
            RequestDispatcher rd = request.getRequestDispatcher("/editStatus.jsp");
            rd.forward(request, response);
        } catch (NullPointerException e){
            e.printStackTrace();
            System.out.println("Eraore la cale ");
        }
    }
}
