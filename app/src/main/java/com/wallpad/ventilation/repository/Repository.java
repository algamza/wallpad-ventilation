package com.wallpad.ventilation.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.gsmart.IGSmartData;
import com.wallpad.ventilation.model.VentilationModel;
import com.wallpad.ventilation.repository.common.Mapper;
import com.wallpad.ventilation.repository.local.dao.VentilationDao;
import com.wallpad.ventilation.repository.local.entities.VentilationEntity;
import com.wallpad.ventilation.repository.local.entities.VentilationStateEntity;
import com.wallpad.ventilation.repository.remote.IGServiceHelper;
import com.wallpad.ventilation.repository.remote.ContentProviderHelper;
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
    private final ContentProviderHelper contentProviderHelper;
    private final IGServiceHelper iGServiceHelper;
    private final TestHelper testHelper;

    private final VentilationDao ventilationDao;
    private final LiveData<List<VentilationModel>> ventilations;

    @Inject public Repository( TestHelper testHelper,
        ContentProviderHelper contentProviderHelper,
        IGServiceHelper iGServiceHelper,
        VentilationDao ventilationDao
    ) {
        this.testHelper = testHelper;
        this.contentProviderHelper = contentProviderHelper;
        this.iGServiceHelper = iGServiceHelper;
        this.ventilationDao = ventilationDao;

        ventilations = Transformations.map(ventilationDao.getEntities(), entities -> {
            List<VentilationModel> models = new ArrayList<>();
            for (VentilationEntity entity : entities ) models.add(Mapper.mapToVentilationModel(entity));
            return models;
        });
    }

    public void injectIGSmartService(IGSmartData igSmartData) {
        iGServiceHelper.setIGService(igSmartData, iCallback);
    }


    private final IGServiceHelper.ICallback iCallback = new IGServiceHelper.ICallback() {
        @Override
        public void onUpdateVentilationState(VentilationStateEntity state) {

        }
    };
}
