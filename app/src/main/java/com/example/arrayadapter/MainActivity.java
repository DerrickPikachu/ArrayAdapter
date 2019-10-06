package com.example.arrayadapter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private class MainListener implements AdapterView.OnItemSelectedListener, View.OnClickListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            if (i == 3) {
                temperatureSpinner.setAdapter(drinkType2);
            }
            else {
                temperatureSpinner.setAdapter(drinkType1);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            //no use
        }

        @Override
        public void onClick(View view) {
            int pos = drinkSpinner.getSelectedItemPosition();
            String[] drinkList = getResources().getStringArray(R.array.drink_name);
            String output = drinkList[pos] + ",";

            if (pos == 3) {
                output = output + drinkType2.getItem(temperatureSpinner.getSelectedItemPosition());
            }
            else {
                output = output +drinkType1.getItem(temperatureSpinner.getSelectedItemPosition());
            }

            result.setText(output);
        }
    }

    MainListener listener = new MainListener();
    TextView result;
    Button orderBtn;
    Spinner drinkSpinner, temperatureSpinner;
    ArrayAdapter drinkType1;
    ArrayAdapter drinkType2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] temType1 = {"冰", "去冰", "溫"};
        String[] temType2 = {"冰", "去冰"};

        //get component
        result = findViewById(R.id.result);
        orderBtn = findViewById(R.id.orderBtn);
        drinkSpinner = findViewById(R.id.drinkSpinner);
        temperatureSpinner = findViewById(R.id.temperature);

        //set listener
        orderBtn.setOnClickListener(listener);
        drinkSpinner.setOnItemSelectedListener(listener);

        drinkType1 = setUpArrayAdapter(temType1);
        drinkType2 = setUpArrayAdapter(temType2);

        temperatureSpinner.setAdapter(drinkType1);
    }

    private ArrayAdapter setUpArrayAdapter(String[] list) {
        ArrayAdapter newAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, list);
        newAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return newAdapter;
    }

}
