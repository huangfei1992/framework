package com.example.skn.framework.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;

import com.example.skn.framework.R;

/**
 * Created by skn on 2017/7/6.
 */

public class DialogUtil {

    private static Dialog dialog;

    private static void getDefault(Context context) {
        if (dialog == null)
            dialog = new Dialog(context, R.style.current_dialog);


    }

    public static void loading(Context context) {
        getDefault(context);
        dialog.setContentView(R.layout.layout_loading);
        dialog.setCancelable(false);
        dialog.show();
    }

    public static void cancel() {
        if (dialog != null)
            dialog.dismiss();
    }



}
