package org.telegram.ui.Components.blur3.utils;

import android.graphics.Bitmap;
import java.lang.ref.WeakReference;

/* loaded from: classes7.dex */
public class BitmapChangeTracker {
    private long generationId;
    private boolean invalidated;
    private WeakReference<Bitmap> ref;

    public BitmapChangeTracker() {
        this.invalidated = true;
    }

    private static long generationOf(Bitmap r2) {
        if (r2 != null) goto L4;
        return 0;
    L4:
        if (r2.isRecycled() == false) goto L6;
        return 0;
    L6:
        return r2.getGenerationId();
    }

    public boolean isInvalidated(Bitmap r5) {
        if (this.invalidated == false) goto L5;
        return true;
    L5:
        WeakReference<Bitmap> r0 = this.ref;
        if (r0 == null) goto L8;
        Bitmap r02 = r0.get();
    L9:
        if (r02 == r5) goto L12;
        return true;
    L12:
        if (generationOf(r5) == this.generationId) goto L14;
        return true;
    L14:
        return false;
    L8:
        r02 = null;
        goto L9
    }

    public void set(Bitmap r3) {
        if (r3 == null) goto L4;
        WeakReference<Bitmap> r0 = new WeakReference(r3);
    L5:
        this.ref = r0;
        this.generationId = generationOf(r3);
        this.invalidated = false;
        return;
    L4:
        r0 = null;
        goto L5
    }
}
