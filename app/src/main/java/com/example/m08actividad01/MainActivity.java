package com.example.m08actividad01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Unit> units;
    Unit unitFrom;
    Unit unitTo;

    EditText tbValue;
    Double value;
    Button btCalculate;
    TextView lbResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversor);

        tbValue = findViewById(R.id.textValue);

        units = unitListFactory();

        ArrayAdapter<Unit> adapter = new ArrayAdapter<Unit>(
                getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, units);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spUnitFrom = (Spinner) findViewById(R.id.spUnitFrom);
        spUnitFrom.setAdapter(adapter);
        spUnitFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                unitFrom = (Unit)spUnitFrom.getSelectedItem();
                lbResult.setText("FROM Seleccionado: " + unitFrom.name + " (" + unitFrom.squaredMeterEquivalence + " m2)");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Spinner spUnitTo = (Spinner) findViewById(R.id.spUnitTo);
        spUnitTo.setAdapter(adapter);
        spUnitTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                unitTo = (Unit)spUnitTo.getSelectedItem();
                lbResult.setText("TO Seleccionado: " + unitTo.name + " (" + unitTo.squaredMeterEquivalence + " m2)");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        lbResult = findViewById(R.id.textResultado);

        btCalculate = (Button) findViewById(R.id.btCalculate);
        btCalculate.setOnClickListener(view -> {
            if (!tbValue.getText().toString().matches("")){
                value = Double.parseDouble(tbValue.getText().toString());
                convert();
            }
        });
    }

    private void convert() {
        if(unitFrom != null && unitTo != null && value != null) {
            double result = (unitFrom.squaredMeterEquivalence / unitTo.squaredMeterEquivalence) * value;
            String formattedResult = DecimalFormat(result);
            lbResult.setText(formattedResult+ " " + unitTo.name);
        }
    }

    private ArrayList<Unit> unitListFactory() {
        ArrayList<Unit> units = new ArrayList<Unit>();
        units.add(new Unit("", 1));
        units.add(new Unit("Metro cuadrado", 1));
        units.add(new Unit("Kilómetro cuadrado", 1.0E6));
        units.add(new Unit("Milla cuadrada", 2.59E6));
        units.add(new Unit("Yarda cuadrada", 0.836127));
        units.add(new Unit("Pie cuadrado", 0.092903));
        units.add(new Unit("Pulgada cuadrada", 0.00064516));
        units.add(new Unit("Hectárea", 10000));
        units.add(new Unit("Acre", 4046.86));

        return units;
    }

    private String DecimalFormat(double number) {
        DecimalFormat df = new DecimalFormat("0.00");

        return df.format(number);
    }
}
