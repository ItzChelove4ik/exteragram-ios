package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import me.vkryl.android.animator.ListAnimator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedLinearLayout;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;

/* loaded from: classes7.dex */
public class ChatActivityTopPanelLayout extends AnimatedLinearLayout {
    BlurredBackgroundDrawable backgroundDrawable;
    private FragmentContextView callFragmentContextView;
    private final Path clipPath;
    private final RectF clipRectF;

    public ChatActivityTopPanelLayout(Context r1) {
        super(r1);
        this.clipPath = new Path();
        this.clipRectF = new RectF();
        setOrientation(1);
        updateColors();
    }

    private void checkBoundsAndClipping() {
        float r0 = getMetadata().getTotalHeight();
        float r1 = getMetadata().getTotalVisibility();
        this.clipRectF.set(getPaddingLeft(), getPaddingTop(), getMeasuredWidth() - getPaddingRight(), getPaddingTop() + r0);
        float r3 = Math.min(AndroidUtilities.dp(18.0f), Math.min(this.clipRectF.width(), this.clipRectF.height()) / 2.0f);
        this.clipPath.rewind();
        this.clipPath.addRoundRect(this.clipRectF, r3, r3, Path.Direction.CW);
        BlurredBackgroundDrawable r32 = this.backgroundDrawable;
        if (r32 == null) goto L6;
        r32.setAlpha((int) (r1 * 255.0f));
        this.backgroundDrawable.setBounds(getPaddingLeft() - AndroidUtilities.dp(7.0f), 0, (getMeasuredWidth() - getPaddingRight()) + AndroidUtilities.dp(7.0f), (getPaddingTop() + getPaddingBottom()) + ((int) r0));
        this.backgroundDrawable.setRadius(Math.min(AndroidUtilities.dp(18.0f), r0 / 2.0f));
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r15) {
        if (getMetadata().getTotalVisibility() != 0.0f) goto L5;
        return;
    L5:
        BlurredBackgroundDrawable r0 = this.backgroundDrawable;
        if (r0 == null) goto L8;
        r0.draw(r15);
    L8:
        FragmentContextView r02 = this.callFragmentContextView;
        int r2 = 0;
        if (r02 == null) goto L24;
        int r03 = r02.getCurrentStyle();
        if (r03 == 3) goto L13;
        if (r03 != 1) goto L24;
    L13:
        int r04 = getEntriesCount();
        int r3 = 0;
        boolean r5 = false;
    L14:
        if (r3 >= r04) goto L25;
        ListAnimator.Entry<AnimatedLinearLayout.Holder> r6 = getEntry(r3);
        float r7 = getPaddingTop() + r6.getRectF().top;
        View r8 = r6.item.view;
        float r62 = r6.getVisibility();
        if (r62 <= 0.0f) goto L23;
        FragmentContextView r9 = this.callFragmentContextView;
        if (r9 != r8) goto L21;
    L22:
        CapsuleBlobDrawable r52 = this.callFragmentContextView.getCapsuleBlobDrawable();
        int r82 = r52.getRequiredInset();
        int r11 = -r82;
        r52.setBounds(getPaddingLeft() - r82, r11, (getMeasuredWidth() - getPaddingRight()) + r82, (AndroidUtilities.dp(36.0f) + (r82 * 2)) + r11);
        r52.setAlpha((int) (r62 * 255.0f));
        r15.save();
        r15.translate(0.0f, r7);
        r52.draw(r15);
        r15.restore();
        r5 = true;
        goto L23
    L21:
        if (r9.getParent() == r8) goto L22;
    L23:
        r3 = r3 + 1;
    L25:
        r15.save();
        r15.clipPath(this.clipPath);
        int r05 = getEntriesCount();
    L26:
        if (r2 >= r05) goto L39;
        ListAnimator.Entry<AnimatedLinearLayout.Holder> r32 = getEntry(r2);
        float r92 = getPaddingTop() + r32.getRectF().top;
        View r4 = r32.item.view;
        float r33 = r32.getVisibility() * Math.min(1.0f, r32.getPosition());
        if (r33 > 0.0f) goto L30;
    L29:
        Canvas r72 = r15;
    L38:
        r2 = r2 + 1;
        r15 = r72;
        goto L26
    L30:
        if (r5 == false) goto L37;
        FragmentContextView r63 = this.callFragmentContextView;
        if (r63 == null) goto L37;
        if (r63 == r4) goto L29;
        if (r63.getParent() == r4) goto L29;
    L37:
        int r42 = Theme.forcedDividerPaint.getAlpha();
        Theme.forcedDividerPaint.setAlpha((int) (r42 * r33));
        float r73 = 1.0f - r33;
        float r64 = getPaddingLeft() + (AndroidUtilities.dp(16.0f) * r73);
        float r34 = getPaddingRight() + (AndroidUtilities.dp(16.0f) * r73);
        r72 = r15;
        r72.drawLine(r64, r92, getWidth() - r34, r92, Theme.forcedDividerPaint);
        Theme.forcedDividerPaint.setAlpha(r42);
        goto L38
    L39:
        Canvas r74 = r15;
        super.dispatchDraw(r74);
        r74.restore();
        return;
    L24:
        r5 = false;
        goto L25
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent r2) {
        if (super.dispatchTouchEvent(r2) == false) goto L5;
        return true;
    L5:
        if (r2.getAction() != 0) goto L11;
        BlurredBackgroundDrawable r1 = this.backgroundDrawable;
        if (r1 != null) goto L9;
        return false;
    L9:
        if (r1.getBounds().contains((int) r2.getX(), (int) r2.getY()) == true) goto L17;
        return false;
    L17:
        return true;
    L11:
        return false;
    }

    @Override // org.telegram.ui.Components.AnimatedLinearLayout
    public void onItemsChanged() {
        super.onItemsChanged();
        checkBoundsAndClipping();
        invalidate();
    }

    @Override // org.telegram.ui.Components.AnimatedLinearLayout, android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        checkBoundsAndClipping();
    }

    public void setBlurredBackground(BlurredBackgroundDrawable r1) {
        this.backgroundDrawable = r1;
    }

    public void setCallFragmentContextView(FragmentContextView r1) {
        this.callFragmentContextView = r1;
        r1.getCapsuleBlobDrawable().setCallback(this);
    }

    @Override // android.view.View
    public void setPadding(int r1, int r2, int r3, int r4) {
        super.setPadding(r1, r2, r3, r4);
        checkBoundsAndClipping();
        invalidate();
    }

    public void updateColors() {
        BlurredBackgroundDrawable r0 = this.backgroundDrawable;
        if (r0 == null) goto L5;
        r0.updateColors();
    L5:
        Color.alpha(Theme.getColor(Theme.key_windowBackgroundWhite));
        invalidate();
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (super.verifyDrawable(r2) == true) goto L11;
        FragmentContextView r1 = this.callFragmentContextView;
        if (r1 != null) goto L7;
        return false;
    L7:
        if (r1.getCapsuleBlobDrawable() == r2) goto L14;
        return false;
    L14:
        return true;
    L11:
        return true;
    }
}
