package dao;

import model.*;
import org.apache.commons.lang3.StringUtils;
import services.searchService;
import utils.Settings;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Student on 2/9/2017.
 */
public class StudentsDao {

    PreparedStatement preparedStatement = null;
    GroupDao groupDao = new GroupDao();
    DisciplineDao disciplineDao = new DisciplineDao();
    MarksDao marksDao = new MarksDao();
    AddressDao addressDao = new AddressDao();
    PhoneDao phoneDao = new PhoneDao();
    LibrarySubscriptionDao librarySubscription = new LibrarySubscriptionDao();
    public StudentsDao() {

    }

    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        preparedStatement = Settings.getConnection().prepareStatement("SELECT * FROM student inner join person on student.student_id = person.student_id");
        ResultSet eq = preparedStatement.executeQuery();
        while (eq.next()) {
            Student student = new Student();
            student.setId(eq.getLong("student_id"));
            student.setFirstname(eq.getString("firstname"));
            student.setLastname(eq.getString("lasttname"));
            student.setPicture(eq.getString("picture_path"));
            student.setGroup(groupDao.getById(eq.getLong("group_id")));
            student.setAddress(addressDao.getAddressById(eq.getLong("address_id")));
            student.setLibrarySubscription(librarySubscription.getById(eq.getInt("libsubscription_id")));
            student.setPhone(phoneDao.getStudentPhonesById(eq.getLong("person_id")));
            student.setDob(eq.getDate("dob"));
            student.setDeleteStatus(eq.getBoolean("delete_status"));
            student.setGender(eq.getString("gender"));
            student.setMarks(marksDao.getAverageMrks(eq.getLong("student_id")));
            if (student.isDeleteStatus() == true){
                students.add(student);
            }
//            System.out.println(student.getId() + " " + student.getFirstname() + " " + student.getLastname() + " " + student.getGroup().getName() + " "
//                    + student.getAddress().getCity() + " " + student.getAddress().getCountry() + " " + student.getAddress().getAddress()
//                    + " " + student.getDob() + " " + student.getGender() + " " + student.getLibrarySubscription().getStatus());
        }
        return students;

    }
    public List<Student> getAllStudentsByFilter(searchService searchService) throws SQLException {
        List<Student> students = new ArrayList<>();
        String name = "";
        String address = "";
        List<String> whereConditions = new ArrayList<String>();
        if(StringUtils.isNotEmpty(searchService.getName())){
            whereConditions.add("person.firstname LIKE '" + searchService.getName() + "%' or person.lasttname LIKE '" + searchService.getName() + "%'");
        }
        if(StringUtils.isNoneEmpty(searchService.getAddress())){
            whereConditions.add("address_id in(select address.address_id from address,person where address.address_id = person.address_id and address.address like '" + searchService.getAddress() + "%')");
        }
        String whereClause = whereConditions.stream().collect(Collectors.joining(" AND "));
        preparedStatement = Settings.getConnection().prepareStatement("SELECT * FROM student inner join person on student.student_id = person.student_id where " + whereClause);
        ResultSet eq = preparedStatement.executeQuery();
        while (eq.next()) {
            Student student = new Student();
            student.setId(eq.getLong("student_id"));
            student.setFirstname(eq.getString("firstname"));
            student.setLastname(eq.getString("lasttname"));
            student.setPicture(eq.getString("picture_path"));
            student.setGroup(groupDao.getById(eq.getLong("group_id")));
            student.setAddress(addressDao.getAddressById(eq.getLong("address_id")));
            student.setLibrarySubscription(librarySubscription.getById(eq.getInt("libsubscription_id")));
            student.setPhone(phoneDao.getStudentPhonesById(eq.getLong("person_id")));
            student.setDob(eq.getDate("dob"));
            student.setDeleteStatus(eq.getBoolean("delete_status"));
            student.setGender(eq.getString("gender"));
            student.setMarks(marksDao.getAverageMrks(eq.getLong("student_id")));
            if (student.isDeleteStatus() == true){
                students.add(student);
            }
//            System.out.println(student.getId() + " " + student.getFirstname() + " " + student.getLastname() + " " + student.getGroup().getName() + " "
//                    + student.getAddress().getCity() + " " + student.getAddress().getCountry() + " " + student.getAddress().getAddress()
//                    + " " + student.getDob() + " " + student.getGender() + " " + student.getLibrarySubscription().getStatus());
        }
        return students;

    }

    public Student findOneWithoutMarks(Long studentId) {
        return null;
    }

    public Student getById(Long student_id) throws SQLException {

        preparedStatement = Settings.getConnection().prepareStatement("SELECT * FROM student inner join person on student.student_id = person.student_id where student.student_id = ? ");
        preparedStatement.setLong(1, student_id);
        ResultSet eq = preparedStatement.executeQuery();
        Student student = new Student();
        while (eq.next()) {
            student.setId(student_id);
            student.setFirstname(eq.getString("firstname"));
            student.setPicture(eq.getString("picture_path"));
            student.setLastname(eq.getString("lasttname"));
            student.setGroup(groupDao.getById(eq.getLong("group_id")));
            student.setAddress(addressDao.getAddressById(eq.getLong("address_id")));
            student.setLibrarySubscription(librarySubscription.getById(eq.getInt("libsubscription_id")));
            student.setDob(eq.getDate("dob"));
            student.setDeleteStatus(eq.getBoolean("delete_status"));
            student.setGender(eq.getString("gender"));
            student.setMarks(marksDao.getStudentMrks(eq.getLong("student_id")));
        }
        return student;

    }
    public long create(Student student ) throws SQLException{
        long keyId = 0L;
        preparedStatement = Settings.getConnection().prepareStatement(" insert into student (group_id, calculatescholarship, delete_status) values (?,?, ?); ", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setLong(1, groupDao.create(student.getGroup()));
        preparedStatement.setDouble(2, student.getCalculateScholarship());
        preparedStatement.setBoolean(3, true);
        preparedStatement.executeUpdate();

        ResultSet rs = preparedStatement.getGeneratedKeys();
        if(rs.next()){
            keyId = rs.getLong(1);

        }


        return keyId;

    }
    public long update(Student student ) throws SQLException{
        long keyId = 0L;
        preparedStatement = Settings.getConnection().prepareStatement(" update student set group_id = ? , calculatescholarship = ? where student_id = ? ", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setLong(1, groupDao.update(student.getGroup()));
        preparedStatement.setDouble(2, student.getCalculateScholarship());
        preparedStatement.setLong(3, student.getId());
        preparedStatement.executeUpdate();

        ResultSet rs = preparedStatement.getGeneratedKeys();
        if(rs.next()){
            keyId = rs.getLong(1);

        }


        return keyId;

    }
    public long updateStatus(Student student ) throws SQLException{
        long keyId = 0L;
        preparedStatement = Settings.getConnection().prepareStatement(" update student set delete_status = ? where student_id = ? ", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setBoolean(1, student.isDeleteStatus());
        preparedStatement.setLong(2, student.getId());
        preparedStatement.executeUpdate();

        ResultSet rs = preparedStatement.getGeneratedKeys();
        if(rs.next()){
            keyId = rs.getLong(1);

        }


        return keyId;

    }

}
