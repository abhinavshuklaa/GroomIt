package com.avenger.timesaver.ui;

import android.app.Activity;
import android.content.Intent;
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
import com.avenger.timesaver.models.ShopServicesModel;
import com.avenger.timesaver.retrofit.ApiClient;
import com.avenger.timesaver.retrofit.Network;
import com.razorpay.Checkout;

import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SlotBooking extends AppCompatActivity implements SlotBooking_a {
    private static final String TAG = SlotBooking.class.getSimpleName();

    CalendarView calender;
    TextView date_view;
    TimePicker picker;
    Button btnGet;
    TextView tvw;
    String total = "0";
    ArrayList<ShopServicesModel> selectedServiceList = new ArrayList<>();
    ApiClient apiClient = Network.getInstance().create(ApiClient.class);
    String date;
    String token = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_ui);
        Checkout.preload(getApplicationContext());
        selectedServiceList = StoreDetailsActivity.Companion.getSelectedServiceList();
        token = StoreDetailsActivity.Companion.getShoperToken();
        calculateTotal();
        initViews();
    }

    private void calculateTotal() {
        int tot = 0;
        for (int i = 0; i < selectedServiceList.size(); i++) {
            tot += (int) selectedServiceList.get(i).getPrice();
        }
        total = tot + "";
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
                    date = Date;


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
            Log.d("TAG", "goToPayments: " + (total + "00"));
            options.put("amount", total + "00");

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

            sendNotificationToShop();
            sendNotificationToUser();


            Toast.makeText(this, "Payment Successful: " + razorpayPaymentID, Toast.LENGTH_SHORT).show();
            NearByFragment.Companion.getLastShop();
            Intent intent = new Intent(this, OrderReceiptActivity.class);
            intent.putExtra("date", date_view.getText().toString());
            intent.putExtra("time", tvw.getText().toString());
            intent.putExtra("transactionId", razorpayPaymentID);
            intent.putExtra("amount",total);

            startActivity(new Intent(this, OrderReceiptActivity.class));


        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentSuccess", e);
        }
    }

    private void sendNotificationToUser() {
        String title = "Payment Successful";
        String body = "Your Appointment is Booked " + date;

        hitTarget(token, title, body);

    }

    private void hitTarget(String token, String title, String body) {
        apiClient.sendPushNotification(token, title, body).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + "notificationSent");
                } else {
                    Log.d(TAG, "onResponse: Notification Failed");
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });
    }

    private void sendNotificationToShop() {
        String title = "Payment Successful";
        String body = "Your Appointment is Booked " + date;

        hitTarget(token, title, body);
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