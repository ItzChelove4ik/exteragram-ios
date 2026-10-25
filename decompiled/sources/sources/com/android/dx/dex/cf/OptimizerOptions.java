package com.android.dx.dex.cf;

import c.a$$ExternalSyntheticBUOutline0;
import com.android.dx.rop.code.RopMethod;
import com.android.dx.rop.code.TranslationAdvice;
import com.android.dx.ssa.Optimizer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.EnumSet;
import java.util.HashSet;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public class OptimizerOptions {
    private HashSet<String> dontOptimizeList;
    private HashSet<String> optimizeList;
    private boolean optimizeListsLoaded;

    public OptimizerOptions() {
    }

    private static HashSet<String> loadStringsFromFile(String r4) {
        HashSet<String> r0 = new HashSet();
        FileReader r1 = new FileReader(r4);     // Catch: IOException -> L7
        BufferedReader r2 = new BufferedReader(r1);     // Catch: IOException -> L7
    L4:
        String r3 = r2.readLine();     // Catch: IOException -> L7
        if (r3 == null) goto L9;
        r0.add(r3);     // Catch: IOException -> L7
        goto L4
    L9:
        r1.close();     // Catch: IOException -> L7
        return r0;
    L7:
        e = move-exception;
        a$$ExternalSyntheticBUOutline0.m("Error with optimize list: ", r4, e);
        return null;
    }

    public void compareOptimizerStep(RopMethod r7, int r8, boolean r9, CfOptions r10, TranslationAdvice r11, RopMethod r12) {
        EnumSet r5 = EnumSet.allOf(Optimizer.OptionalStep.class);
        r5.remove(Optimizer.OptionalStep.CONST_COLLECTOR);
        RopMethod r6 = Optimizer.optimize(r7, r8, r9, r10.localInfo, r11, r5);
        System.err.printf("optimize step regs:(%d/%d/%.2f%%) insns:(%d/%d/%.2f%%)\n", new Object[]{Integer.valueOf(r12.getBlocks().getRegCount()), Integer.valueOf(r6.getBlocks().getRegCount()), Double.valueOf(((r6.getBlocks().getRegCount() - r12.getBlocks().getRegCount()) / r6.getBlocks().getRegCount()) * 100.0d), Integer.valueOf(r12.getBlocks().getEffectiveInstructionCount()), Integer.valueOf(r6.getBlocks().getEffectiveInstructionCount()), Double.valueOf(((r8 - r7) / r8) * 100.0d)});
    }

    public void loadOptimizeLists(String r2, String r3) {
        if (this.optimizeListsLoaded == false) goto L5;
        return;
    L5:
        if (r2 == null) goto L10;
        if (r3 == null) goto L10;
        GlShader$$ExternalSyntheticBUOutline1.m("optimize and don't optimize lists  are mutually exclusive.");
        return;
    L10:
        if (r2 == null) goto L12;
        this.optimizeList = loadStringsFromFile(r2);
    L12:
        if (r3 == null) goto L14;
        this.dontOptimizeList = loadStringsFromFile(r3);
    L14:
        this.optimizeListsLoaded = true;
    }

    public boolean shouldOptimize(String r2) {
        HashSet<String> r0 = this.optimizeList;
        if (r0 == null) goto L7;
        return r0.contains(r2);
    L7:
        if (this.dontOptimizeList != null) goto L9;
        return true;
    L9:
        return !r1.contains(r2);
    }
}
