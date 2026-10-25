package com.google.android.gms.internal.measurement;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public final class zzjq extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzjq> CREATOR = null;
    public final String zza;
    public final String zzb;
    public final zzjo zzc;
    public final boolean zzd;

    static {
        CREATOR = new zzjr();
    }

    public zzjq(String r1, String r2, zzjo r3, boolean r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
    }

    public final boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof zzjq) == true) goto L8;
        return false;
    L8:
        zzjq r52 = (zzjq) r5;
        if (zzkl.zza(this.zza, r52.zza) == true) goto L11;
    L17:
        return false;
    L11:
        if (zzkl.zza(this.zzb, r52.zzb) == false) goto L17;
        if (zzkl.zza(this.zzc, r52.zzc) == false) goto L17;
        if (this.zzd != r52.zzd) goto L17;
        return true;
    }

    public final String toString() {
        StringBuilder r0 = new StringBuilder();
        zza(r0);
        return r0.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r5, int r6) {
        String r0 = this.zza;
        int r1 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeString(r5, 2, r0, false);
        SafeParcelWriter.writeString(r5, 3, this.zzb, false);
        SafeParcelWriter.writeParcelable(r5, 4, this.zzc, r6, false);
        SafeParcelWriter.writeBoolean(r5, 5, this.zzd);
        SafeParcelWriter.finishObjectHeader(r5, r1);
    }

    public final void zza(StringBuilder r3) {
        r3.append("FlagOverride(");
        r3.append(this.zza);
        r3.append(", ");
        r3.append(this.zzb);
        r3.append(", ");
        this.zzc.zzf(r3);
        r3.append(", ");
        r3.append(this.zzd);
        r3.append(")");
    }
}
