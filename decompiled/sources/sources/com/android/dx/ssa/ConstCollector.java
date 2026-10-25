package com.android.dx.ssa;

import com.android.dx.rop.code.LocalItem;
import com.android.dx.rop.code.PlainCstInsn;
import com.android.dx.rop.code.PlainInsn;
import com.android.dx.rop.code.RegisterSpec;
import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.rop.code.Rop;
import com.android.dx.rop.code.Rops;
import com.android.dx.rop.code.SourcePosition;
import com.android.dx.rop.code.ThrowingCstInsn;
import com.android.dx.rop.cst.Constant;
import com.android.dx.rop.cst.TypedConstant;
import com.android.dx.rop.type.StdTypeList;
import com.android.dx.rop.type.TypeBearer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes4.dex */
public class ConstCollector {
    private static final boolean COLLECT_ONE_LOCAL = false;
    private static final boolean COLLECT_STRINGS = false;
    private static final int MAX_COLLECTED_CONSTANTS = 5;
    private final SsaMethod ssaMeth;

    /* renamed from: com.android.dx.ssa.ConstCollector$1, reason: invalid class name */
    public class AnonymousClass1 implements Comparator<Constant> {
        final /* synthetic */ ConstCollector this$0;
        final /* synthetic */ HashMap val$countUses;

        public AnonymousClass1(ConstCollector r1, HashMap r2) {
            this.this$0 = r1;
            this.val$countUses = r2;
        }

        /* renamed from: compare, reason: avoid collision after fix types in other method */
        public int compare2(Constant r2, Constant r3) {
            int r0 = ((Integer) this.val$countUses.get(r3)).intValue() - ((Integer) this.val$countUses.get(r2)).intValue();
            if (r0 == 0) goto L5;
            return r0;
        L5:
            return r2.compareTo2(r3);
        }

        @Override // java.util.Comparator
        public /* bridge */ /* synthetic */ int compare(Constant r1, Constant r2) {
            return compare2(r1, r2);
        }

        @Override // java.util.Comparator
        public boolean equals(Object r1) {
            if (r1 != this) goto L5;
            return true;
        L5:
            return false;
        }
    }

    /* renamed from: com.android.dx.ssa.ConstCollector$2, reason: invalid class name */
    public class AnonymousClass2 extends RegisterMapper {
        final /* synthetic */ ConstCollector this$0;
        final /* synthetic */ RegisterSpec val$newReg;
        final /* synthetic */ RegisterSpec val$origReg;

        public AnonymousClass2(ConstCollector r1, RegisterSpec r2, RegisterSpec r3) {
            this.this$0 = r1;
            this.val$origReg = r2;
            this.val$newReg = r3;
        }

        @Override // com.android.dx.ssa.RegisterMapper
        public int getNewRegisterCount() {
            return ConstCollector.access$000(this.this$0).getRegCount();
        }

        @Override // com.android.dx.ssa.RegisterMapper
        public RegisterSpec map(RegisterSpec r3) {
            if (r3.getReg() == this.val$origReg.getReg()) goto L5;
            return r3;
        L5:
            return this.val$newReg.withLocalItem(r3.getLocalItem());
        }
    }

    private ConstCollector(SsaMethod r1) {
        this.ssaMeth = r1;
    }

    public static /* synthetic */ SsaMethod access$000(ConstCollector r0) {
        return r0.ssaMeth;
    }

    private void fixLocalAssignment(RegisterSpec r6, RegisterSpec r7) {
        Iterator<SsaInsn> r5 = this.ssaMeth.getUseListForRegister(r6.getReg()).iterator();
    L4:
        if (r5.hasNext() == false) goto L12;
        SsaInsn r62 = r5.next();
        RegisterSpec r0 = r62.getLocalAssignment();
        if (r0 == null) goto L4;
        if (r62.getResult() == null) goto L4;
        LocalItem r02 = r0.getLocalItem();
        r62.setResultLocal(null);
        r7 = r7.withLocalItem(r02);
        SsaInsn r03 = SsaInsn.makeFromRop(new PlainInsn(Rops.opMarkLocal(r7), SourcePosition.NO_INFO, null, RegisterSpecList.make(r7)), r62.getBlock());
        ArrayList<SsaInsn> r1 = r62.getBlock().getInsns();
        r1.add(r1.indexOf(r62) + 1, r03);
        goto L4
    }

    /* JADX WARN: Multi-variable type inference failed */
    private ArrayList<TypedConstant> getConstsSortedByCountUse() {
        int r0 = this.ssaMeth.getRegCount();
        HashMap r1 = new HashMap();
        new HashSet();
        int r3 = 0;
    L4:
        if (r3 >= r0) goto L27;
        SsaInsn r5 = this.ssaMeth.getDefinitionForRegister(r3);
        if (r5 == null) goto L26;
        if (r5.getOpcode() == null) goto L26;
        RegisterSpec r6 = r5.getResult();
        TypeBearer r7 = r6.getTypeBearer();
        if (r7.isConstant() == false) goto L26;
        TypedConstant r72 = (TypedConstant) r7;
        if (r5.getOpcode().getOpcode() != 56) goto L17;
        ArrayList<SsaInsn> r52 = this.ssaMeth.getBlocks().get(r5.getBlock().getPredecessors().nextSetBit(0)).getInsns();
        r5 = r52.get(r52.size() - 1);
    L17:
        if (r5.canThrow() == true) goto L26;
        if (this.ssaMeth.isRegALocal(r6) == true) goto L26;
        Integer r53 = (Integer) r1.get(r72);
        if (r53 != null) goto L25;
        r1.put(r72, 1);
        goto L26
    L25:
        r1.put(r72, Integer.valueOf(r53.intValue() + 1));
    L26:
        r3 = r3 + 1;
        goto L4
    L27:
        ArrayList<TypedConstant> r02 = new ArrayList();
        Iterator r2 = r1.entrySet().iterator();
    L29:
        if (r2.hasNext() == false) goto L33;
        Map.Entry r32 = (Map.Entry) r2.next();
        if (((Integer) r32.getValue()).intValue() <= 1) goto L29;
        r02.add(r32.getKey());
        goto L29
    L33:
        Collections.sort(r02, new AnonymousClass1(this, r1));
        return r02;
    }

    public static void process(SsaMethod r1) {
        new ConstCollector(r1).run();
    }

    private void run() {
        int r0 = this.ssaMeth.getRegCount();
        ArrayList<TypedConstant> r1 = getConstsSortedByCountUse();
        int r2 = Math.min(r1.size(), 5);
        SsaBasicBlock r3 = this.ssaMeth.getEntryBlock();
        HashMap<TypedConstant, RegisterSpec> r4 = new HashMap(r2);
        int r5 = 0;
    L3:
        if (r5 >= r2) goto L9;
        TypedConstant r12 = r1.get(r5);
        RegisterSpec r10 = RegisterSpec.make(this.ssaMeth.makeNewSsaReg(), r12);
        Rop r8 = Rops.opConst(r12);
        if (r8.getBranchingness() != 1) goto L7;
        RegisterSpec r6 = r10;
        r3.addInsnToHead(new PlainCstInsn(Rops.opConst(r12), SourcePosition.NO_INFO, r10, RegisterSpecList.EMPTY, r12));
    L8:
        r4.put(r12, r6);
        r5 = r5 + 1;
        goto L3
    L7:
        r6 = r10;
        SsaBasicBlock r7 = this.ssaMeth.getEntryBlock();
        SsaBasicBlock r13 = r7.getPrimarySuccessor();
        SsaBasicBlock r14 = r7.insertNewSuccessor(r13);
        SourcePosition r9 = SourcePosition.NO_INFO;
        RegisterSpecList r102 = RegisterSpecList.EMPTY;
        r14.replaceLastInsn(new ThrowingCstInsn(r8, r9, r102, StdTypeList.EMPTY, r12));
        r14.insertNewSuccessor(r13).addInsnToHead(new PlainInsn(Rops.opMoveResultPseudo(r6.getTypeBearer()), r9, r6, r102));
        goto L8
    L9:
        updateConstUses(r4, r0);
    }

    private void updateConstUses(HashMap<TypedConstant, RegisterSpec> r11, int r12) {
        new HashSet();
        ArrayList<SsaInsn>[] r0 = this.ssaMeth.getUseListCopy();
        int r2 = 0;
    L3:
        if (r2 >= r12) goto L25;
        SsaInsn r3 = this.ssaMeth.getDefinitionForRegister(r2);
        if (r3 == null) goto L24;
        RegisterSpec r4 = r3.getResult();
        TypeBearer r32 = r3.getResult().getTypeBearer();
        if (r32.isConstant() == false) goto L24;
        RegisterSpec r33 = r11.get((TypedConstant) r32);
        if (r33 == null) goto L24;
        if (this.ssaMeth.isRegALocal(r4) == true) goto L24;
        AnonymousClass2 r5 = new AnonymousClass2(this, r4, r33);
        ArrayList<SsaInsn> r34 = r0[r4.getReg()];
        int r42 = r34.size();
        int r6 = 0;
    L17:
        if (r6 >= r42) goto L24;
        SsaInsn r7 = r34.get(r6);
        r6 = r6 + 1;
        SsaInsn r72 = r7;
        if (r72.canThrow() == false) goto L23;
        if (r72.getBlock().getSuccessors().cardinality() > 1) goto L17;
    L23:
        r72.mapSourceRegisters(r5);
    L24:
        r2 = r2 + 1;
        goto L3
    }
}
