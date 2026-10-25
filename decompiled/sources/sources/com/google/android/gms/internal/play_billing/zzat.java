package com.google.android.gms.internal.play_billing;

import android.os.IBinder;
import android.os.IInterface;

/* loaded from: classes5.dex */
public abstract class zzat extends zzaq implements zzau {
    public static zzau zzc(IBinder r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        IInterface r0 = r2.queryLocalInterface("com.google.android.apps.play.billingtestcompanion.aidl.IBillingOverrideService");
        if ((r0 instanceof zzau) == false) goto L10;
        return (zzau) r0;
    L10:
        return new zzas(r2);
    }
}
