package model;

/**
 * Created by Student on 2/7/2017.
 */
public class Discipline {
    private Long id;
    private String title;
    private Profesor profesor;
    private  Double scholarshipThreshold;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Double getScholarshipThreshold() {
        return scholarshipThreshold;
    }

    public void setScholarshipThreshold(Double scholarshipThreshold) {
        this.scholarshipThreshold = scholarshipThreshold;
    }
}
