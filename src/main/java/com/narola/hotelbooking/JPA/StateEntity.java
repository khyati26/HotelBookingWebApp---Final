package com.narola.hotelbooking.JPA;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "state")
public class StateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", nullable = false, length = 30)
    private String statename;
    @Column(name = "createdon", nullable = true)
    private Date createdon;
    @Temporal(TemporalType.DATE)
    private Date updatedon;

    public StateEntity() {
    }

    public StateEntity(int id, String statename, Date createdon, Date updatedon) {
        this.id = id;
        this.statename = statename;
        this.createdon = createdon;
        this.updatedon = updatedon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatename() {
        return statename;
    }

    public void setStatename(String statename) {
        this.statename = statename;
    }

    public Date getCreatedon() {
        return createdon;
    }

    public void setCreatedon(Date createdon) {
        this.createdon = createdon;
    }

    public Date getUpdatedon() {
        return updatedon;
    }

    public void setUpdatedon(Date updatedon) {
        this.updatedon = updatedon;
    }

    @Override
    public String toString() {
        return "StateEntity{" +
                "id=" + id +
                ", statename='" + statename + '\'' +
                ", createdon=" + createdon +
                ", updatedon=" + updatedon +
                '}';
    }
}
