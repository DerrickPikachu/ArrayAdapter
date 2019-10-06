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
                if (drinkAdapter.getCount() == 3) {
                    drinkAdapter.remove("溫");
                }
            }
            else if (drinkAdapter.getCount() == 2){
                drinkAdapter.add("溫");
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
            String output = drinkList[pos] + "," + drinkAdapter.getItem(temperatureSpinner.getSelectedItemPosition());

            result.setText(output);
        }
    }

    MainListener listener = new MainListener();
    TextView result;
    Button orderBtn;
    Spinner drinkSpinner, temperatureSpinner;
    ArrayAdapter<String> drinkAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] temList = {"冰", "去冰", "溫"};

        //get component
        result = findViewById(R.id.result);
        orderBtn = findViewById(R.id.orderBtn);
        drinkSpinner = findViewById(R.id.drinkSpinner);
        temperatureSpinner = findViewById(R.id.temperature);

        //set listener
        orderBtn.setOnClickListener(listener);
        drinkSpinner.setOnItemSelectedListener(listener);

        drinkAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);

        for (String s:temList)
            drinkAdapter.add(s);

        drinkAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        temperatureSpinner.setAdapter(drinkAdapter);
    }

}
