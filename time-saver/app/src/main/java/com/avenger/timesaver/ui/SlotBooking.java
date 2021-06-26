package com.avenger.timesaver.ui;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.avenger.timesaver.R;
import com.razorpay.Checkout;

import org.json.JSONObject;

public class SlotBooking extends AppCompatActivity implements SlotBooking_a {
    private static final String TAG = SlotBooking.class.getSimpleName();

    CalendarView calender;
    TextView date_view;
    TimePicker picker;
    Button btnGet;
    TextView tvw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_ui);
        Checkout.preload(getApplicationContext());

        initViews();
    }


    private void initViews() {
        calender = (CalendarView)
                findViewById(R.id.calender);
        date_view = (TextView)
                findViewById(R.id.date_view);
        tvw = (TextView) findViewById(R.id.tvSetTime);
        picker = (TimePicker) findViewById(R.id.timePicker1);
        picker.setIs24HourView(true);
        btnGet = (Button) findViewById(R.id.btnSetTime);


        calender
                .setOnDateChangeListener((view, year, month, dayOfMonth) -> {

                    String Date
                            = dayOfMonth + "-"
                            + (month + 1) + "-" + year;

                    date_view.setText("Selected Date: " + Date);
                });

        btnGet.setOnClickListener(v -> {
            int hour, minute;
            String am_pm;
            if (Build.VERSION.SDK_INT >= 23) {
                hour = picker.getHour();
                minute = picker.getMinute();
            } else {
                hour = picker.getCurrentHour();
                minute = picker.getCurrentMinute();
            }
            if (hour > 12) {
                am_pm = "PM";
                hour = hour - 12;
            } else {
                am_pm = "AM";
            }
            tvw.setText("Selected Time: " + hour + ":" + minute + " " + am_pm);
            goToPayments(tvw.getText().toString(), date_view.getText().toString());
        });

    }

    private void goToPayments(String time, String date) {
        /*
          You need to pass current activity in order to let Razorpay create CheckoutActivity
         */
        final Checkout co = new Checkout();
        co.setKeyID("rzp_test_qNhxvmKhRLonbz");
        final Activity activity = this;

        try {
            JSONObject options = new JSONObject();
            options.put("name", "Groom It");
            options.put("description", "Appointment Charges");
            options.put("send_sms_hash", true);
            options.put("allow_rotation", true);
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("currency", "INR");
            options.put("amount", "100");

            JSONObject preFill = new JSONObject();
            preFill.put("email", "test@razorpay.com");
            preFill.put("contact", "9876543210");

            options.put("prefill", preFill);

            co.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }


    @SuppressWarnings("unused")
    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        try {
            Toast.makeText(this, "Payment Successful: " + razorpayPaymentID, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentSuccess", e);
        }
    }

    @SuppressWarnings("unused")
    @Override
    public void onPaymentError(int code, String response) {
        try {
            Toast.makeText(this, "Payment failed: " + code + " " + response, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentError", e);
        }
    }

}