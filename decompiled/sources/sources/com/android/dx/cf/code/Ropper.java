package com.android.dx.cf.code;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.cf.code.ByteCatchList;
import com.android.dx.cf.code.LocalVariableList;
import com.android.dx.cf.iface.MethodList;
import com.android.dx.dex.DexOptions;
import com.android.dx.rop.code.BasicBlock;
import com.android.dx.rop.code.BasicBlockList;
import com.android.dx.rop.code.Insn;
import com.android.dx.rop.code.InsnList;
import com.android.dx.rop.code.PlainCstInsn;
import com.android.dx.rop.code.PlainInsn;
import com.android.dx.rop.code.RegisterSpec;
import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.rop.code.Rop;
import com.android.dx.rop.code.RopMethod;
import com.android.dx.rop.code.Rops;
import com.android.dx.rop.code.SourcePosition;
import com.android.dx.rop.code.ThrowingCstInsn;
import com.android.dx.rop.code.ThrowingInsn;
import com.android.dx.rop.code.TranslationAdvice;
import com.android.dx.rop.cst.CstInteger;
import com.android.dx.rop.cst.CstType;
import com.android.dx.rop.type.Prototype;
import com.android.dx.rop.type.StdTypeList;
import com.android.dx.rop.type.Type;
import com.android.dx.rop.type.TypeList;
import com.android.dx.util.Bits;
import com.android.dx.util.Hex;
import com.android.dx.util.IntList;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import okio.Buffer$$ExternalSyntheticBUOutline4;
import org.mvel2.ast.Instance$$ExternalSyntheticBUOutline0;
import org.mvel2.util.StaticFieldStub$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public final class Ropper {
    private static final int PARAM_ASSIGNMENT = -1;
    private static final int RETURN = -2;
    private static final int SPECIAL_LABEL_COUNT = 7;
    private static final int SYNCH_CATCH_1 = -6;
    private static final int SYNCH_CATCH_2 = -7;
    private static final int SYNCH_RETURN = -3;
    private static final int SYNCH_SETUP_1 = -4;
    private static final int SYNCH_SETUP_2 = -5;
    private final ByteBlockList blocks;
    private final CatchInfo[] catchInfos;
    private final ExceptionSetupLabelAllocator exceptionSetupLabelAllocator;
    private boolean hasSubroutines;
    private final RopperMachine machine;
    private final int maxLabel;
    private final int maxLocals;
    private final ConcreteMethod method;
    private final ArrayList<BasicBlock> result;
    private final ArrayList<IntList> resultSubroutines;
    private final Simulator sim;
    private final Frame[] startFrames;
    private final Subroutine[] subroutines;
    private boolean synchNeedsExceptionHandler;

    /* renamed from: com.android.dx.cf.code.Ropper$1, reason: invalid class name */
    public class AnonymousClass1 implements BasicBlock.Visitor {
        final /* synthetic */ Ropper this$0;
        final /* synthetic */ IntList val$reachableSubroutineCallerLabels;

        public AnonymousClass1(Ropper r1, IntList r2) {
            this.this$0 = r1;
            this.val$reachableSubroutineCallerLabels = r2;
        }

        @Override // com.android.dx.rop.code.BasicBlock.Visitor
        public void visitBlock(BasicBlock r2) {
            if (Ropper.access$600(this.this$0, r2) == false) goto L6;
            this.val$reachableSubroutineCallerLabels.add(r2.getLabel());
            return;
        }
    }

    /* renamed from: com.android.dx.cf.code.Ropper$2, reason: invalid class name */
    public class AnonymousClass2 implements BasicBlock.Visitor {
        final /* synthetic */ Ropper this$0;
        final /* synthetic */ IntList val$reachableLabels;

        public AnonymousClass2(Ropper r1, IntList r2) {
            this.this$0 = r1;
            this.val$reachableLabels = r2;
        }

        @Override // com.android.dx.rop.code.BasicBlock.Visitor
        public void visitBlock(BasicBlock r1) {
            this.val$reachableLabels.add(r1.getLabel());
        }
    }

    public class CatchInfo {
        private final Map<Type, ExceptionHandlerSetup> setups;
        final /* synthetic */ Ropper this$0;

        private CatchInfo(Ropper r1) {
            this.this$0 = r1;
            this.setups = new HashMap();
        }

        public /* synthetic */ CatchInfo(Ropper r1, AnonymousClass1 r2) {
            this(r1);
        }

        public ExceptionHandlerSetup getSetup(Type r3) {
            ExceptionHandlerSetup r0 = this.setups.get(r3);
            if (r0 != null) goto L6;
            ExceptionHandlerSetup r1 = new ExceptionHandlerSetup(r3, Ropper.access$000(this.this$0).getNextLabel());
            this.setups.put(r3, r1);
            return r1;
        L6:
            return r0;
        }

        public Collection<ExceptionHandlerSetup> getSetups() {
            return this.setups.values();
        }
    }

    public static class ExceptionHandlerSetup {
        private Type caughtType;
        private int label;

        public ExceptionHandlerSetup(Type r1, int r2) {
            this.caughtType = r1;
            this.label = r2;
        }

        public Type getCaughtType() {
            return this.caughtType;
        }

        public int getLabel() {
            return this.label;
        }
    }

    public class ExceptionSetupLabelAllocator extends LabelAllocator {
        int maxSetupLabel;
        final /* synthetic */ Ropper this$0;

        public ExceptionSetupLabelAllocator(Ropper r2) {
            this.this$0 = r2;
            super(Ropper.access$100(r2));
            this.maxSetupLabel = Ropper.access$100(r2) + Ropper.access$700(r2).getCatches().size();
        }

        @Override // com.android.dx.cf.code.Ropper.LabelAllocator
        public int getNextLabel() {
            int r0 = this.nextAvailableLabel;
            if (r0 >= this.maxSetupLabel) goto L7;
            this.nextAvailableLabel = r0 + 1;
            return r0;
        L7:
            throw new IndexOutOfBoundsException();
        }
    }

    public static class LabelAllocator {
        int nextAvailableLabel;

        public LabelAllocator(int r1) {
            this.nextAvailableLabel = r1;
        }

        public int getNextLabel() {
            int r0 = this.nextAvailableLabel;
            this.nextAvailableLabel = r0 + 1;
            return r0;
        }
    }

    public class Subroutine {
        private BitSet callerBlocks;
        private BitSet retBlocks;
        private int startBlock;
        final /* synthetic */ Ropper this$0;

        public Subroutine(Ropper r2, int r3) {
            this.this$0 = r2;
            this.startBlock = r3;
            this.retBlocks = new BitSet(Ropper.access$100(r2));
            this.callerBlocks = new BitSet(Ropper.access$100(r2));
            Ropper.access$202(r2, true);
        }

        public Subroutine(Ropper r1, int r2, int r3) {
            this(r1, r2);
            addRetBlock(r3);
        }

        public static /* synthetic */ int access$1000(Subroutine r0) {
            return r0.startBlock;
        }

        public static /* synthetic */ BitSet access$1300(Subroutine r0) {
            return r0.retBlocks;
        }

        public void addCallerBlock(int r1) {
            this.callerBlocks.set(r1);
        }

        public void addRetBlock(int r1) {
            this.retBlocks.set(r1);
        }

        public int getStartBlock() {
            return this.startBlock;
        }

        public IntList getSuccessors() {
            IntList r0 = new IntList(this.callerBlocks.size());
            int r1 = this.callerBlocks.nextSetBit(0);
        L3:
            if (r1 < 0) goto L5;
            r0.add(Ropper.access$300(this.this$0, r1).getSuccessors().get(0));
            r1 = this.callerBlocks.nextSetBit(r1 + 1);
            goto L3
        L5:
            r0.setImmutable();
            return r0;
        }

        public void mergeToSuccessors(Frame r10, int[] r11) {
            int r0 = this.callerBlocks.nextSetBit(0);
        L3:
            if (r0 < 0) goto L9;
            int r4 = Ropper.access$300(this.this$0, r0).getSuccessors().get(0);
            Frame r7 = r10.subFrameForLabel(this.startBlock, r0);
            if (r7 == null) goto L7;
            int[] r8 = r11;
            Ropper.access$400(this.this$0, r4, -1, null, r7, r8);
        L8:
            r0 = this.callerBlocks.nextSetBit(r0 + 1);
            r11 = r8;
            goto L3
        L7:
            r8 = r11;
            Bits.set(r8, r0);
            goto L8
        }
    }

    public class SubroutineInliner {
        private final LabelAllocator labelAllocator;
        private final ArrayList<IntList> labelToSubroutines;
        private final HashMap<Integer, Integer> origLabelToCopiedLabel;
        private int subroutineStart;
        private int subroutineSuccessor;
        final /* synthetic */ Ropper this$0;
        private final BitSet workList;

        public SubroutineInliner(Ropper r2, LabelAllocator r3, ArrayList<IntList> r4) {
            this.this$0 = r2;
            this.origLabelToCopiedLabel = new HashMap();
            this.workList = new BitSet(Ropper.access$100(r2));
            this.labelAllocator = r3;
            this.labelToSubroutines = r4;
        }

        private void copyBlock(int r9, int r10) {
            BasicBlock r0 = Ropper.access$300(this.this$0, r9);
            IntList r1 = r0.getSuccessors();
            int r3 = 0;
            int r4 = -1;
            if (Ropper.access$600(this.this$0, r0) == false) goto L5;
            IntList r92 = IntList.makeImmutable(mapOrAllocateLabel(r1.get(0)), r1.get(1));
        L19:
            Ropper r12 = this.this$0;
            Ropper.access$1200(r12, new BasicBlock(r10, Ropper.access$1100(r12, r0.getInsns()), r92, r4), this.labelToSubroutines.get(r10));
            return;
        L5:
            Subroutine r93 = Ropper.access$900(this.this$0, r9);
            if (r93 != null) goto L8;
            int r94 = r0.getPrimarySuccessor();
            int r2 = r1.size();
            IntList r5 = new IntList(r2);
        L13:
            if (r3 >= r2) goto L18;
            int r6 = r1.get(r3);
            int r7 = mapOrAllocateLabel(r6);
            r5.add(r7);
            if (r94 != r6) goto L17;
            r4 = r7;
        L17:
            r3 = r3 + 1;
            goto L13
        L18:
            r5.setImmutable();
            r92 = r5;
            goto L19
        L8:
            if (Subroutine.access$1000(r93) != this.subroutineStart) goto L10;
            r92 = IntList.makeImmutable(this.subroutineSuccessor);
            r4 = this.subroutineSuccessor;
            goto L19
        L10:
            StaticFieldStub$$ExternalSyntheticBUOutline0.m("ret instruction returns to label ", Hex.u2(Subroutine.access$1000(r93)), " expected: ", Hex.u2(this.subroutineStart));
        }

        private boolean involvedInSubroutine(int r1, int r2) {
            IntList r0 = this.labelToSubroutines.get(r1);
            if (r0 != null) goto L5;
            return false;
        L5:
            if (r0.size() > 0) goto L7;
            return false;
        L7:
            if (r0.top() != r2) goto L13;
            return true;
        L13:
            return false;
        }

        private int mapOrAllocateLabel(int r5) {
            Integer r0 = this.origLabelToCopiedLabel.get(Integer.valueOf(r5));
            if (r0 == null) goto L7;
            return r0.intValue();
        L7:
            if (involvedInSubroutine(r5, this.subroutineStart) == true) goto L9;
            return r5;
        L9:
            int r02 = this.labelAllocator.getNextLabel();
            this.workList.set(r5);
            this.origLabelToCopiedLabel.put(Integer.valueOf(r5), Integer.valueOf(r02));
        L10:
            int r1 = this.labelToSubroutines.size();
            ArrayList<IntList> r2 = this.labelToSubroutines;
            if (r1 > r02) goto L13;
            r2.add(null);
            goto L10
        L13:
            r2.set(r02, r2.get(r5));
            return r02;
        }

        public void inlineSubroutineCalledFrom(BasicBlock r8) {
            this.subroutineSuccessor = r8.getSuccessors().get(0);
            int r0 = r8.getSuccessors().get(1);
            this.subroutineStart = r0;
            int r02 = mapOrAllocateLabel(r0);
            int r2 = this.workList.nextSetBit(0);
        L3:
            if (r2 < 0) goto L8;
            this.workList.clear(r2);
            int r3 = this.origLabelToCopiedLabel.get(Integer.valueOf(r2)).intValue();
            copyBlock(r2, r3);
            Ropper r4 = this.this$0;
            if (Ropper.access$600(r4, Ropper.access$300(r4, r2)) == false) goto L7;
            new SubroutineInliner(this.this$0, this.labelAllocator, this.labelToSubroutines).inlineSubroutineCalledFrom(Ropper.access$300(this.this$0, r3));
        L7:
            r2 = this.workList.nextSetBit(0);
            goto L3
        L8:
            Ropper.access$800(this.this$0, new BasicBlock(r8.getLabel(), r8.getInsns(), IntList.makeImmutable(r02), r02), this.labelToSubroutines.get(r8.getLabel()));
        }
    }

    private Ropper(ConcreteMethod r5, TranslationAdvice r6, MethodList r7, DexOptions r8) {
        if (r5 == null) goto L9;
        if (r6 == null) goto L7;
        this.method = r5;
        ByteBlockList r0 = BasicBlocker.identifyBlocks(r5);
        this.blocks = r0;
        int r1 = r0.getMaxLabel();
        this.maxLabel = r1;
        int r2 = r5.getMaxLocals();
        this.maxLocals = r2;
        RopperMachine r3 = new RopperMachine(this, r5, r6, r7);
        this.machine = r3;
        this.sim = new Simulator(r3, r5, r8);
        Frame[] r62 = new Frame[r1];
        this.startFrames = r62;
        this.subroutines = new Subroutine[r1];
        this.result = new ArrayList((r0.size() * 2) + 10);
        this.resultSubroutines = new ArrayList((r0.size() * 2) + 10);
        this.catchInfos = new CatchInfo[r1];
        this.synchNeedsExceptionHandler = false;
        r62[0] = new Frame(r2, r5.getMaxStack());
        this.exceptionSetupLabelAllocator = new ExceptionSetupLabelAllocator(this);
        return;
    L7:
        f$$ExternalSyntheticBUOutline2.m("advice == null");
        throw null;
    L9:
        f$$ExternalSyntheticBUOutline2.m("method == null");
        throw null;
    }

    public static /* synthetic */ ExceptionSetupLabelAllocator access$000(Ropper r0) {
        return r0.exceptionSetupLabelAllocator;
    }

    public static /* synthetic */ int access$100(Ropper r0) {
        return r0.maxLabel;
    }

    public static /* synthetic */ InsnList access$1100(Ropper r0, InsnList r1) {
        return r0.filterMoveReturnAddressInsns(r1);
    }

    public static /* synthetic */ void access$1200(Ropper r0, BasicBlock r1, IntList r2) {
        r0.addBlock(r1, r2);
    }

    public static /* synthetic */ boolean access$202(Ropper r0, boolean r1) {
        r0.hasSubroutines = r1;
        return r1;
    }

    public static /* synthetic */ BasicBlock access$300(Ropper r0, int r1) {
        return r0.labelToBlock(r1);
    }

    public static /* synthetic */ void access$400(Ropper r0, int r1, int r2, Subroutine r3, Frame r4, int[] r5) {
        r0.mergeAndWorkAsNecessary(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ boolean access$600(Ropper r0, BasicBlock r1) {
        return r0.isSubroutineCaller(r1);
    }

    public static /* synthetic */ ConcreteMethod access$700(Ropper r0) {
        return r0.method;
    }

    public static /* synthetic */ boolean access$800(Ropper r0, BasicBlock r1, IntList r2) {
        return r0.addOrReplaceBlockNoDelete(r1, r2);
    }

    public static /* synthetic */ Subroutine access$900(Ropper r0, int r1) {
        return r0.subroutineFromRetBlock(r1);
    }

    private void addBlock(BasicBlock r2, IntList r3) {
        if (r2 == null) goto L5;
        this.result.add(r2);
        r3.throwIfMutable();
        this.resultSubroutines.add(r3);
        return;
    L5:
        f$$ExternalSyntheticBUOutline2.m("block == null");
    }

    private void addExceptionSetupBlocks() {
        int r0 = this.catchInfos.length;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L11;
        CatchInfo r3 = this.catchInfos[r2];
        if (r3 == null) goto L10;
        Iterator<ExceptionHandlerSetup> r32 = r3.getSetups().iterator();
    L8:
        if (r32.hasNext() == false) goto L10;
        ExceptionHandlerSetup r4 = r32.next();
        SourcePosition r5 = labelToBlock(r2).getFirstInsn().getPosition();
        InsnList r6 = new InsnList(2);
        Rop r8 = Rops.opMoveException(r4.getCaughtType());
        RegisterSpec r9 = RegisterSpec.make(this.maxLocals, r4.getCaughtType());
        RegisterSpecList r10 = RegisterSpecList.EMPTY;
        r6.set(0, new PlainInsn(r8, r5, r9, r10));
        r6.set(1, new PlainInsn(Rops.GOTO, r5, null, r10));
        r6.setImmutable();
        addBlock(new BasicBlock(r4.getLabel(), r6, IntList.makeImmutable(r2), r2), this.startFrames[r2].getSubroutines());
    L10:
        r2 = r2 + 1;
        goto L3
    }

    private boolean addOrReplaceBlock(BasicBlock r3, IntList r4) {
        boolean r0 = false;
        if (r3 == null) goto L10;
        int r1 = labelToResultIndex(r3.getLabel());
        if (r1 < 0) goto L8;
        removeBlockAndSpecialSuccessors(r1);
        r0 = true;
    L8:
        this.result.add(r3);
        r4.throwIfMutable();
        this.resultSubroutines.add(r4);
        return r0;
    L10:
        f$$ExternalSyntheticBUOutline2.m("block == null");
        return false;
    }

    private boolean addOrReplaceBlockNoDelete(BasicBlock r3, IntList r4) {
        boolean r0 = false;
        if (r3 == null) goto L10;
        int r1 = labelToResultIndex(r3.getLabel());
        if (r1 < 0) goto L8;
        this.result.remove(r1);
        this.resultSubroutines.remove(r1);
        r0 = true;
    L8:
        this.result.add(r3);
        r4.throwIfMutable();
        this.resultSubroutines.add(r4);
        return r0;
    L10:
        f$$ExternalSyntheticBUOutline2.m("block == null");
        return false;
    }

    private void addReturnBlock() {
        Rop r0 = this.machine.getReturnOp();
        if (r0 != null) goto L5;
        return;
    L5:
        SourcePosition r1 = this.machine.getReturnPosition();
        int r2 = getSpecialLabel(-2);
        if (isSynchronized() == false) goto L8;
        InsnList r3 = new InsnList(1);
        r3.set(0, new ThrowingInsn(Rops.MONITOR_EXIT, r1, RegisterSpecList.make(getSynchReg()), StdTypeList.EMPTY));
        r3.setImmutable();
        int r6 = getSpecialLabel(-3);
        addBlock(new BasicBlock(r2, r3, IntList.makeImmutable(r6), r6), IntList.EMPTY);
        r2 = r6;
    L8:
        InsnList r32 = new InsnList(1);
        TypeList r4 = r0.getSources();
        if (r4.size() != 0) goto L11;
        RegisterSpecList r42 = RegisterSpecList.EMPTY;
    L12:
        r32.set(0, new PlainInsn(r0, r1, null, r42));
        r32.setImmutable();
        IntList r12 = IntList.EMPTY;
        addBlock(new BasicBlock(r2, r32, r12, -1), r12);
        return;
    L11:
        r42 = RegisterSpecList.make(RegisterSpec.make(0, r4.getType(0)));
        goto L12
    }

    private void addSetupBlocks() {
        LocalVariableList r0 = this.method.getLocalVariables();
        SourcePosition r5 = this.method.makeSourcePosistion(0);
        StdTypeList r1 = this.method.getEffectiveDescriptor().getParameterTypes();
        int r9 = r1.size();
        InsnList r10 = new InsnList(r9 + 1);
        int r11 = 0;
        int r12 = 0;
    L3:
        if (r11 >= r9) goto L10;
        Type r13 = r1.get(r11);
        LocalVariableList.Item r3 = r0.pcAndIndexToLocal(0, r12);
        if (r3 != null) goto L8;
        RegisterSpec r32 = RegisterSpec.make(r12, r13);
    L9:
        r10.set(r11, new PlainCstInsn(Rops.opMoveParam(r13), r5, r32, RegisterSpecList.EMPTY, CstInteger.make(r12)));
        r12 = r12 + r13.getCategory();
        r11 = r11 + 1;
        goto L3
    L8:
        r32 = RegisterSpec.makeLocalOptional(r12, r13, r3.getLocalItem());
        goto L9
    L10:
        Rop r14 = Rops.GOTO;
        RegisterSpecList r6 = RegisterSpecList.EMPTY;
        r10.set(r9, new PlainInsn(r14, r5, null, r6));
        r10.setImmutable();
        boolean r02 = isSynchronized();
        if (r02 == false) goto L13;
        int r92 = getSpecialLabel(-4);
    L14:
        BasicBlock r33 = new BasicBlock(getSpecialLabel(-1), r10, IntList.makeImmutable(r92), r92);
        IntList r102 = IntList.EMPTY;
        addBlock(r33, r102);
        if (r02 == false) goto L30;
        RegisterSpec r03 = getSynchReg();
        int r122 = 2;
        if (isStatic() == false) goto L19;
        ThrowingCstInsn r34 = new ThrowingCstInsn(Rops.CONST_OBJECT, r5, r6, StdTypeList.EMPTY, this.method.getDefiningClass());
        InsnList r15 = new InsnList(1);
        r15.set(0, r34);
    L20:
        int r35 = getSpecialLabel(SYNCH_SETUP_2);
        r15.setImmutable();
        addBlock(new BasicBlock(r92, r15, IntList.makeImmutable(r35), r35), r102);
        if (isStatic() == true) goto L24;
        r122 = 1;
    L24:
        InsnList r16 = new InsnList(r122);
        if (isStatic() == false) goto L27;
        r16.set(0, new PlainInsn(Rops.opMoveResultPseudo(r03), r5, r03, r6));
    L27:
        r16.set(isStatic() ? 1 : 0, new ThrowingInsn(Rops.MONITOR_ENTER, r5, RegisterSpecList.make(r03), StdTypeList.EMPTY));
        r16.setImmutable();
        addBlock(new BasicBlock(r35, r16, IntList.makeImmutable(0), 0), r102);
        return;
    L19:
        InsnList r142 = new InsnList(2);
        PlainCstInsn r36 = new PlainCstInsn(Rops.MOVE_PARAM_OBJECT, r5, r03, r6, CstInteger.VALUE_0);
        r6 = r6;
        r142.set(0, r36);
        r142.set(1, new PlainInsn(r14, r5, null, r6));
        r15 = r142;
        goto L20
    L30:
        return;
    L13:
        r92 = 0;
        goto L14
    }

    private void addSynchExceptionHandlerBlock() {
        if (this.synchNeedsExceptionHandler == true) goto L5;
        return;
    L5:
        SourcePosition r0 = this.method.makeSourcePosistion(0);
        Type r2 = Type.THROWABLE;
        RegisterSpec r3 = RegisterSpec.make(0, r2);
        InsnList r4 = new InsnList(2);
        r4.set(0, new PlainInsn(Rops.opMoveException(r2), r0, r3, RegisterSpecList.EMPTY));
        Rop r5 = Rops.MONITOR_EXIT;
        RegisterSpecList r6 = RegisterSpecList.make(getSynchReg());
        StdTypeList r7 = StdTypeList.EMPTY;
        r4.set(1, new ThrowingInsn(r5, r0, r6, r7));
        r4.setImmutable();
        int r22 = getSpecialLabel(SYNCH_CATCH_2);
        BasicBlock r62 = new BasicBlock(getSpecialLabel(SYNCH_CATCH_1), r4, IntList.makeImmutable(r22), r22);
        IntList r42 = IntList.EMPTY;
        addBlock(r62, r42);
        InsnList r63 = new InsnList(1);
        r63.set(0, new ThrowingInsn(Rops.THROW, r0, RegisterSpecList.make(r3), r7));
        r63.setImmutable();
        addBlock(new BasicBlock(r22, r63, r42, -1), r42);
    }

    public static RopMethod convert(ConcreteMethod r1, TranslationAdvice r2, MethodList r3, DexOptions r4) {
        Ropper r0 = new Ropper(r1, r2, r3, r4);     // Catch: SimException -> L4
        r0.doit();     // Catch: SimException -> L4
        return r0.getRopMethod();
    L4:
        e = move-exception;
        e.addContext("...while working on method " + r1.getNat().toHuman());
        throw e;
    }

    private void deleteUnreachableBlocks() {
        IntList r0 = new IntList(this.result.size());
        this.resultSubroutines.clear();
        forEachNonSubBlockDepthFirst(getSpecialLabel(-1), new AnonymousClass2(this, r0));
        r0.sort();
        int r1 = this.result.size() - 1;
    L3:
        if (r1 < 0) goto L8;
        if (r0.indexOf(this.result.get(r1).getLabel()) >= 0) goto L7;
        this.result.remove(r1);
    L7:
        r1 = r1 - 1;
        goto L3
    }

    private void doit() {
        int[] r0 = Bits.makeBitSet(this.maxLabel);
        Bits.set(r0, 0);
        addSetupBlocks();
        setFirstFrame();
    L3:
        int r2 = Bits.findFirst(r0, 0);
        if (r2 < 0) goto L5;
        Bits.clear(r0, r2);
        processBlock(this.blocks.labelToBlock(r2), this.startFrames[r2], r0);     // Catch: SimException -> L12
    L12:
        e = move-exception;
        e.addContext("...while working on block " + Hex.u2(r2));
        throw e;
    L5:
        addReturnBlock();
        addSynchExceptionHandlerBlock();
        addExceptionSetupBlocks();
        if (this.hasSubroutines == false) goto L19;
        inlineSubroutines();
        return;
    }

    private InsnList filterMoveReturnAddressInsns(InsnList r7) {
        int r6 = r7.size();
        int r0 = 0;
        int r1 = 0;
        int r2 = 0;
    L3:
        if (r1 >= r6) goto L8;
        if (r7.get(r1).getOpcode() == Rops.MOVE_RETURN_ADDRESS) goto L7;
        r2 = r2 + 1;
    L7:
        r1 = r1 + 1;
        goto L3
    L8:
        if (r2 != r6) goto L10;
        return r7;
    L10:
        InsnList r12 = new InsnList(r2);
        int r22 = 0;
    L11:
        if (r0 >= r6) goto L16;
        Insn r3 = r7.get(r0);
        if (r3.getOpcode() == Rops.MOVE_RETURN_ADDRESS) goto L15;
        r12.set(r22, r3);
        r22 = r22 + 1;
    L15:
        r0 = r0 + 1;
        goto L11
    L16:
        r12.setImmutable();
        return r12;
    }

    private void forEachNonSubBlockDepthFirst(int r3, BasicBlock.Visitor r4) {
        forEachNonSubBlockDepthFirst0(labelToBlock(r3), r4, new BitSet(this.maxLabel));
    }

    private void forEachNonSubBlockDepthFirst0(BasicBlock r6, BasicBlock.Visitor r7, BitSet r8) {
        r7.visitBlock(r6);
        r8.set(r6.getLabel());
        IntList r0 = r6.getSuccessors();
        int r1 = r0.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L15;
        int r3 = r0.get(r2);
        if (r8.get(r3) == true) goto L14;
        if (isSubroutineCaller(r6) == false) goto L11;
        if (r2 > 0) goto L14;
    L11:
        int r32 = labelToResultIndex(r3);
        if (r32 < 0) goto L14;
        forEachNonSubBlockDepthFirst0(this.result.get(r32), r7, r8);
    L14:
        r2 = r2 + 1;
        goto L3
    }

    private int getAvailableLabel() {
        int r0 = getMinimumUnreservedLabel();
        ArrayList<BasicBlock> r4 = this.result;
        int r1 = r4.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L7;
        BasicBlock r3 = r4.get(r2);
        r2 = r2 + 1;
        int r32 = r3.getLabel();
        if (r32 < r0) goto L3;
        r0 = r32 + 1;
        goto L3
    L7:
        return r0;
    }

    private int getMinimumUnreservedLabel() {
        return (this.maxLabel + this.method.getCatches().size()) + 7;
    }

    private int getNormalRegCount() {
        return this.maxLocals + this.method.getMaxStack();
    }

    private RopMethod getRopMethod() {
        int r0 = this.result.size();
        BasicBlockList r1 = new BasicBlockList(r0);
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L5;
        r1.set(r2, this.result.get(r2));
        r2 = r2 + 1;
        goto L3
    L5:
        r1.setImmutable();
        return new RopMethod(r1, getSpecialLabel(-1));
    }

    private int getSpecialLabel(int r2) {
        return (this.maxLabel + this.method.getCatches().size()) + (~r2);
    }

    private RegisterSpec getSynchReg() {
        int r1 = getNormalRegCount();
        if (r1 >= 1) goto L6;
        r1 = 1;
    L6:
        return RegisterSpec.make(r1, Type.OBJECT);
    }

    private void inlineSubroutines() {
        IntList r0 = new IntList(4);
        int r2 = 0;
        forEachNonSubBlockDepthFirst(0, new AnonymousClass1(this, r0));
        int r1 = getAvailableLabel();
        ArrayList r3 = new ArrayList(r1);
        int r4 = 0;
    L3:
        if (r4 >= r1) goto L5;
        r3.add(null);
        r4 = r4 + 1;
        goto L3
    L5:
        int r12 = 0;
    L7:
        if (r12 >= this.result.size()) goto L13;
        BasicBlock r42 = this.result.get(r12);
        if (r42 == null) goto L12;
        r3.set(r42.getLabel(), this.resultSubroutines.get(r12));
    L12:
        r12 = r12 + 1;
        goto L7
    L13:
        int r13 = r0.size();
    L14:
        if (r2 >= r13) goto L16;
        new SubroutineInliner(this, new LabelAllocator(getAvailableLabel()), r3).inlineSubroutineCalledFrom(labelToBlock(r0.get(r2)));
        r2 = r2 + 1;
        goto L14
    L16:
        deleteUnreachableBlocks();
    }

    private boolean isStatic() {
        if ((this.method.getAccessFlags() & 8) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    private boolean isSubroutineCaller(BasicBlock r4) {
        IntList r42 = r4.getSuccessors();
        if (r42.size() >= 2) goto L5;
        return false;
    L5:
        int r43 = r42.get(1);
        Subroutine[] r3 = this.subroutines;
        if (r43 < r3.length) goto L8;
    L10:
        return false;
    L8:
        if (r3[r43] == null) goto L10;
        return true;
    }

    private boolean isSynchronized() {
        if ((this.method.getAccessFlags() & 32) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    private BasicBlock labelToBlock(int r2) {
        int r0 = labelToResultIndex(r2);
        if (r0 >= 0) goto L5;
        Buffer$$ExternalSyntheticBUOutline4.m("no such label ", Hex.u2(r2));
        return null;
    L5:
        return this.result.get(r0);
    }

    private int labelToResultIndex(int r4) {
        int r0 = this.result.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        if (this.result.get(r1).getLabel() == r4) goto L6;
        r1 = r1 + 1;
        goto L3
    L6:
        return r1;
    L8:
        return -1;
    }

    private void mergeAndWorkAsNecessary(int r3, int r4, Subroutine r5, Frame r6, int[] r7) {
        Frame[] r0 = this.startFrames;
        Frame r1 = r0[r3];
        if (r1 == null) goto L10;
        if (r5 == null) goto L6;
        Frame r42 = r1.mergeWithSubroutineCaller(r6, r5.getStartBlock(), r4);
    L7:
        if (r42 == r1) goto L15;
        this.startFrames[r3] = r42;
        Bits.set(r7, r3);
        return;
    L15:
        return;
    L6:
        r42 = r1.mergeWith(r6);
        goto L7
    L10:
        if (r5 == null) goto L12;
        r0[r3] = r6.makeNewSubroutineStartFrame(r3, r4);
    L13:
        Bits.set(r7, r3);
        return;
    L12:
        r0[r3] = r6;
        goto L13
    }

    private void processBlock(ByteBlock r21, Frame r22, int[] r23) {
        ByteCatchList r6 = r21.getCatches();
        this.machine.startBlock(r6.toRopCatchList());
        Frame r4 = r22.copy();
        this.sim.simulate(r21, r4);
        r4.setImmutable();
        int r8 = this.machine.getExtraBlockCount();
        ArrayList<Insn> r9 = this.machine.getInsns();
        int r10 = r9.size();
        int r11 = r6.size();
        IntList r1 = r21.getSuccessors();
        if (this.machine.hasJsr() == false) goto L8;
        int r2 = r1.get(1);
        Subroutine[] r3 = this.subroutines;
        if (r3[r2] != null) goto L7;
        r3[r2] = new Subroutine(this, r2);
    L7:
        this.subroutines[r2].addCallerBlock(r21.getLabel());
        int[] r5 = r23;
        IntList r15 = r1;
        Subroutine r32 = this.subroutines[r2];
        int r12 = 1;
    L20:
        int r24 = r15.size();
    L21:
        if (r12 >= r24) goto L28;
        int r122 = r12;
        int r13 = r15.get(r122);
        int r16 = r24;
        mergeAndWorkAsNecessary(r13, r21.getLabel(), r32, r4, r5);     // Catch: SimException -> L25
        r5 = r23;
        r4 = r4;
        r12 = r122 + 1;
        r24 = r16;
    L25:
        e = move-exception;
        e.addContext("...while merging to block " + Hex.u2(r13));
        throw e;
    L28:
        int r162 = r24;
        Frame r14 = r4;
        if (r162 == 0) goto L31;
    L33:
        int r25 = r162;
    L34:
        if (r25 != 0) goto L37;
        int r33 = -1;
    L36:
        int r123 = r33;
        if (isSynchronized() == true) goto L43;
    L45:
        boolean r163 = false;
    L46:
        if (r163 == true) goto L50;
        if (r11 != 0) goto L50;
        Frame r18 = r14;
    L82:
        int r17 = r15.indexOf(r123);
    L83:
        if (r8 <= 0) goto L97;
        r10 = r10 - 1;
        Insn r26 = r9.get(r10);
        if (r26.getOpcode().getBranchingness() != 1) goto L87;
        boolean r34 = true;
    L89:
        if (r34 == false) goto L91;
        int r52 = 2;
    L92:
        InsnList r42 = new InsnList(r52);
        r42.set(0, r26);
        if (r34 == false) goto L95;
        r42.set(1, new PlainInsn(Rops.GOTO, r26.getPosition(), null, RegisterSpecList.EMPTY));
        IntList r27 = IntList.makeImmutable(r123);
    L96:
        r42.setImmutable();
        int r35 = getAvailableLabel();
        addBlock(new BasicBlock(r35, r42, r27, r123), r18.getSubroutines());
        r15 = r15.mutableCopy();
        r15.set(r17, r35);
        r15.setImmutable();
        r8 = r8 - 1;
        r123 = r35;
        goto L83
    L95:
        r27 = r15;
        goto L96
    L91:
        r52 = 1;
        goto L92
    L87:
        r34 = false;
        goto L89
    L97:
        int r53 = 0;
        if (r10 != 0) goto L100;
        Insn r19 = null;
    L101:
        if (r19 != null) goto L103;
    L104:
        if (r19 != null) goto L106;
        SourcePosition r110 = SourcePosition.NO_INFO;
    L107:
        r9.add(new PlainInsn(Rops.GOTO, r110, null, RegisterSpecList.EMPTY));
        r10 = r10 + 1;
    L108:
        InsnList r111 = new InsnList(r10);
    L109:
        if (r53 >= r10) goto L111;
        r111.set(r53, r9.get(r53));
        r53 = r53 + 1;
        goto L109
    L111:
        r111.setImmutable();
        addOrReplaceBlock(new BasicBlock(r21.getLabel(), r111, r15, r123), r18.getSubroutines());
        return;
    L106:
        r110 = r19.getPosition();
        goto L107
    L103:
        if (r19.getOpcode().getBranchingness() != 1) goto L108;
    L100:
        r19 = r9.get(r10 - 1);
    L50:
        r15 = new IntList(r25);
        int r28 = 0;
        boolean r36 = false;
    L51:
        if (r28 >= r11) goto L70;
        ByteCatchList.Item r43 = r6.get(r28);
        CstType r54 = r43.getExceptionClass();
        int r44 = r43.getHandlerPc();
        if (r54 != CstType.OBJECT) goto L55;
        boolean r132 = true;
    L56:
        boolean r133 = r132 | r36;
        Frame r45 = r14.makeExceptionHandlerStartFrame(r54);
        int r172 = r28;
        Frame r182 = r14;
        int r112 = r44;
        mergeAndWorkAsNecessary(r112, r21.getLabel(), null, r45, r23);     // Catch: SimException -> L64
        CatchInfo r29 = this.catchInfos[r112];
        if (r29 != null) goto L63;
        r29 = new CatchInfo(this, null);
        this.catchInfos[r112] = r29;
    L63:
        r15.add(r29.getSetup(r54.getClassType()).getLabel());
        r28 = r172 + 1;
        r36 = r133;
        r14 = r182;
    L64:
        e = e;
    L68:
        e.addContext("...while merging exception to block " + Hex.u2(r112));
        throw e;
    L66:
        e = e;
        r112 = r44;
        goto L68
    L55:
        r132 = false;
        goto L56
    L70:
        r18 = r14;
        if (r163 == false) goto L79;
        if (r36 == true) goto L79;
        r15.add(getSpecialLabel(SYNCH_CATCH_1));
        this.synchNeedsExceptionHandler = true;
        int r210 = (r10 - r8) - 1;
    L74:
        if (r210 >= r10) goto L79;
        Insn r113 = r9.get(r210);
        if (r113.canThrow() == false) goto L78;
        r9.set(r210, r113.withAddedCatch(Type.OBJECT));
    L78:
        r210 = r210 + 1;
    L79:
        if (r123 < 0) goto L81;
        r15.add(r123);
    L81:
        r15.setImmutable();
        goto L82
    L43:
        if (this.machine.canThrow() == false) goto L45;
        r163 = true;
        goto L46
    L37:
        r33 = this.machine.getPrimarySuccessorIndex();
        if (r33 < 0) goto L36;
        r33 = r15.get(r33);
        goto L36
    L31:
        if (this.machine.returns() == false) goto L33;
        r15 = IntList.makeImmutable(getSpecialLabel(-2));
        r25 = 1;
        goto L34
    L8:
        boolean r211 = this.machine.hasRet();
        RopperMachine r37 = this.machine;
        if (r211 == false) goto L16;
        int r114 = r37.getReturnAddress().getSubroutineAddress();
        Subroutine[] r212 = this.subroutines;
        Subroutine r38 = r212[r114];
        if (r38 != null) goto L13;
        r212[r114] = new Subroutine(this, r114, r21.getLabel());
    L14:
        IntList r213 = this.subroutines[r114].getSuccessors();
        r5 = r23;
        this.subroutines[r114].mergeToSuccessors(r4, r5);
        r12 = r213.size();
        r15 = r213;
    L15:
        r32 = null;
        goto L20
    L13:
        r38.addRetBlock(r21.getLabel());
        goto L14
    L16:
        r5 = r23;
        if (r37.wereCatchesUsed() == false) goto L19;
        r15 = r1;
        r12 = r11;
        goto L15
    L19:
        r15 = r1;
        r32 = null;
        r12 = 0;
        goto L20
    }

    private void removeBlockAndSpecialSuccessors(int r6) {
        int r0 = getMinimumUnreservedLabel();
        IntList r1 = this.result.get(r6).getSuccessors();
        int r2 = r1.size();
        this.result.remove(r6);
        this.resultSubroutines.remove(r6);
        int r62 = 0;
    L3:
        if (r62 >= r2) goto L12;
        int r3 = r1.get(r62);
        if (r3 < r0) goto L11;
        int r4 = labelToResultIndex(r3);
        if (r4 < 0) goto L9;
        removeBlockAndSpecialSuccessors(r4);
        goto L11
    L9:
        Instance$$ExternalSyntheticBUOutline0.m("Invalid label ", Hex.u2(r3));
        return;
    L11:
        r62 = r62 + 1;
        goto L3
    }

    private void setFirstFrame() {
        Prototype r0 = this.method.getEffectiveDescriptor();
        this.startFrames[0].initializeWithParameters(r0.getParameterTypes());
        this.startFrames[0].setImmutable();
    }

    private Subroutine subroutineFromRetBlock(int r4) {
        int r0 = this.subroutines.length - 1;
    L3:
        if (r0 < 0) goto L10;
        Subroutine r1 = this.subroutines[r0];
        if (r1 == null) goto L9;
        if (Subroutine.access$1300(r1).get(r4) == false) goto L9;
        return r1;
    L9:
        r0 = r0 - 1;
        goto L3
    L10:
        return null;
    }

    public int getFirstTempStackReg() {
        int r0 = getNormalRegCount();
        if (isSynchronized() == true) goto L5;
        return r0;
    L5:
        return r0 + 1;
    }
}
