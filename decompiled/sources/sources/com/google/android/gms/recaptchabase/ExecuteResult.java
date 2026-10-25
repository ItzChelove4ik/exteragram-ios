package com.google.android.gms.recaptchabase;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes5.dex */
public final class ExecuteResult extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ExecuteResult> CREATOR = null;
    private String zza;

    static {
        CREATOR = new zzd();
    }

    public ExecuteResult(String r1) {
        this.zza = r1;
    }

    public boolean equals(Object r2) {
        if (this != r2) goto L6;
        return true;
    L6:
        if ((r2 instanceof ExecuteResult) == true) goto L8;
        return false;
    L8:
        return Objects.equal(this.zza, ((ExecuteResult) r2).zza);
    }

    public String getPayload() {
        return this.zza;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.zza});
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r3, int r4) {
        int r42 = SafeParcelWriter.beginObjectHeader(r3);
        SafeParcelWriter.writeString(r3, 1, getPayload(), false);
        SafeParcelWriter.finishObjectHeader(r3, r42);
    }
}
