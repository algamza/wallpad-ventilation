package com.wallpad.ventilation.repository.common;

import com.wallpad.ventilation.model.VentilationModel;
import com.wallpad.ventilation.repository.local.entities.VentilationEntity;
import com.wallpad.ventilation.repository.local.entities.VentilationPropertyEntity;
import com.wallpad.ventilation.repository.local.entities.VentilationStateEntity;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static List<VentilationModel> mapToVentilationModels(List<VentilationEntity> entities) {
        List<VentilationModel> models = new ArrayList<>();
        for ( VentilationEntity entity : entities ) {
            VentilationPropertyEntity property = entity.getProperty();
            if ( hasGroupId(models, property.getGroupId()) ) continue;
            models.add(new VentilationModel(property.getGroupId(), property.getAirVolumeRange(),
                    property.isCo2Sensor(), property.isSaveMode(), property.isAutoMode(), property.isHeatMode(),
                    property.isSleepMode(), property.isByPassMode(), new ArrayList<>()));
        }
        for ( VentilationEntity entity : entities ) {
            VentilationPropertyEntity property = entity.getProperty();
            VentilationStateEntity state = entity.getState();
            if ( property == null ) continue;
            int id = property.getChannelId();
            int error = 0;
            boolean on = false;
            int airVolume = 0;
            VentilationModel.State.MODE mode = VentilationModel.State.MODE.AUTO;
            boolean heatOn = false;
            boolean co2Overload = false;
            boolean smokeOn = false;
            boolean filterChangeOn = false;
            boolean heatChangeOn = false;
            boolean fanOverload = false;
            if ( state != null ) {
                error = state.getError();
                on = state.isOn();
                airVolume = state.getAirVolume();
                mode = mapToMode(state.getMode());
                heatOn = state.isHeatOn();
                co2Overload = state.isCo2Overload();
                smokeOn = state.isSmokeOn();
                filterChangeOn = state.isFilterChangeOn();
                heatChangeOn = state.isHeatChangeOn();
                fanOverload = state.isFanOverload();
            }
            VentilationModel model = getVentilationModel(models, property.getGroupId());
            List<VentilationModel.State> states = model.getStates();
            states.add(new VentilationModel.State(id, error, on, airVolume, mode, heatOn, co2Overload,
                    smokeOn, filterChangeOn, heatChangeOn, fanOverload));
        }
        return models;
    }

    private static VentilationModel.State.MODE mapToMode(int mode) {
        switch (mode) {
            case 1: return VentilationModel.State.MODE.NORMAL;
            case 2: return VentilationModel.State.MODE.SLEEP;
            case 3: return VentilationModel.State.MODE.HEAT;
            case 4: return VentilationModel.State.MODE.AUTO;
            case 5: return VentilationModel.State.MODE.SAVE;
        }
        return VentilationModel.State.MODE.NORMAL;
    }

    public static int mapToMode(VentilationModel.State.MODE mode) {
        switch (mode) {
            case NORMAL: return 1;
            case SLEEP: return 2;
            case HEAT: return 3;
            case AUTO: return 4;
            case SAVE: return 5;
        }
        return 1;
    }

    public static List<Integer> getKeys(List<VentilationPropertyEntity> entities) {
        List<Integer> keys = new ArrayList<>();
        for ( VentilationPropertyEntity entity : entities ) {
            if ( entity == null ) continue;
            keys.add(entity.getPrimaryKey());
        }
        return keys;
    }

    private static VentilationModel getVentilationModel(List<VentilationModel> models, int id) {
        for ( VentilationModel model: models ) {
            if ( model.getId() == id ) return model;
        }
        return null;
    }

    private static boolean hasGroupId(List<VentilationModel> models, int id) {
        for ( VentilationModel model : models ) {
            if ( model.getId() == id ) return true;
        }
        return false;
    }

    public static List<VentilationEntity> mapToLightEntities(List<VentilationModel> models) {
        List<VentilationEntity> entities = new ArrayList<>();
        for ( VentilationModel model: models) {
            int groupId = model.getId();
            List<VentilationModel.State> states = model.getStates();
            for ( VentilationModel.State state : states ) {
                int channelId = state.getId();
                int key = Mapper.getKey(groupId, channelId);
                VentilationPropertyEntity propertyEntity = new VentilationPropertyEntity(key, groupId,
                        channelId, model.getAirVolumeRange(), model.isCo2Sensor(), model.isSaveMode(),
                        model.isAutoMode(), model.isHeatMode(), model.isSleepMode(), model.isByPassMode());
                VentilationStateEntity stateEntity = new VentilationStateEntity(key, groupId, channelId,
                        state.getError(), state.isOn(), state.getAirVolume(), mapToMode(state.getMode()),
                        state.isHeatOn(), state.isCo2Overload(), state.isSmokeOn(), state.isFilterChangeOn(),
                        state.isHeatChangeOn(), state.isFanOverload());
                entities.add(new VentilationEntity(propertyEntity, stateEntity));
            }
        }
        return entities;
    }

    public static int getKey(int group, int channel) {
        return group*100+channel;
    }
}
