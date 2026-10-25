package com.google.android.gms.cast.framework.media.internal;

import android.os.Parcel;

/* loaded from: classes4.dex */
public abstract class zzh extends com.google.android.gms.internal.cast.zzb implements zzi {
    public zzh() {
        super("com.google.android.gms.cast.framework.media.internal.IFetchBitmapTaskProgressPublisher");
    }

    @Override // com.google.android.gms.internal.cast.zzb
    public final boolean zza(int r5, Parcel r6, Parcel r7, int r8) {
        if (r5 != 1) goto L5;
        long r0 = r6.readLong();
        long r2 = r6.readLong();
        com.google.android.gms.internal.cast.zzc.zzf(r6);
        zzb(r0, r2);
        r7.writeNoException();
    L10:
        return true;
    L5:
        if (r5 == 2) goto L8;
        return false;
    L8:
        r7.writeNoException();
        r7.writeInt(12451000);
        goto L10
    }
}
