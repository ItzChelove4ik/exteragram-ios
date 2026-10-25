package org.telegram.ui.Stories;

import android.content.Context;
import android.view.TextureView;

/* loaded from: classes7.dex */
abstract class HwTextureView extends TextureView {
    public HwTextureView(Context r1) {
        super(r1);
    }

    @Override // android.view.View
    public void invalidate() {
        if (HwFrameLayout.hwEnabled == false) goto L6;
        HwFrameLayout.hwViews.add(this);
        return;
    L6:
        super.invalidate();
    }

    @Override // android.view.View
    public void invalidate(int r2, int r3, int r4, int r5) {
        if (HwFrameLayout.hwEnabled == false) goto L6;
        HwFrameLayout.hwViews.add(this);
        return;
    L6:
        super.invalidate(r2, r3, r4, r5);
    }
}
