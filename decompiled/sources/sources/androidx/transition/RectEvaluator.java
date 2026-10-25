package androidx.transition;

import android.animation.TypeEvaluator;
import android.graphics.Rect;

/* loaded from: classes4.dex */
class RectEvaluator implements TypeEvaluator<Rect> {
    private Rect mRect;

    public RectEvaluator() {
    }

    /* renamed from: evaluate, reason: avoid collision after fix types in other method */
    public Rect evaluate2(float r5, Rect r6, Rect r7) {
        int r0 = r6.left + ((int) ((r7.left - r0) * r5));
        int r1 = r6.top + ((int) ((r7.top - r1) * r5));
        int r2 = r6.right + ((int) ((r7.right - r2) * r5));
        int r62 = r6.bottom + ((int) ((r7.bottom - r6) * r5));
        Rect r52 = this.mRect;
        if (r52 == null) goto L5;
        r52.set(r0, r1, r2, r62);
        return this.mRect;
    L5:
        return new Rect(r0, r1, r2, r62);
    }

    @Override // android.animation.TypeEvaluator
    public /* bridge */ /* synthetic */ Rect evaluate(float r1, Rect r2, Rect r3) {
        return evaluate2(r1, r2, r3);
    }
}
