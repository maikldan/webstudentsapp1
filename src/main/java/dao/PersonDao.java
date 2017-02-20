package dao;

import model.*;
import utils.Settings;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Student on 2/13/2017.
 */
public class PersonDao {

    public PersonDao() {

    };


    PreparedStatement preparedStatement = null;
    AddressDao addressDao = new AddressDao();
    StudentsDao studentsDao = new StudentsDao();
    LibrarySubscriptionDao librarySubscriptionDao = new LibrarySubscriptionDao();
    Phone phone = new Phone();
    PhoneDao phoneDao = new PhoneDao();
    public long findByStudentId(long id) {
        long idPerson = 0;
        try {
            preparedStatement = Settings.getConnection().prepareStatement("Select * from student, person  where student.student_id = person.student_id and student.student_id = ?");
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            idPerson = rs.getLong("person_id");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idPerson;
    }
    public long create(Person person) throws SQLException {
        long keyId = 0L;
        Settings.getConnection().setAutoCommit(false);
        preparedStatement = Settings.getConnection().prepareStatement(" insert into person (firstname,lasttname,dob,gender,picture_path,student_id,profesor_id,address_id, libsubscription_id) values (?,?,?,?,?,?,?,?,?); ", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, person.getFirstname());
        preparedStatement.setString(2, person.getLastname());
        preparedStatement.setObject(3, person.getDob());
        preparedStatement.setString(4, person.getGender());
        preparedStatement.setString(5, person.getPicture());
        preparedStatement.setLong(6, studentsDao.create(person.getStudent()));
        preparedStatement.setNull(7, 0);
        preparedStatement.setLong(8, addressDao.create(person.getAddress()));
        preparedStatement.setLong(9, librarySubscriptionDao.create(person.getLibrarySubscription() == null ? new LibrarySubscription(Status.NONE) : person.getLibrarySubscription()));
        preparedStatement.executeUpdate();
        ResultSet rs = preparedStatement.getGeneratedKeys();
        if(rs.next()){
            keyId = rs.getLong(1);

        }
        person.setId(keyId);
        ArrayList<Phone> phones = new ArrayList<>();
        phones = (ArrayList<Phone>) person.getPhone();
        for (int i = 0; i < phones.size(); i++) {
            phones.get(i).setPerson(person);
            phoneDao.create(phones.get(i));
        }
        Settings.getConnection().commit();
        return keyId;

    }
    public void update(Person person) throws SQLException {

        Settings.getConnection().setAutoCommit(false);
        preparedStatement = Settings.getConnection().prepareStatement(" update person set firstname = ?,lasttname = ?,dob = ? ,gender = ? ,picture_path = ?  where person_id = ? ", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, person.getFirstname());
        preparedStatement.setString(2, person.getLastname());
        preparedStatement.setObject(3, person.getDob());
        preparedStatement.setString(4, person.getGender());
        preparedStatement.setString(5, person.getPicture());
        studentsDao.update(person.getStudent());
        addressDao.update(person.getAddress());
        preparedStatement.setLong(6, person.getId());
        preparedStatement.executeUpdate();
        ResultSet rs = preparedStatement.getGeneratedKeys();

        ArrayList<Phone> phones = new ArrayList<>();
        phones = (ArrayList<Phone>) person.getPhone();
        for (int i = 0; i < phones.size(); i++) {
            phoneDao.update(phones.get(i));
        }
        Settings.getConnection().commit();

    }
}
