package com.google.android.gms.wearable.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.internal.wearable.zzb;
import com.google.android.gms.internal.wearable.zzc;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public abstract class zzez extends zzb implements zzfa {
    public zzez() {
        super("com.google.android.gms.wearable.internal.IWearableListener");
    }

    @Override // com.google.android.gms.internal.wearable.zzb
    public final boolean zza(int r2, Parcel r3, Parcel r4, int r5) {
        if (r2 != 13) goto L5;
        zzfx r22 = (zzfx) zzc.zza(r3, zzfx.CREATOR);
        IBinder r42 = r3.readStrongBinder();
        if (r42 != null) goto L22;
        zzev r43 = null;
    L26:
        zzc.zzb(r3);
        zzl(r22, r43);
        return true;
    L22:
        IInterface r52 = r42.queryLocalInterface("com.google.android.gms.wearable.internal.IRpcResponseCallback");
        if ((r52 instanceof zzev) == false) goto L25;
        r43 = (zzev) r52;
        goto L26
    L25:
        r43 = new zzev(r42);
        goto L26
    L5:
        if (r2 == 14) goto L18;
        switch(r2) {
            case 1: goto L17;
            case 2: goto L16;
            case 3: goto L15;
            case 4: goto L14;
            case 5: goto L13;
            case 6: goto L12;
            case 7: goto L11;
            case 8: goto L10;
            case 9: goto L9;
            default: goto L7;
        };
    L7:
        return false;
    L9:
        zzi r23 = (zzi) zzc.zza(r3, zzi.CREATOR);
        zzc.zzb(r3);
        zzf(r23);
        return true;
    L10:
        zzao r24 = (zzao) zzc.zza(r3, zzao.CREATOR);
        zzc.zzb(r3);
        zzc(r24);
        return true;
    L11:
        zzbf r25 = (zzbf) zzc.zza(r3, zzbf.CREATOR);
        zzc.zzb(r3);
        zzb(r25);
        return true;
    L12:
        zzl r26 = (zzl) zzc.zza(r3, zzl.CREATOR);
        zzc.zzb(r3);
        zzh(r26);
        return true;
    L13:
        ArrayList r27 = r3.createTypedArrayList(zzgm.CREATOR);
        zzc.zzb(r3);
        zzd(r27);
        return true;
    L14:
        zzgm r28 = (zzgm) zzc.zza(r3, zzgm.CREATOR);
        zzc.zzb(r3);
        zzj(r28);
        return true;
    L15:
        zzgm r29 = (zzgm) zzc.zza(r3, zzgm.CREATOR);
        zzc.zzb(r3);
        zzi(r29);
        return true;
    L16:
        zzfx r210 = (zzfx) zzc.zza(r3, zzfx.CREATOR);
        zzc.zzb(r3);
        zzg(r210);
        return true;
    L17:
        DataHolder r211 = (DataHolder) zzc.zza(r3, DataHolder.CREATOR);
        zzc.zzb(r3);
        zze(r211);
        return true;
    L18:
        zzcf r1 = (zzcf) zzc.zza(r3, zzcf.CREATOR);
        zzc.zzb(r3);
        return true;
    }
}
