package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes5.dex */
public final class zzs extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzs> CREATOR = null;
    public final boolean zza;
    public final List zzb;

    static {
        CREATOR = new zzt();
    }

    public zzs(boolean r1, List r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    public final boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L18:
        return false;
    L8:
        if (zzs.class != r5.getClass()) goto L18;
        zzs r52 = (zzs) r5;
        if (this.zza != r52.zza) goto L18;
        List r4 = this.zzb;
        List r53 = r52.zzb;
        if (r4 == r53) goto L17;
        if (r4 == null) goto L18;
        if (r4.equals(r53) == false) goto L18;
    L17:
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Boolean.valueOf(this.zza), this.zzb});
    }

    public final String toString() {
        return "AppWearDetailsParcelable{isWatchface=" + this.zza + ", watchfaceCategories=" + String.valueOf(this.zzb) + "}";
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r3, int r4) {
        int r42 = SafeParcelWriter.beginObjectHeader(r3);
        SafeParcelWriter.writeBoolean(r3, 1, this.zza);
        SafeParcelWriter.writeStringList(r3, 2, this.zzb, false);
        SafeParcelWriter.finishObjectHeader(r3, r42);
    }
}
