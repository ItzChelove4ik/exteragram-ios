package org.telegram.ui.Components.voip;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.transition.ChangeBounds;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.transition.TransitionValues;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.core.content.ContextCompat;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;

/* loaded from: classes7.dex */
public class EndCloseLayout extends FrameLayout {
    private final EndCloseView endCloseView;
    private boolean isClosedState;
    private final TransitionSet transitionSet;

    /* renamed from: org.telegram.ui.Components.voip.EndCloseLayout$1, reason: invalid class name */
    public class AnonymousClass1 extends ChangeBounds {
        final /* synthetic */ EndCloseLayout this$0;

        /* renamed from: org.telegram.ui.Components.voip.EndCloseLayout$1$1, reason: invalid class name and collision with other inner class name */
        public class C00611 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass1 this$1;
            final /* synthetic */ TransitionValues val$startValues;

            public C00611(AnonymousClass1 r1, TransitionValues r2) {
                this.this$1 = r1;
                this.val$startValues = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r1) {
                super.onAnimationEnd(r1);
                this.val$startValues.view.setEnabled(true);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator r1) {
                super.onAnimationStart(r1);
                this.val$startValues.view.setEnabled(false);
            }
        }

        /* renamed from: $r8$lambda$KdGplViGpMaBlpb-eQcu0bTu2bI, reason: not valid java name */
        public static /* synthetic */ void m13814$r8$lambda$KdGplViGpMaBlpbeQcu0bTu2bI(TransitionValues r0, ValueAnimator r1) {
            EndCloseView r02 = (EndCloseView) r0.view;
            r02.round = ((Integer) r1.getAnimatedValue()).intValue();
        }

        /* renamed from: $r8$lambda$fL8P-kfDDJIPoji2n8wm-VaQaIE, reason: not valid java name */
        public static /* synthetic */ void m13815$r8$lambda$fL8PkfDDJIPoji2n8wmVaQaIE(TransitionValues r0, ValueAnimator r1) {
            EndCloseView r02 = (EndCloseView) r0.view;
            r02.backColor = ((Integer) r1.getAnimatedValue()).intValue();
        }

        public static /* synthetic */ void $r8$lambda$li6egKfxlc9lvjSJeyv9gLNLn_M(TransitionValues r0, ValueAnimator r1) {
            EndCloseView r02 = (EndCloseView) r0.view;
            r02.callDeclineAlpha = ((Integer) r1.getAnimatedValue()).intValue();
        }

        public static /* synthetic */ void $r8$lambda$sN9V0M9aH9SL7nVG3j5G5TJr0vA(TransitionValues r0, ValueAnimator r1) {
            EndCloseView r02 = (EndCloseView) r0.view;
            r02.closeTextAlpha = ((Integer) r1.getAnimatedValue()).intValue();
        }

        public AnonymousClass1(EndCloseLayout r1) {
            this.this$0 = r1;
        }

        @Override // android.transition.ChangeBounds, android.transition.Transition
        public void captureEndValues(TransitionValues r6) {
            super.captureEndValues(r6);
            View r5 = r6.view;
            if ((r5 instanceof EndCloseView) == false) goto L6;
            int r0 = ((EndCloseView) r5).backColor;
            int r1 = ((EndCloseView) r5).round;
            int r2 = ((EndCloseView) r5).callDeclineAlpha;
            int r52 = ((EndCloseView) r5).closeTextAlpha;
            r6.values.put("back_color_end_close", Integer.valueOf(r0));
            r6.values.put("round_end_close", Integer.valueOf(r1));
            r6.values.put("decline_call_alpha_end_close", Integer.valueOf(r2));
            r6.values.put("close_text_alpha_end_close", Integer.valueOf(r52));
            return;
        }

        @Override // android.transition.ChangeBounds, android.transition.Transition
        public void captureStartValues(TransitionValues r6) {
            super.captureStartValues(r6);
            View r5 = r6.view;
            if ((r5 instanceof EndCloseView) == false) goto L6;
            int r0 = ((EndCloseView) r5).backColor;
            int r1 = ((EndCloseView) r5).round;
            int r2 = ((EndCloseView) r5).callDeclineAlpha;
            int r52 = ((EndCloseView) r5).closeTextAlpha;
            r6.values.put("back_color_end_close", Integer.valueOf(r0));
            r6.values.put("round_end_close", Integer.valueOf(r1));
            r6.values.put("decline_call_alpha_end_close", Integer.valueOf(r2));
            r6.values.put("close_text_alpha_end_close", Integer.valueOf(r52));
            return;
        }

        @Override // android.transition.ChangeBounds, android.transition.Transition
        public Animator createAnimator(ViewGroup r20, TransitionValues r21, TransitionValues r22) {
            if (r21 == null) goto L13;
            if (r22 == null) goto L13;
            if ((r21.view instanceof EndCloseView) == false) goto L13;
            AnimatorSet r2 = new AnimatorSet();
            Animator r3 = super.createAnimator(r20, r21, r22);
            if (r3 == null) goto L10;
            r2.playTogether(new Animator[]{r3});
        L10:
            Integer r32 = (Integer) r21.values.get("back_color_end_close");
            Integer r4 = (Integer) r22.values.get("back_color_end_close");
            Integer r5 = (Integer) r21.values.get("round_end_close");
            Integer r6 = (Integer) r22.values.get("round_end_close");
            Integer r7 = (Integer) r21.values.get("decline_call_alpha_end_close");
            Integer r8 = (Integer) r22.values.get("decline_call_alpha_end_close");
            Integer r9 = (Integer) r21.values.get("close_text_alpha_end_close");
            Integer r1 = (Integer) r22.values.get("close_text_alpha_end_close");
            ValueAnimator r10 = new ValueAnimator();
            r10.setIntValues(new int[]{r32.intValue(), r4.intValue()});
            r10.setEvaluator(new ArgbEvaluator());
            r10.addUpdateListener(new EndCloseLayout$1$$ExternalSyntheticLambda0(r21));
            r2.playTogether(new Animator[]{r10});
            ValueAnimator r42 = ValueAnimator.ofInt(new int[]{r5.intValue(), r6.intValue()});
            r42.addUpdateListener(new EndCloseLayout$1$$ExternalSyntheticLambda1(r21));
            r2.playTogether(new Animator[]{r42});
            ValueAnimator r43 = ValueAnimator.ofInt(new int[]{r7.intValue(), r8.intValue(), r8.intValue(), r8.intValue(), r8.intValue(), r8.intValue(), r8.intValue()});
            r43.addUpdateListener(new EndCloseLayout$1$$ExternalSyntheticLambda2(r21));
            r2.playTogether(new Animator[]{r43});
            ValueAnimator r12 = ValueAnimator.ofInt(new int[]{r9.intValue(), r9.intValue(), (int) (r1.intValue() * 0.25f), (int) (r1.intValue() * 0.5f), (int) (r1.intValue() * 0.75f), r1.intValue()});
            r12.addUpdateListener(new EndCloseLayout$1$$ExternalSyntheticLambda3(r21));
            r2.playTogether(new Animator[]{r12});
            r2.addListener(new C00611(this, r21));
            return r2;
        L13:
            return super.createAnimator(r20, r21, r22);
        }
    }

    public static class EndCloseView extends View {
        public int backColor;
        private final Paint backgroundPaint;
        private final RectF backgroundRect;
        public int callDeclineAlpha;
        private final Drawable callDeclineDrawable;
        private final String closeText;
        public int closeTextAlpha;
        private Drawable rippleDrawable;
        public int round;
        private final Paint textPaint;
        private final Paint textPaintMask;

        public EndCloseView(Context r8) {
            super(r8);
            this.backgroundPaint = new Paint(1);
            Paint r82 = new Paint(1);
            this.textPaintMask = r82;
            Paint r1 = new Paint(1);
            this.textPaint = r1;
            this.backgroundRect = new RectF();
            this.backColor = -761748;
            this.round = AndroidUtilities.dp(26.0f);
            this.callDeclineAlpha = 255;
            this.closeTextAlpha = 0;
            Drawable r2 = ContextCompat.getDrawable(getContext(), R.drawable.calls_decline).mutate();
            this.callDeclineDrawable = r2;
            r2.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.MULTIPLY));
            r82.setTextSize(AndroidUtilities.dp(18.0f));
            r82.setTypeface(AndroidUtilities.bold());
            Paint.Align r3 = Paint.Align.CENTER;
            r82.setTextAlign(r3);
            r82.setColor(-16777216);
            r82.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
            r1.setTextSize(AndroidUtilities.dp(18.0f));
            r1.setTypeface(AndroidUtilities.bold());
            r1.setTextAlign(r3);
            r1.setColor(-16777216);
            setLayerType(2, null);
            setClickable(true);
            this.closeText = LocaleController.getString(R.string.Close);
        }

        @Override // android.view.View
        public boolean dispatchTouchEvent(MotionEvent r2) {
            if (isEnabled() == true) goto L7;
            return false;
        L7:
            return super.dispatchTouchEvent(r2);
        }

        @Override // android.view.View
        public void drawableStateChanged() {
            super.drawableStateChanged();
            Drawable r0 = this.rippleDrawable;
            if (r0 == null) goto L6;
            r0.setState(getDrawableState());
            return;
        }

        @Override // android.view.View
        public void jumpDrawablesToCurrentState() {
            super.jumpDrawablesToCurrentState();
            Drawable r0 = this.rippleDrawable;
            if (r0 == null) goto L6;
            r0.jumpToCurrentState();
            return;
        }

        @Override // android.view.View
        public void onDraw(Canvas r8) {
            float r0 = getWidth() / 2.0f;
            float r2 = getHeight() / 2.0f;
            this.backgroundPaint.setColor(this.backColor);
            this.backgroundRect.set(0.0f, 0.0f, getWidth(), getHeight());
            RectF r3 = this.backgroundRect;
            int r4 = this.round;
            r8.drawRoundRect(r3, r4, r4, this.backgroundPaint);
            this.callDeclineDrawable.setBounds((int) (r0 - (r3.getIntrinsicWidth() / 2.0f)), (int) (r2 - (this.callDeclineDrawable.getIntrinsicHeight() / 2)), (int) ((this.callDeclineDrawable.getIntrinsicWidth() / 2) + r0), (int) ((this.callDeclineDrawable.getIntrinsicHeight() / 2) + r2));
            this.callDeclineDrawable.setAlpha(this.callDeclineAlpha);
            this.callDeclineDrawable.draw(r8);
            this.textPaintMask.setAlpha(this.closeTextAlpha);
            this.textPaint.setAlpha((this.closeTextAlpha / 255) * 38);
            r8.drawText(this.closeText, r0, AndroidUtilities.dp(6.0f) + r2, this.textPaintMask);
            r8.drawText(this.closeText, r0, r2 + AndroidUtilities.dp(6.0f), this.textPaint);
            if (this.rippleDrawable != null) goto L5;
            Drawable r02 = Theme.createRadSelectorDrawable(Theme.getColor(Theme.key_listSelector), 8, 8);
            this.rippleDrawable = r02;
            r02.setCallback(this);
        L5:
            this.rippleDrawable.setBounds(0, 0, getWidth(), getHeight());
            this.rippleDrawable.draw(r8);
        }

        @Override // android.view.View
        public boolean verifyDrawable(Drawable r2) {
            if (this.rippleDrawable != r2) goto L5;
            return true;
        L5:
            if (super.verifyDrawable(r2) == true) goto L11;
            return false;
        L11:
            return true;
        }
    }

    public static /* synthetic */ void $r8$lambda$CVthNoqNeD37x_r3N2rrLWsn3nk(EndCloseLayout r0, View.OnClickListener r1) {
        r0.lambda$switchToClose$0(r1);
    }

    public EndCloseLayout(Context r4) {
        super(r4);
        this.isClosedState = false;
        setWillNotDraw(false);
        EndCloseView r1 = new EndCloseView(r4);
        this.endCloseView = r1;
        addView(r1, LayoutHelper.createFrame(52, 52, 5));
        TransitionSet r42 = new TransitionSet();
        this.transitionSet = r42;
        r42.setOrdering(0);
        r42.addTransition(new AnonymousClass1(this));
        r42.setDuration(500);
        r42.setInterpolator(CubicBezierInterpolator.DEFAULT);
    }

    private /* synthetic */ void lambda$switchToClose$0(View.OnClickListener r1) {
        this.endCloseView.setOnClickListener(r1);
    }

    public EndCloseView getEndCloseView() {
        return this.endCloseView;
    }

    public void switchToClose(View.OnClickListener r3, boolean r4) {
        if (this.isClosedState == false) goto L5;
        return;
    L5:
        this.isClosedState = true;
        if (r4 == false) goto L8;
        TransitionManager.beginDelayedTransition(this, this.transitionSet);
    L8:
        EndCloseView r42 = this.endCloseView;
        r42.closeTextAlpha = 255;
        r42.backColor = -1;
        r42.callDeclineAlpha = 0;
        r42.round = AndroidUtilities.dp(8.0f);
        ViewGroup.LayoutParams r43 = this.endCloseView.getLayoutParams();
        r43.width = -1;
        this.endCloseView.setLayoutParams(r43);
        AndroidUtilities.runOnUIThread(new EndCloseLayout$$ExternalSyntheticLambda0(this, r3), 500);
    }
}
