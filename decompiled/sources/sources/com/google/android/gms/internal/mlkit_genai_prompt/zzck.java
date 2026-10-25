package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public final class zzck extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzck> CREATOR = null;
    private final String zza;

    static {
        CREATOR = new zzcl();
    }

    public zzck(String r1) {
        this.zza = r1;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r3, int r4) {
        String r2 = this.zza;
        int r42 = SafeParcelWriter.beginObjectHeader(r3);
        SafeParcelWriter.writeString(r3, 1, r2, false);
        SafeParcelWriter.finishObjectHeader(r3, r42);
    }

    public final String zza() {
        return this.zza;
    }
}
