package org.telegram.messenger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* loaded from: classes5.dex */
public class PopupReplyReceiver extends BroadcastReceiver {
    public PopupReplyReceiver() {
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context r1, Intent r2) {
        if (r2 == null) goto L9;
        ApplicationLoader.postInitApplication();
        int r0 = r2.getIntExtra("currentAccount", UserConfig.selectedAccount);
        if (UserConfig.isValidAccount(r0) == true) goto L7;
        return;
    L7:
        NotificationsController.getInstance(r0).forceShowPopupForReply();
        return;
    }
}
