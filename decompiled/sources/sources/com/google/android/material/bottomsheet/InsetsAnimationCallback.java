package com.google.android.material.bottomsheet;

import android.view.View;
import androidx.core.view.WindowInsetsAnimationCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.animation.AnimationUtils;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
class InsetsAnimationCallback extends WindowInsetsAnimationCompat.Callback {
    private int startTranslationY;
    private int startY;
    private final int[] tmpLocation;
    private final View view;

    public InsetsAnimationCallback(View r2) {
        super(0);
        this.tmpLocation = new int[2];
        this.view = r2;
    }

    @Override // androidx.core.view.WindowInsetsAnimationCompat.Callback
    public void onEnd(WindowInsetsAnimationCompat r1) {
        this.view.setTranslationY(0.0f);
    }

    @Override // androidx.core.view.WindowInsetsAnimationCompat.Callback
    public void onPrepare(WindowInsetsAnimationCompat r2) {
        this.view.getLocationOnScreen(this.tmpLocation);
        this.startY = this.tmpLocation[1];
    }

    @Override // androidx.core.view.WindowInsetsAnimationCompat.Callback
    public WindowInsetsCompat onProgress(WindowInsetsCompat r4, List<WindowInsetsAnimationCompat> r5) {
        Iterator<WindowInsetsAnimationCompat> r52 = r5.iterator();
    L4:
        if (r52.hasNext() == false) goto L8;
        WindowInsetsAnimationCompat r0 = r52.next();
        if ((r0.getTypeMask() & WindowInsetsCompat.Type.ime()) == 0) goto L4;
        this.view.setTranslationY(AnimationUtils.lerp(this.startTranslationY, 0, r0.getInterpolatedFraction()));
    L8:
        return r4;
    }

    @Override // androidx.core.view.WindowInsetsAnimationCompat.Callback
    public WindowInsetsAnimationCompat.BoundsCompat onStart(WindowInsetsAnimationCompat r2, WindowInsetsAnimationCompat.BoundsCompat r3) {
        this.view.getLocationOnScreen(this.tmpLocation);
        int r0 = this.startY - this.tmpLocation[1];
        this.startTranslationY = r0;
        this.view.setTranslationY(r0);
        return r3;
    }
}
