package com.example.economy.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.example.economy.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class InformesFragment extends Fragment {

    private View vista;
    private BarChart barChart;
    private BarDataSet barDataSet;

    public InformesFragment() {}

    public static InformesFragment newInstance() {
        Bundle args = new Bundle();
        InformesFragment fragment = new InformesFragment();
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
        vista = inflater.inflate(R.layout.fragment_informes, container, false);
       // CargarEstadisticas();

        return vista;

    }

    public void CargarEstadisticas() {
        barChart = vista.findViewById(R.id.BarChart);

        ArrayList<BarEntry> ingresos = new ArrayList<>();
        //cargar datos de la bd

        barDataSet = new BarDataSet(ingresos, "ingresos");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.WHITE);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Ingresos de todos las carteras");
        barChart.animateY(2000);

    }//CargarEstadisticas()
}