package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.IBinder;
import android.os.IInterface;

/* loaded from: classes4.dex */
public interface IAccountAccessor extends IInterface {

    public static abstract class Stub extends com.google.android.gms.internal.common.zzb implements IAccountAccessor {
        public static IAccountAccessor asInterface(IBinder r2) {
            if (r2 != null) goto L5;
            return null;
        L5:
            IInterface r0 = r2.queryLocalInterface("com.google.android.gms.common.internal.IAccountAccessor");
            if ((r0 instanceof IAccountAccessor) == false) goto L10;
            return (IAccountAccessor) r0;
        L10:
            return new zzt(r2);
        }
    }

    Account zzb();
}
