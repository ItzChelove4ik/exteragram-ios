package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public final class zzuj extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzuj> CREATOR = null;
    private final boolean zza;
    private final boolean zzb;
    private final boolean zzc;
    private final boolean zzd;
    private final boolean zze;

    static {
        CREATOR = new zzuk();
    }

    public zzuj(boolean r1, boolean r2, boolean r3, boolean r4, boolean r5) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r3, int r4) {
        boolean r42 = this.zza;
        int r0 = SafeParcelWriter.beginObjectHeader(r3);
        SafeParcelWriter.writeBoolean(r3, 1, r42);
        SafeParcelWriter.writeBoolean(r3, 2, this.zzb);
        SafeParcelWriter.writeBoolean(r3, 3, this.zzc);
        SafeParcelWriter.writeBoolean(r3, 4, this.zzd);
        SafeParcelWriter.writeBoolean(r3, 5, this.zze);
        SafeParcelWriter.finishObjectHeader(r3, r0);
    }
}
