package org.telegram.ui.Components.voip;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import java.util.Objects;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LiteMode;
import org.telegram.ui.Components.MotionBackgroundDrawable;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class VoIpGradientLayout extends FrameLayout {
    private boolean allowAnimations;
    private int alphaBlueGreen;
    private int alphaBlueViolet;
    private int alphaGreen;
    private int alphaOrangeRed;
    private final VoIPBackgroundProvider backgroundProvider;
    private ValueAnimator badConnectionAnimator;
    private final Drawable bgBlueGreen;
    private final Drawable bgBlueGreenDark;
    private final Drawable bgBlueGreenLight;
    private final Drawable bgBlueViolet;
    private final Drawable bgBlueVioletDark;
    private final Drawable bgBlueVioletLight;
    private final MotionBackgroundDrawable bgGreen;
    private final MotionBackgroundDrawable bgGreenDark;
    private final MotionBackgroundDrawable bgGreenDarkReveal;
    private final MotionBackgroundDrawable bgGreenLight;
    private final MotionBackgroundDrawable bgGreenLightReveal;
    private final MotionBackgroundDrawable bgOrangeRed;
    private final MotionBackgroundDrawable bgOrangeRedDark;
    private final MotionBackgroundDrawable bgOrangeRedLight;
    private ValueAnimator callingAnimator;
    private int clipCx;
    private int clipCy;
    private final Path clipPath;
    private float clipRadius;
    private AnimatorSet connectedAnimatorSet;
    private final AnimatorSet defaultAnimatorSet;
    private boolean isPaused;
    public volatile boolean lockDrawing;
    private boolean showClip;
    private GradientState state;

    /* renamed from: org.telegram.ui.Components.voip.VoIpGradientLayout$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ VoIpGradientLayout this$0;

        public AnonymousClass1(VoIpGradientLayout r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            VoIpGradientLayout.m14003$$Nest$fputshowClip(this.this$0, false);
            VoIpGradientLayout.m14001$$Nest$fgetbackgroundProvider(this.this$0).setReveal(false);
            if (VoIpGradientLayout.m14000$$Nest$fgetallowAnimations(this.this$0) == true) goto L5;
        L7:
            VoIpGradientLayout.m14004$$Nest$mswitchToConnectedAnimator(this.this$0);
            return;
        L5:
            if (VoIpGradientLayout.m14002$$Nest$fgetdefaultAnimatorSet(this.this$0) == null) goto L7;
            VoIpGradientLayout.m14002$$Nest$fgetdefaultAnimatorSet(this.this$0).cancel();
            VoIpGradientLayout.m14002$$Nest$fgetdefaultAnimatorSet(this.this$0).start();
            goto L7
        }
    }

    public enum GradientState extends Enum<GradientState> {
        private static final /* synthetic */ GradientState[] $VALUES = null;
        public static final GradientState BAD_CONNECTION = null;
        public static final GradientState CALLING = null;
        public static final GradientState CONNECTED = null;

        private static /* synthetic */ GradientState[] $values() {
            return new GradientState[]{CALLING, CONNECTED, BAD_CONNECTION};
        }

        static {
            CALLING = new GradientState("CALLING", 0);
            CONNECTED = new GradientState("CONNECTED", 1);
            BAD_CONNECTION = new GradientState("BAD_CONNECTION", 2);
            $VALUES = $values();
        }

        GradientState(String r1, int r2) {
        }

        public static GradientState valueOf(String r1) {
            return (GradientState) Enum.valueOf(GradientState.class, r1);
        }

        public static GradientState[] values() {
            return (GradientState[]) $VALUES.clone();
        }
    }

    public class PureColorDrawable extends Drawable {
        private final int color;
        final /* synthetic */ VoIpGradientLayout this$0;

        public PureColorDrawable(VoIpGradientLayout r1, int r2) {
            this.this$0 = r1;
            this.color = r2;
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r1) {
            r1.drawColor(this.color);
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -2;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r1) {
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
        }
    }

    /* renamed from: $r8$lambda$--HMb3irTfsv1Zi2qLTjnJdx7to, reason: not valid java name */
    public static /* synthetic */ void m13999$r8$lambda$HMb3irTfsv1Zi2qLTjnJdx7to(VoIpGradientLayout r0, ValueAnimator r1) {
        r0.lambda$showToBadConnection$5(r1);
    }

    public static /* synthetic */ void $r8$lambda$0_CbgdwlmnXFaiGxw5mE9t8sX1M(VoIpGradientLayout r0, ValueAnimator r1) {
        r0.lambda$switchToCalling$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$24GHYzt0oxBGS1BxzfqmWhlW_c0(VoIpGradientLayout r0, VoIPBackgroundProvider r1, ValueAnimator r2) {
        r0.lambda$new$0(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$WqWSymJZ9o177NZ_JhdeZrvZ10o(VoIpGradientLayout r0, ValueAnimator r1) {
        r0.lambda$switchToConnectedAnimator$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$iYDWVArbzg24ciCupKszRkw3jOI(VoIpGradientLayout r0, ValueAnimator r1) {
        r0.lambda$switchToCallConnected$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$kg09YHvFYemiG2eeW9B6mDYePtA(VoIpGradientLayout r0, ValueAnimator r1) {
        r0.lambda$hideBadConnection$6(r1);
    }

    public static /* synthetic */ void $r8$lambda$t8e97wbL3O0ivRZLaWWBU9PZWvY(VoIpGradientLayout r0, ValueAnimator r1) {
        r0.lambda$switchToConnectedAnimator$4(r1);
    }

    /* renamed from: -$$Nest$fgetallowAnimations, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m14000$$Nest$fgetallowAnimations(VoIpGradientLayout r0) {
        return r0.allowAnimations;
    }

    /* renamed from: -$$Nest$fgetbackgroundProvider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ VoIPBackgroundProvider m14001$$Nest$fgetbackgroundProvider(VoIpGradientLayout r0) {
        return r0.backgroundProvider;
    }

    /* renamed from: -$$Nest$fgetdefaultAnimatorSet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet m14002$$Nest$fgetdefaultAnimatorSet(VoIpGradientLayout r0) {
        return r0.defaultAnimatorSet;
    }

    /* renamed from: -$$Nest$fputshowClip, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14003$$Nest$fputshowClip(VoIpGradientLayout r0, boolean r1) {
        r0.showClip = r1;
    }

    /* renamed from: -$$Nest$mswitchToConnectedAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14004$$Nest$mswitchToConnectedAnimator(VoIpGradientLayout r0) {
        r0.switchToConnectedAnimator();
    }

    public VoIpGradientLayout(Context r22, boolean r23, VoIPBackgroundProvider r24) {
        super(r22);
        this.alphaBlueViolet = 0;
        this.alphaBlueGreen = 0;
        this.alphaGreen = 0;
        this.alphaOrangeRed = 0;
        this.clipRadius = 0.0f;
        this.showClip = false;
        this.clipPath = new Path();
        this.clipCx = 0;
        this.clipCy = 0;
        this.isPaused = false;
        this.lockDrawing = false;
        this.backgroundProvider = r24;
        this.allowAnimations = LiteMode.isEnabled(512);
        if (r23 == false) goto L5;
        Drawable r4 = new PureColorDrawable(this, -15130842);
    L6:
        this.bgBlueViolet = r4;
        if (r23 == false) goto L9;
        Drawable r42 = new PureColorDrawable(this, -15130842);
    L10:
        this.bgBlueGreen = r42;
        this.bgGreen = new MotionBackgroundDrawable(-16275028, -16270749, -5649306, -10833593, 0, false, true);
        this.bgOrangeRed = new MotionBackgroundDrawable(-1545896, -1613425, -2387892, -2198984, 0, false, true);
        if (r23 == false) goto L13;
        Drawable r43 = new PureColorDrawable(this, -15130842);
    L14:
        this.bgBlueVioletDark = r43;
        if (r23 == false) goto L17;
        Drawable r5 = new PureColorDrawable(this, -15130842);
    L18:
        this.bgBlueGreenDark = r5;
        MotionBackgroundDrawable r6 = new MotionBackgroundDrawable(-16741490, -16673972, -7357129, -13525721, 0, false, true);
        this.bgGreenDark = r6;
        MotionBackgroundDrawable r7 = new MotionBackgroundDrawable(-1949911, -1691537, -3705322, -2663914, 0, false, true);
        this.bgOrangeRedDark = r7;
        if (r23 == false) goto L21;
        Drawable r8 = new PureColorDrawable(this, -15130842);
    L22:
        this.bgBlueVioletLight = r8;
        if (r23 == false) goto L25;
        Drawable r9 = new PureColorDrawable(this, -15130842);
    L26:
        this.bgBlueGreenLight = r9;
        MotionBackgroundDrawable r10 = new MotionBackgroundDrawable(-16723243, -16129415, -3674272, -9578153, 0, false, true);
        this.bgGreenLight = r10;
        MotionBackgroundDrawable r11 = new MotionBackgroundDrawable(-34714, -32091, -85931, -29103, 0, false, true);
        this.bgOrangeRedLight = r11;
        this.bgGreenLightReveal = new MotionBackgroundDrawable(-16723243, -16129415, -3674272, -9578153, 0, false, true);
        this.bgGreenDarkReveal = new MotionBackgroundDrawable(-16741490, -16673972, -7357129, -13525721, 0, false, true);
        r43.setBounds(0, 0, 80, 80);
        r5.setBounds(0, 0, 80, 80);
        r6.setBounds(0, 0, 80, 80);
        r7.setBounds(0, 0, 80, 80);
        r8.setBounds(0, 0, 80, 80);
        r9.setBounds(0, 0, 80, 80);
        r10.setBounds(0, 0, 80, 80);
        r11.setBounds(0, 0, 80, 80);
        setWillNotDraw(false);
        setLayerType(2, null);
        AnimatorSet r3 = new AnimatorSet();
        this.defaultAnimatorSet = r3;
        ValueAnimator r44 = ValueAnimator.ofInt(new int[]{0, 360});
        r44.addUpdateListener(new VoIpGradientLayout$$ExternalSyntheticLambda3(this, r24));
        r44.setRepeatCount(-1);
        r44.setRepeatMode(1);
        r3.setInterpolator(new LinearInterpolator());
        r3.playTogether(new Animator[]{r44});
        r3.setDuration(12000);
        if (this.allowAnimations == false) goto L29;
        r3.start();
    L29:
        switchToCalling();
        return;
    L25:
        r9 = new MotionBackgroundDrawable(-11170817, -10507265, -16458548, -14105857, 0, false, true);
        goto L26
    L21:
        r8 = new MotionBackgroundDrawable(-2726657, -7186179, -13778695, -11034113, 0, false, true);
        goto L22
    L17:
        r5 = new MotionBackgroundDrawable(-13803306, -13866273, -16738923, -16608823, 0, false, true);
        goto L18
    L13:
        r43 = new MotionBackgroundDrawable(-5818672, -9819171, -15755831, -14124319, 0, false, true);
        goto L14
    L9:
        r42 = new MotionBackgroundDrawable(-12224791, -12879119, -16207709, -15226140, 0, false, true);
        goto L10
    L5:
        r4 = new MotionBackgroundDrawable(-4958504, -8304404, -14637865, -12612630, 0, false, true);
        goto L6
    }

    private /* synthetic */ void lambda$hideBadConnection$6(ValueAnimator r1) {
        this.alphaOrangeRed = ((Integer) r1.getAnimatedValue()).intValue();
        invalidate();
        this.backgroundProvider.invalidateViews();
    }

    private /* synthetic */ void lambda$new$0(VoIPBackgroundProvider r1, ValueAnimator r2) {
        r1.setDegree(((Integer) r2.getAnimatedValue()).intValue());
        int r12 = r1.getDegree();
        if (r12 < 0) goto L7;
        if (r12 > 2) goto L7;
    L11:
        if (this.isPaused == false) goto L18;
        this.defaultAnimatorSet.pause();
        AnimatorSet r0 = this.connectedAnimatorSet;
        if (r0 == null) goto L19;
        r0.pause();
        return;
    L19:
        return;
    L18:
        return;
    L7:
        if (r12 >= 180) goto L9;
        return;
    L9:
        if (r12 <= 182) goto L11;
    }

    private /* synthetic */ void lambda$showToBadConnection$5(ValueAnimator r1) {
        this.alphaOrangeRed = ((Integer) r1.getAnimatedValue()).intValue();
        invalidate();
        this.backgroundProvider.invalidateViews();
    }

    private /* synthetic */ void lambda$switchToCallConnected$2(ValueAnimator r1) {
        this.clipRadius = ((Float) r1.getAnimatedValue()).floatValue();
        invalidate();
        this.backgroundProvider.invalidateViews();
    }

    private /* synthetic */ void lambda$switchToCalling$1(ValueAnimator r1) {
        this.alphaBlueViolet = ((Integer) r1.getAnimatedValue()).intValue();
        invalidate();
    }

    private /* synthetic */ void lambda$switchToConnectedAnimator$3(ValueAnimator r1) {
        this.alphaBlueGreen = ((Integer) r1.getAnimatedValue()).intValue();
        invalidate();
    }

    private /* synthetic */ void lambda$switchToConnectedAnimator$4(ValueAnimator r1) {
        this.alphaBlueViolet = ((Integer) r1.getAnimatedValue()).intValue();
        invalidate();
    }

    private void switchToConnectedAnimator() {
        if (this.connectedAnimatorSet == null) goto L5;
        return;
    L5:
        ValueAnimator r0 = this.callingAnimator;
        if (r0 == null) goto L8;
        r0.removeAllUpdateListeners();
        this.callingAnimator.cancel();
        this.callingAnimator = null;
    L8:
        this.alphaGreen = 255;
        this.connectedAnimatorSet = new AnimatorSet();
        ValueAnimator r2 = ValueAnimator.ofInt(new int[]{0, 255, 255, 255, 0});
        r2.addUpdateListener(new VoIpGradientLayout$$ExternalSyntheticLambda5(this));
        r2.setRepeatCount(-1);
        r2.setRepeatMode(1);
        ValueAnimator r02 = ValueAnimator.ofInt(new int[]{0, 0, 255, 0, 0});
        r02.addUpdateListener(new VoIpGradientLayout$$ExternalSyntheticLambda6(this));
        r02.setRepeatCount(-1);
        r02.setRepeatMode(1);
        this.connectedAnimatorSet.playTogether(new Animator[]{r02, r2});
        this.connectedAnimatorSet.setInterpolator(new LinearInterpolator());
        this.connectedAnimatorSet.setDuration(24000);
        if (this.allowAnimations == false) goto L11;
        this.connectedAnimatorSet.start();
    L12:
        invalidate();
        return;
    L11:
        this.alphaBlueGreen = 0;
        this.alphaBlueViolet = 0;
        goto L12
    }

    public void hideBadConnection() {
        GradientState r0 = this.state;
        GradientState r1 = GradientState.CONNECTED;
        if (r0 != r1) goto L5;
        return;
    L5:
        this.state = r1;
        switchToConnectedAnimator();
        ValueAnimator r02 = this.badConnectionAnimator;
        if (r02 == null) goto L8;
        r02.removeAllUpdateListeners();
        this.badConnectionAnimator.cancel();
    L8:
        ValueAnimator r03 = ValueAnimator.ofInt(new int[]{this.alphaOrangeRed, 0});
        this.badConnectionAnimator = r03;
        r03.addUpdateListener(new VoIpGradientLayout$$ExternalSyntheticLambda0(this));
        this.badConnectionAnimator.setDuration(500);
        this.badConnectionAnimator.start();
    }

    public boolean isConnectedCalled() {
        GradientState r1 = this.state;
        if (r1 != GradientState.CONNECTED) goto L5;
        return true;
    L5:
        if (r1 == GradientState.BAD_CONNECTION) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        AnimatorSet r0 = this.defaultAnimatorSet;
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        AnimatorSet r02 = this.connectedAnimatorSet;
        if (r02 == null) goto L8;
        r02.cancel();
    L8:
        ValueAnimator r1 = this.callingAnimator;
        if (r1 == null) goto L12;
        r1.cancel();
        return;
    }

    @Override // android.view.View
    public void onDraw(Canvas r11) {
        if (this.lockDrawing == false) goto L5;
        return;
    L5:
        float r0 = getWidth() / 2.0f;
        float r2 = getHeight() / 2.0f;
        r11.save();
        float r1 = ((float) Math.sqrt((r0 * r0) + (r2 * r0))) / Math.min(r2, r0);
        r11.scale(r1, r1, r0, r2);
        r11.rotate(this.backgroundProvider.getDegree(), r0, r2);
        Canvas r12 = this.backgroundProvider.getLightCanvas();
        PorterDuff.Mode r3 = PorterDuff.Mode.CLEAR;
        r12.drawColor(0, r3);
        this.backgroundProvider.getDarkCanvas().drawColor(0, r3);
        int r13 = this.alphaGreen;
        if (r13 != 0) goto L8;
    L10:
        int r14 = this.alphaBlueGreen;
        if (r14 != 0) goto L13;
    L15:
        int r15 = this.alphaBlueViolet;
        if (r15 != 0) goto L18;
    L20:
        int r16 = this.alphaOrangeRed;
        if (r16 == 0) goto L23;
        this.bgOrangeRed.setAlpha(r16);
        this.bgOrangeRedDark.setAlpha(this.alphaOrangeRed);
        this.bgOrangeRedLight.setAlpha(this.alphaOrangeRed);
        this.bgOrangeRed.draw(r11);
        this.bgOrangeRedDark.draw(this.backgroundProvider.getDarkCanvas());
        this.bgOrangeRedLight.draw(this.backgroundProvider.getLightCanvas());
    L23:
        r11.restore();
        if (this.showClip == false) goto L26;
        this.clipPath.rewind();
        Path r17 = this.clipPath;
        float r6 = this.clipCx;
        float r7 = this.clipCy;
        float r8 = this.clipRadius;
        Path.Direction r9 = Path.Direction.CW;
        r17.addCircle(r6, r7, r8, r9);
        r11.clipPath(this.clipPath);
        Objects.requireNonNull(this.backgroundProvider);
        Objects.requireNonNull(this.backgroundProvider);
        r11.scale(1.12f, 1.12f, r0, r2);
        this.bgGreen.setAlpha(255);
        this.bgGreen.draw(r11);
        this.clipPath.rewind();
        this.clipPath.addCircle(this.clipCx / 4.0f, this.clipCy / 4.0f, this.clipRadius / 4.0f, r9);
        this.backgroundProvider.getRevealCanvas().drawColor(0, r3);
        this.backgroundProvider.getRevealCanvas().save();
        this.backgroundProvider.getRevealCanvas().clipPath(this.clipPath);
        this.bgGreenLightReveal.setAlpha(255);
        this.bgGreenLightReveal.draw(this.backgroundProvider.getRevealCanvas());
        this.backgroundProvider.getRevealCanvas().restore();
        this.backgroundProvider.getRevealDrakCanvas().drawColor(0, r3);
        this.backgroundProvider.getRevealDrakCanvas().save();
        this.backgroundProvider.getRevealDrakCanvas().clipPath(this.clipPath);
        this.bgGreenDarkReveal.setAlpha(255);
        this.bgGreenDarkReveal.draw(this.backgroundProvider.getRevealDrakCanvas());
        this.backgroundProvider.getRevealDrakCanvas().restore();
    L26:
        super.onDraw(r11);
        return;
    L18:
        if (this.alphaOrangeRed == 255) goto L20;
        this.bgBlueViolet.setAlpha(r15);
        this.bgBlueVioletDark.setAlpha(this.alphaBlueViolet);
        this.bgBlueVioletLight.setAlpha(this.alphaBlueViolet);
        this.bgBlueViolet.draw(r11);
        this.bgBlueVioletDark.draw(this.backgroundProvider.getDarkCanvas());
        this.bgBlueVioletLight.draw(this.backgroundProvider.getLightCanvas());
        goto L20
    L13:
        if (this.alphaOrangeRed == 255) goto L15;
        this.bgBlueGreen.setAlpha(r14);
        this.bgBlueGreenDark.setAlpha(this.alphaBlueGreen);
        this.bgBlueGreenLight.setAlpha(this.alphaBlueGreen);
        this.bgBlueGreen.draw(r11);
        this.bgBlueGreenDark.draw(this.backgroundProvider.getDarkCanvas());
        this.bgBlueGreenLight.draw(this.backgroundProvider.getLightCanvas());
        goto L15
    L8:
        if (this.alphaOrangeRed == 255) goto L10;
        this.bgGreen.setAlpha(r13);
        this.bgGreenLight.setAlpha(this.alphaGreen);
        this.bgGreenDark.setAlpha(this.alphaGreen);
        this.bgGreen.draw(r11);
        this.bgGreenLight.draw(this.backgroundProvider.getLightCanvas());
        this.bgGreenDark.draw(this.backgroundProvider.getDarkCanvas());
        goto L10
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        this.bgGreen.setBounds(0, 0, getWidth(), getHeight());
        this.bgOrangeRed.setBounds(0, 0, getWidth(), getHeight());
        this.bgBlueGreen.setBounds(0, 0, getWidth(), getHeight());
        this.bgBlueViolet.setBounds(0, 0, getWidth(), getHeight());
        this.bgGreenLightReveal.setBounds(0, 0, getWidth() / 4, getHeight() / 4);
        this.bgGreenDarkReveal.setBounds(0, 0, getWidth() / 4, getHeight() / 4);
        this.backgroundProvider.setTotalSize(getWidth(), getHeight());
    }

    public void pause() {
        if (this.isPaused == false) goto L5;
        return;
    L5:
        this.isPaused = true;
    }

    public void resume() {
        if (this.isPaused == false) goto L16;
        this.isPaused = false;
        if (this.defaultAnimatorSet.isPaused() == false) goto L8;
        this.defaultAnimatorSet.resume();
    L8:
        AnimatorSet r0 = this.connectedAnimatorSet;
        if (r0 != null) goto L11;
        return;
    L11:
        if (r0.isPaused() == false) goto L15;
        this.connectedAnimatorSet.resume();
        return;
    L15:
        return;
    }

    public void showToBadConnection() {
        GradientState r0 = this.state;
        GradientState r1 = GradientState.BAD_CONNECTION;
        if (r0 != r1) goto L5;
        return;
    L5:
        this.state = r1;
        ValueAnimator r02 = ValueAnimator.ofInt(new int[]{this.alphaOrangeRed, 255});
        this.badConnectionAnimator = r02;
        r02.addUpdateListener(new VoIpGradientLayout$$ExternalSyntheticLambda2(this));
        this.badConnectionAnimator.setDuration(500);
        this.badConnectionAnimator.start();
    }

    public void switchToCallConnected(int r9, int r10, boolean r11) {
        GradientState r0 = this.state;
        GradientState r1 = GradientState.CONNECTED;
        if (r0 != r1) goto L5;
        return;
    L5:
        if (r0 == GradientState.BAD_CONNECTION) goto L17;
        this.state = r1;
        ValueAnimator r02 = this.callingAnimator;
        if (r02 == null) goto L10;
        r02.removeAllUpdateListeners();
        this.callingAnimator.cancel();
        this.callingAnimator = null;
    L10:
        this.clipCx = r9;
        this.clipCy = r10;
        Point r03 = AndroidUtilities.displaySize;
        int r12 = r03.x - r9;
        int r13 = r12 * r12;
        int r04 = ((r03.y + AndroidUtilities.statusBarHeight) + AndroidUtilities.navigationBarHeight) - r10;
        int r05 = r04 * r04;
        double r2 = Math.sqrt(r13 + r05);
        int r92 = r9 * r9;
        double r4 = Math.sqrt(r05 + r92);
        int r102 = r10 * r10;
        double r93 = Math.max(Math.max(Math.max(r2, r4), Math.sqrt(r92 + r102)), Math.sqrt(r13 + r102));
        this.showClip = true;
        this.backgroundProvider.setReveal(true);
        ValueAnimator r94 = ValueAnimator.ofFloat(new float[]{0.0f, (float) r93});
        r94.addUpdateListener(new VoIpGradientLayout$$ExternalSyntheticLambda1(this));
        r94.addListener(new AnonymousClass1(this));
        if (r11 == false) goto L13;
        long r103 = 400;
    L14:
        r94.setDuration(r103);
        r94.start();
        return;
    L13:
        r103 = 0;
        goto L14
    }

    public void switchToCalling() {
        GradientState r0 = this.state;
        GradientState r1 = GradientState.CALLING;
        if (r0 == r1) goto L10;
        this.state = r1;
        this.alphaBlueGreen = 255;
        ValueAnimator r02 = ValueAnimator.ofInt(new int[]{255, 0, 255});
        this.callingAnimator = r02;
        r02.addUpdateListener(new VoIpGradientLayout$$ExternalSyntheticLambda4(this));
        this.callingAnimator.setRepeatCount(-1);
        this.callingAnimator.setRepeatMode(1);
        this.callingAnimator.setInterpolator(new LinearInterpolator());
        this.callingAnimator.setDuration(12000);
        if (this.allowAnimations == false) goto L9;
        this.callingAnimator.start();
        return;
    L9:
        return;
    }
}
