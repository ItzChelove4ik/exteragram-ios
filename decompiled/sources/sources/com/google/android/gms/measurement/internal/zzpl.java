package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* loaded from: classes5.dex */
public final class zzpl extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzpl> CREATOR = null;
    public final int zza;
    public final String zzb;
    public final long zzc;
    public final Long zzd;
    public final String zze;
    public final String zzf;
    public final Double zzg;

    static {
        CREATOR = new zzpm();
    }

    public zzpl(int r1, String r2, long r3, Long r5, Float r6, String r7, String r8, Double r9) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r5;
        if (r1 != 1) goto L7;
        if (r6 == null) goto L6;
        r9 = Double.valueOf(r6.doubleValue());
        goto L7
    L6:
        r9 = null;
    L7:
        this.zzg = r9;
        this.zze = r7;
        this.zzf = r8;
    }

    public zzpl(zzpn r7) {
        this(r7.zzc, r7.zzd, r7.zze, r7.zzb);
    }

    public zzpl(String r2, long r3, Object r5, String r6) {
        Preconditions.checkNotEmpty(r2);
        this.zza = 2;
        this.zzb = r2;
        this.zzc = r3;
        this.zzf = r6;
        if (r5 != null) goto L7;
        this.zzd = null;
        this.zzg = null;
        this.zze = null;
        return;
    L7:
        if ((r5 instanceof Long) == false) goto L11;
        this.zzd = (Long) r5;
        this.zzg = null;
        this.zze = null;
        return;
    L11:
        if ((r5 instanceof String) == false) goto L15;
        this.zzd = null;
        this.zzg = null;
        this.zze = (String) r5;
        return;
    L15:
        if ((r5 instanceof Double) == false) goto L18;
        this.zzd = null;
        this.zzg = (Double) r5;
        this.zze = null;
        return;
    L18:
        f$$ExternalSyntheticBUOutline1.m("User attribute given of un-supported type");
        throw null;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r1, int r2) {
        zzpm.zza(this, r1, r2);
    }

    public final Object zza() {
        Long r0 = this.zzd;
        if (r0 == null) goto L5;
        return r0;
    L5:
        Double r02 = this.zzg;
        if (r02 == null) goto L8;
        return r02;
    L8:
        String r1 = this.zze;
        if (r1 == null) goto L11;
        return r1;
    L11:
        return null;
    }
}
