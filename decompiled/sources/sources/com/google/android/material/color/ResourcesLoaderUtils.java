package com.google.android.material.color;

import android.content.Context;
import android.content.res.loader.ResourcesLoader;
import java.util.Map;

/* loaded from: classes5.dex */
final class ResourcesLoaderUtils {
    private ResourcesLoaderUtils() {
    }

    public static boolean addResourcesLoaderToContext(Context r3, Map<Integer, Integer> r4) {
        ResourcesLoader r42 = ColorResourcesLoaderCreator.create(r3, r4);
        if (r42 == null) goto L6;
        r3.getResources().addLoaders(new ResourcesLoader[]{r42});
        return true;
    L6:
        return false;
    }

    public static boolean isColorResource(int r1) {
        if (28 <= r1) goto L5;
        return false;
    L5:
        if (r1 > 31) goto L10;
        return true;
    L10:
        return false;
    }
}
