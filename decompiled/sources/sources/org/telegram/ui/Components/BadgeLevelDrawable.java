package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import androidx.core.graphics.ColorUtils;
import androidx.core.math.MathUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.R;
import org.telegram.ui.Components.AnimatedTextView;

/* loaded from: classes7.dex */
public class BadgeLevelDrawable extends Drawable implements Drawable.Callback {
    private static int[] res;
    private final Context context;
    private Drawable inner;
    private int innerColor;
    private int lastLevelIndex;
    private int level;
    private Drawable outer;
    private int outerColor;
    private final AnimatedTextView.AnimatedTextDrawable text;
    private int textColor;

    public BadgeLevelDrawable(Context r8) {
        this.context = r8;
        AnimatedTextView.AnimatedTextDrawable r0 = new AnimatedTextView.AnimatedTextDrawable();
        this.text = r0;
        r0.setTypeface(AndroidUtilities.getTypeface(AndroidUtilities.TYPEFACE_ROBOTO_CONDENSED_BOLD));
        r0.setAnimationProperties(0.2f, 0, 160, CubicBezierInterpolator.EASE_OUT_QUINT);
        r0.setTextSize(AndroidUtilities.dp(10.0f));
        r0.setGravity(17);
        r0.setCallback(this);
        r0.centerY = true;
        init();
    }

    private void checkBounds() {
        Drawable r0 = this.inner;
        if (r0 == null) goto L5;
        r0.setBounds(getBounds());
    L5:
        Drawable r02 = this.outer;
        if (r02 == null) goto L9;
        r02.setBounds(getBounds());
        return;
    }

    private static int getIndexByLevel(int r2) {
        if (r2 >= 0) goto L6;
        return 18;
    L6:
        if (r2 > 10) goto L8;
        int r22 = r2 - 1;
    L10:
        return MathUtils.clamp(r22, 0, 17);
    L8:
        r22 = (r2 / 10) + 8;
        goto L10
    }

    private static void init() {
        if (res == null) goto L5;
        return;
    L5:
        res = new int[]{R.drawable.profile_level1_inner, R.drawable.profile_level1_outer, R.drawable.profile_level2_inner, R.drawable.profile_level2_outer, R.drawable.profile_level3_inner, R.drawable.profile_level3_outer, R.drawable.profile_level4_inner, R.drawable.profile_level4_outer, R.drawable.profile_level5_inner, R.drawable.profile_level5_outer, R.drawable.profile_level6_inner, R.drawable.profile_level6_outer, R.drawable.profile_level7_inner, R.drawable.profile_level7_outer, R.drawable.profile_level8_inner, R.drawable.profile_level8_outer, R.drawable.profile_level9_inner, R.drawable.profile_level9_outer, R.drawable.profile_level10_inner, R.drawable.profile_level10_outer, R.drawable.profile_level20_inner, R.drawable.profile_level20_outer, R.drawable.profile_level30_inner, R.drawable.profile_level30_outer, R.drawable.profile_level40_inner, R.drawable.profile_level40_outer, R.drawable.profile_level50_inner, R.drawable.profile_level50_outer, R.drawable.profile_level60_inner, R.drawable.profile_level60_outer, R.drawable.profile_level70_inner, R.drawable.profile_level70_outer, R.drawable.profile_level80_inner, R.drawable.profile_level80_outer, R.drawable.profile_level90_inner, R.drawable.profile_level90_outer, R.drawable.profile_level_minus_inner, R.drawable.profile_level_minus_outer};
    }

    private void setLevelIndex(int r5) {
        if (this.lastLevelIndex == r5) goto L5;
    L10:
        int r2 = r5 * 2;
        Drawable r0 = this.context.getResources().getDrawable(res[r2]).mutate();
        this.inner = r0;
        int r1 = this.innerColor;
        PorterDuff.Mode r3 = PorterDuff.Mode.MULTIPLY;
        r0.setColorFilter(r1, r3);
        Drawable r02 = this.context.getResources().getDrawable(res[r2 + 1]).mutate();
        this.outer = r02;
        r02.setColorFilter(this.outerColor, r3);
        this.lastLevelIndex = r5;
        checkBounds();
        return;
    L5:
        if (this.inner == null) goto L10;
        if (this.outer == null) goto L10;
    }

    public void debugUpdateStart() {
    }

    public void debugUpdateStop() {
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r3) {
        Drawable r0 = this.outer;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.inner == null) goto L10;
        r0.draw(r3);
        this.inner.draw(r3);
        r3.save();
        r3.translate(getBounds().exactCenterX(), getBounds().exactCenterY());
        this.text.draw(r3);
        r3.restore();
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return AndroidUtilities.dp(24.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return AndroidUtilities.dp(24.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable r1) {
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect r1) {
        checkBounds();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable r1, Runnable r2, long r3) {
        scheduleSelf(r2, r3);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r2) {
        setInnerColor(ColorUtils.setAlphaComponent(this.innerColor, r2));
        setOuterColor(ColorUtils.setAlphaComponent(this.outerColor, r2));
        setTextColor(ColorUtils.setAlphaComponent(this.textColor, r2));
    }

    public void setBadgeLevel(int r3, boolean r4) {
        if (this.level == r3) goto L5;
    L10:
        AnimatedTextView.AnimatedTextDrawable r0 = this.text;
        if (r3 < 0) goto L13;
        String r1 = Integer.toString(r3);
    L14:
        r0.setText(r1, r4);
        this.level = r3;
        setLevelIndex(getIndexByLevel(r3));
        invalidateSelf();
        return;
    L13:
        r1 = "!";
        goto L14
    L5:
        if (this.inner == null) goto L10;
        if (this.outer == null) goto L10;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
    }

    public void setInnerColor(int r3) {
        if (this.innerColor == r3) goto L8;
        this.innerColor = r3;
        Drawable r0 = this.inner;
        if (r0 == null) goto L9;
        r0.setColorFilter(r3, PorterDuff.Mode.MULTIPLY);
        invalidateSelf();
        return;
    L9:
        return;
    }

    public void setOuterColor(int r3) {
        if (this.outerColor == r3) goto L8;
        this.outerColor = r3;
        if (this.inner == null) goto L9;
        this.outer.setColorFilter(r3, PorterDuff.Mode.MULTIPLY);
        invalidateSelf();
        return;
    L9:
        return;
    }

    public void setTextColor(int r3) {
        if (this.textColor == r3) goto L6;
        this.textColor = r3;
        this.text.setTextColor(r3, false);
        invalidateSelf();
        return;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable r1, Runnable r2) {
        unscheduleSelf(r2);
    }
}
