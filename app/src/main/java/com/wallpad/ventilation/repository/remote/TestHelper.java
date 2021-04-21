package com.wallpad.ventilation.repository.remote;

import com.wallpad.ventilation.model.VentilationModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class TestHelper {
    private VentilationModel ventilationModel = null;

    @Inject public TestHelper() { init(); }

/*
    public VentilationModel getVentilation() { return ventilationModel; }
    public void requestPowerOn(int id, boolean on) {
        ventilationModel.getState().setOn(on);
    }
    public void requestMode(int id, VentilationModel.State.MODE mode) { ventilationModel.getState().setMode(mode); }
    public void requestVolume(int id, int volume) { ventilationModel.getState().setAirVolume(volume); }
*/
    private void init() {
        /*
        ventilationModel = new VentilationModel(
                0, 4, true, true, true, true, true, true,
                new VentilationModel.State(
                        0, 0, true, 3, VentilationModel.State.MODE.AUTO, false,
                        false, false, false, false, false));

         */

    }
}
