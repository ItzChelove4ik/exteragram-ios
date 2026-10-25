package com.android.dx.command.dump;

import com.android.dx.cf.code.BasicBlocker;
import com.android.dx.cf.code.ByteBlock;
import com.android.dx.cf.code.ByteBlockList;
import com.android.dx.cf.code.ByteCatchList;
import com.android.dx.cf.code.BytecodeArray;
import com.android.dx.cf.code.ConcreteMethod;
import com.android.dx.cf.code.Ropper;
import com.android.dx.cf.direct.CodeObserver;
import com.android.dx.cf.direct.DirectClassFile;
import com.android.dx.cf.direct.StdAttributeFactory;
import com.android.dx.cf.iface.Member;
import com.android.dx.cf.iface.Method;
import com.android.dx.rop.code.AccessFlags;
import com.android.dx.rop.code.BasicBlock;
import com.android.dx.rop.code.BasicBlockList;
import com.android.dx.rop.code.DexTranslationAdvice;
import com.android.dx.rop.code.InsnList;
import com.android.dx.rop.code.RopMethod;
import com.android.dx.rop.cst.CstType;
import com.android.dx.ssa.Optimizer;
import com.android.dx.util.ByteArray;
import com.android.dx.util.Hex;
import com.android.dx.util.IntList;
import com.google.android.gms.cast.CastStatusCodes;
import java.io.PrintStream;

/* loaded from: classes4.dex */
public class BlockDumper extends BaseDumper {
    protected DirectClassFile classFile;
    private boolean first;
    private final boolean optimize;
    private final boolean rop;
    protected boolean suppressDump;

    public BlockDumper(byte[] r1, PrintStream r2, String r3, boolean r4, Args r5) {
        super(r1, r2, r3, r5);
        this.rop = r4;
        this.classFile = null;
        this.suppressDump = true;
        this.first = true;
        this.optimize = r5.optimize;
    }

    public static void dump(byte[] r6, PrintStream r7, String r8, boolean r9, Args r10) {
        new BlockDumper(r6, r7, r8, r9, r10).dump();
    }

    private void regularDump(ConcreteMethod r17) {
        BytecodeArray r1 = r17.getCode();
        ByteArray r2 = r1.getBytes();
        ByteBlockList r3 = BasicBlocker.identifyBlocks(r17);
        int r4 = r3.size();
        CodeObserver r5 = new CodeObserver(r2, this);
        this.suppressDump = false;
        int r7 = 0;
        int r8 = 0;
    L4:
        if (r7 >= r4) goto L25;
        ByteBlock r12 = r3.get(r7);
        int r13 = r12.getStart();
        int r14 = r12.getEnd();
        if (r8 >= r13) goto L8;
        parsed(r2, r8, r13 - r8, "dead code " + Hex.u2(r8) + ".." + Hex.u2(r13));
    L8:
        parsed(r2, r13, 0, "block " + Hex.u2(r12.getLabel()) + ": " + Hex.u2(r13) + ".." + Hex.u2(r14));
        changeIndent(1);
    L9:
        if (r13 >= r14) goto L11;
        int r82 = r1.parseInstruction(r13, r5);
        r5.setPreviousOffset(r13);
        r13 = r13 + r82;
        goto L9
    L11:
        IntList r83 = r12.getSuccessors();
        int r9 = r83.size();
        if (r9 != 0) goto L14;
        parsed(r2, r14, 0, "returns");
    L17:
        ByteCatchList r84 = r12.getCatches();
        int r92 = r84.size();
        int r10 = 0;
    L18:
        if (r10 >= r92) goto L24;
        ByteCatchList.Item r11 = r84.get(r10);
        CstType r122 = r11.getExceptionClass();
        StringBuilder r132 = new StringBuilder("catch ");
        if (r122 != CstType.OBJECT) goto L22;
        String r123 = "<any>";
    L23:
        r132.append(r123);
        r132.append(" -> ");
        r132.append(Hex.u2(r11.getHandlerPc()));
        parsed(r2, r14, 0, r132.toString());
        r10 = r10 + 1;
        goto L18
    L22:
        r123 = r122.toHuman();
        goto L23
    L24:
        changeIndent(-1);
        r7 = r7 + 1;
        r8 = r14;
        goto L4
    L14:
        int r102 = 0;
    L15:
        if (r102 >= r9) goto L17;
        parsed(r2, r14, 0, "next " + Hex.u2(r83.get(r102)));
        r102 = r102 + 1;
        goto L15
    L25:
        int r15 = r2.size();
        if (r8 >= r15) goto L28;
        parsed(r2, r8, r15 - r8, "dead code " + Hex.u2(r8) + ".." + Hex.u2(r15));
    L28:
        this.suppressDump = true;
    }

    private void ropDump(ConcreteMethod r17) {
        DexTranslationAdvice r2 = DexTranslationAdvice.THE_ONE;
        ByteArray r3 = r17.getCode().getBytes();
        RopMethod r4 = Ropper.convert(r17, r2, this.classFile.getMethods(), this.dexOptions);
        StringBuilder r5 = new StringBuilder(CastStatusCodes.AUTHENTICATION_FAILED);
        if (this.optimize == false) goto L5;
        boolean r6 = AccessFlags.isStatic(r17.getAccessFlags());
        r4 = Optimizer.optimize(r4, BaseDumper.computeParamWidth(r17, r6), r6, true, r2);
    L5:
        BasicBlockList r1 = r4.getBlocks();
        int[] r22 = r1.getLabelsInOrder();
        r5.append("first " + Hex.u2(r4.getFirstLabel()) + "\n");
        int r62 = r22.length;
        int r10 = 0;
    L6:
        if (r10 >= r62) goto L24;
        int r11 = r22[r10];
        BasicBlock r12 = r1.get(r1.indexOfLabel(r11));
        r5.append("block ");
        r5.append(Hex.u2(r11));
        r5.append("\n");
        IntList r112 = r4.labelToPredecessors(r11);
        int r13 = r112.size();
        int r14 = 0;
    L8:
        if (r14 >= r13) goto L10;
        r5.append("  pred ");
        r5.append(Hex.u2(r112.get(r14)));
        r5.append("\n");
        r14 = r14 + 1;
        goto L8
    L10:
        InsnList r113 = r12.getInsns();
        int r132 = r113.size();
        int r142 = 0;
    L11:
        if (r142 >= r132) goto L13;
        r113.get(r142);
        r5.append("  ");
        r5.append(r113.get(r142).toHuman());
        r5.append("\n");
        r142 = r142 + 1;
        goto L11
    L13:
        IntList r114 = r12.getSuccessors();
        int r133 = r114.size();
        if (r133 != 0) goto L16;
        r5.append("  returns\n");
    L23:
        r10 = r10 + 1;
        goto L6
    L16:
        int r122 = r12.getPrimarySuccessor();
        int r143 = 0;
    L17:
        if (r143 >= r133) goto L23;
        int r15 = r114.get(r143);
        r5.append("  next ");
        r5.append(Hex.u2(r15));
        if (r133 == 1) goto L22;
        if (r15 != r122) goto L22;
        r5.append(" *");
    L22:
        r5.append("\n");
        r143 = r143 + 1;
        goto L17
    L24:
        this.suppressDump = false;
        parsed(r3, 0, r3.size(), r5.toString());
        this.suppressDump = true;
    }

    @Override // com.android.dx.command.dump.BaseDumper, com.android.dx.cf.iface.ParseObserver
    public void changeIndent(int r2) {
        if (this.suppressDump == true) goto L6;
        super.changeIndent(r2);
        return;
    }

    public void dump() {
        ByteArray r1 = new ByteArray(getBytes());
        DirectClassFile r0 = new DirectClassFile(r1, getFilePath(), getStrictParse());
        this.classFile = r0;
        StdAttributeFactory r2 = StdAttributeFactory.THE_ONE;
        r0.setAttributeFactory(r2);
        this.classFile.getMagic();
        DirectClassFile r02 = new DirectClassFile(r1, getFilePath(), getStrictParse());
        r02.setAttributeFactory(r2);
        r02.setObserver(this);
        r02.getMagic();
    }

    @Override // com.android.dx.command.dump.BaseDumper, com.android.dx.cf.iface.ParseObserver
    public void endParsingMember(ByteArray r1, int r2, String r3, String r4, Member r5) {
        if ((r5 instanceof Method) == true) goto L6;
        return;
    L6:
        if (shouldDumpMethod(r3) == true) goto L9;
        return;
    L9:
        if ((r5.getAccessFlags() & 1280) == 0) goto L11;
        return;
    L11:
        ConcreteMethod r12 = new ConcreteMethod((Method) r5, this.classFile, true, true);
        if (this.rop == false) goto L15;
        ropDump(r12);
        return;
    L15:
        regularDump(r12);
    }

    @Override // com.android.dx.command.dump.BaseDumper, com.android.dx.cf.iface.ParseObserver
    public void parsed(ByteArray r2, int r3, int r4, String r5) {
        if (this.suppressDump == true) goto L6;
        super.parsed(r2, r3, r4, r5);
        return;
    }

    public boolean shouldDumpMethod(String r1) {
        String r0 = this.args.method;
        if (r0 != null) goto L5;
        return true;
    L5:
        if (r0.equals(r1) == true) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // com.android.dx.command.dump.BaseDumper, com.android.dx.cf.iface.ParseObserver
    public void startParsingMember(ByteArray r4, int r5, String r6, String r7) {
        if (r7.indexOf(40) >= 0) goto L6;
        return;
    L6:
        if (shouldDumpMethod(r6) == true) goto L8;
        return;
    L8:
        this.suppressDump = false;
        if (this.first == false) goto L11;
        this.first = false;
    L12:
        parsed(r4, r5, 0, "method " + r6 + " " + r7);
        this.suppressDump = true;
        return;
    L11:
        parsed(r4, r5, 0, "\n");
        goto L12
    }
}
