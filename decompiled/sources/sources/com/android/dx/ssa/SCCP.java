package com.android.dx.ssa;

import com.android.dx.rop.code.CstInsn;
import com.android.dx.rop.code.Insn;
import com.android.dx.rop.code.PlainInsn;
import com.android.dx.rop.code.RegisterSpec;
import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.rop.code.Rop;
import com.android.dx.rop.code.Rops;
import com.android.dx.rop.cst.Constant;
import com.android.dx.rop.cst.CstInteger;
import com.android.dx.rop.cst.TypedConstant;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public class SCCP {
    private static final int CONSTANT = 1;
    private static final int TOP = 0;
    private static final int VARYING = 2;
    private final ArrayList<SsaInsn> branchWorklist;
    private final ArrayList<SsaBasicBlock> cfgPhiWorklist;
    private final ArrayList<SsaBasicBlock> cfgWorklist;
    private final BitSet executableBlocks;
    private final Constant[] latticeConstants;
    private final int[] latticeValues;
    private final int regCount;
    private final SsaMethod ssaMeth;
    private final ArrayList<SsaInsn> ssaWorklist;
    private final ArrayList<SsaInsn> varyingWorklist;

    private SCCP(SsaMethod r4) {
        this.ssaMeth = r4;
        int r0 = r4.getRegCount();
        this.regCount = r0;
        this.latticeValues = new int[r0];
        this.latticeConstants = new Constant[r0];
        this.cfgWorklist = new ArrayList();
        this.cfgPhiWorklist = new ArrayList();
        this.executableBlocks = new BitSet(r4.getBlocks().size());
        this.ssaWorklist = new ArrayList();
        this.varyingWorklist = new ArrayList();
        this.branchWorklist = new ArrayList();
        int r02 = 0;
    L4:
        if (r02 >= this.regCount) goto L6;
        this.latticeValues[r02] = 0;
        this.latticeConstants[r02] = null;
        r02 = r02 + 1;
        goto L4
    }

    private void addBlockToWorklist(SsaBasicBlock r3) {
        if (this.executableBlocks.get(r3.getIndex()) == true) goto L6;
        this.cfgWorklist.add(r3);
        this.executableBlocks.set(r3.getIndex());
        return;
    L6:
        this.cfgPhiWorklist.add(r3);
    }

    private void addUsersToWorklist(int r3, int r4) {
        SsaMethod r0 = this.ssaMeth;
        if (r4 != 2) goto L8;
        Iterator<SsaInsn> r32 = r0.getUseListForRegister(r3).iterator();
    L6:
        if (r32.hasNext() == false) goto L12;
        SsaInsn r42 = r32.next();
        this.varyingWorklist.add(r42);
        goto L6
    L12:
        return;
    L8:
        Iterator<SsaInsn> r33 = r0.getUseListForRegister(r3).iterator();
    L10:
        if (r33.hasNext() == false) goto L15;
        SsaInsn r43 = r33.next();
        this.ssaWorklist.add(r43);
        goto L10
    }

    private static String latticeValName(int r1) {
        if (r1 != 0) goto L4;
        return "TOP";
    L4:
        if (r1 != 1) goto L6;
        return "CONSTANT";
    L6:
        if (r1 == 2) goto L9;
        return "UNKNOWN";
    L9:
        return "VARYING";
    }

    public static void process(SsaMethod r1) {
        new SCCP(r1).run();
    }

    private void replaceBranches() {
        ArrayList<SsaInsn> r0 = this.branchWorklist;
        int r1 = r0.size();
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L15;
        SsaInsn r4 = r0.get(r3);
        r3 = r3 + 1;
        SsaInsn r42 = r4;
        SsaBasicBlock r5 = r42.getBlock();
        int r6 = r5.getSuccessorList().size();
        int r8 = 0;
        int r9 = -1;
    L5:
        if (r8 >= r6) goto L11;
        int r10 = r5.getSuccessorList().get(r8);
        if (this.executableBlocks.get(r10) == true) goto L9;
        r9 = r10;
    L9:
        r8 = r8 + 1;
        goto L5
    L11:
        if (r6 != 2) goto L3;
        if (r9 == (-1)) goto L3;
        r5.replaceLastInsn(new PlainInsn(Rops.GOTO, r42.getOriginalRopInsn().getPosition(), null, RegisterSpecList.EMPTY));
        r5.removeSuccessor(r9);
        goto L3
    }

    private void replaceConstants() {
        int r0 = 0;
    L4:
        if (r0 >= this.regCount) goto L22;
        if (this.latticeValues[r0] != 1) goto L21;
        if ((this.latticeConstants[r0] instanceof TypedConstant) == false) goto L21;
        SsaInsn r1 = this.ssaMeth.getDefinitionForRegister(r0);
        if (r1.getResult().getTypeBearer().isConstant() == true) goto L21;
        r1.setResult(r1.getResult().withType((TypedConstant) this.latticeConstants[r0]));
        Iterator<SsaInsn> r12 = this.ssaMeth.getUseListForRegister(r0).iterator();
    L16:
        if (r12.hasNext() == false) goto L21;
        SsaInsn r2 = r12.next();
        if (r2.isPhiOrMove() == true) goto L16;
        NormalSsaInsn r3 = (NormalSsaInsn) r2;
        RegisterSpecList r22 = r2.getSources();
        int r4 = r22.indexOfRegister(r0);
        r3.changeOneSource(r4, r22.get(r4).withType((TypedConstant) this.latticeConstants[r0]));
    L21:
        r0 = r0 + 1;
        goto L4
    }

    private void run() {
        addBlockToWorklist(this.ssaMeth.getEntryBlock());
    L4:
        if (this.cfgWorklist.isEmpty() == false) goto L15;
        if (this.cfgPhiWorklist.isEmpty() == false) goto L15;
        if (this.ssaWorklist.isEmpty() == false) goto L15;
        if (this.varyingWorklist.isEmpty() == false) goto L15;
        replaceConstants();
        replaceBranches();
        return;
    L15:
        if (this.cfgWorklist.isEmpty() == true) goto L18;
        simulateBlock(this.cfgWorklist.remove(this.cfgWorklist.size() - 1));
    L18:
        if (this.cfgPhiWorklist.isEmpty() == true) goto L21;
        simulatePhiBlock(this.cfgPhiWorklist.remove(this.cfgPhiWorklist.size() - 1));
    L21:
        if (this.varyingWorklist.isEmpty() == true) goto L30;
        SsaInsn r0 = this.varyingWorklist.remove(this.varyingWorklist.size() - 1);
        if (this.executableBlocks.get(r0.getBlock().getIndex()) == false) goto L21;
        if ((r0 instanceof PhiInsn) == true) goto L27;
        simulateStmt(r0);
        goto L21
    L27:
        simulatePhi((PhiInsn) r0);
    L30:
        if (this.ssaWorklist.isEmpty() == true) goto L4;
        SsaInsn r02 = this.ssaWorklist.remove(this.ssaWorklist.size() - 1);
        if (this.executableBlocks.get(r02.getBlock().getIndex()) == false) goto L30;
        if ((r02 instanceof PhiInsn) == true) goto L36;
        simulateStmt(r02);
        goto L30
    L36:
        simulatePhi((PhiInsn) r02);
        goto L30
    }

    private boolean setLatticeValueTo(int r4, int r5, Constant r6) {
        int[] r0 = this.latticeValues;
        if (r5 == 1) goto L10;
        if (r0[r4] == r5) goto L8;
        r0[r4] = r5;
        return true;
    L8:
        return false;
    L10:
        if (r0[r4] == r5) goto L12;
    L15:
        this.latticeValues[r4] = r5;
        this.latticeConstants[r4] = r6;
        return true;
    L12:
        if (this.latticeConstants[r4].equals(r6) == false) goto L15;
        return false;
    }

    private void simulateBlock(SsaBasicBlock r5) {
        ArrayList<SsaInsn> r52 = r5.getInsns();
        int r0 = r52.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        SsaInsn r2 = r52.get(r1);
        r1 = r1 + 1;
        SsaInsn r22 = r2;
        if ((r22 instanceof PhiInsn) == true) goto L6;
        simulateStmt(r22);
        goto L3
    L6:
        simulatePhi((PhiInsn) r22);
        goto L3
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v11, types: [com.android.dx.rop.cst.Constant[]] */
    /* JADX WARN: Type inference failed for: r2v12 */
    private void simulateBranch(SsaInsn r10) {
        Rop r0 = r10.getOpcode();
        RegisterSpecList r1 = r10.getSources();
        int r5 = 0;
        if (r0.getBranchingness() != 4) goto L63;
        RegisterSpec r2 = r1.get(0);
        int r3 = r2.getReg();
        Constant r6 = null;
        if (this.ssaMeth.isRegALocal(r2) == false) goto L7;
    L9:
        CstInteger r22 = null;
    L11:
        if (r1.size() != 2) goto L18;
        RegisterSpec r32 = r1.get(1);
        int r7 = r32.getReg();
        if (this.ssaMeth.isRegALocal(r32) == true) goto L18;
        if (this.latticeValues[r7] != 1) goto L18;
        r6 = this.latticeConstants[r7];
    L18:
        if (r22 != null) goto L20;
    L42:
        if (r22 == null) goto L63;
        if (r6 == null) goto L63;
        if (r22.getBasicType() != 6) goto L63;
        int r12 = r22.getValue();
        int r23 = ((CstInteger) r6).getValue();
        switch(r0.getOpcode()) {
            case 7: goto L61;
            case 8: goto L59;
            case 9: goto L57;
            case 10: goto L55;
            case 11: goto L53;
            case 12: goto L51;
            default: goto L49;
        };
    L49:
        GlShader$$ExternalSyntheticBUOutline1.m("Unexpected op");
        return;
    L51:
        if (r12 > r23) goto L29;
    L30:
        boolean r02 = false;
    L31:
        boolean r13 = true;
    L64:
        SsaBasicBlock r24 = r10.getBlock();
        if (r13 == false) goto L72;
        if (r02 == false) goto L68;
        int r03 = r24.getSuccessorList().get(1);
    L69:
        addBlockToWorklist(this.ssaMeth.getBlocks().get(r03));
        this.branchWorklist.add(r10);
        return;
    L68:
        r03 = r24.getSuccessorList().get(0);
    L72:
        if (r5 >= r24.getSuccessorList().size()) goto L74;
        addBlockToWorklist(this.ssaMeth.getBlocks().get(r24.getSuccessorList().get(r5)));
        r5 = r5 + 1;
        goto L72
    L74:
        return;
    L29:
        r02 = true;
        goto L31
    L53:
        if (r12 > r23) goto L30;
    L55:
        if (r12 < r23) goto L30;
    L57:
        if (r12 >= r23) goto L30;
    L59:
        if (r12 == r23) goto L30;
    L61:
        if (r12 != r23) goto L30;
    L20:
        if (r1.size() != 1) goto L42;
        if (r22.getBasicType() != 6) goto L63;
        int r14 = r22.getValue();
        switch(r0.getOpcode()) {
            case 7: goto L40;
            case 8: goto L38;
            case 9: goto L36;
            case 10: goto L34;
            case 11: goto L32;
            case 12: goto L28;
            default: goto L26;
        };
    L26:
        GlShader$$ExternalSyntheticBUOutline1.m("Unexpected op");
        return;
    L28:
        if (r14 <= 0) goto L30;
    L32:
        if (r14 > 0) goto L30;
    L34:
        if (r14 < 0) goto L30;
    L36:
        if (r14 >= 0) goto L30;
    L38:
        if (r14 == 0) goto L30;
    L40:
        if (r14 != 0) goto L30;
    L7:
        if (this.latticeValues[r3] != 1) goto L9;
        r22 = this.latticeConstants[r3];
    L63:
        r02 = false;
        r13 = false;
        goto L64
    }

    private Constant simulateMath(SsaInsn r8, int r9) {
        Insn r0 = r8.getOriginalRopInsn();
        int r1 = r8.getOpcode().getOpcode();
        RegisterSpecList r82 = r8.getSources();
        boolean r2 = false;
        int r3 = r82.get(0).getReg();
        if (this.latticeValues[r3] == 1) goto L5;
        Constant r32 = null;
    L7:
        if (r82.size() != 1) goto L9;
        Constant r7 = ((CstInsn) r0).getConstant();
    L13:
        if (r32 == null) goto L46;
        if (r7 == null) goto L46;
        if (r9 == 6) goto L19;
        return null;
    L19:
        int r92 = ((CstInteger) r32).getValue();
        int r72 = ((CstInteger) r7).getValue();
        switch(r1) {
            case 14: goto L41;
            case 15: goto L38;
            case 16: goto L36;
            case 17: goto L33;
            case 18: goto L29;
            case 19: goto L21;
            case 20: goto L28;
            case 21: goto L27;
            case 22: goto L26;
            case 23: goto L25;
            case 24: goto L24;
            case 25: goto L23;
            default: goto L21;
        };
    L21:
        GlShader$$ExternalSyntheticBUOutline1.m("Unexpected op");
        return null;
    L23:
        int r73 = r92 >>> r72;
    L42:
        if (r2 == false) goto L45;
        return null;
    L45:
        return CstInteger.make(r73);
    L24:
        r73 = r92 >> r72;
        goto L42
    L25:
        r73 = r92 << r72;
        goto L42
    L26:
        r73 = r72 ^ r92;
        goto L42
    L27:
        r73 = r72 | r92;
        goto L42
    L28:
        r73 = r72 & r92;
        goto L42
    L29:
        if (r72 != 0) goto L31;
    L30:
        r73 = 0;
        r2 = true;
        goto L42
    L31:
        int r93 = r92 % r72;
    L32:
        r73 = r93;
        goto L42
    L33:
        if (r72 == 0) goto L30;
        r93 = r92 / r72;
        goto L32
    L36:
        r93 = r92 * r72;
        goto L32
    L41:
        r93 = r92 + r72;
        goto L32
    L38:
        if (r82.size() != 1) goto L40;
        r73 = r72 - r92;
        goto L42
    L40:
        r93 = r92 - r72;
    L46:
        return null;
    L9:
        int r02 = r82.get(1).getReg();
        if (this.latticeValues[r02] == 1) goto L12;
        r7 = null;
        goto L13
    L12:
        r7 = this.latticeConstants[r02];
        goto L13
    L5:
        r32 = this.latticeConstants[r3];
        goto L7
    }

    private void simulatePhi(PhiInsn r12) {
        int r0 = r12.getResult().getReg();
        int r2 = 2;
        if (this.latticeValues[r0] == 2) goto L32;
        RegisterSpecList r1 = r12.getSources();
        int r3 = r1.size();
        int r4 = 0;
        Constant r6 = null;
        int r5 = 0;
    L6:
        if (r4 >= r3) goto L20;
        int r7 = r12.predBlockIndexForSourcesIndex(r4);
        int r8 = r1.get(r4).getReg();
        int r9 = this.latticeValues[r8];
        if (this.executableBlocks.get(r7) == false) goto L18;
        if (r9 != 1) goto L19;
        Constant[] r92 = this.latticeConstants;
        if (r6 != null) goto L16;
        r6 = r92[r8];
        r5 = 1;
        goto L18
    L16:
        if (r92[r8].equals(r6) == true) goto L18;
    L22:
        if (setLatticeValueTo(r0, r2, r6) == false) goto L31;
        addUsersToWorklist(r0, r2);
        return;
    L31:
        return;
    L19:
        r2 = r9;
    L18:
        r4 = r4 + 1;
        goto L6
    L20:
        r2 = r5;
        goto L22
    }

    private void simulatePhiBlock(SsaBasicBlock r5) {
        ArrayList<SsaInsn> r52 = r5.getInsns();
        int r0 = r52.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L7;
        SsaInsn r2 = r52.get(r1);
        r1 = r1 + 1;
        SsaInsn r22 = r2;
        if ((r22 instanceof PhiInsn) == false) goto L10;
        simulatePhi((PhiInsn) r22);
        goto L3
    L10:
        return;
    }

    private void simulateStmt(SsaInsn r8) {
        Insn r0 = r8.getOriginalRopInsn();
        int r2 = 1;
        if (r0.getOpcode().getBranchingness() == 1) goto L5;
    L6:
        simulateBranch(r8);
    L7:
        int r1 = r8.getOpcode().getOpcode();
        RegisterSpec r3 = r8.getResult();
        if (r3 == null) goto L10;
    L14:
        int r5 = r3.getReg();
        if (r1 == 2) goto L32;
        if (r1 != 5) goto L19;
        Constant r82 = ((CstInsn) r0).getConstant();
    L36:
        if (setLatticeValueTo(r5, r2, r82) == false) goto L40;
        addUsersToWorklist(r5, r2);
        return;
    L40:
        return;
    L19:
        if (r1 == 56) goto L27;
        switch(r1) {
            case 14: goto L23;
            case 15: goto L23;
            case 16: goto L23;
            case 17: goto L23;
            case 18: goto L23;
            default: goto L21;
        };
    L21:
        switch(r1) {
            case 20: goto L23;
            case 21: goto L23;
            case 22: goto L23;
            case 23: goto L23;
            case 24: goto L23;
            case 25: goto L23;
            default: goto L34;
        };
    L34:
        r82 = null;
    L26:
        r2 = 2;
    L23:
        r82 = simulateMath(r8, r3.getBasicType());
        if (r82 == null) goto L26;
    L27:
        int r83 = this.latticeValues[r5];
        if (r83 != 1) goto L34;
        r2 = r83;
        r82 = this.latticeConstants[r5];
        goto L36
    L32:
        if (r8.getSources().size() != 1) goto L34;
        int r84 = r8.getSources().get(0).getReg();
        r2 = this.latticeValues[r84];
        r82 = this.latticeConstants[r84];
        goto L36
    L10:
        if (r1 != 17) goto L12;
    L13:
        r3 = r8.getBlock().getPrimarySuccessor().getInsns().get(0).getResult();
        goto L14
    L12:
        if (r1 == 18) goto L13;
        return;
    L5:
        if (r0.getOpcode().isCallLike() == false) goto L7;
        goto L6
    }
}
