package com.google.android.gms.internal.mlkit_genai_prompt;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public final class zzbz extends AbstractSafeParcelable implements zzv {
    public static final Parcelable.Creator<zzbz> CREATOR = null;
    private final int zza;
    private final String zzb;
    private final Bitmap zzc;
    private final int zzd;
    private final ParcelFileDescriptor zze;
    private final boolean zzf;
    private final zzbv zzg;
    private final zzbx zzh;

    static {
        CREATOR = new zzca();
    }

    public zzbz(int r1, String r2, Bitmap r3, int r4, ParcelFileDescriptor r5, boolean r6, zzbv r7, zzbx r8) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
        this.zzf = r6;
        this.zzg = r7;
        this.zzh = r8;
    }

    public static zzbz zzc(int r9, Bitmap r10, boolean r11) {
        return new zzbz(r9, null, r10, 1, null, false, null, null);
    }

    public static zzbz zzd(int r9, String r10) {
        return new zzbz(r9, r10, null, 0, null, false, null, null);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r5, int r6) {
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeInt(r5, 1, this.zza);
        SafeParcelWriter.writeString(r5, 2, this.zzb, false);
        SafeParcelWriter.writeParcelable(r5, 3, this.zzc, r6, false);
        SafeParcelWriter.writeInt(r5, 4, this.zzd);
        SafeParcelWriter.writeParcelable(r5, 5, this.zze, r6, false);
        SafeParcelWriter.writeBoolean(r5, 6, this.zzf);
        SafeParcelWriter.writeParcelable(r5, 7, this.zzg, r6, false);
        SafeParcelWriter.writeParcelable(r5, 8, this.zzh, r6, false);
        SafeParcelWriter.finishObjectHeader(r5, r0);
    }

    public final ParcelFileDescriptor zza() {
        return this.zze;
    }

    public final zzbv zzb() {
        return this.zzg;
    }
}
