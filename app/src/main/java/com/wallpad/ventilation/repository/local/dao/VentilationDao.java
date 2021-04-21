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

import java.util.List;

@Dao
public interface VentilationDao {
    @Transaction
    @Query("SELECT * FROM VentilationPropertyEntity")
    LiveData<List<VentilationEntity>> getEntities();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProperties(List<VentilationPropertyEntity> property);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStates(List<VentilationStateEntity> states);

    @Query("DELETE FROM VentilationPropertyEntity")
    void deleteEntities();

    @Query("DELETE FROM VentilationPropertyEntity WHERE groupId = :groupId")
    void deleteEntity(int groupId);

    @Query("DELETE FROM VentilationPropertyEntity WHERE primaryKey NOT IN (:keys)")
    void deleteNotInEntities(List<Integer> keys);
}
