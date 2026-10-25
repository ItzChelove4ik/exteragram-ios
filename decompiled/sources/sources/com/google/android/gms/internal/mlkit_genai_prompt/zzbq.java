package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Objects;

/* loaded from: classes4.dex */
public final class zzbq extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzbq> CREATOR = null;
    private final zzcm zza;

    static {
        CREATOR = new zzbr();
    }

    public zzbq(zzcm r1) {
        this.zza = r1;
    }

    public final boolean equals(Object r2) {
        if (this != r2) goto L6;
        return true;
    L6:
        if ((r2 instanceof zzbq) == true) goto L8;
        return false;
    L8:
        return Objects.equals(this.zza, ((zzbq) r2).zza);
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.zza});
    }

    public final String toString() {
        String r2 = String.valueOf(this.zza);
        StringBuilder r1 = new StringBuilder(r2.length() + 50);
        r1.append("InferenceErrorDetails {quotaExceededErrorDetails=");
        r1.append(r2);
        r1.append("}");
        return r1.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r4, int r5) {
        int r0 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeParcelable(r4, 1, this.zza, r5, false);
        SafeParcelWriter.finishObjectHeader(r4, r0);
    }

    public final zzcm zza() {
        return this.zza;
    }
}
