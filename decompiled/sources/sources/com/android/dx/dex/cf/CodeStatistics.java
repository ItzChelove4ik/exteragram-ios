package com.android.dx.dex.cf;

import com.android.dx.dex.code.DalvCode;
import com.android.dx.rop.code.RopMethod;
import java.io.PrintStream;

/* loaded from: classes4.dex */
public final class CodeStatistics {
    private static final boolean DEBUG = false;
    public int dexRunningDeltaInsns;
    public int dexRunningDeltaRegisters;
    public int dexRunningTotalInsns;
    public int runningDeltaInsns;
    public int runningDeltaRegisters;
    public int runningOriginalBytes;
    public int runningTotalInsns;

    public CodeStatistics() {
        this.runningDeltaRegisters = 0;
        this.runningDeltaInsns = 0;
        this.runningTotalInsns = 0;
        this.dexRunningDeltaRegisters = 0;
        this.dexRunningDeltaInsns = 0;
        this.dexRunningTotalInsns = 0;
        this.runningOriginalBytes = 0;
    }

    public void dumpStatistics(PrintStream r8) {
        Integer r0 = Integer.valueOf(this.runningDeltaInsns);
        Integer r1 = Integer.valueOf(this.runningTotalInsns);
        int r2 = this.runningDeltaInsns;
        r8.printf("Optimizer Delta Rop Insns: %d total: %d (%.2f%%) Delta Registers: %d\n", new Object[]{r0, r1, Double.valueOf((r2 / (this.runningTotalInsns + Math.abs(r2))) * 100.0d), Integer.valueOf(this.runningDeltaRegisters)});
        Integer r02 = Integer.valueOf(this.dexRunningDeltaInsns);
        Integer r12 = Integer.valueOf(this.dexRunningTotalInsns);
        int r22 = this.dexRunningDeltaInsns;
        r8.printf("Optimizer Delta Dex Insns: Insns: %d total: %d (%.2f%%) Delta Registers: %d\n", new Object[]{r02, r12, Double.valueOf((r22 / (this.dexRunningTotalInsns + Math.abs(r22))) * 100.0d), Integer.valueOf(this.dexRunningDeltaRegisters)});
        r8.printf("Original bytecode byte count: %d\n", new Object[]{Integer.valueOf(this.runningOriginalBytes)});
    }

    public void updateDexStatistics(DalvCode r4, DalvCode r5) {
        this.dexRunningDeltaInsns += r5.getInsns().codeSize() - r4.getInsns().codeSize();
        this.dexRunningDeltaRegisters += r5.getInsns().getRegistersSize() - r4.getInsns().getRegistersSize();
        this.dexRunningTotalInsns += r5.getInsns().codeSize();
    }

    public void updateOriginalByteCount(int r2) {
        this.runningOriginalBytes += r2;
    }

    public void updateRopStatistics(RopMethod r4, RopMethod r5) {
        int r0 = r4.getBlocks().getEffectiveInstructionCount();
        int r42 = r4.getBlocks().getRegCount();
        int r1 = r5.getBlocks().getEffectiveInstructionCount();
        this.runningDeltaInsns += r1 - r0;
        this.runningDeltaRegisters += r5.getBlocks().getRegCount() - r42;
        this.runningTotalInsns += r1;
    }
}
