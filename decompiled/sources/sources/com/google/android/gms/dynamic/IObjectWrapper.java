package com.google.android.gms.dynamic;

import android.os.IBinder;
import android.os.IInterface;

/* loaded from: classes.dex */
public interface IObjectWrapper extends IInterface {

    public static abstract class Stub extends com.google.android.gms.internal.common.zzb implements IObjectWrapper {
        public Stub() {
            super("com.google.android.gms.dynamic.IObjectWrapper");
        }

        public static IObjectWrapper asInterface(IBinder r2) {
            if (r2 != null) goto L5;
            return null;
        L5:
            IInterface r0 = r2.queryLocalInterface("com.google.android.gms.dynamic.IObjectWrapper");
            if ((r0 instanceof IObjectWrapper) == false) goto L10;
            return (IObjectWrapper) r0;
        L10:
            return new zzb(r2);
        }
    }
}
