package com.android.dx.rop.code;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.util.Bits;
import com.android.dx.util.IntList;

/* loaded from: classes4.dex */
public final class LocalVariableExtractor {
    private final BasicBlockList blocks;
    private final RopMethod method;
    private final LocalVariableInfo resultInfo;
    private final int[] workSet;

    private LocalVariableExtractor(RopMethod r3) {
        if (r3 == null) goto L6;
        BasicBlockList r0 = r3.getBlocks();
        int r1 = r0.getMaxLabel();
        this.method = r3;
        this.blocks = r0;
        this.resultInfo = new LocalVariableInfo(r3);
        this.workSet = Bits.makeBitSet(r1);
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("method == null");
        throw null;
    }

    private LocalVariableInfo doit() {
        int r0 = this.method.getFirstLabel();
    L3:
        if (r0 < 0) goto L5;
        Bits.clear(this.workSet, r0);
        processBlock(r0);
        r0 = Bits.findFirst(this.workSet, 0);
        goto L3
    L5:
        this.resultInfo.setImmutable();
        return this.resultInfo;
    }

    public static LocalVariableInfo extract(RopMethod r1) {
        return new LocalVariableExtractor(r1).doit();
    }

    private void processBlock(int r14) {
        RegisterSpecSet r0 = this.resultInfo.mutableCopyOfStarts(r14);
        BasicBlock r142 = this.blocks.labelToBlock(r14);
        InsnList r1 = r142.getInsns();
        int r2 = r1.size();
        int r4 = 0;
        if (r142.hasExceptionHandlers() == true) goto L5;
    L7:
        boolean r3 = false;
    L8:
        int r5 = r2 - 1;
        RegisterSpecSet r7 = r0;
        int r6 = 0;
    L9:
        if (r6 >= r2) goto L29;
        if (r3 == false) goto L13;
        if (r6 != r5) goto L13;
        r7.setImmutable();
        r7 = r7.mutableCopy();
    L13:
        Insn r8 = r1.get(r6);
        RegisterSpec r9 = r8.getLocalAssignment();
        if (r9 != null) goto L20;
        RegisterSpec r82 = r8.getResult();
        if (r82 == null) goto L28;
        if (r7.get(r82.getReg()) == null) goto L28;
        r7.remove(r7.get(r82.getReg()));
    L28:
        r6 = r6 + 1;
        goto L9
    L20:
        RegisterSpec r92 = r9.withSimpleType();
        if (r92.equals(r7.get(r92)) == true) goto L28;
        RegisterSpec r10 = r7.localItemToSpec(r92.getLocalItem());
        if (r10 != null) goto L25;
    L27:
        this.resultInfo.addAssignment(r8, r92);
        r7.put(r92);
        goto L28
    L25:
        if (r10.getReg() == r92.getReg()) goto L27;
        r7.remove(r10);
        goto L27
    L29:
        r7.setImmutable();
        IntList r12 = r142.getSuccessors();
        int r22 = r12.size();
        int r143 = r142.getPrimarySuccessor();
    L30:
        if (r4 >= r22) goto L39;
        int r32 = r12.get(r4);
        if (r32 != r143) goto L34;
        RegisterSpecSet r52 = r7;
    L36:
        if (this.resultInfo.mergeStarts(r32, r52) == false) goto L38;
        Bits.set(this.workSet, r32);
    L38:
        r4 = r4 + 1;
        goto L30
    L34:
        r52 = r0;
        goto L36
    L39:
        return;
    L5:
        if (r1.getLast().getResult() == null) goto L7;
        r3 = true;
        goto L8
    }
}
