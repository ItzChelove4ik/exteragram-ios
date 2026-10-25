package org.telegram.messenger;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.text.TextUtils;

@TargetApi(28)
/* loaded from: classes5.dex */
public class NotificationsDisabledReceiver extends BroadcastReceiver {
    public NotificationsDisabledReceiver() {
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context r20, Intent r21) {
        if ("android.app.action.NOTIFICATION_CHANNEL_BLOCK_STATE_CHANGED".equals(r21.getAction()) == false) goto L94;
        String r1 = r21.getStringExtra("android.app.extra.NOTIFICATION_CHANNEL_ID");
        int r3 = 0;
        boolean r0 = r21.getBooleanExtra("android.app.extra.BLOCKED_STATE", false);
        if (TextUtils.isEmpty(r1) == false) goto L8;
        return;
    L8:
        if (r1.contains("_ia_") == true) goto L95;
        String[] r2 = r1.split("_");
        if (r2.length < 3) goto L96;
        ApplicationLoader.postInitApplication();
        int r4 = Utilities.parseInt(r2[0]);
        if (r4 >= 0) goto L16;
        return;
    L16:
        if (r4 < 16) goto L19;
        return;
    L19:
        if (BuildVars.LOGS_ENABLED == false) goto L22;
        FileLog.d("received disabled notification channel event for " + r1 + " state = " + r0);
    L22:
        if ((SystemClock.elapsedRealtime() - AccountInstance.getInstance(r4).getNotificationsController().lastNotificationChannelCreateTime) <= 1000) goto L24;
        SharedPreferences r6 = AccountInstance.getInstance(r4).getNotificationsSettings();
        int r9 = Integer.MAX_VALUE;
        if (r2[1].startsWith("channel") == false) goto L40;
        if (r1.equals(r6.getString("channels", null)) == true) goto L33;
        return;
    L33:
        if (BuildVars.LOGS_ENABLED == false) goto L35;
        FileLog.d("apply channel{channel} " + r1 + " state");
    L35:
        SharedPreferences.Editor r12 = r6.edit();
        String r22 = NotificationsController.getGlobalNotificationsKey(2);
        if (r0 == false) goto L38;
        r3 = Integer.MAX_VALUE;
    L38:
        r12.putInt(r22, r3).apply();
        AccountInstance.getInstance(r4).getNotificationsController().updateServerNotificationsSettings(2);
    L89:
        AccountInstance.getInstance(r4).getConnectionsManager().resumeNetworkMaybe();
        return;
    L40:
        if (r2[1].startsWith("groups") == false) goto L53;
        if (r1.equals(r6.getString("groups", null)) == true) goto L45;
        return;
    L45:
        if (BuildVars.LOGS_ENABLED == false) goto L47;
        FileLog.d("apply channel{groups} " + r1 + " state");
    L47:
        SharedPreferences.Editor r13 = r6.edit();
        String r23 = NotificationsController.getGlobalNotificationsKey(0);
        if (r0 == true) goto L51;
        r9 = 0;
    L51:
        r13.putInt(r23, r9).apply();
        AccountInstance.getInstance(r4).getNotificationsController().updateServerNotificationsSettings(0);
        goto L89
    L53:
        if (r2[1].startsWith("private") == false) goto L65;
        if (r1.equals(r6.getString("private", null)) == true) goto L58;
        return;
    L58:
        if (BuildVars.LOGS_ENABLED == false) goto L60;
        FileLog.d("apply channel{private} " + r1 + " state");
    L60:
        SharedPreferences.Editor r14 = r6.edit();
        String r24 = NotificationsController.getGlobalNotificationsKey(1);
        if (r0 == false) goto L63;
        r3 = Integer.MAX_VALUE;
    L63:
        r14.putInt(r24, r3).apply();
        AccountInstance.getInstance(r4).getNotificationsController().updateServerNotificationsSettings(1);
        goto L89
    L65:
        if (r2[1].startsWith("stories") == true) goto L67;
        long r142 = Utilities.parseLong(r2[1]).longValue();
        if (r142 == 0) goto L102;
        String r25 = NotificationsController.getSharedPrefKey(r142, 0);
        if (r1.equals(r6.getString("org.telegram.key" + r142, null)) == true) goto L80;
        return;
    L80:
        if (BuildVars.LOGS_ENABLED == false) goto L82;
        FileLog.d("apply channel{else} " + r1 + " state");
    L82:
        SharedPreferences.Editor r15 = r6.edit();
        String r5 = NotificationsSettingsFacade.PROPERTY_NOTIFY + r25;
        if (r0 == false) goto L85;
        r3 = 2;
    L85:
        r15.putInt(r5, r3);
        if (r0 == true) goto L88;
        r15.remove(NotificationsSettingsFacade.PROPERTY_NOTIFY_UNTIL + r25);
    L88:
        r15.apply();
        AccountInstance.getInstance(r4).getNotificationsController().updateServerNotificationsSettings(r142, 0, true);
        goto L89
    L102:
        return;
    L67:
        if (r1.equals(r6.getString("stories", null)) == true) goto L70;
        return;
    L70:
        if (BuildVars.LOGS_ENABLED == false) goto L72;
        FileLog.d("apply channel{stories} " + r1 + " state");
    L72:
        r6.edit().putBoolean(NotificationsController.getGlobalNotificationsKey(3), !r0).commit();
        AccountInstance.getInstance(r4).getNotificationsController().updateServerNotificationsSettings(1);
        goto L89
    L24:
        if (BuildVars.LOGS_ENABLED == false) goto L93;
        FileLog.d("received disable notification event right after creating notification channel, ignoring");
        return;
    L93:
        return;
    L96:
        return;
    L95:
        return;
    }
}
