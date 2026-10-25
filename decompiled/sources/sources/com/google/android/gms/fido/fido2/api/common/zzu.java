package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public final class zzu extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzu> CREATOR = null;

    static {
        CREATOR = new zzv();
    }

    public zzu(boolean r1) {
    }

    public final boolean equals(Object r1) {
        if ((r1 instanceof zzu) == true) goto L6;
        return false;
    L6:
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{Boolean.FALSE});
    }

    public final String toString() {
        return "DevicePublicKeyExtension{devicePublicKey=false}";
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r2, int r3) {
        int r1 = SafeParcelWriter.beginObjectHeader(r2);
        SafeParcelWriter.writeBoolean(r2, 1, false);
        SafeParcelWriter.finishObjectHeader(r2, r1);
    }
}
