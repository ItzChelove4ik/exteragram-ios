package com.android.dx.cf.code;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.cf.code.ByteCatchList;
import com.android.dx.cf.code.BytecodeArray;
import com.android.dx.rop.cst.Constant;
import com.android.dx.rop.cst.CstInvokeDynamic;
import com.android.dx.rop.cst.CstMemberRef;
import com.android.dx.rop.cst.CstMethodHandle;
import com.android.dx.rop.cst.CstProtoRef;
import com.android.dx.rop.cst.CstString;
import com.android.dx.rop.cst.CstType;
import com.android.dx.rop.type.Type;
import com.android.dx.util.Bits;
import com.android.dx.util.IntList;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public final class BasicBlocker implements BytecodeArray.Visitor {
    private final int[] blockSet;
    private final ByteCatchList[] catchLists;
    private final int[] liveSet;
    private final ConcreteMethod method;
    private int previousOffset;
    private final IntList[] targetLists;
    private final int[] workSet;

    private BasicBlocker(ConcreteMethod r2) {
        if (r2 == null) goto L6;
        this.method = r2;
        int r22 = r2.getCode().size() + 1;
        this.workSet = Bits.makeBitSet(r22);
        this.liveSet = Bits.makeBitSet(r22);
        this.blockSet = Bits.makeBitSet(r22);
        this.targetLists = new IntList[r22];
        this.catchLists = new ByteCatchList[r22];
        this.previousOffset = -1;
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("method == null");
        throw null;
    }

    private void addWorkIfNecessary(int r2, boolean r3) {
        if (Bits.get(this.liveSet, r2) == true) goto L5;
        Bits.set(this.workSet, r2);
    L5:
        if (r3 == false) goto L8;
        Bits.set(this.blockSet, r2);
        return;
    }

    private void doit() {
        BytecodeArray r0 = this.method.getCode();
        ByteCatchList r1 = this.method.getCatches();
        int r2 = r1.size();
        Bits.set(this.workSet, 0);
        Bits.set(this.blockSet, 0);
    L4:
        if (Bits.isEmpty(this.workSet) == true) goto L15;
        r0.processWorkSet(this.workSet, this);     // Catch: IllegalArgumentException -> L12
        int r3 = 0;
    L7:
        if (r3 >= r2) goto L4;
        ByteCatchList.Item r5 = r1.get(r3);
        int r6 = r5.getStartPc();
        int r7 = r5.getEndPc();
        if (Bits.anyInRange(this.liveSet, r6, r7) == false) goto L11;
        Bits.set(this.blockSet, r6);
        Bits.set(this.blockSet, r7);
        addWorkIfNecessary(r5.getHandlerPc(), true);
    L11:
        r3 = r3 + 1;
    L12:
        e = move-exception;
        throw new SimException("flow of control falls off end of method", e);
    }

    private ByteBlockList getBlockList() {
        ByteBlock[] r0 = new ByteBlock[this.method.getCode().size()];
        int r1 = 0;
        int r3 = 0;
        int r8 = 0;
    L3:
        int r5 = Bits.findFirst(this.blockSet, r3 + 1);
        if (r5 < 0) goto L5;
        if (Bits.get(this.liveSet, r3) == false) goto L25;
        int r2 = r5 - 1;
        IntList r4 = null;
    L12:
        if (r2 < r3) goto L17;
        r4 = this.targetLists[r2];
        if (r4 != null) goto L18;
        r2 = r2 - 1;
    L18:
        if (r4 != null) goto L21;
        r4 = IntList.makeImmutable(r5);
        ByteCatchList r22 = ByteCatchList.EMPTY;
    L24:
        r0[r8] = new ByteBlock(r3, r3, r5, r4, r22);
        r8 = r8 + 1;
        goto L25
    L21:
        r22 = this.catchLists[r2];
        if (r22 != null) goto L24;
        r22 = ByteCatchList.EMPTY;
        goto L24
    L17:
        r2 = -1;
    L25:
        r3 = r5;
        goto L3
    L5:
        ByteBlockList r9 = new ByteBlockList(r8);
    L6:
        if (r1 >= r8) goto L8;
        r9.set(r1, r0[r1]);
        r1 = r1 + 1;
        goto L6
    L8:
        return r9;
    }

    public static ByteBlockList identifyBlocks(ConcreteMethod r1) {
        BasicBlocker r0 = new BasicBlocker(r1);
        r0.doit();
        return r0.getBlockList();
    }

    private void visitCommon(int r2, int r3, boolean r4) {
        Bits.set(this.liveSet, r2);
        if (r4 == false) goto L6;
        addWorkIfNecessary(r2 + r3, false);
        return;
    L6:
        Bits.set(this.blockSet, r2 + r3);
    }

    private void visitThrowing(int r3, int r4, boolean r5) {
        int r42 = r4 + r3;
        if (r5 == false) goto L5;
        addWorkIfNecessary(r42, true);
    L5:
        ByteCatchList r0 = this.method.getCatches().listFor(r3);
        this.catchLists[r3] = r0;
        IntList[] r2 = this.targetLists;
        if (r5 == true) goto L9;
        r42 = -1;
    L9:
        r2[r3] = r0.toTargetList(r42);
    }

    @Override // com.android.dx.cf.code.BytecodeArray.Visitor
    public int getPreviousOffset() {
        return this.previousOffset;
    }

    @Override // com.android.dx.cf.code.BytecodeArray.Visitor
    public void setPreviousOffset(int r1) {
        this.previousOffset = r1;
    }

    @Override // com.android.dx.cf.code.BytecodeArray.Visitor
    public void visitBranch(int r3, int r4, int r5, int r6) {
        if (r3 != 167) goto L5;
        visitCommon(r4, r5, false);
        this.targetLists[r4] = IntList.makeImmutable(r6);
    L10:
        addWorkIfNecessary(r6, true);
        return;
    L5:
        if (r3 != 168) goto L8;
        addWorkIfNecessary(r4, true);
    L8:
        int r32 = r4 + r5;
        visitCommon(r4, r5, true);
        addWorkIfNecessary(r32, true);
        this.targetLists[r4] = IntList.makeImmutable(r32, r6);
        goto L10
    }

    @Override // com.android.dx.cf.code.BytecodeArray.Visitor
    public void visitConstant(int r1, int r2, int r3, Constant r4, int r5) {
        visitCommon(r2, r3, true);
        if ((r4 instanceof CstMemberRef) == false) goto L5;
    L16:
        visitThrowing(r2, r3, true);
        return;
    L5:
        if ((r4 instanceof CstType) == true) goto L16;
        if ((r4 instanceof CstString) == true) goto L16;
        if ((r4 instanceof CstInvokeDynamic) == true) goto L16;
        if ((r4 instanceof CstMethodHandle) == true) goto L16;
        if ((r4 instanceof CstProtoRef) == true) goto L16;
    }

    @Override // com.android.dx.cf.code.BytecodeArray.Visitor
    public void visitInvalid(int r1, int r2, int r3) {
        visitCommon(r2, r3, true);
    }

    @Override // com.android.dx.cf.code.BytecodeArray.Visitor
    public void visitLocal(int r1, int r2, int r3, int r4, Type r5, int r6) {
        if (r1 != 169) goto L6;
        visitCommon(r2, r3, false);
        this.targetLists[r2] = IntList.EMPTY;
        return;
    L6:
        visitCommon(r2, r3, true);
    }

    @Override // com.android.dx.cf.code.BytecodeArray.Visitor
    public void visitNewarray(int r1, int r2, CstType r3, ArrayList<Constant> r4) {
        visitCommon(r1, r2, true);
        visitThrowing(r1, r2, true);
    }

    @Override // com.android.dx.cf.code.BytecodeArray.Visitor
    public void visitNoArgs(int r3, int r4, int r5, Type r6) {
        if (r3 != 108) goto L5;
    L28:
        visitCommon(r4, r5, true);
        if (r6 != Type.INT) goto L31;
    L34:
        visitThrowing(r4, r5, true);
        return;
    L31:
        if (r6 == Type.LONG) goto L34;
        return;
    L5:
        if (r3 == 112) goto L28;
        if (r3 != 172) goto L9;
    L26:
        visitCommon(r4, r5, false);
        this.targetLists[r4] = IntList.EMPTY;
        return;
    L9:
        if (r3 == 177) goto L26;
        if (r3 != 190) goto L13;
    L24:
        visitCommon(r4, r5, true);
        visitThrowing(r4, r5, true);
        return;
    L13:
        if (r3 != 191) goto L15;
        visitCommon(r4, r5, false);
        visitThrowing(r4, r5, false);
        return;
    L15:
        if (r3 == 194) goto L24;
        if (r3 == 195) goto L24;
        switch(r3) {
            case 46: goto L24;
            case 47: goto L24;
            case 48: goto L24;
            case 49: goto L24;
            case 50: goto L24;
            case 51: goto L24;
            case 52: goto L24;
            case 53: goto L24;
            default: goto L19;
        };
    L19:
        switch(r3) {
            case 79: goto L24;
            case 80: goto L24;
            case 81: goto L24;
            case 82: goto L24;
            case 83: goto L24;
            case 84: goto L24;
            case 85: goto L24;
            case 86: goto L24;
            default: goto L20;
        };
    L20:
        visitCommon(r4, r5, true);
    }

    @Override // com.android.dx.cf.code.BytecodeArray.Visitor
    public void visitSwitch(int r2, int r3, int r4, SwitchList r5, int r6) {
        int r22 = 0;
        visitCommon(r3, r4, false);
        addWorkIfNecessary(r5.getDefaultTarget(), true);
        int r42 = r5.size();
    L3:
        if (r22 >= r42) goto L5;
        addWorkIfNecessary(r5.getTarget(r22), true);
        r22 = r22 + 1;
        goto L3
    L5:
        this.targetLists[r3] = r5.getTargets();
    }
}
