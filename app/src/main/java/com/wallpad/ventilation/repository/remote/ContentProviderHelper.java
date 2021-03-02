package com.wallpad.ventilation.repository.remote;

import android.content.Context;
import android.net.Uri;

import javax.inject.Inject;

public class ContentProviderHelper {
    private static final Uri URI_Boiler = Uri.parse("content://com.gsmart.provider.APIVentilationContentProvider/t_boiler");

    private final Context context;

    @Inject public ContentProviderHelper(Context context) {
        this.context = context;
    }
}
