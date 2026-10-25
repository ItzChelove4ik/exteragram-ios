package org.telegram.ui.iv;

import android.animation.ValueAnimator;
import org.telegram.ui.Components.CubicBezierInterpolator;

/* loaded from: classes7.dex */
class RichBlockInset {
    private ValueAnimator animator;
    private long boundRowId;
    private int currentPx;

    public interface Applier {
        void apply(int r1);
    }

    public static /* synthetic */ void $r8$lambda$Di3KT2Inc4EglP7yhvJIoVFWmds(RichBlockInset r0, Applier r1, ValueAnimator r2) {
        r0.lambda$apply$0(r1, r2);
    }

    public RichBlockInset() {
        this.boundRowId = Long.MIN_VALUE;
        this.currentPx = -1;
    }

    private /* synthetic */ void lambda$apply$0(Applier r1, ValueAnimator r2) {
        int r22 = ((Integer) r2.getAnimatedValue()).intValue();
        this.currentPx = r22;
        r1.apply(r22);
    }

    public void apply(BlockRow r2, Applier r3) {
        apply(r2, r3, true);
    }

    public void apply(BlockRow r6, Applier r7, boolean r8) {
        int r0 = RichBlockChrome.insetFor(r6);
        if (r6 == null) goto L5;
        long r1 = r6.id;
    L7:
        if (r1 == this.boundRowId) goto L9;
    L11:
        boolean r62 = false;
    L12:
        this.boundRowId = r1;
        ValueAnimator r12 = this.animator;
        if (r12 == null) goto L15;
        r12.cancel();
        this.animator = null;
    L15:
        if (r8 == false) goto L21;
        if (r62 == false) goto L21;
        int r63 = this.currentPx;
        if (r63 == r0) goto L21;
        ValueAnimator r64 = ValueAnimator.ofInt(new int[]{r63, r0});
        r64.addUpdateListener(new RichBlockInset$$ExternalSyntheticLambda0(this, r7));
        r64.setInterpolator(CubicBezierInterpolator.DEFAULT);
        r64.setDuration(200);
        this.animator = r64;
        r64.start();
        return;
    L21:
        this.currentPx = r0;
        r7.apply(r0);
        return;
    L9:
        if (this.currentPx < 0) goto L11;
        r62 = true;
        goto L12
    L5:
        r1 = Long.MIN_VALUE;
        goto L7
    }
}
