package com.android.dx.rop.cst;

import com.android.dx.rop.type.Type;

/* loaded from: classes4.dex */
public final class Zeroes {
    private Zeroes() {
    }

    public static Constant zeroFor(Type r3) {
        switch(r3.getBasicType()) {
            case 1: goto L23;
            case 2: goto L21;
            case 3: goto L19;
            case 4: goto L17;
            case 5: goto L15;
            case 6: goto L13;
            case 7: goto L11;
            case 8: goto L9;
            case 9: goto L7;
            default: goto L5;
        };
    L5:
        throw new UnsupportedOperationException("no zero for type: " + r3.toHuman());
    L7:
        return CstKnownNull.THE_ONE;
    L9:
        return CstShort.VALUE_0;
    L11:
        return CstLong.VALUE_0;
    L13:
        return CstInteger.VALUE_0;
    L15:
        return CstFloat.VALUE_0;
    L17:
        return CstDouble.VALUE_0;
    L19:
        return CstChar.VALUE_0;
    L21:
        return CstByte.VALUE_0;
    L23:
        return CstBoolean.VALUE_FALSE;
    }
}
