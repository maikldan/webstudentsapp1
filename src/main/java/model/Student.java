package model;

import java.util.Collection;

/**
 * Created by Student on 2/7/2017.
 */
public class Student extends Person{
    private Long id;
    private Group group;
    private Collection<Discipline> disciplines;
    private Collection<Mark> marks;
    private double calculateScholarship;
    private boolean deleteStatus;

    public Long getId() {
        return id;
    }

    public boolean isDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(boolean deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Collection<Discipline> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(Collection<Discipline> disciplines) {
        this.disciplines = disciplines;
    }

    public Collection<Mark> getMarks() {
        return marks;
    }

    public void setMarks(Collection<Mark> marks) {
        this.marks = marks;
    }

    public double getCalculateScholarship() {
        return calculateScholarship;
    }

    public void setCalculateScholarship(double calculateScholarship) {
        this.calculateScholarship = calculateScholarship;
    }
}
