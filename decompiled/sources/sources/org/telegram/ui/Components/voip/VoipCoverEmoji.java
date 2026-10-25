package org.telegram.ui.Components.voip;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.view.View;
import android.view.animation.LinearInterpolator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;

/* loaded from: classes7.dex */
public class VoipCoverEmoji {
    private final boolean allowAnimations;
    private int alpha;
    private int diffX;
    private ValueAnimator diffXAnimator;
    private AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable emoji;
    private int fromRandomX;
    private int fromRandomY;
    private int height;
    private boolean isShown;
    private final View parent;
    private int posX;
    private int posY;
    private ValueAnimator positionAnimator;
    private int randomX;
    private int randomY;
    private float scale;
    private final int size;
    private int toRandomX;
    private int toRandomY;
    private int width;

    /* renamed from: org.telegram.ui.Components.voip.VoipCoverEmoji$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ VoipCoverEmoji this$0;

        public AnonymousClass1(VoipCoverEmoji r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r4, boolean r5) {
            VoipCoverEmoji r42 = this.this$0;
            VoipCoverEmoji.m14027$$Nest$fputfromRandomX(r42, VoipCoverEmoji.m14025$$Nest$fgettoRandomX(r42));
            VoipCoverEmoji r43 = this.this$0;
            VoipCoverEmoji.m14028$$Nest$fputfromRandomY(r43, VoipCoverEmoji.m14026$$Nest$fgettoRandomY(r43));
            VoipCoverEmoji.m14029$$Nest$fputtoRandomX(this.this$0, Utilities.random.nextInt(AndroidUtilities.dp(16.0f)) + AndroidUtilities.dp(12.0f));
            VoipCoverEmoji.m14030$$Nest$fputtoRandomY(this.this$0, Utilities.random.nextInt(AndroidUtilities.dp(16.0f)) + AndroidUtilities.dp(12.0f));
            if (VoipCoverEmoji.m14024$$Nest$fgetpositionAnimator(this.this$0) == null) goto L6;
            VoipCoverEmoji.m14024$$Nest$fgetpositionAnimator(this.this$0).start();
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$74gEr0jiQY6KnzbITwKMQWzksEk(VoipCoverEmoji r0, View r1, ValueAnimator r2) {
        r0.lambda$new$0(r1, r2);
    }

    /* renamed from: $r8$lambda$ACNr0YmME5c1yxd0IMXFUjWf9-k, reason: not valid java name */
    public static /* synthetic */ void m14021$r8$lambda$ACNr0YmME5c1yxd0IMXFUjWf9k(VoipCoverEmoji r0, ValueAnimator r1) {
        r0.lambda$show$1(r1);
    }

    /* renamed from: $r8$lambda$qM-LD4eqksGOfZe66JXDj00m268, reason: not valid java name */
    public static /* synthetic */ void m14022$r8$lambda$qMLD4eqksGOfZe66JXDj00m268(VoipCoverEmoji r0, ValueAnimator r1) {
        r0.lambda$show$3(r1);
    }

    /* renamed from: $r8$lambda$t1dNT8WsqHFFLr4-rVTS0nrkHMg, reason: not valid java name */
    public static /* synthetic */ void m14023$r8$lambda$t1dNT8WsqHFFLr4rVTS0nrkHMg(VoipCoverEmoji r0, int r1, int r2, ValueAnimator r3) {
        r0.lambda$show$2(r1, r2, r3);
    }

    /* renamed from: -$$Nest$fgetpositionAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator m14024$$Nest$fgetpositionAnimator(VoipCoverEmoji r0) {
        return r0.positionAnimator;
    }

    /* renamed from: -$$Nest$fgettoRandomX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14025$$Nest$fgettoRandomX(VoipCoverEmoji r0) {
        return r0.toRandomX;
    }

    /* renamed from: -$$Nest$fgettoRandomY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14026$$Nest$fgettoRandomY(VoipCoverEmoji r0) {
        return r0.toRandomY;
    }

    /* renamed from: -$$Nest$fputfromRandomX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14027$$Nest$fputfromRandomX(VoipCoverEmoji r0, int r1) {
        r0.fromRandomX = r1;
    }

    /* renamed from: -$$Nest$fputfromRandomY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14028$$Nest$fputfromRandomY(VoipCoverEmoji r0, int r1) {
        r0.fromRandomY = r1;
    }

    /* renamed from: -$$Nest$fputtoRandomX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14029$$Nest$fputtoRandomX(VoipCoverEmoji r0, int r1) {
        r0.toRandomX = r1;
    }

    /* renamed from: -$$Nest$fputtoRandomY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14030$$Nest$fputtoRandomY(VoipCoverEmoji r0, int r1) {
        r0.toRandomY = r1;
    }

    public VoipCoverEmoji(TLRPC.User r7, View r8, int r9) {
        this.alpha = 0;
        this.scale = 0.0f;
        this.parent = r8;
        this.size = r9;
        boolean r1 = LiteMode.isEnabled(512);
        this.allowAnimations = r1;
        long r2 = UserObject.getProfileEmojiId(r7);
        if (r1 == true) goto L5;
        return;
    L5:
        if (r2 == 0) goto L9;
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r72 = new AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable(r8, false, r9, 13);
        this.emoji = r72;
        r72.set(r2, false);
        this.emoji.setColor(-16777216);
        this.emoji.setAlpha(this.alpha);
        ValueAnimator r73 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.positionAnimator = r73;
        r73.addUpdateListener(new VoipCoverEmoji$$ExternalSyntheticLambda0(this, r8));
        this.fromRandomX = this.toRandomX + AndroidUtilities.dp(12.0f);
        this.fromRandomY = this.toRandomY + AndroidUtilities.dp(12.0f);
        this.toRandomX = Utilities.random.nextInt(AndroidUtilities.dp(16.0f)) + AndroidUtilities.dp(12.0f);
        this.toRandomY = Utilities.random.nextInt(AndroidUtilities.dp(16.0f)) + AndroidUtilities.dp(12.0f);
        this.positionAnimator.setInterpolator(new LinearInterpolator());
        this.positionAnimator.addListener(new AnonymousClass1(this));
        this.positionAnimator.setDuration(2000);
        return;
    }

    private int getCenterX() {
        return this.width / 2;
    }

    private /* synthetic */ void lambda$new$0(View r4, ValueAnimator r5) {
        this.randomX = (int) (this.fromRandomX + ((this.toRandomX - r0) * ((Float) r5.getAnimatedValue()).floatValue()));
        this.randomY = (int) (this.fromRandomY + ((this.toRandomY - r0) * ((Float) r5.getAnimatedValue()).floatValue()));
        r4.invalidate();
    }

    private /* synthetic */ void lambda$show$1(ValueAnimator r3) {
        int r32 = ((Integer) r3.getAnimatedValue()).intValue();
        if (this.posX > getCenterX()) goto L6;
        r32 = -r32;
    L6:
        this.diffX = r32;
        this.parent.invalidate();
    }

    private /* synthetic */ void lambda$show$2(int r3, int r4, ValueAnimator r5) {
        this.scale = ((Float) r5.getAnimatedValue()).floatValue();
        this.parent.invalidate();
        if (this.scale > 1.0f) goto L5;
        return;
    L5:
        if (this.diffXAnimator != null) goto L9;
        ValueAnimator r32 = ValueAnimator.ofInt(new int[]{AndroidUtilities.dp(r3), 0});
        this.diffXAnimator = r32;
        r32.addUpdateListener(new VoipCoverEmoji$$ExternalSyntheticLambda3(this));
        this.diffXAnimator.setDuration(r4 - r5.getCurrentPlayTime());
        this.diffXAnimator.start();
        return;
    }

    private /* synthetic */ void lambda$show$3(ValueAnimator r1) {
        this.alpha = ((Integer) r1.getAnimatedValue()).intValue();
        this.parent.invalidate();
    }

    private void show() {
        if (this.isShown == false) goto L6;
        return;
    L6:
        if ((this.emoji.getDrawable() instanceof AnimatedEmojiDrawable) == false) goto L12;
        AnimatedEmojiDrawable r0 = (AnimatedEmojiDrawable) this.emoji.getDrawable();
        if (r0.getImageReceiver() != null) goto L10;
        return;
    L10:
        if (r0.getImageReceiver().hasImageLoaded() == true) goto L12;
        return;
    L12:
        this.isShown = true;
        if (this.posX <= getCenterX()) goto L15;
        int r02 = AndroidUtilities.dp(12.0f);
    L16:
        this.diffX = r02;
        ValueAnimator r03 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r03.setInterpolator(new CubicBezierInterpolator(0.34d, 1.36d, 0.64d, 1.0d));
        r03.addUpdateListener(new VoipCoverEmoji$$ExternalSyntheticLambda1(this, 12, 350));
        r03.setDuration(350);
        r03.setStartDelay(180);
        r03.start();
        ValueAnimator r04 = ValueAnimator.ofInt(new int[]{0, 255, 255});
        r04.setInterpolator(CubicBezierInterpolator.DEFAULT);
        r04.addUpdateListener(new VoipCoverEmoji$$ExternalSyntheticLambda2(this));
        r04.setStartDelay(180);
        r04.setDuration(350);
        r04.start();
        return;
    L15:
        r02 = -AndroidUtilities.dp(12.0f);
        goto L16
    }

    public void onAttachedToWindow() {
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r0 = this.emoji;
        if (r0 == null) goto L10;
        r0.attach();
        ValueAnimator r1 = this.positionAnimator;
        if (r1 == null) goto L9;
        r1.start();
        return;
    L9:
        return;
    }

    public void onDetachedFromWindow() {
        if (this.emoji != null) goto L5;
        return;
    L5:
        ValueAnimator r0 = this.positionAnimator;
        if (r0 == null) goto L8;
        r0.cancel();
        this.positionAnimator = null;
    L8:
        this.emoji.detach();
    }

    public void onDraw(Canvas r6) {
        if (this.emoji != null) goto L5;
        return;
    L5:
        r6.save();
        float r0 = this.scale;
        r6.scale(r0, r0, this.width / 2.0f, AndroidUtilities.dp(300.0f));
        r6.translate(this.posX - this.diffX, this.posY);
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r02 = this.emoji;
        int r1 = this.randomX;
        int r2 = this.randomY;
        int r3 = this.size;
        r02.setBounds(r1, r2, r1 + r3, r3 + r2);
        this.emoji.setAlpha(this.alpha);
        this.emoji.draw(r6);
        r6.restore();
    }

    public void onLayout(int r1, int r2) {
        this.width = r1;
        this.height = r2;
        this.parent.invalidate();
    }

    public void setPosition(int r2, int r3) {
        if (this.emoji != null) goto L5;
        return;
    L5:
        this.posX = r2;
        this.posY = r3;
        this.parent.invalidate();
        show();
    }
}
