package com.android.dx.rop.code;

import com.android.dx.rop.code.Insn;
import com.android.dx.rop.type.StdTypeList;
import com.android.dx.util.Hex;
import com.android.dx.util.IntList;
import com.android.dx.util.LabeledList;
import okio.Buffer$$ExternalSyntheticBUOutline4;

/* loaded from: classes.dex */
public final class BasicBlockList extends LabeledList {
    private int regCount;

    public static class RegCountVisitor implements Insn.Visitor {
        private int regCount;

        public RegCountVisitor() {
            this.regCount = 0;
        }

        private void processReg(RegisterSpec r2) {
            int r22 = r2.getNextReg();
            if (r22 <= this.regCount) goto L6;
            this.regCount = r22;
            return;
        }

        private void visit(Insn r4) {
            RegisterSpec r0 = r4.getResult();
            if (r0 == null) goto L5;
            processReg(r0);
        L5:
            RegisterSpecList r42 = r4.getSources();
            int r02 = r42.size();
            int r1 = 0;
        L6:
            if (r1 >= r02) goto L8;
            processReg(r42.get(r1));
            r1 = r1 + 1;
            goto L6
        }

        public int getRegCount() {
            return this.regCount;
        }

        @Override // com.android.dx.rop.code.Insn.Visitor
        public void visitFillArrayDataInsn(FillArrayDataInsn r1) {
            visit(r1);
        }

        @Override // com.android.dx.rop.code.Insn.Visitor
        public void visitInvokePolymorphicInsn(InvokePolymorphicInsn r1) {
            visit(r1);
        }

        @Override // com.android.dx.rop.code.Insn.Visitor
        public void visitPlainCstInsn(PlainCstInsn r1) {
            visit(r1);
        }

        @Override // com.android.dx.rop.code.Insn.Visitor
        public void visitPlainInsn(PlainInsn r1) {
            visit(r1);
        }

        @Override // com.android.dx.rop.code.Insn.Visitor
        public void visitSwitchInsn(SwitchInsn r1) {
            visit(r1);
        }

        @Override // com.android.dx.rop.code.Insn.Visitor
        public void visitThrowingCstInsn(ThrowingCstInsn r1) {
            visit(r1);
        }

        @Override // com.android.dx.rop.code.Insn.Visitor
        public void visitThrowingInsn(ThrowingInsn r1) {
            visit(r1);
        }
    }

    public BasicBlockList(int r1) {
        super(r1);
        this.regCount = -1;
    }

    private BasicBlockList(BasicBlockList r1) {
        super(r1);
        this.regCount = r1.regCount;
    }

    public boolean catchesEqual(BasicBlock r7, BasicBlock r8) {
        if (StdTypeList.equalContents(r7.getExceptionHandlerTypes(), r8.getExceptionHandlerTypes()) == true) goto L5;
        return false;
    L5:
        IntList r6 = r7.getSuccessors();
        IntList r1 = r8.getSuccessors();
        int r2 = r6.size();
        int r72 = r7.getPrimarySuccessor();
        int r82 = r8.getPrimarySuccessor();
        if (r72 == (-1)) goto L8;
        if (r82 == (-1)) goto L8;
    L10:
        int r3 = 0;
    L11:
        if (r3 >= r2) goto L19;
        int r4 = r6.get(r3);
        int r5 = r1.get(r3);
        if (r4 != r72) goto L16;
        if (r5 == r82) goto L18;
        return false;
    L18:
        r3 = r3 + 1;
        goto L11
    L16:
        if (r4 == r5) goto L18;
        return false;
    L19:
        return true;
    L8:
        if (r72 == r82) goto L10;
        return false;
    }

    public void forEachInsn(Insn.Visitor r4) {
        int r0 = size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        get(r1).getInsns().forEach(r4);
        r1 = r1 + 1;
        goto L3
    }

    public BasicBlock get(int r1) {
        return (BasicBlock) get0(r1);
    }

    public int getEffectiveInstructionCount() {
        int r0 = size();
        int r2 = 0;
        int r3 = 0;
    L3:
        if (r2 >= r0) goto L13;
        BasicBlock r4 = (BasicBlock) getOrNull0(r2);
        if (r4 == null) goto L12;
        InsnList r42 = r4.getInsns();
        int r5 = r42.size();
        int r6 = 0;
    L7:
        if (r6 >= r5) goto L12;
        if (r42.get(r6).getOpcode().getOpcode() == 54) goto L11;
        r3 = r3 + 1;
    L11:
        r6 = r6 + 1;
    L12:
        r2 = r2 + 1;
        goto L3
    L13:
        return r3;
    }

    public int getInstructionCount() {
        int r0 = size();
        int r1 = 0;
        int r2 = 0;
    L3:
        if (r1 >= r0) goto L8;
        BasicBlock r3 = (BasicBlock) getOrNull0(r1);
        if (r3 == null) goto L7;
        r2 = r2 + r3.getInsns().size();
    L7:
        r1 = r1 + 1;
        goto L3
    L8:
        return r2;
    }

    public BasicBlockList getMutableCopy() {
        return new BasicBlockList(this);
    }

    public int getRegCount() {
        if (this.regCount != (-1)) goto L6;
        RegCountVisitor r0 = new RegCountVisitor();
        forEachInsn(r0);
        this.regCount = r0.getRegCount();
    L6:
        return this.regCount;
    }

    public BasicBlock labelToBlock(int r2) {
        int r0 = indexOfLabel(r2);
        if (r0 >= 0) goto L5;
        Buffer$$ExternalSyntheticBUOutline4.m("no such label: ", Hex.u2(r2));
        return null;
    L5:
        return get(r0);
    }

    public BasicBlock preferredSuccessorOf(BasicBlock r5) {
        int r0 = r5.getPrimarySuccessor();
        IntList r52 = r5.getSuccessors();
        int r1 = r52.size();
        if (r1 != 0) goto L5;
        return null;
    L5:
        if (r1 == 1) goto L13;
        if (r0 == (-1)) goto L11;
        return labelToBlock(r0);
    L11:
        return labelToBlock(r52.get(0));
    L13:
        return labelToBlock(r52.get(0));
    }

    public void set(int r1, BasicBlock r2) {
        super.set(r1, r2);
        this.regCount = -1;
    }

    public BasicBlockList withRegisterOffset(int r5) {
        int r0 = size();
        BasicBlockList r1 = new BasicBlockList(r0);
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L9;
        BasicBlock r3 = (BasicBlock) get0(r2);
        if (r3 == null) goto L7;
        r1.set(r2, r3.withRegisterOffset(r5));
    L7:
        r2 = r2 + 1;
        goto L3
    L9:
        if (isImmutable() == false) goto L11;
        r1.setImmutable();
    L11:
        return r1;
    }
}
