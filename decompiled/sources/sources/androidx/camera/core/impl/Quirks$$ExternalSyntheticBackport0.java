package androidx.camera.core.impl;

import c.f$$ExternalSyntheticBUOutline2;
import java.util.Iterator;

/* loaded from: classes3.dex */
public abstract /* synthetic */ class Quirks$$ExternalSyntheticBackport0 {
    public static /* synthetic */ String m(CharSequence r2, Iterable r3) {
        if (r2 == null) goto L10;
        StringBuilder r0 = new StringBuilder();
        Iterator r32 = r3.iterator();
        if (r32.hasNext() == false) goto L9;
    L5:
        r0.append((CharSequence) r32.next());
        if (r32.hasNext() == false) goto L9;
        r0.append(r2);
    L9:
        return r0.toString();
    L10:
        f$$ExternalSyntheticBUOutline2.m("delimiter");
        return null;
    }
}
