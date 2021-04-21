package com.wallpad.ventilation.repository.remote;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.wallpad.ventilation.repository.common.Mapper;
import com.wallpad.ventilation.repository.local.entities.VentilationPropertyEntity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ContentProviderHelper {
    public interface ICallback {
        void onUpdateProperties(List<VentilationPropertyEntity> properties);
    }
    public static final String CONTENT_URI = "content://com.wallpad.service.provider.VentilationInfoContentProvider/t_ventilationInfo";

    private static final String KEY_DEVICE_NO = "devide";
    private static final String KEY_DEVICE_TYPE = "deviceType";
    private static final String KEY_DEVICE_TYPE_FOR_SERVER = "deviceTypeForServer";
    private static final String KEY_GROUP_ID = "groupId";
    private static final String KEY_SUB_ID = "subId";
    private static final String KEY_NAME = "name";
    private static final String KEY_AIR_VOLUME = "airVolumnControlStage";
    private static final String KEY_PROTOCOL = "protocol";
    private static final String KEY_CO2SENSOR = "co2Sensor";
    private static final String KEY_ECONOMY = "economyMode";
    private static final String KEY_AUTO = "autoMode";
    private static final String KEY_HEAT_TRANSFER = "heatTransferMode";
    private static final String KEY_SLEEP = "sleepMode";
    private static final String KEY_NORMAL = "normalMode";

    private final Context context;
    private ICallback callback;

    @Inject
    public ContentProviderHelper(Context context) { this.context = context; }

    public void setCallback(ICallback callback) { this.callback = callback; }

    public void requestProperties() {
        List<VentilationPropertyEntity> properties = new ArrayList<>();
        try (Cursor cursor = context.getContentResolver().query(Uri.parse(CONTENT_URI), null, null, null, null)) {
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    if (cursor.moveToFirst()) {
                        while (!cursor.isAfterLast()) {
                            int primaryKey = 0;
                            int groupId = 0;
                            int channelId = 0;
                            int airVolumeRange = 0;
                            boolean co2Sensor = false;
                            boolean saveMode = false;
                            boolean autoMode = false;
                            boolean heatMode = false;
                            boolean sleepMode = false;
                            boolean byPassMode = false;
                            try {
                                groupId = Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_GROUP_ID)));
                                channelId = Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_SUB_ID)));
                                primaryKey = Mapper.getKey(groupId, channelId);
                                airVolumeRange = Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_AIR_VOLUME)));
                                co2Sensor = Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_CO2SENSOR))) == 1;
                                saveMode = Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_ECONOMY))) == 1;
                                autoMode = Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_AUTO))) == 1;
                                heatMode = Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_HEAT_TRANSFER))) == 1;
                                sleepMode = Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_SLEEP))) == 1;
                                byPassMode = Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_NORMAL))) == 1;
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            properties.add(new VentilationPropertyEntity(primaryKey, groupId, channelId,
                                    airVolumeRange, co2Sensor, saveMode, autoMode, heatMode, sleepMode, byPassMode));
                            cursor.moveToNext();
                        }
                    }
                } while (cursor.moveToNext());
            }
        }
        if ( callback != null ) callback.onUpdateProperties(properties);
    }
}
