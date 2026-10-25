package com.google.android.gms.cast.framework;

import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes4.dex */
public abstract class zzay extends com.google.android.gms.internal.cast.zzb implements zzaz {
    public zzay() {
        super("com.google.android.gms.cast.framework.ISessionManagerListener");
    }

    @Override // com.google.android.gms.internal.cast.zzb
    public final boolean zza(int r1, Parcel r2, Parcel r3, int r4) {
        switch(r1) {
            case 1: goto L15;
            case 2: goto L14;
            case 3: goto L13;
            case 4: goto L12;
            case 5: goto L11;
            case 6: goto L10;
            case 7: goto L9;
            case 8: goto L8;
            case 9: goto L7;
            case 10: goto L6;
            case 11: goto L5;
            default: goto L3;
        };
    L3:
        return false;
    L5:
        r3.writeNoException();
        r3.writeInt(12451000);
        return true;
    L6:
        IObjectWrapper r12 = IObjectWrapper.Stub.asInterface(r2.readStrongBinder());
        int r42 = r2.readInt();
        com.google.android.gms.internal.cast.zzc.zzf(r2);
        zzk(r12, r42);
        r3.writeNoException();
        return true;
    L7:
        IObjectWrapper r13 = IObjectWrapper.Stub.asInterface(r2.readStrongBinder());
        int r43 = r2.readInt();
        com.google.android.gms.internal.cast.zzc.zzf(r2);
        zzj(r13, r43);
        r3.writeNoException();
        return true;
    L8:
        IObjectWrapper r14 = IObjectWrapper.Stub.asInterface(r2.readStrongBinder());
        boolean r44 = com.google.android.gms.internal.cast.zzc.zza(r2);
        com.google.android.gms.internal.cast.zzc.zzf(r2);
        zzi(r14, r44);
        r3.writeNoException();
        return true;
    L9:
        IObjectWrapper r15 = IObjectWrapper.Stub.asInterface(r2.readStrongBinder());
        String r45 = r2.readString();
        com.google.android.gms.internal.cast.zzc.zzf(r2);
        zzh(r15, r45);
        r3.writeNoException();
        return true;
    L10:
        IObjectWrapper r16 = IObjectWrapper.Stub.asInterface(r2.readStrongBinder());
        int r46 = r2.readInt();
        com.google.android.gms.internal.cast.zzc.zzf(r2);
        zzg(r16, r46);
        r3.writeNoException();
        return true;
    L11:
        IObjectWrapper r17 = IObjectWrapper.Stub.asInterface(r2.readStrongBinder());
        com.google.android.gms.internal.cast.zzc.zzf(r2);
        zzf(r17);
        r3.writeNoException();
        return true;
    L12:
        IObjectWrapper r18 = IObjectWrapper.Stub.asInterface(r2.readStrongBinder());
        int r47 = r2.readInt();
        com.google.android.gms.internal.cast.zzc.zzf(r2);
        zze(r18, r47);
        r3.writeNoException();
        return true;
    L13:
        IObjectWrapper r19 = IObjectWrapper.Stub.asInterface(r2.readStrongBinder());
        String r48 = r2.readString();
        com.google.android.gms.internal.cast.zzc.zzf(r2);
        zzd(r19, r48);
        r3.writeNoException();
        return true;
    L14:
        IObjectWrapper r110 = IObjectWrapper.Stub.asInterface(r2.readStrongBinder());
        com.google.android.gms.internal.cast.zzc.zzf(r2);
        zzc(r110);
        r3.writeNoException();
        return true;
    L15:
        IObjectWrapper r0 = zzb();
        r3.writeNoException();
        com.google.android.gms.internal.cast.zzc.zze(r3, r0);
        return true;
    }
}
