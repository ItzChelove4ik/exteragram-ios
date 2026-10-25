package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes5.dex */
public final class zzom extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzom> CREATOR = null;
    public final long zza;
    public byte[] zzb;
    public final String zzc;
    public final Bundle zzd;
    public final int zze;
    public final long zzf;
    public String zzg;

    static {
        CREATOR = new zzon();
    }

    public zzom(long r1, byte[] r3, String r4, Bundle r5, int r6, long r7, String r9) {
        this.zza = r1;
        this.zzb = r3;
        this.zzc = r4;
        this.zzd = r5;
        this.zze = r6;
        this.zzf = r7;
        this.zzg = r9;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r6, int r7) {
        long r0 = this.zza;
        int r72 = SafeParcelWriter.beginObjectHeader(r6);
        SafeParcelWriter.writeLong(r6, 1, r0);
        SafeParcelWriter.writeByteArray(r6, 2, this.zzb, false);
        SafeParcelWriter.writeString(r6, 3, this.zzc, false);
        SafeParcelWriter.writeBundle(r6, 4, this.zzd, false);
        SafeParcelWriter.writeInt(r6, 5, this.zze);
        SafeParcelWriter.writeLong(r6, 6, this.zzf);
        SafeParcelWriter.writeString(r6, 7, this.zzg, false);
        SafeParcelWriter.finishObjectHeader(r6, r72);
    }
}
