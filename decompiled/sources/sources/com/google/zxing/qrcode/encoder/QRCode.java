package com.google.zxing.qrcode.encoder;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Mode;
import com.google.zxing.qrcode.decoder.Version;

/* loaded from: classes5.dex */
public final class QRCode {
    private ErrorCorrectionLevel ecLevel;
    private int maskPattern;
    private ByteMatrix matrix;
    private Mode mode;
    private Version version;

    public QRCode() {
        this.maskPattern = -1;
    }

    public static boolean isValidMaskPattern(int r1) {
        if (r1 >= 0) goto L4;
        return false;
    L4:
        if (r1 >= 8) goto L9;
        return true;
    L9:
        return false;
    }

    public ByteMatrix getMatrix() {
        return this.matrix;
    }

    public void setECLevel(ErrorCorrectionLevel r1) {
        this.ecLevel = r1;
    }

    public void setMaskPattern(int r1) {
        this.maskPattern = r1;
    }

    public void setMatrix(ByteMatrix r1) {
        this.matrix = r1;
    }

    public void setMode(Mode r1) {
        this.mode = r1;
    }

    public void setVersion(Version r1) {
        this.version = r1;
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder(200);
        r0.append("<<\n mode: ");
        r0.append(this.mode);
        r0.append("\n ecLevel: ");
        r0.append(this.ecLevel);
        r0.append("\n version: ");
        r0.append(this.version);
        r0.append("\n maskPattern: ");
        r0.append(this.maskPattern);
        if (this.matrix != null) goto L5;
        r0.append("\n matrix: null\n");
    L6:
        r0.append(">>\n");
        return r0.toString();
    L5:
        r0.append("\n matrix:\n");
        r0.append(this.matrix);
        goto L6
    }
}
