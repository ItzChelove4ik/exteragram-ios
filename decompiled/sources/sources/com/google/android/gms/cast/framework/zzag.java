package com.google.android.gms.cast.framework;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import java.util.Map;

/* loaded from: classes4.dex */
public final class zzag extends com.google.android.gms.internal.cast.zza implements zzai {
    public zzag(IBinder r2) {
        super(r2, "com.google.android.gms.cast.framework.ICastContext");
    }

    @Override // com.google.android.gms.cast.framework.zzai
    public final Bundle zze() {
        Parcel r2 = zzb(1, zza());
        Bundle r0 = (Bundle) com.google.android.gms.internal.cast.zzc.zzb(r2, Bundle.CREATOR);
        r2.recycle();
        return r0;
    }

    @Override // com.google.android.gms.cast.framework.zzai
    public final void zzf(zzad r2) {
        Parcel r0 = zza();
        com.google.android.gms.internal.cast.zzc.zze(r0, r2);
        zzc(3, r0);
    }

    @Override // com.google.android.gms.cast.framework.zzai
    public final zzax zzg() {
        Parcel r3 = zzb(5, zza());
        IBinder r0 = r3.readStrongBinder();
        if (r0 != null) goto L5;
        zzax r02 = null;
    L9:
        r3.recycle();
        return r02;
    L5:
        IInterface r1 = r0.queryLocalInterface("com.google.android.gms.cast.framework.ISessionManager");
        if ((r1 instanceof zzax) == false) goto L8;
        r02 = (zzax) r1;
        goto L9
    L8:
        r02 = new zzaw(r0);
        goto L9
    }

    @Override // com.google.android.gms.cast.framework.zzai
    public final zzap zzh() {
        Parcel r3 = zzb(6, zza());
        IBinder r0 = r3.readStrongBinder();
        if (r0 != null) goto L5;
        zzap r02 = null;
    L9:
        r3.recycle();
        return r02;
    L5:
        IInterface r1 = r0.queryLocalInterface("com.google.android.gms.cast.framework.IDiscoveryManager");
        if ((r1 instanceof zzap) == false) goto L8;
        r02 = (zzap) r1;
        goto L9
    L8:
        r02 = new zzao(r0);
        goto L9
    }

    @Override // com.google.android.gms.cast.framework.zzai
    public final void zzi(String r2, Map r3) {
        Parcel r0 = zza();
        r0.writeString(r2);
        r0.writeMap(r3);
        zzc(11, r0);
    }
}
