package com.google.android.gms.internal.mlkit_vision_label;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public final class zzol extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzol> CREATOR = null;
    private final float zza;
    private final int zzb;

    static {
        CREATOR = new zzom();
    }

    public zzol(float r1, int r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r3, int r4) {
        int r42 = SafeParcelWriter.beginObjectHeader(r3);
        SafeParcelWriter.writeFloat(r3, 1, this.zza);
        SafeParcelWriter.writeInt(r3, 2, this.zzb);
        SafeParcelWriter.finishObjectHeader(r3, r42);
    }
}
