package com.google.android.gms.common.moduleinstall.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes4.dex */
public final class zaf extends com.google.android.gms.internal.base.zaa implements IInterface {
    public zaf(IBinder r2) {
        super(r2, "com.google.android.gms.common.moduleinstall.internal.IModuleInstallService");
    }

    public final void zae(zae r2, ApiFeatureRequest r3) {
        Parcel r0 = zaa();
        com.google.android.gms.internal.base.zac.zac(r0, r2);
        com.google.android.gms.internal.base.zac.zab(r0, r3);
        zac(1, r0);
    }

    public final void zaf(zae r2, ApiFeatureRequest r3, zah r4) {
        Parcel r0 = zaa();
        com.google.android.gms.internal.base.zac.zac(r0, r2);
        com.google.android.gms.internal.base.zac.zab(r0, r3);
        com.google.android.gms.internal.base.zac.zac(r0, r4);
        zac(2, r0);
    }
}
