package com.google.android.gms.internal.mlkit_vision_label;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public final class zzh extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzh> CREATOR = null;
    public final String zza;
    public final String zzb;
    public final float zzc;
    public final int zzd;

    static {
        CREATOR = new zzi();
    }

    public zzh(String r1, String r2, float r3, int r4) {
        this.zzb = r2;
        this.zzc = r3;
        this.zza = r1;
        this.zzd = r4;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r4, int r5) {
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeString(r4, 2, this.zzb, false);
        SafeParcelWriter.writeFloat(r4, 3, this.zzc);
        SafeParcelWriter.writeString(r4, 4, this.zza, false);
        SafeParcelWriter.writeInt(r4, 5, this.zzd);
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }
}
