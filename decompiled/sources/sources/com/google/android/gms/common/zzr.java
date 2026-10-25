package com.google.android.gms.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public final class zzr extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzr> CREATOR = null;
    private final boolean zza;
    private final String zzb;
    private final int zzc;
    private final int zzd;
    private final long zze;

    static {
        CREATOR = new zzs();
    }

    public zzr(boolean r1, String r2, int r3, int r4, long r5) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = zzz.zza(r3) - 1;
        this.zzd = zzc.zza(r4) - 1;
        this.zze = r5;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r4, int r5) {
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeBoolean(r4, 1, this.zza);
        SafeParcelWriter.writeString(r4, 2, this.zzb, false);
        SafeParcelWriter.writeInt(r4, 3, this.zzc);
        SafeParcelWriter.writeInt(r4, 4, this.zzd);
        SafeParcelWriter.writeLong(r4, 5, this.zze);
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }

    public final boolean zza() {
        return this.zza;
    }

    public final String zzb() {
        return this.zzb;
    }

    public final long zzc() {
        return this.zze;
    }

    public final int zzd() {
        return zzz.zza(this.zzc);
    }

    public final int zze() {
        return zzc.zza(this.zzd);
    }
}
