package com.google.android.gms.cast.framework;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes4.dex */
public abstract class zzbc extends com.google.android.gms.internal.cast.zzb implements zzbd {
    public zzbc() {
        super("com.google.android.gms.cast.framework.ISessionProxy");
    }

    @Override // com.google.android.gms.internal.cast.zzb
    public final boolean zza(int r1, Parcel r2, Parcel r3, int r4) {
        switch(r1) {
            case 1: goto L13;
            case 2: goto L12;
            case 3: goto L11;
            case 4: goto L10;
            case 5: goto L9;
            case 6: goto L8;
            case 7: goto L7;
            case 8: goto L6;
            case 9: goto L5;
            default: goto L3;
        };
    L3:
        return false;
    L5:
        Bundle r12 = (Bundle) com.google.android.gms.internal.cast.zzc.zzb(r2, Bundle.CREATOR);
        com.google.android.gms.internal.cast.zzc.zzf(r2);
        zzh(r12);
        r3.writeNoException();
        return true;
    L6:
        Bundle r13 = (Bundle) com.google.android.gms.internal.cast.zzc.zzb(r2, Bundle.CREATOR);
        com.google.android.gms.internal.cast.zzc.zzf(r2);
        zze(r13);
        r3.writeNoException();
        return true;
    L7:
        Bundle r14 = (Bundle) com.google.android.gms.internal.cast.zzc.zzb(r2, Bundle.CREATOR);
        com.google.android.gms.internal.cast.zzc.zzf(r2);
        zzc(r14);
        r3.writeNoException();
        return true;
    L8:
        r3.writeNoException();
        r3.writeInt(12451000);
        return true;
    L9:
        long r0 = zzi();
        r3.writeNoException();
        r3.writeLong(r0);
        return true;
    L10:
        boolean r15 = com.google.android.gms.internal.cast.zzc.zza(r2);
        com.google.android.gms.internal.cast.zzc.zzf(r2);
        zzg(r15);
        r3.writeNoException();
        return true;
    L11:
        Bundle r16 = (Bundle) com.google.android.gms.internal.cast.zzc.zzb(r2, Bundle.CREATOR);
        com.google.android.gms.internal.cast.zzc.zzf(r2);
        zzf(r16);
        r3.writeNoException();
        return true;
    L12:
        Bundle r17 = (Bundle) com.google.android.gms.internal.cast.zzc.zzb(r2, Bundle.CREATOR);
        com.google.android.gms.internal.cast.zzc.zzf(r2);
        zzd(r17);
        r3.writeNoException();
        return true;
    L13:
        IObjectWrapper r02 = zzb();
        r3.writeNoException();
        com.google.android.gms.internal.cast.zzc.zze(r3, r02);
        return true;
    }
}
