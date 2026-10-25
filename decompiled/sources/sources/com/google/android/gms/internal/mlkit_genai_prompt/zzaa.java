package com.google.android.gms.internal.mlkit_genai_prompt;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public final class zzaa extends AbstractSafeParcelable implements zzv {
    public static final Parcelable.Creator<zzaa> CREATOR = null;
    private final int zza;
    private final String zzb;
    private final Bitmap zzc;
    private final int zzd;
    private final ParcelFileDescriptor zze;
    private final ParcelFileDescriptor zzf;

    static {
        CREATOR = new zzab();
    }

    public zzaa(int r1, String r2, Bitmap r3, int r4, ParcelFileDescriptor r5, ParcelFileDescriptor r6) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
        this.zzf = r6;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r5, int r6) {
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeInt(r5, 1, this.zza);
        SafeParcelWriter.writeString(r5, 2, this.zzb, false);
        SafeParcelWriter.writeParcelable(r5, 3, this.zzc, r6, false);
        SafeParcelWriter.writeInt(r5, 4, this.zzd);
        SafeParcelWriter.writeParcelable(r5, 5, this.zze, r6, false);
        SafeParcelWriter.writeParcelable(r5, 6, this.zzf, r6, false);
        SafeParcelWriter.finishObjectHeader(r5, r0);
    }

    public final ParcelFileDescriptor zza() {
        return this.zze;
    }

    public final ParcelFileDescriptor zzb() {
        return this.zzf;
    }
}
