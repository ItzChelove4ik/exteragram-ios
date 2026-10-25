package org.telegram.ui.Stars;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.RenderEffect;
import android.graphics.RuntimeShader;
import android.view.RoundedCorner;
import android.view.View;
import android.view.WindowInsets;
import com.google.zxing.common.detector.MathUtils;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.NotificationBadge;
import org.telegram.messenger.R;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.MotionBackgroundPaint$AgslImpl$RuntimeShaderState$$ExternalSyntheticApiModelOutline0;

/* loaded from: classes7.dex */
public class SuperRipple extends ISuperRipple {
    public final int MAX_COUNT;
    public final float[] centerX;
    public final float[] centerY;
    public int count;
    public float density;
    public RenderEffect effect;
    public final ArrayList<Effect> effects;
    public int height;
    public final float[] intensity;
    public final RuntimeShader shader;
    public final float[] t;
    public int width;

    /* renamed from: org.telegram.ui.Stars.SuperRipple$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ SuperRipple this$0;
        final /* synthetic */ Effect val$effect;

        public AnonymousClass1(SuperRipple r1, Effect r2) {
            this.this$0 = r1;
            this.val$effect = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            this.this$0.effects.remove(this.val$effect);
            SuperRipple.m19225$$Nest$mupdateProperties(this.this$0);
        }
    }

    public static class Effect {
        public final ValueAnimator animator;
        public final float cx;
        public final float cy;
        public final float intensity;
        public float t;

        private Effect(float r1, float r2, float r3, ValueAnimator r4) {
            this.cx = r1;
            this.cy = r2;
            this.intensity = r3;
            this.animator = r4;
        }

        public /* synthetic */ Effect(float r1, float r2, float r3, ValueAnimator r4, SuperRippleIA r5) {
            this(r1, r2, r3, r4);
        }
    }

    public static /* synthetic */ void $r8$lambda$ZXV_F6ezEVeW5a8ZNPcF59eQdA4(SuperRipple r0, Effect r1, ValueAnimator r2) {
        r0.lambda$animate$0(r1, r2);
    }

    /* renamed from: -$$Nest$mupdateProperties, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19225$$Nest$mupdateProperties(SuperRipple r0) {
        r0.updateProperties();
    }

    public SuperRipple(View r2) {
        super(r2);
        this.effects = new ArrayList();
        this.MAX_COUNT = 7;
        this.t = new float[7];
        this.centerX = new float[7];
        this.centerY = new float[7];
        this.intensity = new float[7];
        RuntimeShader r22 = MotionBackgroundPaint$AgslImpl$RuntimeShaderState$$ExternalSyntheticApiModelOutline0.m(AndroidUtilities.readRes(R.raw.superripple_effect));
        this.shader = r22;
        setupSizeUniforms(true);
        this.effect = RenderEffect.createRuntimeShaderEffect(r22, "img");
    }

    private /* synthetic */ void lambda$animate$0(Effect r1, ValueAnimator r2) {
        r1.t = ((Float) r2.getAnimatedValue()).floatValue();
        updateProperties();
    }

    private void setupSizeUniforms(boolean r11) {
        if (r11 == false) goto L4;
    L11:
        RuntimeShader r112 = this.shader;
        int r0 = this.view.getWidth();
        this.width = r0;
        int r1 = this.view.getHeight();
        this.height = r1;
        r112.setFloatUniform("size", r0, r1);
        RuntimeShader r113 = this.shader;
        float r02 = AndroidUtilities.density;
        this.density = r02;
        r113.setFloatUniform("density", r02);
        WindowInsets r114 = this.view.getRootWindowInsets();
        RoundedCorner r03 = null;
        if (r114 != null) goto L14;
        RoundedCorner r12 = null;
    L15:
        if (r114 != null) goto L17;
        RoundedCorner r2 = null;
    L18:
        if (r114 != null) goto L20;
        RoundedCorner r3 = null;
    L21:
        if (r114 == null) goto L24;
        r03 = r114.getRoundedCorner(2);
    L24:
        RuntimeShader r4 = this.shader;
        float r115 = 0.0f;
        if (r03 == null) goto L32;
        View r5 = this.view;
        if (r5 != r5.getRootView()) goto L29;
    L31:
        float r6 = r03.getRadius();
    L33:
        if (r2 != null) goto L35;
        float r7 = 0.0f;
    L36:
        if (r3 == null) goto L43;
        View r10 = this.view;
        if (r10 != r10.getRootView()) goto L40;
    L42:
        float r8 = r3.getRadius();
    L44:
        if (r12 == null) goto L47;
        r115 = r12.getRadius();
    L47:
        r4.setFloatUniform("radius", r6, r7, r8, r115);
        return;
    L40:
        if (AndroidUtilities.navigationBarHeight <= 0) goto L42;
    L43:
        r8 = 0.0f;
        goto L44
    L35:
        r7 = r2.getRadius();
        goto L36
    L29:
        if (AndroidUtilities.navigationBarHeight <= 0) goto L31;
    L32:
        r6 = 0.0f;
        goto L33
    L20:
        r3 = r114.getRoundedCorner(3);
        goto L21
    L17:
        r2 = r114.getRoundedCorner(1);
        goto L18
    L14:
        r12 = r114.getRoundedCorner(0);
        goto L15
    L4:
        if (this.width != this.view.getWidth()) goto L11;
        if (this.height != this.view.getHeight()) goto L11;
        if (Math.abs(this.density - AndroidUtilities.density) > 0.01f) goto L11;
    }

    private void updateProperties() {
        boolean r1 = false;
        if (this.effects.isEmpty() == true) goto L52;
        boolean r4 = true;
        if (this.count == Math.min(7, this.effects.size())) goto L7;
        boolean r0 = true;
    L8:
        this.count = Math.min(7, this.effects.size());
        int r2 = 0;
    L10:
        if (r2 >= this.count) goto L40;
        Effect r3 = this.effects.get(r2);
        if (r0 == false) goto L14;
    L17:
        boolean r02 = true;
    L18:
        this.t[r2] = r3.t;
        if (r02 == false) goto L21;
    L24:
        boolean r03 = true;
    L25:
        this.centerX[r2] = r3.cx;
        if (r03 == false) goto L28;
    L31:
        boolean r04 = true;
    L32:
        this.centerY[r2] = r3.cy;
        if (r04 == false) goto L35;
    L38:
        r0 = true;
    L39:
        this.intensity[r2] = r3.intensity;
        r2 = r2 + 1;
        goto L10
    L35:
        if (Math.abs(this.intensity[r2] - r3.intensity) > 0.001f) goto L38;
        r0 = false;
        goto L39
    L28:
        if (Math.abs(this.centerY[r2] - r3.cy) > 0.001f) goto L31;
        r04 = false;
        goto L32
    L21:
        if (Math.abs(this.centerX[r2] - r3.cx) > 0.001f) goto L24;
        r03 = false;
        goto L25
    L14:
        if (Math.abs(this.t[r2] - r3.t) > 0.001f) goto L17;
        r02 = false;
        goto L18
    L40:
        if (r0 == false) goto L42;
    L49:
        if (r4 == false) goto L51;
        this.shader.setIntUniform(NotificationBadge.NewHtcHomeBadger.COUNT, this.count);
        this.shader.setFloatUniform("t", this.t);
        this.shader.setFloatUniform("centerX", this.centerX);
        this.shader.setFloatUniform("centerY", this.centerY);
        this.shader.setFloatUniform("intensity", this.intensity);
        setupSizeUniforms(false);
        this.effect = RenderEffect.createRuntimeShaderEffect(this.shader, "img");
    L51:
        r1 = r4;
        goto L52
    L42:
        if (this.width != this.view.getWidth()) goto L49;
        if (this.height != this.view.getHeight()) goto L49;
        if (Math.abs(this.density - AndroidUtilities.density) > 0.01f) goto L49;
        r4 = false;
        goto L49
    L7:
        r0 = false;
    L52:
        View r05 = this.view;
        if (this.effects.isEmpty() == false) goto L55;
        RenderEffect r22 = null;
    L56:
        r05.setRenderEffect(r22);
        if (r1 == false) goto L63;
        this.view.invalidate();
        return;
    L63:
        return;
    L55:
        r22 = this.effect;
        goto L56
    }

    @Override // org.telegram.ui.Stars.ISuperRipple
    public void animate(float r10, float r11, float r12) {
        if (this.effects.size() < 7) goto L5;
        return;
    L5:
        float r2 = (Math.max(Math.max(MathUtils.distance(0.0f, 0.0f, r10, r11), MathUtils.distance(this.view.getWidth(), 0.0f, r10, r11)), Math.max(MathUtils.distance(0.0f, this.view.getHeight(), r10, r11), MathUtils.distance(this.view.getWidth(), this.view.getHeight(), r10, r11))) * 2.0f) / (AndroidUtilities.density * 1200.0f);
        ValueAnimator r7 = ValueAnimator.ofFloat(new float[]{0.0f, r2});
        Effect r3 = new Effect(r10, r11, r12, r7, null);
        r7.addUpdateListener(new SuperRipple$$ExternalSyntheticLambda0(this, r3));
        r7.addListener(new AnonymousClass1(this, r3));
        r7.setInterpolator(CubicBezierInterpolator.EASE_OUT);
        r7.setDuration((long) (r2 * 1000.0f));
        this.effects.add(r3);
        updateProperties();
        r7.start();
    }
}
