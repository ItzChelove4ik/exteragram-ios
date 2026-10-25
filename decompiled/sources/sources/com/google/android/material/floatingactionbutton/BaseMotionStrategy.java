package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Property;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import androidx.core.util.Preconditions;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.animation.AnimatorSetCompat;
import com.google.android.material.animation.MotionSpec;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
abstract class BaseMotionStrategy implements MotionStrategy {
    private final Context context;
    private MotionSpec defaultMotionSpec;
    private final ExtendedFloatingActionButton fab;
    private final ArrayList<Animator.AnimatorListener> listeners;
    private MotionSpec motionSpec;
    private final AnimatorTracker tracker;

    /* renamed from: com.google.android.material.floatingactionbutton.BaseMotionStrategy$1, reason: invalid class name */
    public class AnonymousClass1 extends Property<ExtendedFloatingActionButton, Float> {
        final /* synthetic */ BaseMotionStrategy this$0;

        public AnonymousClass1(BaseMotionStrategy r1, Class r2, String r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        /* renamed from: get, reason: avoid collision after fix types in other method */
        public Float get2(ExtendedFloatingActionButton r1) {
            int r0 = Color.alpha(r1.getCurrentOriginalTextColor());
            int r12 = Color.alpha(r1.getCurrentTextColor());
            if (r0 == 0) goto L5;
            float r13 = r12 / r0;
        L7:
            return Float.valueOf(r13);
        L5:
            r13 = 0.0f;
            goto L7
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ Float get(ExtendedFloatingActionButton r1) {
            return get2(r1);
        }

        /* renamed from: set, reason: avoid collision after fix types in other method */
        public void set2(ExtendedFloatingActionButton r3, Float r4) {
            if (r4.floatValue() != 1.0f) goto L6;
            r3.silentlyUpdateTextColor(r3.getOriginalTextColor());
            return;
        L6:
            r3.silentlyUpdateTextColor(ColorStateList.valueOf(ColorUtils.setAlphaComponent(r3.getCurrentOriginalTextColor(), Math.round(AnimationUtils.lerp(0.0f, Color.alpha(r2), r4.floatValue())))));
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ void set(ExtendedFloatingActionButton r1, Float r2) {
            set2(r1, r2);
        }
    }

    public BaseMotionStrategy(ExtendedFloatingActionButton r2, AnimatorTracker r3) {
        this.listeners = new ArrayList();
        this.fab = r2;
        this.context = r2.getContext();
        this.tracker = r3;
    }

    @Override // com.google.android.material.floatingactionbutton.MotionStrategy
    public final void addAnimationListener(Animator.AnimatorListener r1) {
        this.listeners.add(r1);
    }

    @Override // com.google.android.material.floatingactionbutton.MotionStrategy
    public AnimatorSet createAnimator() {
        return createAnimator(getCurrentMotionSpec());
    }

    public AnimatorSet createAnimator(MotionSpec r7) {
        ArrayList r0 = new ArrayList();
        if (r7.hasPropertyValues("opacity") == false) goto L6;
        r0.add(r7.getAnimator("opacity", this.fab, View.ALPHA));
    L6:
        if (r7.hasPropertyValues("scale") == false) goto L9;
        r0.add(r7.getAnimator("scale", this.fab, View.SCALE_Y));
        r0.add(r7.getAnimator("scale", this.fab, View.SCALE_X));
    L9:
        if (r7.hasPropertyValues("width") == false) goto L12;
        r0.add(r7.getAnimator("width", this.fab, ExtendedFloatingActionButton.WIDTH));
    L12:
        if (r7.hasPropertyValues("height") == false) goto L15;
        r0.add(r7.getAnimator("height", this.fab, ExtendedFloatingActionButton.HEIGHT));
    L15:
        if (r7.hasPropertyValues("paddingStart") == false) goto L18;
        r0.add(r7.getAnimator("paddingStart", this.fab, ExtendedFloatingActionButton.PADDING_START));
    L18:
        if (r7.hasPropertyValues("paddingEnd") == false) goto L21;
        r0.add(r7.getAnimator("paddingEnd", this.fab, ExtendedFloatingActionButton.PADDING_END));
    L21:
        if (r7.hasPropertyValues("labelOpacity") == false) goto L23;
        r0.add(r7.getAnimator("labelOpacity", this.fab, new AnonymousClass1(this, Float.class, "LABEL_OPACITY_PROPERTY")));
    L23:
        AnimatorSet r6 = new AnimatorSet();
        AnimatorSetCompat.playTogether(r6, r0);
        return r6;
    }

    @Override // com.google.android.material.floatingactionbutton.MotionStrategy
    public final MotionSpec getCurrentMotionSpec() {
        MotionSpec r0 = this.motionSpec;
        if (r0 == null) goto L6;
        return r0;
    L6:
        if (this.defaultMotionSpec != null) goto L9;
        this.defaultMotionSpec = MotionSpec.createFromResource(this.context, getDefaultMotionSpecResource());
    L9:
        return (MotionSpec) Preconditions.checkNotNull(this.defaultMotionSpec);
    }

    @Override // com.google.android.material.floatingactionbutton.MotionStrategy
    public final List<Animator.AnimatorListener> getListeners() {
        return this.listeners;
    }

    @Override // com.google.android.material.floatingactionbutton.MotionStrategy
    public MotionSpec getMotionSpec() {
        return this.motionSpec;
    }

    @Override // com.google.android.material.floatingactionbutton.MotionStrategy
    public void onAnimationCancel() {
        this.tracker.clear();
    }

    @Override // com.google.android.material.floatingactionbutton.MotionStrategy
    public void onAnimationEnd() {
        this.tracker.clear();
    }

    @Override // com.google.android.material.floatingactionbutton.MotionStrategy
    public void onAnimationStart(Animator r1) {
        this.tracker.onNextAnimationStart(r1);
    }

    @Override // com.google.android.material.floatingactionbutton.MotionStrategy
    public final void removeAnimationListener(Animator.AnimatorListener r1) {
        this.listeners.remove(r1);
    }

    @Override // com.google.android.material.floatingactionbutton.MotionStrategy
    public final void setMotionSpec(MotionSpec r1) {
        this.motionSpec = r1;
    }
}
