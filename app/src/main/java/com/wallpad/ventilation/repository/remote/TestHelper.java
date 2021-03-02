package com.wallpad.ventilation.repository.remote;

import com.wallpad.ventilation.model.VentilationModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class TestHelper {
    private final List<VentilationModel> ventilationModels = new ArrayList<>();

    @Inject public TestHelper() { init(); }


    public List<VentilationModel> getVentilations() { return ventilationModels; }
    public void requestVentilationOn(int id, boolean on) {
        for ( VentilationModel model : ventilationModels ) {
            if ( model.getId() != id ) continue;
            model.getState().setOn(on);
        }
    }


    private void init() {
        ventilationModels.add(new VentilationModel(
                0, 4, true, true, true, true, true, true,
                new VentilationModel.State(
                        0, 0, true, 3, VentilationModel.State.MODE.AUTO, false,
                        false, false, false, false, false))
        );

    }
}
