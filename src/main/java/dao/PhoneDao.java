package dao;

import model.Phone;
import model.PhoneType;
import utils.Settings;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Student on 2/9/2017.
 */
public class PhoneDao {
    PreparedStatement preparedStatement = null;

    public PhoneDao() {
    }

    public List<Phone> getAllPhones() throws SQLException {
        List<Phone> phones = new ArrayList<>();
        PhoneTypeDao phoneTypeDao = new PhoneTypeDao();
        preparedStatement = Settings.getConnection().prepareStatement("SELECT * FROM phone inner join phone_type on phone.phonetype_id = phone_type.phonetype_id");
        ResultSet eq = preparedStatement.executeQuery();
        Phone phone = new Phone();
        while (eq.next()) {
            phone.setId(eq.getLong("phone_id"));
            phone.setNumber(eq.getString("number"));
            phone.setPhoneType(phoneTypeDao.getById(eq.getLong("phonetype_id")));
            phones.add(phone);
        }
        return phones;
    }
    public Phone getById(Long phonetype_id) throws SQLException {
        PhoneTypeDao phoneTypeDao = new PhoneTypeDao();
        preparedStatement = Settings.getConnection().prepareStatement("SELECT * FROM phone where phone.phonetype = ?_id");
        preparedStatement.setLong(1, phonetype_id);
        ResultSet eq = preparedStatement.executeQuery();
        Phone phone = new Phone();
        while (eq.next()) {
            phone.setId(phonetype_id);
            phone.setNumber(eq.getString("number"));
            phone.setPhoneType(phoneTypeDao.getById(eq.getLong("phonetype_id")));
        }
        return phone;
    }
    public List<Phone> getStudentPhonesById(Long person_id) throws SQLException {
        List<Phone> phones = new ArrayList<>();
        PhoneTypeDao phoneTypeDao = new PhoneTypeDao();
        preparedStatement = Settings.getConnection().prepareStatement("SELECT * FROM phone where person_id = ?");
        preparedStatement.setLong(1, person_id);
        ResultSet eq = preparedStatement.executeQuery();

        while (eq.next()) {
            Phone phone = new Phone();
            phone.setId(eq.getLong("phone_id"));
            phone.setNumber(eq.getString("number"));
            phone.setPhoneType(phoneTypeDao.getById(eq.getLong("phonetype_id")));
            phones.add(phone);
        }
        return phones;
    }
    public long create(Phone phone) throws SQLException {
        long keyId = 0L;
        PhoneTypeDao phoneTypeDao = new PhoneTypeDao();
        preparedStatement = Settings.getConnection().prepareStatement(" insert into phone (number, phonetype_id,person_id) values (?,?,?) ", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, phone.getNumber());
        preparedStatement.setLong(2, phone.getPhoneType().getId());
        preparedStatement.setLong(3, phone.getPerson().getId());
        preparedStatement.executeUpdate();
        ResultSet rs = preparedStatement.getGeneratedKeys();
        if(rs.next()){
            keyId = rs.getLong(1);

        }


        return keyId;


    }
    public void update(Phone phone) throws SQLException {

        PhoneTypeDao phoneTypeDao = new PhoneTypeDao();
        preparedStatement = Settings.getConnection().prepareStatement(" update phone set number = ? , phonetype_id = ? where phone_id = ? ", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, phone.getNumber());
        preparedStatement.setLong(2, phone.getPhoneType().getId());
        preparedStatement.setLong(3,phone.getId());
        preparedStatement.executeUpdate();
        ResultSet rs = preparedStatement.getGeneratedKeys();


    }
}

