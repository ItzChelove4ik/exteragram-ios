package com.google.zxing.qrcode.encoder;

/* loaded from: classes5.dex */
final class BlockPair {
    private final byte[] dataBytes;
    private final byte[] errorCorrectionBytes;

    public BlockPair(byte[] r1, byte[] r2) {
        this.dataBytes = r1;
        this.errorCorrectionBytes = r2;
    }

    public byte[] getDataBytes() {
        return this.dataBytes;
    }

    public byte[] getErrorCorrectionBytes() {
        return this.errorCorrectionBytes;
    }
}
