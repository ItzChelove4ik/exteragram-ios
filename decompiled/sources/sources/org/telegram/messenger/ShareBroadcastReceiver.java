package org.telegram.messenger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* loaded from: classes5.dex */
public class ShareBroadcastReceiver extends BroadcastReceiver {
    public ShareBroadcastReceiver() {
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context r2, Intent r3) {
        String r1 = r3.getDataString();
        if (r1 == null) goto L6;
        Intent r32 = new Intent("android.intent.action.SEND");
        r32.setType("text/plain");
        r32.putExtra("android.intent.extra.TEXT", r1);
        Intent r12 = Intent.createChooser(r32, LocaleController.getString(R.string.ShareLink));
        r12.setFlags(268435456);
        r2.startActivity(r12);
        return;
    }
}
