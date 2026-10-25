package org.telegram.ui.Components;

import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionSet;

/* loaded from: classes7.dex */
public abstract class TransitionExt {
    public static Transition createSimpleTransition() {
        TransitionSet r0 = new TransitionSet();
        ChangeBounds r1 = new ChangeBounds();
        r1.setDuration(150);
        r0.addTransition(new Fade().setDuration(150)).addTransition(r1);
        r0.setOrdering(0);
        r0.setInterpolator(CubicBezierInterpolator.DEFAULT);
        return r0;
    }
}
