package org.telegram.ui.community;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import androidx.core.graphics.ColorUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.R;
import org.telegram.messenger.utils.DrawableUtils;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes3.dex */
public class CommunityArrowDrawable extends Drawable {
    private int alpha;
    private final Drawable arrowDrawable;
    private boolean drawCircle;
    private int lastColor;

    public CommunityArrowDrawable() {
        this.alpha = 255;
        this.arrowDrawable = ApplicationLoader.applicationContext.getResources().getDrawable(R.drawable.settings_arrow).mutate();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r8) {
        float r0 = getBounds().exactCenterX();
        float r1 = getBounds().exactCenterY();
        int r2 = Theme.getColor(Theme.key_windowBackgroundWhiteBlackText);
        int r3 = Theme.getColor(Theme.key_windowBackgroundWhite);
        if (this.lastColor == r3) goto L5;
        this.lastColor = r3;
        this.arrowDrawable.setColorFilter(new PorterDuffColorFilter(r3, PorterDuff.Mode.SRC_IN));
    L5:
        r8.drawCircle(r0, r1, AndroidUtilities.dp(7.6666665f), Theme.fillingPaint(ColorUtils.setAlphaComponent(r3, this.alpha)));
        r8.drawCircle(r0, r1, AndroidUtilities.dp(6.6666665f), Theme.fillingPaint(ColorUtils.setAlphaComponent(r2, this.alpha)));
        DrawableUtils.setBounds(this.arrowDrawable, r0, r1, 17);
        r8.translate(0.0f, AndroidUtilities.dp(0.66f));
        r8.save();
        r8.rotate(90.0f, r0, r1);
        DrawableUtils.drawWithScale(r8, this.arrowDrawable, 0.8f);
        r8.restore();
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.alpha;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return AndroidUtilities.dp(13.333333f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return AndroidUtilities.dp(13.333333f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
        this.alpha = r1;
        this.arrowDrawable.setAlpha(r1);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
    }

    public CommunityArrowDrawable withCircle() {
        this.drawCircle = true;
        return this;
    }
}
