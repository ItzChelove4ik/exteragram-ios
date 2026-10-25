package org.telegram.ui.Components;

import android.graphics.Point;
import android.view.View;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes7.dex */
public final class VerticalPositionAutoAnimator {
    private final AnimatorLayoutChangeListener animatorLayoutChangeListener;
    private SpringAnimation floatingButtonAnimator;
    private View floatingButtonView;
    private float offsetY;

    public class AnimatorLayoutChangeListener implements View.OnLayoutChangeListener {
        private boolean ignoreNextLayout;
        private Boolean orientation;
        final /* synthetic */ VerticalPositionAutoAnimator this$0;

        /* renamed from: -$$Nest$fputignoreNextLayout, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13505$$Nest$fputignoreNextLayout(AnimatorLayoutChangeListener r0, boolean r1) {
            r0.ignoreNextLayout = r1;
        }

        public AnimatorLayoutChangeListener(VerticalPositionAutoAnimator r3, View r4, float r5) {
            this.this$0 = r3;
            VerticalPositionAutoAnimator.m13504$$Nest$fputfloatingButtonAnimator(r3, new SpringAnimation(r4, DynamicAnimation.TRANSLATION_Y, VerticalPositionAutoAnimator.m13503$$Nest$fgetoffsetY(r3)));
            VerticalPositionAutoAnimator.m13502$$Nest$fgetfloatingButtonAnimator(r3).getSpring().setDampingRatio(1.0f);
            VerticalPositionAutoAnimator.m13502$$Nest$fgetfloatingButtonAnimator(r3).getSpring().setStiffness(r5);
        }

        private void checkOrientation() {
            Point r0 = AndroidUtilities.displaySize;
            if (r0.x <= r0.y) goto L5;
            boolean r02 = true;
        L6:
            Boolean r1 = this.orientation;
            if (r1 != null) goto L9;
        L12:
            this.orientation = Boolean.valueOf(r02);
            this.ignoreNextLayout = true;
            return;
        L9:
            if (r1.booleanValue() != r02) goto L12;
            return;
        L5:
            r02 = false;
            goto L6
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View r1, int r2, int r3, int r4, int r5, int r6, int r7, int r8, int r9) {
            checkOrientation();
            if (r7 == 0) goto L14;
            if (r7 == r3) goto L14;
            if (this.ignoreNextLayout == true) goto L14;
            VerticalPositionAutoAnimator.m13502$$Nest$fgetfloatingButtonAnimator(this.this$0).cancel();
            int r22 = r1.getVisibility();
            VerticalPositionAutoAnimator r42 = this.this$0;
            if (r22 == 0) goto L12;
            r1.setTranslationY(VerticalPositionAutoAnimator.m13503$$Nest$fgetoffsetY(r42));
            return;
        L12:
            VerticalPositionAutoAnimator.m13502$$Nest$fgetfloatingButtonAnimator(r42).getSpring().setFinalPosition(VerticalPositionAutoAnimator.m13503$$Nest$fgetoffsetY(this.this$0));
            r1.setTranslationY((r7 - r3) + VerticalPositionAutoAnimator.m13503$$Nest$fgetoffsetY(this.this$0));
            VerticalPositionAutoAnimator.m13502$$Nest$fgetfloatingButtonAnimator(this.this$0).start();
            return;
        L14:
            this.ignoreNextLayout = false;
        }
    }

    /* renamed from: -$$Nest$fgetfloatingButtonAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SpringAnimation m13502$$Nest$fgetfloatingButtonAnimator(VerticalPositionAutoAnimator r0) {
        return r0.floatingButtonAnimator;
    }

    /* renamed from: -$$Nest$fgetoffsetY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m13503$$Nest$fgetoffsetY(VerticalPositionAutoAnimator r0) {
        return r0.offsetY;
    }

    /* renamed from: -$$Nest$fputfloatingButtonAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13504$$Nest$fputfloatingButtonAnimator(VerticalPositionAutoAnimator r0, SpringAnimation r1) {
        r0.floatingButtonAnimator = r1;
    }

    private VerticalPositionAutoAnimator(View r2, float r3) {
        this.floatingButtonView = r2;
        AnimatorLayoutChangeListener r0 = new AnimatorLayoutChangeListener(this, r2, r3);
        this.animatorLayoutChangeListener = r0;
        r2.addOnLayoutChangeListener(r0);
    }

    public static VerticalPositionAutoAnimator attach(View r1) {
        return attach(r1, 350.0f);
    }

    public static VerticalPositionAutoAnimator attach(View r1, float r2) {
        return new VerticalPositionAutoAnimator(r1, r2);
    }

    public void addUpdateListener(DynamicAnimation.OnAnimationUpdateListener r1) {
        this.floatingButtonAnimator.addUpdateListener(r1);
    }

    public float getOffsetY() {
        return this.offsetY;
    }

    public void ignoreNextLayout() {
        AnimatorLayoutChangeListener.m13505$$Nest$fputignoreNextLayout(this.animatorLayoutChangeListener, true);
    }

    public void setOffsetY(float r2) {
        this.offsetY = r2;
        if (this.floatingButtonAnimator.isRunning() == false) goto L6;
        this.floatingButtonAnimator.getSpring().setFinalPosition(r2);
        return;
    L6:
        this.floatingButtonView.setTranslationY(r2);
    }
}
