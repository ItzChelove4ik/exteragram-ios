package com.google.android.material.color;

import android.content.Context;
import android.os.Build;
import java.util.Map;

/* loaded from: classes5.dex */
public interface ColorResourcesOverride {
    static ColorResourcesOverride getInstance() {
        int r0 = Build.VERSION.SDK_INT;
        if (30 > r0) goto L9;
        if (r0 > 33) goto L9;
        return ResourcesLoaderColorResourcesOverride.getInstance();
    L9:
        if (r0 >= 34) goto L11;
        return null;
    L11:
        return ResourcesLoaderColorResourcesOverride.getInstance();
    }

    boolean applyIfPossible(Context r1, Map<Integer, Integer> r2);

    Context wrapContextIfPossible(Context r1, Map<Integer, Integer> r2);
}
