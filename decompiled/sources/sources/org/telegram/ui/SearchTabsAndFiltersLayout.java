package org.telegram.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.widget.FrameLayout;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;

/* loaded from: classes3.dex */
public class SearchTabsAndFiltersLayout extends FrameLayout implements Theme.Colorable {
    private BlurredBackgroundDrawable blurredBackgroundDrawable;
    private final Path clipPath;

    public SearchTabsAndFiltersLayout(Context r1) {
        super(r1);
        this.clipPath = new Path();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r2) {
        r2.save();
        r2.clipPath(this.clipPath);
        super.dispatchDraw(r2);
        r2.restore();
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public /* bridge */ /* synthetic */ int[] getColorKeys() {
        return super.getColorKeys();
    }

    @Override // android.view.View
    public void onSizeChanged(int r9, int r10, int r11, int r12) {
        super.onSizeChanged(r9, r10, r11, r12);
        this.clipPath.rewind();
        this.clipPath.addRoundRect(AndroidUtilities.dp(9.0f), AndroidUtilities.dp(9.0f), r9 - AndroidUtilities.dp(9.0f), r10 - AndroidUtilities.dp(9.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), Path.Direction.CW);
    }

    public void setBlurredBackground(BlurredBackgroundDrawable r1) {
        this.blurredBackgroundDrawable = r1;
        setBackground(r1);
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public void updateColors() {
        BlurredBackgroundDrawable r0 = this.blurredBackgroundDrawable;
        if (r0 == null) goto L6;
        r0.updateColors();
        return;
    }
}
