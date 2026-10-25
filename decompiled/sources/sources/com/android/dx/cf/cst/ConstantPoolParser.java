package com.android.dx.cf.cst;

import com.android.dx.cf.iface.ParseException;
import com.android.dx.cf.iface.ParseObserver;
import com.android.dx.rop.cst.Constant;
import com.android.dx.rop.cst.CstDouble;
import com.android.dx.rop.cst.CstFieldRef;
import com.android.dx.rop.cst.CstFloat;
import com.android.dx.rop.cst.CstInteger;
import com.android.dx.rop.cst.CstInterfaceMethodRef;
import com.android.dx.rop.cst.CstInvokeDynamic;
import com.android.dx.rop.cst.CstLong;
import com.android.dx.rop.cst.CstMethodHandle;
import com.android.dx.rop.cst.CstMethodRef;
import com.android.dx.rop.cst.CstNat;
import com.android.dx.rop.cst.CstProtoRef;
import com.android.dx.rop.cst.CstString;
import com.android.dx.rop.cst.CstType;
import com.android.dx.rop.cst.StdConstantPool;
import com.android.dx.rop.type.Type;
import com.android.dx.util.ByteArray;
import com.android.dx.util.Hex;
import java.util.BitSet;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public final class ConstantPoolParser {
    private final ByteArray bytes;
    private int endOffset;
    private ParseObserver observer;
    private final int[] offsets;
    private final StdConstantPool pool;

    public ConstantPoolParser(ByteArray r2) {
        int r0 = r2.getUnsignedShort(8);
        this.bytes = r2;
        this.pool = new StdConstantPool(r0);
        this.offsets = new int[r0];
        this.endOffset = -1;
    }

    private void determineOffsets() {
        int r0 = 10;
        int r2 = 1;
    L3:
        int[] r3 = this.offsets;
        if (r2 >= r3.length) goto L21;
        r3[r2] = r0;
        int r32 = this.bytes.getUnsignedByte(r0);
        switch(r32) {
            case 1: goto L17;
            case 2: goto L8;
            case 3: goto L23;
            case 4: goto L23;
            case 5: goto L15;
            case 6: goto L15;
            case 7: goto L13;
            case 8: goto L13;
            case 9: goto L23;
            case 10: goto L23;
            case 11: goto L23;
            case 12: goto L23;
            case 13: goto L8;
            case 14: goto L8;
            case 15: goto L14;
            case 16: goto L13;
            case 17: goto L8;
            case 18: goto L23;
            default: goto L8;
        };
    L13:
        r0 = r0 + 3;     // Catch: ParseException -> L9
    L12:
        int r33 = 1;
    L18:
        r2 = r2 + r33;
        goto L3
    L14:
        r0 = r0 + 4;     // Catch: ParseException -> L9
        goto L12
    L15:
        r0 = r0 + 9;     // Catch: ParseException -> L9
        r33 = 2;
        goto L18
    L23:
        r0 = r0 + 5;     // Catch: ParseException -> L9
    L9:
        e = move-exception;
        e.addContext("...while preparsing cst " + Hex.u2(r2) + " at offset " + Hex.u4(r0));
        throw e;
    L17:
        r0 = r0 + (this.bytes.getUnsignedShort(r0 + 1) + 3);
        goto L12
    L8:
        throw new ParseException("unknown tag byte: " + Hex.u1(r32));     // Catch: ParseException -> L9
    L21:
        this.endOffset = r0;
    }

    private static int getMethodHandleTypeForKind(int r1) {
        switch(r1) {
            case 1: goto L21;
            case 2: goto L19;
            case 3: goto L17;
            case 4: goto L15;
            case 5: goto L13;
            case 6: goto L11;
            case 7: goto L9;
            case 8: goto L7;
            case 9: goto L5;
            default: goto L3;
        };
    L3:
        CharCodeKt$$ExternalSyntheticBUOutline0.m("invalid kind: ", r1);
        return 0;
    L5:
        return 8;
    L7:
        return 6;
    L9:
        return 7;
    L11:
        return 4;
    L13:
        return 5;
    L15:
        return 0;
    L17:
        return 2;
    L19:
        return 1;
    L21:
        return 3;
    }

    private void parse() {
        determineOffsets();
        ParseObserver r0 = this.observer;
        int r2 = 1;
        if (r0 == null) goto L5;
        r0.parsed(this.bytes, 8, 2, "constant_pool_count: " + Hex.u2(this.offsets.length));
        this.observer.parsed(this.bytes, 10, 0, "\nconstant_pool:");
        this.observer.changeIndent(1);
    L5:
        BitSet r02 = new BitSet(this.offsets.length);
        int r3 = 1;
    L6:
        int[] r4 = this.offsets;
        if (r3 >= r4.length) goto L15;
        if (r4[r3] == 0) goto L13;
        if (this.pool.getOrNull(r3) != null) goto L13;
        parse0(r3, r02);
    L13:
        r3 = r3 + 1;
        goto L6
    L15:
        if (this.observer != null) goto L17;
        return;
    L17:
        if (r2 >= this.offsets.length) goto L34;
        Constant r32 = this.pool.getOrNull(r2);
        if (r32 == null) goto L33;
        int r42 = this.offsets[r2];
        int r5 = this.endOffset;
        int r6 = r2 + 1;
    L22:
        int[] r7 = this.offsets;
        if (r6 >= r7.length) goto L29;
        int r72 = r7[r6];
        if (r72 != 0) goto L26;
        r6 = r6 + 1;
        goto L22
    L26:
        r5 = r72;
    L29:
        if (r02.get(r2) == false) goto L31;
        String r33 = Hex.u2(r2) + ": utf8{\"" + r32.toHuman() + "\"}";
    L32:
        this.observer.parsed(this.bytes, r42, r5 - r42, r33);
        goto L33
    L31:
        r33 = Hex.u2(r2) + ": " + r32.toString();
    L33:
        r2 = r2 + 1;
        goto L17
    L34:
        this.observer.changeIndent(-1);
        this.observer.parsed(this.bytes, this.endOffset, 0, "end constant_pool");
    }

    private Constant parse0(int r9, BitSet r10) {
        Constant r5 = this.pool.getOrNull(r9);
        if (r5 == null) goto L5;
        return r5;
    L5:
        int r52 = this.offsets[r9];
        int r6 = this.bytes.getUnsignedByte(r52);     // Catch: RuntimeException -> L10 ParseException -> L12
        switch(r6) {
            case 1: goto L43;
            case 2: goto L9;
            case 3: goto L42;
            case 4: goto L41;
            case 5: goto L40;
            case 6: goto L39;
            case 7: goto L37;
            case 8: goto L36;
            case 9: goto L35;
            case 10: goto L34;
            case 11: goto L33;
            case 12: goto L31;
            case 13: goto L9;
            case 14: goto L9;
            case 15: goto L16;
            case 16: goto L15;
            case 17: goto L9;
            case 18: goto L14;
            default: goto L9;
        };     // Catch: RuntimeException -> L10 ParseException -> L12
    L14:
        Constant r102 = CstInvokeDynamic.make(this.bytes.getUnsignedShort(r52 + 1), (CstNat) parse0(this.bytes.getUnsignedShort(r52 + 3), r10));     // Catch: RuntimeException -> L10 ParseException -> L12
    L45:
        this.pool.set(r9, r102);
        return r102;
    L15:
        r102 = CstProtoRef.make((CstString) parse0(this.bytes.getUnsignedShort(r52 + 1), r10));     // Catch: RuntimeException -> L10 ParseException -> L12
        goto L45
    L16:
        int r2 = this.bytes.getUnsignedByte(r52 + 1);     // Catch: RuntimeException -> L10 ParseException -> L12
        int r62 = this.bytes.getUnsignedShort(r52 + 2);     // Catch: RuntimeException -> L10 ParseException -> L12
        switch(r2) {
            case 1: goto L29;
            case 2: goto L29;
            case 3: goto L29;
            case 4: goto L29;
            case 5: goto L28;
            case 6: goto L21;
            case 7: goto L21;
            case 8: goto L28;
            case 9: goto L20;
            default: goto L19;
        };     // Catch: RuntimeException -> L10 ParseException -> L12
    L20:
        Constant r103 = (CstInterfaceMethodRef) parse0(r62, r10);     // Catch: RuntimeException -> L10 ParseException -> L12
    L30:
        r102 = CstMethodHandle.make(getMethodHandleTypeForKind(r2), r103);     // Catch: RuntimeException -> L10 ParseException -> L12
        goto L45
    L21:
        r103 = parse0(r62, r10);     // Catch: RuntimeException -> L10 ParseException -> L12
        if ((r103 instanceof CstMethodRef) == true) goto L30;
        if ((r103 instanceof CstInterfaceMethodRef) == true) goto L30;
        throw new ParseException("Unsupported ref constant type for MethodHandle " + r103.getClass());     // Catch: RuntimeException -> L10 ParseException -> L12
    L28:
        r103 = (CstMethodRef) parse0(r62, r10);     // Catch: RuntimeException -> L10 ParseException -> L12
        goto L30
    L29:
        r103 = (CstFieldRef) parse0(r62, r10);     // Catch: RuntimeException -> L10 ParseException -> L12
        goto L30
    L19:
        throw new ParseException("Unsupported MethodHandle kind: " + r2);     // Catch: RuntimeException -> L10 ParseException -> L12
    L31:
        Constant r3 = new CstNat((CstString) parse0(this.bytes.getUnsignedShort(r52 + 1), r10), (CstString) parse0(this.bytes.getUnsignedShort(r52 + 3), r10));     // Catch: RuntimeException -> L10 ParseException -> L12
    L32:
        r102 = r3;
        goto L45
    L33:
        r3 = new CstInterfaceMethodRef((CstType) parse0(this.bytes.getUnsignedShort(r52 + 1), r10), (CstNat) parse0(this.bytes.getUnsignedShort(r52 + 3), r10));     // Catch: RuntimeException -> L10 ParseException -> L12
        goto L32
    L34:
        r3 = new CstMethodRef((CstType) parse0(this.bytes.getUnsignedShort(r52 + 1), r10), (CstNat) parse0(this.bytes.getUnsignedShort(r52 + 3), r10));     // Catch: RuntimeException -> L10 ParseException -> L12
        goto L32
    L35:
        r3 = new CstFieldRef((CstType) parse0(this.bytes.getUnsignedShort(r52 + 1), r10), (CstNat) parse0(this.bytes.getUnsignedShort(r52 + 3), r10));     // Catch: RuntimeException -> L10 ParseException -> L12
        goto L32
    L36:
        r102 = parse0(this.bytes.getUnsignedShort(r52 + 1), r10);     // Catch: RuntimeException -> L10 ParseException -> L12
        goto L45
    L37:
        Constant r22 = new CstType(Type.internClassName(((CstString) parse0(this.bytes.getUnsignedShort(r52 + 1), r10)).getString()));     // Catch: RuntimeException -> L10 ParseException -> L12
    L38:
        r102 = r22;
        goto L45
    L39:
        r102 = CstDouble.make(this.bytes.getLong(r52 + 1));     // Catch: RuntimeException -> L10 ParseException -> L12
        goto L45
    L40:
        r102 = CstLong.make(this.bytes.getLong(r52 + 1));     // Catch: RuntimeException -> L10 ParseException -> L12
        goto L45
    L41:
        r102 = CstFloat.make(this.bytes.getInt(r52 + 1));     // Catch: RuntimeException -> L10 ParseException -> L12
        goto L45
    L42:
        r102 = CstInteger.make(this.bytes.getInt(r52 + 1));     // Catch: RuntimeException -> L10 ParseException -> L12
        goto L45
    L43:
        r22 = parseUtf8(r52);     // Catch: RuntimeException -> L10 ParseException -> L12
        r10.set(r9);     // Catch: RuntimeException -> L10 ParseException -> L12
        goto L38
    L9:
        throw new ParseException("unknown tag byte: " + Hex.u1(r6));     // Catch: RuntimeException -> L10 ParseException -> L12
    L12:
        e = move-exception;
        e.addContext("...while parsing cst " + Hex.u2(r9) + " at offset " + Hex.u4(r52));
        throw e;
    L10:
        e = move-exception;
        ParseException r104 = new ParseException(e);
        r104.addContext("...while parsing cst " + Hex.u2(r9) + " at offset " + Hex.u4(r52));
        throw r104;
    }

    private void parseIfNecessary() {
        if (this.endOffset >= 0) goto L6;
        parse();
        return;
    }

    private CstString parseUtf8(int r3) {
        int r0 = this.bytes.getUnsignedShort(r3 + 1);
        int r32 = r3 + 3;
        return new CstString(this.bytes.slice(r32, r0 + r32));
    L5:
        e = move-exception;
        throw new ParseException(e);
    }

    public int getEndOffset() {
        parseIfNecessary();
        return this.endOffset;
    }

    public StdConstantPool getPool() {
        parseIfNecessary();
        return this.pool;
    }

    public void setObserver(ParseObserver r1) {
        this.observer = r1;
    }
}
