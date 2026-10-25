package com.google.zxing;

import kotlin.UByte;
import org.mvel2.asm.signature.SignatureVisitor;

/* loaded from: classes5.dex */
public abstract class LuminanceSource {
    private final int height;
    private final int width;

    public LuminanceSource(int r1, int r2) {
        this.width = r1;
        this.height = r2;
    }

    public final int getHeight() {
        return this.height;
    }

    public abstract byte[] getMatrix();

    public abstract byte[] getRow(int r1, byte[] r2);

    public final int getWidth() {
        return this.width;
    }

    public final String toString() {
        int r0 = this.width;
        byte[] r1 = new byte[r0];
        StringBuilder r2 = new StringBuilder(this.height * (r0 + 1));
        int r3 = 0;
    L4:
        if (r3 >= this.height) goto L21;
        r1 = getRow(r3, r1);
        int r4 = 0;
    L7:
        if (r4 >= this.width) goto L19;
        int r5 = r1[r4] & UByte.MAX_VALUE;
        if (r5 >= 64) goto L12;
        char r52 = '#';
    L18:
        r2.append(r52);
        r4 = r4 + 1;
        goto L7
    L12:
        if (r5 >= 128) goto L15;
        r52 = SignatureVisitor.EXTENDS;
        goto L18
    L15:
        if (r5 >= 192) goto L17;
        r52 = '.';
        goto L18
    L17:
        r52 = ' ';
        goto L18
    L19:
        r2.append('\n');
        r3 = r3 + 1;
        goto L4
    L21:
        return r2.toString();
    }
}
