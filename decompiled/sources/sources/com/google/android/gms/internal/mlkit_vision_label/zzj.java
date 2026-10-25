package com.google.android.gms.internal.mlkit_vision_label;

import android.os.Parcel;
import android.os.Parcelable;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public final class zzj extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzj> CREATOR = null;
    public int zza;
    public int zzb;
    public float zzc;
    public int zzd;

    static {
        CREATOR = new zzk();
    }

    public zzj(int r2, int r3, float r4, int r5) {
        if (r2 != 1) goto L6;
        this.zza = 1;
        this.zzb = r3;
        this.zzc = r4;
        this.zzd = r5;
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("Unknown language.");
        throw null;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r3, int r4) {
        int r42 = SafeParcelWriter.beginObjectHeader(r3);
        SafeParcelWriter.writeInt(r3, 2, this.zza);
        SafeParcelWriter.writeInt(r3, 3, this.zzb);
        SafeParcelWriter.writeFloat(r3, 4, this.zzc);
        SafeParcelWriter.writeInt(r3, 5, this.zzd);
        SafeParcelWriter.finishObjectHeader(r3, r42);
    }
}
