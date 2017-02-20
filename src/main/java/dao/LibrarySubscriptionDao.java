package dao;

import model.LibrarySubscription;
import model.Status;
import utils.Settings;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Student on 2/8/2017.
 */
public class LibrarySubscriptionDao {

        PreparedStatement preparedStatement = null;
        public LibrarySubscriptionDao() {

        }

        public List<LibrarySubscription> getAllLibraryAbonaments() throws SQLException {
            ArrayList<LibrarySubscription> abonaments = new ArrayList<LibrarySubscription>();
            preparedStatement = Settings.getConnection().prepareStatement("SELECT * FROM library_subscription");
            ResultSet eq = preparedStatement.executeQuery();
            while(eq.next()){
                LibrarySubscription abonament = new LibrarySubscription();
                abonament.setId(eq.getLong("libsubscription_id"));
                abonament.setStatus(Status.valueOf(eq.getString("status")));
                abonament.setStartdate(eq.getDate("startdate"));
                abonament.setEnddate(eq.getDate("enddate"));
                abonaments.add(abonament);
                System.out.println(abonament.getId() + " " + abonament.getStatus() + " " + abonament.getEnddate());
            }
            return abonaments;
        }

        public LibrarySubscription getById(int libsubscription_id) throws SQLException{
            preparedStatement = Settings.getConnection().prepareStatement("SELECT * FROM library_subscription WHERE libsubscription_id = ? ");
            preparedStatement.setInt(1, libsubscription_id);
            ResultSet eq = preparedStatement.executeQuery();
            eq.next();
            LibrarySubscription abonament = new LibrarySubscription();
            abonament.setId((long) libsubscription_id);
            abonament.setStatus(Status.valueOf(eq.getString("status")));
            abonament.setStartdate(eq.getDate("startdate"));
            abonament.setEnddate(eq.getDate("enddate"));
            System.out.println(abonament.getId() + " " + abonament.getStartdate() + " " + abonament.getEnddate() + " " + abonament.getStatus());
            return abonament;

        }
        public long create(LibrarySubscription librarySubscription) throws SQLException{
            long keyId = 0;
            preparedStatement = Settings.getConnection().prepareStatement(" insert into library_subscription (status, startdate, enddate) values (?,?,?); ", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, librarySubscription.getStatus().getCode());
            preparedStatement.setObject(2, librarySubscription.getStartdate());
            preparedStatement.setObject(3, librarySubscription.getEnddate());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()){
                keyId = rs.getLong(1);

            }
            return keyId;
        }
        public void update(LibrarySubscription librarySubscription) throws SQLException{
            preparedStatement = Settings.getConnection().prepareStatement(" update library_subscription set status = ? , startdate = ?, enddate = ? where libsubscription_id = ? ", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, librarySubscription.getStatus().getCode());
            preparedStatement.setDate(2, librarySubscription.getStartdate());
            preparedStatement.setDate(3, librarySubscription.getEnddate());
            preparedStatement.setLong(4, librarySubscription.getId());
            preparedStatement.executeUpdate();

        }
        public void delete(int libabonament_id) throws SQLException{
            preparedStatement = Settings.getConnection().prepareStatement(" delete from library_subscription where libsubscription_id = ? ");
            preparedStatement.setInt(1, libabonament_id);
            preparedStatement.executeUpdate();

        }

    }

