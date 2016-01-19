package com.dp.stopme.helper;

import android.content.Context;

import com.snappydb.DB;
import com.snappydb.DBFactory;
import com.snappydb.SnappydbException;

/**
 * Created by pradeepd on 09-01-2016.
 */
public class DataBaseHelper {

    static final String TABLE_NAME_POINTS = "tblPoints";
    static final String TABLE_NAME_ATTEMPTS = "tblAttempts";
    static final String KEY_POINTS = "points";
    static final String KEY_ATTEMPTS = "attempts";

    private static void insertRow(Context context, String dbName, String key, String value) {
        try {
            DB snappydb = DBFactory.open(context, dbName);
            snappydb.put(key, value);
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
    }

    private static void insertRow(Context context, String dbName, String key, int value) {
        try {
            DB snappydb = DBFactory.open(context, dbName);
            snappydb.put(key, value);
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
    }

    private static void insertRow(Context context, String dbName, String key, double value) {
        try {
            DB snappydb = DBFactory.open(context, dbName);
            snappydb.put(key, value);
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
    }

    private static String retriveValue(Context context, String tableName, String coloumnName) {
        String value = "";
        try {
            DB snappydb = DBFactory.open(context, tableName);
            value = snappydb.get(coloumnName) + "";
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
        return value;
    }

    public static void addPoints(Context context, double points) {
        insertRow(context, TABLE_NAME_POINTS, KEY_POINTS, points);
    }

    public static void addAttempts(Context context, double attempts) {
        insertRow(context, TABLE_NAME_ATTEMPTS, KEY_ATTEMPTS, attempts);
    }

    public static String retrivePoints(Context context) {
        return retriveValue(context, TABLE_NAME_POINTS, KEY_POINTS);
    }

    public static String retriveAttempts(Context context) {
        return retriveValue(context, TABLE_NAME_ATTEMPTS, KEY_ATTEMPTS);
    }
}
