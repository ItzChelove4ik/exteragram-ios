package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes5.dex */
public final class zzi extends AbstractSafeParcelable implements com.google.android.gms.wearable.zza {
    public static final Parcelable.Creator<zzi> CREATOR = null;
    private final byte zza;
    private final byte zzb;
    private final String zzc;

    static {
        CREATOR = new zzj();
    }

    public zzi(byte r1, byte r2, String r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    public final boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L20:
        return false;
    L8:
        if (zzi.class != r5.getClass()) goto L20;
        zzi r52 = (zzi) r5;
        if (this.zza == r52.zza) goto L14;
        return false;
    L14:
        if (this.zzb == r52.zzb) goto L17;
        return false;
    L17:
        if (this.zzc.equals(r52.zzc) == true) goto L19;
        return false;
    L19:
        return true;
    }

    public final int hashCode() {
        return ((((this.zza + 31) * 31) + this.zzb) * 31) + this.zzc.hashCode();
    }

    public final String toString() {
        return "AmsEntityUpdateParcelable{, mEntityId=" + this.zza + ", mAttributeId=" + this.zzb + ", mValue='" + this.zzc + "'}";
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r3, int r4) {
        int r42 = SafeParcelWriter.beginObjectHeader(r3);
        SafeParcelWriter.writeByte(r3, 2, this.zza);
        SafeParcelWriter.writeByte(r3, 3, this.zzb);
        SafeParcelWriter.writeString(r3, 4, this.zzc, false);
        SafeParcelWriter.finishObjectHeader(r3, r42);
    }
}
