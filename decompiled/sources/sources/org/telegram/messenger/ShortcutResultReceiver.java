package org.telegram.messenger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import org.telegram.messenger.Utilities;

/* loaded from: classes5.dex */
public class ShortcutResultReceiver extends BroadcastReceiver {
    /* renamed from: $r8$lambda$DPBmsSp-XosyXtfGN7A9HAe_acI, reason: not valid java name */
    public static /* synthetic */ void m4971$r8$lambda$DPBmsSpXosyXtfGN7A9HAe_acI(Utilities.Callback r1) {
        r1.run(Boolean.TRUE);
    }

    public ShortcutResultReceiver() {
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context r1, Intent r2) {
        int r0 = r2.getIntExtra("account", UserConfig.selectedAccount);
        String r12 = r2.getStringExtra("req_id");
        Utilities.Callback<Boolean> r02 = MediaDataController.getInstance(r0).shortcutCallbacks.remove(r12);
        if (r02 == null) goto L6;
        AndroidUtilities.runOnUIThread(new ShortcutResultReceiver$$ExternalSyntheticLambda0(r02));
        return;
    }
}
