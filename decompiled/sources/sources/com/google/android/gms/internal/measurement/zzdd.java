package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Objects;

/* loaded from: classes.dex */
public final class zzdd extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzdd> CREATOR = null;
    public final int zza;
    public final String zzb;
    public final Intent zzc;

    static {
        CREATOR = new zzde();
    }

    public zzdd(int r1, String r2, Intent r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    public static zzdd zza(Activity r3) {
        return new zzdd(r3.hashCode(), r3.getClass().getCanonicalName(), r3.getIntent());
    }

    public final boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof zzdd) == true) goto L8;
        return false;
    L8:
        zzdd r52 = (zzdd) r5;
        if (this.zza == r52.zza) goto L11;
    L15:
        return false;
    L11:
        if (Objects.equals(this.zzb, r52.zzb) == false) goto L15;
        if (Objects.equals(this.zzc, r52.zzc) == false) goto L15;
        return true;
    }

    public final int hashCode() {
        return this.zza;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r5, int r6) {
        int r0 = this.zza;
        int r1 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeInt(r5, 1, r0);
        SafeParcelWriter.writeString(r5, 2, this.zzb, false);
        SafeParcelWriter.writeParcelable(r5, 3, this.zzc, r6, false);
        SafeParcelWriter.finishObjectHeader(r5, r1);
    }
}
