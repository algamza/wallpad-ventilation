package com.wallpad.ventilation.repository.local.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class VentilationStateEntity {
    @PrimaryKey
    private int primaryKey;
    private int groupId;
    private int channelId;
    private int error;
    private boolean on;
    private int airVolume;
    private int mode;
    private boolean heatOn;
    private boolean co2Overload;
    private boolean smokeOn;
    private boolean filterChangeOn;
    private boolean heatChangeOn;
    private boolean fanOverload;

    public VentilationStateEntity(int primaryKey, int groupId, int channelId, int error, boolean on, int airVolume, int mode, boolean heatOn, boolean co2Overload, boolean smokeOn, boolean filterChangeOn, boolean heatChangeOn, boolean fanOverload) {
        this.primaryKey = primaryKey;
        this.groupId = groupId;
        this.channelId = channelId;
        this.error = error;
        this.on = on;
        this.airVolume = airVolume;
        this.mode = mode;
        this.heatOn = heatOn;
        this.co2Overload = co2Overload;
        this.smokeOn = smokeOn;
        this.filterChangeOn = filterChangeOn;
        this.heatChangeOn = heatChangeOn;
        this.fanOverload = fanOverload;
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

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public int getAirVolume() {
        return airVolume;
    }

    public void setAirVolume(int airVolume) {
        this.airVolume = airVolume;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public boolean isHeatOn() {
        return heatOn;
    }

    public void setHeatOn(boolean heatOn) {
        this.heatOn = heatOn;
    }

    public boolean isCo2Overload() {
        return co2Overload;
    }

    public void setCo2Overload(boolean co2Overload) {
        this.co2Overload = co2Overload;
    }

    public boolean isSmokeOn() {
        return smokeOn;
    }

    public void setSmokeOn(boolean smokeOn) {
        this.smokeOn = smokeOn;
    }

    public boolean isFilterChangeOn() {
        return filterChangeOn;
    }

    public void setFilterChangeOn(boolean filterChangeOn) {
        this.filterChangeOn = filterChangeOn;
    }

    public boolean isHeatChangeOn() {
        return heatChangeOn;
    }

    public void setHeatChangeOn(boolean heatChangeOn) {
        this.heatChangeOn = heatChangeOn;
    }

    public boolean isFanOverload() {
        return fanOverload;
    }

    public void setFanOverload(boolean fanOverload) {
        this.fanOverload = fanOverload;
    }
}
