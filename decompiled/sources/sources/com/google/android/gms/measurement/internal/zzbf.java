package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Iterator;

/* loaded from: classes5.dex */
public final class zzbf extends AbstractSafeParcelable implements Iterable<String> {
    public static final Parcelable.Creator<zzbf> CREATOR = null;
    private final Bundle zza;

    static {
        CREATOR = new zzbg();
    }

    public zzbf(Bundle r1) {
        this.zza = r1;
    }

    @Override // java.lang.Iterable
    public final Iterator<String> iterator() {
        return new zzbe(this);
    }

    public final String toString() {
        return this.zza.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r3, int r4) {
        int r42 = SafeParcelWriter.beginObjectHeader(r3);
        SafeParcelWriter.writeBundle(r3, 2, zzf(), false);
        SafeParcelWriter.finishObjectHeader(r3, r42);
    }

    public final Object zza(String r1) {
        return this.zza.get(r1);
    }

    public final Long zzb(String r1) {
        return Long.valueOf(this.zza.getLong(r1));
    }

    public final Double zzc(String r1) {
        return Double.valueOf(this.zza.getDouble("value"));
    }

    public final String zzd(String r1) {
        return this.zza.getString(r1);
    }

    public final int zze() {
        return this.zza.size();
    }

    public final Bundle zzf() {
        return new Bundle(this.zza);
    }

    public final /* synthetic */ Bundle zzg() {
        return this.zza;
    }
}
