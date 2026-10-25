package com.google.android.gms.internal.measurement;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzkt extends zzbl implements IInterface {
    public zzkt(IBinder r2) {
        super(r2, "com.google.android.gms.phenotype.internal.IPhenotypeService");
    }

    public final void zze(zzks r1, String r2, int r3, String[] r4, byte[] r5) {
        Parcel r32 = zza();
        zzbn.zzd(r32, r1);
        r32.writeString(r2);
        r32.writeInt(0);
        r32.writeStringArray(r4);
        r32.writeByteArray(null);
        zzc(1, r32);
    }

    public final void zzf(zzks r1, String r2, String r3, String r4) {
        Parcel r32 = zza();
        zzbn.zzd(r32, r1);
        r32.writeString(r2);
        r32.writeString(_UrlKt.FRAGMENT_ENCODE_SET);
        r32.writeString(null);
        zzc(11, r32);
    }

    public final void zzg(zzks r2, String r3) {
        Parcel r0 = zza();
        zzbn.zzd(r0, r2);
        r0.writeString(r3);
        zzc(5, r0);
    }

    public final void zzh(zzks r2, byte[] r3) {
        Parcel r0 = zza();
        zzbn.zzd(r0, r2);
        r0.writeByteArray(r3);
        zzc(31, r0);
    }

    public final void zzi(zzkq r2) {
        Parcel r0 = zza();
        zzbn.zzd(r0, r2);
        zzc(27, r0);
    }

    public final void zzj(String r2, zzko r3) {
        Parcel r0 = zza();
        r0.writeString(r2);
        zzbn.zzd(r0, r3);
        zzc(28, r0);
    }
}
