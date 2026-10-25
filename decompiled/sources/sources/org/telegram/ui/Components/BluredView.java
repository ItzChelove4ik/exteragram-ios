package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public abstract class BluredView extends View {
    public final BlurBehindDrawable drawable;

    public BluredView(Context r2, View r3, Theme.ResourcesProvider r4) {
        super(r2);
        BlurBehindDrawable r22 = new BlurBehindDrawable(r3, this, 1, r4);
        this.drawable = r22;
        r22.setAnimateAlpha(false);
        r22.show(true);
    }

    public boolean fullyDrawing() {
        if (this.drawable.isFullyDrawing() == true) goto L5;
        return false;
    L5:
        if (getVisibility() != 0) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // android.view.View
    public void onDraw(Canvas r1) {
        this.drawable.draw(r1);
    }

    @Override // android.view.View
    public void onSizeChanged(int r1, int r2, int r3, int r4) {
        super.onSizeChanged(r1, r2, r3, r4);
        this.drawable.checkSizes();
    }

    public void update() {
        this.drawable.invalidate();
    }
}
