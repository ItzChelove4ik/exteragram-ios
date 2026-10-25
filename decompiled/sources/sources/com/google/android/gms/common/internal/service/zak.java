package com.google.android.gms.common.internal.service;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.common.internal.TelemetryData;
import com.google.android.gms.internal.base.zaa;
import com.google.android.gms.internal.base.zac;

/* loaded from: classes4.dex */
public final class zak extends zaa implements IInterface {
    public zak(IBinder r2) {
        super(r2, "com.google.android.gms.common.internal.service.IClientTelemetryService");
    }

    public final void zae(TelemetryData r2) {
        Parcel r0 = zaa();
        zac.zab(r0, r2);
        zad(1, r0);
    }
}
