package com.google.android.material.internal;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;

/* loaded from: classes5.dex */
public class WindowUtils {

    public static class Api17Impl {
        private Api17Impl() {
        }

        public static Rect getCurrentWindowBounds(WindowManager r2) {
            Display r22 = r2.getDefaultDisplay();
            Point r0 = new Point();
            r22.getRealSize(r0);
            Rect r23 = new Rect();
            r23.right = r0.x;
            r23.bottom = r0.y;
            return r23;
        }
    }

    public static class Api30Impl {
        private Api30Impl() {
        }

        public static Rect getCurrentWindowBounds(WindowManager r0) {
            return r0.getCurrentWindowMetrics().getBounds();
        }
    }

    private WindowUtils() {
    }

    public static Rect getCurrentWindowBounds(Context r2) {
        WindowManager r22 = (WindowManager) r2.getSystemService("window");
        if (Build.VERSION.SDK_INT < 30) goto L7;
        return Api30Impl.getCurrentWindowBounds(r22);
    L7:
        return Api17Impl.getCurrentWindowBounds(r22);
    }
}
