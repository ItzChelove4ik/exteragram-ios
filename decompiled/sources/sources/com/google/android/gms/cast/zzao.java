package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public final class zzao extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzao> CREATOR = null;
    private final zzam zza;
    private final zzam zzb;

    static {
        CREATOR = new zzap();
    }

    public zzao(zzam r1, zzam r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    public final boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof zzao) == true) goto L8;
        return false;
    L8:
        zzao r52 = (zzao) r5;
        if (CastUtils.zza(this.zza, r52.zza) == true) goto L11;
    L13:
        return false;
    L11:
        if (CastUtils.zza(this.zzb, r52.zzb) == false) goto L13;
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zza, this.zzb});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r5, int r6) {
        zzam r0 = this.zza;
        int r1 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeParcelable(r5, 2, r0, r6, false);
        SafeParcelWriter.writeParcelable(r5, 3, this.zzb, r6, false);
        SafeParcelWriter.finishObjectHeader(r5, r1);
    }
}
