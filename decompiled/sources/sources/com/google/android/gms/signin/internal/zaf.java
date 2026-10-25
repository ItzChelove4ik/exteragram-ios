package com.google.android.gms.signin.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.common.internal.IAccountAccessor;

/* loaded from: classes5.dex */
public final class zaf extends com.google.android.gms.internal.base.zaa implements IInterface {
    public zaf(IBinder r2) {
        super(r2, "com.google.android.gms.signin.internal.ISignInService");
    }

    public final void zae(int r2) {
        Parcel r0 = zaa();
        r0.writeInt(r2);
        zac(7, r0);
    }

    public final void zaf(IAccountAccessor r2, int r3, boolean r4) {
        Parcel r0 = zaa();
        com.google.android.gms.internal.base.zac.zac(r0, r2);
        r0.writeInt(r3);
        r0.writeInt(r4 ? 1 : 0);
        zac(9, r0);
    }

    public final void zag(zai r2, zae r3) {
        Parcel r0 = zaa();
        com.google.android.gms.internal.base.zac.zab(r0, r2);
        com.google.android.gms.internal.base.zac.zac(r0, r3);
        zac(12, r0);
    }
}
