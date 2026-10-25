package com.android.dx.dex.code;

import com.android.dx.dex.DexOptions;
import com.android.dx.rop.code.BasicBlock;
import com.android.dx.rop.code.BasicBlockList;
import com.android.dx.rop.code.FillArrayDataInsn;
import com.android.dx.rop.code.Insn;
import com.android.dx.rop.code.InvokePolymorphicInsn;
import com.android.dx.rop.code.LocalVariableInfo;
import com.android.dx.rop.code.PlainCstInsn;
import com.android.dx.rop.code.PlainInsn;
import com.android.dx.rop.code.RegisterSpec;
import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.rop.code.RegisterSpecSet;
import com.android.dx.rop.code.Rop;
import com.android.dx.rop.code.RopMethod;
import com.android.dx.rop.code.SourcePosition;
import com.android.dx.rop.code.SwitchInsn;
import com.android.dx.rop.code.ThrowingCstInsn;
import com.android.dx.rop.code.ThrowingInsn;
import com.android.dx.rop.cst.Constant;
import com.android.dx.rop.cst.CstInteger;
import com.android.dx.util.Bits;
import com.android.dx.util.IntList;
import java.util.ArrayList;
import org.mvel2.MVEL$$ExternalSyntheticBUOutline0;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline2;

/* loaded from: classes.dex */
public final class RopTranslator {
    private final BlockAddresses addresses;
    private final DexOptions dexOptions;
    private final LocalVariableInfo locals;
    private final RopMethod method;
    private int[] order;
    private final OutputCollector output;
    private final int paramSize;
    private final boolean paramsAreInOrder;
    private final int positionInfo;
    private final int regCount;
    private final TranslationVisitor translationVisitor;

    /* renamed from: com.android.dx.dex.code.RopTranslator$1, reason: invalid class name */
    public static class AnonymousClass1 extends Insn.BaseVisitor {
        final /* synthetic */ int val$initialRegCount;
        final /* synthetic */ int val$paramSize;
        final /* synthetic */ boolean[] val$paramsAreInOrder;

        public AnonymousClass1(boolean[] r1, int r2, int r3) {
            this.val$paramsAreInOrder = r1;
            this.val$initialRegCount = r2;
            this.val$paramSize = r3;
        }

        @Override // com.android.dx.rop.code.Insn.BaseVisitor, com.android.dx.rop.code.Insn.Visitor
        public void visitPlainCstInsn(PlainCstInsn r5) {
            if (r5.getOpcode().getOpcode() != 3) goto L12;
            int r0 = ((CstInteger) r5.getConstant()).getValue();
            boolean[] r1 = this.val$paramsAreInOrder;
            if (r1[0] == true) goto L7;
        L9:
            boolean r4 = false;
        L10:
            r1[0] = r4;
            return;
        L7:
            if (((this.val$initialRegCount - this.val$paramSize) + r0) != r5.getResult().getReg()) goto L9;
            r4 = true;
            goto L10
        }
    }

    /* loaded from: classes4.dex */
    public class LocalVariableAwareTranslationVisitor extends TranslationVisitor {
        private final LocalVariableInfo locals;
        final /* synthetic */ RopTranslator this$0;

        public LocalVariableAwareTranslationVisitor(RopTranslator r1, OutputCollector r2, LocalVariableInfo r3) {
            this.this$0 = r1;
            super(r1, r2);
            this.locals = r3;
        }

        public void addIntroductionIfNecessary(Insn r3) {
            RegisterSpec r0 = this.locals.getAssignment(r3);
            if (r0 == null) goto L6;
            addOutput(new LocalStart(r3.getPosition(), r0));
            return;
        }

        @Override // com.android.dx.dex.code.RopTranslator.TranslationVisitor, com.android.dx.rop.code.Insn.Visitor
        public void visitPlainCstInsn(PlainCstInsn r1) {
            super.visitPlainCstInsn(r1);
            addIntroductionIfNecessary(r1);
        }

        @Override // com.android.dx.dex.code.RopTranslator.TranslationVisitor, com.android.dx.rop.code.Insn.Visitor
        public void visitPlainInsn(PlainInsn r1) {
            super.visitPlainInsn(r1);
            addIntroductionIfNecessary(r1);
        }

        @Override // com.android.dx.dex.code.RopTranslator.TranslationVisitor, com.android.dx.rop.code.Insn.Visitor
        public void visitSwitchInsn(SwitchInsn r1) {
            super.visitSwitchInsn(r1);
            addIntroductionIfNecessary(r1);
        }

        @Override // com.android.dx.dex.code.RopTranslator.TranslationVisitor, com.android.dx.rop.code.Insn.Visitor
        public void visitThrowingCstInsn(ThrowingCstInsn r1) {
            super.visitThrowingCstInsn(r1);
            addIntroductionIfNecessary(r1);
        }

        @Override // com.android.dx.dex.code.RopTranslator.TranslationVisitor, com.android.dx.rop.code.Insn.Visitor
        public void visitThrowingInsn(ThrowingInsn r1) {
            super.visitThrowingInsn(r1);
            addIntroductionIfNecessary(r1);
        }
    }

    public class TranslationVisitor implements Insn.Visitor {
        private BasicBlock block;
        private CodeAddress lastAddress;
        private final OutputCollector output;
        final /* synthetic */ RopTranslator this$0;

        public TranslationVisitor(RopTranslator r1, OutputCollector r2) {
            this.this$0 = r1;
            this.output = r2;
        }

        private RegisterSpec getNextMoveResultPseudo() {
            int r0 = this.block.getPrimarySuccessor();
            if (r0 >= 0) goto L5;
            return null;
        L5:
            Insn r3 = RopTranslator.access$500(this.this$0).getBlocks().labelToBlock(r0).getInsns().get(0);
            if (r3.getOpcode().getOpcode() == 56) goto L9;
            return null;
        L9:
            return r3.getResult();
        }

        public void addOutput(DalvInsn r1) {
            this.output.add(r1);
        }

        public void addOutputSuffix(DalvInsn r1) {
            this.output.addSuffix(r1);
        }

        public void setBlock(BasicBlock r1, CodeAddress r2) {
            this.block = r1;
            this.lastAddress = r2;
        }

        @Override // com.android.dx.rop.code.Insn.Visitor
        public void visitFillArrayDataInsn(FillArrayDataInsn r7) {
            SourcePosition r0 = r7.getPosition();
            Constant r1 = r7.getConstant();
            ArrayList<Constant> r2 = r7.getInitValues();
            if (r7.getOpcode().getBranchingness() != 1) goto L6;
            CodeAddress r3 = new CodeAddress(r0);
            DalvInsn r4 = new ArrayData(r0, this.lastAddress, r2, r1);
            DalvInsn r12 = new TargetInsn(Dops.FILL_ARRAY_DATA, r0, RopTranslator.access$000(r7), r3);
            addOutput(this.lastAddress);
            addOutput(r12);
            addOutputSuffix(new OddSpacer(r0));
            addOutputSuffix(r3);
            addOutputSuffix(r4);
            return;
        L6:
            GlShader$$ExternalSyntheticBUOutline1.m("shouldn't happen");
        }

        @Override // com.android.dx.rop.code.Insn.Visitor
        public void visitInvokePolymorphicInsn(InvokePolymorphicInsn r7) {
            SourcePosition r0 = r7.getPosition();
            Dop r1 = RopToDop.dopFor(r7);
            Rop r2 = r7.getOpcode();
            if (r2.getBranchingness() == 6) goto L5;
            GlShader$$ExternalSyntheticBUOutline2.m("Expected BRANCH_THROW got ", r2.getBranchingness());
            return;
        L5:
            if (r2.isCallLike() == false) goto L8;
            addOutput(this.lastAddress);
            addOutput(new MultiCstInsn(r1, r0, r7.getSources(), new Constant[]{r7.getPolymorphicMethod(), r7.getCallSiteProto()}));
            return;
        L8:
            GlShader$$ExternalSyntheticBUOutline1.m("Expected call-like operation");
        }

        @Override // com.android.dx.rop.code.Insn.Visitor
        public void visitPlainCstInsn(PlainCstInsn r6) {
            SourcePosition r0 = r6.getPosition();
            Dop r1 = RopToDop.dopFor(r6);
            Rop r2 = r6.getOpcode();
            int r3 = r2.getOpcode();
            if (r2.getBranchingness() == 1) goto L5;
            GlShader$$ExternalSyntheticBUOutline1.m("shouldn't happen");
            return;
        L5:
            if (r3 == 3) goto L7;
            addOutput(new CstInsn(r1, r0, RopTranslator.access$000(r6), r6.getConstant()));
            return;
        L7:
            if (RopTranslator.access$200(this.this$0) == true) goto L14;
            RegisterSpec r22 = r6.getResult();
            int r62 = ((CstInteger) r6.getConstant()).getValue();
            addOutput(new SimpleInsn(r1, r0, RegisterSpecList.make(r22, RegisterSpec.make((RopTranslator.access$300(this.this$0) - RopTranslator.access$400(this.this$0)) + r62, r22.getType()))));
            return;
        }

        @Override // com.android.dx.rop.code.Insn.Visitor
        public void visitPlainInsn(PlainInsn r6) {
            Rop r0 = r6.getOpcode();
            if (r0.getOpcode() != 54) goto L6;
            return;
        L6:
            if (r0.getOpcode() == 56) goto L27;
            SourcePosition r1 = r6.getPosition();
            Dop r2 = RopToDop.dopFor(r6);
            int r02 = r0.getBranchingness();
            if (r02 != 1) goto L11;
        L23:
            DalvInsn r3 = new SimpleInsn(r2, r1, RopTranslator.access$000(r6));
        L24:
            addOutput(r3);
            return;
        L11:
            if (r02 == 2) goto L23;
            if (r02 != 3) goto L15;
            return;
        L15:
            if (r02 != 4) goto L17;
            int r03 = this.block.getSuccessors().get(1);
            r3 = new TargetInsn(r2, r1, RopTranslator.access$000(r6), RopTranslator.access$100(this.this$0).getStart(r03));
            goto L24
        L17:
            if (r02 == 6) goto L23;
            GlShader$$ExternalSyntheticBUOutline1.m("shouldn't happen");
            return;
        }

        @Override // com.android.dx.rop.code.Insn.Visitor
        public void visitSwitchInsn(SwitchInsn r10) {
            SourcePosition r0 = r10.getPosition();
            IntList r1 = r10.getCases();
            IntList r2 = this.block.getSuccessors();
            int r3 = r1.size();
            int r4 = r2.size();
            int r5 = this.block.getPrimarySuccessor();
            if (r3 == (r4 - 1)) goto L5;
        L15:
            GlShader$$ExternalSyntheticBUOutline1.m("shouldn't happen");
            return;
        L5:
            if (r5 != r2.get(r3)) goto L15;
            CodeAddress[] r42 = new CodeAddress[r3];
            int r52 = 0;
        L7:
            if (r52 >= r3) goto L9;
            int r7 = r2.get(r52);
            r42[r52] = RopTranslator.access$100(this.this$0).getStart(r7);
            r52 = r52 + 1;
            goto L7
        L9:
            CodeAddress r22 = new CodeAddress(r0);
            CodeAddress r32 = new CodeAddress(this.lastAddress.getPosition(), true);
            SwitchData r53 = new SwitchData(r0, r32, r1, r42);
            if (r53.isPacked() == false) goto L12;
            Dop r12 = Dops.PACKED_SWITCH;
        L13:
            DalvInsn r43 = new TargetInsn(r12, r0, RopTranslator.access$000(r10), r22);
            addOutput(r32);
            addOutput(r43);
            addOutputSuffix(new OddSpacer(r0));
            addOutputSuffix(r22);
            addOutputSuffix(r53);
            return;
        L12:
            r12 = Dops.SPARSE_SWITCH;
            goto L13
        }

        @Override // com.android.dx.rop.code.Insn.Visitor
        public void visitThrowingCstInsn(ThrowingCstInsn r11) {
            SourcePosition r0 = r11.getPosition();
            Dop r1 = RopToDop.dopFor(r11);
            Rop r2 = r11.getOpcode();
            Constant r3 = r11.getConstant();
            if (r2.getBranchingness() != 6) goto L29;
            addOutput(this.lastAddress);
            if (r2.isCallLike() == false) goto L8;
            addOutput(new CstInsn(r1, r0, r11.getSources(), r3));
            return;
        L8:
            RegisterSpec r4 = getNextMoveResultPseudo();
            RegisterSpecList r5 = RopTranslator.access$600(r11, r4);
            boolean r7 = true;
            if (r1.hasResult() == false) goto L11;
        L14:
            boolean r6 = true;
        L15:
            if (r4 != null) goto L18;
            r7 = false;
        L18:
            if (r6 == r7) goto L20;
            MVEL$$ExternalSyntheticBUOutline0.m("Insn with result/move-result-pseudo mismatch ", r11);
            return;
        L20:
            if (r2.getOpcode() == 41) goto L22;
        L24:
            DalvInsn r112 = new CstInsn(r1, r0, r5, r3);
        L25:
            addOutput(r112);
            return;
        L22:
            if (r1.getOpcode() == 35) goto L24;
            r112 = new SimpleInsn(r1, r0, r5);
            goto L25
        L11:
            if (r2.getOpcode() == 43) goto L14;
            r6 = false;
            goto L15
        L29:
            GlShader$$ExternalSyntheticBUOutline2.m("Expected BRANCH_THROW got ", r2.getBranchingness());
        }

        @Override // com.android.dx.rop.code.Insn.Visitor
        public void visitThrowingInsn(ThrowingInsn r6) {
            SourcePosition r0 = r6.getPosition();
            Dop r1 = RopToDop.dopFor(r6);
            if (r6.getOpcode().getBranchingness() != 6) goto L13;
            RegisterSpec r2 = getNextMoveResultPseudo();
            boolean r3 = r1.hasResult();
            if (r2 == null) goto L7;
            boolean r4 = true;
        L8:
            if (r3 != r4) goto L11;
            addOutput(this.lastAddress);
            addOutput(new SimpleInsn(r1, r0, RopTranslator.access$600(r6, r2)));
            return;
        L11:
            MVEL$$ExternalSyntheticBUOutline0.m("Insn with result/move-result-pseudo mismatch", r6);
            return;
        L7:
            r4 = false;
            goto L8
        L13:
            GlShader$$ExternalSyntheticBUOutline1.m("shouldn't happen");
        }
    }

    private RopTranslator(RopMethod r8, int r9, LocalVariableInfo r10, int r11, DexOptions r12) {
        this.dexOptions = r12;
        this.method = r8;
        this.positionInfo = r9;
        this.locals = r10;
        this.addresses = new BlockAddresses(r8);
        this.paramSize = r11;
        this.order = null;
        boolean r92 = calculateParamsAreInOrder(r8, r11);
        this.paramsAreInOrder = r92;
        BasicBlockList r82 = r8.getBlocks();
        int r0 = r82.size();
        int r4 = r0 * 3;
        int r1 = r82.getInstructionCount() + r4;
        if (r10 == null) goto L5;
        r1 = r1 + (r0 + r10.getAssignmentCount());
    L5:
        int r3 = r1;
        int r83 = r82.getRegCount();
        if (r92 == false) goto L8;
        int r93 = 0;
    L9:
        int r5 = r83 + r93;
        this.regCount = r5;
        OutputCollector r13 = new OutputCollector(r12, r3, r4, r5, r11);
        this.output = r13;
        if (r10 == null) goto L13;
        this.translationVisitor = new LocalVariableAwareTranslationVisitor(this, r13, r10);
        return;
    L13:
        this.translationVisitor = new TranslationVisitor(this, r13);
        return;
    L8:
        r93 = r11;
        goto L9
    }

    public static /* synthetic */ RegisterSpecList access$000(Insn r0) {
        return getRegs(r0);
    }

    public static /* synthetic */ BlockAddresses access$100(RopTranslator r0) {
        return r0.addresses;
    }

    public static /* synthetic */ boolean access$200(RopTranslator r0) {
        return r0.paramsAreInOrder;
    }

    public static /* synthetic */ int access$300(RopTranslator r0) {
        return r0.regCount;
    }

    public static /* synthetic */ int access$400(RopTranslator r0) {
        return r0.paramSize;
    }

    public static /* synthetic */ RopMethod access$500(RopTranslator r0) {
        return r0.method;
    }

    public static /* synthetic */ RegisterSpecList access$600(Insn r0, RegisterSpec r1) {
        return getRegs(r0, r1);
    }

    private static boolean calculateParamsAreInOrder(RopMethod r4, int r5) {
        boolean[] r1 = {true};
        r4.getBlocks().forEachInsn(new AnonymousClass1(r1, r4.getBlocks().getRegCount(), r5));
        return r1[0];
    }

    private static RegisterSpecList getRegs(Insn r1) {
        return getRegs(r1, r1.getResult());
    }

    private static RegisterSpecList getRegs(Insn r3, RegisterSpec r4) {
        RegisterSpecList r0 = r3.getSources();
        if (r3.getOpcode().isCommutative() == true) goto L5;
    L9:
        if (r4 != null) goto L12;
        return r0;
    L12:
        return r0.withFirst(r4);
    L5:
        if (r0.size() != 2) goto L9;
        if (r4.getReg() != r0.get(1).getReg()) goto L9;
        r0 = RegisterSpecList.make(r0.get(1), r0.get(0));
        goto L9
    }

    private void outputBlock(BasicBlock r5, int r6) {
        CodeAddress r0 = this.addresses.getStart(r5);
        this.output.add(r0);
        LocalVariableInfo r1 = this.locals;
        if (r1 == null) goto L5;
        RegisterSpecSet r12 = r1.getStarts(r5);
        this.output.add(new LocalSnapshot(r0.getPosition(), r12));
    L5:
        this.translationVisitor.setBlock(r5, this.addresses.getLast(r5));
        r5.getInsns().forEach(this.translationVisitor);
        this.output.add(this.addresses.getEnd(r5));
        int r02 = r5.getPrimarySuccessor();
        Insn r13 = r5.getLastInsn();
        if (r02 < 0) goto L16;
        if (r02 != r6) goto L9;
        return;
    L9:
        if (r13.getOpcode().getBranchingness() == 4) goto L11;
    L14:
        TargetInsn r52 = new TargetInsn(Dops.GOTO, r13.getPosition(), RegisterSpecList.EMPTY, this.addresses.getStart(r02));
        this.output.add(r52);
        return;
    L11:
        if (r5.getSecondarySuccessor() != r6) goto L14;
        this.output.reverseBranch(1, this.addresses.getStart(r02));
        return;
    }

    private void outputInstructions() {
        BasicBlockList r0 = this.method.getBlocks();
        int[] r1 = this.order;
        int r2 = r1.length;
        int r3 = 0;
    L3:
        if (r3 >= r2) goto L9;
        int r4 = r3 + 1;
        if (r4 != r1.length) goto L7;
        int r5 = -1;
    L8:
        outputBlock(r0.labelToBlock(r1[r3]), r5);
        r3 = r4;
        goto L3
    L7:
        r5 = r1[r4];
        goto L8
    }

    private void pickOrder() {
        BasicBlockList r0 = this.method.getBlocks();
        int r1 = r0.size();
        int r2 = r0.getMaxLabel();
        int[] r3 = Bits.makeBitSet(r2);
        int[] r22 = Bits.makeBitSet(r2);
        int r5 = 0;
    L3:
        if (r5 >= r1) goto L5;
        Bits.set(r3, r0.get(r5).getLabel());
        r5 = r5 + 1;
        goto L3
    L5:
        int[] r52 = new int[r1];
        int r6 = this.method.getFirstLabel();
        int r7 = 0;
    L7:
        if (r6 == (-1)) goto L40;
    L8:
        IntList r9 = this.method.labelToPredecessors(r6);
        int r10 = r9.size();
        int r11 = 0;
    L9:
        if (r11 >= r10) goto L20;
        int r12 = r9.get(r11);
        if (Bits.get(r22, r12) == true) goto L20;
        if (Bits.get(r3, r12) == false) goto L19;
        if (r0.labelToBlock(r12).getPrimarySuccessor() != r6) goto L19;
        Bits.set(r22, r12);
        r6 = r12;
    L19:
        r11 = r11 + 1;
    L20:
        if (r6 == (-1)) goto L39;
        Bits.clear(r3, r6);
        Bits.clear(r22, r6);
        r52[r7] = r6;
        r7 = r7 + 1;
        BasicBlock r62 = r0.labelToBlock(r6);
        BasicBlock r92 = r0.preferredSuccessorOf(r62);
        if (r92 == null) goto L39;
        int r93 = r92.getLabel();
        int r102 = r62.getPrimarySuccessor();
        if (Bits.get(r3, r93) == true) goto L26;
        if (r102 == r93) goto L32;
        if (r102 < 0) goto L32;
        if (Bits.get(r3, r102) == false) goto L32;
        r6 = r102;
    L32:
        IntList r63 = r62.getSuccessors();
        int r94 = r63.size();
        int r103 = 0;
    L33:
        if (r103 >= r94) goto L38;
        int r112 = r63.get(r103);
        if (Bits.get(r3, r112) == true) goto L36;
        r103 = r103 + 1;
        goto L33
    L36:
        r6 = r112;
        goto L20
    L38:
        r6 = -1;
        goto L20
    L26:
        r6 = r93;
    L39:
        r6 = Bits.findFirst(r3, 0);
        goto L7
    L40:
        if (r7 != r1) goto L43;
        this.order = r52;
        return;
    L43:
        GlShader$$ExternalSyntheticBUOutline1.m("shouldn't happen");
    }

    public static DalvCode translate(RopMethod r6, int r7, LocalVariableInfo r8, int r9, DexOptions r10) {
        return new RopTranslator(r6, r7, r8, r9, r10).translateAndGetResult();
    }

    private DalvCode translateAndGetResult() {
        pickOrder();
        outputInstructions();
        StdCatchBuilder r0 = new StdCatchBuilder(this.method, this.order, this.addresses);
        return new DalvCode(this.positionInfo, this.output.getFinisher(), r0);
    }
}
