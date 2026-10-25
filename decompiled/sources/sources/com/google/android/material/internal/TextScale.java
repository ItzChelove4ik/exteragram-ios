package com.google.android.material.internal;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.transition.Transition;
import androidx.transition.TransitionValues;
import java.util.Map;

/* loaded from: classes5.dex */
public class TextScale extends Transition {
    private static final String PROPNAME_SCALE = "android:textscale:scale";

    /* renamed from: com.google.android.material.internal.TextScale$1, reason: invalid class name */
    public class AnonymousClass1 implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ TextScale this$0;
        final /* synthetic */ TextView val$view;

        public AnonymousClass1(TextScale r1, TextView r2) {
            this.this$0 = r1;
            this.val$view = r2;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator r2) {
            float r22 = ((Float) r2.getAnimatedValue()).floatValue();
            this.val$view.setScaleX(r22);
            this.val$view.setScaleY(r22);
        }
    }

    public TextScale() {
    }

    private void captureValues(TransitionValues r2) {
        View r1 = r2.view;
        if ((r1 instanceof TextView) == false) goto L6;
        r2.values.put(PROPNAME_SCALE, Float.valueOf(((TextView) r1).getScaleX()));
        return;
    }

    @Override // androidx.transition.Transition
    public void captureEndValues(TransitionValues r1) {
        captureValues(r1);
    }

    @Override // androidx.transition.Transition
    public void captureStartValues(TransitionValues r1) {
        captureValues(r1);
    }

    @Override // androidx.transition.Transition
    public Animator createAnimator(ViewGroup r5, TransitionValues r6, TransitionValues r7) {
        if (r6 == null) goto L22;
        if (r7 != null) goto L6;
        return null;
    L6:
        if ((r6.view instanceof TextView) == false) goto L24;
        View r0 = r7.view;
        if ((r0 instanceof TextView) == false) goto L25;
        TextView r02 = (TextView) r0;
        Map<String, Object> r62 = r6.values;
        Map<String, Object> r72 = r7.values;
        float r3 = 1.0f;
        if (r62.get(PROPNAME_SCALE) == null) goto L13;
        float r63 = ((Float) r62.get(PROPNAME_SCALE)).floatValue();
    L15:
        if (r72.get(PROPNAME_SCALE) == null) goto L18;
        r3 = ((Float) r72.get(PROPNAME_SCALE)).floatValue();
    L18:
        if (r63 != r3) goto L20;
        return null;
    L20:
        ValueAnimator r52 = ValueAnimator.ofFloat(new float[]{r63, r3});
        r52.addUpdateListener(new AnonymousClass1(this, r02));
        return r52;
    L13:
        r63 = 1.0f;
        goto L15
    L25:
        return null;
    L24:
        return null;
    L22:
        return null;
    }
}
