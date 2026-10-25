package com.android.dx.ssa;

import com.android.dx.rop.code.BasicBlockList;
import com.android.dx.rop.code.Insn;
import com.android.dx.rop.code.PlainInsn;
import com.android.dx.rop.code.RegisterSpec;
import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.rop.code.RopMethod;
import com.android.dx.rop.code.Rops;
import com.android.dx.rop.code.SourcePosition;
import com.android.dx.ssa.PhiInsn;
import com.android.dx.ssa.SsaBasicBlock;
import com.android.dx.ssa.SsaInsn;
import com.android.dx.util.IntList;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public final class SsaMethod {
    private boolean backMode;
    private ArrayList<SsaBasicBlock> blocks;
    private int borrowedSpareRegisters;
    private SsaInsn[] definitionList;
    private int entryBlockIndex;
    private int exitBlockIndex;
    private final boolean isStatic;
    private int maxLabel;
    private final int paramWidth;
    private int registerCount;
    private int spareRegisterBase;
    private List<SsaInsn>[] unmodifiableUseList;
    private ArrayList<SsaInsn>[] useList;

    /* renamed from: com.android.dx.ssa.SsaMethod$1, reason: invalid class name */
    public class AnonymousClass1 implements SsaInsn.Visitor {
        final /* synthetic */ SsaMethod this$0;

        public AnonymousClass1(SsaMethod r1) {
            this.this$0 = r1;
        }

        @Override // com.android.dx.ssa.SsaInsn.Visitor
        public void visitMoveInsn(NormalSsaInsn r2) {
            SsaMethod.access$000(this.this$0)[r2.getResult().getReg()] = r2;
        }

        @Override // com.android.dx.ssa.SsaInsn.Visitor
        public void visitNonMoveInsn(NormalSsaInsn r2) {
            if (r2.getResult() == null) goto L6;
            SsaMethod.access$000(this.this$0)[r2.getResult().getReg()] = r2;
            return;
        }

        @Override // com.android.dx.ssa.SsaInsn.Visitor
        public void visitPhiInsn(PhiInsn r2) {
            SsaMethod.access$000(this.this$0)[r2.getResult().getReg()] = r2;
        }
    }

    /* renamed from: com.android.dx.ssa.SsaMethod$2, reason: invalid class name */
    public class AnonymousClass2 implements SsaInsn.Visitor {
        final /* synthetic */ SsaMethod this$0;

        public AnonymousClass2(SsaMethod r1) {
            this.this$0 = r1;
        }

        private void addToUses(SsaInsn r6) {
            RegisterSpecList r0 = r6.getSources();
            int r1 = r0.size();
            int r2 = 0;
        L3:
            if (r2 >= r1) goto L5;
            SsaMethod.access$100(this.this$0)[r0.get(r2).getReg()].add(r6);
            r2 = r2 + 1;
            goto L3
        }

        @Override // com.android.dx.ssa.SsaInsn.Visitor
        public void visitMoveInsn(NormalSsaInsn r1) {
            addToUses(r1);
        }

        @Override // com.android.dx.ssa.SsaInsn.Visitor
        public void visitNonMoveInsn(NormalSsaInsn r1) {
            addToUses(r1);
        }

        @Override // com.android.dx.ssa.SsaInsn.Visitor
        public void visitPhiInsn(PhiInsn r1) {
            addToUses(r1);
        }
    }

    private SsaMethod(RopMethod r1, int r2, boolean r3) {
        this.paramWidth = r2;
        this.isStatic = r3;
        this.backMode = false;
        this.maxLabel = r1.getBlocks().getMaxLabel();
        int r12 = r1.getBlocks().getRegCount();
        this.registerCount = r12;
        this.spareRegisterBase = r12;
    }

    public static /* synthetic */ SsaInsn[] access$000(SsaMethod r0) {
        return r0.definitionList;
    }

    public static /* synthetic */ ArrayList[] access$100(SsaMethod r0) {
        return r0.useList;
    }

    public static BitSet bitSetFromLabelList(BasicBlockList r4, IntList r5) {
        BitSet r0 = new BitSet(r4.size());
        int r1 = r5.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        r0.set(r4.indexOfLabel(r5.get(r2)));
        r2 = r2 + 1;
        goto L3
    L5:
        return r0;
    }

    private void buildUseList() {
        if (this.backMode == true) goto L13;
        this.useList = new ArrayList[this.registerCount];
        int r0 = 0;
        int r1 = 0;
    L6:
        if (r1 >= this.registerCount) goto L8;
        this.useList[r1] = new ArrayList();
        r1 = r1 + 1;
        goto L6
    L8:
        forEachInsn(new AnonymousClass2(this));
        this.unmodifiableUseList = new List[this.registerCount];
    L10:
        if (r0 >= this.registerCount) goto L12;
        this.unmodifiableUseList[r0] = Collections.unmodifiableList(this.useList[r0]);
        r0 = r0 + 1;
        goto L10
    L12:
        return;
    L13:
        GlShader$$ExternalSyntheticBUOutline1.m("No use list in back mode");
    }

    private void convertRopToSsaBlocks(RopMethod r5) {
        int r0 = r5.getBlocks().size();
        this.blocks = new ArrayList(r0 + 2);
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        SsaBasicBlock r2 = SsaBasicBlock.newFromRop(r5, r1, this);
        this.blocks.add(r2);
        r1 = r1 + 1;
        goto L3
    L5:
        int r52 = r5.getBlocks().indexOfLabel(r5.getFirstLabel());
        this.entryBlockIndex = this.blocks.get(r52).insertNewPredecessor().getIndex();
        this.exitBlockIndex = -1;
    }

    private static SsaInsn getGoto(SsaBasicBlock r6) {
        return new NormalSsaInsn(new PlainInsn(Rops.GOTO, SourcePosition.NO_INFO, null, RegisterSpecList.EMPTY), r6);
    }

    public static IntList indexListFromLabelList(BasicBlockList r4, IntList r5) {
        IntList r0 = new IntList(r5.size());
        int r1 = r5.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        r0.add(r4.indexOfLabel(r5.get(r2)));
        r2 = r2 + 1;
        goto L3
    L5:
        return r0;
    }

    public static SsaMethod newFromRopMethod(RopMethod r1, int r2, boolean r3) {
        SsaMethod r0 = new SsaMethod(r1, r2, r3);
        r0.convertRopToSsaBlocks(r1);
        return r0;
    }

    private void removeFromUseList(SsaInsn r5, RegisterSpecList r6) {
        if (r6 == null) goto L13;
        int r0 = r6.size();
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L14;
        if (this.useList[r6.get(r1).getReg()].remove(r5) == false) goto L9;
        r1 = r1 + 1;
        goto L5
    L9:
        GlShader$$ExternalSyntheticBUOutline1.m("use not found");
        return;
    L14:
        return;
    }

    public int blockIndexToRopLabel(int r1) {
        if (r1 >= 0) goto L6;
        return -1;
    L6:
        return this.blocks.get(r1).getRopLabel();
    }

    public int borrowSpareRegister(int r3) {
        int r0 = this.spareRegisterBase;
        int r1 = this.borrowedSpareRegisters;
        int r02 = r0 + r1;
        this.borrowedSpareRegisters = r1 + r3;
        this.registerCount = Math.max(this.registerCount, r3 + r02);
        return r02;
    }

    public BitSet computeReachability() {
        int r0 = this.blocks.size();
        BitSet r1 = new BitSet(r0);
        BitSet r2 = new BitSet(r0);
        r1.set(getEntryBlock().getIndex());
    L3:
        int r02 = r1.nextSetBit(0);
        if (r02 == (-1)) goto L6;
        r2.set(r02);
        r1.or(this.blocks.get(r02).getSuccessors());
        r1.andNot(r2);
        goto L3
    L6:
        return r2;
    }

    public void deleteInsns(Set<SsaInsn> r8) {
        Iterator<SsaInsn> r82 = r8.iterator();
    L4:
        if (r82.hasNext() == false) goto L28;
        SsaInsn r0 = r82.next();
        SsaBasicBlock r1 = r0.getBlock();
        ArrayList<SsaInsn> r2 = r1.getInsns();
        int r3 = r2.size() - 1;
    L6:
        if (r3 < 0) goto L11;
        SsaInsn r5 = r2.get(r3);
        if (r0 == r5) goto L9;
        r3 = r3 - 1;
        goto L6
    L9:
        onInsnRemoved(r5);
        r2.remove(r3);
    L11:
        int r02 = r2.size();
        if (r02 != 0) goto L14;
        SsaInsn r52 = null;
    L16:
        if (r1 == getExitBlock()) goto L4;
        if (r02 == 0) goto L22;
        if (r52.getOriginalRopInsn() == null) goto L22;
        if (r52.getOriginalRopInsn().getOpcode().getBranchingness() != 1) goto L4;
    L22:
        r2.add(SsaInsn.makeFromRop(new PlainInsn(Rops.GOTO, SourcePosition.NO_INFO, null, RegisterSpecList.EMPTY), r1));
        BitSet r03 = r1.getSuccessors();
        int r22 = r03.nextSetBit(0);
    L23:
        if (r22 < 0) goto L4;
        if (r22 == r1.getPrimarySuccessorIndex()) goto L27;
        r1.removeSuccessor(r22);
    L27:
        r22 = r03.nextSetBit(r22 + 1);
        goto L23
    L14:
        r52 = r2.get(r02 - 1);
        goto L16
    }

    public void forEachBlockDepthFirst(boolean r8, SsaBasicBlock.Visitor r9) {
        BitSet r0 = new BitSet(this.blocks.size());
        Stack r1 = new Stack();
        if (r8 == false) goto L5;
        SsaBasicBlock r2 = getExitBlock();
    L6:
        if (r2 == null) goto L20;
        r1.add(null);
        r1.add(r2);
    L10:
        if (r1.size() <= 0) goto L27;
        SsaBasicBlock r22 = (SsaBasicBlock) r1.pop();
        SsaBasicBlock r3 = (SsaBasicBlock) r1.pop();
        if (r0.get(r22.getIndex()) == true) goto L10;
        if (r8 == false) goto L15;
        BitSet r4 = r22.getPredecessors();
    L16:
        int r5 = r4.nextSetBit(0);
    L17:
        if (r5 < 0) goto L19;
        r1.add(r22);
        r1.add(this.blocks.get(r5));
        r5 = r4.nextSetBit(r5 + 1);
        goto L17
    L19:
        r0.set(r22.getIndex());
        r9.visitBlock(r22, r3);
        goto L10
    L15:
        r4 = r22.getSuccessors();
        goto L16
    L27:
        return;
    L20:
        return;
    L5:
        r2 = getEntryBlock();
        goto L6
    }

    public void forEachBlockDepthFirstDom(SsaBasicBlock.Visitor r6) {
        BitSet r0 = new BitSet(getBlocks().size());
        Stack r1 = new Stack();
        r1.add(getEntryBlock());
    L4:
        if (r1.size() <= 0) goto L11;
        SsaBasicBlock r5 = (SsaBasicBlock) r1.pop();
        ArrayList<SsaBasicBlock> r2 = r5.getDomChildren();
        if (r0.get(r5.getIndex()) == true) goto L4;
        int r3 = r2.size() - 1;
    L8:
        if (r3 < 0) goto L10;
        r1.add(r2.get(r3));
        r3 = r3 - 1;
        goto L8
    L10:
        r0.set(r5.getIndex());
        r6.visitBlock(r5, null);
        goto L4
    }

    public void forEachInsn(SsaInsn.Visitor r4) {
        ArrayList<SsaBasicBlock> r3 = this.blocks;
        int r0 = r3.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        SsaBasicBlock r2 = r3.get(r1);
        r1 = r1 + 1;
        r2.forEachInsn(r4);
        goto L3
    }

    public void forEachPhiInsn(PhiInsn.Visitor r4) {
        ArrayList<SsaBasicBlock> r3 = this.blocks;
        int r0 = r3.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        SsaBasicBlock r2 = r3.get(r1);
        r1 = r1 + 1;
        r2.forEachPhiInsn(r4);
        goto L3
    }

    public ArrayList<SsaBasicBlock> getBlocks() {
        return this.blocks;
    }

    public SsaInsn getDefinitionForRegister(int r2) {
        if (this.backMode == true) goto L10;
        SsaInsn[] r0 = this.definitionList;
        if (r0 != null) goto L7;
        this.definitionList = new SsaInsn[getRegCount()];
        forEachInsn(new AnonymousClass1(this));
        return this.definitionList[r2];
    L7:
        return r0[r2];
    L10:
        GlShader$$ExternalSyntheticBUOutline1.m("No def list in back mode");
        return null;
    }

    public SsaBasicBlock getEntryBlock() {
        return this.blocks.get(this.entryBlockIndex);
    }

    public int getEntryBlockIndex() {
        return this.entryBlockIndex;
    }

    public SsaBasicBlock getExitBlock() {
        int r0 = this.exitBlockIndex;
        if (r0 >= 0) goto L7;
        return null;
    L7:
        return this.blocks.get(r0);
    }

    public int getExitBlockIndex() {
        return this.exitBlockIndex;
    }

    public int getParamWidth() {
        return this.paramWidth;
    }

    public int getRegCount() {
        return this.registerCount;
    }

    public ArrayList<SsaInsn>[] getUseListCopy() {
        if (this.useList != null) goto L5;
        buildUseList();
    L5:
        ArrayList<SsaInsn>[] r0 = new ArrayList[this.registerCount];
        int r1 = 0;
    L7:
        if (r1 >= this.registerCount) goto L9;
        r0[r1] = new ArrayList(this.useList[r1]);
        r1 = r1 + 1;
        goto L7
    L9:
        return r0;
    }

    public List<SsaInsn> getUseListForRegister(int r2) {
        if (this.unmodifiableUseList != null) goto L6;
        buildUseList();
    L6:
        return this.unmodifiableUseList[r2];
    }

    public boolean isRegALocal(RegisterSpec r4) {
        SsaInsn r0 = getDefinitionForRegister(r4.getReg());
        if (r0 != null) goto L6;
        return false;
    L6:
        if (r0.getLocalAssignment() == null) goto L8;
        return true;
    L8:
        Iterator<SsaInsn> r3 = getUseListForRegister(r4.getReg()).iterator();
    L10:
        if (r3.hasNext() == false) goto L16;
        Insn r42 = r3.next().getOriginalRopInsn();
        if (r42 == null) goto L10;
        if (r42.getOpcode().getOpcode() != 54) goto L10;
        return true;
    L16:
        return false;
    }

    public boolean isStatic() {
        return this.isStatic;
    }

    public void makeExitBlock() {
        if (this.exitBlockIndex >= 0) goto L11;
        this.exitBlockIndex = this.blocks.size();
        int r1 = this.exitBlockIndex;
        int r2 = this.maxLabel;
        this.maxLabel = r2 + 1;
        SsaBasicBlock r0 = new SsaBasicBlock(r1, r2, this);
        this.blocks.add(r0);
        ArrayList<SsaBasicBlock> r12 = this.blocks;
        int r22 = r12.size();
        int r3 = 0;
    L5:
        if (r3 >= r22) goto L8;
        SsaBasicBlock r4 = r12.get(r3);
        r3 = r3 + 1;
        r4.exitBlockFixup(r0);
        goto L5
    L8:
        if (r0.getPredecessors().cardinality() != 0) goto L14;
        this.blocks.remove(this.exitBlockIndex);
        this.exitBlockIndex = -1;
        this.maxLabel--;
        return;
    L14:
        return;
    L11:
        GlShader$$ExternalSyntheticBUOutline1.m("must be called at most once");
    }

    public SsaBasicBlock makeNewGotoBlock() {
        int r0 = this.blocks.size();
        int r2 = this.maxLabel;
        this.maxLabel = r2 + 1;
        SsaBasicBlock r1 = new SsaBasicBlock(r0, r2, this);
        r1.getInsns().add(getGoto(r1));
        this.blocks.add(r1);
        return r1;
    }

    public int makeNewSsaReg() {
        int r0 = this.registerCount;
        int r1 = r0 + 1;
        this.registerCount = r1;
        this.spareRegisterBase = r1;
        onInsnsChanged();
        return r0;
    }

    public void mapRegisters(RegisterMapper r9) {
        ArrayList<SsaBasicBlock> r0 = getBlocks();
        int r1 = r0.size();
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L7;
        SsaBasicBlock r4 = r0.get(r3);
        r3 = r3 + 1;
        ArrayList<SsaInsn> r42 = r4.getInsns();
        int r5 = r42.size();
        int r6 = 0;
    L5:
        if (r6 >= r5) goto L3;
        SsaInsn r7 = r42.get(r6);
        r6 = r6 + 1;
        r7.mapRegisters(r9);
        goto L5
    L7:
        int r92 = r9.getNewRegisterCount();
        this.registerCount = r92;
        this.spareRegisterBase = r92;
    }

    public void onInsnAdded(SsaInsn r2) {
        onSourcesChanged(r2, null);
        updateOneDefinition(r2, null);
    }

    public void onInsnRemoved(SsaInsn r2) {
        if (this.useList == null) goto L5;
        removeFromUseList(r2, r2.getSources());
    L5:
        RegisterSpec r22 = r2.getResult();
        SsaInsn[] r1 = this.definitionList;
        if (r1 == null) goto L10;
        if (r22 == null) goto L11;
        r1[r22.getReg()] = null;
        return;
    L11:
        return;
    }

    public void onInsnsChanged() {
        this.definitionList = null;
        this.useList = null;
        this.unmodifiableUseList = null;
    }

    public void onSourceChanged(SsaInsn r2, RegisterSpec r3, RegisterSpec r4) {
        if (this.useList != null) goto L5;
        return;
    L5:
        if (r3 == null) goto L7;
        int r32 = r3.getReg();
        this.useList[r32].remove(r2);
    L7:
        int r33 = r4.getReg();
        ArrayList<SsaInsn>[] r42 = this.useList;
        if (r42.length > r33) goto L11;
        this.useList = null;
        return;
    L11:
        r42[r33].add(r2);
    }

    public void onSourcesChanged(SsaInsn r5, RegisterSpecList r6) {
        if (this.useList == null) goto L10;
        if (r6 == null) goto L7;
        removeFromUseList(r5, r6);
    L7:
        RegisterSpecList r62 = r5.getSources();
        int r0 = r62.size();
        int r1 = 0;
    L8:
        if (r1 >= r0) goto L12;
        int r2 = r62.get(r1).getReg();
        this.useList[r2].add(r5);
        r1 = r1 + 1;
        goto L8
    L12:
        return;
    }

    public void returnSpareRegisters() {
        this.borrowedSpareRegisters = 0;
    }

    public void setBackMode() {
        this.backMode = true;
        this.useList = null;
        this.definitionList = null;
    }

    public void setNewRegCount(int r1) {
        this.registerCount = r1;
        this.spareRegisterBase = r1;
        onInsnsChanged();
    }

    public void updateOneDefinition(SsaInsn r3, RegisterSpec r4) {
        if (this.definitionList == null) goto L16;
        if (r4 == null) goto L7;
        int r42 = r4.getReg();
        this.definitionList[r42] = null;
    L7:
        RegisterSpec r43 = r3.getResult();
        if (r43 == null) goto L15;
        int r0 = r43.getReg();
        SsaInsn[] r2 = this.definitionList;
        if (r2[r0] != null) goto L13;
        r2[r43.getReg()] = r3;
        return;
    L13:
        GlShader$$ExternalSyntheticBUOutline1.m("Duplicate add of insn");
        return;
    L15:
        return;
    }
}
