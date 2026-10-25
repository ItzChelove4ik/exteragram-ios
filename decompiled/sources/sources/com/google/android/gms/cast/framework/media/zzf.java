package com.google.android.gms.cast.framework.media;

import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

/* loaded from: classes4.dex */
public abstract class zzf extends com.google.android.gms.internal.cast.zzb implements zzg {
    public zzf() {
        super("com.google.android.gms.cast.framework.media.INotificationActionsProvider");
    }

    @Override // com.google.android.gms.internal.cast.zzb
    public final boolean zza(int r1, Parcel r2, Parcel r3, int r4) {
        if (r1 != 1) goto L5;
        r3.writeNoException();
        r3.writeInt(12451000);
    L16:
        return true;
    L5:
        if (r1 != 2) goto L7;
        IObjectWrapper r0 = zze();
        r3.writeNoException();
        com.google.android.gms.internal.cast.zzc.zze(r3, r0);
        goto L16
    L7:
        if (r1 != 3) goto L9;
        List r02 = zzf();
        r3.writeNoException();
        r3.writeTypedList(r02);
        goto L16
    L9:
        if (r1 == 4) goto L12;
        return false;
    L12:
        int[] r03 = zzg();
        r3.writeNoException();
        r3.writeIntArray(r03);
        goto L16
    }
}
