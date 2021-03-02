package com.wallpad.ventilation.repository.remote;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.gsmart.IGSmartData;
import com.wallpad.ventilation.repository.common.SerialCmd;
import com.wallpad.ventilation.repository.local.entities.VentilationStateEntity;

import java.util.List;

public class IGServiceHelper {
    public interface ICallback {
        void onUpdateVentilationState(VentilationStateEntity state);
    }

    private ICallback callback;
    private IGSmartData igSmartData;

    public IGServiceHelper(Context context) {
        context.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if ( !intent.getAction().equals(SerialCmd.ACTION_SERIAL_RECEIVE) ) return;
                // updateData(intent.getStringExtra(SerialCmd.EXTRA_SERIAL));
            }
        }, new IntentFilter(SerialCmd.ACTION_SERIAL_RECEIVE));
    }

    public void setIGService(IGSmartData igSmartData, ICallback callback) {
        this.igSmartData = igSmartData;
        this.callback = callback;
    }

    private void updateBoiler(String[] hex) { }
}
