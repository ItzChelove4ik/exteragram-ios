package com.google.android.gms.cast;

import android.app.Presentation;
import android.content.Context;
import android.view.Display;
import android.view.Window;

@Deprecated
/* loaded from: classes4.dex */
public abstract class CastPresentation extends Presentation {
    public CastPresentation(Context r1, Display r2) {
        super(r1, r2);
        zza();
    }

    public CastPresentation(Context r1, Display r2, int r3) {
        super(r1, r2, r3);
        zza();
    }

    private final void zza() {
        Window r1 = getWindow();
        if (r1 == null) goto L6;
        r1.setType(2030);
        r1.addFlags(268435456);
        r1.addFlags(16777216);
        r1.addFlags(1024);
        return;
    }
}
