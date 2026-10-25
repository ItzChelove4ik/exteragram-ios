package org.telegram.ui.Components.voip;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class VoIPStatusTextView extends FrameLayout {
    boolean animationInProgress;
    ValueAnimator animator;
    VoIPBackgroundProvider backgroundProvider;
    FrameLayout badConnectionLayer;
    TextView badConnectionTextView;
    CharSequence nextTextToSet;
    TextView reconnectTextView;
    TextView[] textView;
    boolean timerShowing;
    VoIPTimerView timerView;

    /* renamed from: org.telegram.ui.Components.voip.VoIPStatusTextView$1, reason: invalid class name */
    public class AnonymousClass1 extends TextView {
        private final RectF bgRect;
        final /* synthetic */ VoIPStatusTextView this$0;
        final /* synthetic */ VoIPBackgroundProvider val$backgroundProvider;

        public AnonymousClass1(VoIPStatusTextView r1, Context r2, VoIPBackgroundProvider r3) {
            this.this$0 = r1;
            this.val$backgroundProvider = r3;
            super(r2);
            this.bgRect = new RectF();
            r3.attach(this);
        }

        @Override // android.widget.TextView, android.view.View
        public void onDraw(Canvas r5) {
            this.bgRect.set(0.0f, 0.0f, getWidth(), getHeight());
            this.val$backgroundProvider.setDarkTranslation(((getX() + ((View) getParent()).getX()) + this.this$0.getX()) + ((View) this.this$0.getParent()).getX(), ((getY() + ((View) getParent()).getY()) + this.this$0.getY()) + ((View) this.this$0.getParent()).getY());
            r5.drawRoundRect(this.bgRect, AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), this.val$backgroundProvider.getDarkPaint());
            super.onDraw(r5);
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.VoIPStatusTextView$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ VoIPStatusTextView this$0;
        final /* synthetic */ View val$in;
        final /* synthetic */ Runnable val$onEnd;
        final /* synthetic */ View val$out;

        public static /* synthetic */ void $r8$lambda$PVilZJk4m4MVwDpqvTEUzfzC9L8(AnonymousClass2 r0) {
            r0.lambda$onAnimationEnd$0();
        }

        public AnonymousClass2(VoIPStatusTextView r1, View r2, View r3, Runnable r4) {
            this.this$0 = r1;
            this.val$out = r2;
            this.val$in = r3;
            this.val$onEnd = r4;
        }

        private /* synthetic */ void lambda$onAnimationEnd$0() {
            TextView[] r4 = this.this$0.textView;
            TextView r1 = r4[0];
            r4[0] = r4[1];
            r4[1] = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r4) {
            this.val$out.setVisibility(8);
            this.val$out.setAlpha(1.0f);
            this.val$out.setTranslationY(0.0f);
            this.val$out.setScaleY(1.0f);
            this.val$out.setScaleX(1.0f);
            this.val$in.setAlpha(1.0f);
            this.val$in.setTranslationY(0.0f);
            this.val$in.setVisibility(0);
            this.val$in.setScaleY(1.0f);
            this.val$in.setScaleX(1.0f);
            Runnable r42 = this.val$onEnd;
            if (r42 == null) goto L5;
            r42.run();
        L5:
            VoIPStatusTextView r43 = this.this$0;
            r43.animationInProgress = false;
            CharSequence r44 = r43.nextTextToSet;
            if (r44 == null) goto L13;
            boolean r45 = r44.equals("timer");
            VoIPStatusTextView r0 = this.this$0;
            if (r45 == false) goto L10;
            r0.showTimer(true);
        L11:
            this.this$0.nextTextToSet = null;
            return;
        L10:
            r0.textView[1].setText(r0.nextTextToSet);
            VoIPStatusTextView r46 = this.this$0;
            TextView[] r02 = r46.textView;
            VoIPStatusTextView.m13987$$Nest$mreplaceViews(r46, r02[0], r02[1], new VoIPStatusTextView$2$$ExternalSyntheticLambda0(this));
            goto L11
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.VoIPStatusTextView$3, reason: invalid class name */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        final /* synthetic */ VoIPStatusTextView this$0;

        public AnonymousClass3(VoIPStatusTextView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            this.this$0.reconnectTextView.setVisibility(8);
        }
    }

    /* renamed from: org.telegram.ui.Components.voip.VoIPStatusTextView$4, reason: invalid class name */
    public class AnonymousClass4 extends AnimatorListenerAdapter {
        final /* synthetic */ VoIPStatusTextView this$0;

        public AnonymousClass4(VoIPStatusTextView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            this.this$0.badConnectionLayer.setVisibility(8);
        }
    }

    public static /* synthetic */ void $r8$lambda$7qpEzghzXJzSCxBxECkhuY5Xs_4(View r2, View r3, ValueAnimator r4) {
        float r42 = ((Float) r4.getAnimatedValue()).floatValue();
        float r1 = 1.0f - r42;
        r2.setTranslationY(AndroidUtilities.dp(8.0f) * r1);
        r2.setAlpha(r42);
        r3.setTranslationY((-AndroidUtilities.dp(6.0f)) * r42);
        r3.setAlpha(r1);
    }

    public static /* synthetic */ void $r8$lambda$KgtWO1tqWTeiw9t3eh2LWwKsI8Q(VoIPStatusTextView r0) {
        r0.lambda$setText$0();
    }

    /* renamed from: -$$Nest$mreplaceViews, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13987$$Nest$mreplaceViews(VoIPStatusTextView r0, View r1, View r2, Runnable r3) {
        r0.replaceViews(r1, r2, r3);
    }

    public VoIPStatusTextView(Context r14, VoIPBackgroundProvider r15) {
        super(r14);
        this.textView = new TextView[2];
        this.backgroundProvider = r15;
        int r2 = 0;
    L4:
        if (r2 >= 2) goto L6;
        this.textView[r2] = new TextView(r14);
        this.textView[r2].setTextSize(1, 15.0f);
        this.textView[r2].setTextColor(-1);
        this.textView[r2].setGravity(1);
        addView(this.textView[r2]);
        r2 = r2 + 1;
        goto L4
    L6:
        this.badConnectionLayer = new FrameLayout(r14);
        AnonymousClass1 r0 = new AnonymousClass1(this, r14, r15);
        this.badConnectionTextView = r0;
        r0.setTextSize(1, 15.0f);
        this.badConnectionTextView.setTextColor(-1);
        this.badConnectionTextView.setGravity(1);
        this.badConnectionTextView.setPadding(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(2.0f));
        this.badConnectionTextView.setText(LocaleController.getString(R.string.VoipWeakNetwork));
        this.badConnectionLayer.addView(this.badConnectionTextView, LayoutHelper.createFrame(-2, -2.0f, 1, 0.0f, 0.0f, 0.0f, 0.0f));
        this.badConnectionLayer.setVisibility(8);
        addView(this.badConnectionLayer, LayoutHelper.createFrame(-1, -2.0f, 0, 0.0f, 44.0f, 0.0f, 0.0f));
        TextView r152 = new TextView(r14);
        this.reconnectTextView = r152;
        r152.setTextSize(1, 15.0f);
        this.reconnectTextView.setTextColor(-1);
        this.reconnectTextView.setGravity(1);
        addView(this.reconnectTextView, LayoutHelper.createFrame(-1, -2.0f, 0, 0.0f, 22.0f, 0.0f, 0.0f));
        SpannableStringBuilder r153 = new SpannableStringBuilder(LocaleController.getString(R.string.VoipReconnecting));
        SpannableString r22 = new SpannableString(".");
        r22.setSpan(new VoIPEllipsizeSpan(new View[]{this.reconnectTextView}), 0, 1, 33);
        r153.append(r22);
        this.reconnectTextView.setText(r153);
        this.reconnectTextView.setVisibility(8);
        VoIPTimerView r154 = new VoIPTimerView(r14);
        this.timerView = r154;
        addView(r154, LayoutHelper.createFrame(-1, -2.0f));
    }

    private /* synthetic */ void lambda$setText$0() {
        TextView[] r4 = this.textView;
        TextView r1 = r4[0];
        r4[0] = r4[1];
        r4[1] = r1;
    }

    private void replaceViews(View r3, View r4, Runnable r5) {
        r3.setVisibility(0);
        r4.setVisibility(0);
        r4.setTranslationY(AndroidUtilities.dp(15.0f));
        r4.setAlpha(0.0f);
        this.animationInProgress = true;
        ValueAnimator r0 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.animator = r0;
        r0.addUpdateListener(new VoIPStatusTextView$$ExternalSyntheticLambda0(r4, r3));
        this.animator.addListener(new AnonymousClass2(this, r3, r4, r5));
        this.animator.setDuration(250).setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.animator.start();
    }

    public void setDrawCallIcon() {
        this.timerView.setDrawCallIcon();
    }

    public void setSignalBarCount(int r1) {
        this.timerView.setSignalBarCount(r1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setText(String r5, boolean r6, boolean r7) {
        if (r6 == false) goto L6;
        SpannableStringBuilder r62 = new SpannableStringBuilder(r5);
        SpannableString r52 = new SpannableString(".");
        r52.setSpan(new VoIPEllipsizeSpan(this.textView), 0, 1, 33);
        r62.append(r52);
        r5 = r62;
    L6:
        if (TextUtils.isEmpty(this.textView[0].getText()) == false) goto L8;
        r7 = false;
    L8:
        if (r7 == true) goto L15;
        ValueAnimator r63 = this.animator;
        if (r63 == null) goto L12;
        r63.cancel();
    L12:
        this.animationInProgress = false;
        this.textView[0].setText(r5);
        this.textView[0].setVisibility(0);
        this.textView[1].setVisibility(8);
        this.timerView.setVisibility(8);
        return;
    L15:
        if (this.animationInProgress == false) goto L18;
        this.nextTextToSet = r5;
        return;
    L18:
        boolean r64 = this.timerShowing;
        TextView[] r72 = this.textView;
        if (r64 == false) goto L23;
        r72[0].setText(r5);
        replaceViews(this.timerView, this.textView[0], null);
        return;
    L23:
        if (r72[0].getText().equals(r5) == true) goto L26;
        this.textView[1].setText(r5);
        TextView[] r53 = this.textView;
        replaceViews(r53[0], r53[1], new VoIPStatusTextView$$ExternalSyntheticLambda1(this));
        return;
    }

    public void showBadConnection(boolean r8, boolean r9) {
        int r0 = 8;
        if (r9 == true) goto L9;
        this.badConnectionLayer.animate().setListener(null).cancel();
        FrameLayout r7 = this.badConnectionLayer;
        if (r8 == false) goto L7;
        r0 = 0;
    L7:
        r7.setVisibility(r0);
        return;
    L9:
        FrameLayout r92 = this.badConnectionLayer;
        if (r8 == false) goto L17;
        if (r92.getVisibility() == 0) goto L21;
        this.badConnectionLayer.setVisibility(0);
        this.badConnectionLayer.setAlpha(0.0f);
        this.badConnectionLayer.setScaleY(0.6f);
        this.badConnectionLayer.setScaleX(0.6f);
        this.badConnectionLayer.animate().setListener(null).cancel();
        this.badConnectionLayer.animate().alpha(1.0f).scaleX(1.0f).scaleY(1.0f).setInterpolator(CubicBezierInterpolator.EASE_OUT_BACK).setDuration(300).start();
        return;
    L21:
        return;
    L17:
        if (r92.getVisibility() != 8) goto L19;
        return;
    L19:
        this.badConnectionLayer.animate().alpha(0.0f).scaleX(0.6f).scaleY(0.6f).setInterpolator(CubicBezierInterpolator.DEFAULT).setListener(new AnonymousClass4(this)).setDuration(300).start();
    }

    public void showReconnect(boolean r6, boolean r7) {
        int r0 = 0;
        if (r7 == true) goto L10;
        this.reconnectTextView.animate().setListener(null).cancel();
        TextView r5 = this.reconnectTextView;
        if (r6 == true) goto L8;
        r0 = 8;
    L8:
        r5.setVisibility(r0);
        return;
    L10:
        TextView r72 = this.reconnectTextView;
        if (r6 == true) goto L13;
        r72.animate().alpha(0.0f).setListener(new AnonymousClass3(this)).setDuration(150).start();
        return;
    L13:
        if (r72.getVisibility() == 0) goto L15;
        this.reconnectTextView.setVisibility(0);
        this.reconnectTextView.setAlpha(0.0f);
    L15:
        this.reconnectTextView.animate().setListener(null).cancel();
        this.reconnectTextView.animate().alpha(1.0f).setDuration(150).start();
    }

    public void showTimer(boolean r4) {
        if (TextUtils.isEmpty(this.textView[0].getText()) == false) goto L6;
        r4 = false;
    L6:
        if (this.timerShowing == false) goto L8;
        return;
    L8:
        this.timerView.updateTimer();
        if (r4 == true) goto L16;
        ValueAnimator r42 = this.animator;
        if (r42 == null) goto L13;
        r42.cancel();
    L13:
        this.timerShowing = true;
        this.animationInProgress = false;
        this.textView[0].setVisibility(8);
        this.textView[1].setVisibility(8);
        this.timerView.setVisibility(0);
        return;
    L16:
        if (this.animationInProgress == false) goto L19;
        this.nextTextToSet = "timer";
        return;
    L19:
        this.timerShowing = true;
        replaceViews(this.textView[0], this.timerView, null);
    }
}
