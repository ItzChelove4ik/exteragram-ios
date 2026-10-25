package org.telegram.messenger;

import android.content.Intent;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.WearAuthSheet;

/* loaded from: classes5.dex */
public class WearAuthListenerService extends WearableListenerService {
    public static final String PATH_CANCEL = "/tg-wear-auth/cancel";
    public static final String PATH_OFFER = "/tg-wear-auth/offer";

    /* renamed from: $r8$lambda$ZoaD8uzVx54e8-TEk1oIZnY6L8g, reason: not valid java name */
    public static /* synthetic */ void m5052$r8$lambda$ZoaD8uzVx54e8TEk1oIZnY6L8g(String r1, String r2, byte[] r3) {
        r1.getClass();
        if (r1.equals(PATH_OFFER) == false) goto L5;
        FileLog.d("wear-auth: offer from " + r2 + " (" + r3.length + " bytes)");
        WearAuthSheet.onOfferReceived(r3, r2);
        return;
    L5:
        if (r1.equals(PATH_CANCEL) == true) goto L8;
        FileLog.d("wear-auth: unexpected path ".concat(r1));
        return;
    L8:
        FileLog.d("wear-auth: cancel from " + r2);
        WearAuthSheet.onCancelReceived();
    }

    public WearAuthListenerService() {
    }

    @Override // com.google.android.gms.wearable.WearableListenerService
    public void onMessageReceived(MessageEvent r5) {
        String r0 = r5.getPath();
        String r1 = r5.getSourceNodeId();
        byte[] r52 = r5.getData();
        if (PATH_OFFER.equals(r0) == true) goto L10;
    L8:
        AndroidUtilities.runOnUIThread(new WearAuthListenerService$$ExternalSyntheticLambda0(r0, r1, r52));
        return;
    L10:
        Intent r2 = new Intent(this, LaunchActivity.class);     // Catch: Exception -> L6
        r2.addFlags(268566528);     // Catch: Exception -> L6
        startActivity(r2);     // Catch: Exception -> L6
    L6:
        e = move-exception;
        FileLog.e("wear-auth: failed to pop LaunchActivity", e);
        goto L8
    }
}
