package com.android.dx.ssa.back;

import com.android.dx.rop.code.BasicBlock;
import com.android.dx.rop.code.BasicBlockList;
import com.android.dx.rop.code.RopMethod;
import com.android.dx.util.IntList;
import java.util.BitSet;

/* loaded from: classes4.dex */
public class IdenticalBlockCombiner {
    private final BasicBlockList blocks;
    private final BasicBlockList newBlocks;
    private final RopMethod ropMethod;

    public IdenticalBlockCombiner(RopMethod r1) {
        this.ropMethod = r1;
        BasicBlockList r12 = r1.getBlocks();
        this.blocks = r12;
        this.newBlocks = r12.getMutableCopy();
    }

    private void combineBlocks(int r10, IntList r11) {
        int r0 = r11.size();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L8;
        int r3 = r11.get(r2);
        IntList r4 = this.ropMethod.labelToPredecessors(this.blocks.labelToBlock(r3).getLabel());
        int r5 = r4.size();
        int r6 = 0;
    L5:
        if (r6 >= r5) goto L7;
        replaceSucc(this.newBlocks.labelToBlock(r4.get(r6)), r3, r10);
        r6 = r6 + 1;
        goto L5
    L7:
        r2 = r2 + 1;
        goto L3
    }

    private static boolean compareInsns(BasicBlock r0, BasicBlock r1) {
        return r0.getInsns().contentEquals(r1.getInsns());
    }

    private void replaceSucc(BasicBlock r4, int r5, int r6) {
        IntList r0 = r4.getSuccessors().mutableCopy();
        r0.set(r0.indexOf(r5), r6);
        int r1 = r4.getPrimarySuccessor();
        if (r1 == r5) goto L6;
        r6 = r1;
    L6:
        r0.setImmutable();
        BasicBlock r52 = new BasicBlock(r4.getLabel(), r4.getInsns(), r0, r6);
        BasicBlockList r3 = this.newBlocks;
        r3.set(r3.indexOfLabel(r4.getLabel()), r52);
    }

    public RopMethod process() {
        int r0 = this.blocks.size();
        BitSet r1 = new BitSet(this.blocks.getMaxLabel());
        int r3 = 0;
    L4:
        if (r3 >= r0) goto L28;
        BasicBlock r5 = this.blocks.get(r3);
        if (r1.get(r5.getLabel()) == true) goto L27;
        IntList r52 = this.ropMethod.labelToPredecessors(r5.getLabel());
        int r6 = r52.size();
        int r7 = 0;
    L9:
        if (r7 >= r6) goto L27;
        int r8 = r52.get(r7);
        BasicBlock r9 = this.blocks.labelToBlock(r8);
        if (r1.get(r8) == true) goto L26;
        if (r9.getSuccessors().size() > 1) goto L26;
        if (r9.getFirstInsn().getOpcode().getOpcode() == 55) goto L26;
        IntList r10 = new IntList();
        int r11 = r7 + 1;
    L18:
        if (r11 >= r6) goto L25;
        int r12 = r52.get(r11);
        BasicBlock r13 = this.blocks.labelToBlock(r12);
        if (r13.getSuccessors().size() != 1) goto L24;
        if (compareInsns(r9, r13) == false) goto L24;
        r10.add(r12);
        r1.set(r12);
    L24:
        r11 = r11 + 1;
        goto L18
    L25:
        combineBlocks(r8, r10);
    L26:
        r7 = r7 + 1;
    L27:
        r3 = r3 + 1;
        goto L4
    L28:
        int r02 = r0 - 1;
    L29:
        BasicBlockList r2 = this.newBlocks;
        if (r02 < 0) goto L35;
        if (r1.get(r2.get(r02).getLabel()) == false) goto L34;
        this.newBlocks.set(r02, null);
    L34:
        r02 = r02 - 1;
        goto L29
    L35:
        r2.shrinkToFit();
        this.newBlocks.setImmutable();
        return new RopMethod(this.newBlocks, this.ropMethod.getFirstLabel());
    }
}
