package com.google.android.gms.internal.mlkit_vision_label;

import android.os.IBinder;
import android.os.IInterface;

/* loaded from: classes4.dex */
public abstract class zzf extends zzb implements zzg {
    public static zzg zza(IBinder r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        IInterface r0 = r2.queryLocalInterface("com.google.android.gms.vision.label.internal.client.INativeImageLabelerCreator");
        if ((r0 instanceof zzg) == false) goto L10;
        return (zzg) r0;
    L10:
        return new zze(r2);
    }
}
