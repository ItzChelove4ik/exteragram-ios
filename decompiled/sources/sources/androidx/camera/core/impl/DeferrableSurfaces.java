package androidx.camera.core.impl;

import androidx.camera.core.impl.DeferrableSurface;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public abstract class DeferrableSurfaces {
    public static void decrementAll(List<DeferrableSurface> r1) {
        Iterator<DeferrableSurface> r12 = r1.iterator();
    L4:
        if (r12.hasNext() == false) goto L6;
        r12.next().decrementUseCount();
        goto L4
    }

    public static void incrementAll(List<DeferrableSurface> r3) {
        if (r3.isEmpty() == true) goto L13;
        int r0 = 0;
    L14:
        r3.get(r0).incrementUseCount();     // Catch: DeferrableSurface.SurfaceClosedException -> L8
        r0 = r0 + 1;     // Catch: DeferrableSurface.SurfaceClosedException -> L8
        if (r0 < r3.size()) goto L14;
        return;
    L8:
        e = move-exception;
        int r02 = r0 - 1;
    L10:
        if (r02 < 0) goto L12;
        r3.get(r02).decrementUseCount();
        r02 = r02 - 1;
        goto L10
    L12:
        throw e;
    }
}
