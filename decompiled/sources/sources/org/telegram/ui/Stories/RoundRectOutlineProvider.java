package org.telegram.ui.Stories;

import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;
import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes7.dex */
public class RoundRectOutlineProvider extends ViewOutlineProvider {
    public float radiusInDp;

    public RoundRectOutlineProvider(int r1) {
        this.radiusInDp = r1;
    }

    @Override // android.view.ViewOutlineProvider
    public void getOutline(View r7, Outline r8) {
        r8.setRoundRect(0, 0, r7.getMeasuredWidth(), r7.getMeasuredHeight(), AndroidUtilities.dpf2(this.radiusInDp));
    }
}
