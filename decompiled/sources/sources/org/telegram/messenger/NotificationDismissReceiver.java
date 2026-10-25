package org.telegram.messenger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/* loaded from: classes.dex */
public class NotificationDismissReceiver extends BroadcastReceiver {
    public NotificationDismissReceiver() {
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context r8, Intent r9) {
        if (r9 == null) goto L25;
        int r7 = r9.getIntExtra("currentAccount", UserConfig.selectedAccount);
        if (UserConfig.isValidAccount(r7) == true) goto L7;
        return;
    L7:
        long r2 = r9.getLongExtra("dialogId", 0);
        int r82 = r9.getIntExtra("messageDate", 0);
        if (r9.hasExtra("story") == false) goto L14;
        if (r9.getBooleanExtra("story", false) == false) goto L14;
        NotificationsController.getInstance(r7).processIgnoreStories();
        return;
    L14:
        if (r9.hasExtra("storyReaction") == false) goto L20;
        if (r9.getBooleanExtra("storyReaction", false) == false) goto L20;
        NotificationsController.getInstance(r7).processIgnoreStoryReactions();
        return;
    L20:
        if (r2 != 0) goto L23;
        FileLog.d("set dismissDate of global to " + r82);
        MessagesController.getNotificationsSettings(r7).edit().putInt("dismissDate", r82).apply();
        return;
    L23:
        FileLog.d("set dismissDate of " + r2 + " to " + r82);
        SharedPreferences.Editor r72 = MessagesController.getNotificationsSettings(r7).edit();
        StringBuilder r92 = new StringBuilder("dismissDate");
        r92.append(r2);
        r72.putInt(r92.toString(), r82).apply();
        return;
    }
}
