package com.android.dx.ssa;

import com.android.dx.rop.code.Insn;
import com.android.dx.rop.code.RegisterSpec;
import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.rop.code.Rop;
import com.android.dx.ssa.SsaInsn;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public final class NormalSsaInsn extends SsaInsn implements Cloneable {
    private Insn insn;

    public NormalSsaInsn(Insn r2, SsaBasicBlock r3) {
        super(r2.getResult(), r3);
        this.insn = r2;
    }

    @Override // com.android.dx.ssa.SsaInsn
    public void accept(SsaInsn.Visitor r2) {
        if (isNormalMoveInsn() == false) goto L6;
        r2.visitMoveInsn(this);
        return;
    L6:
        r2.visitNonMoveInsn(this);
    }

    @Override // com.android.dx.ssa.SsaInsn
    public boolean canThrow() {
        return this.insn.canThrow();
    }

    public final void changeOneSource(int r6, RegisterSpec r7) {
        RegisterSpecList r0 = this.insn.getSources();
        int r1 = r0.size();
        RegisterSpecList r2 = new RegisterSpecList(r1);
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L8;
        if (r3 != r6) goto L6;
        RegisterSpec r4 = r7;
    L7:
        r2.set(r3, r4);
        r3 = r3 + 1;
        goto L3
    L6:
        r4 = r0.get(r3);
        goto L7
    L8:
        r2.setImmutable();
        RegisterSpec r62 = r0.get(r6);
        if (r62.getReg() == r7.getReg()) goto L11;
        getBlock().getParent().onSourceChanged(this, r62, r7);
    L11:
        this.insn = this.insn.withNewRegisters(getResult(), r2);
    }

    @Override // com.android.dx.ssa.SsaInsn
    public NormalSsaInsn clone() {
        return (NormalSsaInsn) super.clone();
    }

    @Override // com.android.dx.ssa.SsaInsn
    public /* bridge */ /* synthetic */ SsaInsn clone() {
        return clone();
    }

    @Override // com.android.dx.ssa.SsaInsn
    /* renamed from: clone, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ Object mo1004clone() {
        return clone();
    }

    @Override // com.android.dx.ssa.SsaInsn
    public RegisterSpec getLocalAssignment() {
        if (this.insn.getOpcode().getOpcode() != 54) goto L5;
        RegisterSpec r2 = this.insn.getSources().get(0);
    L7:
        if (r2 != null) goto L10;
        return null;
    L10:
        if (r2.getLocalItem() != null) goto L12;
        return null;
    L12:
        return r2;
    L5:
        r2 = getResult();
        goto L7
    }

    @Override // com.android.dx.ssa.SsaInsn
    public Rop getOpcode() {
        return this.insn.getOpcode();
    }

    @Override // com.android.dx.ssa.SsaInsn
    public Insn getOriginalRopInsn() {
        return this.insn;
    }

    @Override // com.android.dx.ssa.SsaInsn
    public RegisterSpecList getSources() {
        return this.insn.getSources();
    }

    @Override // com.android.dx.ssa.SsaInsn
    public boolean hasSideEffect() {
        Rop r0 = getOpcode();
        if (r0.getBranchingness() == 1) goto L6;
        return true;
    L6:
        if (Optimizer.getPreserveLocals() == true) goto L8;
    L10:
        boolean r3 = false;
    L11:
        int r02 = r0.getOpcode();
        if (r02 != 2) goto L14;
    L18:
        return r3;
    L14:
        if (r02 == 5) goto L18;
        if (r02 == 55) goto L18;
        return true;
    L8:
        if (getLocalAssignment() == null) goto L10;
        r3 = true;
        goto L11
    }

    @Override // com.android.dx.ssa.SsaInsn
    public boolean isMoveException() {
        if (this.insn.getOpcode().getOpcode() != 4) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // com.android.dx.ssa.SsaInsn
    public boolean isNormalMoveInsn() {
        if (this.insn.getOpcode().getOpcode() != 2) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // com.android.dx.ssa.SsaInsn
    public boolean isPhiOrMove() {
        return isNormalMoveInsn();
    }

    @Override // com.android.dx.ssa.SsaInsn
    public final void mapSourceRegisters(RegisterMapper r4) {
        RegisterSpecList r0 = this.insn.getSources();
        RegisterSpecList r42 = r4.map(r0);
        if (r42 == r0) goto L6;
        this.insn = this.insn.withNewRegisters(getResult(), r42);
        getBlock().getParent().onSourcesChanged(this, r0);
        return;
    }

    public final void setNewSources(RegisterSpecList r3) {
        if (this.insn.getSources().size() != r3.size()) goto L6;
        this.insn = this.insn.withNewRegisters(getResult(), r3);
        return;
    L6:
        GlShader$$ExternalSyntheticBUOutline1.m("Sources counts don't match");
    }

    @Override // com.android.dx.util.ToHuman
    public String toHuman() {
        return toRopInsn().toHuman();
    }

    @Override // com.android.dx.ssa.SsaInsn
    public Insn toRopInsn() {
        return this.insn.withNewRegisters(getResult(), this.insn.getSources());
    }

    public void upgradeToLiteral() {
        RegisterSpecList r0 = this.insn.getSources();
        this.insn = this.insn.withSourceLiteral();
        getBlock().getParent().onSourcesChanged(this, r0);
    }
}
