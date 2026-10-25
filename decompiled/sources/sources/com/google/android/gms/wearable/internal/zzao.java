package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.wearable.CapabilityInfo;
import java.util.List;
import java.util.Set;

/* loaded from: classes5.dex */
public final class zzao extends AbstractSafeParcelable implements CapabilityInfo {
    public static final Parcelable.Creator<zzao> CREATOR = null;
    private final Object zza;
    private final String zzb;
    private final List zzc;
    private Set zzd;

    static {
        CREATOR = new zzap();
    }

    public zzao(String r2, List r3) {
        this.zza = new Object();
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = null;
        Preconditions.checkNotNull(r2);
        Preconditions.checkNotNull(r3);
    }

    public final boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L25:
        return false;
    L8:
        if (zzao.class != r5.getClass()) goto L25;
        zzao r52 = (zzao) r5;
        String r2 = this.zzb;
        String r3 = r52.zzb;
        if (r2 != null) goto L13;
        if (r3 == null) goto L17;
    L16:
        return false;
    L17:
        List r4 = this.zzc;
        List r53 = r52.zzc;
        if (r4 != null) goto L20;
        if (r53 == null) goto L24;
    L23:
        return false;
    L24:
        return true;
    L20:
        if (r4.equals(r53) == true) goto L24;
    L13:
        if (r2.equals(r3) == false) goto L16;
        goto L16
    }

    public final int hashCode() {
        String r0 = this.zzb;
        int r1 = 0;
        if (r0 == null) goto L5;
        int r02 = r0.hashCode();
    L6:
        int r03 = (r02 + 31) * 31;
        List r2 = this.zzc;
        if (r2 == null) goto L10;
        r1 = r2.hashCode();
    L10:
        return r03 + r1;
    L5:
        r02 = 0;
        goto L6
    }

    public final String toString() {
        return "CapabilityInfo{" + this.zzb + ", " + String.valueOf(this.zzc) + "}";
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r4, int r5) {
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeString(r4, 2, this.zzb, false);
        SafeParcelWriter.writeTypedList(r4, 3, this.zzc, false);
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }
}
