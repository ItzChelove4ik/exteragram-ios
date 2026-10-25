package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.List;

/* loaded from: classes4.dex */
public final class zzw extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzw> CREATOR = null;
    private final zzml zza;

    static {
        CREATOR = new zzx();
    }

    public zzw(List r1) {
        this.zza = zzml.zzq(r1);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r3, int r4) {
        zzml r2 = this.zza;
        int r42 = SafeParcelWriter.beginObjectHeader(r3);
        SafeParcelWriter.writeTypedList(r3, 1, r2, false);
        SafeParcelWriter.finishObjectHeader(r3, r42);
    }

    public final zzml zza() {
        return this.zza;
    }
}
