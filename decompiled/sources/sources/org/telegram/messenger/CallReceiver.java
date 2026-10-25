package org.telegram.messenger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import org.telegram.PhoneFormat.PhoneFormat;

/* loaded from: classes5.dex */
public class CallReceiver extends BroadcastReceiver {
    public CallReceiver() {
    }

    public static void checkLastReceivedCall() {
        String r0 = getLastReceivedCall();
        if (r0 == null) goto L6;
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.didReceiveCall, new Object[]{r0});
        return;
    }

    public static void clearLastCall() {
        SharedConfig.getPreferences().edit().remove("last_call_phone_number").remove("last_call_time").apply();
    }

    public static String getLastReceivedCall() {
        String r0 = SharedConfig.getPreferences().getString("last_call_phone_number", null);
        if (r0 != null) goto L5;
        return null;
    L5:
        long r3 = SharedConfig.getPreferences().getLong("last_call_time", 0);
        if ((System.currentTimeMillis() - r3) >= 54000000) goto L8;
        return r0;
    L8:
        return null;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context r3, Intent r4) {
        if (r4.getAction().equals("android.intent.action.PHONE_STATE") == false) goto L8;
        String r2 = r4.getStringExtra("state");
        if (TelephonyManager.EXTRA_STATE_RINGING.equals(r2) == false) goto L9;
        String r22 = PhoneFormat.stripExceptNumbers(r4.getStringExtra("incoming_number"));
        SharedConfig.getPreferences().edit().putString("last_call_phone_number", r22).putLong("last_call_time", System.currentTimeMillis()).apply();
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.didReceiveCall, new Object[]{r22});
        return;
    L9:
        return;
    }
}
