package com.android.dx.command.dump;

import com.android.dx.cf.code.ConcreteMethod;
import com.android.dx.cf.code.Ropper;
import com.android.dx.cf.direct.DirectClassFile;
import com.android.dx.cf.direct.StdAttributeFactory;
import com.android.dx.cf.iface.Member;
import com.android.dx.cf.iface.Method;
import com.android.dx.cf.iface.ParseObserver;
import com.android.dx.dex.DexOptions;
import com.android.dx.rop.code.AccessFlags;
import com.android.dx.rop.code.BasicBlock;
import com.android.dx.rop.code.BasicBlockList;
import com.android.dx.rop.code.DexTranslationAdvice;
import com.android.dx.rop.code.RopMethod;
import com.android.dx.ssa.Optimizer;
import com.android.dx.util.ByteArray;
import com.android.dx.util.Hex;
import com.android.dx.util.IntList;

/* loaded from: classes4.dex */
public class DotDumper implements ParseObserver {
    private final Args args;
    private final byte[] bytes;
    private DirectClassFile classFile;
    private final DexOptions dexOptions;
    private final String filePath;
    private final boolean optimize;
    private final boolean strictParse;

    public DotDumper(byte[] r1, String r2, Args r3) {
        this.bytes = r1;
        this.filePath = r2;
        this.strictParse = r3.strictParse;
        this.optimize = r3.optimize;
        this.args = r3;
        this.dexOptions = new DexOptions();
    }

    public static void dump(byte[] r1, String r2, Args r3) {
        new DotDumper(r1, r2, r3).run();
    }

    private void run() {
        ByteArray r0 = new ByteArray(this.bytes);
        DirectClassFile r1 = new DirectClassFile(r0, this.filePath, this.strictParse);
        this.classFile = r1;
        StdAttributeFactory r2 = StdAttributeFactory.THE_ONE;
        r1.setAttributeFactory(r2);
        this.classFile.getMagic();
        DirectClassFile r12 = new DirectClassFile(r0, this.filePath, this.strictParse);
        r12.setAttributeFactory(r2);
        r12.setObserver(this);
        r12.getMagic();
    }

    @Override // com.android.dx.cf.iface.ParseObserver
    public void changeIndent(int r1) {
    }

    @Override // com.android.dx.cf.iface.ParseObserver
    public void endParsingMember(ByteArray r11, int r12, String r13, String r14, Member r15) {
        if ((r15 instanceof Method) == true) goto L6;
        return;
    L6:
        if (shouldDumpMethod(r13) == true) goto L8;
        return;
    L8:
        ConcreteMethod r112 = new ConcreteMethod((Method) r15, this.classFile, true, true);
        DexTranslationAdvice r122 = DexTranslationAdvice.THE_ONE;
        RopMethod r152 = Ropper.convert(r112, r122, this.classFile.getMethods(), this.dexOptions);
        if (this.optimize == false) goto L11;
        boolean r10 = AccessFlags.isStatic(r112.getAccessFlags());
        r152 = Optimizer.optimize(r152, BaseDumper.computeParamWidth(r112, r10), r10, true, r122);
    L11:
        System.out.println("digraph " + r13 + "{");
        System.out.println("\tfirst -> n" + Hex.u2(r152.getFirstLabel()) + ";");
        BasicBlockList r102 = r152.getBlocks();
        int r113 = r102.size();
        int r153 = 0;
    L12:
        if (r153 >= r113) goto L28;
        BasicBlock r0 = r102.get(r153);
        int r1 = r0.getLabel();
        IntList r2 = r0.getSuccessors();
        if (r2.size() != 0) goto L17;
        System.out.println("\tn" + Hex.u2(r1) + " -> returns;");
    L27:
        r153 = r153 + 1;
        goto L12
    L17:
        if (r2.size() != 1) goto L19;
        System.out.println("\tn" + Hex.u2(r1) + " -> n" + Hex.u2(r2.get(0)) + ";");
        goto L27
    L19:
        System.out.print("\tn" + Hex.u2(r1) + " -> {");
        int r3 = 0;
    L21:
        if (r3 >= r2.size()) goto L26;
        int r6 = r2.get(r3);
        if (r6 == r0.getPrimarySuccessor()) goto L25;
        System.out.print(" n" + Hex.u2(r6) + " ");
    L25:
        r3 = r3 + 1;
        goto L21
    L26:
        System.out.println("};");
        System.out.println("\tn" + Hex.u2(r1) + " -> n" + Hex.u2(r0.getPrimarySuccessor()) + " [label=\"primary\"];");
        goto L27
    L28:
        System.out.println("}");
    }

    @Override // com.android.dx.cf.iface.ParseObserver
    public void parsed(ByteArray r1, int r2, int r3, String r4) {
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

    @Override // com.android.dx.cf.iface.ParseObserver
    public void startParsingMember(ByteArray r1, int r2, String r3, String r4) {
    }
}
