package com.wallpad.ventilation.repository.common;

import com.wallpad.ventilation.model.VentilationModel;
import com.wallpad.ventilation.repository.local.entities.VentilationEntity;
import com.wallpad.ventilation.repository.local.entities.VentilationPropertyEntity;
import com.wallpad.ventilation.repository.local.entities.VentilationStateEntity;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static VentilationModel mapToVentilationModel(VentilationEntity entity) {
        VentilationPropertyEntity property = entity.getProperty();
        VentilationStateEntity state = entity.getState();
        return new VentilationModel(property.getId(), property.getAirVolumeRange(), property.isCo2Sensor(), property.isSaveMode(), property.isAutoMode(), property.isHeatMode(), property.isSleepMode(), property.isByPassMode(),
                new VentilationModel.State(state.getId(), state.getError(), state.isOn(), state.getAirVolume(), mapToVentilationMode(state.getMode()), state.isHeatOn(), state.isCo2Overload(), state.isSmokeOn(), state.isFilterChangeOn(), state.isHeatChangeOn(), state.isFanOverload()));
    }

    public static VentilationEntity mapToVentilationEntity(VentilationModel model) {
        VentilationPropertyEntity property = new VentilationPropertyEntity(model.getId(), model.getAirVolumeRange(), model.isCo2Sensor(), model.isSaveMode(), model.isAutoMode(), model.isHeatMode(), model.isSleepMode(), model.isByPassMode());
        VentilationModel.State _state = model.getState();
        VentilationStateEntity state = new VentilationStateEntity(_state.getId(), _state.getError(), _state.isOn(), _state.getAirVolume(), mapToInt(_state.getMode()), _state.isHeatOn(), _state.isCo2Overload(), _state.isSmokeOn(), _state.isFilterChangeOn(), _state.isHeatChangeOn(), _state.isFanOverload());
        return new VentilationEntity(property, state);
    }


    private static VentilationModel.State.MODE mapToVentilationMode(int mode) {
        switch (mode) {
            case 1: return VentilationModel.State.MODE.NORMAL;
            case 2: return VentilationModel.State.MODE.SLEEP;
            case 3: return VentilationModel.State.MODE.HEAT;
            case 4: return VentilationModel.State.MODE.AUTO;
            case 5: return VentilationModel.State.MODE.SAVE;
        }
        return VentilationModel.State.MODE.NORMAL;
    }

    private static int mapToInt(VentilationModel.State.MODE mode) {
        switch (mode) {
            case NORMAL: return 1;
            case SAVE: return 5;
            case AUTO: return 4;
            case HEAT: return 3;
            case SLEEP: return 2;
        }
        return 1;
    }
}
