package dao;

import model.Address;
import utils.Settings;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Student on 2/7/2017.
 */
public class AddressDao {
    PreparedStatement preparedStatement = null;

    public AddressDao() {

    }

    public List<Address> getAllAddresses() throws SQLException {
        ArrayList<Address> addresses = new ArrayList<Address>();
        preparedStatement = Settings.getConnection().prepareStatement("SELECT * FROM address");
        ResultSet eq = preparedStatement.executeQuery();
        while (eq.next()) {
            Address address = new Address();
            address.setId(eq.getLong("id"));
            address.setAddress(eq.getString("address"));
            address.setCity(eq.getString("city"));
            address.setCountry(eq.getString("country"));
            addresses.add(address);
            System.out.println(address.getCity());
        }
        return addresses;
    }

    public Address getAddressById(Long id) throws SQLException {
        preparedStatement = Settings.getConnection().prepareStatement("SELECT * FROM address WHERE address_id = ? ");
        preparedStatement.setLong(1, id);
        ResultSet eq = preparedStatement.executeQuery();
        eq.next();
        Address addrById = new Address();
        addrById.setId(id);
        addrById.setAddress(eq.getString("address"));
        addrById.setCity(eq.getString("city"));
        addrById.setCountry(eq.getString("country"));

        return addrById;

    }

    public long create(Address address) throws SQLException {
        long keyId = 0L;
        preparedStatement = Settings.getConnection().prepareStatement(" insert into address (country, city, address) values (?,?,?); ", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, address.getCountry());
        preparedStatement.setString(2, address.getCity());
        preparedStatement.setString(3, address.getAddress());
        preparedStatement.executeUpdate();
        ResultSet rs = preparedStatement.getGeneratedKeys();
        if(rs.next()){
            keyId = rs.getLong(1);

        }


        return keyId;


    }


    public long update(Address address) throws SQLException {
        long keyId = 0L;
        preparedStatement = Settings.getConnection().prepareStatement(" update address set country = ? , city = ?, address = ? where address_id = ? ");

        preparedStatement.setString(1, address.getCountry());
        preparedStatement.setString(2, address.getCity());
        preparedStatement.setString(3, address.getAddress());
        preparedStatement.setLong(4, address.getId());
        preparedStatement.executeUpdate();
        ResultSet rs = preparedStatement.getGeneratedKeys();
        if(rs.next()){
            keyId = rs.getLong(1);

        }


        return keyId;


    }

    public void delete(Long address_id) throws SQLException {
        preparedStatement = Settings.getConnection().prepareStatement(" delete from address where address_id = ? ");
        preparedStatement.setLong(1, address_id);
        preparedStatement.executeUpdate();

    }

}
