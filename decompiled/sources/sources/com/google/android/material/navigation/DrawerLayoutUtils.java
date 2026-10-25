package com.google.android.material.navigation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.animation.AnimationUtils;

/* loaded from: classes5.dex */
public class DrawerLayoutUtils {
    private static final int DEFAULT_SCRIM_ALPHA = 0;
    private static final int DEFAULT_SCRIM_COLOR = -1728053248;

    /* renamed from: com.google.android.material.navigation.DrawerLayoutUtils$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ DrawerLayout val$drawerLayout;
        final /* synthetic */ View val$drawerView;

        public AnonymousClass1(DrawerLayout r1, View r2) {
            this.val$drawerView = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            throw null;
        }
    }

    public static /* synthetic */ void $r8$lambda$Q0EfB5Np2_kRW5ayeCHnYQx1LkA(DrawerLayout r1, ValueAnimator r2) {
        ColorUtils.setAlphaComponent(DEFAULT_SCRIM_COLOR, AnimationUtils.lerp(DEFAULT_SCRIM_ALPHA, 0, r2.getAnimatedFraction()));
        throw null;
    }

    static {
        DEFAULT_SCRIM_ALPHA = Color.alpha(DEFAULT_SCRIM_COLOR);
    }

    private DrawerLayoutUtils() {
    }

    public static Animator.AnimatorListener getScrimCloseAnimatorListener(DrawerLayout r1, View r2) {
        return new AnonymousClass1(r1, r2);
    }

    public static ValueAnimator.AnimatorUpdateListener getScrimCloseAnimatorUpdateListener(DrawerLayout r1) {
        return new DrawerLayoutUtils$$ExternalSyntheticLambda0(r1);
    }
}
