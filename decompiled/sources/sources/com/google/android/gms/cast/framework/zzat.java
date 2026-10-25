package com.google.android.gms.cast.framework;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes4.dex */
public final class zzat extends com.google.android.gms.internal.cast.zza implements zzav {
    public zzat(IBinder r2) {
        super(r2, "com.google.android.gms.cast.framework.ISession");
    }

    @Override // com.google.android.gms.cast.framework.zzav
    public final int zze() {
        Parcel r2 = zzb(17, zza());
        int r0 = r2.readInt();
        r2.recycle();
        return r0;
    }

    @Override // com.google.android.gms.cast.framework.zzav
    public final IObjectWrapper zzf() {
        Parcel r2 = zzb(1, zza());
        IObjectWrapper r0 = IObjectWrapper.Stub.asInterface(r2.readStrongBinder());
        r2.recycle();
        return r0;
    }

    @Override // com.google.android.gms.cast.framework.zzav
    public final String zzg() {
        Parcel r2 = zzb(2, zza());
        String r0 = r2.readString();
        r2.recycle();
        return r0;
    }

    @Override // com.google.android.gms.cast.framework.zzav
    public final String zzh() {
        Parcel r2 = zzb(3, zza());
        String r0 = r2.readString();
        r2.recycle();
        return r0;
    }

    @Override // com.google.android.gms.cast.framework.zzav
    public final boolean zzi() {
        Parcel r2 = zzb(5, zza());
        boolean r0 = com.google.android.gms.internal.cast.zzc.zza(r2);
        r2.recycle();
        return r0;
    }

    @Override // com.google.android.gms.cast.framework.zzav
    public final boolean zzj() {
        Parcel r2 = zzb(6, zza());
        boolean r0 = com.google.android.gms.internal.cast.zzc.zza(r2);
        r2.recycle();
        return r0;
    }

    @Override // com.google.android.gms.cast.framework.zzav
    public final boolean zzk() {
        Parcel r2 = zzb(7, zza());
        boolean r0 = com.google.android.gms.internal.cast.zzc.zza(r2);
        r2.recycle();
        return r0;
    }

    @Override // com.google.android.gms.cast.framework.zzav
    public final boolean zzl() {
        Parcel r2 = zzb(8, zza());
        boolean r0 = com.google.android.gms.internal.cast.zzc.zza(r2);
        r2.recycle();
        return r0;
    }

    @Override // com.google.android.gms.cast.framework.zzav
    public final boolean zzm() {
        Parcel r2 = zzb(9, zza());
        boolean r0 = com.google.android.gms.internal.cast.zzc.zza(r2);
        r2.recycle();
        return r0;
    }

    @Override // com.google.android.gms.cast.framework.zzav
    public final boolean zzn() {
        Parcel r2 = zzb(10, zza());
        boolean r0 = com.google.android.gms.internal.cast.zzc.zza(r2);
        r2.recycle();
        return r0;
    }

    @Override // com.google.android.gms.cast.framework.zzav
    public final int zzo() {
        Parcel r2 = zzb(18, zza());
        int r0 = r2.readInt();
        r2.recycle();
        return r0;
    }

    @Override // com.google.android.gms.cast.framework.zzav
    public final void zzp(String r2) {
        Parcel r0 = zza();
        r0.writeString(r2);
        zzc(11, r0);
    }

    @Override // com.google.android.gms.cast.framework.zzav
    public final void zzq(int r2) {
        Parcel r0 = zza();
        r0.writeInt(r2);
        zzc(12, r0);
    }

    @Override // com.google.android.gms.cast.framework.zzav
    public final void zzr(int r2) {
        Parcel r0 = zza();
        r0.writeInt(r2);
        zzc(13, r0);
    }

    @Override // com.google.android.gms.cast.framework.zzav
    public final void zzs(boolean r3) {
        Parcel r0 = zza();
        int r1 = com.google.android.gms.internal.cast.zzc.$r8$clinit;
        r0.writeInt(r3 ? 1 : 0);
        zzc(14, r0);
    }

    @Override // com.google.android.gms.cast.framework.zzav
    public final void zzt(int r2) {
        Parcel r0 = zza();
        r0.writeInt(r2);
        zzc(15, r0);
    }

    @Override // com.google.android.gms.cast.framework.zzav
    public final void zzu(int r2) {
        Parcel r0 = zza();
        r0.writeInt(r2);
        zzc(16, r0);
    }
}
