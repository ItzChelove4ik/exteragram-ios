package com.android.dx.ssa;

import c.f$$ExternalSyntheticBUOutline1;
import com.android.dx.rop.code.BasicBlock;
import com.android.dx.rop.code.BasicBlockList;
import com.android.dx.rop.code.Insn;
import com.android.dx.rop.code.InsnList;
import com.android.dx.rop.code.PlainInsn;
import com.android.dx.rop.code.RegisterSpec;
import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.rop.code.Rop;
import com.android.dx.rop.code.RopMethod;
import com.android.dx.rop.code.Rops;
import com.android.dx.rop.code.SourcePosition;
import com.android.dx.ssa.PhiInsn;
import com.android.dx.ssa.SsaInsn;
import com.android.dx.util.Hex;
import com.android.dx.util.IntList;
import com.android.dx.util.IntSet;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.mvel2.util.StaticFieldStub$$ExternalSyntheticBUOutline0;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public final class SsaBasicBlock {
    public static final Comparator<SsaBasicBlock> LABEL_COMPARATOR = null;
    private final ArrayList<SsaBasicBlock> domChildren;
    private final int index;
    private final ArrayList<SsaInsn> insns;
    private IntSet liveIn;
    private IntSet liveOut;
    private int movesFromPhisAtBeginning;
    private int movesFromPhisAtEnd;
    private final SsaMethod parent;
    private BitSet predecessors;
    private int primarySuccessor;
    private final int ropLabel;
    private IntList successorList;
    private BitSet successors;

    public static final class LabelComparator implements Comparator<SsaBasicBlock> {
        public LabelComparator() {
        }

        /* renamed from: compare, reason: avoid collision after fix types in other method */
        public int compare2(SsaBasicBlock r1, SsaBasicBlock r2) {
            int r0 = SsaBasicBlock.access$000(r1);
            int r12 = SsaBasicBlock.access$000(r2);
            if (r0 >= r12) goto L6;
            return -1;
        L6:
            if (r0 <= r12) goto L9;
            return 1;
        L9:
            return 0;
        }

        @Override // java.util.Comparator
        public /* bridge */ /* synthetic */ int compare(SsaBasicBlock r1, SsaBasicBlock r2) {
            return compare2(r1, r2);
        }
    }

    public interface Visitor {
        void visitBlock(SsaBasicBlock r1, SsaBasicBlock r2);
    }

    static {
        LABEL_COMPARATOR = new LabelComparator();
    }

    public SsaBasicBlock(int r2, int r3, SsaMethod r4) {
        this.primarySuccessor = -1;
        this.movesFromPhisAtEnd = 0;
        this.movesFromPhisAtBeginning = 0;
        this.parent = r4;
        this.index = r2;
        this.insns = new ArrayList();
        this.ropLabel = r3;
        this.predecessors = new BitSet(r4.getBlocks().size());
        this.successors = new BitSet(r4.getBlocks().size());
        this.successorList = new IntList();
        this.domChildren = new ArrayList();
    }

    public static /* synthetic */ int access$000(SsaBasicBlock r0) {
        return r0.ropLabel;
    }

    private static boolean checkRegUsed(BitSet r3, RegisterSpec r4) {
        int r0 = r4.getReg();
        int r42 = r4.getCategory();
        if (r3.get(r0) == false) goto L5;
    L11:
        return true;
    L5:
        if (r42 == 2) goto L7;
        return false;
    L7:
        if (r3.get(r0 + 1) == true) goto L11;
        return false;
    }

    private int getCountPhiInsns() {
        int r0 = this.insns.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        if ((this.insns.get(r1) instanceof PhiInsn) == false) goto L8;
        r1 = r1 + 1;
    L8:
        return r1;
    }

    public static SsaBasicBlock newFromRop(RopMethod r7, int r8, SsaMethod r9) {
        BasicBlockList r0 = r7.getBlocks();
        BasicBlock r1 = r0.get(r8);
        SsaBasicBlock r2 = new SsaBasicBlock(r8, r1.getLabel(), r9);
        InsnList r82 = r1.getInsns();
        r2.insns.ensureCapacity(r82.size());
        int r92 = r82.size();
        int r3 = 0;
    L3:
        if (r3 >= r92) goto L5;
        r2.insns.add(new NormalSsaInsn(r82.get(r3), r2));
        r3 = r3 + 1;
        goto L3
    L5:
        r2.predecessors = SsaMethod.bitSetFromLabelList(r0, r7.labelToPredecessors(r1.getLabel()));
        r2.successors = SsaMethod.bitSetFromLabelList(r0, r1.getSuccessors());
        IntList r72 = SsaMethod.indexListFromLabelList(r0, r1.getSuccessors());
        r2.successorList = r72;
        if (r72.size() == 0) goto L12;
        int r73 = r1.getPrimarySuccessor();
        if (r73 >= 0) goto L10;
        int r74 = -1;
    L11:
        r2.primarySuccessor = r74;
        goto L12
    L10:
        r74 = r0.indexOfLabel(r73);
    L12:
        return r2;
    }

    private void scheduleUseBeforeAssigned(List<SsaInsn> r12) {
        BitSet r0 = new BitSet(this.parent.getRegCount());
        BitSet r1 = new BitSet(this.parent.getRegCount());
        int r2 = r12.size();
        int r4 = 0;
    L3:
        if (r4 >= r2) goto L26;
        int r5 = r4;
    L5:
        if (r5 >= r2) goto L7;
        setRegsUsed(r0, r12.get(r5).getSources().get(0));
        setRegsUsed(r1, r12.get(r5).getResult());
        r5 = r5 + 1;
        goto L5
    L7:
        int r52 = r4;
        int r6 = r52;
    L8:
        if (r52 >= r2) goto L13;
        if (checkRegUsed(r0, r12.get(r52).getResult()) == true) goto L12;
        Collections.swap(r12, r52, r6);
        r6 = r6 + 1;
    L12:
        r52 = r52 + 1;
        goto L8
    L13:
        if (r4 != r6) goto L24;
        int r42 = r6;
    L15:
        if (r42 >= r2) goto L22;
        SsaInsn r53 = r12.get(r42);
        if (checkRegUsed(r0, r53.getResult()) == false) goto L21;
        if (checkRegUsed(r1, r53.getSources().get(0)) == false) goto L21;
        Collections.swap(r12, r6, r42);
    L23:
        RegisterSpec r22 = r53.getResult();
        RegisterSpec r43 = r22.withReg(this.parent.borrowSpareRegister(r22.getCategory()));
        Rop r9 = Rops.opMove(r22.getType());
        SourcePosition r10 = SourcePosition.NO_INFO;
        NormalSsaInsn r7 = new NormalSsaInsn(new PlainInsn(r9, r10, r43, r53.getSources()), this);
        int r54 = r6 + 1;
        r12.add(r6, r7);
        r12.set(r54, new NormalSsaInsn(new PlainInsn(Rops.opMove(r22.getType()), r10, r22, RegisterSpecList.make(r43)), this));
        r2 = r12.size();
        r4 = r54;
    L25:
        r0.clear();
        r1.clear();
    L21:
        r42 = r42 + 1;
        goto L15
    L22:
        r53 = null;
        goto L23
    L24:
        r4 = r6;
        goto L25
    }

    private static void setRegsUsed(BitSet r2, RegisterSpec r3) {
        r2.set(r3.getReg());
        if (r3.getCategory() <= 1) goto L6;
        r2.set(r3.getReg() + 1);
        return;
    }

    public void addDomChild(SsaBasicBlock r1) {
        this.domChildren.add(r1);
    }

    public void addInsnToHead(Insn r3) {
        SsaInsn r32 = SsaInsn.makeFromRop(r3, this);
        this.insns.add(getCountPhiInsns(), r32);
        this.parent.onInsnAdded(r32);
    }

    public void addLiveIn(int r2) {
        if (this.liveIn != null) goto L5;
        this.liveIn = SetFactory.makeLivenessSet(this.parent.getRegCount());
    L5:
        this.liveIn.add(r2);
    }

    public void addLiveOut(int r2) {
        if (this.liveOut != null) goto L5;
        this.liveOut = SetFactory.makeLivenessSet(this.parent.getRegCount());
    L5:
        this.liveOut.add(r2);
    }

    public void addMoveToBeginning(RegisterSpec r5, RegisterSpec r6) {
        if (r5.getReg() != r6.getReg()) goto L5;
        return;
    L5:
        this.insns.add(getCountPhiInsns(), new NormalSsaInsn(new PlainInsn(Rops.opMove(r5.getType()), SourcePosition.NO_INFO, r5, RegisterSpecList.make(r6)), this));
        this.movesFromPhisAtBeginning++;
    }

    public void addMoveToEnd(RegisterSpec r6, RegisterSpec r7) {
        if (this.successors.cardinality() <= 1) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("Inserting a move to a block with multiple successors");
        return;
    L5:
        if (r6.getReg() == r7.getReg()) goto L17;
        ArrayList<SsaInsn> r0 = this.insns;
        NormalSsaInsn r02 = (NormalSsaInsn) r0.get(r0.size() - 1);
        if (r02.getResult() == null) goto L10;
    L14:
        int r03 = this.successors.nextSetBit(0);
    L15:
        if (r03 < 0) goto L21;
        this.parent.getBlocks().get(r03).addMoveToBeginning(r6, r7);
        r03 = this.successors.nextSetBit(r03 + 1);
        goto L15
    L21:
        return;
    L10:
        if (r02.getSources().size() > 0) goto L14;
        NormalSsaInsn r04 = new NormalSsaInsn(new PlainInsn(Rops.opMove(r6.getType()), SourcePosition.NO_INFO, r6, RegisterSpecList.make(r7)), this);
        ArrayList<SsaInsn> r62 = this.insns;
        r62.add(r62.size() - 1, r04);
        this.movesFromPhisAtEnd++;
        return;
    }

    public void addPhiInsnForReg(int r3) {
        this.insns.add(0, new PhiInsn(r3, this));
    }

    public void addPhiInsnForReg(RegisterSpec r3) {
        this.insns.add(0, new PhiInsn(r3, this));
    }

    public void exitBlockFixup(SsaBasicBlock r3) {
        if (this != r3) goto L5;
        return;
    L5:
        if (this.successorList.size() != 0) goto L8;
        this.successors.set(r3.index);
        this.successorList.add(r3.index);
        this.primarySuccessor = r3.index;
        r3.predecessors.set(this.index);
        return;
    }

    public void forEachInsn(SsaInsn.Visitor r4) {
        int r0 = this.insns.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        this.insns.get(r1).accept(r4);
        r1 = r1 + 1;
        goto L3
    }

    public void forEachPhiInsn(PhiInsn.Visitor r5) {
        int r0 = this.insns.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L7;
        SsaInsn r2 = this.insns.get(r1);
        if ((r2 instanceof PhiInsn) == false) goto L10;
        r5.visitPhiInsn((PhiInsn) r2);
        r1 = r1 + 1;
        goto L3
    L10:
        return;
    }

    public ArrayList<SsaBasicBlock> getDomChildren() {
        return this.domChildren;
    }

    public int getIndex() {
        return this.index;
    }

    public ArrayList<SsaInsn> getInsns() {
        return this.insns;
    }

    public IntSet getLiveInRegs() {
        if (this.liveIn != null) goto L6;
        this.liveIn = SetFactory.makeLivenessSet(this.parent.getRegCount());
    L6:
        return this.liveIn;
    }

    public IntSet getLiveOutRegs() {
        if (this.liveOut != null) goto L6;
        this.liveOut = SetFactory.makeLivenessSet(this.parent.getRegCount());
    L6:
        return this.liveOut;
    }

    public SsaMethod getParent() {
        return this.parent;
    }

    public List<SsaInsn> getPhiInsns() {
        return this.insns.subList(0, getCountPhiInsns());
    }

    public BitSet getPredecessors() {
        return this.predecessors;
    }

    public SsaBasicBlock getPrimarySuccessor() {
        if (this.primarySuccessor >= 0) goto L7;
        return null;
    L7:
        return this.parent.getBlocks().get(this.primarySuccessor);
    }

    public int getPrimarySuccessorIndex() {
        return this.primarySuccessor;
    }

    public int getPrimarySuccessorRopLabel() {
        return this.parent.blockIndexToRopLabel(this.primarySuccessor);
    }

    public int getRopLabel() {
        return this.ropLabel;
    }

    public String getRopLabelString() {
        return Hex.u2(this.ropLabel);
    }

    public IntList getRopLabelSuccessorList() {
        IntList r0 = new IntList(this.successorList.size());
        int r1 = this.successorList.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        r0.add(this.parent.blockIndexToRopLabel(this.successorList.get(r2)));
        r2 = r2 + 1;
        goto L3
    L5:
        return r0;
    }

    public IntList getSuccessorList() {
        return this.successorList;
    }

    public BitSet getSuccessors() {
        return this.successors;
    }

    public SsaBasicBlock insertNewPredecessor() {
        SsaBasicBlock r0 = this.parent.makeNewGotoBlock();
        r0.predecessors = this.predecessors;
        r0.successors.set(this.index);
        r0.successorList.add(this.index);
        r0.primarySuccessor = this.index;
        BitSet r1 = new BitSet(this.parent.getBlocks().size());
        this.predecessors = r1;
        r1.set(r0.index);
        int r12 = r0.predecessors.nextSetBit(0);
    L3:
        if (r12 < 0) goto L5;
        this.parent.getBlocks().get(r12).replaceSuccessor(this.index, r0.index);
        r12 = r0.predecessors.nextSetBit(r12 + 1);
        goto L3
    L5:
        return r0;
    }

    public SsaBasicBlock insertNewSuccessor(SsaBasicBlock r5) {
        SsaBasicBlock r0 = this.parent.makeNewGotoBlock();
        if (this.successors.get(r5.index) == false) goto L15;
        r0.predecessors.set(this.index);
        r0.successors.set(r5.index);
        r0.successorList.add(r5.index);
        r0.primarySuccessor = r5.index;
        int r1 = this.successorList.size() - 1;
    L5:
        if (r1 < 0) goto L10;
        if (this.successorList.get(r1) != r5.index) goto L9;
        this.successorList.set(r1, r0.index);
    L9:
        r1 = r1 - 1;
        goto L5
    L10:
        int r12 = this.primarySuccessor;
        int r2 = r5.index;
        if (r12 != r2) goto L13;
        this.primarySuccessor = r0.index;
    L13:
        this.successors.clear(r2);
        this.successors.set(r0.index);
        r5.predecessors.set(r0.index);
        r5.predecessors.set(this.index, this.successors.get(r5.index));
        return r0;
    L15:
        StaticFieldStub$$ExternalSyntheticBUOutline0.m("Block ", r5.getRopLabelString(), " not successor of ", getRopLabelString());
        return null;
    }

    public boolean isExitBlock() {
        if (this.index != this.parent.getExitBlockIndex()) goto L6;
        return true;
    L6:
        return false;
    }

    public void removeAllPhiInsns() {
        this.insns.subList(0, getCountPhiInsns()).clear();
    }

    public void removeSuccessor(int r4) {
        int r0 = this.successorList.size() - 1;
        int r1 = 0;
    L3:
        IntList r2 = this.successorList;
        if (r0 < 0) goto L10;
        if (r2.get(r0) != r4) goto L8;
        r1 = r0;
    L9:
        r0 = r0 - 1;
        goto L3
    L8:
        this.primarySuccessor = this.successorList.get(r0);
        goto L9
    L10:
        r2.removeIndex(r1);
        this.successors.clear(r4);
        this.parent.getBlocks().get(r4).predecessors.clear(this.index);
    }

    public void replaceLastInsn(Insn r5) {
        if (r5.getOpcode().getBranchingness() == 1) goto L6;
        ArrayList<SsaInsn> r0 = this.insns;
        SsaInsn r02 = r0.get(r0.size() - 1);
        SsaInsn r52 = SsaInsn.makeFromRop(r5, this);
        ArrayList<SsaInsn> r2 = this.insns;
        r2.set(r2.size() - 1, r52);
        this.parent.onInsnRemoved(r02);
        this.parent.onInsnAdded(r52);
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("last insn must branch");
    }

    public void replaceSuccessor(int r3, int r4) {
        if (r3 != r4) goto L4;
        return;
    L4:
        this.successors.set(r4);
        if (this.primarySuccessor != r3) goto L7;
        this.primarySuccessor = r4;
    L7:
        int r0 = this.successorList.size() - 1;
    L8:
        if (r0 < 0) goto L13;
        if (this.successorList.get(r0) != r3) goto L12;
        this.successorList.set(r0, r4);
    L12:
        r0 = r0 - 1;
        goto L8
    L13:
        this.successors.clear(r3);
        this.parent.getBlocks().get(r4).predecessors.set(this.index);
        this.parent.getBlocks().get(r3).predecessors.clear(this.index);
    }

    public void scheduleMovesFromPhis() {
        int r0 = this.movesFromPhisAtBeginning;
        if (r0 <= 1) goto L10;
        scheduleUseBeforeAssigned(this.insns.subList(0, r0));
        if (this.insns.get(this.movesFromPhisAtBeginning).isMoveException() == false) goto L10;
        GlShader$$ExternalSyntheticBUOutline1.m("Unexpected: moves from phis before move-exception");
        return;
    L10:
        if (this.movesFromPhisAtEnd <= 1) goto L12;
        ArrayList<SsaInsn> r02 = this.insns;
        scheduleUseBeforeAssigned(r02.subList((r02.size() - this.movesFromPhisAtEnd) - 1, this.insns.size() - 1));
    L12:
        this.parent.returnSpareRegisters();
    }

    public String toString() {
        return "{" + this.index + ":" + Hex.u2(this.ropLabel) + '}';
    }
}
