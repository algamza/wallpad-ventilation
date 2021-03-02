package com.wallpad.ventilation.model;

import java.util.List;

public class VentilationModel {
    private int id;
    private int airVolumeRange;
    private boolean co2Sensor;
    private boolean saveMode;
    private boolean autoMode;
    private boolean heatMode;
    private boolean sleepMode;
    private boolean byPassMode;
    private State state;

    public VentilationModel(int id, int airVolumeRange, boolean co2Sensor, boolean saveMode, boolean autoMode, boolean heatMode, boolean sleepMode, boolean byPassMode, State state) {
        this.id = id;
        this.airVolumeRange = airVolumeRange;
        this.co2Sensor = co2Sensor;
        this.saveMode = saveMode;
        this.autoMode = autoMode;
        this.heatMode = heatMode;
        this.sleepMode = sleepMode;
        this.byPassMode = byPassMode;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public static class State {
        public enum MODE {
            NORMAL,
            SLEEP,
            HEAT,
            AUTO,
            SAVE
        }
        private int id;
        private int error;
        private boolean on;
        private int airVolume;
        private MODE mode;
        private boolean heatOn;
        private boolean co2Overload;
        private boolean smokeOn;
        private boolean filterChangeOn;
        private boolean heatChangeOn;
        private boolean fanOverload;

        public State(int id, int error, boolean on, int airVolume, MODE mode, boolean heatOn, boolean co2Overload, boolean smokeOn, boolean filterChangeOn, boolean heatChangeOn, boolean fanOverload) {
            this.id = id;
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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public MODE getMode() {
            return mode;
        }

        public void setMode(MODE mode) {
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
}
