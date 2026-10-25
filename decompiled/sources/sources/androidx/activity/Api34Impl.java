package androidx.activity;

import android.window.BackEvent;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J(\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nH\u0007J\u0010\u0010\b\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0004H\u0007J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004H\u0007J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0004H\u0007J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0004H\u0007¨\u0006\f"}, d2 = {"Landroidx/activity/Api34Impl;", _UrlKt.FRAGMENT_ENCODE_SET, "()V", "createOnBackEvent", "Landroid/window/BackEvent;", "touchX", _UrlKt.FRAGMENT_ENCODE_SET, "touchY", "progress", "swipeEdge", _UrlKt.FRAGMENT_ENCODE_SET, "backEvent", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Api34Impl {
    public static final Api34Impl INSTANCE = null;

    static {
        INSTANCE = new Api34Impl();
    }

    private Api34Impl() {
    }

    public final BackEvent createOnBackEvent(float r1, float r2, float r3, int r4) {
        return new BackEvent(r1, r2, r3, r4);
    }

    public final float progress(BackEvent r1) {
        return r1.getProgress();
    }

    public final int swipeEdge(BackEvent r1) {
        return r1.getSwipeEdge();
    }

    public final float touchX(BackEvent r1) {
        return r1.getTouchX();
    }

    public final float touchY(BackEvent r1) {
        return r1.getTouchY();
    }
}
