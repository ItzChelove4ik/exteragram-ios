package com.google.android.gms.cast.framework.media;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public final class zze extends com.google.android.gms.internal.cast.zza implements zzg {
    public zze(IBinder r2) {
        super(r2, "com.google.android.gms.cast.framework.media.INotificationActionsProvider");
    }

    @Override // com.google.android.gms.cast.framework.media.zzg
    public final IObjectWrapper zze() {
        throw null;
    }

    @Override // com.google.android.gms.cast.framework.media.zzg
    public final List zzf() {
        Parcel r2 = zzb(3, zza());
        ArrayList r0 = r2.createTypedArrayList(NotificationAction.CREATOR);
        r2.recycle();
        return r0;
    }

    @Override // com.google.android.gms.cast.framework.media.zzg
    public final int[] zzg() {
        Parcel r2 = zzb(4, zza());
        int[] r0 = r2.createIntArray();
        r2.recycle();
        return r0;
    }
}
