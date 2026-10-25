package com.android.dx.ssa;

import com.android.dx.rop.code.Insn;
import com.android.dx.rop.code.LocalItem;
import com.android.dx.rop.code.RegisterSpec;
import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.rop.code.Rop;
import com.android.dx.rop.code.SourcePosition;
import com.android.dx.rop.type.Type;
import com.android.dx.rop.type.TypeBearer;
import com.android.dx.ssa.SsaInsn;
import com.android.dx.util.Hex;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public final class PhiInsn extends SsaInsn {
    private final ArrayList<Operand> operands;
    private final int ropResultReg;
    private RegisterSpecList sources;

    public static class Operand {
        public final int blockIndex;
        public RegisterSpec regSpec;
        public final int ropLabel;

        public Operand(RegisterSpec r1, int r2, int r3) {
            this.regSpec = r1;
            this.blockIndex = r2;
            this.ropLabel = r3;
        }
    }

    public interface Visitor {
        void visitPhiInsn(PhiInsn r1);
    }

    public PhiInsn(int r2, SsaBasicBlock r3) {
        super(RegisterSpec.make(r2, Type.VOID), r3);
        this.operands = new ArrayList();
        this.ropResultReg = r2;
    }

    public PhiInsn(RegisterSpec r1, SsaBasicBlock r2) {
        super(r1, r2);
        this.operands = new ArrayList();
        this.ropResultReg = r1.getReg();
    }

    @Override // com.android.dx.ssa.SsaInsn
    public void accept(SsaInsn.Visitor r1) {
        r1.visitPhiInsn(this);
    }

    public void addPhiOperand(RegisterSpec r4, SsaBasicBlock r5) {
        this.operands.add(new Operand(r4, r5.getIndex(), r5.getRopLabel()));
        this.sources = null;
    }

    public boolean areAllOperandsEqual() {
        if (this.operands.size() != 0) goto L5;
        return true;
    L5:
        int r0 = this.operands.get(0).regSpec.getReg();
        ArrayList<Operand> r6 = this.operands;
        int r3 = r6.size();
        int r4 = 0;
    L6:
        if (r4 >= r3) goto L10;
        Operand r5 = r6.get(r4);
        r4 = r4 + 1;
        if (r0 == r5.regSpec.getReg()) goto L6;
        return false;
    L10:
        return true;
    }

    @Override // com.android.dx.ssa.SsaInsn
    public boolean canThrow() {
        return false;
    }

    public void changeResultType(TypeBearer r2, LocalItem r3) {
        setResult(RegisterSpec.makeLocalOptional(getResult().getReg(), r2, r3));
    }

    @Override // com.android.dx.ssa.SsaInsn
    public PhiInsn clone() {
        throw new UnsupportedOperationException("can't clone phi");
    }

    @Override // com.android.dx.ssa.SsaInsn
    public /* bridge */ /* synthetic */ SsaInsn clone() {
        return clone();
    }

    @Override // com.android.dx.ssa.SsaInsn
    /* renamed from: clone */
    public /* bridge */ /* synthetic */ Object mo1004clone() {
        return clone();
    }

    @Override // com.android.dx.ssa.SsaInsn
    public Rop getOpcode() {
        return null;
    }

    @Override // com.android.dx.ssa.SsaInsn
    public Insn getOriginalRopInsn() {
        return null;
    }

    public int getRopResultReg() {
        return this.ropResultReg;
    }

    @Override // com.android.dx.ssa.SsaInsn
    public RegisterSpecList getSources() {
        RegisterSpecList r0 = this.sources;
        if (r0 == null) goto L6;
        return r0;
    L6:
        if (this.operands.size() == 0) goto L8;
        int r02 = this.operands.size();
        this.sources = new RegisterSpecList(r02);
        int r1 = 0;
    L10:
        if (r1 >= r02) goto L12;
        Operand r2 = this.operands.get(r1);
        this.sources.set(r1, r2.regSpec);
        r1 = r1 + 1;
        goto L10
    L12:
        this.sources.setImmutable();
        return this.sources;
    L8:
        return RegisterSpecList.EMPTY;
    }

    @Override // com.android.dx.ssa.SsaInsn
    public boolean hasSideEffect() {
        if (Optimizer.getPreserveLocals() == true) goto L5;
        return false;
    L5:
        if (getLocalAssignment() == null) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // com.android.dx.ssa.SsaInsn
    public boolean isPhiOrMove() {
        return true;
    }

    @Override // com.android.dx.ssa.SsaInsn
    public boolean isRegASource(int r5) {
        ArrayList<Operand> r4 = this.operands;
        int r0 = r4.size();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L8;
        Operand r3 = r4.get(r2);
        r2 = r2 + 1;
        if (r3.regSpec.getReg() != r5) goto L3;
        return true;
    L8:
        return false;
    }

    @Override // com.android.dx.ssa.SsaInsn
    public final void mapSourceRegisters(RegisterMapper r7) {
        ArrayList<Operand> r0 = this.operands;
        int r1 = r0.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L7;
        Operand r3 = r0.get(r2);
        r2 = r2 + 1;
        Operand r32 = r3;
        RegisterSpec r4 = r32.regSpec;
        RegisterSpec r5 = r7.map(r4);
        r32.regSpec = r5;
        if (r4 == r5) goto L3;
        getBlock().getParent().onSourceChanged(this, r4, r32.regSpec);
        goto L3
    L7:
        this.sources = null;
    }

    public int predBlockIndexForSourcesIndex(int r1) {
        return this.operands.get(r1).blockIndex;
    }

    public List<SsaBasicBlock> predBlocksForReg(int r6, SsaMethod r7) {
        ArrayList r0 = new ArrayList();
        ArrayList<Operand> r5 = this.operands;
        int r1 = r5.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L7;
        Operand r3 = r5.get(r2);
        r2 = r2 + 1;
        Operand r32 = r3;
        if (r32.regSpec.getReg() != r6) goto L3;
        r0.add(r7.getBlocks().get(r32.blockIndex));
        goto L3
    L7:
        return r0;
    }

    public void removePhiRegister(RegisterSpec r8) {
        ArrayList r0 = new ArrayList();
        ArrayList<Operand> r1 = this.operands;
        int r2 = r1.size();
        int r3 = 0;
    L3:
        if (r3 >= r2) goto L7;
        Operand r4 = r1.get(r3);
        r3 = r3 + 1;
        Operand r42 = r4;
        if (r42.regSpec.getReg() != r8.getReg()) goto L3;
        r0.add(r42);
        goto L3
    L7:
        this.operands.removeAll(r0);
        this.sources = null;
    }

    @Override // com.android.dx.util.ToHuman
    public String toHuman() {
        return toHumanWithInline(null);
    }

    public final String toHumanWithInline(String r6) {
        StringBuilder r0 = new StringBuilder(80);
        r0.append(SourcePosition.NO_INFO);
        r0.append(": phi");
        if (r6 == null) goto L5;
        r0.append("(");
        r0.append(r6);
        r0.append(")");
    L5:
        RegisterSpec r62 = getResult();
        if (r62 != null) goto L8;
        r0.append(" .");
    L9:
        r0.append(" <-");
        int r63 = getSources().size();
        if (r63 != 0) goto L12;
        r0.append(" .");
    L16:
        return r0.toString();
    L12:
        int r2 = 0;
    L13:
        if (r2 >= r63) goto L16;
        r0.append(" ");
        r0.append(this.sources.get(r2).toHuman() + "[b=" + Hex.u2(this.operands.get(r2).ropLabel) + "]");
        r2 = r2 + 1;
        goto L13
    L8:
        r0.append(" ");
        r0.append(r62.toHuman());
        goto L9
    }

    @Override // com.android.dx.ssa.SsaInsn
    public Insn toRopInsn() {
        throw new IllegalArgumentException("Cannot convert phi insns to rop form");
    }

    public void updateSourcesToDefinitions(SsaMethod r7) {
        ArrayList<Operand> r0 = this.operands;
        int r1 = r0.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        Operand r3 = r0.get(r2);
        r2 = r2 + 1;
        Operand r32 = r3;
        RegisterSpec r4 = r7.getDefinitionForRegister(r32.regSpec.getReg()).getResult();
        r32.regSpec = r32.regSpec.withType(r4.getType());
        goto L3
    L5:
        this.sources = null;
    }
}
