package com.wallpad.ventilation.view.ventilation;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.wallpad.ventilation.R;
import com.wallpad.ventilation.model.VentilationModel;
import com.wallpad.ventilation.repository.Repository;

public class VentilationViewModel extends ViewModel {
    private Repository repository;
    private final LiveData<Ventilation> ventilation;

    @ViewModelInject
    public VentilationViewModel(Repository repository) {
        this.repository = repository;
        ventilation = Transformations.map(repository.getVentilation(), model -> {
            VentilationModel.State state = model.getState();
            if ( state == null ) return new Ventilation(callback, model.getId(), model.getAirVolumeRange(), model.isCo2Sensor(), model.isSaveMode(), model.isAutoMode(), model.isHeatMode(), model.isSleepMode(), model.isByPassMode(), false, Ventilation.MODE.AUTO, 0,false, false, false, false, false, false, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            return new Ventilation(callback, model.getId(), model.getAirVolumeRange(), model.isCo2Sensor(),
                    model.isSaveMode(), model.isAutoMode(), model.isHeatMode(), model.isSleepMode(), model.isByPassMode(),
                    state.isOn(), mapToMode(state.getMode(), state.isOn()), mapToVolume(state.getAirVolume(), state.isOn()), state.isHeatOn(), state.isCo2Overload(), state.isSmokeOn(),
                    state.isFilterChangeOn(), state.isHeatChangeOn(), state.isFanOverload(), mapToResTextMode(state.getMode(), state.isOn()),
                    mapToResIconMode(state.getMode()), mapToResTextVolume(state.getAirVolume(), state.isOn()), mapToResTextErrCo2(state.isCo2Overload(), state.isOn()),
                    mapToResTextErrHeat(state.isHeatOn(), state.isOn()), mapToResTextErrSmoke(state.isSmokeOn(), state.isOn()),
                    mapToResTextErrFilter(state.isFilterChangeOn(), state.isOn()), mapToResTextErrChanger(state.isHeatChangeOn(), state.isOn()), mapToResTextErrPan(state.isFanOverload(), state.isOn()));
        });
    }

    public LiveData<Ventilation> getVentilation() { return ventilation; }

    private final Ventilation.Callback callback = new Ventilation.Callback() {
        @Override
        public void onClickMode(int id, Ventilation.MODE mode) { repository.requestMode(id, mapToMode(mode)); }

        @Override
        public void onClickVolume(int id, int volume) {
            repository.requestVolume(id, volume);
        }

        @Override
        public void onClickPower(int id, boolean on) {
            repository.requestPower(id, on);
        }
    };

    private int mapToVolume(int volume, boolean on) {
        if ( !on ) return 0;
        switch (volume) {
            case 0: return 0;
            case 1: return 1;
            case 2: return 2;
            case 3: return 3;
        }
        return 0;
    }

    private VentilationModel.State.MODE mapToMode(Ventilation.MODE mode) {
        switch (mode) {
            case NORMAL: return VentilationModel.State.MODE.NORMAL;
            case SAVE: return VentilationModel.State.MODE.SAVE;
            case HEAT: return VentilationModel.State.MODE.HEAT;
            case SLEEP: return VentilationModel.State.MODE.SLEEP;
            case AUTO: return VentilationModel.State.MODE.AUTO;
        }
        return VentilationModel.State.MODE.NORMAL;
    }

    private Ventilation.MODE mapToMode(VentilationModel.State.MODE mode, boolean on) {
        if ( !on ) return Ventilation.MODE.NONE;
        switch (mode) {
            case NORMAL: return Ventilation.MODE.NORMAL;
            case AUTO: return Ventilation.MODE.AUTO;
            case SLEEP: return Ventilation.MODE.SLEEP;
            case HEAT: return Ventilation.MODE.HEAT;
            case SAVE: return Ventilation.MODE.SAVE;
        }
        return Ventilation.MODE.NONE;
    }

    private int mapToResTextMode(VentilationModel.State.MODE mode, boolean on) {
        if ( !on ) return R.string.STR_DASH;
        switch (mode) {
            case NORMAL: return R.string.STR_NORMAL;
            case AUTO: return R.string.STR_AUTO;
            case SLEEP: return R.string.STR_SLEEP;
            case HEAT: return R.string.STR_HEAT;
            case SAVE: return R.string.STR_SAVE;
        }
        return 0;
    }

    private int mapToResIconMode(VentilationModel.State.MODE mode) {
        switch (mode) {
            case SAVE: return R.drawable.ic_sub_ventilation_saving_nor;
            case HEAT: return R.drawable.ic_sub_ventilation_battleline_nor;
            case SLEEP: return R.drawable.ic_sub_ventilation_sleep_nor;
            case AUTO: return R.drawable.ic_sub_ventilation_auto_nor;
            case NORMAL: return R.drawable.ic_sub_ventilation_normal_nor;
        }
        return 0;
    }

    private int mapToResTextVolume(int volume, boolean on) {
        if ( !on ) return R.string.STR_DASH;
        switch (volume) {
            case 0: return R.string.STR_DASH;
            case 1: return R.string.STR_STEP_1;
            case 2: return R.string.STR_STEP_2;
            case 3: return R.string.STR_STEP_3;
        }
        return 0;
    }

    private int mapToResTextErrCo2(boolean err, boolean on) {
        if ( !on ) return R.string.STR_DASH;
        if ( err ) return R.string.STR_EXCESSIVE_CONCENTRATION;
        return R.string.STR_RUN_NORMAL;
    }

    private int mapToResTextErrHeat(boolean err, boolean on) {
        if ( !on ) return R.string.STR_DASH;
        if ( err ) return R.string.STR_RUNNING;
        return R.string.STR_STOP;
    }

    private int mapToResTextErrSmoke(boolean err, boolean on) {
        if ( !on ) return R.string.STR_DASH;
        if ( err ) return R.string.STR_SMOKE_RUN;
        return R.string.STR_RUN_NORMAL;
    }

    private int mapToResTextErrFilter(boolean err, boolean on) {
        if ( !on ) return R.string.STR_DASH;
        if ( err ) return R.string.STR_NEED_CHANGE;
        return R.string.STR_RUN_NORMAL;
    }

    private int mapToResTextErrChanger(boolean err, boolean on) {
        if ( !on ) return R.string.STR_DASH;
        if ( err ) return R.string.STR_NEED_CHANGE;
        return R.string.STR_RUN_NORMAL;
    }

    private int mapToResTextErrPan(boolean err, boolean on) {
        if ( !on ) return R.string.STR_DASH;
        if ( err ) return R.string.STR_OVERLOAD;
        return R.string.STR_RUN_NORMAL;
    }

    public static class Ventilation {
        public interface Callback {
            void onClickMode(int id, MODE mode);
            void onClickVolume(int id, int volume);
            void onClickPower(int id, boolean on);
        }
        public enum MODE {
            NONE,
            NORMAL,
            SLEEP,
            HEAT,
            AUTO,
            SAVE
        }
        private Callback callback;
        private int id;
        private int volumeRange;
        private boolean supportCo2;
        private boolean supportSave;
        private boolean supportAuto;
        private boolean supportHeat;
        private boolean supportSleep;
        private boolean supportByPass;
        private boolean on;
        private MODE mode;
        private int volume;
        private boolean errHeat;
        private boolean errCo2;
        private boolean errSmoke;
        private boolean errFilter;
        private boolean errChanger;
        private boolean errPan;
        private int resIdTextMode;
        private int resIdIconMode;
        private int resIdTextVolume;
        private int resIdTextCo2;
        private int resIdTextHeat;
        private int resIdTextSmoke;
        private int resIdTextFilter;
        private int resIdTextChanger;
        private int resIdTextPan;

        public Ventilation(Callback callback, int id, int volumeRange, boolean supportCo2, boolean supportSave, boolean supportAuto, boolean supportHeat, boolean supportSleep, boolean supportByPass, boolean on, MODE mode, int volume, boolean errHeat, boolean errCo2, boolean errSmoke, boolean errFilter, boolean errChanger, boolean errPan, int resIdTextMode, int resIdIconMode, int resIdTextVolume, int resIdTextCo2, int resIdTextHeat, int resIdTextSmoke, int resIdTextFilter, int resIdTextChanger, int resIdTextPan) {
            this.callback = callback;
            this.id = id;
            this.volumeRange = volumeRange;
            this.supportCo2 = supportCo2;
            this.supportSave = supportSave;
            this.supportAuto = supportAuto;
            this.supportHeat = supportHeat;
            this.supportSleep = supportSleep;
            this.supportByPass = supportByPass;
            this.on = on;
            this.mode = mode;
            this.volume = volume;
            this.errHeat = errHeat;
            this.errCo2 = errCo2;
            this.errSmoke = errSmoke;
            this.errFilter = errFilter;
            this.errChanger = errChanger;
            this.errPan = errPan;
            this.resIdTextMode = resIdTextMode;
            this.resIdIconMode = resIdIconMode;
            this.resIdTextVolume = resIdTextVolume;
            this.resIdTextCo2 = resIdTextCo2;
            this.resIdTextHeat = resIdTextHeat;
            this.resIdTextSmoke = resIdTextSmoke;
            this.resIdTextFilter = resIdTextFilter;
            this.resIdTextChanger = resIdTextChanger;
            this.resIdTextPan = resIdTextPan;
        }

        public Callback getCallback() {
            return callback;
        }

        public void setCallback(Callback callback) {
            this.callback = callback;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getVolumeRange() {
            return volumeRange;
        }

        public void setVolumeRange(int volumeRange) {
            this.volumeRange = volumeRange;
        }

        public boolean isSupportCo2() {
            return supportCo2;
        }

        public void setSupportCo2(boolean supportCo2) {
            this.supportCo2 = supportCo2;
        }

        public boolean isSupportSave() {
            return supportSave;
        }

        public void setSupportSave(boolean supportSave) {
            this.supportSave = supportSave;
        }

        public boolean isSupportAuto() {
            return supportAuto;
        }

        public void setSupportAuto(boolean supportAuto) {
            this.supportAuto = supportAuto;
        }

        public boolean isSupportHeat() {
            return supportHeat;
        }

        public void setSupportHeat(boolean supportHeat) {
            this.supportHeat = supportHeat;
        }

        public boolean isSupportSleep() {
            return supportSleep;
        }

        public void setSupportSleep(boolean supportSleep) {
            this.supportSleep = supportSleep;
        }

        public boolean isSupportByPass() {
            return supportByPass;
        }

        public void setSupportByPass(boolean supportByPass) {
            this.supportByPass = supportByPass;
        }

        public boolean isOn() {
            return on;
        }

        public void setOn(boolean on) {
            this.on = on;
        }

        public MODE getMode() {
            return mode;
        }

        public void setMode(MODE mode) {
            this.mode = mode;
        }

        public int getVolume() {
            return volume;
        }

        public void setVolume(int volume) {
            this.volume = volume;
        }

        public boolean isErrHeat() {
            return errHeat;
        }

        public void setErrHeat(boolean errHeat) {
            this.errHeat = errHeat;
        }

        public boolean isErrCo2() {
            return errCo2;
        }

        public void setErrCo2(boolean errCo2) {
            this.errCo2 = errCo2;
        }

        public boolean isErrSmoke() {
            return errSmoke;
        }

        public void setErrSmoke(boolean errSmoke) {
            this.errSmoke = errSmoke;
        }

        public boolean isErrFilter() {
            return errFilter;
        }

        public void setErrFilter(boolean errFilter) {
            this.errFilter = errFilter;
        }

        public boolean isErrChanger() {
            return errChanger;
        }

        public void setErrChanger(boolean errChanger) {
            this.errChanger = errChanger;
        }

        public boolean isErrPan() {
            return errPan;
        }

        public void setErrPan(boolean errPan) {
            this.errPan = errPan;
        }

        public int getResIdTextMode() {
            return resIdTextMode;
        }

        public void setResIdTextMode(int resIdTextMode) {
            this.resIdTextMode = resIdTextMode;
        }

        public int getResIdIconMode() {
            return resIdIconMode;
        }

        public void setResIdIconMode(int resIdIconMode) {
            this.resIdIconMode = resIdIconMode;
        }

        public int getResIdTextVolume() {
            return resIdTextVolume;
        }

        public void setResIdTextVolume(int resIdTextVolume) {
            this.resIdTextVolume = resIdTextVolume;
        }

        public int getResIdTextCo2() {
            return resIdTextCo2;
        }

        public void setResIdTextCo2(int resIdTextCo2) {
            this.resIdTextCo2 = resIdTextCo2;
        }

        public int getResIdTextHeat() {
            return resIdTextHeat;
        }

        public void setResIdTextHeat(int resIdTextHeat) {
            this.resIdTextHeat = resIdTextHeat;
        }

        public int getResIdTextSmoke() {
            return resIdTextSmoke;
        }

        public void setResIdTextSmoke(int resIdTextSmoke) {
            this.resIdTextSmoke = resIdTextSmoke;
        }

        public int getResIdTextFilter() {
            return resIdTextFilter;
        }

        public void setResIdTextFilter(int resIdTextFilter) {
            this.resIdTextFilter = resIdTextFilter;
        }

        public int getResIdTextChanger() {
            return resIdTextChanger;
        }

        public void setResIdTextChanger(int resIdTextChanger) {
            this.resIdTextChanger = resIdTextChanger;
        }

        public int getResIdTextPan() {
            return resIdTextPan;
        }

        public void setResIdTextPan(int resIdTextPan) {
            this.resIdTextPan = resIdTextPan;
        }
    }
}