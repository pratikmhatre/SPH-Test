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
    private Long id;
    @Property
    private String quarter;

    @Property
    private String volume;

    @Generated(hash = 1500977876)
    public VolumeDataTable(Long pk, Long id, String quarter, String volume) {
        this.pk = pk;
        this.id = id;
        this.quarter = quarter;
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

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuarter() {
        return this.quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public String getVolume() {
        return this.volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }
}
