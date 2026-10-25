package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public final class zzab extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzab> CREATOR = null;
    private final long zza;

    static {
        CREATOR = new zzac();
    }

    public zzab(long r1) {
        this.zza = ((Long) Preconditions.checkNotNull(Long.valueOf(r1))).longValue();
    }

    public final boolean equals(Object r5) {
        if ((r5 instanceof zzab) == true) goto L6;
        return false;
    L6:
        if (this.zza != ((zzab) r5).zza) goto L9;
        return true;
    L9:
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{Long.valueOf(this.zza)});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r3, int r4) {
        long r0 = this.zza;
        int r2 = SafeParcelWriter.beginObjectHeader(r3);
        SafeParcelWriter.writeLong(r3, 1, r0);
        SafeParcelWriter.finishObjectHeader(r3, r2);
    }
}
