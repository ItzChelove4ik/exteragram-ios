package com.google.android.gms.cast.framework;

import android.os.Parcel;
import com.google.android.gms.cast.LaunchOptions;

/* loaded from: classes4.dex */
public abstract class zzae extends com.google.android.gms.internal.cast.zzb implements zzaf {
    public zzae() {
        super("com.google.android.gms.cast.framework.ICastConnectionController");
    }

    @Override // com.google.android.gms.internal.cast.zzb
    public final boolean zza(int r2, Parcel r3, Parcel r4, int r5) {
        if (r2 != 1) goto L5;
        String r22 = r3.readString();
        String r0 = r3.readString();
        com.google.android.gms.internal.cast.zzc.zzf(r3);
        zzb(r22, r0);
        r4.writeNoException();
    L19:
        return true;
    L5:
        if (r2 != 2) goto L7;
        String r23 = r3.readString();
        LaunchOptions r02 = (LaunchOptions) com.google.android.gms.internal.cast.zzc.zzb(r3, LaunchOptions.CREATOR);
        com.google.android.gms.internal.cast.zzc.zzf(r3);
        zzc(r23, r02);
        r4.writeNoException();
        goto L19
    L7:
        if (r2 != 3) goto L9;
        String r24 = r3.readString();
        com.google.android.gms.internal.cast.zzc.zzf(r3);
        zzd(r24);
        r4.writeNoException();
        goto L19
    L9:
        if (r2 != 4) goto L11;
        int r25 = r3.readInt();
        com.google.android.gms.internal.cast.zzc.zzf(r3);
        zze(r25);
        r4.writeNoException();
        goto L19
    L11:
        if (r2 == 5) goto L14;
        return false;
    L14:
        r4.writeNoException();
        r4.writeInt(12451000);
        goto L19
    }
}
