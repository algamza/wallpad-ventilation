package com.wallpad.ventilation.di;

import android.content.Context;

import com.wallpad.ventilation.repository.Repository;
import com.wallpad.ventilation.repository.local.dao.VentilationDao;
import com.wallpad.ventilation.repository.remote.ContentProviderHelper;
import com.wallpad.ventilation.repository.remote.IWallpadServiceHelper;
import com.wallpad.ventilation.repository.remote.TestHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;

@InstallIn(ApplicationComponent.class)
@Module
public class RepositoryModule {

    @Provides
    @Singleton
    public ContentProviderHelper provideContentProviderHelper(@ApplicationContext Context context) {
        return new ContentProviderHelper(context);
    }

    @Provides
    @Singleton
    public IWallpadServiceHelper provideAidlHelper(@ApplicationContext Context context) {
        return new IWallpadServiceHelper(context);
    }

    @Provides
    @Singleton
    public Repository provideRepository(
            TestHelper testHelper,
            ContentProviderHelper contentProviderHelper,
            IWallpadServiceHelper iWallpadServiceHelper,
            VentilationDao dao
    ) {
        return new Repository(
                testHelper,
                contentProviderHelper,
                iWallpadServiceHelper,
                dao
        );
    }
}
