package com.google.zxing.qrcode.decoder;

import com.google.zxing.ResultPoint;

/* loaded from: classes5.dex */
public final class QRCodeDecoderMetaData {
    private final boolean mirrored;

    public QRCodeDecoderMetaData(boolean r1) {
        this.mirrored = r1;
    }

    public void applyMirroredCorrection(ResultPoint[] r4) {
        if (this.mirrored == false) goto L10;
        if (r4 != null) goto L6;
        return;
    L6:
        if (r4.length < 3) goto L12;
        ResultPoint r0 = r4[0];
        r4[0] = r4[2];
        r4[2] = r0;
        return;
    L12:
        return;
    }
}
