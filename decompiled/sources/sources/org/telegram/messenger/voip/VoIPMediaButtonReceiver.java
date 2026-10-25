package org.telegram.messenger.voip;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;

/* loaded from: classes6.dex */
public class VoIPMediaButtonReceiver extends BroadcastReceiver {
    public VoIPMediaButtonReceiver() {
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context r1, Intent r2) {
        if ("android.intent.action.MEDIA_BUTTON".equals(r2.getAction()) == true) goto L5;
        return;
    L5:
        if (VoIPService.getSharedInstance() == null) goto L10;
        KeyEvent r0 = (KeyEvent) r2.getParcelableExtra("android.intent.extra.KEY_EVENT");
        VoIPService.getSharedInstance().onMediaButtonEvent(r0);
        return;
    }
}
