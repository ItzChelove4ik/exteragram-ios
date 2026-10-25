package com.google.android.material.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import java.util.List;

/* loaded from: classes5.dex */
public class AnimatorSetCompat {
    public AnimatorSetCompat() {
    }

    public static void playTogether(AnimatorSet r10, List<Animator> r11) {
        int r0 = r11.size();
        long r1 = 0;
        int r4 = 0;
    L3:
        if (r4 >= r0) goto L5;
        Animator r5 = r11.get(r4);
        r1 = Math.max(r1, r5.getStartDelay() + r5.getDuration());
        r4 = r4 + 1;
        goto L3
    L5:
        ValueAnimator r02 = ValueAnimator.ofInt(new int[]{0, 0});
        r02.setDuration(r1);
        r11.add(0, r02);
        r10.playTogether(r11);
    }
}
