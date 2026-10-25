package com.android.dx.rop.code;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.type.TypeList;
import com.android.dx.util.Hex;
import com.android.dx.util.IntList;
import com.android.dx.util.LabeledItem;
import kotlin.text.CharsKt__CharKt$$ExternalSyntheticBUOutline0;
import okio.ByteString$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
public final class BasicBlock implements LabeledItem {
    private final InsnList insns;
    private final int label;
    private final int primarySuccessor;
    private final IntList successors;

    /* loaded from: classes4.dex */
    public interface Visitor {
        void visitBlock(BasicBlock r1);
    }

    public BasicBlock(int r6, InsnList r7, IntList r8, int r9) {
        if (r6 >= 0) goto L40;
        f$$ExternalSyntheticBUOutline1.m("label < 0");
        throw null;
    L40:
        r7.throwIfMutable();     // Catch: NullPointerException -> L36
        int r1 = r7.size();
        if (r1 == 0) goto L34;
        int r2 = r1 - 2;
    L9:
        if (r2 < 0) goto L16;
        if (r7.get(r2).getOpcode().getBranchingness() != 1) goto L13;
        r2 = r2 - 1;
        goto L9
    L13:
        CharsKt__CharKt$$ExternalSyntheticBUOutline0.m("insns[", r2, "] is a branch or can throw");
        throw null;
    L16:
        if (r7.get(r1 - 1).getOpcode().getBranchingness() != 1) goto L42;
        f$$ExternalSyntheticBUOutline1.m("insns does not end with a branch or throwing instruction");
        throw null;
    L42:
        r8.throwIfMutable();     // Catch: NullPointerException -> L30
        if (r9 < (-1)) goto L28;
        if (r9 >= 0) goto L22;
    L26:
        this.label = r6;
        this.insns = r7;
        this.successors = r8;
        this.primarySuccessor = r9;
        return;
    L22:
        if (r8.contains(r9) == true) goto L26;
        throw new IllegalArgumentException("primarySuccessor " + r9 + " not in successors " + r8);
    L28:
        f$$ExternalSyntheticBUOutline1.m("primarySuccessor < -1");
        throw null;
    L30:
        f$$ExternalSyntheticBUOutline2.m("successors == null");
        throw null;
    L34:
        f$$ExternalSyntheticBUOutline1.m("insns.size() == 0");
        throw null;
    L36:
        f$$ExternalSyntheticBUOutline2.m("insns == null");
        throw null;
    }

    public boolean canThrow() {
        return this.insns.getLast().canThrow();
    }

    public boolean equals(Object r1) {
        if (this != r1) goto L5;
        return true;
    L5:
        return false;
    }

    public TypeList getExceptionHandlerTypes() {
        return this.insns.getLast().getCatches();
    }

    public Insn getFirstInsn() {
        return this.insns.get(0);
    }

    public InsnList getInsns() {
        return this.insns;
    }

    @Override // com.android.dx.util.LabeledItem
    public int getLabel() {
        return this.label;
    }

    public Insn getLastInsn() {
        return this.insns.getLast();
    }

    public int getPrimarySuccessor() {
        return this.primarySuccessor;
    }

    public int getSecondarySuccessor() {
        if (this.successors.size() != 2) goto L9;
        int r0 = this.successors.get(0);
        if (r0 == this.primarySuccessor) goto L7;
        return r0;
    L7:
        return this.successors.get(1);
    L9:
        ByteString$$ExternalSyntheticBUOutline0.m("block doesn't have exactly two successors");
        return 0;
    }

    public IntList getSuccessors() {
        return this.successors;
    }

    public boolean hasExceptionHandlers() {
        if (this.insns.getLast().getCatches().size() == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public int hashCode() {
        return System.identityHashCode(this);
    }

    public String toString() {
        return "{" + Hex.u2(this.label) + '}';
    }

    public BasicBlock withRegisterOffset(int r4) {
        return new BasicBlock(this.label, this.insns.withRegisterOffset(r4), this.successors, this.primarySuccessor);
    }
}
