package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public class UserVerificationMethodExtension extends AbstractSafeParcelable {
    public static final Parcelable.Creator<UserVerificationMethodExtension> CREATOR = null;
    private final boolean zza;

    static {
        CREATOR = new zzba();
    }

    public UserVerificationMethodExtension(boolean r1) {
        this.zza = r1;
    }

    public boolean equals(Object r3) {
        if ((r3 instanceof UserVerificationMethodExtension) == true) goto L6;
        return false;
    L6:
        if (this.zza != ((UserVerificationMethodExtension) r3).zza) goto L9;
        return true;
    L9:
        return false;
    }

    public boolean getUvm() {
        return this.zza;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{Boolean.valueOf(this.zza)});
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r2, int r3) {
        int r32 = SafeParcelWriter.beginObjectHeader(r2);
        SafeParcelWriter.writeBoolean(r2, 1, getUvm());
        SafeParcelWriter.finishObjectHeader(r2, r32);
    }
}
