package com.wallpad.ventilation.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.wallpad.IWallpadData;
import com.wallpad.ventilation.model.VentilationModel;
import com.wallpad.ventilation.repository.common.Mapper;
import com.wallpad.ventilation.repository.local.dao.VentilationDao;
import com.wallpad.ventilation.repository.local.entities.VentilationEntity;
import com.wallpad.ventilation.repository.local.entities.VentilationStateEntity;
import com.wallpad.ventilation.repository.remote.IWallpadServiceHelper;
import com.wallpad.ventilation.repository.remote.TestHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Repository {
    private final ExecutorService executorService = Executors.newFixedThreadPool(6);
    private final IWallpadServiceHelper IWallpadServiceHelper;
    private final TestHelper testHelper;

    private final VentilationDao ventilationDao;
    private final LiveData<VentilationModel> ventilation;

    @Inject public Repository( TestHelper testHelper,
        IWallpadServiceHelper IWallpadServiceHelper,
        VentilationDao ventilationDao
    ) {
        this.testHelper = testHelper;
        this.IWallpadServiceHelper = IWallpadServiceHelper;
        this.ventilationDao = ventilationDao;

        ventilation = Transformations.map(ventilationDao.getEntity(), Mapper::mapToVentilationModel);
    }

    public  LiveData<VentilationModel> getVentilation() {
        executorService.execute(()->setVentilation(testHelper.getVentilation()));
        return ventilation;
    }

    private void setVentilation(VentilationModel model) {
        executorService.execute(() -> {
            if ( model == null ) return;
            VentilationEntity entity = Mapper.mapToVentilationEntity(model);
            ventilationDao.insertProperty(entity.getProperty());
            ventilationDao.insertState(entity.getState());
        });
    }

    public void injectIWallpadService(IWallpadData iWallpadData) {
        IWallpadServiceHelper.setIGService(iWallpadData, iCallback);
    }

    public void requestMode(int id, VentilationModel.State.MODE mode) {
        testHelper.requestMode(id, mode);
        setVentilation(testHelper.getVentilation());
    }

    public void requestVolume(int id, int volume) {
        testHelper.requestVolume(id, volume);
        setVentilation(testHelper.getVentilation());
    }

    public void requestPower(int id, boolean on) {
        testHelper.requestPowerOn(id, on);
        setVentilation(testHelper.getVentilation());
    }

    private final IWallpadServiceHelper.ICallback iCallback = new IWallpadServiceHelper.ICallback() {
        @Override
        public void onUpdateVentilationState(VentilationStateEntity state) {

        }
    };
}
