package com.android.dx.ssa;

import com.android.dx.rop.code.RopMethod;
import com.android.dx.rop.code.TranslationAdvice;
import com.android.dx.ssa.back.LivenessAnalyzer;
import com.android.dx.ssa.back.SsaToRop;
import java.util.EnumSet;

/* loaded from: classes4.dex */
public class Optimizer {
    private static TranslationAdvice advice = null;
    private static boolean preserveLocals = true;

    public enum OptionalStep extends Enum<OptionalStep> {
        private static final /* synthetic */ OptionalStep[] $VALUES = null;
        public static final OptionalStep CONST_COLLECTOR = null;
        public static final OptionalStep ESCAPE_ANALYSIS = null;
        public static final OptionalStep LITERAL_UPGRADE = null;
        public static final OptionalStep MOVE_PARAM_COMBINER = null;
        public static final OptionalStep SCCP = null;

        static {
            OptionalStep r0 = new OptionalStep("MOVE_PARAM_COMBINER", 0);
            MOVE_PARAM_COMBINER = r0;
            OptionalStep r1 = new OptionalStep("SCCP", 1);
            SCCP = r1;
            OptionalStep r2 = new OptionalStep("LITERAL_UPGRADE", 2);
            LITERAL_UPGRADE = r2;
            OptionalStep r3 = new OptionalStep("CONST_COLLECTOR", 3);
            CONST_COLLECTOR = r3;
            OptionalStep r4 = new OptionalStep("ESCAPE_ANALYSIS", 4);
            ESCAPE_ANALYSIS = r4;
            $VALUES = new OptionalStep[]{r0, r1, r2, r3, r4};
        }

        OptionalStep(String r1, int r2) {
        }

        public static OptionalStep valueOf(String r1) {
            return (OptionalStep) Enum.valueOf(OptionalStep.class, r1);
        }

        public static OptionalStep[] values() {
            return (OptionalStep[]) $VALUES.clone();
        }
    }

    static {
    }

    public Optimizer() {
    }

    public static SsaMethod debugDeadCodeRemover(RopMethod r0, int r1, boolean r2, boolean r3, TranslationAdvice r4) {
        preserveLocals = r3;
        advice = r4;
        SsaMethod r02 = SsaConverter.convertToSsaMethod(r0, r1, r2);
        DeadCodeRemover.process(r02);
        return r02;
    }

    public static SsaMethod debugEdgeSplit(RopMethod r0, int r1, boolean r2, boolean r3, TranslationAdvice r4) {
        preserveLocals = r3;
        advice = r4;
        return SsaConverter.testEdgeSplit(r0, r1, r2);
    }

    public static SsaMethod debugNoRegisterAllocation(RopMethod r0, int r1, boolean r2, boolean r3, TranslationAdvice r4, EnumSet<OptionalStep> r5) {
        preserveLocals = r3;
        advice = r4;
        SsaMethod r02 = SsaConverter.convertToSsaMethod(r0, r1, r2);
        runSsaFormSteps(r02, r5);
        LivenessAnalyzer.constructInterferenceGraph(r02);
        return r02;
    }

    public static SsaMethod debugPhiPlacement(RopMethod r0, int r1, boolean r2, boolean r3, TranslationAdvice r4) {
        preserveLocals = r3;
        advice = r4;
        return SsaConverter.testPhiPlacement(r0, r1, r2);
    }

    public static SsaMethod debugRenaming(RopMethod r0, int r1, boolean r2, boolean r3, TranslationAdvice r4) {
        preserveLocals = r3;
        advice = r4;
        return SsaConverter.convertToSsaMethod(r0, r1, r2);
    }

    public static TranslationAdvice getAdvice() {
        return advice;
    }

    public static boolean getPreserveLocals() {
        return preserveLocals;
    }

    public static RopMethod optimize(RopMethod r7, int r8, boolean r9, boolean r10, TranslationAdvice r11) {
        return optimize(r7, r8, r9, r10, r11, EnumSet.allOf(OptionalStep.class));
    }

    public static RopMethod optimize(RopMethod r1, int r2, boolean r3, boolean r4, TranslationAdvice r5, EnumSet<OptionalStep> r6) {
        preserveLocals = r4;
        advice = r5;
        SsaMethod r42 = SsaConverter.convertToSsaMethod(r1, r2, r3);
        runSsaFormSteps(r42, r6);
        RopMethod r43 = SsaToRop.convertToRopMethod(r42, false);
        if (r43.getBlocks().getRegCount() > advice.getMaxOptimalRegisterCount()) goto L5;
        return r43;
    L5:
        return optimizeMinimizeRegisters(r1, r2, r3, r6);
    }

    private static RopMethod optimizeMinimizeRegisters(RopMethod r0, int r1, boolean r2, EnumSet<OptionalStep> r3) {
        SsaMethod r02 = SsaConverter.convertToSsaMethod(r0, r1, r2);
        EnumSet<E> r12 = r3.clone();
        r12.remove(OptionalStep.CONST_COLLECTOR);
        runSsaFormSteps(r02, r12);
        return SsaToRop.convertToRopMethod(r02, true);
    }

    private static void runSsaFormSteps(SsaMethod r3, EnumSet<OptionalStep> r4) {
        if (r4.contains(OptionalStep.MOVE_PARAM_COMBINER) == false) goto L5;
        MoveParamCombiner.process(r3);
    L5:
        boolean r1 = false;
        if (r4.contains(OptionalStep.SCCP) == false) goto L8;
        SCCP.process(r3);
        DeadCodeRemover.process(r3);
        boolean r0 = false;
    L10:
        if (r4.contains(OptionalStep.LITERAL_UPGRADE) == false) goto L12;
        LiteralOpUpgrader.process(r3);
        DeadCodeRemover.process(r3);
        r0 = false;
    L12:
        OptionalStep r2 = OptionalStep.ESCAPE_ANALYSIS;
        r4.remove(r2);
        if (r4.contains(r2) == false) goto L16;
        EscapeAnalysis.process(r3);
        DeadCodeRemover.process(r3);
        r0 = false;
    L16:
        if (r4.contains(OptionalStep.CONST_COLLECTOR) == false) goto L18;
        ConstCollector.process(r3);
        DeadCodeRemover.process(r3);
    L19:
        if (r1 == false) goto L21;
        DeadCodeRemover.process(r3);
    L21:
        PhiTypeResolver.process(r3);
        return;
    L18:
        r1 = r0;
        goto L19
    L8:
        r0 = true;
        goto L10
    }
}
