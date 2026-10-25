package com.google.android.gms.internal.mlkit_vision_label;

import android.os.IBinder;
import android.os.IInterface;

/* loaded from: classes4.dex */
public abstract class zzoh extends zzb implements zzoi {
    public static zzoi zza(IBinder r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        IInterface r0 = r2.queryLocalInterface("com.google.mlkit.vision.label.aidls.IImageLabelerCreator");
        if ((r0 instanceof zzoi) == false) goto L10;
        return (zzoi) r0;
    L10:
        return new zzog(r2);
    }
}
