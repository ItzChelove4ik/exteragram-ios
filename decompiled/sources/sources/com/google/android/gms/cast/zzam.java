package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public final class zzam extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzam> CREATOR = null;
    private final float zza;
    private final float zzb;
    private final float zzc;

    static {
        CREATOR = new zzan();
    }

    public zzam(float r1, float r2, float r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    public final boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof zzam) == true) goto L8;
        return false;
    L8:
        zzam r52 = (zzam) r5;
        if (this.zza == r52.zza) goto L11;
    L15:
        return false;
    L11:
        if (this.zzb != r52.zzb) goto L15;
        if (this.zzc != r52.zzc) goto L15;
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{Float.valueOf(this.zza), Float.valueOf(this.zzb), Float.valueOf(this.zzc)});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r3, int r4) {
        float r42 = this.zza;
        int r0 = SafeParcelWriter.beginObjectHeader(r3);
        SafeParcelWriter.writeFloat(r3, 2, r42);
        SafeParcelWriter.writeFloat(r3, 3, this.zzb);
        SafeParcelWriter.writeFloat(r3, 4, this.zzc);
        SafeParcelWriter.finishObjectHeader(r3, r0);
    }
}
