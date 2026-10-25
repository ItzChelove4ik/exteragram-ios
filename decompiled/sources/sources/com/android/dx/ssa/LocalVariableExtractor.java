package com.android.dx.ssa;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.code.RegisterSpec;
import com.android.dx.rop.code.RegisterSpecSet;
import com.android.dx.util.IntList;
import java.util.ArrayList;
import java.util.BitSet;

/* loaded from: classes4.dex */
public class LocalVariableExtractor {
    private final ArrayList<SsaBasicBlock> blocks;
    private final SsaMethod method;
    private final LocalVariableInfo resultInfo;
    private final BitSet workSet;

    private LocalVariableExtractor(SsaMethod r3) {
        if (r3 == null) goto L6;
        ArrayList<SsaBasicBlock> r0 = r3.getBlocks();
        this.method = r3;
        this.blocks = r0;
        this.resultInfo = new LocalVariableInfo(r3);
        this.workSet = new BitSet(r0.size());
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("method == null");
        throw null;
    }

    private LocalVariableInfo doit() {
        if (this.method.getRegCount() <= 0) goto L7;
        int r0 = this.method.getEntryBlockIndex();
    L5:
        if (r0 < 0) goto L7;
        this.workSet.clear(r0);
        processBlock(r0);
        r0 = this.workSet.nextSetBit(0);
    L7:
        this.resultInfo.setImmutable();
        return this.resultInfo;
    }

    public static LocalVariableInfo extract(SsaMethod r1) {
        return new LocalVariableExtractor(r1).doit();
    }

    private void processBlock(int r14) {
        RegisterSpecSet r0 = this.resultInfo.mutableCopyOfStarts(r14);
        SsaBasicBlock r1 = this.blocks.get(r14);
        ArrayList<SsaInsn> r2 = r1.getInsns();
        int r3 = r2.size();
        if (r14 == this.method.getExitBlockIndex()) goto L42;
        int r142 = r3 - 1;
        SsaInsn r4 = r2.get(r142);
        int r6 = 0;
        if (r4.getOriginalRopInsn().getCatches().size() != 0) goto L8;
    L10:
        boolean r42 = false;
    L11:
        RegisterSpecSet r7 = r0;
        int r5 = 0;
    L12:
        if (r5 >= r3) goto L32;
        if (r42 == false) goto L16;
        if (r5 != r142) goto L16;
        r7.setImmutable();
        r7 = r7.mutableCopy();
    L16:
        SsaInsn r8 = r2.get(r5);
        RegisterSpec r9 = r8.getLocalAssignment();
        if (r9 != null) goto L23;
        RegisterSpec r82 = r8.getResult();
        if (r82 == null) goto L31;
        if (r7.get(r82.getReg()) == null) goto L31;
        r7.remove(r7.get(r82.getReg()));
    L31:
        r5 = r5 + 1;
        goto L12
    L23:
        RegisterSpec r92 = r9.withSimpleType();
        if (r92.equals(r7.get(r92)) == true) goto L31;
        RegisterSpec r10 = r7.localItemToSpec(r92.getLocalItem());
        if (r10 != null) goto L28;
    L30:
        this.resultInfo.addAssignment(r8, r92);
        r7.put(r92);
        goto L31
    L28:
        if (r10.getReg() == r92.getReg()) goto L30;
        r7.remove(r10);
        goto L30
    L32:
        r7.setImmutable();
        IntList r143 = r1.getSuccessorList();
        int r22 = r143.size();
        int r12 = r1.getPrimarySuccessorIndex();
    L33:
        if (r6 >= r22) goto L52;
        int r32 = r143.get(r6);
        if (r32 != r12) goto L37;
        RegisterSpecSet r43 = r7;
    L39:
        if (this.resultInfo.mergeStarts(r32, r43) == false) goto L41;
        this.workSet.set(r32);
    L41:
        r6 = r6 + 1;
        goto L33
    L37:
        r43 = r0;
        goto L39
    L52:
        return;
    L8:
        if (r4.getResult() == null) goto L10;
        r42 = true;
        goto L11
    }
}
