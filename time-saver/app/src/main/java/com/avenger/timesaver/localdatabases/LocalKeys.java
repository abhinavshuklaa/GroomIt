package com.avenger.timesaver.localdatabases;


import com.avenger.timesaver.R;
import com.avenger.timesaver.models.ShopServicesModel;

import java.util.ArrayList;

public class LocalKeys {

    public static final String KEY_IS_USER_LOGGED_IN = "DOES_USER_LOGGED_IN";
    public static final String KEY_USER_GOOGLE_ID = "GOOGLE_USER_ID";
    public static final String KEY_USER_GENDER = "USER_GENDER";
    public static final String KEY_IS_USER_DETAILS_FILLED = "DOES_USER_DETAILS_FILLED";

    public static final ArrayList<ShopServicesModel> serviceList = new ArrayList<>();

    public static ArrayList<ShopServicesModel> getAllServiceList() {
        loadServiceList();
        return serviceList;
    }

    private static void loadServiceList() {
        serviceList.add(new ShopServicesModel(1, R.drawable.cuttingnormal, "Hair Cut - Normal", 150));
        serviceList.add(new ShopServicesModel(2, R.drawable.cutting, "Hair Cut - Styling", 250));
        serviceList.add(new ShopServicesModel(3, R.drawable.haircolor, "Hair Colouring", 300));
        serviceList.add(new ShopServicesModel(4, R.drawable.hairstright, "Hair Straightening", 1200));
        serviceList.add(new ShopServicesModel(5, R.drawable.shave, "Beard Shaving - Normal", 60));
        serviceList.add(new ShopServicesModel(6, R.drawable.beardstyle, "Beard Trimming - Normal", 60));
        serviceList.add(new ShopServicesModel(7, R.drawable.beardstyle, "Beard Trimming - Styling", 80));
        serviceList.add(new ShopServicesModel(8, R.drawable.shave, "Beard Shaving - Styling", 90));
        serviceList.add(new ShopServicesModel(9, R.drawable.facial, "Facials - Normal", 450));
        serviceList.add(new ShopServicesModel(10, R.drawable.facial, "Facials - Anti-Tan", 850));
        serviceList.add(new ShopServicesModel(11, R.drawable.facial, "Facials - Fruits", 750));
        serviceList.add(new ShopServicesModel(12, R.drawable.facial, "Facials - Bleach", 350));
        serviceList.add(new ShopServicesModel(13, R.drawable.neckmassage, "Neck - Bleach", 200));
        serviceList.add(new ShopServicesModel(14, R.drawable.fullbodybleach, "Full Body - Bleach", 1000));
        serviceList.add(new ShopServicesModel(15, R.drawable.arm, "Full Arms - Bleach", 250));
        serviceList.add(new ShopServicesModel(16, R.drawable.waxing, "Face - Wax", 350));
        serviceList.add(new ShopServicesModel(17, R.drawable.waxing, "Arms - Wax", 250));
        serviceList.add(new ShopServicesModel(18, R.drawable.headmassage, "Head Massage", 350));
        serviceList.add(new ShopServicesModel(19, R.drawable.headmassage, "Head Massage - Relaxing", 500));
        serviceList.add(new ShopServicesModel(20, R.drawable.fullbodymassage, "Full Body Massage", 850));
    }

}
