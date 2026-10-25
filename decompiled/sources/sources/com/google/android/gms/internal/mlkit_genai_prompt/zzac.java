package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public final class zzac extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzac> CREATOR = null;
    public final int zza;
    private final String zzb;

    @Deprecated
    private final float zzc;
    private final float zzd;

    static {
        CREATOR = new zzad();
    }

    public zzac(String r1, float r2, int r3, float r4) {
        this.zzb = r1;
        this.zzc = r2;
        this.zza = r3;
        this.zzd = r4;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r4, int r5) {
        String r52 = this.zzb;
        int r0 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeString(r4, 1, r52, false);
        SafeParcelWriter.writeFloat(r4, 2, this.zzc);
        SafeParcelWriter.writeInt(r4, 3, this.zza);
        SafeParcelWriter.writeFloat(r4, 4, this.zzd);
        SafeParcelWriter.finishObjectHeader(r4, r0);
    }

    public final String zza() {
        return this.zzb;
    }

    public final float zzb() {
        return this.zzd;
    }
}
