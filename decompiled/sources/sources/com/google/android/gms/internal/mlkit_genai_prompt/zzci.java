package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

/* loaded from: classes4.dex */
public final class zzci implements SafeParcelable, zzv {
    public static final Parcelable.Creator<zzci> CREATOR = null;
    private final zzml zza;
    private final zzbt zzb;
    private final zzck zzc;
    private final zzml zzd;

    static {
        CREATOR = new zzcj();
    }

    public zzci(List r1, zzbt r2, zzck r3, List r4) {
        this.zza = zzml.zzq(r1);
        this.zzb = r2;
        this.zzc = r3;
        if (r4 != null) goto L5;
        zzml r12 = zzml.zzk();
    L6:
        this.zzd = r12;
        return;
    L5:
        r12 = zzml.zzq(r4);
        goto L6
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r5, int r6) {
        zzml r0 = this.zza;
        int r1 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeTypedList(r5, 1, r0, false);
        SafeParcelWriter.writeParcelable(r5, 2, this.zzb, r6, false);
        SafeParcelWriter.writeParcelable(r5, 3, this.zzc, r6, false);
        SafeParcelWriter.writeTypedList(r5, 4, this.zzd, false);
        SafeParcelWriter.finishObjectHeader(r5, r1);
    }

    public final zzml zza() {
        return this.zza;
    }

    public final zzbt zzb() {
        return this.zzb;
    }

    public final zzck zzc() {
        return this.zzc;
    }

    public final zzml zzd() {
        return this.zzd;
    }
}
