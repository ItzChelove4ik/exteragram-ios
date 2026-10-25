package com.android.dx.command.dump;

import com.android.dx.cf.code.ConcreteMethod;
import com.android.dx.cf.code.Ropper;
import com.android.dx.cf.iface.Member;
import com.android.dx.cf.iface.Method;
import com.android.dx.rop.code.AccessFlags;
import com.android.dx.rop.code.DexTranslationAdvice;
import com.android.dx.rop.code.RopMethod;
import com.android.dx.ssa.Optimizer;
import com.android.dx.ssa.SsaBasicBlock;
import com.android.dx.ssa.SsaInsn;
import com.android.dx.ssa.SsaMethod;
import com.android.dx.util.ByteArray;
import com.android.dx.util.Hex;
import com.android.dx.util.IntList;
import com.google.android.gms.cast.CastStatusCodes;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumSet;

/* loaded from: classes4.dex */
public class SsaDumper extends BlockDumper {
    private SsaDumper(byte[] r7, PrintStream r8, String r9, Args r10) {
        super(r7, r8, r9, true, r10);
    }

    public static void dump(byte[] r1, PrintStream r2, String r3, Args r4) {
        new SsaDumper(r1, r2, r3, r4).dump();
    }

    @Override // com.android.dx.command.dump.BlockDumper, com.android.dx.command.dump.BaseDumper, com.android.dx.cf.iface.ParseObserver
    public void endParsingMember(ByteArray r17, int r18, String r19, String r20, Member r21) {
        if ((r21 instanceof Method) == true) goto L6;
        return;
    L6:
        if (shouldDumpMethod(r19) == true) goto L9;
        return;
    L9:
        if ((r21.getAccessFlags() & 1280) == 0) goto L11;
        return;
    L11:
        ConcreteMethod r2 = new ConcreteMethod((Method) r21, this.classFile, true, true);
        DexTranslationAdvice r9 = DexTranslationAdvice.THE_ONE;
        RopMethod r5 = Ropper.convert(r2, r9, this.classFile.getMethods(), this.dexOptions);
        boolean r7 = AccessFlags.isStatic(r2.getAccessFlags());
        int r6 = BaseDumper.computeParamWidth(r2, r7);
        String r1 = this.args.ssaStep;
        if (r1 != null) goto L15;
        SsaMethod r12 = Optimizer.debugNoRegisterAllocation(r5, r6, r7, true, r9, EnumSet.allOf(Optimizer.OptionalStep.class));
    L27:
        StringBuilder r22 = new StringBuilder(CastStatusCodes.AUTHENTICATION_FAILED);
        r22.append("first ");
        r22.append(Hex.u2(r12.blockIndexToRopLabel(r12.getEntryBlockIndex())));
        r22.append('\n');
        ArrayList r52 = (ArrayList) r12.getBlocks().clone();
        Collections.sort(r52, SsaBasicBlock.LABEL_COMPARATOR);
        int r62 = r52.size();
        int r8 = 0;
    L28:
        if (r8 >= r62) goto L47;
        Object r92 = r52.get(r8);
        r8 = r8 + 1;
        SsaBasicBlock r93 = (SsaBasicBlock) r92;
        r22.append("block ");
        r22.append(Hex.u2(r93.getRopLabel()));
        r22.append('\n');
        BitSet r10 = r93.getPredecessors();
        int r11 = r10.nextSetBit(0);
    L30:
        if (r11 < 0) goto L32;
        r22.append("  pred ");
        r22.append(Hex.u2(r12.blockIndexToRopLabel(r11)));
        r22.append('\n');
        r11 = r10.nextSetBit(r11 + 1);
        goto L30
    L32:
        r22.append("  live in:" + r93.getLiveInRegs());
        r22.append("\n");
        ArrayList<SsaInsn> r112 = r93.getInsns();
        int r122 = r112.size();
        int r13 = 0;
    L33:
        if (r13 >= r122) goto L36;
        SsaInsn r14 = r112.get(r13);
        r13 = r13 + 1;
        r22.append("  ");
        r22.append(r14.toHuman());
        r22.append('\n');
        goto L33
    L36:
        if (r93.getSuccessors().cardinality() != 0) goto L38;
        r22.append("  returns\n");
    L46:
        r22.append("  live out:" + r93.getLiveOutRegs());
        r22.append("\n");
        goto L28
    L38:
        int r113 = r93.getPrimarySuccessorRopLabel();
        IntList r123 = r93.getRopLabelSuccessorList();
        int r132 = r123.size();
        int r142 = 0;
    L39:
        if (r142 >= r132) goto L46;
        r22.append("  next ");
        r22.append(Hex.u2(r123.get(r142)));
        if (r132 == 1) goto L45;
        if (r113 != r123.get(r142)) goto L45;
        r22.append(" *");
    L45:
        r22.append('\n');
        r142 = r142 + 1;
        goto L39
    L47:
        this.suppressDump = false;
        parsed(r17, 0, r17.size(), r22.toString());
        this.suppressDump = true;
        return;
    L15:
        if ("edge-split".equals(r1) == false) goto L18;
        r12 = Optimizer.debugEdgeSplit(r5, r6, r7, true, r9);
        goto L27
    L18:
        if ("phi-placement".equals(this.args.ssaStep) == false) goto L21;
        r12 = Optimizer.debugPhiPlacement(r5, r6, r7, true, r9);
        goto L27
    L21:
        if ("renaming".equals(this.args.ssaStep) == false) goto L24;
        r12 = Optimizer.debugRenaming(r5, r6, r7, true, r9);
        goto L27
    L24:
        if ("dead-code".equals(this.args.ssaStep) == false) goto L26;
        r12 = Optimizer.debugDeadCodeRemover(r5, r6, r7, true, r9);
        goto L27
    L26:
        r12 = null;
        goto L27
    }
}
