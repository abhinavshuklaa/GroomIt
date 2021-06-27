package com.avenger.timesaver.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.avenger.timesaver.MainActivity;
import com.avenger.timesaver.R;

public class FeedbackActivity extends AppCompatActivity {
    TextView btn_1, btn_2, btn_3, btn_4, btn_5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback_ui);
        initViews();
    }

    private void initViews() {
        btn_1 = findViewById(R.id.btnBadAmbience);
        btn_2 = findViewById(R.id.btnBadAmbience_1);
        btn_3 = findViewById(R.id.btnBadAmbience_2);
        btn_4 = findViewById(R.id.btnBadAmbience_3);
        btn_5 = findViewById(R.id.btnSubmit_report_pop_Up);

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_1.setBackgroundColor(getResources().getColor(R.color.black));
                btn_1.setTextColor(getResources().getColor(R.color.contentBodyColor));
            }
        });
        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(FeedbackActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });
    }
}