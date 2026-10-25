package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
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

/* loaded from: classes3.dex */
public class DialogsActivityTopPanelLayout extends AnimatedLinearLayout {
    BlurredBackgroundDrawable backgroundDrawable;
    private FragmentContextView callFragmentContextView;
    private final Path clipPath;
    private final RectF clipRectF;
    private int defaultRadiusDp;
    private boolean exceptCall;
    private boolean onlyCall;

    public DialogsActivityTopPanelLayout(Context r1) {
        super(r1);
        this.clipPath = new Path();
        this.clipRectF = new RectF();
        this.defaultRadiusDp = 24;
        setOrientation(1);
        updateColors();
    }

    private void checkBoundsAndClipping() {
        float r0 = getMetadata().getTotalHeight();
        float r1 = getMetadata().getTotalVisibility();
        this.clipRectF.set(getPaddingLeft(), getPaddingTop(), getMeasuredWidth() - getPaddingRight(), getPaddingTop() + r0);
        float r2 = Math.min(AndroidUtilities.dp(this.defaultRadiusDp), Math.min(this.clipRectF.width(), this.clipRectF.height()) / 2.0f);
        this.clipPath.rewind();
        this.clipPath.addRoundRect(this.clipRectF, r2, r2, Path.Direction.CW);
        BlurredBackgroundDrawable r22 = this.backgroundDrawable;
        if (r22 == null) goto L6;
        r22.setAlpha((int) (r1 * 255.0f));
        this.backgroundDrawable.setBounds(AndroidUtilities.dp(4.0f), AndroidUtilities.dp(14.0f), getMeasuredWidth() - AndroidUtilities.dp(4.0f), ((getPaddingTop() + getPaddingBottom()) + ((int) r0)) - AndroidUtilities.dp(14.0f));
        this.backgroundDrawable.setRadius(Math.min(AndroidUtilities.dp(this.defaultRadiusDp), r0 / 2.0f));
        return;
    }

    private boolean isCallView(View r1) {
        FragmentContextView r0 = this.callFragmentContextView;
        if (r0 == null) goto L9;
        if (r0 != r1) goto L6;
        return true;
    L6:
        if (r0.getParent() != r1) goto L12;
        return true;
    L12:
        return false;
    L9:
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r18) {
        Canvas r1 = r18;
        if (getMetadata().getTotalVisibility() == 0.0f) goto L47;
        BlurredBackgroundDrawable r2 = this.backgroundDrawable;
        if (r2 == null) goto L8;
        r2.draw(r1);
    L8:
        FragmentContextView r22 = this.callFragmentContextView;
        View r3 = null;
        if (r22 == null) goto L22;
        int r23 = r22.getCurrentStyle();
        if (r23 == 3) goto L13;
        if (r23 != 1) goto L22;
    L13:
        int r24 = getEntriesCount();
        int r4 = 0;
    L14:
        if (r4 >= r24) goto L22;
        ListAnimator.Entry<AnimatedLinearLayout.Holder> r5 = getEntry(r4);
        float r6 = getPaddingTop() + r5.getRectF().top;
        View r10 = r5.item.view;
        float r52 = r5.getVisibility();
        if (r52 <= 0.0f) goto L21;
        if (isCallView(r10) == false) goto L21;
        CapsuleBlobDrawable r32 = this.callFragmentContextView.getCapsuleBlobDrawable();
        int r11 = r32.getRequiredInset();
        int r14 = -r11;
        r32.setBounds(getPaddingLeft() - r11, r14, (getMeasuredWidth() - getPaddingRight()) + r11, (AndroidUtilities.dp(36.0f) + (r11 * 2)) + r14);
        r32.setAlpha((int) (r52 * 255.0f));
        r1.save();
        r1.translate(0.0f, r6);
        r32.draw(r1);
        r1.restore();
        r3 = r10;
    L21:
        r4 = r4 + 1;
    L22:
        View r102 = r3;
        r1.save();
        r1.clipPath(this.clipPath);
        int r112 = getEntriesCount();
        int r12 = 0;
    L23:
        if (r12 >= r112) goto L31;
        ListAnimator.Entry<AnimatedLinearLayout.Holder> r25 = getEntry(r12);
        float r33 = getPaddingTop() + r25.getRectF().top;
        View r42 = r25.item.view;
        float r26 = r25.getVisibility() * Math.min(1.0f, r25.getPosition());
        if (r26 <= 0.0f) goto L30;
        if (r102 == r42) goto L30;
        int r13 = Theme.forcedDividerPaint.getAlpha();
        Theme.forcedDividerPaint.setAlpha((int) (r13 * r26));
        float r62 = 1.0f - r26;
        r1.drawLine(getPaddingLeft() + (AndroidUtilities.dp(16.0f) * r62), r33, getWidth() - (getPaddingRight() + (AndroidUtilities.dp(16.0f) * r62)), r33, Theme.forcedDividerPaint);
        Theme.forcedDividerPaint.setAlpha(r13);
    L30:
        r12 = r12 + 1;
        r1 = r18;
        goto L23
    L31:
        if (r102 == null) goto L33;
        boolean r15 = true;
    L34:
        this.exceptCall = r15;
        this.onlyCall = false;
        super.dispatchDraw(r18);
        r18.restore();
        if (r102 == null) goto L46;
        this.onlyCall = true;
        this.exceptCall = false;
        super.dispatchDraw(r18);
        return;
    L46:
        return;
    L33:
        r15 = false;
        goto L34
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

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas r3, View r4, long r5) {
        boolean r0 = isCallView(r4);
        if (r0 == true) goto L5;
    L6:
        if (r0 == true) goto L12;
        if (this.onlyCall == false) goto L12;
        return false;
    L12:
        return super.drawChild(r3, r4, r5);
    L5:
        if (this.exceptCall == false) goto L6;
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

    public void setDefaultRadiusDp(int r1) {
        this.defaultRadiusDp = r1;
    }

    public void updateColors() {
        BlurredBackgroundDrawable r0 = this.backgroundDrawable;
        if (r0 == null) goto L5;
        r0.updateColors();
    L5:
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
