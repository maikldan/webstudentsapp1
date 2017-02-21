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
 * Created by Student on 2/15/2017.
 */
public class ProfesorDao {
    public ProfesorDao(){}
    PreparedStatement preparedStatement = null;
    AddressDao addressDao = new AddressDao();
    public List<Profesor> getAll() throws SQLException {
        List<Profesor> profesors = new ArrayList<Profesor>();
        preparedStatement = Settings.getConnection().prepareStatement("SELECT * FROM profesor,person where person.profesor_id = profesor.profesor_id");
        ResultSet eq = preparedStatement.executeQuery();
        while(eq.next()) {
            Profesor profesor = new Profesor();
            profesor.setId(eq.getLong("profesor_id"));
            profesor.setFirstname(eq.getString("firstname"));
            profesor.setLastname(eq.getString("lasttname"));
            profesor.setAddress(addressDao.getAddressById(eq.getLong("address_id")));
            profesor.setDob(eq.getDate("dob"));
            profesor.setGender(eq.getString("gender"));
            profesors.add(profesor);
        }
        return profesors;
        }
    }
