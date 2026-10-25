package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import okio.Buffer$$ExternalSyntheticBUOutline2;

/* loaded from: classes4.dex */
public final class zzdf extends AbstractSafeParcelable implements zzv {
    public static final Parcelable.Creator<zzdf> CREATOR = null;
    private final zzml zza;

    static {
        CREATOR = new zzdg();
    }

    public zzdf(List r1) {
        if (r1 != null) goto L5;
        zzml r12 = zzml.zzk();
    L6:
        this.zza = r12;
        return;
    L5:
        r12 = zzml.zzq(r1);
        goto L6
    }

    public final String toString() {
        zzml r2 = this.zza;
        zzku r0 = zzku.zza(",");
        Iterator r22 = r2.iterator();
        StringBuilder r1 = new StringBuilder();
        r0.zzb(r1, r22);     // Catch: IOException -> L6
        return r1.toString();
    L6:
        e = move-exception;
        Buffer$$ExternalSyntheticBUOutline2.m(e);
        return null;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r3, int r4) {
        zzml r2 = this.zza;
        int r42 = SafeParcelWriter.beginObjectHeader(r3);
        SafeParcelWriter.writeTypedList(r3, 1, r2, false);
        SafeParcelWriter.finishObjectHeader(r3, r42);
    }
}
