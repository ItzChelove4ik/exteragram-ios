package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes.dex */
public final class zzdb extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzdb> CREATOR = null;
    public final long zza;
    public final long zzb;
    public final boolean zzc;
    public final Bundle zzd;
    public final String zze;

    static {
        CREATOR = new zzdc();
    }

    public zzdb(long r1, long r3, boolean r5, Bundle r6, String r7) {
        this.zza = r1;
        this.zzb = r3;
        this.zzc = r5;
        this.zzd = r6;
        this.zze = r7;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r4, int r5) {
        long r0 = this.zza;
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeLong(r4, 1, r0);
        SafeParcelWriter.writeLong(r4, 2, this.zzb);
        SafeParcelWriter.writeBoolean(r4, 3, this.zzc);
        SafeParcelWriter.writeBundle(r4, 7, this.zzd, false);
        SafeParcelWriter.writeString(r4, 8, this.zze, false);
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }
}
