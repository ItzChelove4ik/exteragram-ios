package com.android.dx.ssa.back;

import com.android.dx.rop.code.BasicBlock;
import com.android.dx.rop.code.BasicBlockList;
import com.android.dx.rop.code.InsnList;
import com.android.dx.rop.code.RegisterSpec;
import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.rop.code.Rop;
import com.android.dx.rop.code.RopMethod;
import com.android.dx.rop.code.Rops;
import com.android.dx.ssa.BasicRegisterMapper;
import com.android.dx.ssa.PhiInsn;
import com.android.dx.ssa.RegisterMapper;
import com.android.dx.ssa.SsaBasicBlock;
import com.android.dx.ssa.SsaInsn;
import com.android.dx.ssa.SsaMethod;
import com.android.dx.util.Hex;
import com.android.dx.util.IntList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Comparator;
import org.mvel2.ast.Instance$$ExternalSyntheticBUOutline0;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public class SsaToRop {
    private static final boolean DEBUG = false;
    private final InterferenceGraph interference;
    private final boolean minimizeRegisters;
    private final SsaMethod ssaMeth;

    /* renamed from: com.android.dx.ssa.back.SsaToRop$1, reason: invalid class name */
    public class AnonymousClass1 implements SsaBasicBlock.Visitor {
        final /* synthetic */ SsaToRop this$0;
        final /* synthetic */ ArrayList val$blocks;

        public AnonymousClass1(SsaToRop r1, ArrayList r2) {
            this.this$0 = r1;
            this.val$blocks = r2;
        }

        @Override // com.android.dx.ssa.SsaBasicBlock.Visitor
        public void visitBlock(SsaBasicBlock r5, SsaBasicBlock r6) {
            ArrayList<SsaInsn> r62 = r5.getInsns();
            if (r62.size() == 1) goto L5;
            return;
        L5:
            if (r62.get(0).getOpcode() != Rops.GOTO) goto L11;
            BitSet r63 = (BitSet) r5.getPredecessors().clone();
            int r0 = r63.nextSetBit(0);
        L7:
            if (r0 < 0) goto L12;
            ((SsaBasicBlock) this.val$blocks.get(r0)).replaceSuccessor(r5.getIndex(), r5.getPrimarySuccessorIndex());
            r0 = r63.nextSetBit(r0 + 1);
            goto L7
        L12:
            return;
        }
    }

    /* renamed from: com.android.dx.ssa.back.SsaToRop$2, reason: invalid class name */
    public class AnonymousClass2 implements Comparator<Integer> {
        final /* synthetic */ SsaToRop this$0;

        public AnonymousClass2(SsaToRop r1) {
            this.this$0 = r1;
        }

        /* renamed from: compare, reason: avoid collision after fix types in other method */
        public int compare2(Integer r2, Integer r3) {
            return SsaToRop.access$000(this.this$0).getUseListForRegister(r3.intValue()).size() - SsaToRop.access$000(this.this$0).getUseListForRegister(r2.intValue()).size();
        }

        @Override // java.util.Comparator
        public /* bridge */ /* synthetic */ int compare(Integer r1, Integer r2) {
            return compare2(r1, r2);
        }
    }

    public static class PhiVisitor implements PhiInsn.Visitor {
        private final ArrayList<SsaBasicBlock> blocks;

        public PhiVisitor(ArrayList<SsaBasicBlock> r1) {
            this.blocks = r1;
        }

        @Override // com.android.dx.ssa.PhiInsn.Visitor
        public void visitPhiInsn(PhiInsn r8) {
            RegisterSpecList r0 = r8.getSources();
            RegisterSpec r1 = r8.getResult();
            int r2 = r0.size();
            int r3 = 0;
        L3:
            if (r3 >= r2) goto L5;
            RegisterSpec r4 = r0.get(r3);
            this.blocks.get(r8.predBlockIndexForSourcesIndex(r3)).addMoveToEnd(r1, r4);
            r3 = r3 + 1;
            goto L3
        }
    }

    private SsaToRop(SsaMethod r1, boolean r2) {
        this.minimizeRegisters = r2;
        this.ssaMeth = r1;
        this.interference = LivenessAnalyzer.constructInterferenceGraph(r1);
    }

    public static /* synthetic */ SsaMethod access$000(SsaToRop r0) {
        return r0.ssaMeth;
    }

    private RopMethod convert() {
        FirstFitLocalCombiningAllocator r0 = new FirstFitLocalCombiningAllocator(this.ssaMeth, this.interference, this.minimizeRegisters);
        RegisterMapper r1 = r0.allocateRegisters();
        this.ssaMeth.setBackMode();
        this.ssaMeth.mapRegisters(r1);
        removePhiFunctions();
        if (r0.wantsParamsMovedHigh() == false) goto L5;
        moveParametersToHighRegisters();
    L5:
        removeEmptyGotos();
        BasicBlockList r12 = convertBasicBlocks();
        SsaMethod r4 = this.ssaMeth;
        return new IdenticalBlockCombiner(new RopMethod(r12, r4.blockIndexToRopLabel(r4.getEntryBlockIndex()))).process();
    }

    private BasicBlock convertBasicBlock(SsaBasicBlock r5) {
        IntList r0 = r5.getRopLabelSuccessorList();
        int r1 = r5.getPrimarySuccessorRopLabel();
        SsaBasicBlock r2 = this.ssaMeth.getExitBlock();
        if (r2 != null) goto L5;
        int r22 = -1;
    L7:
        if (r0.contains(r22) == true) goto L9;
    L13:
        r0.setImmutable();
        return new BasicBlock(r5.getRopLabel(), convertInsns(r5.getInsns()), r0, r1);
    L9:
        if (r0.size() > 1) goto L11;
        r0 = IntList.EMPTY;
        verifyValidExitPredecessor(r5);
        r1 = -1;
        goto L13
    L11:
        Instance$$ExternalSyntheticBUOutline0.m("Exit predecessor must have no other successors", Hex.u2(r5.getRopLabel()));
        return null;
    L5:
        r22 = r2.getRopLabel();
        goto L7
    }

    private BasicBlockList convertBasicBlocks() {
        ArrayList<SsaBasicBlock> r0 = this.ssaMeth.getBlocks();
        SsaBasicBlock r1 = this.ssaMeth.getExitBlock();
        BitSet r2 = this.ssaMeth.computeReachability();
        int r3 = r2.cardinality();
        if (r1 != null) goto L5;
    L7:
        BasicBlockList r4 = new BasicBlockList(r3);
        int r32 = r0.size();
        int r5 = 0;
        int r6 = 0;
    L8:
        if (r6 >= r32) goto L13;
        SsaBasicBlock r7 = r0.get(r6);
        r6 = r6 + 1;
        SsaBasicBlock r72 = r7;
        if (r2.get(r72.getIndex()) == false) goto L8;
        if (r72 == r1) goto L8;
        r4.set(r5, convertBasicBlock(r72));
        r5 = r5 + 1;
        goto L8
    L13:
        if (r1 != null) goto L15;
    L19:
        return r4;
    L15:
        if (r1.getInsns().isEmpty() == true) goto L19;
        GlShader$$ExternalSyntheticBUOutline1.m("Exit block must have no insns when leaving SSA form");
        return null;
    L5:
        if (r2.get(r1.getIndex()) == false) goto L7;
        r3 = r3 - 1;
        goto L7
    }

    private InsnList convertInsns(ArrayList<SsaInsn> r4) {
        int r3 = r4.size();
        InsnList r0 = new InsnList(r3);
        int r1 = 0;
    L3:
        if (r1 >= r3) goto L5;
        r0.set(r1, r4.get(r1).toRopInsn());
        r1 = r1 + 1;
        goto L3
    L5:
        r0.setImmutable();
        return r0;
    }

    public static RopMethod convertToRopMethod(SsaMethod r1, boolean r2) {
        return new SsaToRop(r1, r2).convert();
    }

    private void moveParametersToHighRegisters() {
        int r0 = this.ssaMeth.getParamWidth();
        BasicRegisterMapper r1 = new BasicRegisterMapper(this.ssaMeth.getRegCount());
        int r2 = this.ssaMeth.getRegCount();
        int r3 = 0;
    L3:
        if (r3 >= r2) goto L9;
        if (r3 >= r0) goto L7;
        r1.addMapping(r3, (r2 - r0) + r3, 1);
    L8:
        r3 = r3 + 1;
        goto L3
    L7:
        r1.addMapping(r3, r3 - r0, 1);
        goto L8
    L9:
        this.ssaMeth.mapRegisters(r1);
    }

    private void removeEmptyGotos() {
        ArrayList<SsaBasicBlock> r0 = this.ssaMeth.getBlocks();
        this.ssaMeth.forEachBlockDepthFirst(false, new AnonymousClass1(this, r0));
    }

    private void removePhiFunctions() {
        ArrayList<SsaBasicBlock> r5 = this.ssaMeth.getBlocks();
        int r0 = r5.size();
        int r1 = 0;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L5;
        SsaBasicBlock r3 = r5.get(r2);
        r2 = r2 + 1;
        SsaBasicBlock r32 = r3;
        r32.forEachPhiInsn(new PhiVisitor(r5));
        r32.removeAllPhiInsns();
        goto L3
    L5:
        int r02 = r5.size();
    L6:
        if (r1 >= r02) goto L8;
        SsaBasicBlock r22 = r5.get(r1);
        r1 = r1 + 1;
        r22.scheduleMovesFromPhis();
        goto L6
    }

    private void verifyValidExitPredecessor(SsaBasicBlock r2) {
        Rop r1 = r2.getInsns().get(r1.size() - 1).getOpcode();
        if (r1.getBranchingness() != 2) goto L5;
        return;
    L5:
        if (r1 == Rops.THROW) goto L10;
        GlShader$$ExternalSyntheticBUOutline1.m("Exit predecessor must end in valid exit statement.");
        return;
    }

    public int[] getRegistersByFrequency() {
        int r0 = this.ssaMeth.getRegCount();
        Integer[] r1 = new Integer[r0];
        int r2 = 0;
        int r3 = 0;
    L3:
        if (r3 >= r0) goto L5;
        r1[r3] = Integer.valueOf(r3);
        r3 = r3 + 1;
        goto L3
    L5:
        Arrays.sort(r1, new AnonymousClass2(this));
        int[] r5 = new int[r0];
    L6:
        if (r2 >= r0) goto L8;
        r5[r2] = r1[r2].intValue();
        r2 = r2 + 1;
        goto L6
    L8:
        return r5;
    }
}
