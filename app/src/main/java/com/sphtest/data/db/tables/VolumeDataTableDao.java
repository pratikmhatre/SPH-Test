package com.sphtest.data.db.tables;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "volumes".
*/
public class VolumeDataTableDao extends AbstractDao<VolumeDataTable, Long> {

    public static final String TABLENAME = "volumes";

    /**
     * Properties of entity VolumeDataTable.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Pk = new Property(0, Long.class, "pk", true, "_id");
        public final static Property Id = new Property(1, String.class, "id", false, "ID");
        public final static Property Quarter = new Property(2, String.class, "quarter", false, "QUARTER");
        public final static Property Year = new Property(3, String.class, "year", false, "YEAR");
        public final static Property Volume = new Property(4, String.class, "volume", false, "VOLUME");
        public final static Property IsVolumeDecreased = new Property(5, Boolean.class, "isVolumeDecreased", false, "IS_VOLUME_DECREASED");
    }


    public VolumeDataTableDao(DaoConfig config) {
        super(config);
    }
    
    public VolumeDataTableDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"volumes\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: pk
                "\"ID\" TEXT," + // 1: id
                "\"QUARTER\" TEXT," + // 2: quarter
                "\"YEAR\" TEXT," + // 3: year
                "\"VOLUME\" TEXT," + // 4: volume
                "\"IS_VOLUME_DECREASED\" INTEGER);"); // 5: isVolumeDecreased
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"volumes\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, VolumeDataTable entity) {
        stmt.clearBindings();
 
        Long pk = entity.getPk();
        if (pk != null) {
            stmt.bindLong(1, pk);
        }
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(2, id);
        }
 
        String quarter = entity.getQuarter();
        if (quarter != null) {
            stmt.bindString(3, quarter);
        }
 
        String year = entity.getYear();
        if (year != null) {
            stmt.bindString(4, year);
        }
 
        String volume = entity.getVolume();
        if (volume != null) {
            stmt.bindString(5, volume);
        }
 
        Boolean isVolumeDecreased = entity.getIsVolumeDecreased();
        if (isVolumeDecreased != null) {
            stmt.bindLong(6, isVolumeDecreased ? 1L: 0L);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, VolumeDataTable entity) {
        stmt.clearBindings();
 
        Long pk = entity.getPk();
        if (pk != null) {
            stmt.bindLong(1, pk);
        }
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(2, id);
        }
 
        String quarter = entity.getQuarter();
        if (quarter != null) {
            stmt.bindString(3, quarter);
        }
 
        String year = entity.getYear();
        if (year != null) {
            stmt.bindString(4, year);
        }
 
        String volume = entity.getVolume();
        if (volume != null) {
            stmt.bindString(5, volume);
        }
 
        Boolean isVolumeDecreased = entity.getIsVolumeDecreased();
        if (isVolumeDecreased != null) {
            stmt.bindLong(6, isVolumeDecreased ? 1L: 0L);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public VolumeDataTable readEntity(Cursor cursor, int offset) {
        VolumeDataTable entity = new VolumeDataTable( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // pk
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // id
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // quarter
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // year
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // volume
            cursor.isNull(offset + 5) ? null : cursor.getShort(offset + 5) != 0 // isVolumeDecreased
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, VolumeDataTable entity, int offset) {
        entity.setPk(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setQuarter(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setYear(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setVolume(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setIsVolumeDecreased(cursor.isNull(offset + 5) ? null : cursor.getShort(offset + 5) != 0);
     }
    
    @Override
    protected final Long updateKeyAfterInsert(VolumeDataTable entity, long rowId) {
        entity.setPk(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(VolumeDataTable entity) {
        if(entity != null) {
            return entity.getPk();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(VolumeDataTable entity) {
        return entity.getPk() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
