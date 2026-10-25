package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* loaded from: classes5.dex */
public final class zzbh extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzbh> CREATOR = null;
    public final String zza;
    public final zzbf zzb;
    public final String zzc;
    public final long zzd;
    public final long zze;

    static {
        CREATOR = new zzbi();
    }

    public zzbh(zzbh r2, long r3, long r5) {
        Preconditions.checkNotNull(r2);
        this.zza = r2.zza;
        this.zzb = r2.zzb;
        this.zzc = r2.zzc;
        this.zzd = r3;
        this.zze = r5;
    }

    public zzbh(String r1, zzbf r2, String r3, long r4, long r6) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r6;
    }

    public final String toString() {
        String r0 = String.valueOf(this.zzb);
        String r1 = this.zzc;
        int r2 = String.valueOf(r1).length();
        String r5 = this.zza;
        int r3 = String.valueOf(r5).length();
        int r22 = ((r2 + 13) + r3) + 8;
        StringBuilder r32 = new StringBuilder(r22 + r0.length());
        r32.append("origin=");
        r32.append(r1);
        r32.append(",name=");
        r32.append(r5);
        r32.append(",params=");
        r32.append(r0);
        return r32.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r1, int r2) {
        zzbi.zza(this, r1, r2);
    }
}
