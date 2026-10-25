package com.google.android.gms.common.internal.service;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.internal.base.zaa;
import com.google.android.gms.internal.base.zac;

/* loaded from: classes4.dex */
public final class zaj extends zaa implements IInterface {
    public zaj(IBinder r2) {
        super(r2, "com.google.android.gms.common.internal.service.IClientNotificationTelemetryService");
    }

    public final void zae(com.google.android.gms.common.internal.zab r2) {
        Parcel r0 = zaa();
        zac.zab(r0, r2);
        zad(1, r0);
    }
}
