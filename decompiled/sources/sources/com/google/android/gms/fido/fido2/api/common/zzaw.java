package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public final class zzaw extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzaw> CREATOR = null;
    private final String zza;

    static {
        CREATOR = new zzax();
    }

    public zzaw(String r1) {
        this.zza = r1;
    }

    public final boolean equals(Object r2) {
        if ((r2 instanceof zzaw) == true) goto L7;
        return false;
    L7:
        return Objects.equal(this.zza, ((zzaw) r2).zza);
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zza});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r3, int r4) {
        String r2 = this.zza;
        int r42 = SafeParcelWriter.beginObjectHeader(r3);
        SafeParcelWriter.writeString(r3, 1, r2, false);
        SafeParcelWriter.finishObjectHeader(r3, r42);
    }
}
