package com.android.dx;

import com.android.dx.rop.cst.CstBoolean;
import com.android.dx.rop.cst.CstByte;
import com.android.dx.rop.cst.CstChar;
import com.android.dx.rop.cst.CstDouble;
import com.android.dx.rop.cst.CstFloat;
import com.android.dx.rop.cst.CstInteger;
import com.android.dx.rop.cst.CstKnownNull;
import com.android.dx.rop.cst.CstLong;
import com.android.dx.rop.cst.CstShort;
import com.android.dx.rop.cst.CstString;
import com.android.dx.rop.cst.CstType;
import com.android.dx.rop.cst.TypedConstant;

/* loaded from: classes.dex */
final class Constants {
    private Constants() {
    }

    public static TypedConstant getConstant(Object r2) {
        if (r2 != null) goto L6;
        return CstKnownNull.THE_ONE;
    L6:
        if ((r2 instanceof Boolean) == false) goto L10;
        return CstBoolean.make(((Boolean) r2).booleanValue());
    L10:
        if ((r2 instanceof Byte) == false) goto L14;
        return CstByte.make(((Byte) r2).byteValue());
    L14:
        if ((r2 instanceof Character) == false) goto L18;
        return CstChar.make(((Character) r2).charValue());
    L18:
        if ((r2 instanceof Double) == false) goto L22;
        return CstDouble.make(Double.doubleToLongBits(((Double) r2).doubleValue()));
    L22:
        if ((r2 instanceof Float) == false) goto L26;
        return CstFloat.make(Float.floatToIntBits(((Float) r2).floatValue()));
    L26:
        if ((r2 instanceof Integer) == false) goto L30;
        return CstInteger.make(((Integer) r2).intValue());
    L30:
        if ((r2 instanceof Long) == false) goto L34;
        return CstLong.make(((Long) r2).longValue());
    L34:
        if ((r2 instanceof Short) == false) goto L38;
        return CstShort.make(((Short) r2).shortValue());
    L38:
        if ((r2 instanceof String) == false) goto L42;
        return new CstString((String) r2);
    L42:
        if ((r2 instanceof Class) == false) goto L46;
        return new CstType(TypeId.get((Class) r2).ropType);
    L46:
        if ((r2 instanceof TypeId) == true) goto L48;
        Constants$$ExternalSyntheticBUOutline0.m("Not a constant: ", r2);
        return null;
    L48:
        return new CstType(((TypeId) r2).ropType);
    }
}
