package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public final class zzcr extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzcr> CREATOR = null;
    private final int zza;

    static {
        CREATOR = new zzcs();
    }

    public zzcr(int r1) {
        this.zza = r1;
    }

    public final boolean equals(Object r4) {
        if (this != r4) goto L6;
        return true;
    L6:
        if ((r4 instanceof zzcr) == true) goto L8;
    L10:
        return false;
    L8:
        if (this.zza != ((zzcr) r4).zza) goto L10;
        return true;
    }

    public final int hashCode() {
        return Integer.hashCode(this.zza);
    }

    public final String toString() {
        int r2 = this.zza;
        if (r2 != 0) goto L5;
        String r22 = "UNSPECIFIED";
    L12:
        StringBuilder r1 = new StringBuilder(r22.length() + 33);
        r1.append("RetryDetails { retryGuidance = ");
        r1.append(r22);
        r1.append(" }");
        return r1.toString();
    L5:
        if (r2 != 1) goto L7;
        r22 = "RETRYABLE";
        goto L12
    L7:
        if (r2 == 2) goto L9;
        r22 = "Unknown";
        goto L12
    L9:
        r22 = "NOT_RETRYABLE";
        goto L12
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r2, int r3) {
        int r1 = this.zza;
        int r32 = SafeParcelWriter.beginObjectHeader(r2);
        SafeParcelWriter.writeInt(r2, 1, r1);
        SafeParcelWriter.finishObjectHeader(r2, r32);
    }
}
