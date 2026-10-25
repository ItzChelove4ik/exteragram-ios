package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public final class zaw extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zaw> CREATOR = null;
    final int zaa;
    private final Account zab;
    private final int zac;
    private final GoogleSignInAccount zad;

    static {
        CREATOR = new zax();
    }

    public zaw(int r1, Account r2, int r3, GoogleSignInAccount r4) {
        this.zaa = r1;
        this.zab = r2;
        this.zac = r3;
        this.zad = r4;
    }

    public zaw(Account r2, int r3, GoogleSignInAccount r4) {
        this(2, r2, r3, r4);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r5, int r6) {
        int r0 = this.zaa;
        int r1 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeInt(r5, 1, r0);
        SafeParcelWriter.writeParcelable(r5, 2, this.zab, r6, false);
        SafeParcelWriter.writeInt(r5, 3, this.zac);
        SafeParcelWriter.writeParcelable(r5, 4, this.zad, r6, false);
        SafeParcelWriter.finishObjectHeader(r5, r1);
    }
}
