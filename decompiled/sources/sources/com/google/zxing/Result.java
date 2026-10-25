package com.google.zxing;

import java.util.EnumMap;
import java.util.Map;

/* loaded from: classes5.dex */
public final class Result {
    private final BarcodeFormat format;
    private final int numBits;
    private final byte[] rawBytes;
    private Map<ResultMetadataType, Object> resultMetadata;
    private ResultPoint[] resultPoints;
    private final String text;
    private final long timestamp;

    public Result(String r1, byte[] r2, int r3, ResultPoint[] r4, BarcodeFormat r5, long r6) {
        this.text = r1;
        this.rawBytes = r2;
        this.numBits = r3;
        this.resultPoints = r4;
        this.format = r5;
        this.resultMetadata = null;
        this.timestamp = r6;
    }

    public Result(String r8, byte[] r9, ResultPoint[] r10, BarcodeFormat r11) {
        this(r8, r9, r10, r11, System.currentTimeMillis());
    }

    public Result(String r10, byte[] r11, ResultPoint[] r12, BarcodeFormat r13, long r14) {
        if (r11 != null) goto L5;
        int r0 = 0;
    L6:
        this(r10, r11, r0, r12, r13, r14);
        return;
    L5:
        r0 = r11.length * 8;
        goto L6
    }

    public ResultPoint[] getResultPoints() {
        return this.resultPoints;
    }

    public String getText() {
        return this.text;
    }

    public void putMetadata(ResultMetadataType r3, Object r4) {
        if (this.resultMetadata != null) goto L5;
        this.resultMetadata = new EnumMap(ResultMetadataType.class);
    L5:
        this.resultMetadata.put(r3, r4);
    }

    public String toString() {
        return this.text;
    }
}
