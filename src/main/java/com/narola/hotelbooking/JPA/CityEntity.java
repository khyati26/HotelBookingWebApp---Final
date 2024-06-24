package com.narola.hotelbooking.JPA;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "city")
public class CityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "cityname", nullable = false)
    private String cityName;

    @ManyToOne(cascade = {CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name = "stateid")
    private StateEntity state;

    private String image;

    private int popular;

    @Column(name = "createdon", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdon;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedon;

    public CityEntity() {
    }

    public CityEntity(int id, String cityName, StateEntity state, String image, int popular, Date createdon, Date updatedon) {
        this.id = id;
        this.cityName = cityName;
        this.state = state;
        this.image = image;
        this.popular = popular;
        this.createdon = createdon;
        this.updatedon = updatedon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public StateEntity getState() {
        return state;
    }

    public void setState(StateEntity state) {
        this.state = state;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPopular() {
        return popular;
    }

    public void setPopular(int popular) {
        this.popular = popular;
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
}
