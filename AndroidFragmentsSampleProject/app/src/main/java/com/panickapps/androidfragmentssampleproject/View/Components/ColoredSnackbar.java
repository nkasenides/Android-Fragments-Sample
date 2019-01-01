package com.panickapps.androidfragmentssampleproject.View.Components;

import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

/**
 * Project: buzzchat
 * Created by hfnov on 20-Nov-18 for PaNickApps.
 */
public class ColoredSnackbar {

    public static void show(View v, String message, int length, int color) {
        Snackbar snackbar = Snackbar.make(v, message, length);
        View view = snackbar.getView();
        TextView textView = view.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(color);
        snackbar.show();
    }

    public static void show(View v, String message, int length, int color, String actionText, View.OnClickListener actionClick) {
        Snackbar snackbar = Snackbar.make(v, message, length);
        snackbar.setAction(actionText, actionClick);
        View view = snackbar.getView();
        TextView textView = view.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(color);
        snackbar.show();
    }

}
