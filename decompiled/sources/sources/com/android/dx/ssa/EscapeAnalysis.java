package com.android.dx.ssa;

import com.android.dx.rop.code.Exceptions;
import com.android.dx.rop.code.FillArrayDataInsn;
import com.android.dx.rop.code.Insn;
import com.android.dx.rop.code.PlainCstInsn;
import com.android.dx.rop.code.PlainInsn;
import com.android.dx.rop.code.RegisterSpec;
import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.rop.code.Rop;
import com.android.dx.rop.code.Rops;
import com.android.dx.rop.code.ThrowingCstInsn;
import com.android.dx.rop.code.ThrowingInsn;
import com.android.dx.rop.cst.Constant;
import com.android.dx.rop.cst.CstLiteralBits;
import com.android.dx.rop.cst.CstMethodRef;
import com.android.dx.rop.cst.CstNat;
import com.android.dx.rop.cst.CstString;
import com.android.dx.rop.cst.CstType;
import com.android.dx.rop.cst.TypedConstant;
import com.android.dx.rop.cst.Zeroes;
import com.android.dx.rop.type.StdTypeList;
import com.android.dx.rop.type.Type;
import com.android.dx.rop.type.TypeBearer;
import com.android.dx.ssa.SsaBasicBlock;
import com.android.dx.ssa.SsaInsn;
import com.android.dx.util.ToHuman;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: classes4.dex */
public class EscapeAnalysis {
    private final ArrayList<EscapeSet> latticeValues;
    private final int regCount;
    private final SsaMethod ssaMeth;

    /* renamed from: com.android.dx.ssa.EscapeAnalysis$1, reason: invalid class name */
    public class AnonymousClass1 extends RegisterMapper {
        final /* synthetic */ EscapeAnalysis this$0;
        final /* synthetic */ RegisterSpec val$result;
        final /* synthetic */ RegisterSpec val$source;

        public AnonymousClass1(EscapeAnalysis r1, RegisterSpec r2, RegisterSpec r3) {
            this.this$0 = r1;
            this.val$result = r2;
            this.val$source = r3;
        }

        @Override // com.android.dx.ssa.RegisterMapper
        public int getNewRegisterCount() {
            return EscapeAnalysis.access$000(this.this$0).getRegCount();
        }

        @Override // com.android.dx.ssa.RegisterMapper
        public RegisterSpec map(RegisterSpec r3) {
            if (r3.getReg() == this.val$result.getReg()) goto L5;
            return r3;
        L5:
            return this.val$source;
        }
    }

    /* renamed from: com.android.dx.ssa.EscapeAnalysis$2, reason: invalid class name */
    public class AnonymousClass2 implements SsaBasicBlock.Visitor {
        final /* synthetic */ EscapeAnalysis this$0;

        /* renamed from: com.android.dx.ssa.EscapeAnalysis$2$1, reason: invalid class name */
        public class AnonymousClass1 implements SsaInsn.Visitor {
            final /* synthetic */ AnonymousClass2 this$1;

            public AnonymousClass1(AnonymousClass2 r1) {
                this.this$1 = r1;
            }

            @Override // com.android.dx.ssa.SsaInsn.Visitor
            public void visitMoveInsn(NormalSsaInsn r1) {
            }

            @Override // com.android.dx.ssa.SsaInsn.Visitor
            public void visitNonMoveInsn(NormalSsaInsn r1) {
                EscapeAnalysis.access$100(this.this$1.this$0, r1);
            }

            @Override // com.android.dx.ssa.SsaInsn.Visitor
            public void visitPhiInsn(PhiInsn r1) {
            }
        }

        public AnonymousClass2(EscapeAnalysis r1) {
            this.this$0 = r1;
        }

        @Override // com.android.dx.ssa.SsaBasicBlock.Visitor
        public void visitBlock(SsaBasicBlock r1, SsaBasicBlock r2) {
            r1.forEachInsn(new AnonymousClass1(this));
        }
    }

    public static class EscapeSet {
        ArrayList<EscapeSet> childSets;
        EscapeState escape;
        ArrayList<EscapeSet> parentSets;
        BitSet regSet;
        boolean replaceableArray;

        public EscapeSet(int r2, int r3, EscapeState r4) {
            BitSet r0 = new BitSet(r3);
            this.regSet = r0;
            r0.set(r2);
            this.escape = r4;
            this.childSets = new ArrayList();
            this.parentSets = new ArrayList();
            this.replaceableArray = false;
        }
    }

    public enum EscapeState extends Enum<EscapeState> {
        private static final /* synthetic */ EscapeState[] $VALUES = null;
        public static final EscapeState GLOBAL = null;
        public static final EscapeState INTER = null;
        public static final EscapeState METHOD = null;
        public static final EscapeState NONE = null;
        public static final EscapeState TOP = null;

        static {
            EscapeState r0 = new EscapeState("TOP", 0);
            TOP = r0;
            EscapeState r1 = new EscapeState("NONE", 1);
            NONE = r1;
            EscapeState r2 = new EscapeState("METHOD", 2);
            METHOD = r2;
            EscapeState r3 = new EscapeState("INTER", 3);
            INTER = r3;
            EscapeState r4 = new EscapeState("GLOBAL", 4);
            GLOBAL = r4;
            $VALUES = new EscapeState[]{r0, r1, r2, r3, r4};
        }

        EscapeState(String r1, int r2) {
        }

        public static EscapeState valueOf(String r1) {
            return (EscapeState) Enum.valueOf(EscapeState.class, r1);
        }

        public static EscapeState[] values() {
            return (EscapeState[]) $VALUES.clone();
        }
    }

    private EscapeAnalysis(SsaMethod r1) {
        this.ssaMeth = r1;
        this.regCount = r1.getRegCount();
        this.latticeValues = new ArrayList();
    }

    public static /* synthetic */ SsaMethod access$000(EscapeAnalysis r0) {
        return r0.ssaMeth;
    }

    public static /* synthetic */ void access$100(EscapeAnalysis r0, SsaInsn r1) {
        r0.processInsn(r1);
    }

    private void addEdge(EscapeSet r1, EscapeSet r2) {
        if (r2.parentSets.contains(r1) == true) goto L6;
        r2.parentSets.add(r1);
    L6:
        if (r1.childSets.contains(r2) == true) goto L9;
        r1.childSets.add(r2);
        return;
    }

    private int findSetIndex(RegisterSpec r4) {
        int r0 = 0;
    L4:
        if (r0 >= this.latticeValues.size()) goto L9;
        if (this.latticeValues.get(r0).regSet.get(r4.getReg()) == true) goto L9;
        r0 = r0 + 1;
    L9:
        return r0;
    }

    private SsaInsn getInsnForMove(SsaInsn r2) {
        return this.ssaMeth.getBlocks().get(r2.getBlock().getPredecessors().nextSetBit(0)).getInsns().get(r1.size() - 1);
    }

    private SsaInsn getMoveForInsn(SsaInsn r2) {
        int r22 = r2.getBlock().getSuccessors().nextSetBit(0);
        return this.ssaMeth.getBlocks().get(r22).getInsns().get(0);
    }

    private void insertExceptionThrow(SsaInsn r13, RegisterSpec r14, HashSet<SsaInsn> r15) {
        CstType r5 = new CstType(Exceptions.TYPE_ArrayIndexOutOfBoundsException);
        RegisterSpecList r2 = RegisterSpecList.EMPTY;
        insertThrowingInsnBefore(r13, r2, null, 40, r5);
        SsaBasicBlock r12 = r13.getBlock();
        SsaBasicBlock r122 = r12.insertNewSuccessor(r12.getPrimarySuccessor());
        SsaInsn r7 = r122.getInsns().get(0);
        RegisterSpec r9 = RegisterSpec.make(this.ssaMeth.makeNewSsaReg(), r5);
        insertPlainInsnBefore(r7, r2, r9, 56, null);
        SsaBasicBlock r123 = r122.insertNewSuccessor(r122.getPrimarySuccessor());
        SsaInsn r72 = r123.getInsns().get(0);
        Constant r11 = new CstMethodRef(r5, new CstNat(new CstString("<init>"), new CstString("(I)V")));
        insertThrowingInsnBefore(r72, RegisterSpecList.make(r9, r14), null, 52, r11);
        r15.add(r72);
        SsaBasicBlock r124 = r123.insertNewSuccessor(r123.getPrimarySuccessor());
        SsaInsn r73 = r124.getInsns().get(0);
        insertThrowingInsnBefore(r73, RegisterSpecList.make(r9), null, 35, null);
        r124.replaceSuccessor(r124.getPrimarySuccessorIndex(), this.ssaMeth.getExitBlock().getIndex());
        r15.add(r73);
    }

    private void insertPlainInsnBefore(SsaInsn r8, RegisterSpecList r9, RegisterSpec r10, int r11, Constant r12) {
        Insn r0 = r8.getOriginalRopInsn();
        if (r11 != 56) goto L6;
        Rop r112 = Rops.opMoveResultPseudo(r10.getType());
    L5:
        Rop r2 = r112;
        if (r12 != null) goto L9;
        Insn r113 = new PlainInsn(r2, r0.getPosition(), r10, r9);
    L10:
        NormalSsaInsn r92 = new NormalSsaInsn(r113, r8.getBlock());
        ArrayList<SsaInsn> r102 = r8.getBlock().getInsns();
        r102.add(r102.lastIndexOf(r8), r92);
        this.ssaMeth.onInsnAdded(r92);
        return;
    L9:
        r113 = new PlainCstInsn(r2, r0.getPosition(), r10, r9, r12);
        goto L10
    L6:
        r112 = Rops.ropFor(r11, r10, r9, r12);
        goto L5
    }

    private void insertThrowingInsnBefore(SsaInsn r8, RegisterSpecList r9, RegisterSpec r10, int r11, Constant r12) {
        Insn r0 = r8.getOriginalRopInsn();
        Rop r2 = Rops.ropFor(r11, r10, r9, r12);
        if (r12 != null) goto L5;
        Insn r102 = new ThrowingInsn(r2, r0.getPosition(), r9, StdTypeList.EMPTY);
    L6:
        NormalSsaInsn r92 = new NormalSsaInsn(r102, r8.getBlock());
        ArrayList<SsaInsn> r103 = r8.getBlock().getInsns();
        r103.add(r103.lastIndexOf(r8), r92);
        this.ssaMeth.onInsnAdded(r92);
        return;
    L5:
        r102 = new ThrowingCstInsn(r2, r0.getPosition(), r9, StdTypeList.EMPTY, r12);
        goto L6
    }

    private void movePropagate() {
        int r1 = 0;
    L4:
        if (r1 >= this.ssaMeth.getRegCount()) goto L21;
        SsaInsn r2 = this.ssaMeth.getDefinitionForRegister(r1);
        if (r2 == null) goto L20;
        if (r2.getOpcode() == null) goto L20;
        if (r2.getOpcode().getOpcode() != 2) goto L20;
        ArrayList<SsaInsn>[] r3 = this.ssaMeth.getUseListCopy();
        RegisterSpec r4 = r2.getSources().get(0);
        RegisterSpec r22 = r2.getResult();
        if (r4.getReg() < this.regCount) goto L15;
    L17:
        AnonymousClass1 r5 = new AnonymousClass1(this, r22, r4);
        ArrayList<SsaInsn> r23 = r3[r22.getReg()];
        int r32 = r23.size();
        int r42 = 0;
    L18:
        if (r42 >= r32) goto L20;
        SsaInsn r6 = r23.get(r42);
        r42 = r42 + 1;
        r6.mapSourceRegisters(r5);
        goto L18
    L15:
        if (r22.getReg() >= this.regCount) goto L17;
    L20:
        r1 = r1 + 1;
        goto L4
    }

    public static void process(SsaMethod r1) {
        new EscapeAnalysis(r1).run();
    }

    private void processInsn(SsaInsn r5) {
        int r0 = r5.getOpcode().getOpcode();
        RegisterSpec r1 = r5.getResult();
        if (r0 != 56) goto L9;
        if (r1.getTypeBearer().getBasicType() != 9) goto L9;
        processRegister(r1, processMoveResultPseudoInsn(r5));
        return;
    L9:
        if (r0 != 3) goto L15;
        if (r1.getTypeBearer().getBasicType() != 9) goto L15;
        EscapeSet r52 = new EscapeSet(r1.getReg(), this.regCount, EscapeState.NONE);
        this.latticeValues.add(r52);
        processRegister(r1, r52);
        return;
    L15:
        if (r0 == 55) goto L17;
        return;
    L17:
        if (r1.getTypeBearer().getBasicType() != 9) goto L21;
        EscapeSet r53 = new EscapeSet(r1.getReg(), this.regCount, EscapeState.NONE);
        this.latticeValues.add(r53);
        processRegister(r1, r53);
        return;
    }

    private EscapeSet processMoveResultPseudoInsn(SsaInsn r5) {
        RegisterSpec r0 = r5.getResult();
        SsaInsn r52 = getInsnForMove(r5);
        int r1 = r52.getOpcode().getOpcode();
        if (r1 != 5) goto L5;
    L26:
        EscapeSet r53 = new EscapeSet(r0.getReg(), this.regCount, EscapeState.NONE);
    L27:
        this.latticeValues.add(r53);
        return r53;
    L5:
        if (r1 != 38) goto L7;
    L18:
        RegisterSpec r54 = r52.getSources().get(0);
        int r12 = findSetIndex(r54);
        if (r12 == this.latticeValues.size()) goto L23;
        EscapeSet r4 = this.latticeValues.get(r12);
        r4.regSet.set(r0.getReg());
        return r4;
    L23:
        if (r54.getType() != Type.KNOWN_NULL) goto L25;
        r53 = new EscapeSet(r0.getReg(), this.regCount, EscapeState.NONE);
        goto L27
    L25:
        r53 = new EscapeSet(r0.getReg(), this.regCount, EscapeState.GLOBAL);
        goto L27
    L7:
        if (r1 == 45) goto L18;
        if (r1 == 46) goto L17;
        switch(r1) {
            case 40: goto L26;
            case 41: goto L14;
            case 42: goto L14;
            case 43: goto L18;
            default: goto L11;
        };
    L11:
        return null;
    L14:
        if (r52.getSources().get(0).getTypeBearer().isConstant() == false) goto L16;
        r53 = new EscapeSet(r0.getReg(), this.regCount, EscapeState.NONE);
        r53.replaceableArray = true;
        goto L27
    L16:
        r53 = new EscapeSet(r0.getReg(), this.regCount, EscapeState.GLOBAL);
        goto L27
    L17:
        r53 = new EscapeSet(r0.getReg(), this.regCount, EscapeState.GLOBAL);
        goto L27
    }

    private void processPhiUse(SsaInsn r3, EscapeSet r4, ArrayList<RegisterSpec> r5) {
        int r0 = findSetIndex(r3.getResult());
        if (r0 == this.latticeValues.size()) goto L11;
        EscapeSet r32 = this.latticeValues.get(r0);
        if (r32 == r4) goto L13;
        r4.replaceableArray = false;
        r4.regSet.or(r32.regSet);
        if (r4.escape.compareTo(r32.escape) >= 0) goto L9;
        r4.escape = r32.escape;
    L9:
        replaceNode(r4, r32);
        this.latticeValues.remove(r0);
        return;
    L13:
        return;
    L11:
        r4.regSet.set(r3.getResult().getReg());
        r5.add(r3.getResult());
    }

    private void processRegister(RegisterSpec r5, EscapeSet r6) {
        ArrayList<RegisterSpec> r0 = new ArrayList();
        r0.add(r5);
    L4:
        if (r0.isEmpty() == true) goto L12;
        RegisterSpec r52 = r0.remove(r0.size() - 1);
        Iterator<SsaInsn> r1 = this.ssaMeth.getUseListForRegister(r52.getReg()).iterator();
    L7:
        if (r1.hasNext() == false) goto L4;
        SsaInsn r2 = r1.next();
        if (r2.getOpcode() == null) goto L10;
        processUse(r52, r2, r6, r0);
        goto L7
    L10:
        processPhiUse(r2, r6, r0);
        goto L7
    }

    private void processUse(RegisterSpec r5, SsaInsn r6, EscapeSet r7, ArrayList<RegisterSpec> r8) {
        int r0 = r6.getOpcode().getOpcode();
        if (r0 != 2) goto L5;
        r7.regSet.set(r6.getResult().getReg());
        r8.add(r6.getResult());
        return;
    L5:
        if (r0 != 33) goto L7;
    L50:
        r7.escape = EscapeState.INTER;
        return;
    L7:
        if (r0 == 35) goto L50;
        if (r0 != 43) goto L11;
    L46:
        EscapeState r4 = r7.escape;
        EscapeState r52 = EscapeState.METHOD;
        if (r4.compareTo(r52) >= 0) goto L59;
        r7.escape = r52;
        return;
    L59:
        return;
    L11:
        if (r0 == 7) goto L46;
        if (r0 == 8) goto L46;
        if (r0 == 38) goto L43;
        if (r0 == 39) goto L23;
        switch(r0) {
            case 47: goto L26;
            case 48: goto L20;
            case 49: goto L50;
            case 50: goto L50;
            case 51: goto L50;
            case 52: goto L50;
            case 53: goto L50;
            default: goto L60;
        };
    L20:
        r7.escape = EscapeState.GLOBAL;
        return;
    L60:
        return;
    L26:
        if (r6.getSources().get(0).getTypeBearer().getBasicType() != 9) goto L61;
        r7.replaceableArray = false;
        RegisterSpecList r62 = r6.getSources();
        if (r62.get(0).getReg() != r5.getReg()) goto L36;
        int r53 = findSetIndex(r62.get(1));
        if (r53 == this.latticeValues.size()) goto L54;
        EscapeSet r54 = this.latticeValues.get(r53);
        addEdge(r54, r7);
        if (r7.escape.compareTo(r54.escape) >= 0) goto L55;
        r7.escape = r54.escape;
        return;
    L55:
        return;
    L54:
        return;
    L36:
        int r55 = findSetIndex(r62.get(0));
        if (r55 == this.latticeValues.size()) goto L56;
        EscapeSet r56 = this.latticeValues.get(r55);
        addEdge(r7, r56);
        if (r56.escape.compareTo(r7.escape) >= 0) goto L57;
        r56.escape = r7.escape;
        return;
    L57:
        return;
    L56:
        return;
    L61:
        return;
    L23:
        if (r6.getSources().get(2).getTypeBearer().isConstant() == true) goto L26;
        r7.replaceableArray = false;
        goto L26
    L43:
        if (r6.getSources().get(1).getTypeBearer().isConstant() == true) goto L58;
        r7.replaceableArray = false;
        return;
    }

    private void replaceDef(SsaInsn r9, SsaInsn r10, int r11, ArrayList<RegisterSpec> r12) {
        Type r102 = r9.getResult().getType();
        int r0 = 0;
    L3:
        if (r0 >= r11) goto L5;
        Constant r7 = Zeroes.zeroFor(r102.getComponentType());
        int r2 = this.ssaMeth.makeNewSsaReg();
        RegisterSpec r5 = RegisterSpec.make(r2, (TypedConstant) r7);
        r12.add(r5);
        insertPlainInsnBefore(r9, RegisterSpecList.EMPTY, r5, 5, r7);
        r0 = r0 + 1;
        goto L3
    }

    private void replaceNode(EscapeSet r6, EscapeSet r7) {
        ArrayList<EscapeSet> r5 = r7.parentSets;
        int r0 = r5.size();
        int r1 = 0;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L5;
        EscapeSet r3 = r5.get(r2);
        r2 = r2 + 1;
        EscapeSet r32 = r3;
        r32.childSets.remove(r7);
        r32.childSets.add(r6);
        r6.parentSets.add(r32);
        goto L3
    L5:
        ArrayList<EscapeSet> r52 = r7.childSets;
        int r02 = r52.size();
    L6:
        if (r1 >= r02) goto L8;
        EscapeSet r22 = r52.get(r1);
        r1 = r1 + 1;
        EscapeSet r23 = r22;
        r23.parentSets.remove(r7);
        r23.parentSets.add(r6);
        r6.childSets.add(r23);
        goto L6
    }

    private void replaceUse(SsaInsn r10, SsaInsn r11, ArrayList<RegisterSpec> r12, HashSet<SsaInsn> r13) {
        int r0 = r12.size();
        int r1 = r10.getOpcode().getOpcode();
        if (r1 != 34) goto L5;
        ToHuman r102 = r11.getSources().get(0).getTypeBearer();
        SsaInsn r3 = getMoveForInsn(r10);
        insertPlainInsnBefore(r3, RegisterSpecList.EMPTY, r3.getResult(), 5, (Constant) r102);
        r13.add(r3);
        return;
    L5:
        if (r1 != 57) goto L7;
        ArrayList<Constant> r103 = ((FillArrayDataInsn) r10.getOriginalRopInsn()).getInitValues();
        int r112 = 0;
    L24:
        if (r112 >= r0) goto L30;
        RegisterSpec r5 = RegisterSpec.make(r12.get(r112).getReg(), (TypeBearer) r103.get(r112));
        insertPlainInsnBefore(r10, RegisterSpecList.EMPTY, r5, 5, r103.get(r112));
        r12.set(r112, r5);
        r112 = r112 + 1;
        goto L24
    L30:
        return;
    L7:
        if (r1 != 38) goto L9;
        SsaInsn r32 = getMoveForInsn(r10);
        RegisterSpecList r9 = r10.getSources();
        int r14 = ((CstLiteralBits) r9.get(1).getTypeBearer()).getIntBits();
        if (r14 >= r0) goto L20;
        RegisterSpec r92 = r12.get(r14);
        insertPlainInsnBefore(r32, RegisterSpecList.make(r92), r92.withReg(r32.getResult().getReg()), 2, null);
    L21:
        r13.add(r32);
        return;
    L20:
        insertExceptionThrow(r32, r9.get(1), r13);
        r13.add(r32.getBlock().getInsns().get(2));
        goto L21
    L9:
        if (r1 != 39) goto L26;
        RegisterSpecList r113 = r10.getSources();
        int r15 = ((CstLiteralBits) r113.get(2).getTypeBearer()).getIntBits();
        if (r15 >= r0) goto L15;
        RegisterSpec r114 = r113.get(0);
        RegisterSpec r52 = r114.withReg(r12.get(r15).getReg());
        insertPlainInsnBefore(r10, RegisterSpecList.make(r114), r52, 2, null);
        r12.set(r15, r52.withSimpleType());
        return;
    L15:
        insertExceptionThrow(r10, r113.get(2), r13);
        return;
    }

    private void run() {
        this.ssaMeth.forEachBlockDepthFirstDom(new AnonymousClass2(this));
        ArrayList<EscapeSet> r0 = this.latticeValues;
        int r1 = r0.size();
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L11;
        EscapeSet r4 = r0.get(r3);
        r3 = r3 + 1;
        EscapeSet r42 = r4;
        if (r42.escape == EscapeState.NONE) goto L3;
        ArrayList<EscapeSet> r5 = r42.childSets;
        int r6 = r5.size();
        int r7 = 0;
    L7:
        if (r7 >= r6) goto L3;
        EscapeSet r8 = r5.get(r7);
        r7 = r7 + 1;
        EscapeSet r82 = r8;
        if (r42.escape.compareTo(r82.escape) <= 0) goto L7;
        r82.escape = r42.escape;
        goto L7
    L11:
        scalarReplacement();
    }

    private void scalarReplacement() {
        ArrayList<EscapeSet> r0 = this.latticeValues;
        int r1 = r0.size();
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L14;
        EscapeSet r4 = r0.get(r3);
        r3 = r3 + 1;
        EscapeSet r42 = r4;
        if (r42.replaceableArray == false) goto L3;
        if (r42.escape != EscapeState.NONE) goto L3;
        int r43 = r42.regSet.nextSetBit(0);
        SsaInsn r5 = this.ssaMeth.getDefinitionForRegister(r43);
        SsaInsn r6 = getInsnForMove(r5);
        int r7 = ((CstLiteralBits) r6.getSources().get(0).getTypeBearer()).getIntBits();
        ArrayList<RegisterSpec> r8 = new ArrayList(r7);
        HashSet<SsaInsn> r9 = new HashSet();
        replaceDef(r5, r6, r7, r8);
        r9.add(r6);
        r9.add(r5);
        Iterator<SsaInsn> r44 = this.ssaMeth.getUseListForRegister(r43).iterator();
    L11:
        if (r44.hasNext() == false) goto L13;
        SsaInsn r52 = r44.next();
        replaceUse(r52, r6, r8, r9);
        r9.add(r52);
        goto L11
    L13:
        this.ssaMeth.deleteInsns(r9);
        this.ssaMeth.onInsnsChanged();
        SsaConverter.updateSsaMethod(this.ssaMeth, this.regCount);
        movePropagate();
        goto L3
    }
}
