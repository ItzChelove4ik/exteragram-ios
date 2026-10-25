package com.android.dx.cf.code;

import com.android.dx.rop.type.Type;
import com.android.dx.rop.type.TypeBearer;
import com.android.dx.util.Hex;

/* loaded from: classes4.dex */
public final class Merger {
    private Merger() {
    }

    public static boolean isPossiblyAssignableFrom(TypeBearer r5, TypeBearer r6) {
        Type r52 = r5.getType();
        Type r62 = r6.getType();
        if (r52.equals(r62) == false) goto L5;
        return true;
    L5:
        int r0 = r52.getBasicType();
        int r2 = r62.getBasicType();
        if (r0 != 10) goto L8;
        r52 = Type.OBJECT;
        r0 = 9;
    L8:
        if (r2 != 10) goto L11;
        r62 = Type.OBJECT;
        r2 = 9;
    L11:
        if (r0 != 9) goto L43;
        if (r2 != 9) goto L43;
        Type r02 = Type.KNOWN_NULL;
        if (r52 != r02) goto L17;
        return false;
    L17:
        if (r62 != r02) goto L20;
        return true;
    L20:
        if (r52 != Type.OBJECT) goto L23;
        return true;
    L23:
        if (r52.isArray() == false) goto L34;
        if (r62.isArray() == true) goto L27;
        return false;
    L27:
        r52 = r52.getComponentType();
        r62 = r62.getComponentType();
        if (r52.isArray() == false) goto L32;
        if (r62.isArray() == true) goto L27;
    L32:
        return isPossiblyAssignableFrom(r52, r62);
    L34:
        if (r62.isArray() == true) goto L36;
    L41:
        return true;
    L36:
        if (r52 == Type.SERIALIZABLE) goto L41;
        if (r52 == Type.CLONEABLE) goto L41;
        return false;
    L43:
        if (r52.isIntlike() == true) goto L45;
    L47:
        return false;
    L45:
        if (r62.isIntlike() == false) goto L47;
        return true;
    }

    public static OneLocalsArray mergeLocals(OneLocalsArray r5, OneLocalsArray r6) {
        if (r5 != r6) goto L4;
        return r5;
    L4:
        int r0 = r5.getMaxLocals();
        if (r6.getMaxLocals() != r0) goto L21;
        OneLocalsArray r1 = null;
        int r2 = 0;
    L7:
        if (r2 >= r0) goto L16;
        TypeBearer r3 = r5.getOrNull(r2);
        TypeBearer r4 = mergeType(r3, r6.getOrNull(r2));
        if (r4 == r3) goto L15;
        if (r1 != null) goto L12;
        r1 = r5.copy();
    L12:
        if (r4 != null) goto L14;
        r1.invalidate(r2);
        goto L15
    L14:
        r1.set(r2, r4);
    L15:
        r2 = r2 + 1;
        goto L7
    L16:
        if (r1 != null) goto L18;
        return r5;
    L18:
        r1.setImmutable();
        return r1;
    L21:
        throw new SimException("mismatched maxLocals values");
    }

    public static ExecutionStack mergeStack(ExecutionStack r6, ExecutionStack r7) {
        if (r6 != r7) goto L4;
        return r6;
    L4:
        int r0 = r6.size();
        if (r7.size() != r0) goto L26;
        ExecutionStack r1 = null;
        int r2 = 0;
    L7:
        if (r2 >= r0) goto L21;
        TypeBearer r3 = r6.peek(r2);
        TypeBearer r4 = r7.peek(r2);
        TypeBearer r5 = mergeType(r3, r4);
        if (r5 == r3) goto L20;
        if (r1 != null) goto L12;
        r1 = r6.copy();
    L12:
        if (r5 == null) goto L17;
        r1.change(r2, r5);     // Catch: SimException -> L14
    L14:
        e = move-exception;
        e.addContext("...while merging stack[" + Hex.u2(r2) + "]");
        throw e;
    L17:
        throw new SimException("incompatible: " + r3 + ", " + r4);     // Catch: SimException -> L14
    L20:
        r2 = r2 + 1;
        goto L7
    L21:
        if (r1 != null) goto L23;
        return r6;
    L23:
        r1.setImmutable();
        return r1;
    L26:
        throw new SimException("mismatched stack depths");
    }

    public static TypeBearer mergeType(TypeBearer r2, TypeBearer r3) {
        if (r2 != null) goto L4;
    L40:
        return r2;
    L4:
        if (r2.equals(r3) == true) goto L40;
        if (r3 != null) goto L9;
        return null;
    L9:
        Type r22 = r2.getType();
        Type r32 = r3.getType();
        if (r22 != r32) goto L13;
        return r22;
    L13:
        if (r22.isReference() == false) goto L34;
        if (r32.isReference() == false) goto L34;
        Type r0 = Type.KNOWN_NULL;
        if (r22 != r0) goto L19;
        return r32;
    L19:
        if (r32 != r0) goto L22;
        return r22;
    L22:
        if (r22.isArray() == false) goto L32;
        if (r32.isArray() == false) goto L32;
        TypeBearer r23 = mergeType(r22.getComponentType(), r32.getComponentType());
        if (r23 != null) goto L30;
        return Type.OBJECT;
    L30:
        return ((Type) r23).getArrayType();
    L32:
        return Type.OBJECT;
    L34:
        if (r22.isIntlike() == true) goto L36;
    L39:
        return null;
    L36:
        if (r32.isIntlike() == false) goto L39;
        return Type.INT;
    }
}
