package com.android.dx.ssa.back;

import com.android.dx.rop.code.RegisterSpec;
import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.ssa.PhiInsn;
import com.android.dx.ssa.SsaBasicBlock;
import com.android.dx.ssa.SsaInsn;
import com.android.dx.ssa.SsaMethod;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;
import java.util.List;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public class LivenessAnalyzer {
    private SsaBasicBlock blockN;
    private final InterferenceGraph interference;
    private final BitSet liveOutBlocks;
    private NextFunction nextFunction;
    private final int regV;
    private final SsaMethod ssaMeth;
    private int statementIndex;
    private final BitSet visitedBlocks;

    /* renamed from: com.android.dx.ssa.back.LivenessAnalyzer$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$android$dx$ssa$back$LivenessAnalyzer$NextFunction = null;

        static {
            int[] r0 = new int[NextFunction.values().length];
            $SwitchMap$com$android$dx$ssa$back$LivenessAnalyzer$NextFunction = r0;
            r0[NextFunction.LIVE_IN_AT_STATEMENT.ordinal()] = 1;     // Catch: NoSuchFieldError -> L7
        L10:
            $SwitchMap$com$android$dx$ssa$back$LivenessAnalyzer$NextFunction[NextFunction.LIVE_OUT_AT_STATEMENT.ordinal()] = 2;     // Catch: NoSuchFieldError -> L8
        L12:
            $SwitchMap$com$android$dx$ssa$back$LivenessAnalyzer$NextFunction[NextFunction.LIVE_OUT_AT_BLOCK.ordinal()] = 3;     // Catch: NoSuchFieldError -> L9
            return;
        }
    }

    public enum NextFunction extends Enum<NextFunction> {
        private static final /* synthetic */ NextFunction[] $VALUES = null;
        public static final NextFunction DONE = null;
        public static final NextFunction LIVE_IN_AT_STATEMENT = null;
        public static final NextFunction LIVE_OUT_AT_BLOCK = null;
        public static final NextFunction LIVE_OUT_AT_STATEMENT = null;

        static {
            NextFunction r0 = new NextFunction("LIVE_IN_AT_STATEMENT", 0);
            LIVE_IN_AT_STATEMENT = r0;
            NextFunction r1 = new NextFunction("LIVE_OUT_AT_STATEMENT", 1);
            LIVE_OUT_AT_STATEMENT = r1;
            NextFunction r2 = new NextFunction("LIVE_OUT_AT_BLOCK", 2);
            LIVE_OUT_AT_BLOCK = r2;
            NextFunction r3 = new NextFunction("DONE", 3);
            DONE = r3;
            $VALUES = new NextFunction[]{r0, r1, r2, r3};
        }

        NextFunction(String r1, int r2) {
        }

        public static NextFunction valueOf(String r1) {
            return (NextFunction) Enum.valueOf(NextFunction.class, r1);
        }

        public static NextFunction[] values() {
            return (NextFunction[]) $VALUES.clone();
        }
    }

    private LivenessAnalyzer(SsaMethod r2, int r3, InterferenceGraph r4) {
        int r0 = r2.getBlocks().size();
        this.ssaMeth = r2;
        this.regV = r3;
        this.visitedBlocks = new BitSet(r0);
        this.liveOutBlocks = new BitSet(r0);
        this.interference = r4;
    }

    private static void coInterferePhiRegisters(InterferenceGraph r2, RegisterSpec r3, RegisterSpecList r4) {
        int r32 = r3.getReg();
        int r0 = 0;
    L4:
        if (r0 >= r4.size()) goto L6;
        r2.add(r32, r4.get(r0).getReg());
        r0 = r0 + 1;
        goto L4
    }

    private static void coInterferePhis(SsaMethod r11, InterferenceGraph r12) {
        ArrayList<SsaBasicBlock> r112 = r11.getBlocks();
        int r0 = r112.size();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L13;
        SsaBasicBlock r3 = r112.get(r2);
        r2 = r2 + 1;
        List<SsaInsn> r32 = r3.getPhiInsns();
        int r4 = r32.size();
        int r5 = 0;
    L5:
        if (r5 >= r4) goto L3;
        int r6 = 0;
    L7:
        if (r6 >= r4) goto L12;
        if (r5 == r6) goto L11;
        SsaInsn r7 = r32.get(r5);
        SsaInsn r8 = r32.get(r6);
        coInterferePhiRegisters(r12, r7.getResult(), r8.getSources());
        coInterferePhiRegisters(r12, r8.getResult(), r7.getSources());
        r12.add(r7.getResult().getReg(), r8.getResult().getReg());
    L11:
        r6 = r6 + 1;
        goto L7
    L12:
        r5 = r5 + 1;
        goto L5
    }

    public static InterferenceGraph constructInterferenceGraph(SsaMethod r4) {
        int r0 = r4.getRegCount();
        InterferenceGraph r1 = new InterferenceGraph(r0);
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L5;
        new LivenessAnalyzer(r4, r2, r1).run();
        r2 = r2 + 1;
        goto L3
    L5:
        coInterferePhis(r4, r1);
        return r1;
    }

    private void handleTailRecursion() {
    L2:
        NextFunction r0 = this.nextFunction;
        NextFunction r1 = NextFunction.DONE;
        if (r0 == r1) goto L14;
        int r02 = AnonymousClass1.$SwitchMap$com$android$dx$ssa$back$LivenessAnalyzer$NextFunction[r0.ordinal()];
        if (r02 != 1) goto L7;
        this.nextFunction = r1;
        liveInAtStatement();
        goto L2
    L7:
        if (r02 != 2) goto L9;
        this.nextFunction = r1;
        liveOutAtStatement();
        goto L2
    L9:
        if (r02 != 3) goto L2;
        this.nextFunction = r1;
        liveOutAtBlock();
        goto L2
    }

    private void liveInAtStatement() {
        int r0 = this.statementIndex;
        if (r0 != 0) goto L6;
        this.blockN.addLiveIn(this.regV);
        this.liveOutBlocks.or(this.blockN.getPredecessors());
        return;
    L6:
        this.statementIndex = r0 - 1;
        this.nextFunction = NextFunction.LIVE_OUT_AT_STATEMENT;
    }

    private void liveOutAtBlock() {
        if (this.visitedBlocks.get(this.blockN.getIndex()) == true) goto L6;
        this.visitedBlocks.set(this.blockN.getIndex());
        this.blockN.addLiveOut(this.regV);
        this.statementIndex = this.blockN.getInsns().size() - 1;
        this.nextFunction = NextFunction.LIVE_OUT_AT_STATEMENT;
        return;
    }

    private void liveOutAtStatement() {
        SsaInsn r0 = this.blockN.getInsns().get(this.statementIndex);
        RegisterSpec r1 = r0.getResult();
        if (r0.isResultReg(this.regV) == true) goto L8;
        if (r1 == null) goto L6;
        this.interference.add(this.regV, r1.getReg());
    L6:
        this.nextFunction = NextFunction.LIVE_IN_AT_STATEMENT;
        return;
    }

    public void run() {
        Iterator<SsaInsn> r0 = this.ssaMeth.getUseListForRegister(this.regV).iterator();
    L4:
        if (r0.hasNext() == false) goto L16;
        SsaInsn r1 = r0.next();
        this.nextFunction = NextFunction.DONE;
        if ((r1 instanceof PhiInsn) == true) goto L7;
        SsaBasicBlock r2 = r1.getBlock();
        this.blockN = r2;
        int r12 = r2.getInsns().indexOf(r1);
        this.statementIndex = r12;
        if (r12 < 0) goto L14;
        this.nextFunction = NextFunction.LIVE_IN_AT_STATEMENT;
        handleTailRecursion();
        goto L4
    L14:
        GlShader$$ExternalSyntheticBUOutline1.m("insn not found in it's own block");
        return;
    L7:
        Iterator<SsaBasicBlock> r13 = ((PhiInsn) r1).predBlocksForReg(this.regV, this.ssaMeth).iterator();
    L9:
        if (r13.hasNext() == false) goto L4;
        this.blockN = r13.next();
        this.nextFunction = NextFunction.LIVE_OUT_AT_BLOCK;
        handleTailRecursion();
    L16:
        int r02 = this.liveOutBlocks.nextSetBit(0);
        if (r02 < 0) goto L19;
        this.blockN = this.ssaMeth.getBlocks().get(r02);
        this.liveOutBlocks.clear(r02);
        this.nextFunction = NextFunction.LIVE_OUT_AT_BLOCK;
        handleTailRecursion();
        goto L16
    }
}
