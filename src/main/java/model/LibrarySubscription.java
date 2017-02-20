package model;

import java.sql.Date;

/**
 * Created by Student on 2/7/2017.
 */
public class LibrarySubscription {
    private Long id;
    private Status status;
    private Date startdate;
    private Date enddate;
    public LibrarySubscription(){}

    public LibrarySubscription(Status status){
        this.status = status;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }
}
