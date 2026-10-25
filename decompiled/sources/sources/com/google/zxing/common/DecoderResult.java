package com.google.zxing.common;

import java.util.List;

/* loaded from: classes5.dex */
public final class DecoderResult {
    private final List<byte[]> byteSegments;
    private final String ecLevel;
    private Integer errorsCorrected;
    private int numBits;
    private Object other;
    private final byte[] rawBytes;
    private final int structuredAppendParity;
    private final int structuredAppendSequenceNumber;
    private final int symbologyModifier;
    private final String text;

    public DecoderResult(byte[] r1, String r2, List<byte[]> r3, String r4, int r5, int r6, int r7) {
        this.rawBytes = r1;
        if (r1 != null) goto L5;
        int r12 = 0;
    L6:
        this.numBits = r12;
        this.text = r2;
        this.byteSegments = r3;
        this.ecLevel = r4;
        this.structuredAppendParity = r6;
        this.structuredAppendSequenceNumber = r5;
        this.symbologyModifier = r7;
        return;
    L5:
        r12 = r1.length * 8;
        goto L6
    }

    public List<byte[]> getByteSegments() {
        return this.byteSegments;
    }

    public String getECLevel() {
        return this.ecLevel;
    }

    public Integer getErrorsCorrected() {
        return this.errorsCorrected;
    }

    public Object getOther() {
        return this.other;
    }

    public byte[] getRawBytes() {
        return this.rawBytes;
    }

    public int getStructuredAppendParity() {
        return this.structuredAppendParity;
    }

    public int getStructuredAppendSequenceNumber() {
        return this.structuredAppendSequenceNumber;
    }

    public int getSymbologyModifier() {
        return this.symbologyModifier;
    }

    public String getText() {
        return this.text;
    }

    public boolean hasStructuredAppend() {
        if (this.structuredAppendParity >= 0) goto L5;
        return false;
    L5:
        if (this.structuredAppendSequenceNumber < 0) goto L10;
        return true;
    L10:
        return false;
    }

    public void setErrorsCorrected(Integer r1) {
        this.errorsCorrected = r1;
    }

    public void setOther(Object r1) {
        this.other = r1;
    }
}
