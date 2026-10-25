package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Arrays;
import java.util.Locale;

/* loaded from: classes5.dex */
public final class zziv extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zziv> CREATOR = null;
    public final String zza;
    public final int zzb;
    public final int zzc;

    static {
        CREATOR = new zzfc();
    }

    public zziv(String r1, int r2, int r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    public final boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L20:
        return false;
    L8:
        if (zziv.class != r5.getClass()) goto L20;
        zziv r52 = (zziv) r5;
        if (this.zzb != r52.zzb) goto L20;
        if (this.zzc != r52.zzc) goto L20;
        String r4 = this.zza;
        String r53 = r52.zza;
        if (r4 == r53) goto L19;
        if (r4 == null) goto L20;
        if (r4.equals(r53) == false) goto L20;
    L19:
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zza, Integer.valueOf(this.zzb), Integer.valueOf(this.zzc)});
    }

    public final String toString() {
        return String.format(Locale.US, "WebIconParcelable{%dx%d - %s}", new Object[]{Integer.valueOf(this.zzb), Integer.valueOf(this.zzc), this.zza});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r4, int r5) {
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeString(r4, 1, this.zza, false);
        SafeParcelWriter.writeInt(r4, 2, this.zzb);
        SafeParcelWriter.writeInt(r4, 3, this.zzc);
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }
}
