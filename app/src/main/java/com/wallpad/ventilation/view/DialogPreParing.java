package com.wallpad.ventilation.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.wallpad.ventilation.R;

public class DialogPreParing extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.KDWallPad_Dark_CotaCharcoal_Dialog);
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_preparing, null);
        builder.setView(view);
        return builder.create();
    }
}
