package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.zaw;

/* loaded from: classes5.dex */
public final class zai extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zai> CREATOR = null;
    final int zaa;
    final zaw zab;

    static {
        CREATOR = new zaj();
    }

    public zai(int r1, zaw r2) {
        this.zaa = r1;
        this.zab = r2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r4, int r5) {
        int r0 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeInt(r4, 1, this.zaa);
        SafeParcelWriter.writeParcelable(r4, 2, this.zab, r5, false);
        SafeParcelWriter.finishObjectHeader(r4, r0);
    }
}
