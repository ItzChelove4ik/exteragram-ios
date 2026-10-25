package androidx.core.view.accessibility;

import android.view.View;
import android.view.accessibility.AccessibilityRecord;

/* loaded from: classes3.dex */
public abstract class AccessibilityRecordCompat {
    @Deprecated
    public static void setMaxScrollX(AccessibilityRecord r0, int r1) {
        r0.setMaxScrollX(r1);
    }

    @Deprecated
    public static void setMaxScrollY(AccessibilityRecord r0, int r1) {
        r0.setMaxScrollY(r1);
    }

    @Deprecated
    public static void setSource(AccessibilityRecord r0, View r1, int r2) {
        r0.setSource(r1, r2);
    }
}
