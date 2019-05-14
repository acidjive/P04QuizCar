package com.cynmjcn.p04quizcar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DBHelper myDB;

    EditText etbrand, etLitre;
    Button btnInsert, btnRetrieve;
    TextView tvResults;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new DBHelper(this);

        btnInsert = (Button)findViewById(R.id.btnInsert);
        btnRetrieve = (Button)findViewById(R.id.btnRetrieve);
        etbrand = (EditText)findViewById(R.id.etBrand);
        etLitre = (EditText)findViewById(R.id.etLitre);
        tvResults = (TextView)findViewById(R.id.tvResult);


        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String brand = etbrand.getText().toString();
                String litre = etLitre.getText().toString();

                myDB.insertCar(brand, litre);

                boolean insert = true;
                if (insert == true){
                    Toast.makeText(MainActivity.this, "inserted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "not inserted", Toast.LENGTH_SHORT).show();
                }
                myDB.close();
            }
        });

        btnRetrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> data = myDB.getCarBrand();
                String title = "";
                for (int i = 0; i < data.size(); i++){
                    title += data.get(i) + "\n";
                }

                myDB.close();

                tvResults.setText("Car Information: \n" + title + "\n");


            }
        });

    }
}