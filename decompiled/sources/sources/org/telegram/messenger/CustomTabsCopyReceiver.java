package org.telegram.messenger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/* loaded from: classes5.dex */
public class CustomTabsCopyReceiver extends BroadcastReceiver {
    public CustomTabsCopyReceiver() {
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context r1, Intent r2) {
        String r0 = r2.getDataString();
        if (r0 == null) goto L6;
        AndroidUtilities.addToClipboard(r0);
        Toast.makeText(r1, LocaleController.getString(R.string.LinkCopied), 0).show();
        return;
    }
}
