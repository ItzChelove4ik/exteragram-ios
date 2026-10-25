package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public final class zab extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zab> CREATOR = null;
    private final int zaa;
    private final String zab;
    private final long zac;
    private final int zad;
    private final boolean zae;

    static {
        CREATOR = new zac();
    }

    public zab(int r1, String r2, long r3, int r5, boolean r6) {
        this.zaa = r1;
        this.zab = r2;
        this.zac = r3;
        this.zad = r5;
        this.zae = r6;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r4, int r5) {
        int r52 = this.zaa;
        int r0 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeInt(r4, 1, r52);
        SafeParcelWriter.writeString(r4, 2, this.zab, false);
        SafeParcelWriter.writeLong(r4, 3, this.zac);
        SafeParcelWriter.writeInt(r4, 4, this.zad);
        SafeParcelWriter.writeBoolean(r4, 5, this.zae);
        SafeParcelWriter.finishObjectHeader(r4, r0);
    }
}
