package com.sphtest.data.db.tables;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "volumes")
public class VolumeDataTable {
    @Id(autoincrement = true)
    private Long pk;

    @Property
    private String id;

    @Property
    private String quarter;

    @Property
    private String year;

    @Property
    private String volume;

    @Generated(hash = 414971193)
    public VolumeDataTable(Long pk, String id, String quarter, String year,
            String volume) {
        this.pk = pk;
        this.id = id;
        this.quarter = quarter;
        this.year = year;
        this.volume = volume;
    }

    @Generated(hash = 1043229581)
    public VolumeDataTable() {
    }

    public Long getPk() {
        return this.pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuarter() {
        return this.quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public String getYear() {
        return this.year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getVolume() {
        return this.volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    
}
