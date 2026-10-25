package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.view.View;
import com.google.android.gms.cast.MediaError;
import java.util.ArrayList;
import org.telegram.ui.Components.Reactions.HwEmojis;

/* loaded from: classes3.dex */
public class EllipsizeSpanAnimator {
    boolean attachedToWindow;
    private final AnimatorSet ellAnimator;
    private final TextAlphaSpan[] ellSpans;
    public ArrayList<View> ellipsizedViews;

    /* renamed from: org.telegram.ui.Components.EllipsizeSpanAnimator$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        private Runnable restarter;
        final /* synthetic */ EllipsizeSpanAnimator this$0;
        final /* synthetic */ View val$parentView;

        /* renamed from: org.telegram.ui.Components.EllipsizeSpanAnimator$1$1, reason: invalid class name and collision with other inner class name */
        public class RunnableC00511 implements Runnable {
            final /* synthetic */ AnonymousClass1 this$1;

            public RunnableC00511(AnonymousClass1 r1) {
                this.this$1 = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                EllipsizeSpanAnimator r0 = this.this$1.this$0;
                if (r0.attachedToWindow == true) goto L5;
                return;
            L5:
                if (r0.ellipsizedViews.isEmpty() == false) goto L7;
                return;
            L7:
                if (EllipsizeSpanAnimator.m10173$$Nest$fgetellAnimator(this.this$1.this$0).isRunning() == true) goto L15;
                EllipsizeSpanAnimator.m10173$$Nest$fgetellAnimator(this.this$1.this$0).start();     // Catch: Exception -> L10
                return;
            L16:
                return;
            }
        }

        public AnonymousClass1(EllipsizeSpanAnimator r1, View r2) {
            this.this$0 = r1;
            this.val$parentView = r2;
            this.restarter = new RunnableC00511(this);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            if (this.this$0.attachedToWindow == false) goto L6;
            this.val$parentView.postDelayed(this.restarter, 300);
            return;
        }
    }

    public static class TextAlphaSpan extends CharacterStyle {
        private int alpha;

        public TextAlphaSpan() {
            this.alpha = 0;
        }

        public TextAlphaSpan(int r1) {
            this.alpha = r1;
        }

        public void setAlpha(int r1) {
            this.alpha = r1;
        }

        @Override // android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r3) {
            r3.setAlpha((int) (r3.getAlpha() * (this.alpha / 255.0f)));
        }
    }

    public static /* synthetic */ void $r8$lambda$PNZdAHm7dfH31ADedZCUSlzjEwk(EllipsizeSpanAnimator r0, TextAlphaSpan r1, ValueAnimator r2) {
        r0.lambda$createEllipsizeAnimator$0(r1, r2);
    }

    /* renamed from: -$$Nest$fgetellAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet m10173$$Nest$fgetellAnimator(EllipsizeSpanAnimator r0) {
        return r0.ellAnimator;
    }

    public EllipsizeSpanAnimator(View r16) {
        TextAlphaSpan[] r0 = {new TextAlphaSpan(), new TextAlphaSpan(), new TextAlphaSpan()};
        this.ellSpans = r0;
        this.ellipsizedViews = new ArrayList();
        AnimatorSet r1 = new AnimatorSet();
        this.ellAnimator = r1;
        r1.playTogether(new Animator[]{createEllipsizeAnimator(r0[0], 0, 255, 0, 300), createEllipsizeAnimator(r0[1], 0, 255, 150, 300), createEllipsizeAnimator(r0[2], 0, 255, 300, 300), createEllipsizeAnimator(r0[0], 255, 0, 1000, MediaError.DetailedErrorCode.MANIFEST_UNKNOWN), createEllipsizeAnimator(r0[1], 255, 0, 1000, MediaError.DetailedErrorCode.MANIFEST_UNKNOWN), createEllipsizeAnimator(r0[2], 255, 0, 1000, MediaError.DetailedErrorCode.MANIFEST_UNKNOWN)});
        r1.addListener(new AnonymousClass1(this, r16));
    }

    private Animator createEllipsizeAnimator(TextAlphaSpan r1, int r2, int r3, int r4, int r5) {
        ValueAnimator r22 = ValueAnimator.ofInt(new int[]{r2, r3});
        r22.addUpdateListener(new EllipsizeSpanAnimator$$ExternalSyntheticLambda0(this, r1));
        r22.setDuration(r5);
        r22.setStartDelay(r4);
        r22.setInterpolator(CubicBezierInterpolator.DEFAULT);
        return r22;
    }

    private /* synthetic */ void lambda$createEllipsizeAnimator$0(TextAlphaSpan r1, ValueAnimator r2) {
        r1.setAlpha(((Integer) r2.getAnimatedValue()).intValue());
        int r12 = 0;
    L4:
        if (r12 >= this.ellipsizedViews.size()) goto L9;
        if (HwEmojis.isHwEnabled() == true) goto L8;
        this.ellipsizedViews.get(r12).invalidate();
    L8:
        r12 = r12 + 1;
        goto L4
    }

    public void addView(View r2) {
        if (this.ellipsizedViews.isEmpty() == false) goto L6;
        this.ellAnimator.start();
    L6:
        if (this.ellipsizedViews.contains(r2) == true) goto L9;
        this.ellipsizedViews.add(r2);
        return;
    }

    public void onAttachedToWindow() {
        this.attachedToWindow = true;
        if (this.ellAnimator.isRunning() == true) goto L6;
        this.ellAnimator.start();
        return;
    }

    public void onDetachedFromWindow() {
        this.attachedToWindow = false;
        this.ellAnimator.cancel();
    }

    public void removeView(View r2) {
        this.ellipsizedViews.remove(r2);
        if (this.ellipsizedViews.isEmpty() == false) goto L6;
        this.ellAnimator.cancel();
        return;
    }

    public void wrap(SpannableString r5, int r6) {
        int r2 = r6 + 1;
        r5.setSpan(this.ellSpans[0], r6, r2, 0);
        int r3 = r6 + 2;
        r5.setSpan(this.ellSpans[1], r2, r3, 0);
        r5.setSpan(this.ellSpans[2], r3, r6 + 3, 0);
    }
}
