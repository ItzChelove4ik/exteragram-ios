package org.telegram.ui.Components.voip;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.FrameLayout;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LiteMode;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;

/* loaded from: classes7.dex */
public class ImageWithWavesView extends FrameLayout {
    private final boolean allowAnimations;
    private AnimatorSet animatorSet;
    private final AvatarWavesDrawable avatarWavesDrawable;
    private final BackupImageView backupImageView;
    private boolean isConnectedCalled;
    private boolean isMuted;

    public static class AvatarWavesDrawable {
        float amplitude;
        float animateAmplitudeDiff;
        float animateToAmplitude;
        private ValueAnimator animator;
        private final VoipBlobDrawable blobDrawable;
        private final VoipBlobDrawable blobDrawable2;
        public boolean muteToStatic;
        private int muteToStaticInvalidationCount;
        public float muteToStaticProgress;
        boolean showWaves;
        float wavesEnter;

        public static /* synthetic */ void $r8$lambda$lzoukBAGh25MNikDW9Bh53IOR9c(AvatarWavesDrawable r0, ValueAnimator r1) {
            r0.lambda$setMuteToStatic$0(r1);
        }

        public AvatarWavesDrawable(int r3, int r4, int r5, int r6) {
            this.wavesEnter = 0.0f;
            this.muteToStatic = false;
            this.muteToStaticProgress = 1.0f;
            VoipBlobDrawable r0 = new VoipBlobDrawable(r6 - 1);
            this.blobDrawable = r0;
            VoipBlobDrawable r1 = new VoipBlobDrawable(r6);
            this.blobDrawable2 = r1;
            r0.minRadius = r3;
            r0.maxRadius = r4;
            r1.minRadius = r3 - r5;
            r1.maxRadius = r4 - r5;
            r0.generateBlob();
            r1.generateBlob();
            r0.paint.setColor(-1);
            r0.paint.setAlpha(20);
            r1.paint.setColor(-1);
            r1.paint.setAlpha(36);
        }

        private /* synthetic */ void lambda$setMuteToStatic$0(ValueAnimator r1) {
            this.muteToStaticProgress = ((Float) r1.getAnimatedValue()).floatValue();
        }

        public void draw(Canvas r6, float r7, float r8, View r9) {
            float r1 = (this.amplitude * 0.4f) + 0.8f;
            if (this.showWaves == false) goto L5;
        L6:
            r6.save();
            float r12 = r1 * CubicBezierInterpolator.DEFAULT.getInterpolation(this.wavesEnter);
            r6.scale(r12, r12, r7, r8);
            this.blobDrawable.update(this.amplitude, 1.0f, this.muteToStaticProgress);
            VoipBlobDrawable r0 = this.blobDrawable;
            r0.draw(r7, r8, r6, r0.paint);
            this.blobDrawable2.update(this.amplitude, 1.0f, this.muteToStaticProgress);
            this.blobDrawable2.draw(r7, r8, r6, this.blobDrawable.paint);
            r6.restore();
        L8:
            if (this.muteToStatic == true) goto L10;
        L12:
            int r62 = this.muteToStaticInvalidationCount;
            if (r62 == 0) goto L16;
            this.muteToStaticInvalidationCount = r62 - 1;
        L16:
            if (this.wavesEnter == 0.0f) goto L19;
            r9.invalidate();
            return;
        L19:
            return;
        L10:
            if (this.muteToStaticInvalidationCount != 0) goto L12;
            return;
        L5:
            if (this.wavesEnter == 0.0f) goto L8;
            goto L6
        }

        public void setAmplitude(double r3) {
            float r32 = ((float) r3) / 80.0f;
            float r0 = 0.0f;
            if (this.showWaves == true) goto L6;
            r32 = 0.0f;
        L6:
            if (r32 <= 1.0f) goto L9;
            r0 = 1.0f;
        L12:
            this.animateToAmplitude = r0;
            this.animateAmplitudeDiff = (r0 - this.amplitude) / 200.0f;
            return;
        L9:
            if (r32 < 0.0f) goto L12;
            r0 = r32;
            goto L12
        }

        public void setMuteToStatic(boolean r4, boolean r5, View r6) {
            if (this.muteToStatic == r4) goto L17;
            this.muteToStatic = r4;
            ValueAnimator r0 = this.animator;
            if (r0 == null) goto L8;
            r0.removeAllUpdateListeners();
            this.animator.cancel();
        L8:
            if (r4 == false) goto L10;
            this.animator = ValueAnimator.ofFloat(new float[]{this.muteToStaticProgress, 0.0f});
            this.muteToStaticInvalidationCount = (int) (2000.0f / AndroidUtilities.screenRefreshTime);
        L11:
            this.animator.addUpdateListener(new ImageWithWavesView$AvatarWavesDrawable$$ExternalSyntheticLambda0(this));
            ValueAnimator r42 = this.animator;
            if (r5 == false) goto L14;
            r42.setDuration(150);
        L15:
            this.animator.start();
            r6.invalidate();
            return;
        L14:
            r42.setDuration(1000);
            goto L15
        L10:
            this.muteToStaticInvalidationCount = 0;
            this.animator = ValueAnimator.ofFloat(new float[]{this.muteToStaticProgress, 1.0f});
            goto L11
        }

        public void setShowWaves(boolean r2, View r3) {
            if (this.showWaves == r2) goto L5;
            r3.invalidate();
        L5:
            this.showWaves = r2;
        }

        public void update() {
            float r0 = this.animateToAmplitude;
            float r1 = this.amplitude;
            if (r0 == r1) goto L12;
            float r2 = this.animateAmplitudeDiff;
            float r12 = r1 + (16.0f * r2);
            this.amplitude = r12;
            if (r2 <= 0.0f) goto L10;
            if (r12 <= r0) goto L12;
            this.amplitude = r0;
            goto L12
        L10:
            if (r12 >= r0) goto L12;
            this.amplitude = r0;
        L12:
            boolean r02 = this.showWaves;
            if (r02 == false) goto L20;
            float r22 = this.wavesEnter;
            if (r22 == 1.0f) goto L20;
            float r23 = r22 + 0.045714285f;
            this.wavesEnter = r23;
            if (r23 <= 1.0f) goto L27;
            this.wavesEnter = 1.0f;
            return;
        L27:
            return;
        L20:
            if (r02 == true) goto L28;
            float r03 = this.wavesEnter;
            if (r03 == 0.0f) goto L29;
            float r04 = r03 - 0.045714285f;
            this.wavesEnter = r04;
            if (r04 >= 0.0f) goto L30;
            this.wavesEnter = 0.0f;
            return;
        L30:
            return;
        L29:
            return;
        }
    }

    public ImageWithWavesView(Context r6) {
        super(r6);
        AvatarWavesDrawable r0 = new AvatarWavesDrawable(AndroidUtilities.dp(104.0f), AndroidUtilities.dp(111.0f), AndroidUtilities.dp(12.0f), 8);
        this.avatarWavesDrawable = r0;
        r0.setAmplitude(3.0d);
        r0.setShowWaves(true, this);
        BackupImageView r02 = new BackupImageView(r6);
        this.backupImageView = r02;
        addView(r02, LayoutHelper.createFrame(135, 135, 17));
        setWillNotDraw(false);
        AnimatorSet r03 = new AnimatorSet();
        this.animatorSet = r03;
        r03.playTogether(new Animator[]{ObjectAnimator.ofFloat(this, View.SCALE_X, new float[]{1.0f, 1.05f, 1.0f, 1.05f, 1.0f}), ObjectAnimator.ofFloat(this, View.SCALE_Y, new float[]{1.0f, 1.05f, 1.0f, 1.05f, 1.0f})});
        this.animatorSet.setInterpolator(CubicBezierInterpolator.EASE_OUT);
        this.animatorSet.setDuration(3000);
        boolean r04 = LiteMode.isEnabled(512);
        this.allowAnimations = r04;
        if (r04 == false) goto L5;
        this.animatorSet.start();
    L5:
        setClipChildren(false);
    }

    public void onConnected() {
        if (this.isConnectedCalled == false) goto L5;
        return;
    L5:
        AnimatorSet r0 = this.animatorSet;
        if (r0 == null) goto L8;
        r0.cancel();
    L8:
        this.isConnectedCalled = true;
        AnimatorSet r1 = new AnimatorSet();
        this.animatorSet = r1;
        r1.playTogether(new Animator[]{ObjectAnimator.ofFloat(this, View.SCALE_X, new float[]{getScaleX(), 1.05f, 1.0f}), ObjectAnimator.ofFloat(this, View.SCALE_Y, new float[]{getScaleY(), 1.05f, 1.0f})});
        this.animatorSet.setInterpolator(CubicBezierInterpolator.EASE_OUT);
        this.animatorSet.setDuration(400);
        this.animatorSet.start();
    }

    @Override // android.view.View
    public void onDraw(Canvas r4) {
        if (this.allowAnimations == false) goto L5;
        this.avatarWavesDrawable.update();
        this.avatarWavesDrawable.draw(r4, getWidth() / 2, getHeight() / 2, this);
    L5:
        super.onDraw(r4);
    }

    public void onNeedRating() {
        setShowWaves(false);
        AnimatorSet r1 = this.animatorSet;
        if (r1 == null) goto L5;
        r1.cancel();
    L5:
        setAlpha(1.0f);
        AnimatorSet r2 = new AnimatorSet();
        this.animatorSet = r2;
        r2.playTogether(new Animator[]{ObjectAnimator.ofFloat(this, View.TRANSLATION_Y, new float[]{getTranslationY(), -AndroidUtilities.dp(24.0f)}), ObjectAnimator.ofFloat(this, View.SCALE_X, new float[]{getScaleX(), 0.9f, 1.0f}), ObjectAnimator.ofFloat(this, View.SCALE_Y, new float[]{getScaleY(), 0.9f, 1.0f})});
        this.animatorSet.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.animatorSet.setDuration(300);
        this.animatorSet.setStartDelay(250);
        this.animatorSet.start();
    }

    public void setAmplitude(double r3) {
        if (this.isMuted == false) goto L5;
        return;
    L5:
        AvatarWavesDrawable r2 = this.avatarWavesDrawable;
        if (r3 <= 1.5d) goto L9;
        r2.setAmplitude(r3);
        return;
    L9:
        r2.setAmplitude(0.0d);
    }

    public void setImage(ImageLocation r1, String r2, Drawable r3, Object r4) {
        this.backupImageView.setImage(r1, r2, r3, r4);
    }

    public void setMute(boolean r4, boolean r5) {
        if (this.isMuted == r4) goto L9;
        this.isMuted = r4;
        if (r4 == false) goto L7;
        this.avatarWavesDrawable.setAmplitude(3.0d);
    L7:
        this.avatarWavesDrawable.setMuteToStatic(r4, r5, this);
        return;
    }

    public void setRoundRadius(int r1) {
        this.backupImageView.setRoundRadius(r1);
    }

    public void setShowWaves(boolean r2) {
        this.avatarWavesDrawable.setShowWaves(r2, this);
    }
}
