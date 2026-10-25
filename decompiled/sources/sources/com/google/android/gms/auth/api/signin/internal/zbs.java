package com.google.android.gms.auth.api.signin.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

/* loaded from: classes4.dex */
public final class zbs extends com.google.android.gms.internal.p000authapi.zba implements IInterface {
    public zbs(IBinder r2) {
        super(r2, "com.google.android.gms.auth.api.signin.internal.ISignInService");
    }

    public final void zbd(zbr r2, GoogleSignInOptions r3) {
        Parcel r0 = zba();
        com.google.android.gms.internal.p000authapi.zbc.zbc(r0, r2);
        com.google.android.gms.internal.p000authapi.zbc.zbb(r0, r3);
        zbb(102, r0);
    }

    public final void zbe(zbr r2, GoogleSignInOptions r3) {
        Parcel r0 = zba();
        com.google.android.gms.internal.p000authapi.zbc.zbc(r0, r2);
        com.google.android.gms.internal.p000authapi.zbc.zbb(r0, r3);
        zbb(103, r0);
    }
}
