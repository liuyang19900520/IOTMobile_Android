package com.liuyang19900520.iotmobile_android.util;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

import com.liuyang19900520.iotmobile_android.R;


/**
 *
 * @author liuyang
 * @date 2017/10/13
 */

public class SnackBarUtils {

    public static void show(View view, CharSequence msg) {
        Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG);
        ((TextView) (snackbar.getView().findViewById(R.id.snackbar_text))).setTextColor(Color.WHITE);
        snackbar.show();
    }

    public static void show(View view, int resId, Context context) {
        show(view, context.getResources().getText(resId));
    }
}
