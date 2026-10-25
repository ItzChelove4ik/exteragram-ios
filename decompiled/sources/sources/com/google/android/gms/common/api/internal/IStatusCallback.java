package com.google.android.gms.common.api.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.base.zab;

/* loaded from: classes4.dex */
public interface IStatusCallback extends IInterface {

    public static abstract class Stub extends zab implements IStatusCallback {
        public Stub() {
            super("com.google.android.gms.common.api.internal.IStatusCallback");
        }

        public static IStatusCallback asInterface(IBinder r2) {
            if (r2 != null) goto L5;
            return null;
        L5:
            IInterface r0 = r2.queryLocalInterface("com.google.android.gms.common.api.internal.IStatusCallback");
            if ((r0 instanceof IStatusCallback) == false) goto L10;
            return (IStatusCallback) r0;
        L10:
            return new zabt(r2);
        }

        @Override // com.google.android.gms.internal.base.zab
        public final boolean zaa(int r1, Parcel r2, Parcel r3, int r4) {
            if (r1 != 1) goto L6;
            Status r12 = (Status) com.google.android.gms.internal.base.zac.zaa(r2, Status.CREATOR);
            com.google.android.gms.internal.base.zac.zad(r2);
            onResult(r12);
            return true;
        L6:
            return false;
        }
    }

    void onResult(Status r1);
}
