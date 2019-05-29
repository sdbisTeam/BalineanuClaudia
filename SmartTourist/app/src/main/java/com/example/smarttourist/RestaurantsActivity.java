package com.example.smarttourist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RestaurantsActivity extends AppCompatActivity {

    Button btnGoBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        /*
        TextView txtR1 = findViewById(R.id.txtLatR1);
        TextView txtRr1 = findViewById(R.id.txtLongR1);

        TextView txt1 = findViewById(R.id.txtCLat);
        TextView txt2 = findViewById(R.id.txtCLong);



*/

        btnGoBack = (Button) findViewById(R.id.btnBack);
        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //just finish the current activity
                finish();
            }
        });
    }
}
