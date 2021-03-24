package com.wallpad.ventilation.view.ventilation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.wallpad.ventilation.R;
import com.wallpad.ventilation.databinding.FragmentVentilationBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class VentilationFragment extends Fragment {
    private VentilationViewModel viewModel;

    public static VentilationFragment newInstance() {
        VentilationFragment fragment = new VentilationFragment();
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(VentilationViewModel.class);
        FragmentVentilationBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_ventilation, container, false);
        binding.setLifecycleOwner(this);
        binding.setVentilation(viewModel);
        return binding.getRoot();
    }
}
