package com.wallpad.ventilation.repository.local.entities;

import androidx.room.Embedded;
import androidx.room.Relation;

public class VentilationEntity {
    @Embedded private VentilationPropertyEntity property;
    @Relation(
            parentColumn = "primaryKey",
            entityColumn = "primaryKey",
            entity = VentilationStateEntity.class
    )
    private VentilationStateEntity state;

    public VentilationEntity(VentilationPropertyEntity property, VentilationStateEntity state) {
        this.property = property;
        this.state = state;
    }

    public VentilationPropertyEntity getProperty() {
        return property;
    }

    public void setProperty(VentilationPropertyEntity property) {
        this.property = property;
    }

    public VentilationStateEntity getState() {
        return state;
    }

    public void setState(VentilationStateEntity state) {
        this.state = state;
    }
}
