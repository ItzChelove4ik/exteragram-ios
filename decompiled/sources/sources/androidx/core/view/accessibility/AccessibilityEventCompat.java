package androidx.core.view.accessibility;

import android.annotation.SuppressLint;
import android.view.accessibility.AccessibilityEvent;

/* loaded from: classes.dex */
public abstract class AccessibilityEventCompat {
    @SuppressLint({"WrongConstant"})
    @Deprecated
    public static int getContentChangeTypes(AccessibilityEvent r0) {
        return r0.getContentChangeTypes();
    }

    @SuppressLint({"WrongConstant"})
    @Deprecated
    public static void setContentChangeTypes(AccessibilityEvent r0, int r1) {
        r0.setContentChangeTypes(r1);
    }
}
