package edu.vandy.cs251.imagegrabber;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * This is the class that contains most used debugging methods
 * Log and Toast
 *
 */
public class DebugUtils {
    public static void printLog(String tag, String message){
        Log.e(tag, message);
    }

    public static void showToast(Context context, String toastMessage){
        Toast.makeText(context, toastMessage, Toast.LENGTH_LONG).show();
    }
}
