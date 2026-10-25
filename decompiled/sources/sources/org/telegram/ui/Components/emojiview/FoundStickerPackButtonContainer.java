package org.telegram.ui.Components.emojiview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.utils.GradientProtectionDrawable;
import org.telegram.ui.ActionBar.Theme;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class FoundStickerPackButtonContainer extends FrameLayout {
    private final GradientProtectionDrawable gradientProtectionDrawable;
    private final Theme.ResourcesProvider resourcesProvider;

    public FoundStickerPackButtonContainer(Context r2, Theme.ResourcesProvider r3) {
        super(r2);
        this.gradientProtectionDrawable = new GradientProtectionDrawable(8);
        this.resourcesProvider = r3;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r4) {
        this.gradientProtectionDrawable.setColor(Theme.multAlpha(Theme.getColor(Theme.key_windowBackgroundWhite, this.resourcesProvider), 0.65f));
        this.gradientProtectionDrawable.draw(r4);
        super.dispatchDraw(r4);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent r1) {
        super.dispatchTouchEvent(r1);
        return true;
    }

    @Override // android.view.View
    public void onSizeChanged(int r1, int r2, int r3, int r4) {
        super.onSizeChanged(r1, r2, r3, r4);
        this.gradientProtectionDrawable.setBounds(0, 0, r1, r2);
        this.gradientProtectionDrawable.setInsets(0, 0, 0, getPaddingBottom() + AndroidUtilities.dp(24.0f));
    }
}
