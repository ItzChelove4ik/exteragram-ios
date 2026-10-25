package com.google.android.gms.auth.api.signin.internal;

import android.os.Parcel;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.Status;

/* loaded from: classes4.dex */
public abstract class zbq extends com.google.android.gms.internal.p000authapi.zbb implements zbr {
    public zbq() {
        super("com.google.android.gms.auth.api.signin.internal.ISignInCallbacks");
    }

    @Override // com.google.android.gms.internal.p000authapi.zbb
    public final boolean zba(int r1, Parcel r2, Parcel r3, int r4) {
        switch(r1) {
            case 101: goto L7;
            case 102: goto L6;
            case 103: goto L5;
            default: goto L3;
        };
    L3:
        return false;
    L5:
        Status r12 = (Status) com.google.android.gms.internal.p000authapi.zbc.zba(r2, Status.CREATOR);
        com.google.android.gms.internal.p000authapi.zbc.zbd(r2);
        zbd(r12);
    L8:
        r3.writeNoException();
        return true;
    L6:
        Status r13 = (Status) com.google.android.gms.internal.p000authapi.zbc.zba(r2, Status.CREATOR);
        com.google.android.gms.internal.p000authapi.zbc.zbd(r2);
        zbc(r13);
        goto L8
    L7:
        GoogleSignInAccount r14 = (GoogleSignInAccount) com.google.android.gms.internal.p000authapi.zbc.zba(r2, GoogleSignInAccount.CREATOR);
        Status r42 = (Status) com.google.android.gms.internal.p000authapi.zbc.zba(r2, Status.CREATOR);
        com.google.android.gms.internal.p000authapi.zbc.zbd(r2);
        zbb(r14, r42);
        goto L8
    }
}
