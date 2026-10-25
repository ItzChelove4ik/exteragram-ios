package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public final class zzad extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzad> CREATOR = null;
    private final boolean zza;

    static {
        CREATOR = new zzae();
    }

    public zzad(boolean r1) {
        this.zza = ((Boolean) Preconditions.checkNotNull(Boolean.valueOf(r1))).booleanValue();
    }

    public final boolean equals(Object r3) {
        if ((r3 instanceof zzad) == true) goto L6;
        return false;
    L6:
        if (this.zza != ((zzad) r3).zza) goto L9;
        return true;
    L9:
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{Boolean.valueOf(this.zza)});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r2, int r3) {
        boolean r1 = this.zza;
        int r32 = SafeParcelWriter.beginObjectHeader(r2);
        SafeParcelWriter.writeBoolean(r2, 1, r1);
        SafeParcelWriter.finishObjectHeader(r2, r32);
    }
}
