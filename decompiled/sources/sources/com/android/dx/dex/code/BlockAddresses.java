package com.android.dx.dex.code;

import com.android.dx.rop.code.BasicBlock;
import com.android.dx.rop.code.BasicBlockList;
import com.android.dx.rop.code.RopMethod;
import com.android.dx.rop.code.SourcePosition;

/* loaded from: classes.dex */
public final class BlockAddresses {
    private final CodeAddress[] ends;
    private final CodeAddress[] lasts;
    private final CodeAddress[] starts;

    public BlockAddresses(RopMethod r3) {
        int r0 = r3.getBlocks().getMaxLabel();
        this.starts = new CodeAddress[r0];
        this.lasts = new CodeAddress[r0];
        this.ends = new CodeAddress[r0];
        setupArrays(r3);
    }

    private void setupArrays(RopMethod r9) {
        BasicBlockList r92 = r9.getBlocks();
        int r0 = r92.size();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L5;
        BasicBlock r3 = r92.get(r2);
        int r4 = r3.getLabel();
        this.starts[r4] = new CodeAddress(r3.getInsns().get(0).getPosition());
        SourcePosition r32 = r3.getLastInsn().getPosition();
        this.lasts[r4] = new CodeAddress(r32);
        this.ends[r4] = new CodeAddress(r32);
        r2 = r2 + 1;
        goto L3
    }

    public CodeAddress getEnd(int r1) {
        return this.ends[r1];
    }

    public CodeAddress getEnd(BasicBlock r1) {
        return this.ends[r1.getLabel()];
    }

    public CodeAddress getLast(int r1) {
        return this.lasts[r1];
    }

    public CodeAddress getLast(BasicBlock r1) {
        return this.lasts[r1.getLabel()];
    }

    public CodeAddress getStart(int r1) {
        return this.starts[r1];
    }

    public CodeAddress getStart(BasicBlock r1) {
        return this.starts[r1.getLabel()];
    }
}
