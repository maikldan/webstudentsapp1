package dao;

import model.Discipline;
import model.Mark;
import model.Profesor;
import utils.Settings;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Student on 2/9/2017.
 */
public class MarksDao {

    PreparedStatement preparedStatement = null;
    DisciplineDao disciplineDao = new DisciplineDao();
    StudentsDao studentsDao = null;

    public MarksDao(){

    }
    public List<Mark> getAllMarks() throws SQLException{
//studentsDao.findOneWithoutMarks
        studentsDao = new StudentsDao();
        ArrayList<Mark> marks = new ArrayList<Mark>();
        preparedStatement = Settings.getConnection().prepareStatement("SELECT * FROM mark");
        ResultSet eq = preparedStatement.executeQuery();
        while(eq.next()){
            Mark mark = new Mark();
            mark.setId(eq.getLong("id"));
            mark.setDiscipline(disciplineDao.getById(eq.getInt("discipline_id")));
            //mark.setStudent(studentsDao.getById(eq.getInt("student_id")));
//            mark.setProfesor(ProfesorDao.getByIdeq.getString("profesor_id"));
            mark.setMark(eq.getDouble("mark"));
            mark.setDate(eq.getDate("createdate"));
            marks.add(mark);

        }
        return marks;
    }
    public Mark getById(Long mark_id) throws SQLException{
//studentsDao.findOneWithoutMarks
        studentsDao = new StudentsDao();
        preparedStatement = Settings.getConnection().prepareStatement("SELECT * FROM mark");
        ResultSet eq = preparedStatement.executeQuery();
            Mark mark = new Mark();
            mark.setId(eq.getLong("mark_id"));
            mark.setDiscipline(disciplineDao.getById(eq.getInt("discipline_id")));
//            mark.setProfesor(ProfesorDao.getByIdeq.getString("profesor_id"));
        //mark.setStudent(studentsDao.getById(eq.getInt("student_id")));
            mark.setMark(eq.getDouble("mark"));
            mark.setDate(eq.getDate("createdate"));

        return mark;
    }

    public List<Mark> getStudentMrks(Long student_id) throws SQLException {
//studentsDao.findOneWithoutMarks
        studentsDao = new StudentsDao();
        ArrayList<Mark> studentmarks = new ArrayList<Mark>();
        preparedStatement = Settings.getConnection().prepareStatement("SELECT * FROM mark where student_id = ?");
        preparedStatement.setLong(1, student_id);
        ResultSet eq = preparedStatement.executeQuery();

        while(eq.next()){
            Discipline discipline = disciplineDao.getById(eq.getInt("discipline_id"));
            Mark mark = new Mark();
            mark.setId(eq.getLong("mark_id"));
            mark.setDiscipline(discipline);
//            mark.setProfesor(ProfesorDao.getByIdeq.getString("profesor_id"));
            //mark.setStudent(studentsDao.getById(eq.getInt("student_id")));
            mark.setMark(eq.getDouble("mark"));
            mark.setDate(eq.getDate("createdate"));
            studentmarks.add(mark);
//            System.out.println(mark.getMark());
        }
        return studentmarks;
    }
    public List<Mark> getAverageMrks(Long student_id) throws SQLException {
//studentsDao.findOneWithoutMarks
        studentsDao = new StudentsDao();
        ArrayList<Mark> studentmarks = new ArrayList<Mark>();
        preparedStatement = Settings.getConnection().prepareStatement("SELECT * FROM average_mark where student_id = ?");
        preparedStatement.setLong(1, student_id);
        ResultSet eq = preparedStatement.executeQuery();

        while(eq.next()){
            Discipline discipline = new Discipline();
            discipline.setTitle(eq.getString("name"));
            Mark mark = new Mark();
            mark.setDiscipline(discipline);
//            mark.setProfesor(ProfesorDao.getByIdeq.getString("profesor_id"));
            //mark.setStudent(studentsDao.getById(eq.getInt("student_id")));
            mark.setMark(eq.getDouble("avg"));
            studentmarks.add(mark);
//            System.out.println(mark.getMark());
        }
        return studentmarks;
    }
    public long create(Mark mark) throws SQLException{
        DisciplineDao disciplineDao = new DisciplineDao();
        long keyId = 0L;
        preparedStatement = Settings.getConnection().prepareStatement(" insert into mark (mark, createdate, discipline_id, profesor_id, student_id) values (?,?,?, ?,?) ", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setDouble(1, mark.getMark());
        preparedStatement.setDate(2, mark.getDate());
        preparedStatement.setLong(3, mark.getDiscipline().getId());
        preparedStatement.setLong(4, mark.getProfesor().getId());
        preparedStatement.setLong(5, mark.getStudent().getId());
        preparedStatement.executeUpdate();
        ResultSet rs = preparedStatement.getGeneratedKeys();
        if(rs.next()){
            keyId = rs.getLong(1);

        }


        return keyId;

    }
}
