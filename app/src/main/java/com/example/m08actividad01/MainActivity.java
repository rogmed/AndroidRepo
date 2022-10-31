package com.example.m08actividad01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Unit> units;
    Double value;
    Unit unitFrom;
    Unit unitTo;
    Double rate;
    Double result;

    EditText tbValue;
    TextView lbResult;
    ArrayAdapter<Unit> adapter;
    Spinner spUnitFrom;
    Spinner spUnitTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversor);

        units = unitListFactory();

        tbValue = findViewById(R.id.textValue);
        tbValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                calculateAndShowResult();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        adapter = new ArrayAdapter<Unit>(
                this, android.R.layout.simple_spinner_dropdown_item, units);

        spUnitFrom = (Spinner) findViewById(R.id.spUnitFrom);
        spUnitFrom.setAdapter(adapter);
        spUnitFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                unitFrom = (Unit) spUnitFrom.getSelectedItem();
                setConversionRate();
                calculateAndShowResult();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spUnitTo = (Spinner) findViewById(R.id.spUnitTo);
        spUnitTo.setAdapter(adapter);
        spUnitTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                unitTo = (Unit) spUnitTo.getSelectedItem();
                setConversionRate();
                calculateAndShowResult();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        unitFrom = (Unit) spUnitFrom.getSelectedItem();
        unitTo = (Unit) spUnitTo.getSelectedItem();

        lbResult = findViewById(R.id.textResult);
    }

    private void calculateAndShowResult() {
        if (!valueIsEmpty() && unitsAreSelected()) {
            value = Double.parseDouble(tbValue.getText().toString());
            result = rate * value;
            String formattedResult = DecimalFormat(result);
            lbResult.setText(formattedResult);
        } else {
            value = 0.0;
            lbResult.setText(" -- ");
        }
    }

    private boolean valueIsEmpty() {
        return tbValue.getText().toString().equals("");
    }

    private boolean unitsAreSelected() {
        return unitFrom.squaredMeterEquivalence != 0 && unitTo.squaredMeterEquivalence != 0;
    }

    private void setConversionRate() {
        if (unitsAreSelected()) {
            rate = (unitFrom.squaredMeterEquivalence / unitTo.squaredMeterEquivalence);
        }
    }

    private String DecimalFormat(double number) {
        DecimalFormat df = new DecimalFormat("0.00");

        return df.format(number);
    }

    private ArrayList<Unit> unitListFactory() {
        ArrayList<Unit> units = new ArrayList<Unit>();
        units.add(new Unit("Seleccione unidad", 0));
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
}
