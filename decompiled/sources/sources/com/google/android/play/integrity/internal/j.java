package com.google.android.play.integrity.internal;

import android.os.Bundle;
import android.os.Parcel;

/* loaded from: classes5.dex */
public abstract class j extends b implements k {
    public j() {
        super("com.google.android.play.core.integrity.protocol.IExpressIntegrityServiceCallback");
    }

    @Override // com.google.android.play.integrity.internal.b
    public final boolean a(int r1, Parcel r2, Parcel r3, int r4) {
        if (r1 != 2) goto L5;
        Bundle r12 = (Bundle) c.a(r2, Bundle.CREATOR);
        c.b(r2);
        e(r12);
        return true;
    L5:
        if (r1 != 3) goto L7;
        Bundle r13 = (Bundle) c.a(r2, Bundle.CREATOR);
        c.b(r2);
        c(r13);
        return true;
    L7:
        if (r1 != 4) goto L9;
        Bundle r14 = (Bundle) c.a(r2, Bundle.CREATOR);
        c.b(r2);
        d(r14);
        return true;
    L9:
        if (r1 == 5) goto L12;
        return false;
    L12:
        Bundle r15 = (Bundle) c.a(r2, Bundle.CREATOR);
        c.b(r2);
        b(r15);
        return true;
    }
}
