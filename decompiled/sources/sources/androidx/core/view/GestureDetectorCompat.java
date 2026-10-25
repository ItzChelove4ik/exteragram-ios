package androidx.core.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.MotionEvent;

@Deprecated
/* loaded from: classes.dex */
public final class GestureDetectorCompat {
    private final GestureDetector mDetector;

    public GestureDetectorCompat(Context r2, GestureDetector.OnGestureListener r3) {
        this(r2, r3, null);
    }

    public GestureDetectorCompat(Context r2, GestureDetector.OnGestureListener r3, Handler r4) {
        this.mDetector = new GestureDetector(r2, r3, r4);
    }

    public boolean onTouchEvent(MotionEvent r1) {
        return this.mDetector.onTouchEvent(r1);
    }

    @SuppressLint({"KotlinPropertyAccess"})
    public void setIsLongpressEnabled(boolean r1) {
        this.mDetector.setIsLongpressEnabled(r1);
    }
}
