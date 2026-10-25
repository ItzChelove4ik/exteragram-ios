package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public final class zzuf extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzuf> CREATOR = null;
    private final float[] zza;
    private final Bitmap zzb;
    private final int zzc;
    private final int zzd;
    private final int zze;
    private final int zzf;
    private final int zzg;

    static {
        CREATOR = new zzug();
    }

    public zzuf(float[] r1, Bitmap r2, int r3, int r4, int r5, int r6, int r7) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
        this.zzf = r6;
        this.zzg = r7;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r5, int r6) {
        float[] r0 = this.zza;
        int r1 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeFloatArray(r5, 1, r0, false);
        SafeParcelWriter.writeParcelable(r5, 2, this.zzb, r6, false);
        SafeParcelWriter.writeInt(r5, 3, this.zzc);
        SafeParcelWriter.writeInt(r5, 4, this.zzd);
        SafeParcelWriter.writeInt(r5, 5, this.zze);
        SafeParcelWriter.writeInt(r5, 6, this.zzf);
        SafeParcelWriter.writeInt(r5, 7, this.zzg);
        SafeParcelWriter.finishObjectHeader(r5, r1);
    }

    public final int zza() {
        return this.zzd;
    }

    public final int zzb() {
        return this.zze;
    }

    public final int zzc() {
        return this.zzf;
    }

    public final int zzd() {
        return this.zzc;
    }

    public final Bitmap zze() {
        return this.zzb;
    }

    public final float[] zzf() {
        return this.zza;
    }
}
