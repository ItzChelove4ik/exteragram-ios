package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class PinnedLineView extends View {
    float animateFromPosition;
    int animateFromTotal;
    int animateToPosition;
    int animateToTotal;
    boolean animationInProgress;
    float animationProgress;
    ValueAnimator animator;
    private int color;
    Paint fadePaint;
    Paint fadePaint2;
    private int lineHFrom;
    private int lineHTo;
    private boolean needDrawFade;
    private int nextPosition;
    Paint paint;
    RectF rectF;
    boolean replaceInProgress;
    private final Theme.ResourcesProvider resourcesProvider;
    Paint selectedPaint;
    int selectedPosition;
    private float startOffsetFrom;
    private float startOffsetTo;
    int totalCount;

    /* renamed from: org.telegram.ui.Components.PinnedLineView$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ PinnedLineView this$0;

        public AnonymousClass1(PinnedLineView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            PinnedLineView r22 = this.this$0;
            r22.animationInProgress = false;
            r22.selectedPosition = r22.animateToPosition;
            r22.invalidate();
            if (PinnedLineView.m11782$$Nest$fgetnextPosition(this.this$0) < 0) goto L6;
            PinnedLineView r23 = this.this$0;
            PinnedLineView.m11785$$Nest$mselectPosition(r23, PinnedLineView.m11782$$Nest$fgetnextPosition(r23));
            PinnedLineView.m11783$$Nest$fputnextPosition(this.this$0, -1);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.PinnedLineView$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ PinnedLineView this$0;

        public AnonymousClass2(PinnedLineView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            PinnedLineView r22 = this.this$0;
            r22.replaceInProgress = false;
            r22.animationInProgress = false;
            r22.invalidate();
            if (PinnedLineView.m11782$$Nest$fgetnextPosition(this.this$0) < 0) goto L5;
            PinnedLineView r23 = this.this$0;
            PinnedLineView.m11785$$Nest$mselectPosition(r23, PinnedLineView.m11782$$Nest$fgetnextPosition(r23));
            PinnedLineView.m11783$$Nest$fputnextPosition(this.this$0, -1);
        L5:
            PinnedLineView.m11784$$Nest$mcheckLayerType(this.this$0);
        }
    }

    public static /* synthetic */ void $r8$lambda$NZNkcEPjeON2Kd_yR1wr2esMnGg(PinnedLineView r0, ValueAnimator r1) {
        r0.lambda$set$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$sIolS8PHKTh61H2dYo5cQrxHjAo(PinnedLineView r0, ValueAnimator r1) {
        r0.lambda$selectPosition$0(r1);
    }

    /* renamed from: -$$Nest$fgetnextPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11782$$Nest$fgetnextPosition(PinnedLineView r0) {
        return r0.nextPosition;
    }

    /* renamed from: -$$Nest$fputnextPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11783$$Nest$fputnextPosition(PinnedLineView r0, int r1) {
        r0.nextPosition = r1;
    }

    /* renamed from: -$$Nest$mcheckLayerType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11784$$Nest$mcheckLayerType(PinnedLineView r0) {
        r0.checkLayerType();
    }

    /* renamed from: -$$Nest$mselectPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11785$$Nest$mselectPosition(PinnedLineView r0, int r1) {
        r0.selectPosition(r1);
    }

    public PinnedLineView(Context r22, Theme.ResourcesProvider r23) {
        super(r22);
        this.selectedPosition = -1;
        this.totalCount = 0;
        this.rectF = new RectF();
        this.paint = new Paint(1);
        this.selectedPaint = new Paint(1);
        this.nextPosition = -1;
        this.resourcesProvider = r23;
        Paint r3 = this.paint;
        Paint.Style r4 = Paint.Style.FILL;
        r3.setStyle(r4);
        Paint r32 = this.paint;
        Paint.Cap r5 = Paint.Cap.ROUND;
        r32.setStrokeCap(r5);
        this.selectedPaint.setStyle(r4);
        this.selectedPaint.setStrokeCap(r5);
        this.fadePaint = new Paint();
        Shader.TileMode r20 = Shader.TileMode.CLAMP;
        this.fadePaint.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, AndroidUtilities.dp(6.0f), new int[]{-1, 0}, new float[]{0.0f, 1.0f}, r20));
        Paint r42 = this.fadePaint;
        PorterDuff.Mode r6 = PorterDuff.Mode.DST_OUT;
        r42.setXfermode(new PorterDuffXfermode(r6));
        this.fadePaint2 = new Paint();
        this.fadePaint2.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, AndroidUtilities.dp(6.0f), new int[]{0, -1}, new float[]{0.0f, 1.0f}, r20));
        this.fadePaint2.setXfermode(new PorterDuffXfermode(r6));
        updateColors();
    }

    private void checkLayerType() {
        if (this.replaceInProgress == false) goto L5;
        int r0 = Math.max(this.animateFromTotal, this.animateToTotal);
    L6:
        int r2 = 0;
        if (r0 <= 3) goto L9;
        boolean r02 = true;
    L10:
        if (r02 == false) goto L13;
        r2 = 2;
    L13:
        if (getLayerType() == r2) goto L15;
        setLayerType(r2, null);
        invalidate();
    L15:
        this.needDrawFade = r02;
        return;
    L9:
        r02 = false;
        goto L10
    L5:
        r0 = this.totalCount;
        goto L6
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private /* synthetic */ void lambda$selectPosition$0(ValueAnimator r1) {
        this.animationProgress = ((Float) r1.getAnimatedValue()).floatValue();
        invalidate();
    }

    private /* synthetic */ void lambda$set$1(ValueAnimator r1) {
        this.animationProgress = ((Float) r1.getAnimatedValue()).floatValue();
        invalidate();
    }

    private void selectPosition(int r4) {
        if (this.replaceInProgress == false) goto L7;
        this.nextPosition = r4;
        return;
    L7:
        if (this.animationInProgress == true) goto L9;
        this.animateFromPosition = this.selectedPosition;
    L17:
        if (r4 == this.selectedPosition) goto L20;
        this.animateToPosition = r4;
        this.animationInProgress = true;
        this.animationProgress = 0.0f;
        invalidate();
        ValueAnimator r42 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.animator = r42;
        r42.addUpdateListener(new PinnedLineView$$ExternalSyntheticLambda1(this));
        this.animator.addListener(new AnonymousClass1(this));
        this.animator.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.animator.setDuration(220);
        this.animator.start();
        return;
    L20:
        return;
    L9:
        if (this.animateToPosition == r4) goto L21;
        ValueAnimator r0 = this.animator;
        if (r0 == null) goto L14;
        r0.cancel();
    L14:
        float r02 = this.animateFromPosition;
        float r1 = this.animationProgress;
        this.animateFromPosition = (r02 * (1.0f - r1)) + (this.animateToPosition * r1);
        goto L17
    }

    @Override // android.view.View
    public void onDraw(Canvas r18) {
        super.onDraw(r18);
        if (this.selectedPosition >= 0) goto L5;
        return;
    L5:
        if (this.totalCount == 0) goto L68;
        int r2 = AndroidUtilities.dp(8.0f);
        float r4 = 1.0f;
        if (this.replaceInProgress == false) goto L11;
        float r3 = this.lineHFrom;
        float r5 = this.animationProgress;
        float r32 = (r3 * (1.0f - r5)) + (this.lineHTo * r5);
    L15:
        if (r32 == 0.0f) goto L70;
        float r52 = AndroidUtilities.dpf2(0.7f);
        if (this.replaceInProgress == false) goto L21;
        float r6 = this.startOffsetFrom;
        float r8 = this.animationProgress;
        float r62 = (r6 * (1.0f - r8)) + (this.startOffsetTo * r8);
    L30:
        float r82 = getMeasuredWidth() / 2.0f;
        float r22 = r2;
        int r9 = Math.max(0, (int) (((r22 + r62) / r32) - 1.0f));
        int r10 = r9 + 6;
        if (this.replaceInProgress == false) goto L33;
        int r11 = Math.max(this.animateFromTotal, this.animateToTotal);
    L34:
        int r102 = Math.min(r10, r11);
    L35:
        if (r9 >= r102) goto L53;
        float r112 = ((r9 * r32) + r22) - r62;
        float r12 = r112 + r32;
        if (r12 >= 0.0f) goto L39;
    L40:
        float r16 = r4;
    L52:
        r9 = r9 + 1;
        r4 = r16;
        goto L35
    L39:
        if (r112 > getMeasuredHeight()) goto L40;
        this.rectF.set(0.0f, r112 + r52, getMeasuredWidth(), r12 - r52);
        boolean r113 = this.replaceInProgress;
        if (r113 == true) goto L44;
    L46:
        r16 = r4;
        if (r113 == true) goto L49;
    L51:
        r18.drawRoundRect(this.rectF, r82, r82, this.paint);
        goto L52
    L49:
        if (r9 < this.animateFromTotal) goto L51;
        this.paint.setColor(ColorUtils.setAlphaComponent(this.color, (int) (((Color.alpha(r11) / 255.0f) * 76.0f) * this.animationProgress)));
        r18.drawRoundRect(this.rectF, r82, r82, this.paint);
        this.paint.setColor(ColorUtils.setAlphaComponent(this.color, (int) ((Color.alpha(r11) / 255.0f) * 76.0f)));
        goto L52
    L44:
        if (r9 < this.animateToTotal) goto L46;
        r16 = r4;
        this.paint.setColor(ColorUtils.setAlphaComponent(this.color, (int) (((Color.alpha(r14) / 255.0f) * 76.0f) * (r16 - this.animationProgress))));
        r18.drawRoundRect(this.rectF, r82, r82, this.paint);
        this.paint.setColor(ColorUtils.setAlphaComponent(this.color, (int) ((Color.alpha(r11) / 255.0f) * 76.0f)));
        goto L52
    L53:
        float r162 = r4;
        if (this.animationInProgress == false) goto L56;
        float r42 = this.animateFromPosition;
        float r92 = this.animationProgress;
        float r23 = (r22 + (((r42 * (r162 - r92)) + (this.animateToPosition * r92)) * r32)) - r62;
        this.rectF.set(0.0f, r23 + r52, getMeasuredWidth(), (r23 + r32) - r52);
        r18.drawRoundRect(this.rectF, r82, r82, this.selectedPaint);
    L58:
        if (this.needDrawFade == false) goto L67;
        r18.drawRect(0.0f, 0.0f, getMeasuredWidth(), AndroidUtilities.dp(6.0f), this.fadePaint);
        r18.drawRect(0.0f, getMeasuredHeight() - AndroidUtilities.dp(6.0f), getMeasuredWidth(), getMeasuredHeight(), this.fadePaint);
        r18.translate(0.0f, getMeasuredHeight() - AndroidUtilities.dp(6.0f));
        r18.drawRect(0.0f, 0.0f, getMeasuredWidth(), AndroidUtilities.dp(6.0f), this.fadePaint2);
        return;
    L67:
        return;
    L56:
        float r24 = (r22 + (this.selectedPosition * r32)) - r62;
        this.rectF.set(0.0f, r24 + r52, getMeasuredWidth(), (r24 + r32) - r52);
        r18.drawRoundRect(this.rectF, r82, r82, this.selectedPaint);
        goto L58
    L33:
        r11 = this.totalCount;
        goto L34
    L21:
        if (this.animationInProgress == false) goto L23;
        float r93 = this.animationProgress;
        r62 = (((this.animateFromPosition - 1.0f) * r32) * (1.0f - r93)) + (((this.animateToPosition - 1) * r32) * r93);
    L25:
        if (r62 >= 0.0f) goto L27;
        r62 = 0.0f;
        goto L30
    L27:
        float r83 = r2;
        if (((((this.totalCount - 1) * r32) + r83) - r62) >= ((getMeasuredHeight() - r2) - r32)) goto L30;
        r62 = (r83 + ((this.totalCount - 1) * r32)) - ((getMeasuredHeight() - r2) - r32);
        goto L30
    L23:
        r62 = (this.selectedPosition - 1) * r32;
        goto L25
    L70:
        return;
    L11:
        if (this.totalCount == 0) goto L69;
        r32 = (getMeasuredHeight() - (r2 * 2)) / Math.min(this.totalCount, 3);
        goto L15
    L69:
        return;
    }

    public void set(int r8, int r9, boolean r10) {
        int r0 = this.selectedPosition;
        if (r0 < 0) goto L7;
        if (r9 == 0) goto L7;
        if (this.totalCount == 0) goto L7;
    L8:
        if (r10 == true) goto L14;
        ValueAnimator r102 = this.animator;
        if (r102 == null) goto L12;
        r102.cancel();
    L12:
        this.selectedPosition = r8;
        this.totalCount = r9;
        invalidate();
    L39:
        checkLayerType();
        return;
    L14:
        if (this.totalCount == r9) goto L16;
    L23:
        ValueAnimator r103 = this.animator;
        if (r103 == null) goto L26;
        this.nextPosition = 0;
        r103.cancel();
    L26:
        int r104 = AndroidUtilities.dp(8.0f);
        int r1 = r104 * 2;
        this.lineHFrom = (getMeasuredHeight() - r1) / Math.min(this.totalCount, 3);
        this.lineHTo = (getMeasuredHeight() - r1) / Math.min(r9, 3);
        float r02 = (this.selectedPosition - 1) * this.lineHFrom;
        this.startOffsetFrom = r02;
        if (r02 >= 0.0f) goto L29;
        this.startOffsetFrom = 0.0f;
    L32:
        float r03 = (r8 - 1) * this.lineHTo;
        this.startOffsetTo = r03;
        if (r03 >= 0.0f) goto L35;
        this.startOffsetTo = 0.0f;
    L38:
        this.animateFromPosition = this.selectedPosition;
        this.animateToPosition = r8;
        this.selectedPosition = r8;
        this.animateFromTotal = this.totalCount;
        this.animateToTotal = r9;
        this.totalCount = r9;
        this.replaceInProgress = true;
        this.animationInProgress = true;
        this.animationProgress = 0.0f;
        invalidate();
        ValueAnimator r82 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.animator = r82;
        r82.addUpdateListener(new PinnedLineView$$ExternalSyntheticLambda0(this));
        this.animator.addListener(new AnonymousClass2(this));
        this.animator.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.animator.setDuration(220);
        this.animator.start();
        goto L39
    L35:
        int r5 = r9 - 1;
        float r3 = ((r3 * r5) + r104) - r03;
        int r04 = getMeasuredHeight() - r104;
        int r6 = this.lineHTo;
        if (r3 >= (r04 - r6)) goto L38;
        this.startOffsetTo = ((r5 * r6) + r104) - ((getMeasuredHeight() - r104) - this.lineHTo);
        goto L38
    L29:
        float r32 = (((this.totalCount - 1) * r3) + r104) - r02;
        int r05 = getMeasuredHeight() - r104;
        int r52 = this.lineHFrom;
        if (r32 >= (r05 - r52)) goto L32;
        this.startOffsetFrom = (((this.totalCount - 1) * r52) + r104) - ((getMeasuredHeight() - r104) - this.lineHFrom);
        goto L32
    L16:
        if (Math.abs(r0 - r8) > 2) goto L18;
    L22:
        selectPosition(r8);
        goto L39
    L18:
        if (this.animationInProgress == true) goto L22;
        if (this.replaceInProgress == true) goto L22;
    L7:
        r10 = false;
        goto L8
    }

    public void updateColors() {
        int r0 = getThemedColor(Theme.key_chat_topPanelLine);
        this.color = r0;
        this.paint.setColor(ColorUtils.setAlphaComponent(r0, (int) ((Color.alpha(r0) / 255.0f) * 112.0f)));
        this.selectedPaint.setColor(this.color);
    }
}
