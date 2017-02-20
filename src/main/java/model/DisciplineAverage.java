package model;

/**
 * Created by Student on 2/7/2017.
 */
public class DisciplineAverage {
    private Long id;
    private Student student;
    private Discipline discipline;
    private Double averageMark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Discipline getDisciplina() {
        return discipline;
    }

    public void setDisciplina(Discipline disciplina) {
        this.discipline = discipline;
    }

    public Double getAverageMark() {
        return averageMark;
    }

    public void setAverageMark(Double averageMark) {
        this.averageMark = averageMark;
    }
}
