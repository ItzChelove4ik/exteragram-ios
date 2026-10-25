package com.google.android.material.appbar;

import android.R;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import com.google.android.material.internal.ThemeEnforcement;

/* loaded from: classes5.dex */
class ViewUtilsLollipop {
    private static final int[] STATE_LIST_ANIM_ATTRS = null;

    static {
        STATE_LIST_ANIM_ATTRS = new int[]{R.attr.stateListAnimator};
    }

    public ViewUtilsLollipop() {
    }

    public static void setBoundsViewOutlineProvider(View r1) {
        r1.setOutlineProvider(ViewOutlineProvider.BOUNDS);
    }

    public static void setDefaultAppBarLayoutStateListAnimator(View r11, float r12) {
        int r0 = r11.getResources().getInteger(com.google.android.material.R.integer.app_bar_elevation_anim_duration);
        StateListAnimator r1 = new StateListAnimator();
        long r9 = r0;
        r1.addState(new int[]{R.attr.state_enabled, com.google.android.material.R.attr.state_liftable, -com.google.android.material.R.attr.state_lifted}, ObjectAnimator.ofFloat(r11, "elevation", new float[]{0.0f}).setDuration(r9));
        r1.addState(new int[]{R.attr.state_enabled}, ObjectAnimator.ofFloat(r11, "elevation", new float[]{r12}).setDuration(r9));
        r1.addState(new int[0], ObjectAnimator.ofFloat(r11, "elevation", new float[]{0.0f}).setDuration(0));
        r11.setStateListAnimator(r1);
    }

    public static void setStateListAnimatorFromAttrs(View r7, AttributeSet r8, int r9, int r10) {
        Context r0 = r7.getContext();
        TypedArray r82 = ThemeEnforcement.obtainStyledAttributes(r0, r8, STATE_LIST_ANIM_ATTRS, r9, r10, new int[0]);
    L7:
        th = move-exception;
        r82.recycle();
        throw th;
    L4:
        if (r82.hasValue(0) == false) goto L9;
        r7.setStateListAnimator(AnimatorInflater.loadStateListAnimator(r0, r82.getResourceId(0, 0)));     // Catch: Throwable -> L7
    L9:
        r82.recycle();
    }
}
