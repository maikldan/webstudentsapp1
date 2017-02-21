package dao;

import model.Group;
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
public class GroupDao {

    PreparedStatement preparedStatement = null;

    public GroupDao() {}

    public List<Group> getAllGroups() throws SQLException {
        List<Group> groups = new ArrayList<Group>();
        preparedStatement = Settings.getConnection().prepareStatement("SELECT * FROM groupp");
        ResultSet eq = preparedStatement.executeQuery();
        while (eq.next()) {
            Group group = new Group();
            group.setId(eq.getLong("group_id"));
            group.setName(eq.getString("name"));

            groups.add(group);
            System.out.println(group.getId());
        }
        return groups;

    }
    public Group getById(Long group_id) throws SQLException {
        preparedStatement = Settings.getConnection().prepareStatement("SELECT * FROM groupp where group_id = ?");
        preparedStatement.setLong(1, group_id);
        ResultSet eq = preparedStatement.executeQuery();
        Group group = new Group();
        while (eq.next()) {

            group.setId(group_id);
            group.setName(eq.getString("name"));

        }
        return group;

    }
    public long create(Group group) throws  SQLException{
        long keyId = 0L;
        preparedStatement = Settings.getConnection().prepareStatement(" insert into groupp (name) values (?); ", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, group.getName());
        preparedStatement.executeUpdate();
        ResultSet rs = preparedStatement.getGeneratedKeys();
        if(rs.next()){
            keyId = rs.getLong(1);
        }
        return keyId;

    }
    public long update(Group group) throws  SQLException{
        long keyId = 0L;
        preparedStatement = Settings.getConnection().prepareStatement(" update groupp set name = ?  where group_id = ? ", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, group.getName());
        preparedStatement.setLong(2, group.getId());
        preparedStatement.executeUpdate();
        ResultSet rs = preparedStatement.getGeneratedKeys();
        if(rs.next()){
            keyId = rs.getLong(1);
        }
        return keyId;

    }
}
