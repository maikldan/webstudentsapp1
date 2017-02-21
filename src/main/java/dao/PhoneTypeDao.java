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
public class PhoneTypeDao {
    PreparedStatement preparedStatement = null;
        public PhoneTypeDao(){}

     public List<PhoneType> getAll() throws SQLException {
         List<PhoneType> phones = new ArrayList<PhoneType>();

         preparedStatement = Settings.getConnection().prepareStatement("SELECT * FROM phone_type");
         ResultSet eq = preparedStatement.executeQuery();
         while (eq.next()) {
             PhoneType phone = new PhoneType();
             phone.setId(eq.getLong("phonetype_id"));
             phone.setName(eq.getString("name"));
             phones.add(phone);
         }
        return phones;
     }

    public PhoneType getById(Long phonetype_id) throws SQLException {
        preparedStatement = Settings.getConnection().prepareStatement("SELECT * FROM phone_type where phonetype_id = ?");
        preparedStatement.setLong(1, phonetype_id);
        ResultSet eq = preparedStatement.executeQuery();
        PhoneType phoneType = new PhoneType();
        while (eq.next()) {

            phoneType.setId(phonetype_id);
            phoneType.setName(eq.getString("name"));
        }
            return phoneType;
    }

public long create(PhoneType phoneType) throws SQLException {
    long keyId = 0;
        preparedStatement = Settings.getConnection().prepareStatement(" insert into phone_type (name) values (?); ",Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,phoneType.getName());
            preparedStatement.executeUpdate();
        ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()){
            keyId = rs.getLong(1);

        }


            return keyId;


}
}
