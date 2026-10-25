package com.android.dx.dex.code;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.rop.code.SourcePosition;
import com.android.dx.rop.cst.Constant;
import com.android.dx.rop.cst.CstString;
import com.android.dx.util.Hex;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
public final class CstInsn extends FixedSizeInsn {
    private int classIndex;
    private final Constant constant;
    private int index;

    public CstInsn(Dop r1, SourcePosition r2, RegisterSpecList r3, Constant r4) {
        super(r1, r2, r3);
        if (r4 == null) goto L6;
        this.constant = r4;
        this.index = -1;
        this.classIndex = -1;
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("constant == null");
        throw null;
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public String argString() {
        return this.constant.toHuman();
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public String cstComment() {
        if (hasIndex() == true) goto L6;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        StringBuilder r0 = new StringBuilder(20);
        r0.append(getConstant().typeName());
        r0.append('@');
        int r2 = this.index;
        if (r2 >= 65536) goto L9;
        r0.append(Hex.u2(r2));
    L11:
        return r0.toString();
    L9:
        r0.append(Hex.u4(r2));
        goto L11
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public String cstString() {
        Constant r1 = this.constant;
        if ((r1 instanceof CstString) == false) goto L7;
        return ((CstString) r1).toQuoted();
    L7:
        return r1.toHuman();
    }

    public int getClassIndex() {
        int r0 = this.classIndex;
        if (r0 < 0) goto L5;
        return r0;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("class index not yet set");
        return 0;
    }

    public Constant getConstant() {
        return this.constant;
    }

    public int getIndex() {
        int r0 = this.index;
        if (r0 < 0) goto L5;
        return r0;
    L5:
        CstInsn$$ExternalSyntheticBUOutline0.m("index not yet set for ", this.constant);
        return 0;
    }

    public boolean hasClassIndex() {
        if (this.classIndex < 0) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean hasIndex() {
        if (this.index < 0) goto L6;
        return true;
    L6:
        return false;
    }

    public void setClassIndex(int r2) {
        if (r2 >= 0) goto L4;
        f$$ExternalSyntheticBUOutline1.m("index < 0");
        return;
    L4:
        if (this.classIndex >= 0) goto L7;
        this.classIndex = r2;
        return;
    L7:
        Segment$$ExternalSyntheticBUOutline1.m("class index already set");
    }

    public void setIndex(int r2) {
        if (r2 >= 0) goto L4;
        f$$ExternalSyntheticBUOutline1.m("index < 0");
        return;
    L4:
        if (this.index >= 0) goto L7;
        this.index = r2;
        return;
    L7:
        Segment$$ExternalSyntheticBUOutline1.m("index already set");
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public DalvInsn withOpcode(Dop r5) {
        CstInsn r0 = new CstInsn(r5, getPosition(), getRegisters(), this.constant);
        int r52 = this.index;
        if (r52 < 0) goto L5;
        r0.setIndex(r52);
    L5:
        int r4 = this.classIndex;
        if (r4 < 0) goto L8;
        r0.setClassIndex(r4);
    L8:
        return r0;
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public DalvInsn withRegisters(RegisterSpecList r5) {
        CstInsn r0 = new CstInsn(getOpcode(), getPosition(), r5, this.constant);
        int r52 = this.index;
        if (r52 < 0) goto L5;
        r0.setIndex(r52);
    L5:
        int r4 = this.classIndex;
        if (r4 < 0) goto L8;
        r0.setClassIndex(r4);
    L8:
        return r0;
    }
}
