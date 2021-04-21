package com.wallpad.ventilation.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.wallpad.IWallpadData;
import com.wallpad.ventilation.model.VentilationModel;
import com.wallpad.ventilation.repository.common.Mapper;
import com.wallpad.ventilation.repository.local.dao.VentilationDao;
import com.wallpad.ventilation.repository.local.entities.VentilationEntity;
import com.wallpad.ventilation.repository.local.entities.VentilationPropertyEntity;
import com.wallpad.ventilation.repository.local.entities.VentilationStateEntity;
import com.wallpad.ventilation.repository.remote.ContentProviderHelper;
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
    private final IWallpadServiceHelper iWallpadServiceHelper;
    private final ContentProviderHelper contentProviderHelper;
    private final TestHelper testHelper;

    private final VentilationDao ventilationDao;
    private final LiveData<List<VentilationModel>> models;

    @Inject public Repository( TestHelper testHelper,
                               ContentProviderHelper contentProviderHelper,
                               IWallpadServiceHelper iWallpadServiceHelper,
                               VentilationDao ventilationDao
    ) {
        this.testHelper = testHelper;
        this.iWallpadServiceHelper = iWallpadServiceHelper;
        this.contentProviderHelper = contentProviderHelper;
        this.ventilationDao = ventilationDao;

        models = Transformations.map(ventilationDao.getEntities(), Mapper::mapToVentilationModels);
    }

    public void injectIWallpadService(IWallpadData iWallpadData) {
        iWallpadServiceHelper.setIGService(iWallpadData, serviceCallback);
        contentProviderHelper.setCallback(providerCallback);
        contentProviderHelper.requestProperties();
    }

    public LiveData<List<VentilationModel>> getVentilations() {
        executorService.execute(contentProviderHelper::requestProperties);
        return models;
    }

    public void requestControlPowerOn(int group, int sub, boolean on) {
        iWallpadServiceHelper.requestPowerOn(group, sub, on);
    }

    public void requestControlVolume(int group, int sub, int volume) {
        iWallpadServiceHelper.requestVolume(group, sub, volume);
    }

    public void requestControlMode(int group, int sub, VentilationModel.State.MODE mode) {
        iWallpadServiceHelper.requestMode(group, sub, Mapper.mapToMode(mode));
    }

    private final IWallpadServiceHelper.ICallback serviceCallback = new IWallpadServiceHelper.ICallback() {
        @Override
        public void onUpdateStates(List<VentilationStateEntity> states) {
            executorService.execute(() -> ventilationDao.insertStates(states));
        }
    };

    private final ContentProviderHelper.ICallback providerCallback = new ContentProviderHelper.ICallback() {
        @Override
        public void onUpdateProperties(List<VentilationPropertyEntity> properties) {
            executorService.execute(()->{
                ventilationDao.deleteNotInEntities(Mapper.getKeys(properties));
                ventilationDao.insertProperties(properties);
                int groupId = 0;
                for ( VentilationPropertyEntity property : properties ) {
                    if ( groupId != property.getGroupId() ) {
                        groupId = property.getGroupId();
                        iWallpadServiceHelper.requestStates(groupId);
                    }
                }
            });
        }
    };
}
