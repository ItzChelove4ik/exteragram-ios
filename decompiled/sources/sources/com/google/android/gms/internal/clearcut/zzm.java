package com.google.android.gms.internal.clearcut;

import android.os.Parcel;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;

/* loaded from: classes4.dex */
public abstract class zzm extends zzb implements zzl {
    public zzm() {
        super("com.google.android.gms.clearcut.internal.IClearcutLoggerCallbacks");
    }

    @Override // com.google.android.gms.internal.clearcut.zzb
    public final boolean dispatchTransaction(int r1, Parcel r2, Parcel r3, int r4) {
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
        zzb((Status) zzc.zza(r2, Status.CREATOR), (com.google.android.gms.clearcut.zzc) zzc.zza(r2, com.google.android.gms.clearcut.zzc.CREATOR));
        return true;
    L6:
        zza((Status) zzc.zza(r2, Status.CREATOR), (com.google.android.gms.clearcut.zzc) zzc.zza(r2, com.google.android.gms.clearcut.zzc.CREATOR));
        return true;
    L7:
        zza((DataHolder) zzc.zza(r2, DataHolder.CREATOR));
        return true;
    L8:
        zza((Status) zzc.zza(r2, Status.CREATOR), (com.google.android.gms.clearcut.zze[]) r2.createTypedArray(com.google.android.gms.clearcut.zze.CREATOR));
        return true;
    L9:
        zzb((Status) zzc.zza(r2, Status.CREATOR), r2.readLong());
        return true;
    L10:
        zzc((Status) zzc.zza(r2, Status.CREATOR));
        return true;
    L11:
        zza((Status) zzc.zza(r2, Status.CREATOR), r2.readLong());
        return true;
    L12:
        zzb((Status) zzc.zza(r2, Status.CREATOR));
        return true;
    L13:
        zza((Status) zzc.zza(r2, Status.CREATOR));
        return true;
    }
}
