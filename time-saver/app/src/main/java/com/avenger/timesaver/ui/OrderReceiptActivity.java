package com.avenger.timesaver.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.avenger.timesaver.MainActivity;
import com.avenger.timesaver.R;
import com.avenger.timesaver.models.Shop;

public class OrderReceiptActivity extends AppCompatActivity {
    TextView tvAppointmentDateFixed, tvAppointmentTimeFixed, tvAppointmentLocation, tvAppointmentCall, tvTransactionIDAppointment, tvAppointmentAmount;
    Shop shop = NearByFragment.Companion.getLastShop();
    Button btn;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_receipt);
        initViews();
//        getDataFromIntent();
    }

    private void getDataFromIntent() {
        Intent intent=getIntent();
//        tvAppointmentAmount.setText(intent.getStringExtra("amount"));
//        tvAppointmentTimeFixed.setText(getIntent().getStringExtra("time"));
//        tvTransactionIDAppointment.setText(getIntent().getStringExtra("transactionId"));
//        tvAppointmentDateFixed.setText(getIntent().getStringExtra("date"));
//        tvAppointmentLocation.setText(shop.getAddressLine());
//        tvAppointmentCall.setText(shop.getContact_no1());


    }

    private void initViews() {
        tvAppointmentDateFixed = findViewById(R.id.tvAppointmentDateFixed);
        tvAppointmentTimeFixed = findViewById(R.id.tvAppointmentTimeFixed);
        tvAppointmentLocation = findViewById(R.id.tvAppointmentLocation);
        tvAppointmentCall = findViewById(R.id.tvAppointmentCall);
        tvTransactionIDAppointment = findViewById(R.id.tvTransactionIDAppointment);
        tvAppointmentAmount = findViewById(R.id.tvAppointmentAmount);
        btn=findViewById(R.id.btnGooo);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(OrderReceiptActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}