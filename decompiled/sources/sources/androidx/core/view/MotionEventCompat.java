package androidx.core.view;

import android.view.MotionEvent;

/* loaded from: classes3.dex */
public abstract class MotionEventCompat {
    public static boolean isFromSource(MotionEvent r0, int r1) {
        if ((r0.getSource() & r1) != r1) goto L6;
        return true;
    L6:
        return false;
    }
}
