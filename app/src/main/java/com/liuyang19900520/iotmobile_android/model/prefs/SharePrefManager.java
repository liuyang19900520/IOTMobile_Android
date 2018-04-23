package com.liuyang19900520.iotmobile_android.model.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

/**
 * SharePreferences管理类
 *
 * @author liuyang
 * @date 2017/9/21
 */

public class SharePrefManager {

    private static final String SHAREDPREFERENCES_NAME = "iot";

    private SharedPreferences SPfres;

    @Inject
    public SharePrefManager(Context context) {
        SPfres = context.getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
    }


    public void setToken(String token) {
        SPfres.edit().putString("token", token).commit();
    }

    public String getToken() {
        return SPfres.getString("token", "");
    }

    public void setRefreshToken(String refreshToken) {
        SPfres.edit().putString("refreshToken", refreshToken).commit();
    }

    public String getRefreshToken() {
        return SPfres.getString("refreshToken", "");
    }


    public void setClientKey(String username) {
        SPfres.edit().putString("clientKey", username).commit();
    }

    public String getClientKey() {
        return SPfres.getString("clientKey", "");
    }


    /**
     * 设置省流量模式
     */
    public void setProvincialTrafficPatterns(boolean event) {
        SPfres.edit().putBoolean("provincial_traffic_patterns", event).commit();
    }

    public boolean getProvincialTrafficPattern() {
        return SPfres.getBoolean("provincial_traffic_patterns", false);
    }

    public void setLocalProvincialTrafficPatterns(boolean event) {
        SPfres.edit().putBoolean("local_provincial_traffic_patterns", event).commit();
    }

    public boolean getLocalProvincialTrafficPatterns() {
        return SPfres.getBoolean("local_provincial_traffic_patterns", false);
    }

    /**
     * 设置夜间模式
     *
     * @param event
     */
    public void setNightMode(boolean event) {
        SPfres.edit().putBoolean("nightmode", event).commit();
    }

    public boolean getNightMode() {
        return SPfres.getBoolean("nightmode", false);
    }

    public void setLocalMode(int localMode) {
        SPfres.edit().putInt("localMode", localMode).commit();
    }

    public int getLocalMode() {
        return SPfres.getInt("localMode", 0);
    }
}
