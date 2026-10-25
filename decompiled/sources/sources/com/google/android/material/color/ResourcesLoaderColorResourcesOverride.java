package com.google.android.material.color;

import android.content.Context;
import android.content.res.Configuration;
import android.view.ContextThemeWrapper;
import com.google.android.material.R;
import java.util.Map;

/* loaded from: classes5.dex */
class ResourcesLoaderColorResourcesOverride implements ColorResourcesOverride {

    /* renamed from: com.google.android.material.color.ResourcesLoaderColorResourcesOverride$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static class ResourcesLoaderColorResourcesOverrideSingleton {
        private static final ResourcesLoaderColorResourcesOverride INSTANCE = null;

        static {
            INSTANCE = new ResourcesLoaderColorResourcesOverride(null);
        }

        private ResourcesLoaderColorResourcesOverrideSingleton() {
        }

        public static /* synthetic */ ResourcesLoaderColorResourcesOverride access$000() {
            return INSTANCE;
        }
    }

    private ResourcesLoaderColorResourcesOverride() {
    }

    public /* synthetic */ ResourcesLoaderColorResourcesOverride(AnonymousClass1 r1) {
        this();
    }

    public static ColorResourcesOverride getInstance() {
        return ResourcesLoaderColorResourcesOverrideSingleton.access$000();
    }

    @Override // com.google.android.material.color.ColorResourcesOverride
    public boolean applyIfPossible(Context r1, Map<Integer, Integer> r2) {
        if (ResourcesLoaderUtils.addResourcesLoaderToContext(r1, r2) == false) goto L6;
        ThemeUtils.applyThemeOverlay(r1, R.style.ThemeOverlay_Material3_PersonalizedColors);
        return true;
    L6:
        return false;
    }

    @Override // com.google.android.material.color.ColorResourcesOverride
    public Context wrapContextIfPossible(Context r2, Map<Integer, Integer> r3) {
        ContextThemeWrapper r1 = new ContextThemeWrapper(r2, R.style.ThemeOverlay_Material3_PersonalizedColors);
        r1.applyOverrideConfiguration(new Configuration());
        if (ResourcesLoaderUtils.addResourcesLoaderToContext(r1, r3) == false) goto L5;
        return r1;
    L5:
        return r2;
    }
}
