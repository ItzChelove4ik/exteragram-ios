package com.google.android.play.integrity.internal;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public abstract class d {
    public static final List a(List r5) {
        ArrayList r0 = new ArrayList();
        Iterator r52 = r5.iterator();
    L4:
        if (r52.hasNext() == false) goto L6;
        f r1 = (f) r52.next();
        Bundle r2 = new Bundle();
        r2.putInt("event_type", r1.a());
        r2.putLong("event_timestamp", r1.b());
        r0.add(r2);
        goto L4
    L6:
        return r0;
    }

    public static final void b(int r2, List r3) {
        r3.add(f.c(r2, System.currentTimeMillis()));
    }
}
