package androidx.core.hardware.display;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.view.Display;

/* loaded from: classes3.dex */
public final class DisplayManagerCompat {
    private final Context mContext;

    private DisplayManagerCompat(Context r1) {
        this.mContext = r1;
    }

    public static DisplayManagerCompat getInstance(Context r1) {
        return new DisplayManagerCompat(r1);
    }

    public Display getDisplay(int r2) {
        return ((DisplayManager) this.mContext.getSystemService("display")).getDisplay(r2);
    }
}
