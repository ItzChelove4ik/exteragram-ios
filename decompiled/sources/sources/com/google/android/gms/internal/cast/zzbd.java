package com.google.android.gms.internal.cast;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes4.dex */
public abstract class zzbd extends zzb implements zzbe {
    public zzbd() {
        super("com.google.android.gms.cast.framework.internal.IMediaRouter");
    }

    @Override // com.google.android.gms.internal.cast.zzb
    public final boolean zza(int r3, Parcel r4, Parcel r5, int r6) {
        switch(r3) {
            case 1: goto L18;
            case 2: goto L17;
            case 3: goto L16;
            case 4: goto L15;
            case 5: goto L14;
            case 6: goto L13;
            case 7: goto L12;
            case 8: goto L11;
            case 9: goto L10;
            case 10: goto L9;
            case 11: goto L8;
            case 12: goto L7;
            case 13: goto L6;
            case 14: goto L5;
            default: goto L3;
        };
    L3:
        return false;
    L5:
        String r32 = r4.readString();
        zzc.zzf(r4);
        zzn(r32);
        r5.writeNoException();
        return true;
    L6:
        int r33 = r4.readInt();
        zzc.zzf(r4);
        zzm(r33);
        r5.writeNoException();
        return true;
    L7:
        boolean r2 = zzl();
        r5.writeNoException();
        int r34 = zzc.$r8$clinit;
        r5.writeInt(r2 ? 1 : 0);
        return true;
    L8:
        zzk();
        r5.writeNoException();
        return true;
    L9:
        r5.writeNoException();
        r5.writeInt(12451000);
        return true;
    L10:
        String r22 = zzj();
        r5.writeNoException();
        r5.writeString(r22);
        return true;
    L11:
        String r35 = r4.readString();
        zzc.zzf(r4);
        Bundle r23 = zzi(r35);
        r5.writeNoException();
        zzc.zzd(r5, r23);
        return true;
    L12:
        boolean r24 = zzh();
        r5.writeNoException();
        int r36 = zzc.$r8$clinit;
        r5.writeInt(r24 ? 1 : 0);
        return true;
    L13:
        zzg();
        r5.writeNoException();
        return true;
    L14:
        String r37 = r4.readString();
        zzc.zzf(r4);
        zzf(r37);
        r5.writeNoException();
        return true;
    L15:
        Bundle r38 = (Bundle) zzc.zzb(r4, Bundle.CREATOR);
        int r62 = r4.readInt();
        zzc.zzf(r4);
        boolean r25 = zze(r38, r62);
        r5.writeNoException();
        r5.writeInt(r25 ? 1 : 0);
        return true;
    L16:
        Bundle r39 = (Bundle) zzc.zzb(r4, Bundle.CREATOR);
        zzc.zzf(r4);
        zzd(r39);
        r5.writeNoException();
        return true;
    L17:
        Bundle r310 = (Bundle) zzc.zzb(r4, Bundle.CREATOR);
        int r63 = r4.readInt();
        zzc.zzf(r4);
        zzc(r310, r63);
        r5.writeNoException();
        return true;
    L18:
        Bundle r311 = (Bundle) zzc.zzb(r4, Bundle.CREATOR);
        IBinder r64 = r4.readStrongBinder();
        if (r64 != null) goto L21;
        zzbg r65 = null;
    L25:
        zzc.zzf(r4);
        zzb(r311, r65);
        r5.writeNoException();
        return true;
    L21:
        IInterface r0 = r64.queryLocalInterface("com.google.android.gms.cast.framework.internal.IMediaRouterCallback");
        if ((r0 instanceof zzbg) == false) goto L24;
        r65 = (zzbg) r0;
        goto L25
    L24:
        r65 = new zzbf(r64);
        goto L25
    }
}
