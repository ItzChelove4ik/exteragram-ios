package com.google.android.gms.vision.face.internal.client;

import android.os.IBinder;
import android.os.IInterface;

/* loaded from: classes5.dex */
public abstract class zzl extends com.google.android.gms.internal.vision.zza implements zzi {
    public static zzi asInterface(IBinder r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        IInterface r0 = r2.queryLocalInterface("com.google.android.gms.vision.face.internal.client.INativeFaceDetectorCreator");
        if ((r0 instanceof zzi) == false) goto L10;
        return (zzi) r0;
    L10:
        return new zzk(r2);
    }
}
