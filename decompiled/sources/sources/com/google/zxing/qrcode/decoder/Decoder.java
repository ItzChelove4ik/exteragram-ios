package com.google.zxing.qrcode.decoder;

import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonDecoder;
import com.google.zxing.common.reedsolomon.ReedSolomonException;
import java.util.Map;
import kotlin.UByte;

/* loaded from: classes5.dex */
public final class Decoder {
    private final ReedSolomonDecoder rsDecoder;

    public Decoder() {
        this.rsDecoder = new ReedSolomonDecoder(GenericGF.QR_CODE_FIELD_256);
    }

    private int correctErrors(byte[] r6, int r7) {
        int r0 = r6.length;
        int[] r1 = new int[r0];
        int r2 = 0;
        int r3 = 0;
    L3:
        if (r3 >= r0) goto L11;
        r1[r3] = r6[r3] & UByte.MAX_VALUE;
        r3 = r3 + 1;
        goto L3
    L11:
        int r5 = this.rsDecoder.decodeWithECCount(r1, r6.length - r7);     // Catch: ReedSolomonException -> L9
    L6:
        if (r2 >= r7) goto L8;
        r6[r2] = (byte) r1[r2];
        r2 = r2 + 1;
        goto L6
    L8:
        return r5;
    L10:
        throw ChecksumException.getChecksumInstance();
    }

    private DecoderResult decode(BitMatrixParser r14, Map<DecodeHintType, ?> r15) {
        Version r0 = r14.readVersion();
        ErrorCorrectionLevel r1 = r14.readFormatInformation().getErrorCorrectionLevel();
        DataBlock[] r142 = DataBlock.getDataBlocks(r14.readCodewords(), r0, r1);
        int r2 = r142.length;
        int r4 = 0;
        int r5 = 0;
    L3:
        if (r4 >= r2) goto L5;
        r5 = r5 + r142[r4].getNumDataCodewords();
        r4 = r4 + 1;
        goto L3
    L5:
        byte[] r22 = new byte[r5];
        int r42 = r142.length;
        int r52 = 0;
        int r6 = 0;
        int r7 = 0;
    L6:
        if (r52 >= r42) goto L11;
        DataBlock r8 = r142[r52];
        byte[] r9 = r8.getCodewords();
        int r82 = r8.getNumDataCodewords();
        r6 = r6 + correctErrors(r9, r82);
        int r10 = 0;
    L8:
        if (r10 >= r82) goto L10;
        r22[r7] = r9[r10];
        r10 = r10 + 1;
        r7 = r7 + 1;
        goto L8
    L10:
        r52 = r52 + 1;
        goto L6
    L11:
        DecoderResult r13 = DecodedBitStreamParser.decode(r22, r0, r1, r15);
        r13.setErrorsCorrected(Integer.valueOf(r6));
        return r13;
    }

    public DecoderResult decode(BitMatrix r5, Map<DecodeHintType, ?> r6) {
        BitMatrixParser r0 = new BitMatrixParser(r5);
        FormatException r52 = null;
        return decode(r0, r6);
    L5:
        ChecksumException e = e;
    L14:
        r0.remask();     // Catch: Throwable -> L11
        r0.setMirror(true);     // Catch: Throwable -> L11
        r0.readVersion();     // Catch: Throwable -> L11
        r0.readFormatInformation();     // Catch: Throwable -> L11
        r0.mirror();     // Catch: Throwable -> L11
        DecoderResult r4 = this.decode(r0, r6);     // Catch: Throwable -> L11
        r4.setOther(new QRCodeDecoderMetaData(true));     // Catch: Throwable -> L11
        return r4;
    L11:
        if (r52 == null) goto L13;
        throw r52;
    L13:
        throw e;
    L7:
        e = move-exception;
        e = null;
        r52 = e;
        goto L14
    }
}
