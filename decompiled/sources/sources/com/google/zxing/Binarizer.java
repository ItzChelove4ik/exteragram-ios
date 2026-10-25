package com.google.zxing;

import com.google.zxing.common.BitMatrix;

/* loaded from: classes5.dex */
public abstract class Binarizer {
    private final LuminanceSource source;

    public Binarizer(LuminanceSource r1) {
        this.source = r1;
    }

    public abstract BitMatrix getBlackMatrix();

    public final LuminanceSource getLuminanceSource() {
        return this.source;
    }
}
