package com.android.dx.ssa.back;

import com.android.dx.rop.code.CstInsn;
import com.android.dx.rop.code.LocalItem;
import com.android.dx.rop.code.RegisterSpec;
import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.rop.code.Rop;
import com.android.dx.rop.cst.CstInteger;
import com.android.dx.ssa.InterferenceRegisterMapper;
import com.android.dx.ssa.NormalSsaInsn;
import com.android.dx.ssa.Optimizer;
import com.android.dx.ssa.PhiInsn;
import com.android.dx.ssa.RegisterMapper;
import com.android.dx.ssa.SsaInsn;
import com.android.dx.ssa.SsaMethod;
import com.android.dx.util.IntIterator;
import com.android.dx.util.IntSet;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public class FirstFitLocalCombiningAllocator extends RegisterAllocator {
    private static final boolean DEBUG = false;
    private final ArrayList<NormalSsaInsn> invokeRangeInsns;
    private final Map<LocalItem, ArrayList<RegisterSpec>> localVariables;
    private final InterferenceRegisterMapper mapper;
    private final boolean minimizeRegisters;
    private final ArrayList<NormalSsaInsn> moveResultPseudoInsns;
    private final int paramRangeEnd;
    private final ArrayList<PhiInsn> phiInsns;
    private final BitSet reservedRopRegs;
    private final BitSet ssaRegsMapped;
    private final BitSet usedRopRegs;

    /* renamed from: com.android.dx.ssa.back.FirstFitLocalCombiningAllocator$1, reason: invalid class name */
    public class AnonymousClass1 implements SsaInsn.Visitor {
        final /* synthetic */ FirstFitLocalCombiningAllocator this$0;

        public AnonymousClass1(FirstFitLocalCombiningAllocator r1) {
            this.this$0 = r1;
        }

        private void processInsn(SsaInsn r5) {
            RegisterSpec r0 = r5.getLocalAssignment();
            if (r0 == null) goto L9;
            LocalItem r1 = r0.getLocalItem();
            ArrayList r2 = (ArrayList) FirstFitLocalCombiningAllocator.access$200(this.this$0).get(r1);
            if (r2 != null) goto L7;
            r2 = new ArrayList();
            FirstFitLocalCombiningAllocator.access$200(this.this$0).put(r1, r2);
        L7:
            r2.add(r0);
        L9:
            if ((r5 instanceof NormalSsaInsn) == false) goto L19;
            if (r5.getOpcode().getOpcode() != 56) goto L15;
            FirstFitLocalCombiningAllocator.access$300(this.this$0).add((NormalSsaInsn) r5);
            return;
        L15:
            if (Optimizer.getAdvice().requiresSourcesInOrder(r5.getOriginalRopInsn().getOpcode(), r5.getSources()) == false) goto L22;
            FirstFitLocalCombiningAllocator.access$400(this.this$0).add((NormalSsaInsn) r5);
            return;
        L22:
            return;
        L19:
            if ((r5 instanceof PhiInsn) == false) goto L23;
            FirstFitLocalCombiningAllocator.access$500(this.this$0).add((PhiInsn) r5);
            return;
        }

        @Override // com.android.dx.ssa.SsaInsn.Visitor
        public void visitMoveInsn(NormalSsaInsn r1) {
            processInsn(r1);
        }

        @Override // com.android.dx.ssa.SsaInsn.Visitor
        public void visitNonMoveInsn(NormalSsaInsn r1) {
            processInsn(r1);
        }

        @Override // com.android.dx.ssa.SsaInsn.Visitor
        public void visitPhiInsn(PhiInsn r1) {
            processInsn(r1);
        }
    }

    public enum Alignment extends Enum<Alignment> {
        private static final /* synthetic */ Alignment[] $VALUES = null;
        public static final Alignment EVEN = null;
        public static final Alignment ODD = null;
        public static final Alignment UNSPECIFIED = null;

        /* renamed from: com.android.dx.ssa.back.FirstFitLocalCombiningAllocator$Alignment$1, reason: invalid class name */
        public enum AnonymousClass1 extends Alignment {
            public AnonymousClass1(String r2, int r3) {
                super(r2, r3, null);
            }

            @Override // com.android.dx.ssa.back.FirstFitLocalCombiningAllocator.Alignment
            public int nextClearBit(BitSet r1, int r2) {
                int r0 = r1.nextClearBit(r2);
            L4:
                if (FirstFitLocalCombiningAllocator.access$100(r0) == true) goto L6;
                r0 = r1.nextClearBit(r0 + 1);
                goto L4
            L6:
                return r0;
            }
        }

        /* renamed from: com.android.dx.ssa.back.FirstFitLocalCombiningAllocator$Alignment$2, reason: invalid class name */
        public enum AnonymousClass2 extends Alignment {
            public AnonymousClass2(String r2, int r3) {
                super(r2, r3, null);
            }

            @Override // com.android.dx.ssa.back.FirstFitLocalCombiningAllocator.Alignment
            public int nextClearBit(BitSet r1, int r2) {
                int r0 = r1.nextClearBit(r2);
            L4:
                if (FirstFitLocalCombiningAllocator.access$100(r0) == false) goto L6;
                r0 = r1.nextClearBit(r0 + 1);
                goto L4
            L6:
                return r0;
            }
        }

        /* renamed from: com.android.dx.ssa.back.FirstFitLocalCombiningAllocator$Alignment$3, reason: invalid class name */
        public enum AnonymousClass3 extends Alignment {
            public AnonymousClass3(String r2, int r3) {
                super(r2, r3, null);
            }

            @Override // com.android.dx.ssa.back.FirstFitLocalCombiningAllocator.Alignment
            public int nextClearBit(BitSet r1, int r2) {
                return r1.nextClearBit(r2);
            }
        }

        static {
            AnonymousClass1 r0 = new AnonymousClass1("EVEN", 0);
            EVEN = r0;
            AnonymousClass2 r1 = new AnonymousClass2("ODD", 1);
            ODD = r1;
            AnonymousClass3 r3 = new AnonymousClass3("UNSPECIFIED", 2);
            UNSPECIFIED = r3;
            $VALUES = new Alignment[]{r0, r1, r3};
        }

        Alignment(String r1, int r2) {
        }

        /* synthetic */ Alignment(String r1, int r2, AnonymousClass1 r3) {
            this(r1, r2);
        }

        public static Alignment valueOf(String r1) {
            return (Alignment) Enum.valueOf(Alignment.class, r1);
        }

        public static Alignment[] values() {
            return (Alignment[]) $VALUES.clone();
        }

        public abstract int nextClearBit(BitSet r1, int r2);
    }

    public static class Multiset {
        private final int[] count;
        private final int[] reg;
        private int size;

        public Multiset(int r2) {
            this.reg = new int[r2];
            this.count = new int[r2];
            this.size = 0;
        }

        public void add(int r5) {
            int r0 = 0;
        L3:
            int r1 = this.size;
            int[] r2 = this.reg;
            if (r0 >= r1) goto L10;
            if (r2[r0] == r5) goto L7;
            r0 = r0 + 1;
            goto L3
        L7:
            int[] r4 = this.count;
            r4[r0] = r4[r0] + 1;
            return;
        L10:
            r2[r1] = r5;
            this.count[r1] = 1;
            this.size = r1 + 1;
        }

        public int getAndRemoveHighestCount() {
            int r0 = -1;
            int r2 = -1;
            int r3 = 0;
            int r4 = 0;
        L3:
            int r5 = this.size;
            int[] r6 = this.count;
            if (r3 >= r5) goto L9;
            int r52 = r6[r3];
            if (r4 >= r52) goto L8;
            r2 = this.reg[r3];
            r0 = r3;
            r4 = r52;
        L8:
            r3 = r3 + 1;
            goto L3
        L9:
            r6[r0] = 0;
            return r2;
        }

        public int getSize() {
            return this.size;
        }
    }

    public FirstFitLocalCombiningAllocator(SsaMethod r3, InterferenceGraph r4, boolean r5) {
        super(r3, r4);
        this.ssaRegsMapped = new BitSet(r3.getRegCount());
        this.mapper = new InterferenceRegisterMapper(r4, r3.getRegCount());
        this.minimizeRegisters = r5;
        int r32 = r3.getParamWidth();
        this.paramRangeEnd = r32;
        BitSet r42 = new BitSet(r32 * 2);
        this.reservedRopRegs = r42;
        r42.set(0, r32);
        this.usedRopRegs = new BitSet(r32 * 2);
        this.localVariables = new TreeMap();
        this.moveResultPseudoInsns = new ArrayList();
        this.invokeRangeInsns = new ArrayList();
        this.phiInsns = new ArrayList();
    }

    public static /* synthetic */ boolean access$100(int r0) {
        return isEven(r0);
    }

    public static /* synthetic */ Map access$200(FirstFitLocalCombiningAllocator r0) {
        return r0.localVariables;
    }

    public static /* synthetic */ ArrayList access$300(FirstFitLocalCombiningAllocator r0) {
        return r0.moveResultPseudoInsns;
    }

    public static /* synthetic */ ArrayList access$400(FirstFitLocalCombiningAllocator r0) {
        return r0.invokeRangeInsns;
    }

    public static /* synthetic */ ArrayList access$500(FirstFitLocalCombiningAllocator r0) {
        return r0.phiInsns;
    }

    private void addMapping(RegisterSpec r4, int r5) {
        int r0 = r4.getReg();
        if (this.ssaRegsMapped.get(r0) == false) goto L5;
    L8:
        GlShader$$ExternalSyntheticBUOutline1.m("attempt to add invalid register mapping");
        return;
    L5:
        if (canMapReg(r4, r5) == false) goto L8;
        int r1 = r4.getCategory();
        this.mapper.addMapping(r4.getReg(), r5, r1);
        this.ssaRegsMapped.set(r0);
        this.usedRopRegs.set(r5, r1 + r5);
    }

    private void adjustAndMapSourceRangeRange(NormalSsaInsn r13) {
        int r0 = findRangeAndAdjust(r13);
        RegisterSpecList r132 = r13.getSources();
        int r1 = r132.size();
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L17;
        RegisterSpec r4 = r132.get(r3);
        int r5 = r4.getReg();
        int r6 = r4.getCategory();
        int r7 = r0 + r6;
        if (this.ssaRegsMapped.get(r5) == true) goto L16;
        LocalItem r52 = getLocalItemForReg(r5);
        addMapping(r4, r0);
        if (r52 == null) goto L16;
        markReserved(r0, r6);
        ArrayList<RegisterSpec> r42 = this.localVariables.get(r52);
        int r53 = r42.size();
        int r8 = 0;
    L10:
        if (r8 >= r53) goto L16;
        RegisterSpec r9 = r42.get(r8);
        if ((-1) != r132.indexOfRegister(r9.getReg())) goto L15;
        tryMapReg(r9, r0, r6);
    L15:
        r8 = r8 + 1;
    L16:
        r3 = r3 + 1;
        r0 = r7;
        goto L3
    }

    private void analyzeInstructions() {
        this.ssaMeth.forEachInsn(new AnonymousClass1(this));
    }

    private boolean canMapReg(RegisterSpec r2, int r3) {
        if (spansParamRange(r3, r2.getCategory()) == false) goto L5;
        return false;
    L5:
        if (this.mapper.interferes(r2, r3) == true) goto L10;
        return true;
    L10:
        return false;
    }

    private boolean canMapRegs(ArrayList<RegisterSpec> r7, int r8) {
        int r0 = r7.size();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L10;
        RegisterSpec r3 = r7.get(r2);
        r2 = r2 + 1;
        RegisterSpec r32 = r3;
        if (this.ssaRegsMapped.get(r32.getReg()) == true) goto L3;
        if (canMapReg(r32, r8) == true) goto L3;
        return false;
    L10:
        return true;
    }

    private int findAnyFittingRange(NormalSsaInsn r9, int r10, int[] r11, BitSet r12) {
        Alignment r0 = Alignment.UNSPECIFIED;
        int r1 = r11.length;
        int r2 = 0;
        int r3 = 0;
        int r4 = 0;
        int r5 = 0;
    L3:
        if (r2 >= r1) goto L13;
        if (r11[r2] == 2) goto L7;
        r5 = r5 + 1;
    L12:
        r2 = r2 + 1;
        goto L3
    L7:
        if (isEven(r5) == false) goto L9;
        r4 = r4 + 1;
    L10:
        r5 = r5 + 2;
        goto L12
    L9:
        r3 = r3 + 1;
        goto L10
    L13:
        if (r3 > r4) goto L15;
        if (r4 > 0) goto L20;
    L23:
        int r13 = this.paramRangeEnd;
    L24:
        int r14 = findNextUnreservedRopReg(r13, r10, r0);
        if (fitPlanForRange(r14, r9, r11, r12) >= 0) goto L26;
        r13 = r14 + 1;
        r12.clear();
        goto L24
    L26:
        return r14;
    L20:
        if (isEven(this.paramRangeEnd) == false) goto L22;
        r0 = Alignment.EVEN;
        goto L23
    L22:
        r0 = Alignment.ODD;
        goto L23
    L15:
        if (isEven(this.paramRangeEnd) == false) goto L17;
        r0 = Alignment.ODD;
        goto L23
    L17:
        r0 = Alignment.EVEN;
        goto L23
    }

    private int findNextUnreservedRopReg(int r2, int r3) {
        return findNextUnreservedRopReg(r2, r3, getAlignment(r3));
    }

    private int findNextUnreservedRopReg(int r4, int r5, Alignment r6) {
        int r42 = r6.nextClearBit(this.reservedRopRegs, r4);
    L3:
        int r0 = 1;
    L4:
        if (r0 >= r5) goto L8;
        if (this.reservedRopRegs.get(r42 + r0) == true) goto L8;
        r0 = r0 + 1;
    L8:
        if (r0 == r5) goto L9;
        r42 = r6.nextClearBit(this.reservedRopRegs, r42 + r0);
        goto L3
    L9:
        return r42;
    }

    private int findRangeAndAdjust(NormalSsaInsn r18) {
        RegisterSpecList r2 = r18.getSources();
        int r3 = r2.size();
        int[] r4 = new int[r3];
        int r6 = 0;
        int r7 = 0;
    L3:
        if (r6 >= r3) goto L5;
        int r8 = r2.get(r6).getCategory();
        r4[r6] = r8;
        r7 = r7 + r8;
        r6 = r6 + 1;
        goto L3
    L5:
        int r82 = Integer.MIN_VALUE;
        BitSet r9 = null;
        int r12 = -1;
        int r10 = 0;
        int r11 = 0;
    L6:
        if (r10 >= r3) goto L27;
        int r13 = r2.get(r10).getReg();
        if (r10 == 0) goto L11;
        r11 = r11 - r4[r10 - 1];
    L11:
        if (this.ssaRegsMapped.get(r13) == false) goto L26;
        int r132 = this.mapper.oldToNew(r13) + r11;
        if (r132 < 0) goto L26;
        if (spansParamRange(r132, r7) == true) goto L26;
        BitSet r14 = new BitSet(r3);
        int r15 = fitPlanForRange(r132, r18, r4, r14);
        if (r15 < 0) goto L26;
        int r5 = r15 - r14.cardinality();
        if (r5 <= r82) goto L24;
        r82 = r5;
        r12 = r132;
        r9 = r14;
    L24:
        if (r15 == r7) goto L27;
    L26:
        r10 = r10 + 1;
    L27:
        if (r12 != (-1)) goto L29;
        r9 = new BitSet(r3);
        r12 = findAnyFittingRange(r18, r7, r4, r9);
    L29:
        int r32 = r9.nextSetBit(0);
    L30:
        if (r32 < 0) goto L32;
        r18.changeOneSource(r32, insertMoveBefore(r18, r2.get(r32)));
        r32 = r9.nextSetBit(r32 + 1);
        goto L30
    L32:
        return r12;
    }

    private int findRopRegForLocal(int r5, int r6) {
        Alignment r0 = getAlignment(r6);
        int r52 = r0.nextClearBit(this.usedRopRegs, r5);
    L3:
        int r1 = 1;
    L4:
        if (r1 >= r6) goto L8;
        if (this.usedRopRegs.get(r52 + r1) == true) goto L8;
        r1 = r1 + 1;
    L8:
        if (r1 == r6) goto L9;
        r52 = r0.nextClearBit(this.usedRopRegs, r52 + r1);
        goto L3
    L9:
        return r52;
    }

    private int fitPlanForRange(int r11, NormalSsaInsn r12, int[] r13, BitSet r14) {
        RegisterSpecList r0 = r12.getSources();
        int r1 = r0.size();
        RegisterSpecList r122 = ssaSetToSpecs(r12.getBlock().getLiveOutRegs());
        BitSet r2 = new BitSet(this.ssaMeth.getRegCount());
        int r3 = 0;
        int r4 = 0;
    L3:
        if (r3 >= r1) goto L29;
        RegisterSpec r5 = r0.get(r3);
        int r6 = r5.getReg();
        int r7 = r13[r3];
        if (r3 == 0) goto L8;
        r11 = r11 + r13[r3 - 1];
    L8:
        if (this.ssaRegsMapped.get(r6) == false) goto L13;
        if (this.mapper.oldToNew(r6) != r11) goto L13;
    L11:
        r4 = r4 + r7;
    L27:
        r2.set(r6);
        r3 = r3 + 1;
    L13:
        if (rangeContainsReserved(r11, r7) == true) goto L14;
        if (this.ssaRegsMapped.get(r6) == true) goto L23;
        if (canMapReg(r5, r11) == false) goto L23;
        if (r2.get(r6) == false) goto L11;
    L23:
        if (this.mapper.areAnyPinned(r122, r11, r7) == true) goto L28;
        if (this.mapper.areAnyPinned(r0, r11, r7) == true) goto L28;
        r14.set(r3);
    L28:
        return -1;
    L14:
        return -1;
    L29:
        return r4;
    }

    private Alignment getAlignment(int r3) {
        Alignment r0 = Alignment.UNSPECIFIED;
        if (r3 == 2) goto L5;
        return r0;
    L5:
        if (isEven(this.paramRangeEnd) == false) goto L9;
        return Alignment.EVEN;
    L9:
        return Alignment.ODD;
    }

    private LocalItem getLocalItemForReg(int r6) {
        Iterator<Map.Entry<LocalItem, ArrayList<RegisterSpec>>> r5 = this.localVariables.entrySet().iterator();
    L4:
        if (r5.hasNext() == false) goto L11;
        Map.Entry<LocalItem, ArrayList<RegisterSpec>> r0 = r5.next();
        ArrayList<RegisterSpec> r1 = r0.getValue();
        int r2 = r1.size();
        int r3 = 0;
    L6:
        if (r3 >= r2) goto L4;
        RegisterSpec r4 = r1.get(r3);
        r3 = r3 + 1;
        if (r4.getReg() != r6) goto L6;
        return r0.getKey();
    L11:
        return null;
    }

    private int getParameterIndexForReg(int r3) {
        SsaInsn r2 = this.ssaMeth.getDefinitionForRegister(r3);
        if (r2 != null) goto L5;
        return -1;
    L5:
        Rop r0 = r2.getOpcode();
        if (r0 != null) goto L8;
    L11:
        return -1;
    L8:
        if (r0.getOpcode() != 3) goto L11;
        return ((CstInteger) ((CstInsn) r2.getOriginalRopInsn()).getConstant()).getValue();
    }

    private void handleCheckCastResults() {
        ArrayList<NormalSsaInsn> r0 = this.moveResultPseudoInsns;
        int r1 = r0.size();
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L30;
        NormalSsaInsn r4 = r0.get(r3);
        r3 = r3 + 1;
        NormalSsaInsn r42 = r4;
        RegisterSpec r5 = r42.getResult();
        int r6 = r5.getReg();
        BitSet r43 = r42.getBlock().getPredecessors();
        boolean r8 = true;
        if (r43.cardinality() != 1) goto L3;
        ArrayList<SsaInsn> r44 = this.ssaMeth.getBlocks().get(r43.nextSetBit(0)).getInsns();
        SsaInsn r45 = r44.get(r44.size() - 1);
        if (r45.getOpcode().getOpcode() != 43) goto L3;
        RegisterSpec r7 = r45.getSources().get(0);
        int r9 = r7.getReg();
        int r10 = r7.getCategory();
        boolean r11 = this.ssaRegsMapped.get(r6);
        boolean r12 = this.ssaRegsMapped.get(r9);
        if (((!r12) & r11) == false) goto L14;
        r12 = tryMapReg(r7, this.mapper.oldToNew(r6), r10);
    L14:
        if (((!r11) & r12) == false) goto L16;
        r11 = tryMapReg(r5, this.mapper.oldToNew(r9), r10);
    L16:
        if (r11 == false) goto L18;
        if (r12 == false) goto L18;
    L23:
        if (r45.getOriginalRopInsn().getCatches().size() != 0) goto L26;
        r8 = false;
    L26:
        int r52 = this.mapper.oldToNew(r6);
        if (r52 == this.mapper.oldToNew(r9)) goto L3;
        if (r8 == true) goto L3;
        ((NormalSsaInsn) r45).changeOneSource(0, insertMoveBefore(r45, r7));
        addMapping(r45.getSources().get(0), r52);
    L18:
        int r112 = findNextUnreservedRopReg(this.paramRangeEnd, r10);
        ArrayList<RegisterSpec> r122 = new ArrayList(2);
        r122.add(r5);
        r122.add(r7);
    L20:
        if (tryMapRegs(r122, r112, r10, false) == true) goto L23;
        r112 = findNextUnreservedRopReg(r112 + 1, r10);
        goto L20
    }

    private void handleInvokeRangeInsns() {
        ArrayList<NormalSsaInsn> r0 = this.invokeRangeInsns;
        int r1 = r0.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        NormalSsaInsn r3 = r0.get(r2);
        r2 = r2 + 1;
        adjustAndMapSourceRangeRange(r3);
        goto L3
    }

    private void handleLocalAssociatedOther() {
        Iterator<ArrayList<RegisterSpec>> r0 = this.localVariables.values().iterator();
    L4:
        if (r0.hasNext() == false) goto L19;
        ArrayList<RegisterSpec> r1 = r0.next();
        int r2 = this.paramRangeEnd;
        boolean r4 = false;
    L6:
        int r5 = r1.size();
        int r7 = 0;
        int r8 = 1;
    L7:
        if (r7 >= r5) goto L13;
        RegisterSpec r9 = r1.get(r7);
        int r10 = r9.getCategory();
        if (this.ssaRegsMapped.get(r9.getReg()) == true) goto L12;
        if (r10 <= r8) goto L12;
        r8 = r10;
    L12:
        r7 = r7 + 1;
        goto L7
    L13:
        int r22 = findRopRegForLocal(r2, r8);
        if (canMapRegs(r1, r22) == false) goto L16;
        r4 = tryMapRegs(r1, r22, r8, true);
    L16:
        r2 = r22 + 1;
        if (r4 == false) goto L6;
    }

    private void handleLocalAssociatedParams() {
        Iterator<ArrayList<RegisterSpec>> r0 = this.localVariables.values().iterator();
    L4:
        if (r0.hasNext() == false) goto L14;
        ArrayList<RegisterSpec> r1 = r0.next();
        int r2 = r1.size();
        int r3 = 0;
        int r4 = -1;
        int r5 = 0;
    L6:
        if (r5 >= r2) goto L11;
        RegisterSpec r42 = r1.get(r5);
        int r6 = getParameterIndexForReg(r42.getReg());
        if (r6 >= 0) goto L9;
        r5 = r5 + 1;
        r4 = r6;
        goto L6
    L9:
        r3 = r42.getCategory();
        addMapping(r42, r6);
        r4 = r6;
    L11:
        if (r4 < 0) goto L4;
        tryMapRegs(r1, r4, r3, true);
        goto L4
    }

    private void handleNormalUnassociated() {
        int r0 = this.ssaMeth.getRegCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L16;
        if (this.ssaRegsMapped.get(r1) == true) goto L15;
        RegisterSpec r2 = getDefinitionSpecForSsaReg(r1);
        if (r2 == null) goto L15;
        int r3 = r2.getCategory();
        int r4 = findNextUnreservedRopReg(this.paramRangeEnd, r3);
    L12:
        if (canMapReg(r2, r4) == true) goto L14;
        r4 = findNextUnreservedRopReg(r4 + 1, r3);
        goto L12
    L14:
        addMapping(r2, r4);
    L15:
        r1 = r1 + 1;
        goto L3
    }

    private void handlePhiInsns() {
        ArrayList<PhiInsn> r0 = this.phiInsns;
        int r1 = r0.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        PhiInsn r3 = r0.get(r2);
        r2 = r2 + 1;
        processPhiInsn(r3);
        goto L3
    }

    private void handleUnassociatedParameters() {
        int r0 = this.ssaMeth.getRegCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L11;
        if (this.ssaRegsMapped.get(r1) == true) goto L10;
        int r2 = getParameterIndexForReg(r1);
        RegisterSpec r3 = getDefinitionSpecForSsaReg(r1);
        if (r2 < 0) goto L10;
        addMapping(r3, r2);
    L10:
        r1 = r1 + 1;
        goto L3
    }

    private static boolean isEven(int r1) {
        if ((r1 & 1) != 0) goto L5;
        return true;
    L5:
        return false;
    }

    private boolean isThisPointerReg(int r1) {
        if (r1 == 0) goto L4;
        return false;
    L4:
        if (this.ssaMeth.isStatic() == true) goto L9;
        return true;
    L9:
        return false;
    }

    private void markReserved(int r2, int r3) {
        this.reservedRopRegs.set(r2, r3 + r2, true);
    }

    private void printLocalVars() {
        System.out.println("Printing local vars");
        Iterator<Map.Entry<LocalItem, ArrayList<RegisterSpec>>> r8 = this.localVariables.entrySet().iterator();
    L4:
        if (r8.hasNext() == false) goto L9;
        Map.Entry<LocalItem, ArrayList<RegisterSpec>> r0 = r8.next();
        StringBuilder r1 = new StringBuilder();
        r1.append('{');
        r1.append(' ');
        ArrayList<RegisterSpec> r3 = r0.getValue();
        int r4 = r3.size();
        int r5 = 0;
    L6:
        if (r5 >= r4) goto L8;
        RegisterSpec r6 = r3.get(r5);
        r5 = r5 + 1;
        r1.append('v');
        r1.append(r6.getReg());
        r1.append(' ');
        goto L6
    L8:
        r1.append('}');
        System.out.printf("Local: %s Registers: %s\n", new Object[]{r0.getKey(), r1});
        goto L4
    }

    private void processPhiInsn(PhiInsn r10) {
        RegisterSpec r0 = r10.getResult();
        int r1 = r0.getReg();
        int r2 = r0.getCategory();
        RegisterSpecList r102 = r10.getSources();
        int r3 = r102.size();
        ArrayList<RegisterSpec> r4 = new ArrayList();
        Multiset r5 = new Multiset(r3 + 1);
        if (this.ssaRegsMapped.get(r1) == false) goto L5;
        r5.add(this.mapper.oldToNew(r1));
    L6:
        int r12 = 0;
    L7:
        if (r12 >= r3) goto L13;
        RegisterSpec r6 = this.ssaMeth.getDefinitionForRegister(r102.get(r12).getReg()).getResult();
        int r7 = r6.getReg();
        if (this.ssaRegsMapped.get(r7) == false) goto L11;
        r5.add(this.mapper.oldToNew(r7));
    L12:
        r12 = r12 + 1;
        goto L7
    L11:
        r4.add(r6);
        goto L12
    L13:
        int r103 = 0;
    L15:
        if (r103 >= r5.getSize()) goto L17;
        tryMapRegs(r4, r5.getAndRemoveHighestCount(), r2, false);
        r103 = r103 + 1;
        goto L15
    L17:
        int r104 = findNextUnreservedRopReg(this.paramRangeEnd, r2);
    L19:
        if (tryMapRegs(r4, r104, r2, false) == true) goto L21;
        r104 = findNextUnreservedRopReg(r104 + 1, r2);
        goto L19
    L21:
        return;
    L5:
        r4.add(r0);
        goto L6
    }

    private boolean rangeContainsReserved(int r3, int r4) {
        int r0 = r3;
    L4:
        if (r0 >= (r3 + r4)) goto L10;
        if (this.reservedRopRegs.get(r0) == true) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        return true;
    L10:
        return false;
    }

    private boolean spansParamRange(int r1, int r2) {
        int r0 = this.paramRangeEnd;
        if (r1 < r0) goto L5;
        return false;
    L5:
        if ((r1 + r2) <= r0) goto L10;
        return true;
    L10:
        return false;
    }

    private boolean tryMapReg(RegisterSpec r2, int r3, int r4) {
        if (r2.getCategory() <= r4) goto L5;
        return false;
    L5:
        if (this.ssaRegsMapped.get(r2.getReg()) == false) goto L7;
        return false;
    L7:
        if (canMapReg(r2, r3) == false) goto L13;
        addMapping(r2, r3);
        return true;
    L13:
        return false;
    }

    private boolean tryMapRegs(ArrayList<RegisterSpec> r9, int r10, int r11, boolean r12) {
        int r0 = r9.size();
        boolean r2 = false;
        int r3 = 0;
    L4:
        if (r3 >= r0) goto L18;
        RegisterSpec r5 = r9.get(r3);
        r3 = r3 + 1;
        RegisterSpec r52 = r5;
        if (this.ssaRegsMapped.get(r52.getReg()) == true) goto L4;
        boolean r6 = tryMapReg(r52, r10, r11);
        if (r6 == false) goto L13;
        if (r2 == true) goto L13;
        r2 = false;
    L14:
        if (r6 == false) goto L4;
        if (r12 == false) goto L4;
        markReserved(r10, r52.getCategory());
    L13:
        r2 = true;
        goto L14
    L18:
        return !r2;
    }

    @Override // com.android.dx.ssa.back.RegisterAllocator
    public RegisterMapper allocateRegisters() {
        analyzeInstructions();
        handleLocalAssociatedParams();
        handleUnassociatedParameters();
        handleInvokeRangeInsns();
        handleLocalAssociatedOther();
        handleCheckCastResults();
        handlePhiInsns();
        handleNormalUnassociated();
        return this.mapper;
    }

    public RegisterSpecList ssaSetToSpecs(IntSet r5) {
        RegisterSpecList r0 = new RegisterSpecList(r5.elements());
        IntIterator r52 = r5.iterator();
        int r1 = 0;
    L4:
        if (r52.hasNext() == false) goto L6;
        r0.set(r1, getDefinitionSpecForSsaReg(r52.next()));
        r1 = r1 + 1;
        goto L4
    L6:
        return r0;
    }

    @Override // com.android.dx.ssa.back.RegisterAllocator
    public boolean wantsParamsMovedHigh() {
        return true;
    }
}
