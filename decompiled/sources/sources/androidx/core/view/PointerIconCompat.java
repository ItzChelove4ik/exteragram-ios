package androidx.core.view;

import android.content.Context;
import android.view.PointerIcon;

/* loaded from: classes3.dex */
public final class PointerIconCompat {
    private final PointerIcon mPointerIcon;

    public static class Api24Impl {
        public static PointerIcon getSystemIcon(Context r0, int r1) {
            return PointerIcon.getSystemIcon(r0, r1);
        }
    }

    private PointerIconCompat(PointerIcon r1) {
        this.mPointerIcon = r1;
    }

    public static PointerIconCompat getSystemIcon(Context r1, int r2) {
        return new PointerIconCompat(Api24Impl.getSystemIcon(r1, r2));
    }

    public Object getPointerIcon() {
        return this.mPointerIcon;
    }
}
