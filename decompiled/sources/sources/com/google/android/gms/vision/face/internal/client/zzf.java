package com.google.android.gms.vision.face.internal.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes5.dex */
public final class zzf extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzf> CREATOR = null;
    public int zza;
    public int zzb;
    public int zzc;
    public boolean zzd;
    public boolean zze;
    public float zzf;

    static {
        CREATOR = new zze();
    }

    public zzf() {
    }

    public zzf(int r1, int r2, int r3, boolean r4, boolean r5, float r6) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
        this.zzf = r6;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r3, int r4) {
        int r42 = SafeParcelWriter.beginObjectHeader(r3);
        SafeParcelWriter.writeInt(r3, 2, this.zza);
        SafeParcelWriter.writeInt(r3, 3, this.zzb);
        SafeParcelWriter.writeInt(r3, 4, this.zzc);
        SafeParcelWriter.writeBoolean(r3, 5, this.zzd);
        SafeParcelWriter.writeBoolean(r3, 6, this.zze);
        SafeParcelWriter.writeFloat(r3, 7, this.zzf);
        SafeParcelWriter.finishObjectHeader(r3, r42);
    }
}
