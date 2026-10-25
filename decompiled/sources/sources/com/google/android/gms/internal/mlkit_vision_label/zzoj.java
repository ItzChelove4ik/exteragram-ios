package com.google.android.gms.internal.mlkit_vision_label;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public final class zzoj extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzoj> CREATOR = null;
    private final String zza;
    private final float zzb;
    private final String zzc;
    private final int zzd;

    static {
        CREATOR = new zzok();
    }

    public zzoj(String r1, float r2, String r3, int r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r4, int r5) {
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeString(r4, 1, this.zza, false);
        SafeParcelWriter.writeFloat(r4, 2, this.zzb);
        SafeParcelWriter.writeString(r4, 3, this.zzc, false);
        SafeParcelWriter.writeInt(r4, 4, this.zzd);
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }

    public final float zza() {
        return this.zzb;
    }

    public final int zzb() {
        return this.zzd;
    }

    public final String zzc() {
        return this.zzc;
    }

    public final String zzd() {
        return this.zza;
    }
}
