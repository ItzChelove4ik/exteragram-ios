package com.android.dx.dex.code;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.rop.code.SourcePosition;
import com.android.dx.rop.cst.Constant;
import com.android.dx.util.Hex;
import com.google.protobuf.MessageSchema$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
public final class MultiCstInsn extends FixedSizeInsn {
    private static final int NOT_SET = -1;
    private int classIndex;
    private final Constant[] constants;
    private final int[] index;

    public MultiCstInsn(Dop r3, SourcePosition r4, RegisterSpecList r5, Constant[] r6) {
        super(r3, r4, r5);
        if (r6 == null) goto L14;
        this.constants = r6;
        this.index = new int[r6.length];
        int r42 = 0;
    L5:
        int[] r52 = this.index;
        if (r42 >= r52.length) goto L12;
        if (r6[r42] == null) goto L10;
        r52[r42] = -1;
        r42 = r42 + 1;
        goto L5
    L10:
        f$$ExternalSyntheticBUOutline2.m("constants[i] == null");
        throw null;
    L12:
        this.classIndex = -1;
        return;
    L14:
        f$$ExternalSyntheticBUOutline2.m("constants == null");
        throw null;
    }

    private MultiCstInsn(Dop r1, SourcePosition r2, RegisterSpecList r3, Constant[] r4, int[] r5, int r6) {
        super(r1, r2, r3);
        this.constants = r4;
        this.index = r5;
        this.classIndex = r6;
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public String argString() {
        StringBuilder r0 = new StringBuilder();
        int r1 = 0;
    L4:
        if (r1 >= this.constants.length) goto L10;
        if (r0.length() <= 0) goto L8;
        r0.append(", ");
    L8:
        r0.append(this.constants[r1].toHuman());
        r1 = r1 + 1;
        goto L4
    L10:
        return r0.toString();
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public String cstComment() {
        StringBuilder r0 = new StringBuilder();
        int r1 = 0;
    L4:
        if (r1 >= this.constants.length) goto L17;
        if (hasIndex(r1) == false) goto L7;
        if (r1 <= 0) goto L11;
        r0.append(", ");
    L11:
        r0.append(getConstant(r1).typeName());
        r0.append('@');
        int r2 = getIndex(r1);
        if (r2 >= 65536) goto L14;
        r0.append(Hex.u2(r2));
    L15:
        r1 = r1 + 1;
        goto L4
    L14:
        r0.append(Hex.u4(r2));
        goto L15
    L7:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L17:
        return r0.toString();
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public String cstString() {
        return argString();
    }

    public int getClassIndex() {
        if (hasClassIndex() == true) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("class index not yet set");
        return 0;
    L5:
        return this.classIndex;
    }

    public Constant getConstant(int r1) {
        return this.constants[r1];
    }

    public int getIndex(int r3) {
        if (hasIndex(r3) == true) goto L5;
        MessageSchema$$ExternalSyntheticBUOutline0.m("index not yet set for constant ", r3, " value = ", this.constants[r3]);
        return 0;
    L5:
        return this.index[r3];
    }

    public int getNumberOfConstants() {
        return this.constants.length;
    }

    public boolean hasClassIndex() {
        if (this.classIndex == (-1)) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean hasIndex(int r1) {
        if (this.index[r1] == (-1)) goto L6;
        return true;
    L6:
        return false;
    }

    public void setClassIndex(int r2) {
        if (r2 >= 0) goto L4;
        f$$ExternalSyntheticBUOutline1.m("index < 0");
        return;
    L4:
        if (hasClassIndex() == true) goto L7;
        this.classIndex = r2;
        return;
    L7:
        Segment$$ExternalSyntheticBUOutline1.m("class index already set");
    }

    public void setIndex(int r2, int r3) {
        if (r3 >= 0) goto L4;
        f$$ExternalSyntheticBUOutline1.m("index < 0");
        return;
    L4:
        if (hasIndex(r2) == true) goto L7;
        this.index[r2] = r3;
        return;
    L7:
        Segment$$ExternalSyntheticBUOutline1.m("index already set");
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public DalvInsn withOpcode(Dop r8) {
        return new MultiCstInsn(r8, getPosition(), getRegisters(), this.constants, this.index, this.classIndex);
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public DalvInsn withRegisters(RegisterSpecList r8) {
        return new MultiCstInsn(getOpcode(), getPosition(), r8, this.constants, this.index, this.classIndex);
    }
}
