package org.telegram.ui.Components.voip;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewOutlineProvider;
import android.view.ViewParent;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.Components.CubicBezierInterpolator;

/* loaded from: classes7.dex */
public class VoIPFloatingLayout extends FrameLayout {
    private final float FLOATING_MODE_SCALE;
    private boolean active;
    public boolean alwaysFloating;
    public int bottomOffset;
    float bottomPadding;
    private VoIPFloatingLayoutDelegate delegate;
    private boolean floatingMode;
    public boolean isAppearing;
    int lastH;
    WindowInsets lastInsets;
    int lastW;
    float leftPadding;
    public boolean measuredAsFloatingMode;
    boolean moving;
    ValueAnimator mutedAnimator;
    Drawable mutedDrawable;
    Paint mutedPaint;
    float mutedProgress;
    private ValueAnimator.AnimatorUpdateListener mutedUpdateListener;
    private float overrideCornerRadius;
    final Path path;
    private ValueAnimator.AnimatorUpdateListener progressUpdateListener;
    final RectF rectF;
    public float relativePositionToSetX;
    float relativePositionToSetY;
    float rightPadding;
    public float savedRelativePositionX;
    public float savedRelativePositionY;
    private boolean setedFloatingMode;
    float starX;
    float starY;
    float startMovingFromX;
    float startMovingFromY;
    long startTime;
    ValueAnimator switchToFloatingModeAnimator;
    private boolean switchingToFloatingMode;
    public boolean switchingToPip;
    View.OnClickListener tapListener;
    float toFloatingModeProgress;
    float topPadding;
    float touchSlop;
    private boolean uiVisible;
    public float updatePositionFromX;
    public float updatePositionFromY;
    final Paint xRefPaint;

    /* renamed from: org.telegram.ui.Components.voip.VoIPFloatingLayout$1, reason: invalid class name */
    public class AnonymousClass1 implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ VoIPFloatingLayout this$0;

        public AnonymousClass1(VoIPFloatingLayout r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator r3) {
            VoIPFloatingLayout r0 = this.this$0;
            r0.toFloatingModeProgress = ((Float) r3.getAnimatedValue()).floatValue();
            if (VoIPFloatingLayout.m13949$$Nest$fgetdelegate(this.this$0) == null) goto L5;
            VoIPFloatingLayoutDelegate r32 = VoIPFloatingLayout.m13949$$Nest$fgetdelegate(this.this$0);
            VoIPFloatingLayout r02 = this.this$0;
            r32.onChange(r02.toFloatingModeProgress, r02.measuredAsFloatingMode);
        L5:
            this.this$0.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.VoIPFloatingLayout$2, reason: invalid class name */
    public class AnonymousClass2 extends ViewOutlineProvider {
        final /* synthetic */ VoIPFloatingLayout this$0;

        public AnonymousClass2(VoIPFloatingLayout r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View r7, Outline r8) {
            float r2 = VoIPFloatingLayout.m13951$$Nest$fgetoverrideCornerRadius(this.this$0);
            VoIPFloatingLayout r4 = this.this$0;
            if (r2 < 0.0f) goto L11;
            if (VoIPFloatingLayout.m13951$$Nest$fgetoverrideCornerRadius(r4) >= 1.0f) goto L8;
            r8.setRect(0, 0, r7.getMeasuredWidth(), r7.getMeasuredHeight());
            return;
        L8:
            r8.setRoundRect(0, 0, r7.getMeasuredWidth(), r7.getMeasuredHeight(), VoIPFloatingLayout.m13951$$Nest$fgetoverrideCornerRadius(this.this$0));
            return;
        L11:
            if (VoIPFloatingLayout.m13950$$Nest$fgetfloatingMode(r4) == true) goto L14;
            r8.setRect(0, 0, r7.getMeasuredWidth(), r7.getMeasuredHeight());
            return;
        L14:
            int r3 = r7.getMeasuredWidth();
            int r42 = r7.getMeasuredHeight();
            if (VoIPFloatingLayout.m13950$$Nest$fgetfloatingMode(this.this$0) == false) goto L17;
            float r5 = AndroidUtilities.dp(4.0f);
        L18:
            r8.setRoundRect(0, 0, r3, r42, r5);
            return;
        L17:
            r5 = 0.0f;
            goto L18
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.VoIPFloatingLayout$3, reason: invalid class name */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        final /* synthetic */ VoIPFloatingLayout this$0;
        final /* synthetic */ float val$toX;
        final /* synthetic */ float val$toY;

        public AnonymousClass3(VoIPFloatingLayout r1, float r2, float r3) {
            this.this$0 = r1;
            this.val$toX = r2;
            this.val$toY = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            VoIPFloatingLayout.m13954$$Nest$fputswitchingToFloatingMode(this.this$0, false);
            VoIPFloatingLayout.m13953$$Nest$fputfloatingMode(this.this$0, true);
            VoIPFloatingLayout r22 = this.this$0;
            r22.updatePositionFromX = this.val$toX;
            r22.updatePositionFromY = this.val$toY;
            r22.requestLayout();
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.VoIPFloatingLayout$4, reason: invalid class name */
    public class AnonymousClass4 implements ViewTreeObserver.OnPreDrawListener {
        final /* synthetic */ VoIPFloatingLayout this$0;
        final /* synthetic */ float val$fromX;
        final /* synthetic */ float val$fromY;

        /* renamed from: org.telegram.ui.Components.voip.VoIPFloatingLayout$4$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass4 this$1;

            public AnonymousClass1(AnonymousClass4 r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                VoIPFloatingLayout.m13954$$Nest$fputswitchingToFloatingMode(this.this$1.this$0, false);
                this.this$1.this$0.requestLayout();
            }
        }

        public AnonymousClass4(VoIPFloatingLayout r1, float r2, float r3) {
            this.this$0 = r1;
            this.val$fromX = r2;
            this.val$fromY = r3;
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            VoIPFloatingLayout r0 = this.this$0;
            if (r0.measuredAsFloatingMode == true) goto L8;
            ValueAnimator r02 = r0.switchToFloatingModeAnimator;
            if (r02 == null) goto L7;
            r02.cancel();
        L7:
            VoIPFloatingLayout r03 = this.this$0;
            r03.switchToFloatingModeAnimator = ValueAnimator.ofFloat(new float[]{r03.toFloatingModeProgress, 0.0f});
            VoIPFloatingLayout r04 = this.this$0;
            r04.switchToFloatingModeAnimator.addUpdateListener(VoIPFloatingLayout.m13952$$Nest$fgetprogressUpdateListener(r04));
            this.this$0.switchToFloatingModeAnimator.setDuration(300);
            this.this$0.switchToFloatingModeAnimator.start();
            float r05 = this.val$fromX - ((this.this$0.getMeasuredWidth() - (this.this$0.getMeasuredWidth() * 0.23f)) / 2.0f);
            float r1 = this.val$fromY - ((this.this$0.getMeasuredHeight() - (this.this$0.getMeasuredHeight() * 0.23f)) / 2.0f);
            this.this$0.getViewTreeObserver().removeOnPreDrawListener(this);
            this.this$0.setTranslationX(r05);
            this.this$0.setTranslationY(r1);
            this.this$0.setScaleX(0.23f);
            this.this$0.setScaleY(0.23f);
            this.this$0.animate().setListener(null).cancel();
            this.this$0.animate().setListener(new AnonymousClass1(this)).scaleX(1.0f).scaleY(1.0f).translationX(0.0f).translationY(0.0f).alpha(1.0f).setDuration(300).setStartDelay(0).setInterpolator(CubicBezierInterpolator.DEFAULT).start();
        L9:
            return false;
        L8:
            VoIPFloatingLayout.m13953$$Nest$fputfloatingMode(r0, false);
            this.this$0.requestLayout();
            goto L9
        }
    }

    public interface VoIPFloatingLayoutDelegate {
        void onChange(float r1, boolean r2);
    }

    public static /* synthetic */ void $r8$lambda$JKihx34zYKiC9s31WyZ4HCmtjmg(VoIPFloatingLayout r0, ValueAnimator r1) {
        r0.lambda$new$0(r1);
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ VoIPFloatingLayoutDelegate m13949$$Nest$fgetdelegate(VoIPFloatingLayout r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$fgetfloatingMode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13950$$Nest$fgetfloatingMode(VoIPFloatingLayout r0) {
        return r0.floatingMode;
    }

    /* renamed from: -$$Nest$fgetoverrideCornerRadius, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m13951$$Nest$fgetoverrideCornerRadius(VoIPFloatingLayout r0) {
        return r0.overrideCornerRadius;
    }

    /* renamed from: -$$Nest$fgetprogressUpdateListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator.AnimatorUpdateListener m13952$$Nest$fgetprogressUpdateListener(VoIPFloatingLayout r0) {
        return r0.progressUpdateListener;
    }

    /* renamed from: -$$Nest$fputfloatingMode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13953$$Nest$fputfloatingMode(VoIPFloatingLayout r0, boolean r1) {
        r0.floatingMode = r1;
    }

    /* renamed from: -$$Nest$fputswitchingToFloatingMode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13954$$Nest$fputswitchingToFloatingMode(VoIPFloatingLayout r0, boolean r1) {
        r0.switchingToFloatingMode = r1;
    }

    public VoIPFloatingLayout(Context r4) {
        super(r4);
        this.FLOATING_MODE_SCALE = 0.23f;
        this.path = new Path();
        this.rectF = new RectF();
        this.xRefPaint = new Paint(1);
        this.mutedPaint = new Paint(1);
        this.relativePositionToSetX = -1.0f;
        this.relativePositionToSetY = -1.0f;
        this.toFloatingModeProgress = 0.0f;
        this.mutedProgress = 0.0f;
        this.overrideCornerRadius = -1.0f;
        this.active = true;
        this.progressUpdateListener = new AnonymousClass1(this);
        this.mutedUpdateListener = new VoIPFloatingLayout$$ExternalSyntheticLambda0(this);
        this.touchSlop = ViewConfiguration.get(r4).getScaledTouchSlop();
        setOutlineProvider(new AnonymousClass2(this));
        setClipToOutline(true);
        this.mutedPaint.setColor(ColorUtils.setAlphaComponent(-16777216, 102));
        this.mutedDrawable = ContextCompat.getDrawable(r4, R.drawable.calls_mute_mini);
    }

    private /* synthetic */ void lambda$new$0(ValueAnimator r1) {
        this.mutedProgress = ((Float) r1.getAnimatedValue()).floatValue();
        invalidate();
    }

    private void setRelativePositionInternal(float r7, float r8, int r9, int r10, boolean r11) {
        Object r0 = getParent();
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.floatingMode == true) goto L7;
        return;
    L7:
        if (this.switchingToFloatingMode == false) goto L9;
        return;
    L9:
        if (this.active == false) goto L35;
        float r2 = 0.0f;
        if (this.lastInsets != null) goto L14;
        float r1 = 0.0f;
    L16:
        if (this.lastInsets == null) goto L19;
        r2 = r3.getSystemWindowInsetBottom() + this.bottomPadding;
    L19:
        View r02 = (View) r0;
        float r3 = this.leftPadding + ((((r02.getMeasuredWidth() - this.leftPadding) - this.rightPadding) - r9) * r7);
        float r12 = r1 + ((((r02.getMeasuredHeight() - r2) - r1) - r10) * r8);
        if (r11 == false) goto L28;
        animate().setListener(null).cancel();
        ViewPropertyAnimator r72 = animate().scaleX(1.0f).scaleY(1.0f).translationX(r3).translationY(r12).alpha(1.0f);
        if (this.uiVisible == false) goto L24;
        long r102 = 0;
    L25:
        r72.setStartDelay(r102).setDuration(150).setInterpolator(CubicBezierInterpolator.DEFAULT).start();
        return;
    L24:
        r102 = 150;
        goto L25
    L28:
        if (this.alwaysFloating == true) goto L30;
        animate().setListener(null).cancel();
        setScaleX(1.0f);
        setScaleY(1.0f);
        animate().alpha(1.0f).setDuration(150).start();
    L30:
        setTranslationX(r3);
        setTranslationY(r12);
        return;
    L14:
        r1 = r1.getSystemWindowInsetTop() + this.topPadding;
        goto L16
    }

    private void updatePadding() {
        float r0 = 16.0f;
        this.leftPadding = AndroidUtilities.dp(16.0f);
        this.rightPadding = AndroidUtilities.dp(16.0f);
        if (this.uiVisible == false) goto L6;
        int r1 = AndroidUtilities.dp(60.0f);
    L7:
        this.topPadding = r1;
        if (this.uiVisible == false) goto L10;
        r0 = 100.0f;
    L10:
        this.bottomPadding = AndroidUtilities.dp(r0) + this.bottomOffset;
        return;
    L6:
        r1 = AndroidUtilities.dp(16.0f);
        goto L7
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r11) {
        if (this.updatePositionFromX < 0.0f) goto L12;
        if (this.isAppearing == true) goto L7;
        animate().setListener(null).cancel();
    L7:
        setTranslationX(this.updatePositionFromX);
        setTranslationY(this.updatePositionFromY);
        if (this.isAppearing == true) goto L10;
        setScaleX(1.0f);
        setScaleY(1.0f);
        setAlpha(1.0f);
    L10:
        this.updatePositionFromX = -1.0f;
        this.updatePositionFromY = -1.0f;
    L12:
        if (this.relativePositionToSetX >= 0.0f) goto L14;
    L18:
        VoIPFloatingLayout r4 = this;
    L19:
        super.dispatchDraw(r11);
        if (r4.switchingToFloatingMode == true) goto L24;
        boolean r10 = r4.floatingMode;
        boolean r1 = r4.setedFloatingMode;
        if (r10 == r1) goto L24;
        r4.setFloatingMode(r1, true);
    L24:
        int r102 = r4.getMeasuredWidth() >> 1;
        int r0 = r4.getMeasuredHeight() - ((int) ((AndroidUtilities.dp(18.0f) * 1.0f) / r4.getScaleY()));
        r11.save();
        float r2 = r102;
        float r5 = r0;
        r11.scale(((1.0f / r4.getScaleX()) * r4.toFloatingModeProgress) * r4.mutedProgress, ((1.0f / r4.getScaleY()) * r4.toFloatingModeProgress) * r4.mutedProgress, r2, r5);
        r11.drawCircle(r2, r5, AndroidUtilities.dp(14.0f), r4.mutedPaint);
        Drawable r12 = r4.mutedDrawable;
        r12.setBounds(r102 - (r12.getIntrinsicWidth() / 2), r0 - (r4.mutedDrawable.getIntrinsicHeight() / 2), r102 + (r4.mutedDrawable.getIntrinsicWidth() / 2), r0 + (r4.mutedDrawable.getIntrinsicHeight() / 2));
        r4.mutedDrawable.draw(r11);
        r11.restore();
        if (r4.switchingToFloatingMode == false) goto L28;
        r4.invalidate();
        return;
    L28:
        return;
    L14:
        if (this.floatingMode == false) goto L18;
        if (getMeasuredWidth() <= 0) goto L18;
        r4 = this;
        r4.setRelativePositionInternal(this.relativePositionToSetX, this.relativePositionToSetY, getMeasuredWidth(), getMeasuredHeight(), false);
        r4.relativePositionToSetX = -1.0f;
        r4.relativePositionToSetY = -1.0f;
        goto L19
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent r1) {
        return true;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r5, int r6) {
        int r52 = View.MeasureSpec.getSize(r5);
        int r62 = View.MeasureSpec.getSize(r6);
        this.measuredAsFloatingMode = false;
        if (this.floatingMode == false) goto L6;
        r52 = (int) (r52 * 0.23f);
        r62 = (int) (r62 * 0.23f);
        this.measuredAsFloatingMode = true;
    L8:
        VoIPFloatingLayoutDelegate r0 = this.delegate;
        if (r0 == null) goto L11;
        r0.onChange(this.toFloatingModeProgress, this.measuredAsFloatingMode);
    L11:
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(r52, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r62, TLObject.FLAG_30));
        if (getMeasuredHeight() != this.lastH) goto L14;
    L16:
        this.lastH = getMeasuredHeight();
        this.lastW = getMeasuredWidth();
        updatePadding();
        return;
    L14:
        if (getMeasuredWidth() == this.lastW) goto L16;
        this.path.reset();
        this.rectF.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
        this.path.addRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), Path.Direction.CW);
        this.path.toggleInverseFillType();
        goto L16
    L6:
        if (this.switchingToPip == true) goto L8;
        setTranslationX(0.0f);
        setTranslationY(0.0f);
        goto L8
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r10) {
        ViewParent r0 = getParent();
        if (this.floatingMode == true) goto L5;
    L62:
        return false;
    L5:
        if (this.switchingToFloatingMode == true) goto L62;
        if (this.active == false) goto L62;
        int r1 = r10.getAction();
        if (r1 == 0) goto L57;
        if (r1 != 1) goto L13;
    L27:
        if (r0 != null) goto L29;
    L55:
        this.moving = false;
    L61:
        return true;
    L29:
        if (this.floatingMode == false) goto L55;
        if (this.switchingToFloatingMode == true) goto L55;
        r0.requestDisallowInterceptTouchEvent(false);
        animate().setListener(null).cancel();
        ViewPropertyAnimator r102 = animate().scaleX(1.0f).scaleY(1.0f).alpha(1.0f).setStartDelay(0);
        if (this.tapListener != null) goto L35;
    L39:
        int r02 = ((View) getParent()).getMeasuredWidth();
        int r12 = ((View) getParent()).getMeasuredHeight();
        float r3 = this.topPadding;
        float r4 = this.bottomPadding;
        if (this.lastInsets == null) goto L42;
        r3 = r3 + r5.getSystemWindowInsetTop();
        r4 = r4 + this.lastInsets.getSystemWindowInsetBottom();
    L42:
        float r5 = getX();
        float r6 = this.leftPadding;
        if (r5 >= r6) goto L46;
        r102.translationX(r6);
    L49:
        if (getY() >= r3) goto L52;
        r102.translationY(r3);
    L54:
        r102.setDuration(150).setInterpolator(CubicBezierInterpolator.DEFAULT).start();
        goto L55
    L52:
        if ((getY() + getMeasuredHeight()) <= (r12 - r4)) goto L54;
        r102.translationY((r12 - getMeasuredHeight()) - r4);
        goto L54
    L46:
        if ((getX() + getMeasuredWidth()) <= (r02 - this.rightPadding)) goto L49;
        r102.translationX((r02 - getMeasuredWidth()) - this.rightPadding);
        goto L49
    L35:
        if (this.moving == true) goto L39;
        if ((System.currentTimeMillis() - this.startTime) >= 200) goto L39;
        this.tapListener.onClick(this);
        goto L39
    L13:
        if (r1 != 2) goto L15;
        float r13 = (r10.getX() + getX()) - this.starX;
        float r2 = (r10.getY() + getY()) - this.starY;
        if (this.moving == true) goto L25;
        float r32 = (r13 * r13) + (r2 * r2);
        float r42 = this.touchSlop;
        if (r32 <= (r42 * r42)) goto L25;
        if (r0 == null) goto L23;
        r0.requestDisallowInterceptTouchEvent(true);
    L23:
        this.moving = true;
        this.starX = r10.getX() + getX();
        this.starY = r10.getY() + getY();
        this.startMovingFromX = getTranslationX();
        this.startMovingFromY = getTranslationY();
        r13 = 0.0f;
        r2 = 0.0f;
    L25:
        if (this.moving == false) goto L61;
        setTranslationX(this.startMovingFromX + r13);
        setTranslationY(this.startMovingFromY + r2);
        goto L61
    L15:
        if (r1 == 3) goto L27;
    L57:
        if (this.floatingMode == false) goto L61;
        if (this.switchingToFloatingMode == true) goto L61;
        this.startTime = System.currentTimeMillis();
        this.starX = r10.getX() + getX();
        this.starY = r10.getY() + getY();
        animate().setListener(null).cancel();
        animate().scaleY(1.05f).scaleX(1.05f).alpha(1.0f).setStartDelay(0).start();
        goto L61
    }

    public void restoreRelativePosition() {
        updatePadding();
        float r1 = this.savedRelativePositionX;
        if (r1 >= 0.0f) goto L5;
        return;
    L5:
        if (this.switchingToFloatingMode == true) goto L9;
        setRelativePositionInternal(r1, this.savedRelativePositionY, getMeasuredWidth(), getMeasuredHeight(), true);
        this.savedRelativePositionX = -1.0f;
        this.savedRelativePositionY = -1.0f;
        return;
    }

    public void saveRelativePosition() {
        if (getMeasuredWidth() > 0) goto L5;
    L19:
        this.savedRelativePositionX = -1.0f;
        this.savedRelativePositionY = -1.0f;
        return;
    L5:
        if (this.relativePositionToSetX >= 0.0f) goto L19;
        Object r0 = getParent();
        if (r0 != null) goto L10;
        return;
    L10:
        if (this.lastInsets != null) goto L12;
        float r2 = 0.0f;
    L14:
        if (this.lastInsets != null) goto L16;
        float r3 = 0.0f;
    L17:
        View r02 = (View) r0;
        this.savedRelativePositionX = (getTranslationX() - this.leftPadding) / (((r02.getMeasuredWidth() - this.leftPadding) - this.rightPadding) - getMeasuredWidth());
        this.savedRelativePositionY = (getTranslationY() - r2) / (((r02.getMeasuredHeight() - r3) - r2) - getMeasuredHeight());
        this.savedRelativePositionX = Math.max(0.0f, Math.min(1.0f, this.savedRelativePositionX));
        this.savedRelativePositionY = Math.max(0.0f, Math.min(1.0f, this.savedRelativePositionY));
        return;
    L16:
        r3 = r3.getSystemWindowInsetBottom() + this.bottomPadding;
        goto L17
    L12:
        r2 = r2.getSystemWindowInsetTop() + this.topPadding;
        goto L14
    }

    public void setBottomOffset(int r2, boolean r3) {
        if (getParent() == null) goto L8;
        if (r3 == false) goto L8;
        this.bottomOffset = r2;
        return;
    L8:
        this.bottomOffset = r2;
    }

    public void setCornerRadius(float r1) {
        this.overrideCornerRadius = r1;
        invalidateOutline();
    }

    public void setDelegate(VoIPFloatingLayoutDelegate r1) {
        this.delegate = r1;
    }

    public void setFloatingMode(boolean r12, boolean r13) {
        if (getMeasuredWidth() > 0) goto L5;
    L6:
        r13 = false;
    L7:
        float r0 = 1.0f;
        if (r13 == true) goto L18;
        if (this.floatingMode == r12) goto L46;
        this.floatingMode = r12;
        this.setedFloatingMode = r12;
        if (r12 == true) goto L15;
        r0 = 0.0f;
    L15:
        this.toFloatingModeProgress = r0;
        requestLayout();
        invalidateOutline();
        return;
    L46:
        return;
    L18:
        if (this.switchingToFloatingMode == false) goto L22;
        this.setedFloatingMode = r12;
        return;
    L22:
        if (r12 == false) goto L35;
        if (this.floatingMode == true) goto L35;
        this.floatingMode = true;
        this.setedFloatingMode = r12;
        updatePadding();
        float r6 = this.relativePositionToSetX;
        if (r6 < 0.0f) goto L28;
        VoIPFloatingLayout r5 = this;
        r5.setRelativePositionInternal(r6, this.relativePositionToSetY, (int) (getMeasuredWidth() * 0.23f), (int) (getMeasuredHeight() * 0.23f), false);
    L29:
        r5.floatingMode = false;
        r5.switchingToFloatingMode = true;
        float r11 = r5.getTranslationX();
        float r122 = r5.getTranslationY();
        r5.setTranslationX(0.0f);
        r5.setTranslationY(0.0f);
        r5.invalidate();
        ValueAnimator r2 = r5.switchToFloatingModeAnimator;
        if (r2 == null) goto L32;
        r2.cancel();
    L32:
        ValueAnimator r1 = ValueAnimator.ofFloat(new float[]{r5.toFloatingModeProgress, 1.0f});
        r5.switchToFloatingModeAnimator = r1;
        r1.addUpdateListener(r5.progressUpdateListener);
        r5.switchToFloatingModeAnimator.setDuration(300);
        r5.switchToFloatingModeAnimator.start();
        r5.animate().setListener(null).cancel();
        ViewPropertyAnimator r132 = r5.animate().scaleX(0.23f).scaleY(0.23f).translationX(r11 - ((r5.getMeasuredWidth() - (r5.getMeasuredWidth() * 0.23f)) / 2.0f)).translationY(r122 - ((r5.getMeasuredHeight() - (r5.getMeasuredHeight() * 0.23f)) / 2.0f)).alpha(1.0f).setStartDelay(0).setDuration(300);
        CubicBezierInterpolator r02 = CubicBezierInterpolator.DEFAULT;
        r132.setInterpolator(r02).setListener(new AnonymousClass3(r5, r11, r122)).setInterpolator(r02).start();
        return;
    L28:
        r5 = this;
    L35:
        if (r12 == true) goto L41;
        if (this.floatingMode == false) goto L41;
        this.setedFloatingMode = r12;
        float r112 = getTranslationX();
        float r123 = getTranslationY();
        updatePadding();
        this.floatingMode = false;
        this.switchingToFloatingMode = true;
        requestLayout();
        animate().setListener(null).cancel();
        getViewTreeObserver().addOnPreDrawListener(new AnonymousClass4(this, r112, r123));
        return;
    L41:
        if (this.floatingMode == true) goto L44;
        r0 = 0.0f;
    L44:
        this.toFloatingModeProgress = r0;
        this.floatingMode = r12;
        this.setedFloatingMode = r12;
        requestLayout();
        return;
    L5:
        if (getVisibility() == 0) goto L7;
        goto L6
    }

    public void setInsets(WindowInsets r1) {
        this.lastInsets = r1;
    }

    public void setIsActive(boolean r1) {
        this.active = r1;
    }

    public void setMuted(boolean r4, boolean r5) {
        ValueAnimator r0 = this.mutedAnimator;
        float r1 = 0.0f;
        if (r5 == true) goto L10;
        if (r0 == null) goto L6;
        r0.cancel();
    L6:
        if (r4 == false) goto L8;
        r1 = 1.0f;
    L8:
        this.mutedProgress = r1;
        invalidate();
        return;
    L10:
        if (r0 == null) goto L12;
        r0.cancel();
    L12:
        float r52 = this.mutedProgress;
        if (r4 == false) goto L15;
        r1 = 1.0f;
    L15:
        ValueAnimator r42 = ValueAnimator.ofFloat(new float[]{r52, r1});
        this.mutedAnimator = r42;
        r42.addUpdateListener(this.mutedUpdateListener);
        this.mutedAnimator.setDuration(150);
        this.mutedAnimator.start();
    }

    public void setOnTapListener(View.OnClickListener r1) {
        this.tapListener = r1;
    }

    public void setRelativePosition(float r8, float r9) {
        Object r0 = getParent();
        if (this.floatingMode == false) goto L14;
        if (r0 == null) goto L14;
        if (((View) r0).getMeasuredWidth() > 0) goto L14;
        if (getMeasuredWidth() == 0) goto L14;
        if (getMeasuredHeight() == 0) goto L14;
        setRelativePositionInternal(r8, r9, getMeasuredWidth(), getMeasuredHeight(), true);
        return;
    L14:
        this.relativePositionToSetX = r8;
        this.relativePositionToSetY = r9;
    }

    public void setRelativePosition(VoIPFloatingLayout r8) {
        Object r0 = getParent();
        if (r0 != null) goto L6;
        return;
    L6:
        if (this.lastInsets != null) goto L8;
        float r1 = 0.0f;
    L10:
        if (this.lastInsets != null) goto L12;
        float r3 = 0.0f;
    L13:
        View r02 = (View) r0;
        setRelativePosition(Math.min(1.0f, Math.max(0.0f, (r8.getTranslationX() - this.leftPadding) / (((r02.getMeasuredWidth() - this.leftPadding) - this.rightPadding) - r8.getMeasuredWidth()))), Math.min(1.0f, Math.max(0.0f, (r8.getTranslationY() - r1) / (((r02.getMeasuredHeight() - r3) - r1) - r8.getMeasuredHeight()))));
        return;
    L12:
        r3 = r3.getSystemWindowInsetBottom() + this.bottomPadding;
        goto L13
    L8:
        r1 = r1.getSystemWindowInsetTop() + this.topPadding;
        goto L10
    }

    public void setUiVisible(boolean r2) {
        if (getParent() != null) goto L6;
        this.uiVisible = r2;
        return;
    L6:
        this.uiVisible = r2;
    }
}
