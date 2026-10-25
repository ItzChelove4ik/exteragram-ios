package com.google.android.material.internal;

import android.animation.TypeEvaluator;
import android.graphics.Rect;

/* loaded from: classes5.dex */
public class RectEvaluator implements TypeEvaluator<Rect> {
    private final Rect rect;

    public RectEvaluator(Rect r1) {
        this.rect = r1;
    }

    /* renamed from: evaluate, reason: avoid collision after fix types in other method */
    public Rect evaluate2(float r5, Rect r6, Rect r7) {
        this.rect.set(r6.left + ((int) ((r7.left - r0) * r5)), r6.top + ((int) ((r7.top - r1) * r5)), r6.right + ((int) ((r7.right - r2) * r5)), r6.bottom + ((int) ((r7.bottom - r6) * r5)));
        return this.rect;
    }

    @Override // android.animation.TypeEvaluator
    public /* bridge */ /* synthetic */ Rect evaluate(float r1, Rect r2, Rect r3) {
        return evaluate2(r1, r2, r3);
    }
}
