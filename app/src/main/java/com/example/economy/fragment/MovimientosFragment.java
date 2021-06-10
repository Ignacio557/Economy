package com.example.economy.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.economy.R;


public class MovimientosFragment extends Fragment {


    public MovimientosFragment() {
        // Required empty public constructor
    }

    public static MovimientosFragment newInstance() {
        
        Bundle args = new Bundle();
        
        MovimientosFragment fragment = new MovimientosFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movimientos, container, false);
    }
}