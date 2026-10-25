package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* loaded from: classes4.dex */
public final class zzq extends Exception implements SafeParcelable {
    public static final Parcelable.Creator<zzq> CREATOR = null;
    private final int zza;
    private final int zzb;
    private final zzcr zzc;
    private final zzbq zzd;

    static {
        CREATOR = new zzr();
    }

    public zzq(int r1, int r2, String r3, zzcr r4, zzbq r5) {
        super(r3);
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r4;
        this.zzd = r5;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r5, int r6) {
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeInt(r5, 1, this.zza);
        SafeParcelWriter.writeInt(r5, 2, this.zzb);
        SafeParcelWriter.writeString(r5, 3, getMessage(), false);
        SafeParcelWriter.writeParcelable(r5, 4, this.zzc, r6, false);
        SafeParcelWriter.writeParcelable(r5, 5, this.zzd, r6, false);
        SafeParcelWriter.finishObjectHeader(r5, r0);
    }

    public final int zza() {
        return this.zza;
    }

    public final int zzb() {
        return this.zzb;
    }

    public final zzbq zzc() {
        return this.zzd;
    }
}
