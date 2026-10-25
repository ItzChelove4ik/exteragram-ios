package com.android.dx.dex.code;

import c.f$$ExternalSyntheticBUOutline1;
import com.android.dx.rop.code.RegisterSpec;
import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.rop.code.SourcePosition;
import com.android.dx.util.AnnotatedOutput;

/* loaded from: classes.dex */
public final class HighRegisterPrefix extends VariableSizeInsn {
    private SimpleInsn[] insns;

    public HighRegisterPrefix(SourcePosition r1, RegisterSpecList r2) {
        super(r1, r2);
        if (r2.size() == 0) goto L6;
        this.insns = null;
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("registers.size() == 0");
        throw null;
    }

    private void calculateInsnsIfNecessary() {
        if (this.insns != null) goto L8;
        RegisterSpecList r0 = getRegisters();
        int r1 = r0.size();
        this.insns = new SimpleInsn[r1];
        int r2 = 0;
        int r3 = 0;
    L6:
        if (r2 >= r1) goto L10;
        RegisterSpec r4 = r0.get(r2);
        this.insns[r2] = moveInsnFor(r4, r3);
        r3 = r3 + r4.getCategory();
        r2 = r2 + 1;
        goto L6
    L10:
        return;
    }

    private static SimpleInsn moveInsnFor(RegisterSpec r2, int r3) {
        return DalvInsn.makeMove(SourcePosition.NO_INFO, RegisterSpec.make(r3, r2.getType()), r2);
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public String argString() {
        return null;
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public int codeSize() {
        calculateInsnsIfNecessary();
        SimpleInsn[] r4 = this.insns;
        int r0 = r4.length;
        int r1 = 0;
        int r2 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r2 = r2 + r4[r1].codeSize();
        r1 = r1 + 1;
        goto L3
    L5:
        return r2;
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public String listingString0(boolean r8) {
        RegisterSpecList r7 = getRegisters();
        int r0 = r7.size();
        StringBuilder r1 = new StringBuilder(100);
        int r2 = 0;
        int r3 = 0;
    L3:
        if (r2 >= r0) goto L9;
        RegisterSpec r4 = r7.get(r2);
        SimpleInsn r5 = moveInsnFor(r4, r3);
        if (r2 == 0) goto L7;
        r1.append('\n');
    L7:
        r1.append(r5.listingString0(r8));
        r3 = r3 + r4.getCategory();
        r2 = r2 + 1;
        goto L3
    L9:
        return r1.toString();
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public DalvInsn withRegisters(RegisterSpecList r2) {
        return new HighRegisterPrefix(getPosition(), r2);
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public void writeTo(AnnotatedOutput r4) {
        calculateInsnsIfNecessary();
        SimpleInsn[] r3 = this.insns;
        int r0 = r3.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r3[r1].writeTo(r4);
        r1 = r1 + 1;
        goto L3
    }
}
