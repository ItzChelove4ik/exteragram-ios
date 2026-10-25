package org.telegram.ui.Components.Premium;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.utils.Choreographer60FpsContent;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Stars.StarsReactionsSheet;

/* loaded from: classes7.dex */
public class ProfilePremiumCell extends TextCell {
    private final int colorKey;
    private final Runnable invalidateRunnable;
    private final StarsReactionsSheet.Particles particles;

    public ProfilePremiumCell(Context r2, int r3, Theme.ResourcesProvider r4) {
        super(r2, r4);
        this.particles = new StarsReactionsSheet.Particles(1, 15);
        this.invalidateRunnable = new ProfilePremiumCell$$ExternalSyntheticLambda0(this);
        if (r3 != 1) goto L5;
        int r22 = Theme.key_starsGradient1;
    L6:
        this.colorKey = r22;
        return;
    L5:
        r22 = Theme.key_premiumGradient2;
        goto L6
    }

    @Override // org.telegram.ui.Cells.TextCell, android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r4) {
        if (LiteMode.isEnabled(131072) == false) goto L5;
        this.particles.process();
        this.particles.draw(r4, Theme.getColor(this.colorKey));
        Choreographer60FpsContent.getInstance().addFrameCallback(this.invalidateRunnable, 15);
    L6:
        super.dispatchDraw(r4);
        return;
    L5:
        Choreographer60FpsContent.getInstance().removeFrameCallback(this.invalidateRunnable);
        goto L6
    }

    @Override // org.telegram.ui.Cells.TextCell, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Choreographer60FpsContent.getInstance().removeFrameCallback(this.invalidateRunnable);
    }

    @Override // org.telegram.ui.Cells.TextCell, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r3, int r4, int r5, int r6, int r7) {
        super.onLayout(r3, r4, r5, r6, r7);
        float r32 = this.imageView.getX() + (this.imageView.getWidth() / 2.0f);
        float r42 = ((this.imageView.getPaddingTop() + this.imageView.getY()) + (this.imageView.getHeight() / 2.0f)) - AndroidUtilities.dp(3.0f);
        RectF r52 = AndroidUtilities.rectTmp;
        r52.set(r32 - AndroidUtilities.dp(16.0f), r42 - AndroidUtilities.dp(16.0f), r32 + AndroidUtilities.dp(16.0f), r42 + AndroidUtilities.dp(16.0f));
        this.particles.setBounds(r52);
    }
}
