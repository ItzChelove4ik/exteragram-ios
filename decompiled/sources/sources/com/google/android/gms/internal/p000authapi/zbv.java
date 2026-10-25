package com.google.android.gms.internal.p000authapi;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.GetPhoneNumberHintIntentRequest;
import com.google.android.gms.auth.api.identity.GetSignInIntentRequest;
import com.google.android.gms.common.api.ApiMetadata;
import com.google.android.gms.common.api.internal.IStatusCallback;

/* loaded from: classes4.dex */
public final class zbv extends zba implements IInterface {
    public zbv(IBinder r2) {
        super(r2, "com.google.android.gms.auth.api.identity.internal.ISignInService");
    }

    public final void zbc(zbl r2, BeginSignInRequest r3, ApiMetadata r4) {
        Parcel r0 = zba();
        zbc.zbc(r0, r2);
        zbc.zbb(r0, r3);
        zbc.zbb(r0, r4);
        zbb(1, r0);
    }

    public final void zbd(IStatusCallback r2, String r3, ApiMetadata r4) {
        Parcel r0 = zba();
        zbc.zbc(r0, r2);
        r0.writeString(r3);
        zbc.zbb(r0, r4);
        zbb(2, r0);
    }

    public final void zbe(zbq r2, GetSignInIntentRequest r3, ApiMetadata r4) {
        Parcel r0 = zba();
        zbc.zbc(r0, r2);
        zbc.zbb(r0, r3);
        zbc.zbb(r0, r4);
        zbb(3, r0);
    }

    public final void zbf(zbo r2, GetPhoneNumberHintIntentRequest r3, String r4, ApiMetadata r5) {
        Parcel r0 = zba();
        zbc.zbc(r0, r2);
        zbc.zbb(r0, r3);
        r0.writeString(r4);
        zbc.zbb(r0, r5);
        zbb(4, r0);
    }
}
