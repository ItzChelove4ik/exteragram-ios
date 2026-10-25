package com.android.dx.dex.code;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.rop.code.SourcePosition;
import com.android.dx.rop.cst.Constant;
import com.android.dx.rop.cst.CstLiteral32;
import com.android.dx.rop.cst.CstLiteral64;
import com.android.dx.rop.cst.CstType;
import com.android.dx.util.AnnotatedOutput;
import com.android.dx.util.Hex;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public final class ArrayData extends VariableSizeInsn {
    private final Constant arrayType;
    private final int elemWidth;
    private final int initLength;
    private final CodeAddress user;
    private final ArrayList<Constant> values;

    public ArrayData(SourcePosition r2, CodeAddress r3, ArrayList<Constant> r4, Constant r5) {
        super(r2, RegisterSpecList.EMPTY);
        if (r3 == null) goto L39;
        if (r4 != null) goto L6;
        f$$ExternalSyntheticBUOutline2.m("values == null");
        throw null;
    L6:
        if (r4.size() <= 0) goto L35;
        this.arrayType = r5;
        if (r5 != CstType.BYTE_ARRAY) goto L10;
    L32:
        this.elemWidth = 1;
    L33:
        this.user = r3;
        this.values = r4;
        this.initLength = r4.size();
        return;
    L10:
        if (r5 == CstType.BOOLEAN_ARRAY) goto L32;
        if (r5 != CstType.SHORT_ARRAY) goto L15;
    L31:
        this.elemWidth = 2;
        goto L33
    L15:
        if (r5 == CstType.CHAR_ARRAY) goto L31;
        if (r5 != CstType.INT_ARRAY) goto L20;
    L30:
        this.elemWidth = 4;
        goto L33
    L20:
        if (r5 == CstType.FLOAT_ARRAY) goto L30;
        if (r5 != CstType.LONG_ARRAY) goto L25;
    L29:
        this.elemWidth = 8;
        goto L33
    L25:
        if (r5 == CstType.DOUBLE_ARRAY) goto L29;
        f$$ExternalSyntheticBUOutline1.m("Unexpected constant type");
        throw null;
    L35:
        f$$ExternalSyntheticBUOutline1.m("Illegal number of init values");
        throw null;
    L39:
        f$$ExternalSyntheticBUOutline2.m("user == null");
        throw null;
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public String argString() {
        StringBuilder r0 = new StringBuilder(100);
        int r1 = this.values.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L6;
        r0.append("\n    ");
        r0.append(r2);
        r0.append(": ");
        r0.append(this.values.get(r2).toHuman());
        r2 = r2 + 1;
        goto L3
    L6:
        return r0.toString();
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public int codeSize() {
        return (((this.initLength * this.elemWidth) + 1) / 2) + 4;
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public String listingString0(boolean r4) {
        int r42 = this.user.getAddress();
        StringBuilder r0 = new StringBuilder(100);
        int r1 = this.values.size();
        r0.append("fill-array-data-payload // for fill-array-data @ ");
        r0.append(Hex.u2(r42));
        int r43 = 0;
    L3:
        if (r43 >= r1) goto L6;
        r0.append("\n  ");
        r0.append(r43);
        r0.append(": ");
        r0.append(this.values.get(r43).toHuman());
        r43 = r43 + 1;
        goto L3
    L6:
        return r0.toString();
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public DalvInsn withRegisters(RegisterSpecList r4) {
        return new ArrayData(getPosition(), this.user, this.values, this.arrayType);
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public void writeTo(AnnotatedOutput r8) {
        int r0 = this.values.size();
        r8.writeShort(768);
        r8.writeShort(this.elemWidth);
        r8.writeInt(this.initLength);
        int r1 = this.elemWidth;
        if (r1 == 1) goto L19;
        if (r1 != 2) goto L6;
        int r12 = 0;
    L17:
        if (r12 >= r0) goto L23;
        r8.writeShort((short) ((CstLiteral32) this.values.get(r12)).getIntBits());
        r12 = r12 + 1;
    L23:
        if (this.elemWidth == 1) goto L25;
        return;
    L25:
        if ((r0 % 2) == 0) goto L33;
        r8.writeByte(0);
        return;
    L33:
        return;
    L6:
        if (r1 != 4) goto L8;
        int r13 = 0;
    L14:
        if (r13 >= r0) goto L23;
        r8.writeInt(((CstLiteral32) this.values.get(r13)).getIntBits());
        r13 = r13 + 1;
        goto L14
    L8:
        if (r1 != 8) goto L23;
        int r14 = 0;
    L11:
        if (r14 >= r0) goto L23;
        r8.writeLong(((CstLiteral64) this.values.get(r14)).getLongBits());
        r14 = r14 + 1;
        goto L11
    L19:
        int r15 = 0;
    L20:
        if (r15 >= r0) goto L23;
        r8.writeByte((byte) ((CstLiteral32) this.values.get(r15)).getIntBits());
        r15 = r15 + 1;
        goto L20
    }
}
