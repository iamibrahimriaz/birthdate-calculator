package com.example.birthdatecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private EditText etBirthDate;
    private Button btnCalculateAge;
    private TextView tvAgeResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etBirthDate = findViewById(R.id.et_birthdate);
        btnCalculateAge = findViewById(R.id.btn_calculate_age);
        tvAgeResult = findViewById(R.id.tv_age_result);

        btnCalculateAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String birthDateString = etBirthDate.getText().toString();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date birthDate = null;
                try {
                    birthDate = sdf.parse(birthDateString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Calendar birthCalendar = Calendar.getInstance();
                birthCalendar.setTimeInMillis(birthDate.getTime());
                Calendar currentCalendar = Calendar.getInstance();
                int years = currentCalendar.get(Calendar.YEAR) - birthCalendar.get(Calendar.YEAR);
                int months = currentCalendar.get(Calendar.MONTH) - birthCalendar.get(Calendar.MONTH);
                int days = currentCalendar.get(Calendar.DAY_OF_MONTH) - birthCalendar.get(Calendar.DAY_OF_MONTH);
                if (months < 0 || (months == 0 && days < 0)) {
                    years--;
                }
                tvAgeResult.setText("Your age is " + years + " years, " + Math.abs(months) + " months, and " + Math.abs(days) + " days old.");
            }
        });
    }
}
