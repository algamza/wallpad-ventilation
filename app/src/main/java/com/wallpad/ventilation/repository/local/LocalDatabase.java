package com.wallpad.ventilation.repository.local;

import androidx.room.RoomDatabase;

import com.wallpad.ventilation.repository.local.dao.VentilationDao;
import com.wallpad.ventilation.repository.local.entities.VentilationPropertyEntity;
import com.wallpad.ventilation.repository.local.entities.VentilationStateEntity;

@androidx.room.Database(entities = {
        VentilationPropertyEntity.class,
        VentilationStateEntity.class
}, version = 1, exportSchema = false)
public abstract class LocalDatabase extends RoomDatabase {
    public abstract VentilationDao ventilationDao();
}
