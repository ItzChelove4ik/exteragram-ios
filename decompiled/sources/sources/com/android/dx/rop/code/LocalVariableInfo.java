package com.android.dx.rop.code;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.util.MutabilityControl;
import java.util.HashMap;

/* loaded from: classes4.dex */
public final class LocalVariableInfo extends MutabilityControl {
    private final RegisterSpecSet[] blockStarts;
    private final RegisterSpecSet emptySet;
    private final HashMap<Insn, RegisterSpec> insnAssignments;
    private final int regCount;

    public LocalVariableInfo(RopMethod r4) {
        if (r4 == null) goto L6;
        BasicBlockList r42 = r4.getBlocks();
        int r0 = r42.getMaxLabel();
        int r1 = r42.getRegCount();
        this.regCount = r1;
        RegisterSpecSet r2 = new RegisterSpecSet(r1);
        this.emptySet = r2;
        this.blockStarts = new RegisterSpecSet[r0];
        this.insnAssignments = new HashMap(r42.getInstructionCount());
        r2.setImmutable();
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("method == null");
        throw null;
    }

    private RegisterSpecSet getStarts0(int r1) {
        return this.blockStarts[r1];
    L4:
        f$$ExternalSyntheticBUOutline1.m("bogus label");
        return null;
    }

    public void addAssignment(Insn r1, RegisterSpec r2) {
        throwIfImmutable();
        if (r1 == null) goto L9;
        if (r2 == null) goto L7;
        this.insnAssignments.put(r1, r2);
        return;
    L7:
        f$$ExternalSyntheticBUOutline2.m("spec == null");
        return;
    L9:
        f$$ExternalSyntheticBUOutline2.m("insn == null");
    }

    public void debugDump() {
        int r0 = 0;
    L3:
        RegisterSpecSet[] r1 = this.blockStarts;
        if (r0 >= r1.length) goto L13;
        RegisterSpecSet r12 = r1[r0];
        if (r12 == null) goto L12;
        if (r12 != this.emptySet) goto L11;
        System.out.printf("%04x: empty set\n", new Object[]{Integer.valueOf(r0)});
        goto L12
    L11:
        System.out.printf("%04x: %s\n", new Object[]{Integer.valueOf(r0), this.blockStarts[r0]});
    L12:
        r0 = r0 + 1;
        goto L3
    }

    public RegisterSpec getAssignment(Insn r1) {
        return this.insnAssignments.get(r1);
    }

    public int getAssignmentCount() {
        return this.insnAssignments.size();
    }

    public RegisterSpecSet getStarts(int r1) {
        RegisterSpecSet r12 = getStarts0(r1);
        if (r12 == null) goto L6;
        return r12;
    L6:
        return this.emptySet;
    }

    public RegisterSpecSet getStarts(BasicBlock r1) {
        return getStarts(r1.getLabel());
    }

    public boolean mergeStarts(int r5, RegisterSpecSet r6) {
        RegisterSpecSet r0 = getStarts0(r5);
        if (r0 != null) goto L6;
        setStarts(r5, r6);
        return true;
    L6:
        RegisterSpecSet r2 = r0.mutableCopy();
        if (r0.size() == 0) goto L9;
        r2.intersect(r6, true);
    L11:
        if (r0.equals(r2) == false) goto L14;
        return false;
    L14:
        r2.setImmutable();
        setStarts(r5, r2);
        return true;
    L9:
        r2 = r6.mutableCopy();
        goto L11
    }

    public RegisterSpecSet mutableCopyOfStarts(int r1) {
        RegisterSpecSet r12 = getStarts0(r1);
        if (r12 == null) goto L7;
        return r12.mutableCopy();
    L7:
        return new RegisterSpecSet(this.regCount);
    }

    public void setStarts(int r1, RegisterSpecSet r2) {
        throwIfImmutable();
        if (r2 != null) goto L10;
        f$$ExternalSyntheticBUOutline2.m("specs == null");
        return;
    L10:
        this.blockStarts[r1] = r2;     // Catch: ArrayIndexOutOfBoundsException -> L6
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("bogus label");
    }
}
