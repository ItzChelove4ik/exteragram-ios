package org.mvel2.asm;

/* loaded from: classes5.dex */
final class Handler {
    final int catchType;
    final String catchTypeDescriptor;
    final Label endPc;
    final Label handlerPc;
    Handler nextHandler;
    final Label startPc;

    public Handler(Handler r7, Label r8, Label r9) {
        this(r8, r9, r7.handlerPc, r7.catchType, r7.catchTypeDescriptor);
        this.nextHandler = r7.nextHandler;
    }

    public Handler(Label r1, Label r2, Label r3, int r4, String r5) {
        this.startPc = r1;
        this.endPc = r2;
        this.handlerPc = r3;
        this.catchType = r4;
        this.catchTypeDescriptor = r5;
    }

    public static int getExceptionTableLength(Handler r1) {
        int r0 = 0;
    L3:
        if (r1 == null) goto L5;
        r0 = r0 + 1;
        r1 = r1.nextHandler;
        goto L3
    L5:
        return r0;
    }

    public static int getExceptionTableSize(Handler r0) {
        return (getExceptionTableLength(r0) * 8) + 2;
    }

    public static void putExceptionTable(Handler r2, ByteVector r3) {
        r3.putShort(getExceptionTableLength(r2));
    L3:
        if (r2 == null) goto L5;
        r3.putShort(r2.startPc.bytecodeOffset).putShort(r2.endPc.bytecodeOffset).putShort(r2.handlerPc.bytecodeOffset).putShort(r2.catchType);
        r2 = r2.nextHandler;
        goto L3
    }

    public static Handler removeRange(Handler r7, Label r8, Label r9) {
        if (r7 != null) goto L5;
        return null;
    L5:
        Handler r0 = removeRange(r7.nextHandler, r8, r9);
        r7.nextHandler = r0;
        Label r1 = r7.startPc;
        int r2 = r1.bytecodeOffset;
        Label r3 = r7.endPc;
        int r4 = r3.bytecodeOffset;
        int r5 = r8.bytecodeOffset;
        if (r9 != null) goto L8;
        int r6 = Integer.MAX_VALUE;
    L9:
        if (r5 >= r4) goto L22;
        if (r6 <= r2) goto L22;
        if (r5 > r2) goto L17;
        if (r6 < r4) goto L16;
        return r0;
    L16:
        return new Handler(r7, r9, r3);
    L17:
        if (r6 >= r4) goto L19;
        r7.nextHandler = new Handler(r7, r9, r3);
        return new Handler(r7, r7.startPc, r8);
    L19:
        return new Handler(r7, r1, r8);
    L22:
        return r7;
    L8:
        r6 = r9.bytecodeOffset;
        goto L9
    }
}
