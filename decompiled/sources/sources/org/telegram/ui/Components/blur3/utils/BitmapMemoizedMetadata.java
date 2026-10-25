package org.telegram.ui.Components.blur3.utils;

import android.graphics.Bitmap;

/* loaded from: classes7.dex */
public class BitmapMemoizedMetadata<T> {
    private final BitmapChangeTracker lastBitmap;
    private T memoized;
    private final Provider<T> provider;

    public interface Provider<T> {
        T get(Bitmap r1);
    }

    public BitmapMemoizedMetadata(Provider<T> r2) {
        this.lastBitmap = new BitmapChangeTracker();
        this.provider = r2;
    }

    public T get(Bitmap r2) {
        if (this.lastBitmap.isInvalidated(r2) == false) goto L6;
        this.memoized = this.provider.get(r2);
        this.lastBitmap.set(r2);
    L6:
        return this.memoized;
    }
}
