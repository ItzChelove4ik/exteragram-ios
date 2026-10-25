package com.android.dex.util;

/* loaded from: classes4.dex */
public final class ByteArrayByteInput implements ByteInput {
    private final byte[] bytes;
    private int position;

    public ByteArrayByteInput(byte... r1) {
        this.bytes = r1;
    }

    @Override // com.android.dex.util.ByteInput
    public byte readByte() {
        byte[] r0 = this.bytes;
        int r1 = this.position;
        this.position = r1 + 1;
        return r0[r1];
    }
}
