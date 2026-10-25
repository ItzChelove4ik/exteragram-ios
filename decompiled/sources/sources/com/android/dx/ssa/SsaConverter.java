package com.android.dx.ssa;

import com.android.dx.rop.code.RegisterSpec;
import com.android.dx.rop.code.RopMethod;
import com.android.dx.ssa.DomFront;
import com.android.dx.util.IntIterator;
import java.util.ArrayList;
import java.util.BitSet;

/* loaded from: classes4.dex */
public class SsaConverter {
    public static final boolean DEBUG = false;

    public SsaConverter() {
    }

    public static SsaMethod convertToSsaMethod(RopMethod r0, int r1, boolean r2) {
        SsaMethod r02 = SsaMethod.newFromRopMethod(r0, r1, r2);
        edgeSplit(r02);
        placePhiFunctions(r02, LocalVariableExtractor.extract(r02), 0);
        new SsaRenamer(r02).run();
        r02.makeExitBlock();
        return r02;
    }

    private static void edgeSplit(SsaMethod r0) {
        edgeSplitPredecessors(r0);
        edgeSplitMoveExceptionsAndResults(r0);
        edgeSplitSuccessors(r0);
    }

    private static void edgeSplitMoveExceptionsAndResults(SsaMethod r8) {
        ArrayList<SsaBasicBlock> r82 = r8.getBlocks();
        int r0 = r82.size() - 1;
    L3:
        if (r0 < 0) goto L15;
        SsaBasicBlock r2 = r82.get(r0);
        if (r2.isExitBlock() == true) goto L14;
        if (r2.getPredecessors().cardinality() <= 1) goto L14;
        if (r2.getInsns().get(0).isMoveException() == false) goto L14;
        BitSet r3 = (BitSet) r2.getPredecessors().clone();
        int r5 = r3.nextSetBit(0);
    L11:
        if (r5 < 0) goto L13;
        r82.get(r5).insertNewSuccessor(r2).getInsns().add(0, r2.getInsns().get(0).clone());
        r5 = r3.nextSetBit(r5 + 1);
        goto L11
    L13:
        r2.getInsns().remove(0);
    L14:
        r0 = r0 - 1;
        goto L3
    }

    private static void edgeSplitPredecessors(SsaMethod r3) {
        ArrayList<SsaBasicBlock> r32 = r3.getBlocks();
        int r0 = r32.size() - 1;
    L3:
        if (r0 < 0) goto L8;
        SsaBasicBlock r1 = r32.get(r0);
        if (nodeNeedsUniquePredecessor(r1) == false) goto L7;
        r1.insertNewPredecessor();
    L7:
        r0 = r0 - 1;
        goto L3
    }

    private static void edgeSplitSuccessors(SsaMethod r6) {
        ArrayList<SsaBasicBlock> r62 = r6.getBlocks();
        int r0 = r62.size() - 1;
    L3:
        if (r0 < 0) goto L11;
        SsaBasicBlock r1 = r62.get(r0);
        BitSet r2 = (BitSet) r1.getSuccessors().clone();
        int r3 = r2.nextSetBit(0);
    L5:
        if (r3 < 0) goto L10;
        SsaBasicBlock r4 = r62.get(r3);
        if (needsNewSuccessor(r1, r4) == false) goto L9;
        r1.insertNewSuccessor(r4);
    L9:
        r3 = r2.nextSetBit(r3 + 1);
        goto L5
    L10:
        r0 = r0 - 1;
        goto L3
    }

    private static boolean needsNewSuccessor(SsaBasicBlock r3, SsaBasicBlock r4) {
        ArrayList<SsaInsn> r0 = r3.getInsns();
        SsaInsn r02 = r0.get(r0.size() - 1);
        if (r3.getSuccessors().cardinality() <= 1) goto L8;
        if (r4.getPredecessors().cardinality() <= 1) goto L8;
        return true;
    L8:
        if (r02.getResult() != null) goto L12;
        if (r02.getSources().size() > 0) goto L12;
        return false;
    L12:
        if (r4.getPredecessors().cardinality() <= 1) goto L16;
        return true;
    L16:
        return false;
    }

    private static boolean nodeNeedsUniquePredecessor(SsaBasicBlock r2) {
        int r0 = r2.getPredecessors().cardinality();
        int r22 = r2.getSuccessors().cardinality();
        if (r0 <= 1) goto L6;
        if (r22 <= 1) goto L8;
        return true;
    L8:
        return false;
    L6:
        return false;
    }

    private static void placePhiFunctions(SsaMethod r12, LocalVariableInfo r13, int r14) {
        ArrayList<SsaBasicBlock> r0 = r12.getBlocks();
        int r1 = r0.size();
        int r2 = r12.getRegCount() - r14;
        DomFront.DomInfo[] r122 = new DomFront(r12).run();
        BitSet[] r3 = new BitSet[r2];
        BitSet[] r4 = new BitSet[r2];
        int r6 = 0;
    L3:
        if (r6 >= r2) goto L5;
        r3[r6] = new BitSet(r1);
        r4[r6] = new BitSet(r1);
        r6 = r6 + 1;
        goto L3
    L5:
        int r15 = r0.size();
        int r62 = 0;
    L6:
        if (r62 >= r15) goto L15;
        ArrayList<SsaInsn> r7 = r0.get(r62).getInsns();
        int r8 = r7.size();
        int r9 = 0;
    L8:
        if (r9 >= r8) goto L14;
        SsaInsn r10 = r7.get(r9);
        r9 = r9 + 1;
        RegisterSpec r102 = r10.getResult();
        if (r102 == null) goto L8;
        if ((r102.getReg() - r14) < 0) goto L8;
        r3[r102.getReg() - r14].set(r62);
        goto L8
    L14:
        r62 = r62 + 1;
        goto L6
    L15:
        int r16 = 0;
    L16:
        if (r16 >= r2) goto L33;
        BitSet r63 = (BitSet) r3[r16].clone();
    L18:
        int r72 = r63.nextSetBit(0);
        if (r72 < 0) goto L32;
        r63.clear(r72);
        IntIterator r73 = r122[r72].dominanceFrontiers.iterator();
    L22:
        if (r73.hasNext() == false) goto L18;
        int r82 = r73.next();
        if (r4[r16].get(r82) == true) goto L22;
        r4[r16].set(r82);
        int r92 = r16 + r14;
        RegisterSpec r103 = r13.getStarts(r82).get(r92);
        if (r103 != null) goto L28;
        r0.get(r82).addPhiInsnForReg(r92);
    L30:
        if (r3[r16].get(r82) == true) goto L22;
        r63.set(r82);
        goto L22
    L28:
        r0.get(r82).addPhiInsnForReg(r103);
        goto L30
    L32:
        r16 = r16 + 1;
        goto L16
    }

    public static SsaMethod testEdgeSplit(RopMethod r0, int r1, boolean r2) {
        SsaMethod r02 = SsaMethod.newFromRopMethod(r0, r1, r2);
        edgeSplit(r02);
        return r02;
    }

    public static SsaMethod testPhiPlacement(RopMethod r0, int r1, boolean r2) {
        SsaMethod r02 = SsaMethod.newFromRopMethod(r0, r1, r2);
        edgeSplit(r02);
        placePhiFunctions(r02, LocalVariableExtractor.extract(r02), 0);
        return r02;
    }

    public static void updateSsaMethod(SsaMethod r1, int r2) {
        placePhiFunctions(r1, LocalVariableExtractor.extract(r1), r2);
        new SsaRenamer(r1, r2).run();
    }
}
