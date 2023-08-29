package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Bmi extends AppCompatActivity {

    android.widget.Button mcalculatebmi;

    TextView mcurrentheight;
    TextView mcurrentage, mcurrentweight;
    ImageView mdeductweight, maddweight, mdeductage, maddage;
    SeekBar mseekbarforheight;
    RelativeLayout mmale, mfemale;

    int intweight =70;
    int intage =30;
    int currentprogress;

    String mintprogress = "170";
    String typeofuser="0";
    String weight2="70";
    String age2="30";

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        mcalculatebmi = findViewById(R.id.calculatebmi);
        mcurrentage = findViewById(R.id.currentage);
        mcurrentweight = findViewById(R.id.currentweight);
        mcurrentheight = findViewById(R.id.currentheight);
        maddweight = findViewById(R.id.addweight);
        mdeductweight = findViewById(R.id.deductweight);
        maddage = findViewById(R.id.addage);
        mdeductage = findViewById(R.id.deductage);
        mseekbarforheight = findViewById(R.id.seekbarforheight);
        mmale = findViewById(R.id.male);
        mfemale = findViewById(R.id.female);

        mmale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mmale.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.malenfemaleselect));
                mfemale.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.malenfemalenoselect));
                typeofuser = "Male";
            }
        });

        mfemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mfemale.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.malenfemaleselect));
                mmale.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.malenfemalenoselect));
                typeofuser = "Female";
            }
        });

        mseekbarforheight.setMax(300);
        mseekbarforheight.setProgress(170);
        mseekbarforheight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentprogress = progress;
                mintprogress = String.valueOf(currentprogress);
                mcurrentheight.setText(mintprogress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        maddage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intage = intage +1;
                age2 = String.valueOf(intage);
                mcurrentage.setText(age2);
            }
        });

        maddweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intweight = intweight +1;
                weight2 = String.valueOf(intweight);
                mcurrentweight.setText(weight2);
            }
        });

        mdeductweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intweight = intweight -1;
                weight2 = String.valueOf(intweight);
                mcurrentweight.setText(weight2);
            }
        });

        mdeductage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intage = intage -1;
                age2 = String.valueOf(intage);
                mcurrentage.setText(age2);
            }
        });


        mcalculatebmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (typeofuser.equals("0"))
                {
                    Toast.makeText(getApplicationContext(), "Select your gender please.", Toast.LENGTH_SHORT).show();
                }
                else if (mintprogress.equals("0"))
                {
                    Toast.makeText(getApplicationContext(), "Select your height please.", Toast.LENGTH_SHORT).show();
                }
                else if (intage == 0 || intage <0 )
                {
                    Toast.makeText(getApplicationContext(), "Incorrect age.", Toast.LENGTH_SHORT).show();
                }
                else if (intweight == 0 || intweight < 0)
                {
                    Toast.makeText(getApplicationContext(), "Incorrect weight.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent intent = new Intent(Bmi.this, bmiActivity.class);
                    intent.putExtra("gender", typeofuser);
                    intent.putExtra("height", mintprogress);
                    intent.putExtra("weight", weight2);
                    intent.putExtra("age", age2);

                    startActivity(intent);
                }
                }





        });


        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.home);

        HomeActivity homeActivity = new HomeActivity();


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.calculator:
                        startActivity(new Intent(getApplicationContext(), CalculatorActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.calender:
                        startActivity(new Intent(getApplicationContext(), CalenderActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}