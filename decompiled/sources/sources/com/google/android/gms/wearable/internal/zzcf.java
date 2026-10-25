package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes5.dex */
public final class zzcf extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzcf> CREATOR = null;
    public final int zza;
    public final boolean zzb;
    public final boolean zzc;
    public final boolean zzd;
    public final boolean zze;

    static {
        CREATOR = new zzcg();
    }

    public zzcf(int r1, boolean r2, boolean r3, boolean r4, boolean r5) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r3, int r4) {
        int r42 = SafeParcelWriter.beginObjectHeader(r3);
        SafeParcelWriter.writeInt(r3, 1, this.zza);
        SafeParcelWriter.writeBoolean(r3, 2, this.zzb);
        SafeParcelWriter.writeBoolean(r3, 3, this.zzc);
        SafeParcelWriter.writeBoolean(r3, 4, this.zzd);
        SafeParcelWriter.writeBoolean(r3, 5, this.zze);
        SafeParcelWriter.finishObjectHeader(r3, r42);
    }
}
