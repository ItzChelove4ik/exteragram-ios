package com.google.zxing.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.qrcode.decoder.Decoder;
import com.google.zxing.qrcode.decoder.QRCodeDecoderMetaData;
import com.google.zxing.qrcode.detector.Detector;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class QRCodeReader {
    private static final ResultPoint[] NO_POINTS = null;
    private final Decoder decoder;

    static {
        NO_POINTS = new ResultPoint[0];
    }

    public QRCodeReader() {
        this.decoder = new Decoder();
    }

    private static BitMatrix extractPureBits(BitMatrix r11) {
        int[] r0 = r11.getTopLeftOnBit();
        int[] r1 = r11.getBottomRightOnBit();
        if (r0 == null) goto L48;
        if (r1 == null) goto L48;
        float r2 = moduleSize(r0, r11);
        int r4 = r0[1];
        int r5 = r1[1];
        int r02 = r0[0];
        int r12 = r1[0];
        if (r02 >= r12) goto L46;
        if (r4 >= r5) goto L46;
        int r7 = r5 - r4;
        if (r7 == (r12 - r02)) goto L15;
        r12 = r02 + r7;
        if (r12 < r11.getWidth()) goto L15;
        throw NotFoundException.getNotFoundInstance();
    L15:
        int r8 = Math.round(((r12 - r02) + 1) / r2);
        int r3 = Math.round((r7 + 1) / r2);
        if (r8 <= 0) goto L44;
        if (r3 <= 0) goto L44;
        if (r3 != r8) goto L42;
        int r72 = (int) (r2 / 2.0f);
        int r42 = r4 + r72;
        int r03 = r02 + r72;
        int r9 = (((int) ((r8 - 1) * r2)) + r03) - r12;
        if (r9 <= 0) goto L25;
        if (r9 > r72) goto L24;
        r03 = r03 - r9;
        goto L25
    L24:
        throw NotFoundException.getNotFoundInstance();
    L25:
        int r13 = (((int) ((r3 - 1) * r2)) + r42) - r5;
        if (r13 <= 0) goto L31;
        if (r13 > r72) goto L30;
        r42 = r42 - r13;
        goto L31
    L30:
        throw NotFoundException.getNotFoundInstance();
    L31:
        BitMatrix r14 = new BitMatrix(r8, r3);
        int r52 = 0;
    L32:
        if (r52 >= r3) goto L40;
        int r73 = ((int) (r52 * r2)) + r42;
        int r92 = 0;
    L34:
        if (r92 >= r8) goto L39;
        if (r11.get(((int) (r92 * r2)) + r03, r73) == false) goto L38;
        r14.set(r92, r52);
    L38:
        r92 = r92 + 1;
        goto L34
    L39:
        r52 = r52 + 1;
        goto L32
    L40:
        return r14;
    L42:
        throw NotFoundException.getNotFoundInstance();
    L44:
        throw NotFoundException.getNotFoundInstance();
    L46:
        throw NotFoundException.getNotFoundInstance();
    L48:
        throw NotFoundException.getNotFoundInstance();
    }

    private static float moduleSize(int[] r8, BitMatrix r9) {
        int r0 = r9.getHeight();
        int r1 = r9.getWidth();
        int r3 = r8[0];
        boolean r4 = true;
        int r5 = r8[1];
        int r6 = 0;
    L3:
        if (r3 >= r1) goto L12;
        if (r5 >= r0) goto L12;
        if (r4 == r9.get(r3, r5)) goto L11;
        r6 = r6 + 1;
        if (r6 == 5) goto L12;
        r4 = !r4;
    L11:
        r3 = r3 + 1;
        r5 = r5 + 1;
    L12:
        if (r3 == r1) goto L17;
        if (r5 == r0) goto L17;
        return (r3 - r8[0]) / 7.0f;
    L17:
        throw NotFoundException.getNotFoundInstance();
    }

    public Result decode(BinaryBitmap r2) {
        return decode(r2, null);
    }

    public final Result decode(BinaryBitmap r4, Map<DecodeHintType, ?> r5) {
        if (r5 != null) goto L4;
    L6:
        DetectorResult r42 = new Detector(r4.getBlackMatrix()).detect(r5);
        DecoderResult r3 = this.decoder.decode(r42.getBits(), r5);
        ResultPoint[] r43 = r42.getPoints();
    L8:
        if ((r3.getOther() instanceof QRCodeDecoderMetaData) == false) goto L10;
        ((QRCodeDecoderMetaData) r3.getOther()).applyMirroredCorrection(r43);
    L10:
        Result r52 = new Result(r3.getText(), r3.getRawBytes(), r43, BarcodeFormat.QR_CODE);
        List<byte[]> r44 = r3.getByteSegments();
        if (r44 == null) goto L13;
        r52.putMetadata(ResultMetadataType.BYTE_SEGMENTS, r44);
    L13:
        String r45 = r3.getECLevel();
        if (r45 == null) goto L17;
        r52.putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, r45);
    L17:
        if (r3.hasStructuredAppend() == false) goto L19;
        r52.putMetadata(ResultMetadataType.STRUCTURED_APPEND_SEQUENCE, Integer.valueOf(r3.getStructuredAppendSequenceNumber()));
        r52.putMetadata(ResultMetadataType.STRUCTURED_APPEND_PARITY, Integer.valueOf(r3.getStructuredAppendParity()));
    L19:
        r52.putMetadata(ResultMetadataType.ERRORS_CORRECTED, r3.getErrorsCorrected());
        r52.putMetadata(ResultMetadataType.SYMBOLOGY_IDENTIFIER, "]Q" + r3.getSymbologyModifier());
        return r52;
    L4:
        if (r5.containsKey(DecodeHintType.PURE_BARCODE) == false) goto L6;
        r3 = this.decoder.decode(extractPureBits(r4.getBlackMatrix()), r5);
        r43 = NO_POINTS;
        goto L8
    }
}
