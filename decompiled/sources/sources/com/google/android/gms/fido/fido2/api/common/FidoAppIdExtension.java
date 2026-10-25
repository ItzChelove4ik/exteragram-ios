package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public class FidoAppIdExtension extends AbstractSafeParcelable {
    public static final Parcelable.Creator<FidoAppIdExtension> CREATOR = null;
    private final String zza;

    static {
        CREATOR = new zzx();
    }

    public FidoAppIdExtension(String r1) {
        this.zza = (String) Preconditions.checkNotNull(r1);
    }

    public boolean equals(Object r2) {
        if ((r2 instanceof FidoAppIdExtension) == true) goto L7;
        return false;
    L7:
        return this.zza.equals(((FidoAppIdExtension) r2).zza);
    }

    public String getAppId() {
        return this.zza;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.zza});
    }

    public final String toString() {
        return "FidoAppIdExtension{appid='" + this.zza + "'}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r3, int r4) {
        int r42 = SafeParcelWriter.beginObjectHeader(r3);
        SafeParcelWriter.writeString(r3, 2, getAppId(), false);
        SafeParcelWriter.finishObjectHeader(r3, r42);
    }
}
