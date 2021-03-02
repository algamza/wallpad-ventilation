package com.wallpad.ventilation.di;

import android.content.Context;

import androidx.room.Room;

import com.wallpad.ventilation.repository.local.LocalDatabase;
import com.wallpad.ventilation.repository.local.dao.VentilationDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;

@InstallIn(ApplicationComponent.class)
@Module
public class DatabaseModule {
    @Provides
    @Singleton
    public LocalDatabase provideDatabase(@ApplicationContext Context context) {
        return Room.databaseBuilder(context, LocalDatabase.class, "local.db").build();
    }

    @Provides
    public VentilationDao provideVentilationDao(LocalDatabase database) { return database.ventilationDao(); }
}
