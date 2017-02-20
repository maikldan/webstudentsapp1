package model;

/**
 * Created by Student on 2/7/2017.
 */
public class Profesor extends Person{
    private Long id;
    private Double salary;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
