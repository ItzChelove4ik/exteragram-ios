package com.google.android.material.color;

import android.app.Activity;
import android.app.Application;
import android.app.UiModeManager;
import android.app.UiModeManager$ContrastChangeListener;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import androidx.core.content.ContextCompat;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/* loaded from: classes5.dex */
public class ColorContrast {
    private static final float HIGH_CONTRAST_THRESHOLD = 0.6666667f;
    private static final float MEDIUM_CONTRAST_THRESHOLD = 0.33333334f;

    public static class ColorContrastActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
        private final Set<Activity> activitiesInStack;
        private final ColorContrastOptions colorContrastOptions;
        private UiModeManager$ContrastChangeListener contrastChangeListener;

        /* renamed from: com.google.android.material.color.ColorContrast$ColorContrastActivityLifecycleCallbacks$1, reason: invalid class name */
        public class AnonymousClass1 implements UiModeManager$ContrastChangeListener {
            final /* synthetic */ ColorContrastActivityLifecycleCallbacks this$0;

            public AnonymousClass1(ColorContrastActivityLifecycleCallbacks r1) {
                this.this$0 = r1;
            }

            public void onContrastChanged(float r1) {
                Iterator r0 = ColorContrastActivityLifecycleCallbacks.access$000(this.this$0).iterator();
            L4:
                if (r0.hasNext() == false) goto L6;
                ((Activity) r0.next()).recreate();
                goto L4
            }
        }

        public ColorContrastActivityLifecycleCallbacks(ColorContrastOptions r2) {
            this.activitiesInStack = new LinkedHashSet();
            this.colorContrastOptions = r2;
        }

        public static /* synthetic */ Set access$000(ColorContrastActivityLifecycleCallbacks r0) {
            return r0.activitiesInStack;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity r1, Bundle r2) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity r2) {
            this.activitiesInStack.remove(r2);
            UiModeManager r22 = (UiModeManager) r2.getSystemService("uimode");
            if (r22 != null) goto L5;
            return;
        L5:
            if (this.contrastChangeListener != null) goto L7;
            return;
        L7:
            if (this.activitiesInStack.isEmpty() == false) goto L12;
            r22.removeContrastChangeListener(this.contrastChangeListener);
            this.contrastChangeListener = null;
            return;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity r1) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPreCreated(Activity r3, Bundle r4) {
            UiModeManager r42 = (UiModeManager) r3.getSystemService("uimode");
            if (r42 != null) goto L5;
        L9:
            this.activitiesInStack.add(r3);
            if (r42 == null) goto L13;
            ColorContrast.applyToActivityIfAvailable(r3, this.colorContrastOptions);
            return;
        L13:
            return;
        L5:
            if (this.activitiesInStack.isEmpty() == false) goto L9;
            if (this.contrastChangeListener != null) goto L9;
            this.contrastChangeListener = new AnonymousClass1(this);
            r42.addContrastChangeListener(ContextCompat.getMainExecutor(r3.getApplicationContext()), this.contrastChangeListener);
            goto L9
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity r1) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity r1, Bundle r2) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity r1) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity r1) {
        }
    }

    private ColorContrast() {
    }

    public static void applyToActivitiesIfAvailable(Application r1, ColorContrastOptions r2) {
        if (isContrastAvailable() == true) goto L5;
        return;
    L5:
        r1.registerActivityLifecycleCallbacks(new ColorContrastActivityLifecycleCallbacks(r2));
    }

    public static void applyToActivityIfAvailable(Activity r1, ColorContrastOptions r2) {
        if (isContrastAvailable() == false) goto L10;
        int r22 = getContrastThemeOverlayResourceId(r1, r2);
        if (r22 == 0) goto L9;
        ThemeUtils.applyThemeOverlay(r1, r22);
        return;
    L9:
        return;
    }

    private static int getContrastThemeOverlayResourceId(Context r3, ColorContrastOptions r4) {
        UiModeManager r32 = (UiModeManager) r3.getSystemService("uimode");
        if (isContrastAvailable() == false) goto L15;
        if (r32 == null) goto L15;
        float r33 = r32.getContrast();
        int r0 = r4.getMediumContrastThemeOverlay();
        int r42 = r4.getHighContrastThemeOverlay();
        if (r33 < HIGH_CONTRAST_THRESHOLD) goto L11;
        if (r42 == 0) goto L14;
    L13:
        return r42;
    L14:
        return r0;
    L11:
        if (r33 < MEDIUM_CONTRAST_THRESHOLD) goto L15;
        if (r0 != 0) goto L14;
    L15:
        return 0;
    }

    public static boolean isContrastAvailable() {
        if (Build.VERSION.SDK_INT < 34) goto L6;
        return true;
    L6:
        return false;
    }

    public static Context wrapContextIfAvailable(Context r1, ColorContrastOptions r2) {
        if (isContrastAvailable() == false) goto L7;
        int r22 = getContrastThemeOverlayResourceId(r1, r2);
        if (r22 == 0) goto L7;
        return new ContextThemeWrapper(r1, r22);
    L7:
        return r1;
    }
}
