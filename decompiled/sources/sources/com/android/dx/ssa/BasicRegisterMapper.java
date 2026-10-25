package com.android.dx.ssa;

import com.android.dx.rop.code.RegisterSpec;
import com.android.dx.util.IntList;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public class BasicRegisterMapper extends RegisterMapper {
    private final IntList oldToNew;
    private int runningCountNewRegisters;

    public BasicRegisterMapper(int r2) {
        this.oldToNew = new IntList(r2);
    }

    public void addMapping(int r4, int r5, int r6) {
        if (r4 < this.oldToNew.size()) goto L7;
        int r0 = r4 - this.oldToNew.size();
    L5:
        if (r0 < 0) goto L7;
        this.oldToNew.add(-1);
        r0 = r0 - 1;
    L7:
        this.oldToNew.set(r4, r5);
        int r52 = r5 + r6;
        if (this.runningCountNewRegisters >= r52) goto L12;
        this.runningCountNewRegisters = r52;
        return;
    }

    @Override // com.android.dx.ssa.RegisterMapper
    public int getNewRegisterCount() {
        return this.runningCountNewRegisters;
    }

    @Override // com.android.dx.ssa.RegisterMapper
    public RegisterSpec map(RegisterSpec r3) {
        if (r3 != null) goto L13;
        return null;
    L13:
        int r2 = this.oldToNew.get(r3.getReg());     // Catch: IndexOutOfBoundsException -> L7
    L8:
        if (r2 >= 0) goto L10;
        GlShader$$ExternalSyntheticBUOutline1.m("no mapping specified for register");
        return null;
    L10:
        return r3.withReg(r2);
    L7:
        r2 = -1;
        goto L8
    }

    public int oldToNew(int r2) {
        if (r2 < this.oldToNew.size()) goto L7;
        return -1;
    L7:
        return this.oldToNew.get(r2);
    }

    public String toHuman() {
        StringBuilder r0 = new StringBuilder("Old\tNew\n");
        int r1 = this.oldToNew.size();
        int r2 = 0;
    L4:
        if (r2 >= r1) goto L6;
        r0.append(r2);
        r0.append('\t');
        r0.append(this.oldToNew.get(r2));
        r0.append('\n');
        r2 = r2 + 1;
        goto L4
    L6:
        r0.append("new reg count:");
        r0.append(this.runningCountNewRegisters);
        r0.append('\n');
        return r0.toString();
    }
}
