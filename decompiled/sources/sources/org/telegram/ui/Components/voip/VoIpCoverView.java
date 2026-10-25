package org.telegram.ui.Components.voip;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LiteMode;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.CubicBezierInterpolator;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class VoIpCoverView extends View {
    private final boolean allowAnimations;
    private final VoIPBackgroundProvider backgroundProvider;
    private final Rect bgRect;
    private int connectedDiffX;
    private int diffX1;
    private int diffX2;
    private int diffX3;
    private int diffX4;
    private int diffX5;
    private int diffY1;
    private int diffY2;
    private int diffY3;
    private int diffY4;
    private int diffY5;
    private boolean isConnected;
    private boolean isEmojiExpanded;
    private boolean isPaused;
    private ValueAnimator positionAnimator;
    private final Paint saveLayerPaint;
    private VoipCoverEmoji[] voipCoverEmojiLeft;
    private VoipCoverEmoji[] voipCoverEmojiRight;

    public static /* synthetic */ void $r8$lambda$3v1w6AKqKCAS06oHU2UqkVu8Ezc(VoIpCoverView r0, ValueAnimator r1) {
        r0.lambda$onConnected$0(r1);
    }

    /* renamed from: $r8$lambda$G728BTD6k6tUGdhwvAMytA-f71E, reason: not valid java name */
    public static /* synthetic */ void m13998$r8$lambda$G728BTD6k6tUGdhwvAMytAf71E(VoIpCoverView r0, ValueAnimator r1) {
        r0.lambda$onEmojiExpanded$1(r1);
    }

    public VoIpCoverView(Context r11, TLRPC.User r12, VoIPBackgroundProvider r13) {
        super(r11);
        Paint r112 = new Paint(1);
        this.saveLayerPaint = r112;
        this.bgRect = new Rect();
        boolean r0 = LiteMode.isEnabled(512);
        this.allowAnimations = r0;
        this.backgroundProvider = r13;
        if (r0 == true) goto L5;
        return;
    L5:
        this.voipCoverEmojiLeft = new VoipCoverEmoji[]{new VoipCoverEmoji(r12, this, AndroidUtilities.dp(32.0f)), new VoipCoverEmoji(r12, this, AndroidUtilities.dp(28.0f)), new VoipCoverEmoji(r12, this, AndroidUtilities.dp(35.0f)), new VoipCoverEmoji(r12, this, AndroidUtilities.dp(28.0f)), new VoipCoverEmoji(r12, this, AndroidUtilities.dp(26.0f))};
        this.voipCoverEmojiRight = new VoipCoverEmoji[]{new VoipCoverEmoji(r12, this, AndroidUtilities.dp(32.0f)), new VoipCoverEmoji(r12, this, AndroidUtilities.dp(28.0f)), new VoipCoverEmoji(r12, this, AndroidUtilities.dp(35.0f)), new VoipCoverEmoji(r12, this, AndroidUtilities.dp(28.0f)), new VoipCoverEmoji(r12, this, AndroidUtilities.dp(26.0f))};
        r13.attach(this);
        setLayerType(2, null);
        r112.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    }

    private /* synthetic */ void lambda$onConnected$0(ValueAnimator r1) {
        int r12 = ((Integer) r1.getAnimatedValue()).intValue();
        this.diffX1 = r12;
        this.diffX2 = r12;
        this.diffX3 = r12;
        this.diffX4 = r12;
        this.diffX5 = r12;
        invalidate();
    }

    private /* synthetic */ void lambda$onEmojiExpanded$1(ValueAnimator r4) {
        float r42 = ((Float) r4.getAnimatedValue()).floatValue();
        this.diffX1 = AndroidUtilities.lerp(this.connectedDiffX, AndroidUtilities.dp(56.0f), r42);
        this.diffX2 = AndroidUtilities.lerp(this.connectedDiffX, AndroidUtilities.dp(36.0f), r42);
        this.diffX3 = AndroidUtilities.lerp(this.connectedDiffX, AndroidUtilities.dp(60.0f), r42);
        this.diffX4 = AndroidUtilities.lerp(this.connectedDiffX, AndroidUtilities.dp(36.0f), r42);
        this.diffX5 = AndroidUtilities.lerp(this.connectedDiffX, AndroidUtilities.dp(64.0f), r42);
        this.diffY1 = AndroidUtilities.lerp(0, AndroidUtilities.dp(50.0f), r42);
        this.diffY2 = AndroidUtilities.lerp(0, AndroidUtilities.dp(20.0f), r42);
        this.diffY3 = AndroidUtilities.lerp(0, 0, r42);
        this.diffY4 = AndroidUtilities.lerp(0, AndroidUtilities.dp(-20.0f), r42);
        this.diffY5 = AndroidUtilities.lerp(0, AndroidUtilities.dp(-40.0f), r42);
        invalidate();
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.allowAnimations == false) goto L11;
        VoipCoverEmoji[] r0 = this.voipCoverEmojiLeft;
        int r1 = r0.length;
        int r2 = 0;
        int r3 = 0;
    L6:
        if (r3 >= r1) goto L8;
        r0[r3].onAttachedToWindow();
        r3 = r3 + 1;
        goto L6
    L8:
        VoipCoverEmoji[] r5 = this.voipCoverEmojiRight;
        int r02 = r5.length;
    L9:
        if (r2 >= r02) goto L14;
        r5[r2].onAttachedToWindow();
        r2 = r2 + 1;
        goto L9
    L14:
        return;
    }

    public void onConnected() {
        if (this.allowAnimations == true) goto L6;
        return;
    L6:
        if (this.isConnected == false) goto L8;
        return;
    L8:
        this.isConnected = true;
        int r0 = AndroidUtilities.dp(12.0f);
        this.connectedDiffX = r0;
        ValueAnimator r02 = ValueAnimator.ofInt(new int[]{0, r0});
        this.positionAnimator = r02;
        r02.addUpdateListener(new VoIpCoverView$$ExternalSyntheticLambda0(this));
        this.positionAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT);
        this.positionAnimator.setDuration(200);
        this.positionAnimator.start();
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.allowAnimations == false) goto L18;
        VoipCoverEmoji[] r0 = this.voipCoverEmojiLeft;
        int r1 = r0.length;
        int r2 = 0;
        int r3 = 0;
    L6:
        if (r3 >= r1) goto L8;
        r0[r3].onDetachedFromWindow();
        r3 = r3 + 1;
        goto L6
    L8:
        VoipCoverEmoji[] r02 = this.voipCoverEmojiRight;
        int r12 = r02.length;
    L9:
        if (r2 >= r12) goto L11;
        r02[r2].onDetachedFromWindow();
        r2 = r2 + 1;
        goto L9
    L11:
        ValueAnimator r5 = this.positionAnimator;
        if (r5 == null) goto L17;
        r5.cancel();
        return;
    L17:
        return;
    }

    @Override // android.view.View
    public void onDraw(Canvas r18) {
        if (this.allowAnimations == true) goto L6;
        return;
    L6:
        if (this.isPaused == false) goto L8;
        return;
    L8:
        int r5 = 0;
        this.bgRect.set(0, 0, getWidth(), getHeight());
        this.backgroundProvider.setDarkTranslation(getX(), getY());
        int r2 = getMeasuredWidth() / 2;
        this.voipCoverEmojiLeft[0].setPosition((r2 - AndroidUtilities.dp(120.0f)) - this.diffX1, AndroidUtilities.dp(120.0f) - this.diffY1);
        this.voipCoverEmojiLeft[1].setPosition((r2 - AndroidUtilities.dp(180.0f)) - this.diffX2, AndroidUtilities.dp(150.0f) - this.diffY2);
        this.voipCoverEmojiLeft[2].setPosition((r2 - AndroidUtilities.dp(150.0f)) - this.diffX3, AndroidUtilities.dp(185.0f) - this.diffY3);
        this.voipCoverEmojiLeft[3].setPosition((r2 - AndroidUtilities.dp(176.0f)) - this.diffX4, AndroidUtilities.dp(240.0f) - this.diffY4);
        this.voipCoverEmojiLeft[4].setPosition((r2 - AndroidUtilities.dp(130.0f)) - this.diffX5, AndroidUtilities.dp(265.0f) - this.diffY5);
        VoipCoverEmoji[] r3 = this.voipCoverEmojiLeft;
        int r4 = r3.length;
        int r13 = 0;
    L9:
        if (r13 >= r4) goto L11;
        r3[r13].onDraw(r18);
        r13 = r13 + 1;
        goto L9
    L11:
        this.voipCoverEmojiRight[0].setPosition((AndroidUtilities.dp(50.0f) + r2) + this.diffX1, AndroidUtilities.dp(120.0f) - this.diffY1);
        this.voipCoverEmojiRight[1].setPosition((AndroidUtilities.dp(110.0f) + r2) + this.diffX2, AndroidUtilities.dp(150.0f) - this.diffY2);
        this.voipCoverEmojiRight[2].setPosition((AndroidUtilities.dp(80.0f) + r2) + this.diffX3, AndroidUtilities.dp(185.0f) - this.diffY3);
        this.voipCoverEmojiRight[3].setPosition((AndroidUtilities.dp(106.0f) + r2) + this.diffX4, AndroidUtilities.dp(240.0f) - this.diffY4);
        this.voipCoverEmojiRight[4].setPosition((r2 + AndroidUtilities.dp(60.0f)) + this.diffX5, AndroidUtilities.dp(265.0f) - this.diffY5);
        VoipCoverEmoji[] r22 = this.voipCoverEmojiRight;
        int r32 = r22.length;
    L12:
        if (r5 >= r32) goto L14;
        r22[r5].onDraw(r18);
        r5 = r5 + 1;
        goto L12
    L14:
        int r8 = this.backgroundProvider.getDarkPaint().getAlpha();
        this.saveLayerPaint.setAlpha(255);
        r18.saveLayer(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight(), this.saveLayerPaint, 31);
        this.backgroundProvider.getDarkPaint().setAlpha(255);
        r18.drawRect(this.bgRect, this.backgroundProvider.getDarkPaint());
        this.backgroundProvider.getDarkPaint().setAlpha(r8);
        if (this.backgroundProvider.isReveal() == false) goto L17;
        int r23 = this.backgroundProvider.getRevealDarkPaint().getAlpha();
        this.backgroundProvider.getRevealDarkPaint().setAlpha(255);
        r18.drawRect(this.bgRect, this.backgroundProvider.getRevealDarkPaint());
        this.backgroundProvider.getRevealDarkPaint().setAlpha(r23);
    L17:
        r18.restore();
    }

    public void onEmojiExpanded(boolean r3) {
        if (this.allowAnimations == true) goto L6;
        return;
    L6:
        if (r3 != this.isEmojiExpanded) goto L8;
        return;
    L8:
        this.isEmojiExpanded = r3;
        if (r3 == false) goto L11;
        ValueAnimator r32 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
    L12:
        this.positionAnimator = r32;
        r32.addUpdateListener(new VoIpCoverView$$ExternalSyntheticLambda1(this));
        this.positionAnimator.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.positionAnimator.setDuration(200);
        this.positionAnimator.start();
        return;
    L11:
        r32 = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f});
        goto L12
    }

    @Override // android.view.View
    public void onLayout(boolean r3, int r4, int r5, int r6, int r7) {
        super.onLayout(r3, r4, r5, r6, r7);
        if (this.allowAnimations == false) goto L11;
        VoipCoverEmoji[] r32 = this.voipCoverEmojiLeft;
        int r42 = r32.length;
        int r52 = 0;
        int r62 = 0;
    L6:
        if (r62 >= r42) goto L8;
        r32[r62].onLayout(getMeasuredWidth(), getMeasuredHeight());
        r62 = r62 + 1;
        goto L6
    L8:
        VoipCoverEmoji[] r33 = this.voipCoverEmojiRight;
        int r43 = r33.length;
    L9:
        if (r52 >= r43) goto L14;
        r33[r52].onLayout(getMeasuredWidth(), getMeasuredHeight());
        r52 = r52 + 1;
        goto L9
    L14:
        return;
    }

    public void setState(boolean r1) {
        this.isPaused = r1;
        invalidate();
    }
}
