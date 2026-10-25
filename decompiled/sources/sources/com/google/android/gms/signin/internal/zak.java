package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.zay;

/* loaded from: classes5.dex */
public final class zak extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zak> CREATOR = null;
    final int zaa;
    private final ConnectionResult zab;
    private final zay zac;

    static {
        CREATOR = new zal();
    }

    public zak(int r1, ConnectionResult r2, zay r3) {
        this.zaa = r1;
        this.zab = r2;
        this.zac = r3;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r5, int r6) {
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeInt(r5, 1, this.zaa);
        SafeParcelWriter.writeParcelable(r5, 2, this.zab, r6, false);
        SafeParcelWriter.writeParcelable(r5, 3, this.zac, r6, false);
        SafeParcelWriter.finishObjectHeader(r5, r0);
    }

    public final ConnectionResult zaa() {
        return this.zab;
    }

    public final zay zab() {
        return this.zac;
    }
}
