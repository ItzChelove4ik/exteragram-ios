package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.wearable.Node;

/* loaded from: classes5.dex */
public final class zzgm extends AbstractSafeParcelable implements Node {
    public static final Parcelable.Creator<zzgm> CREATOR = null;
    private final String zza;
    private final String zzb;
    private final int zzc;
    private final boolean zzd;

    static {
        CREATOR = new zzgn();
    }

    public zzgm(String r1, String r2, int r3, boolean r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
    }

    public final boolean equals(Object r2) {
        if ((r2 instanceof zzgm) == true) goto L7;
        return false;
    L7:
        return ((zzgm) r2).zza.equals(this.zza);
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final String toString() {
        return "Node{" + this.zzb + ", id=" + this.zza + ", hops=" + this.zzc + ", isNearby=" + this.zzd + "}";
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r4, int r5) {
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeString(r4, 2, this.zza, false);
        SafeParcelWriter.writeString(r4, 3, this.zzb, false);
        SafeParcelWriter.writeInt(r4, 4, this.zzc);
        SafeParcelWriter.writeBoolean(r4, 5, this.zzd);
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }
}
