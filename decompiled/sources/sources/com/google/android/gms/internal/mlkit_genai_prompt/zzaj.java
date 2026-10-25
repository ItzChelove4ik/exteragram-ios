package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public final class zzaj extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzaj> CREATOR = null;
    private final int zza;
    private final zzbt zzb;
    private final zzq zzc;

    static {
        CREATOR = new zzak();
    }

    public zzaj(int r1, zzbt r2, zzq r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r5, int r6) {
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeInt(r5, 1, this.zza);
        SafeParcelWriter.writeParcelable(r5, 2, this.zzb, r6, false);
        SafeParcelWriter.writeParcelable(r5, 3, this.zzc, r6, false);
        SafeParcelWriter.finishObjectHeader(r5, r0);
    }

    public final int zza() {
        return this.zza;
    }

    public final zzq zzb() {
        return this.zzc;
    }
}
