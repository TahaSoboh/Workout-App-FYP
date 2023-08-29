package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class bmiActivity extends AppCompatActivity {

    android.widget.Button mrecalculatebmi;

    TextView mbmidisplay, mbmicategory, mgender;
    Intent intent;

    String mbmi;
    float intbmi;

    String height;
    String weight;
    float intheight, intweight;
    RelativeLayout mbackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi2);
        mrecalculatebmi=findViewById(R.id.recalculatebmi);

        intent = getIntent();

        mbmidisplay = findViewById(R.id.bmidisplay);
        mbmicategory = findViewById(R.id.bmicategory);
        mgender = findViewById(R.id.genderdisplay);
        mbackground = findViewById(R.id.content);


        height = intent.getStringExtra("height");
        weight = intent.getStringExtra("weight");

        intheight = Float.parseFloat(height);
        intweight = Float.parseFloat(weight);

        intheight = intheight/100;
        intbmi = intweight / (intheight*intheight);

        mbmi = Float.toString(intbmi);

        if (intbmi < 16)
        {
            mbmidisplay.setText("Underweight");
        }
        else if (intbmi > 18.5 && intbmi < 25)
        {
            mbmidisplay.setText("Healthy Weight");
        }
        else if (intbmi > 25 && intbmi < 30)
        {
            mbmidisplay.setText("Overweight");
        }
        else if (intbmi > 30)
        {
            mbmidisplay.setText("Obese");
        }

        mgender.setText(intent.getStringExtra("gender"));
        mbmicategory.setText(mbmi);


        mrecalculatebmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (bmiActivity.this, Bmi.class);
                startActivity(intent);
                finish();
            }
        });
    }
}