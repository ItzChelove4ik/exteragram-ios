package com.google.zxing;

import c.f$$ExternalSyntheticBUOutline1;
import com.google.zxing.common.BitMatrix;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public final class BinaryBitmap {
    private final Binarizer binarizer;
    private BitMatrix matrix;

    public BinaryBitmap(Binarizer r1) {
        if (r1 == null) goto L6;
        this.binarizer = r1;
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("Binarizer must be non-null.");
        throw null;
    }

    public BitMatrix getBlackMatrix() {
        if (this.matrix != null) goto L6;
        this.matrix = this.binarizer.getBlackMatrix();
    L6:
        return this.matrix;
    }

    public String toString() {
        return getBlackMatrix().toString();
    L4:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }
}
