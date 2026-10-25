package org.telegram.ui.Components;

import android.R;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.StaticLayout;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.SharedConfig;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class ForwardBackground {
    public final ButtonBounce bounce;
    public final Rect bounds;
    private LoadingDrawable loadingDrawable;
    public final Path path;
    private final RectF r;
    private Drawable rippleDrawable;
    private int rippleDrawableColor;
    private final View view;

    public ForwardBackground(View r4) {
        this.path = new Path();
        this.bounds = new Rect();
        this.r = new RectF();
        this.view = r4;
        this.bounce = new ButtonBounce(r4, 0.8f, 1.4f);
    }

    public void draw(Canvas r6, boolean r7) {
        r6.save();
        r6.clipPath(this.path);
        Drawable r0 = this.rippleDrawable;
        if (r0 == null) goto L5;
        r0.setBounds(this.bounds);
        this.rippleDrawable.draw(r6);
    L5:
        LoadingDrawable r02 = this.loadingDrawable;
        if (r7 == false) goto L14;
        if (r02 != null) goto L10;
        LoadingDrawable r72 = new LoadingDrawable();
        this.loadingDrawable = r72;
        r72.setAppearByGradient(true);
    L20:
        r6.restore();
        LoadingDrawable r73 = this.loadingDrawable;
        if (r73 != null) goto L23;
        return;
    L23:
        if (r73.isDisappeared() == true) goto L27;
        this.loadingDrawable.usePath(this.path);
        this.loadingDrawable.setColors(Theme.multAlpha(this.rippleDrawableColor, 0.7f), Theme.multAlpha(this.rippleDrawableColor, 1.3f), Theme.multAlpha(this.rippleDrawableColor, 1.5f), Theme.multAlpha(this.rippleDrawableColor, 2.0f));
        this.loadingDrawable.setBounds(this.bounds);
        r6.save();
        this.loadingDrawable.draw(r6);
        r6.restore();
        this.view.invalidate();
        return;
    L27:
        return;
    L10:
        if (r02.isDisappeared() == false) goto L12;
    L13:
        this.loadingDrawable.reset();
        this.loadingDrawable.resetDisappear();
        goto L20
    L12:
        if (this.loadingDrawable.isDisappearing() == false) goto L20;
    L14:
        if (r02 == null) goto L20;
        if (r02.isDisappearing() == true) goto L20;
        if (this.loadingDrawable.isDisappeared() == true) goto L20;
        this.loadingDrawable.disappear();
        goto L20
    }

    public void set(StaticLayout[] r17, boolean r18) {
        int r2 = AndroidUtilities.dp(4.0f) + (((int) Theme.chat_forwardNamePaint.getTextSize()) * 2);
        float r3 = Math.max(0, Math.min(6, SharedConfig.bubbleRadius) - 1);
        float r6 = Math.min(9, SharedConfig.bubbleRadius);
        float r7 = Math.min(3, SharedConfig.bubbleRadius);
        float r1 = -AndroidUtilities.dp(((r6 / 9.0f) * 2.66f) + 4.0f);
        float r9 = -AndroidUtilities.dp(3.0f);
        float r22 = r2 + AndroidUtilities.dp(5.0f);
        float r10 = r17[0].getLineWidth(0) + AndroidUtilities.dp(r8);
        float r4 = r17[1].getLineWidth(0) + AndroidUtilities.dp(r8);
        this.path.rewind();
        if (r18 == true) goto L6;
        r3 = SharedConfig.bubbleRadius / 2.0f;
    L6:
        float r32 = AndroidUtilities.dp(r3) * 2;
        this.r.set(r1, r9, r1 + r32, r32 + r9);
        this.path.arcTo(this.r, 180.0f, 90.0f);
        float r33 = r10 - r4;
        float r12 = r7 + r6;
        if (Math.abs(r33) >= AndroidUtilities.dp(r12)) goto L9;
        float r5 = Math.max(r10, r4);
    L11:
        if (Math.abs(r33) <= AndroidUtilities.dp(r12)) goto L16;
        float r34 = AndroidUtilities.dp(r7) * 2;
        if (r10 >= r4) goto L15;
        float r15 = ((r22 - r9) * 0.45f) + r9;
        float r72 = AndroidUtilities.dp(r6) * 2;
        this.r.set(r5 - r72, r9, r5, r9 + r72);
        this.path.arcTo(this.r, 270.0f, 90.0f);
        this.r.set(r10, r15 - r34, r34 + r10, r15);
        this.path.arcTo(this.r, 180.0f, -90.0f);
        float r35 = Math.min(r22 - r15, AndroidUtilities.dp(r6) * 2);
        float r62 = r4 - r35;
        this.r.set(r62, r15, r4, r15 + r35);
        this.path.arcTo(this.r, 270.0f, 90.0f);
        this.r.set(r62, r22 - r35, r4, r22);
        this.path.arcTo(this.r, 0.0f, 90.0f);
    L17:
        this.r.set(r1, r22 - r72, r72 + r1, r22);
        this.path.arcTo(this.r, 90.0f, 90.0f);
        this.path.close();
        this.bounds.set((int) r1, (int) r9, (int) Math.max(r10, r4), (int) r22);
        return;
    L15:
        float r8 = ((r22 - r9) * 0.55f) + r9;
        float r73 = Math.min(r8 - r9, AndroidUtilities.dp(r6) * 2);
        this.r.set(r5 - r73, r9, r5, r9 + r73);
        this.path.arcTo(this.r, 270.0f, 90.0f);
        float r52 = AndroidUtilities.dp(r6) * 2;
        this.r.set(r10 - r73, r8 - r73, r10, r8);
        this.path.arcTo(this.r, 0.0f, 90.0f);
        this.r.set(r4, r8, r4 + r34, r34 + r8);
        this.path.arcTo(this.r, 270.0f, -90.0f);
        this.r.set(r4 - r52, r22 - r52, r4, r22);
        this.path.arcTo(this.r, 0.0f, 90.0f);
        r72 = r52;
        goto L17
    L16:
        r72 = AndroidUtilities.dp(r6) * 2;
        float r63 = r5 - r72;
        this.r.set(r63, r9, r5, r9 + r72);
        this.path.arcTo(this.r, 270.0f, 90.0f);
        this.r.set(r63, r22 - r72, r5, r22);
        this.path.arcTo(this.r, 0.0f, 90.0f);
        goto L17
    L9:
        r5 = r10;
        goto L11
    }

    public void setColor(int r3) {
        if (this.rippleDrawableColor == r3) goto L10;
        Drawable r0 = this.rippleDrawable;
        if (r0 != null) goto L7;
        this.rippleDrawable = Theme.createSelectorDrawable(r3, 2);
    L8:
        this.rippleDrawable.setCallback(this.view);
        this.rippleDrawableColor = r3;
        return;
    L7:
        Theme.setSelectorDrawableColor(r0, r3, true);
        goto L8
    }

    public void setPressed(boolean r3) {
        setPressed(r3, this.bounds.centerX(), this.bounds.centerY());
    }

    public void setPressed(boolean r2, float r3, float r4) {
        this.bounce.setPressed(r2);
        if (r2 == false) goto L7;
        Drawable r0 = this.rippleDrawable;
        if (r0 == null) goto L7;
        r0.setHotspot(r3, r4);
    L7:
        Drawable r32 = this.rippleDrawable;
        if (r32 != null) goto L10;
    L14:
        this.view.invalidate();
        return;
    L10:
        if (r2 == false) goto L12;
        int[] r22 = {R.attr.state_enabled, R.attr.state_pressed};
    L13:
        r32.setState(r22);
        goto L14
    L12:
        r22 = new int[0];
        goto L13
    }
}
