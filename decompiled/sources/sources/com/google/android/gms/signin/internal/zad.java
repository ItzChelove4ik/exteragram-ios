package com.google.android.gms.signin.internal;

import android.os.Parcel;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Status;

/* loaded from: classes5.dex */
public abstract class zad extends com.google.android.gms.internal.base.zab implements zae {
    public zad() {
        super("com.google.android.gms.signin.internal.ISignInCallbacks");
    }

    @Override // com.google.android.gms.internal.base.zab
    public final boolean zaa(int r1, Parcel r2, Parcel r3, int r4) {
        switch(r1) {
            case 3: goto L10;
            case 4: goto L9;
            case 5: goto L3;
            case 6: goto L8;
            case 7: goto L7;
            case 8: goto L6;
            case 9: goto L5;
            default: goto L3;
        };
    L3:
        return false;
    L5:
        zag r0 = (zag) com.google.android.gms.internal.base.zac.zaa(r2, zag.CREATOR);
        com.google.android.gms.internal.base.zac.zad(r2);
    L11:
        r3.writeNoException();
        return true;
    L6:
        zak r12 = (zak) com.google.android.gms.internal.base.zac.zaa(r2, zak.CREATOR);
        com.google.android.gms.internal.base.zac.zad(r2);
        zab(r12);
        goto L11
    L7:
        Status r02 = (Status) com.google.android.gms.internal.base.zac.zaa(r2, Status.CREATOR);
        GoogleSignInAccount r03 = (GoogleSignInAccount) com.google.android.gms.internal.base.zac.zaa(r2, GoogleSignInAccount.CREATOR);
        com.google.android.gms.internal.base.zac.zad(r2);
        goto L11
    L8:
        Status r04 = (Status) com.google.android.gms.internal.base.zac.zaa(r2, Status.CREATOR);
        com.google.android.gms.internal.base.zac.zad(r2);
        goto L11
    L9:
        Status r05 = (Status) com.google.android.gms.internal.base.zac.zaa(r2, Status.CREATOR);
        com.google.android.gms.internal.base.zac.zad(r2);
        goto L11
    L10:
        ConnectionResult r06 = (ConnectionResult) com.google.android.gms.internal.base.zac.zaa(r2, ConnectionResult.CREATOR);
        zaa r07 = (zaa) com.google.android.gms.internal.base.zac.zaa(r2, zaa.CREATOR);
        com.google.android.gms.internal.base.zac.zad(r2);
        goto L11
    }
}
