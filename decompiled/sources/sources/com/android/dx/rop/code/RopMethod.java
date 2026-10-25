package com.android.dx.rop.code;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.util.Hex;
import com.android.dx.util.IntList;
import org.mvel2.ast.Instance$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
public final class RopMethod {
    private final BasicBlockList blocks;
    private IntList exitPredecessors;
    private final int firstLabel;
    private IntList[] predecessors;

    public RopMethod(BasicBlockList r2, int r3) {
        if (r2 == null) goto L9;
        if (r3 < 0) goto L7;
        this.blocks = r2;
        this.firstLabel = r3;
        this.predecessors = null;
        this.exitPredecessors = null;
        return;
    L7:
        f$$ExternalSyntheticBUOutline1.m("firstLabel < 0");
        throw null;
    L9:
        f$$ExternalSyntheticBUOutline2.m("blocks == null");
        throw null;
    }

    private void calcPredecessors() {
        int r0 = this.blocks.getMaxLabel();
        IntList[] r1 = new IntList[r0];
        IntList r2 = new IntList(10);
        int r4 = this.blocks.size();
        int r5 = 0;
        int r6 = 0;
    L3:
        if (r6 >= r4) goto L14;
        BasicBlock r7 = this.blocks.get(r6);
        int r8 = r7.getLabel();
        IntList r72 = r7.getSuccessors();
        int r9 = r72.size();
        if (r9 != 0) goto L7;
        r2.add(r8);
    L13:
        r6 = r6 + 1;
        goto L3
    L7:
        int r10 = 0;
    L8:
        if (r10 >= r9) goto L13;
        int r11 = r72.get(r10);
        IntList r12 = r1[r11];
        if (r12 != null) goto L12;
        r12 = new IntList(10);
        r1[r11] = r12;
    L12:
        r12.add(r8);
        r10 = r10 + 1;
    L14:
        if (r5 >= r0) goto L19;
        IntList r3 = r1[r5];
        if (r3 == null) goto L18;
        r3.sort();
        r3.setImmutable();
    L18:
        r5 = r5 + 1;
        goto L14
    L19:
        r2.sort();
        r2.setImmutable();
        int r02 = this.firstLabel;
        if (r1[r02] != null) goto L22;
        r1[r02] = IntList.EMPTY;
    L22:
        this.predecessors = r1;
        this.exitPredecessors = r2;
    }

    public BasicBlockList getBlocks() {
        return this.blocks;
    }

    public IntList getExitPredecessors() {
        if (this.exitPredecessors != null) goto L6;
        calcPredecessors();
    L6:
        return this.exitPredecessors;
    }

    public int getFirstLabel() {
        return this.firstLabel;
    }

    public IntList labelToPredecessors(int r2) {
        if (this.exitPredecessors != null) goto L5;
        calcPredecessors();
    L5:
        IntList r1 = this.predecessors[r2];
        if (r1 == null) goto L8;
        return r1;
    L8:
        Instance$$ExternalSyntheticBUOutline0.m("no such block: ", Hex.u2(r2));
        return null;
    }

    public RopMethod withRegisterOffset(int r3) {
        RopMethod r0 = new RopMethod(this.blocks.withRegisterOffset(r3), this.firstLabel);
        IntList r32 = this.exitPredecessors;
        if (r32 == null) goto L5;
        r0.exitPredecessors = r32;
        r0.predecessors = this.predecessors;
    L5:
        return r0;
    }
}
