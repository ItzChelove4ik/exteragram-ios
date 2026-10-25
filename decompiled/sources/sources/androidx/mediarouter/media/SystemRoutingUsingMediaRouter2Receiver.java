package androidx.mediarouter.media;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* loaded from: classes4.dex */
final class SystemRoutingUsingMediaRouter2Receiver extends BroadcastReceiver {
    public SystemRoutingUsingMediaRouter2Receiver() {
    }

    public static boolean isDeclared(Context r2) {
        Intent r0 = new Intent(r2, SystemRoutingUsingMediaRouter2Receiver.class);
        r0.setPackage(r2.getPackageName());
        if (r2.getPackageManager().queryBroadcastReceivers(r0, 0).size() <= 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context r1, Intent r2) {
    }
}
