package org.telegram.messenger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public class SmsReceiver extends BroadcastReceiver {
    public static /* synthetic */ void $r8$lambda$r1nMPvepN3Ys19yAu1nEUibd5Jw(String r2) {
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.didReceiveSmsCode, new Object[]{r2});
    }

    public SmsReceiver() {
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context r4, Intent r5) {
        if (r5 == null) goto L32;
        SharedPreferences r3 = ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0);     // Catch: Throwable -> L23
        String r42 = r3.getString("sms_hash", null);     // Catch: Throwable -> L23
        if ("com.google.android.gms.auth.api.phone.SMS_RETRIEVED".equals(r5.getAction()) == true) goto L8;
        String r52 = _UrlKt.FRAGMENT_ENCODE_SET;
    L13:
        if (TextUtils.isEmpty(r52) == true) goto L29;
        Matcher r53 = Pattern.compile("[0-9\\-]+").matcher(r52);     // Catch: Throwable -> L23
        if (r53.find() == false) goto L30;
        String r54 = r53.group(0).replace("-", _UrlKt.FRAGMENT_ENCODE_SET);     // Catch: Throwable -> L23
        if (r54.length() < 3) goto L31;
        if (r42 == null) goto L21;
        r3.edit().putString("sms_hash_code", r42 + "|" + r54).apply();     // Catch: Throwable -> L23
    L21:
        AndroidUtilities.runOnUIThread(new SmsReceiver$$ExternalSyntheticLambda0(r54));     // Catch: Throwable -> L23
        return;
    L31:
        return;
    L30:
        return;
    L29:
        return;
    L8:
        if (AndroidUtilities.isWaitingForSms() == false) goto L28;
        r52 = (String) r5.getExtras().get("com.google.android.gms.auth.api.phone.EXTRA_SMS_MESSAGE");     // Catch: Throwable -> L23
        goto L13
    L28:
        return;
    L23:
        th = move-exception;
        FileLog.e(th);
        return;
    }
}
