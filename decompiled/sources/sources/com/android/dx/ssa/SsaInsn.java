package com.android.dx.ssa;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.code.Insn;
import com.android.dx.rop.code.LocalItem;
import com.android.dx.rop.code.RegisterSpec;
import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.rop.code.Rop;
import com.android.dx.util.ToHuman;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public abstract class SsaInsn implements ToHuman, Cloneable {
    private final SsaBasicBlock block;
    private RegisterSpec result;

    public interface Visitor {
        void visitMoveInsn(NormalSsaInsn r1);

        void visitNonMoveInsn(NormalSsaInsn r1);

        void visitPhiInsn(PhiInsn r1);
    }

    public SsaInsn(RegisterSpec r1, SsaBasicBlock r2) {
        if (r2 == null) goto L6;
        this.block = r2;
        this.result = r1;
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("block == null");
        throw null;
    }

    public static SsaInsn makeFromRop(Insn r1, SsaBasicBlock r2) {
        return new NormalSsaInsn(r1, r2);
    }

    public abstract void accept(Visitor r1);

    public abstract boolean canThrow();

    public void changeResultReg(int r2) {
        RegisterSpec r0 = this.result;
        if (r0 == null) goto L6;
        this.result = r0.withReg(r2);
        return;
    }

    public SsaInsn clone() {
        return (SsaInsn) super.clone();
    L4:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("unexpected", e);
        return null;
    }

    /* renamed from: clone */
    public /* bridge */ /* synthetic */ Object mo1004clone() {
        return clone();
    }

    public SsaBasicBlock getBlock() {
        return this.block;
    }

    public RegisterSpec getLocalAssignment() {
        RegisterSpec r0 = this.result;
        if (r0 != null) goto L5;
        return null;
    L5:
        if (r0.getLocalItem() != null) goto L7;
        return null;
    L7:
        return this.result;
    }

    public abstract Rop getOpcode();

    public abstract Insn getOriginalRopInsn();

    public RegisterSpec getResult() {
        return this.result;
    }

    public abstract RegisterSpecList getSources();

    public abstract boolean hasSideEffect();

    public boolean isMoveException() {
        return false;
    }

    public boolean isNormalMoveInsn() {
        return false;
    }

    public abstract boolean isPhiOrMove();

    public boolean isRegASource(int r1) {
        if (getSources().specForRegister(r1) == null) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isResultReg(int r1) {
        RegisterSpec r0 = this.result;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.getReg() != r1) goto L10;
        return true;
    L10:
        return false;
    }

    public final void mapRegisters(RegisterMapper r3) {
        RegisterSpec r0 = this.result;
        this.result = r3.map(r0);
        this.block.getParent().updateOneDefinition(this, r0);
        mapSourceRegisters(r3);
    }

    public abstract void mapSourceRegisters(RegisterMapper r1);

    public void setResult(RegisterSpec r1) {
        if (r1 == null) goto L5;
        this.result = r1;
        return;
    L5:
        f$$ExternalSyntheticBUOutline2.m("result == null");
    }

    public final void setResultLocal(LocalItem r3) {
        if (r3 == this.result.getLocalItem()) goto L9;
        if (r3 != null) goto L6;
    L7:
        this.result = RegisterSpec.makeLocalOptional(this.result.getReg(), this.result.getType(), r3);
        return;
    L6:
        if (r3.equals(this.result.getLocalItem()) == false) goto L7;
        return;
    }

    public abstract Insn toRopInsn();
}
