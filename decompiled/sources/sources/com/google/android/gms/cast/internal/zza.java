package com.google.android.gms.cast.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public final class zza extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zza> CREATOR = null;
    private final String zza;

    static {
        CREATOR = new zzb();
    }

    public zza() {
        this.zza = null;
    }

    public zza(String r1) {
        this.zza = r1;
    }

    public final boolean equals(Object r2) {
        if (r2 != this) goto L6;
        return true;
    L6:
        if ((r2 instanceof zza) == true) goto L10;
        return false;
    L10:
        return CastUtils.zza(this.zza, ((zza) r2).zza);
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zza});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r3, int r4) {
        String r2 = this.zza;
        int r42 = SafeParcelWriter.beginObjectHeader(r3);
        SafeParcelWriter.writeString(r3, 2, r2, false);
        SafeParcelWriter.finishObjectHeader(r3, r42);
    }

    public final String zza() {
        return this.zza;
    }
}
