package com.augtrans.sampleprofile;

import android.content.Context;
import android.content.SharedPreferences;

public class ShareData {

    private Context mContext;
    private String mPreferenceName = "com.carsystemtrack";

    public ShareData(Context context){
        mContext = context;
    }
    public  void clearAll(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(mPreferenceName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    public void deleteSharedValue(String key){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(mPreferenceName, Context.MODE_PRIVATE);
        sharedPreferences.edit().remove(key).commit();
    }

    public boolean containsKey(String key){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(mPreferenceName, Context.MODE_PRIVATE);
        return sharedPreferences.contains(key);
    }

    public void putString(String key, String value){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(mPreferenceName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
    public void putBoolean(String key, boolean value){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(mPreferenceName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }
    public void putInt(String key, int value){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(mPreferenceName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }
    public void putDouble(String key, double value){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(mPreferenceName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, Double.doubleToRawLongBits(value));
        editor.commit();
    }


    public String getString(String key, String defaultValue){
        String value = "";
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(mPreferenceName, Context.MODE_PRIVATE);
        value = sharedPreferences.getString(key,defaultValue);
        return value;
    }

    public boolean getBoolean(String key, boolean defaultValue){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(mPreferenceName, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, defaultValue);
    }
    public int getInt(String key, int defaultValue){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(mPreferenceName, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, defaultValue);
    }

    public double getDouble(String key, double defaultValue){
        double value;
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(mPreferenceName, Context.MODE_PRIVATE);
        value = Double.longBitsToDouble(sharedPreferences.getLong(key, Double.doubleToLongBits(defaultValue)));
        return value;
    }

}
