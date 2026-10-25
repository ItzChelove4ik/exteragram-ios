package org.telegram.ui.Components.blur3.capture;

import android.graphics.ColorMatrix;
import android.os.Build;
import android.view.View;

/* loaded from: classes3.dex */
public interface IBlur3Hash {
    void add(long r1);

    default void add(ColorMatrix r1) {
        add(r1.getArray());
    }

    default void add(View r3) {
        if (Build.VERSION.SDK_INT < 29) goto L6;
        add(r3.getUniqueDrawingId());
        return;
    L6:
        unsupported();
    }

    default void add(boolean r3) {
        if (r3 == false) goto L4;
        long r0 = 1;
    L5:
        add(r0);
        return;
    L4:
        r0 = 0;
        goto L5
    }

    default void add(float[] r4) {
        int r0 = r4.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        addF(r4[r1]);
        r1 = r1 + 1;
        goto L3
    }

    default void addF(float r3) {
        add(Float.floatToIntBits(r3));
    }

    void unsupported();
}
