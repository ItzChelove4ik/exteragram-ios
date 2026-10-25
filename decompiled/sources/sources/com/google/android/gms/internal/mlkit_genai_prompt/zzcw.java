package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public final class zzcw extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzcw> CREATOR = null;
    public static final zzcw zza = null;
    private final boolean zzb;

    static {
        CREATOR = new zzcx();
        zza = new zzcw(false);
    }

    public zzcw(boolean r1) {
        this.zzb = r1;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r2, int r3) {
        boolean r1 = this.zzb;
        int r32 = SafeParcelWriter.beginObjectHeader(r2);
        SafeParcelWriter.writeBoolean(r2, 1, r1);
        SafeParcelWriter.finishObjectHeader(r2, r32);
    }
}
