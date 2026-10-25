package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public final class zzbv extends AbstractSafeParcelable implements zzv {
    public static final Parcelable.Creator<zzbv> CREATOR = null;
    private final ParcelFileDescriptor zza;
    private final String zzb;

    static {
        CREATOR = new zzbw();
    }

    public zzbv(ParcelFileDescriptor r1, String r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r5, int r6) {
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeParcelable(r5, 1, this.zza, r6, false);
        SafeParcelWriter.writeString(r5, 2, this.zzb, false);
        SafeParcelWriter.finishObjectHeader(r5, r0);
    }

    public final ParcelFileDescriptor zza() {
        return this.zza;
    }
}
