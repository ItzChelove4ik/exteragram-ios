package com.google.android.play.integrity.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;

/* loaded from: classes5.dex */
public final class g extends a implements i {
    public g(IBinder r2) {
        super(r2, "com.google.android.play.core.integrity.protocol.IExpressIntegrityService");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.play.integrity.internal.i
    public final void d(Bundle r2, k r3) {
        Parcel r0 = a();
        c.c(r0, r2);
        r0.writeStrongBinder(r3);
        b(3, r0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.play.integrity.internal.i
    public final void e(Bundle r2, k r3) {
        Parcel r0 = a();
        c.c(r0, r2);
        r0.writeStrongBinder(r3);
        b(2, r0);
    }
}
