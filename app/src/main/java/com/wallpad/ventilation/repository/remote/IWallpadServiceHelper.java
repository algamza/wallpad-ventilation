package com.wallpad.ventilation.repository.remote;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.RemoteException;

import com.wallpad.IWallpadData;
import com.wallpad.ventilation.repository.common.SerialParser;
import com.wallpad.ventilation.repository.local.entities.VentilationStateEntity;

import java.util.List;

public class IWallpadServiceHelper {
    public interface ICallback {
        void onUpdateStates(List<VentilationStateEntity> states);
    }
    
    private Context context;
    private ICallback callback;
    private IWallpadData iWallpadData;

    public IWallpadServiceHelper(Context context) {
        this.context = context;
    }

    public void setIGService(IWallpadData iWallpadData, ICallback callback) {
        if ( iWallpadData == null ) {
            this.iWallpadData = null;
            context.unregisterReceiver(serialReceiver);
        } else {
            this.iWallpadData = iWallpadData;
            this.callback = callback;
            context.registerReceiver(serialReceiver, new IntentFilter(SerialParser.INTENT_ACTION_DEVICE_STATUS));
        }
    }

    public void requestStates(int group) {
        if ( iWallpadData == null ) return;
        try {
            iWallpadData.requestDeviceInfoSend(SerialParser.CMD_DEVICE_INQUIRY, SerialParser.getCmdStatus(group));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void requestPowerOn(int group, int channel, boolean on) {
        if ( iWallpadData == null ) return;
        try {
            iWallpadData.requestDeviceInfoSend(SerialParser.CMD_DEVICE_CONTROL, SerialParser.getCmdControlPower(group, channel, on));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void requestVolume(int group, int channel, int volume) {
        if ( iWallpadData == null ) return;
        try {
            iWallpadData.requestDeviceInfoSend(SerialParser.CMD_DEVICE_CONTROL, SerialParser.getCmdControlVolume(group, channel, volume));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void requestMode(int group, int channel, int mode) {
        if ( iWallpadData == null ) return;
        try {
            iWallpadData.requestDeviceInfoSend(SerialParser.CMD_DEVICE_CONTROL, SerialParser.getCmdControlMode(group, channel, mode));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private final BroadcastReceiver serialReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if ( !intent.getAction().equals(SerialParser.INTENT_ACTION_DEVICE_STATUS) ) return;
            String data = intent.getStringExtra(SerialParser.INTENT_ACTION_DEVICE_STATUS_DATA_KEY);
            if ( data == null ) return;
            if ( SerialParser.isVentilationState(data) ) callback.onUpdateStates(SerialParser.parseState(data));
        }
    };
}
