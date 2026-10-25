package org.telegram.ui;

import android.view.View;
import org.telegram.messenger.utils.ViewOutlineProviderImpl;

/* loaded from: classes6.dex */
public abstract class BadWayToMakeButtonRound {
    public static void round(View r1) {
        r1.setOutlineProvider(ViewOutlineProviderImpl.BOUNDS_ROUND_RECT);
        r1.setClipToOutline(true);
    }
}
