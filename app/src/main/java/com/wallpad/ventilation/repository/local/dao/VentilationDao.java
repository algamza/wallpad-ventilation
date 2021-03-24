package com.wallpad.ventilation.repository.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.wallpad.ventilation.repository.local.entities.VentilationEntity;
import com.wallpad.ventilation.repository.local.entities.VentilationPropertyEntity;
import com.wallpad.ventilation.repository.local.entities.VentilationStateEntity;

@Dao
public interface VentilationDao {
    @Transaction
    @Query("SELECT * FROM VentilationPropertyEntity")
    LiveData<VentilationEntity> getEntity();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProperty(VentilationPropertyEntity property);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertState(VentilationStateEntity state);

    @Query("DELETE FROM VentilationPropertyEntity")
    void deleteEntity();
}
