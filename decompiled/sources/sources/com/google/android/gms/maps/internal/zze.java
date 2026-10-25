package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zzj;
import com.google.android.gms.internal.maps.zzk;
import com.google.android.gms.maps.GoogleMapOptions;

/* loaded from: classes5.dex */
public final class zze extends com.google.android.gms.internal.maps.zza implements zzf {
    public zze(IBinder r2) {
        super(r2, "com.google.android.gms.maps.internal.ICreator");
    }

    @Override // com.google.android.gms.maps.internal.zzf
    public final int zzd() {
        Parcel r2 = zzJ(9, zza());
        int r0 = r2.readInt();
        r2.recycle();
        return r0;
    }

    @Override // com.google.android.gms.maps.internal.zzf
    public final ICameraUpdateFactoryDelegate zze() {
        Parcel r3 = zzJ(4, zza());
        IBinder r0 = r3.readStrongBinder();
        if (r0 != null) goto L5;
        ICameraUpdateFactoryDelegate r02 = null;
    L9:
        r3.recycle();
        return r02;
    L5:
        IInterface r1 = r0.queryLocalInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
        if ((r1 instanceof ICameraUpdateFactoryDelegate) == false) goto L8;
        r02 = (ICameraUpdateFactoryDelegate) r1;
        goto L9
    L8:
        r02 = new zzb(r0);
        goto L9
    }

    @Override // com.google.android.gms.maps.internal.zzf
    public final IMapViewDelegate zzg(IObjectWrapper r2, GoogleMapOptions r3) {
        Parcel r0 = zza();
        com.google.android.gms.internal.maps.zzc.zzg(r0, r2);
        com.google.android.gms.internal.maps.zzc.zze(r0, r3);
        Parcel r1 = zzJ(3, r0);
        IBinder r22 = r1.readStrongBinder();
        if (r22 != null) goto L5;
        IMapViewDelegate r23 = null;
    L9:
        r1.recycle();
        return r23;
    L5:
        IInterface r32 = r22.queryLocalInterface("com.google.android.gms.maps.internal.IMapViewDelegate");
        if ((r32 instanceof IMapViewDelegate) == false) goto L8;
        r23 = (IMapViewDelegate) r32;
        goto L9
    L8:
        r23 = new zzl(r22);
        goto L9
    }

    @Override // com.google.android.gms.maps.internal.zzf
    public final zzk zzj() {
        Parcel r2 = zzJ(5, zza());
        zzk r0 = zzj.zzb(r2.readStrongBinder());
        r2.recycle();
        return r0;
    }

    @Override // com.google.android.gms.maps.internal.zzf
    public final void zzl(IObjectWrapper r1, int r2) {
        Parcel r22 = zza();
        com.google.android.gms.internal.maps.zzc.zzg(r22, r1);
        r22.writeInt(20000000);
        zzc(6, r22);
    }

    @Override // com.google.android.gms.maps.internal.zzf
    public final void zzm(IObjectWrapper r2, int r3) {
        Parcel r0 = zza();
        com.google.android.gms.internal.maps.zzc.zzg(r0, r2);
        r0.writeInt(r3);
        zzc(10, r0);
    }

    @Override // com.google.android.gms.maps.internal.zzf
    public final void zzn(IObjectWrapper r2) {
        Parcel r0 = zza();
        com.google.android.gms.internal.maps.zzc.zzg(r0, r2);
        zzc(11, r0);
    }
}
