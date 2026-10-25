package com.android.dx.ssa;

import com.android.dx.rop.code.RegisterSpec;
import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.ssa.SsaInsn;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashSet;

/* loaded from: classes4.dex */
public class DeadCodeRemover {
    private final int regCount;
    private final SsaMethod ssaMeth;
    private final ArrayList<SsaInsn>[] useList;
    private final BitSet worklist;

    public static class NoSideEffectVisitor implements SsaInsn.Visitor {
        BitSet noSideEffectRegs;

        public NoSideEffectVisitor(BitSet r1) {
            this.noSideEffectRegs = r1;
        }

        @Override // com.android.dx.ssa.SsaInsn.Visitor
        public void visitMoveInsn(NormalSsaInsn r2) {
            if (DeadCodeRemover.access$000(r2) == true) goto L6;
            this.noSideEffectRegs.set(r2.getResult().getReg());
            return;
        }

        @Override // com.android.dx.ssa.SsaInsn.Visitor
        public void visitNonMoveInsn(NormalSsaInsn r2) {
            RegisterSpec r0 = r2.getResult();
            if (DeadCodeRemover.access$000(r2) == true) goto L7;
            if (r0 == null) goto L8;
            this.noSideEffectRegs.set(r0.getReg());
            return;
        L8:
            return;
        }

        @Override // com.android.dx.ssa.SsaInsn.Visitor
        public void visitPhiInsn(PhiInsn r2) {
            if (DeadCodeRemover.access$000(r2) == true) goto L6;
            this.noSideEffectRegs.set(r2.getResult().getReg());
            return;
        }
    }

    private DeadCodeRemover(SsaMethod r3) {
        this.ssaMeth = r3;
        int r0 = r3.getRegCount();
        this.regCount = r0;
        this.worklist = new BitSet(r0);
        this.useList = r3.getUseListCopy();
    }

    public static /* synthetic */ boolean access$000(SsaInsn r0) {
        return hasSideEffect(r0);
    }

    private static boolean hasSideEffect(SsaInsn r0) {
        if (r0 != null) goto L6;
        return true;
    L6:
        return r0.hasSideEffect();
    }

    private boolean isCircularNoSideEffect(int r7, BitSet r8) {
        if (r8 != null) goto L5;
    L7:
        ArrayList<SsaInsn> r1 = this.useList[r7];
        int r2 = r1.size();
        int r4 = 0;
    L8:
        if (r4 >= r2) goto L12;
        SsaInsn r5 = r1.get(r4);
        r4 = r4 + 1;
        if (hasSideEffect(r5) == false) goto L8;
        return false;
    L12:
        if (r8 != null) goto L14;
        r8 = new BitSet(this.regCount);
    L14:
        r8.set(r7);
        ArrayList<SsaInsn> r72 = this.useList[r7];
        int r12 = r72.size();
        int r22 = 0;
    L15:
        if (r22 >= r12) goto L21;
        SsaInsn r42 = r72.get(r22);
        r22 = r22 + 1;
        RegisterSpec r43 = r42.getResult();
        if (r43 == null) goto L20;
        if (isCircularNoSideEffect(r43.getReg(), r8) == true) goto L15;
    L20:
        return false;
    L21:
        return true;
    L5:
        if (r8.get(r7) == false) goto L7;
        return true;
    }

    public static void process(SsaMethod r1) {
        new DeadCodeRemover(r1).run();
    }

    private void pruneDeadInstructions() {
        HashSet r0 = new HashSet();
        BitSet r1 = this.ssaMeth.computeReachability();
        ArrayList<SsaBasicBlock> r2 = this.ssaMeth.getBlocks();
        int r4 = 0;
    L3:
        int r42 = r1.nextClearBit(r4);
        if (r42 >= r2.size()) goto L23;
        SsaBasicBlock r5 = r2.get(r42);
        r4 = r42 + 1;
        int r6 = 0;
    L7:
        if (r6 >= r5.getInsns().size()) goto L3;
        SsaInsn r7 = r5.getInsns().get(r6);
        RegisterSpecList r8 = r7.getSources();
        int r9 = r8.size();
        if (r9 == 0) goto L11;
        r0.add(r7);
    L11:
        int r10 = 0;
    L12:
        if (r10 >= r9) goto L14;
        this.useList[r8.get(r10).getReg()].remove(r7);
        r10 = r10 + 1;
        goto L12
    L14:
        RegisterSpec r72 = r7.getResult();
        if (r72 == null) goto L22;
        ArrayList<SsaInsn> r82 = this.useList[r72.getReg()];
        int r92 = r82.size();
        int r102 = 0;
    L18:
        if (r102 >= r92) goto L22;
        SsaInsn r11 = r82.get(r102);
        r102 = r102 + 1;
        SsaInsn r112 = r11;
        if ((r112 instanceof PhiInsn) == false) goto L18;
        ((PhiInsn) r112).removePhiRegister(r72);
    L22:
        r6 = r6 + 1;
        goto L7
    L23:
        this.ssaMeth.deleteInsns(r0);
    }

    private void run() {
        pruneDeadInstructions();
        HashSet r0 = new HashSet();
        this.ssaMeth.forEachInsn(new NoSideEffectVisitor(this.worklist));
    L3:
        int r2 = 0;
        int r1 = this.worklist.nextSetBit(0);
        if (r1 < 0) goto L19;
        this.worklist.clear(r1);
        if (this.useList[r1].size() == 0) goto L9;
        if (isCircularNoSideEffect(r1, null) == false) goto L3;
    L9:
        SsaInsn r12 = this.ssaMeth.getDefinitionForRegister(r1);
        if (r0.contains(r12) == true) goto L3;
        RegisterSpecList r3 = r12.getSources();
        int r4 = r3.size();
    L13:
        if (r2 >= r4) goto L18;
        RegisterSpec r5 = r3.get(r2);
        this.useList[r5.getReg()].remove(r12);
        if (hasSideEffect(this.ssaMeth.getDefinitionForRegister(r5.getReg())) == true) goto L17;
        this.worklist.set(r5.getReg());
    L17:
        r2 = r2 + 1;
        goto L13
    L18:
        r0.add(r12);
        goto L3
    L19:
        this.ssaMeth.deleteInsns(r0);
    }
}
