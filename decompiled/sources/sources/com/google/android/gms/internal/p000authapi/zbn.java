package com.google.android.gms.internal.p000authapi;

import android.app.PendingIntent;
import android.os.Parcel;
import com.google.android.gms.common.api.Status;

/* loaded from: classes4.dex */
public abstract class zbn extends zbb implements zbo {
    public zbn() {
        super("com.google.android.gms.auth.api.identity.internal.IGetPhoneNumberHintIntentCallback");
    }

    @Override // com.google.android.gms.internal.p000authapi.zbb
    public final boolean zba(int r1, Parcel r2, Parcel r3, int r4) {
        if (r1 != 1) goto L6;
        Status r12 = (Status) zbc.zba(r2, Status.CREATOR);
        PendingIntent r42 = (PendingIntent) zbc.zba(r2, PendingIntent.CREATOR);
        zbc.zbd(r2);
        zbb(r12, r42);
        return true;
    L6:
        return false;
    }
}
