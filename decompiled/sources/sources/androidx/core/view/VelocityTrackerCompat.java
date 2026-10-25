package androidx.core.view;

import android.os.Build;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

/* loaded from: classes3.dex */
public abstract class VelocityTrackerCompat {
    private static Map<VelocityTracker, VelocityTrackerFallback> sFallbackTrackers;

    public static class Api34Impl {
        public static float getAxisVelocity(VelocityTracker r0, int r1) {
            return r0.getAxisVelocity(r1);
        }
    }

    static {
        sFallbackTrackers = Collections.synchronizedMap(new WeakHashMap());
    }

    public static void addMovement(VelocityTracker r2, MotionEvent r3) {
        r2.addMovement(r3);
        if (Build.VERSION.SDK_INT < 34) goto L6;
        return;
    L6:
        if (r3.getSource() == 4194304) goto L8;
        return;
    L8:
        if (sFallbackTrackers.containsKey(r2) == true) goto L10;
        sFallbackTrackers.put(r2, new VelocityTrackerFallback());
    L10:
        sFallbackTrackers.get(r2).addMovement(r3);
    }

    public static void computeCurrentVelocity(VelocityTracker r1, int r2) {
        computeCurrentVelocity(r1, r2, Float.MAX_VALUE);
    }

    public static void computeCurrentVelocity(VelocityTracker r0, int r1, float r2) {
        r0.computeCurrentVelocity(r1, r2);
        VelocityTrackerFallback r02 = getFallbackTrackerOrNull(r0);
        if (r02 == null) goto L6;
        r02.computeCurrentVelocity(r1, r2);
        return;
    }

    public static float getAxisVelocity(VelocityTracker r2, int r3) {
        if (Build.VERSION.SDK_INT >= 34) goto L5;
        if (r3 != 0) goto L10;
        return r2.getXVelocity();
    L10:
        if (r3 == 1) goto L12;
        VelocityTrackerFallback r22 = getFallbackTrackerOrNull(r2);
        if (r22 != null) goto L16;
        return 0.0f;
    L16:
        return r22.getAxisVelocity(r3);
    L12:
        return r2.getYVelocity();
    L5:
        return Api34Impl.getAxisVelocity(r2, r3);
    }

    private static VelocityTrackerFallback getFallbackTrackerOrNull(VelocityTracker r1) {
        return sFallbackTrackers.get(r1);
    }
}
