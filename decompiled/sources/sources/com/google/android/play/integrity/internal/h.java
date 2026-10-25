package com.google.android.play.integrity.internal;

import android.os.IBinder;
import android.os.IInterface;

/* loaded from: classes5.dex */
public abstract class h extends b implements i {
    public static i b(IBinder r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        IInterface r0 = r2.queryLocalInterface("com.google.android.play.core.integrity.protocol.IExpressIntegrityService");
        if ((r0 instanceof i) == false) goto L10;
        return (i) r0;
    L10:
        return new g(r2);
    }
}
