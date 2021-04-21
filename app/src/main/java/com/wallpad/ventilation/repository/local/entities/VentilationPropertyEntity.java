package com.wallpad.ventilation.repository.local.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class VentilationPropertyEntity {
    @PrimaryKey
    private int primaryKey;
    private int groupId;
    private int channelId;
    private int airVolumeRange;
    private boolean co2Sensor;
    private boolean saveMode;
    private boolean autoMode;
    private boolean heatMode;
    private boolean sleepMode;
    private boolean byPassMode;

    public VentilationPropertyEntity(int primaryKey, int groupId, int channelId, int airVolumeRange, boolean co2Sensor, boolean saveMode, boolean autoMode, boolean heatMode, boolean sleepMode, boolean byPassMode) {
        this.primaryKey = primaryKey;
        this.groupId = groupId;
        this.channelId = channelId;
        this.airVolumeRange = airVolumeRange;
        this.co2Sensor = co2Sensor;
        this.saveMode = saveMode;
        this.autoMode = autoMode;
        this.heatMode = heatMode;
        this.sleepMode = sleepMode;
        this.byPassMode = byPassMode;
    }

    public int getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(int primaryKey) {
        this.primaryKey = primaryKey;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public int getAirVolumeRange() {
        return airVolumeRange;
    }

    public void setAirVolumeRange(int airVolumeRange) {
        this.airVolumeRange = airVolumeRange;
    }

    public boolean isCo2Sensor() {
        return co2Sensor;
    }

    public void setCo2Sensor(boolean co2Sensor) {
        this.co2Sensor = co2Sensor;
    }

    public boolean isSaveMode() {
        return saveMode;
    }

    public void setSaveMode(boolean saveMode) {
        this.saveMode = saveMode;
    }

    public boolean isAutoMode() {
        return autoMode;
    }

    public void setAutoMode(boolean autoMode) {
        this.autoMode = autoMode;
    }

    public boolean isHeatMode() {
        return heatMode;
    }

    public void setHeatMode(boolean heatMode) {
        this.heatMode = heatMode;
    }

    public boolean isSleepMode() {
        return sleepMode;
    }

    public void setSleepMode(boolean sleepMode) {
        this.sleepMode = sleepMode;
    }

    public boolean isByPassMode() {
        return byPassMode;
    }

    public void setByPassMode(boolean byPassMode) {
        this.byPassMode = byPassMode;
    }
}
