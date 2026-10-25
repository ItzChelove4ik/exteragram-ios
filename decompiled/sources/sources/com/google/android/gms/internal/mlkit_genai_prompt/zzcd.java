package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.List;

/* loaded from: classes4.dex */
public final class zzcd extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzcd> CREATOR = null;
    public final int zza;
    final Bundle zzb;
    private final String zzc;

    @Deprecated
    private final float zzd;
    private final float zze;
    private final zzw zzf;
    private final List zzg;

    static {
        CREATOR = new zzce();
    }

    public zzcd(String r1, float r2, int r3, float r4, Bundle r5, zzw r6, List r7) {
        this.zzc = r1;
        this.zzd = r2;
        this.zza = r3;
        this.zze = r4;
        this.zzb = r5;
        this.zzf = r6;
        this.zzg = r7;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r5, int r6) {
        String r0 = this.zzc;
        int r1 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeString(r5, 1, r0, false);
        SafeParcelWriter.writeFloat(r5, 2, this.zzd);
        SafeParcelWriter.writeInt(r5, 3, this.zza);
        SafeParcelWriter.writeFloat(r5, 4, this.zze);
        SafeParcelWriter.writeBundle(r5, 5, this.zzb, false);
        SafeParcelWriter.writeParcelable(r5, 6, this.zzf, r6, false);
        SafeParcelWriter.writeTypedList(r5, 7, this.zzg, false);
        SafeParcelWriter.finishObjectHeader(r5, r1);
    }

    public final String zza() {
        return this.zzc;
    }

    public final float zzb() {
        return this.zze;
    }

    public final zzw zzc() {
        return this.zzf;
    }

    public final List zzd() {
        return this.zzg;
    }
}
