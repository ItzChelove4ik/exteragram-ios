package com.google.android.gms.internal.p000authapi;

import android.os.Parcel;
import com.google.android.gms.auth.api.identity.BeginSignInResult;
import com.google.android.gms.common.api.Status;

/* loaded from: classes4.dex */
public abstract class zbk extends zbb implements zbl {
    public zbk() {
        super("com.google.android.gms.auth.api.identity.internal.IBeginSignInCallback");
    }

    @Override // com.google.android.gms.internal.p000authapi.zbb
    public final boolean zba(int r1, Parcel r2, Parcel r3, int r4) {
        if (r1 != 1) goto L6;
        Status r12 = (Status) zbc.zba(r2, Status.CREATOR);
        BeginSignInResult r42 = (BeginSignInResult) zbc.zba(r2, BeginSignInResult.CREATOR);
        zbc.zbd(r2);
        zbb(r12, r42);
        return true;
    L6:
        return false;
    }
}
