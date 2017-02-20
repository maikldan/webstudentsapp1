package dao;

import model.Discipline;
import model.Profesor;
import utils.Settings;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Student on 2/9/2017.
 */
public class DisciplineDao {
    PreparedStatement preparedStatement = null;
    public DisciplineDao(){}

    public List<Discipline> getAllDisciplines() throws SQLException {
        ArrayList<Discipline> disciplines = new ArrayList<Discipline>();
        preparedStatement = Settings.getConnection().prepareStatement("SELECT * FROM discipline");
        ResultSet eq = preparedStatement.executeQuery();
        while(eq.next()){
            Discipline discipline = new Discipline();
            discipline.setId(eq.getLong("discipline_id"));
//            discipline.setProfesor(Profesor.getById.eq.getDate("date"));
            discipline.setTitle(eq.getString("name"));
            discipline.setScholarshipThreshold(eq.getDouble("scholarship_threshold"));
            disciplines.add(discipline);
           // System.out.println(discipline.getId() + " " + discipline.getTitle() + " " + discipline.getProfesor() + " " + discipline.getScholarshipThreshold());
        }
        return disciplines;
    }

    public Discipline getById(int discipline_id) throws SQLException{
        preparedStatement = Settings.getConnection().prepareStatement("SELECT * FROM discipline WHERE discipline_id = ? ");
        preparedStatement.setInt(1, discipline_id);
        ResultSet eq = preparedStatement.executeQuery(); eq.next();
        Discipline discipline = new Discipline();
        discipline.setId(eq.getLong("discipline_id"));
        //discipline.setProfesor(Profesor.getById.eq.getDate("startdate"));
        discipline.setTitle(eq.getString("name"));
        discipline.setScholarshipThreshold(eq.getDouble("scholarship_threshold"));
        //System.out.println(discipline.getId() + " " + discipline.getTitle() + " " + discipline.getProfesor() + " " + discipline.getScholarshipThreshold());
        return discipline;

    }
}
