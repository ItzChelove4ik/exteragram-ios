package com.google.android.material.animation;

import android.animation.TypeEvaluator;

/* loaded from: classes5.dex */
public class ArgbEvaluatorCompat implements TypeEvaluator<Integer> {
    private static final ArgbEvaluatorCompat instance = null;

    static {
        instance = new ArgbEvaluatorCompat();
    }

    public ArgbEvaluatorCompat() {
    }

    public static ArgbEvaluatorCompat getInstance() {
        return instance;
    }

    /* renamed from: evaluate, reason: avoid collision after fix types in other method */
    public Integer evaluate2(float r11, Integer r12, Integer r13) {
        int r10 = r12.intValue();
        float r122 = ((r10 >> 24) & 255) / 255.0f;
        int r132 = r13.intValue();
        float r1 = (float) Math.pow(((r10 >> 16) & 255) / 255.0f, 2.2d);
        float r2 = (float) Math.pow(((r10 >> 8) & 255) / 255.0f, 2.2d);
        float r102 = (float) Math.pow((r10 & 255) / 255.0f, 2.2d);
        float r4 = (float) Math.pow(((r132 >> 16) & 255) / 255.0f, 2.2d);
        float r123 = r122 + (((((r132 >> 24) & 255) / 255.0f) - r122) * r11);
        float r22 = r2 + ((((float) Math.pow(((r132 >> 8) & 255) / 255.0f, 2.2d)) - r2) * r11);
        float r103 = r102 + (r11 * (((float) Math.pow((r132 & 255) / 255.0f, 2.2d)) - r102));
        float r112 = ((float) Math.pow(r1 + ((r4 - r1) * r11), 0.45454545454545453d)) * 255.0f;
        float r133 = ((float) Math.pow(r22, 0.45454545454545453d)) * 255.0f;
        float r104 = ((float) Math.pow(r103, 0.45454545454545453d)) * 255.0f;
        return Integer.valueOf(Math.round(r104) | (((Math.round(r112) << 16) | (Math.round(r123 * 255.0f) << 24)) | (Math.round(r133) << 8)));
    }

    @Override // android.animation.TypeEvaluator
    public /* bridge */ /* synthetic */ Integer evaluate(float r1, Integer r2, Integer r3) {
        return evaluate2(r1, r2, r3);
    }
}
