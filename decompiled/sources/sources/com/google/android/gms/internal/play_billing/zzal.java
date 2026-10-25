package com.google.android.gms.internal.play_billing;

import android.os.IBinder;
import android.os.IInterface;

/* loaded from: classes5.dex */
public abstract class zzal extends zzaq implements zzam {
    public static zzam zzs(IBinder r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        IInterface r0 = r2.queryLocalInterface("com.android.vending.billing.IInAppBillingService");
        if ((r0 instanceof zzam) == false) goto L10;
        return (zzam) r0;
    L10:
        return new zzak(r2);
    }
}
