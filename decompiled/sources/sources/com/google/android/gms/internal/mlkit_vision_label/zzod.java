package com.google.android.gms.internal.mlkit_vision_label;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public final class zzod extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzod> CREATOR = null;
    private final int zza;
    private final int zzb;
    private final int zzc;
    private final int zzd;
    private final long zze;

    static {
        CREATOR = new zzoe();
    }

    public zzod(int r1, int r2, int r3, int r4, long r5) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r4, int r5) {
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeInt(r4, 1, this.zza);
        SafeParcelWriter.writeInt(r4, 2, this.zzb);
        SafeParcelWriter.writeInt(r4, 3, this.zzc);
        SafeParcelWriter.writeInt(r4, 4, this.zzd);
        SafeParcelWriter.writeLong(r4, 5, this.zze);
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }
}
