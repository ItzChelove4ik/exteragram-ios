package com.google.android.gms.internal.cast;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.Map;

/* loaded from: classes4.dex */
public final class zzbb extends zza implements zzbc {
    public zzbb(IBinder r2) {
        super(r2, "com.google.android.gms.cast.framework.internal.ICastDynamiteModule");
    }

    @Override // com.google.android.gms.internal.cast.zzbc
    public final int zze() {
        Parcel r2 = zzb(8, zza());
        int r0 = r2.readInt();
        r2.recycle();
        return r0;
    }

    @Override // com.google.android.gms.internal.cast.zzbc
    public final com.google.android.gms.cast.framework.zzai zzf(IObjectWrapper r2, CastOptions r3, zzbe r4, Map r5) {
        Parcel r0 = zza();
        zzc.zze(r0, r2);
        zzc.zzc(r0, r3);
        zzc.zze(r0, r4);
        r0.writeMap(r5);
        Parcel r1 = zzb(1, r0);
        com.google.android.gms.cast.framework.zzai r22 = com.google.android.gms.cast.framework.zzah.zzb(r1.readStrongBinder());
        r1.recycle();
        return r22;
    }

    @Override // com.google.android.gms.internal.cast.zzbc
    public final com.google.android.gms.cast.framework.zzav zzg(String r2, String r3, com.google.android.gms.cast.framework.zzbd r4) {
        Parcel r0 = zza();
        r0.writeString(r2);
        r0.writeString(r3);
        zzc.zze(r0, r4);
        Parcel r1 = zzb(2, r0);
        com.google.android.gms.cast.framework.zzav r22 = com.google.android.gms.cast.framework.zzau.zzb(r1.readStrongBinder());
        r1.recycle();
        return r22;
    }

    @Override // com.google.android.gms.internal.cast.zzbc
    public final com.google.android.gms.cast.framework.zzal zzh(CastOptions r2, IObjectWrapper r3, com.google.android.gms.cast.framework.zzaf r4) {
        Parcel r0 = zza();
        zzc.zzc(r0, r2);
        zzc.zze(r0, r3);
        zzc.zze(r0, r4);
        Parcel r1 = zzb(3, r0);
        com.google.android.gms.cast.framework.zzal r22 = com.google.android.gms.cast.framework.zzak.zzb(r1.readStrongBinder());
        r1.recycle();
        return r22;
    }

    @Override // com.google.android.gms.internal.cast.zzbc
    public final com.google.android.gms.cast.framework.zzas zzi(IObjectWrapper r2, IObjectWrapper r3, IObjectWrapper r4) {
        Parcel r0 = zza();
        zzc.zze(r0, r2);
        zzc.zze(r0, r3);
        zzc.zze(r0, r4);
        Parcel r1 = zzb(5, r0);
        com.google.android.gms.cast.framework.zzas r22 = com.google.android.gms.cast.framework.zzar.zzb(r1.readStrongBinder());
        r1.recycle();
        return r22;
    }

    @Override // com.google.android.gms.internal.cast.zzbc
    public final com.google.android.gms.cast.framework.media.internal.zzg zzj(IObjectWrapper r1, com.google.android.gms.cast.framework.media.internal.zzi r2, int r3, int r4, boolean r5, long r6, int r8, int r9, int r10) {
        Parcel r52 = zza();
        zzc.zze(r52, r1);
        zzc.zze(r52, r2);
        r52.writeInt(r3);
        r52.writeInt(r4);
        r52.writeInt(0);
        r52.writeLong(2097152);
        r52.writeInt(5);
        r52.writeInt(333);
        r52.writeInt(10000);
        Parcel r0 = zzb(6, r52);
        com.google.android.gms.cast.framework.media.internal.zzg r12 = com.google.android.gms.cast.framework.media.internal.zzf.zzb(r0.readStrongBinder());
        r0.recycle();
        return r12;
    }

    @Override // com.google.android.gms.internal.cast.zzbc
    public final com.google.android.gms.cast.framework.media.internal.zzg zzk(IObjectWrapper r1, IObjectWrapper r2, com.google.android.gms.cast.framework.media.internal.zzi r3, int r4, int r5, boolean r6, long r7, int r9, int r10, int r11) {
        Parcel r62 = zza();
        zzc.zze(r62, r1);
        zzc.zze(r62, r2);
        zzc.zze(r62, r3);
        r62.writeInt(r4);
        r62.writeInt(r5);
        r62.writeInt(0);
        r62.writeLong(2097152);
        r62.writeInt(5);
        r62.writeInt(333);
        r62.writeInt(10000);
        Parcel r0 = zzb(7, r62);
        com.google.android.gms.cast.framework.media.internal.zzg r12 = com.google.android.gms.cast.framework.media.internal.zzf.zzb(r0.readStrongBinder());
        r0.recycle();
        return r12;
    }
}
