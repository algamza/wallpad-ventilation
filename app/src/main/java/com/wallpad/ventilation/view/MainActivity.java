package com.wallpad.ventilation.view;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.wallpad.ventilation.R;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) { finish(); }

    public void onClickPreParing(View view) {
        DialogPreParing dialog = new DialogPreParing();
        dialog.show(getSupportFragmentManager(), dialog.getTag());
    }
}
