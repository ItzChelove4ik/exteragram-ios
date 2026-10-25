package org.telegram.ui.Components.blur3.capture;

import android.graphics.Canvas;
import android.graphics.RectF;

/* loaded from: classes3.dex */
public interface IBlur3Capture {
    void capture(Canvas r1, RectF r2);

    default void captureCalculateHash(IBlur3Hash r1, RectF r2) {
        r1.unsupported();
    }
}
