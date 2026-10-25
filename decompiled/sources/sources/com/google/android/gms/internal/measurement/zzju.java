package com.google.android.gms.internal.measurement;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public final class zzju extends AbstractSafeParcelable implements Comparable<zzju> {
    public static final Parcelable.Creator<zzju> CREATOR = null;
    public final int zza;
    public final int zzb;

    static {
        CREATOR = new zzjv();
    }

    public zzju(int r1, int r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(zzju r1) {
        return zza(r1);
    }

    public final boolean equals(Object r3) {
        if ((r3 instanceof zzju) == true) goto L5;
    L8:
        return false;
    L5:
        if (zza((zzju) r3) != 0) goto L8;
        return true;
    }

    public final int hashCode() {
        return (this.zza * 31) + this.zzb;
    }

    public final String toString() {
        int r0 = this.zza;
        int r1 = String.valueOf(r0).length();
        int r3 = this.zzb;
        int r12 = r1 + 19;
        StringBuilder r2 = new StringBuilder((r12 + String.valueOf(r3).length()) + 1);
        r2.append("GenericDimension(");
        r2.append(r0);
        r2.append(", ");
        r2.append(r3);
        r2.append(")");
        return r2.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r3, int r4) {
        int r42 = this.zza;
        int r0 = SafeParcelWriter.beginObjectHeader(r3);
        SafeParcelWriter.writeInt(r3, 1, r42);
        SafeParcelWriter.writeInt(r3, 2, this.zzb);
        SafeParcelWriter.finishObjectHeader(r3, r0);
    }

    public final int zza(zzju r5) {
        int r0 = this.zza;
        int r1 = r5.zza;
        if (r0 >= r1) goto L6;
        return -1;
    L6:
        if (r0 <= r1) goto L8;
        return 1;
    L8:
        int r4 = this.zzb;
        int r52 = r5.zzb;
        if (r4 >= r52) goto L11;
        return -1;
    L11:
        if (r4 <= r52) goto L13;
        return 1;
    L13:
        return 0;
    }
}
