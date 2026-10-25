package com.android.dx.ssa;

import com.android.dx.rop.code.LocalItem;
import com.android.dx.rop.code.PlainInsn;
import com.android.dx.rop.code.RegisterSpec;
import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.rop.code.Rops;
import com.android.dx.rop.code.SourcePosition;
import com.android.dx.rop.type.Type;
import com.android.dx.ssa.PhiInsn;
import com.android.dx.ssa.SsaBasicBlock;
import com.android.dx.ssa.SsaInsn;
import com.android.dx.util.IntList;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;

/* loaded from: classes4.dex */
public class SsaRenamer implements Runnable {
    private static final boolean DEBUG = false;
    private int nextSsaReg;
    private final int ropRegCount;
    private final SsaMethod ssaMeth;
    private final ArrayList<LocalItem> ssaRegToLocalItems;
    private IntList ssaRegToRopReg;
    private final RegisterSpec[][] startsForBlocks;
    private int threshold;

    /* renamed from: com.android.dx.ssa.SsaRenamer$1, reason: invalid class name */
    public class AnonymousClass1 implements SsaBasicBlock.Visitor {
        final /* synthetic */ SsaRenamer this$0;

        public AnonymousClass1(SsaRenamer r1) {
            this.this$0 = r1;
        }

        @Override // com.android.dx.ssa.SsaBasicBlock.Visitor
        public void visitBlock(SsaBasicBlock r1, SsaBasicBlock r2) {
            new BlockRenamer(this.this$0, r1).process();
        }
    }

    public class BlockRenamer implements SsaInsn.Visitor {
        private final SsaBasicBlock block;
        private final RegisterSpec[] currentMapping;
        private final HashMap<SsaInsn, SsaInsn> insnsToReplace;
        private final RenamingMapper mapper;
        private final HashSet<SsaInsn> movesToKeep;
        final /* synthetic */ SsaRenamer this$0;

        /* renamed from: com.android.dx.ssa.SsaRenamer$BlockRenamer$1, reason: invalid class name */
        public class AnonymousClass1 implements PhiInsn.Visitor {
            final /* synthetic */ BlockRenamer this$1;

            public AnonymousClass1(BlockRenamer r1) {
                this.this$1 = r1;
            }

            @Override // com.android.dx.ssa.PhiInsn.Visitor
            public void visitPhiInsn(PhiInsn r4) {
                int r0 = r4.getRopResultReg();
                if (SsaRenamer.access$800(this.this$1.this$0, r0) == true) goto L10;
                RegisterSpec r02 = BlockRenamer.access$200(this.this$1)[r0];
                if (SsaRenamer.access$900(this.this$1.this$0, r02.getReg()) == true) goto L9;
                r4.addPhiOperand(r02, BlockRenamer.access$1000(this.this$1));
                return;
            L9:
                return;
            }
        }

        public class RenamingMapper extends RegisterMapper {
            final /* synthetic */ BlockRenamer this$1;

            public RenamingMapper(BlockRenamer r1) {
                this.this$1 = r1;
            }

            @Override // com.android.dx.ssa.RegisterMapper
            public int getNewRegisterCount() {
                return SsaRenamer.access$100(this.this$1.this$0);
            }

            @Override // com.android.dx.ssa.RegisterMapper
            public RegisterSpec map(RegisterSpec r2) {
                if (r2 != null) goto L5;
                return null;
            L5:
                int r0 = r2.getReg();
                return r2.withReg(BlockRenamer.access$200(this.this$1)[r0].getReg());
            }
        }

        public BlockRenamer(SsaRenamer r3, SsaBasicBlock r4) {
            this.this$0 = r3;
            this.block = r4;
            this.currentMapping = SsaRenamer.access$000(r3)[r4.getIndex()];
            this.movesToKeep = new HashSet();
            this.insnsToReplace = new HashMap();
            this.mapper = new RenamingMapper(this);
            SsaRenamer.access$000(r3)[r4.getIndex()] = null;
        }

        public static /* synthetic */ SsaBasicBlock access$1000(BlockRenamer r0) {
            return r0.block;
        }

        public static /* synthetic */ RegisterSpec[] access$200(BlockRenamer r0) {
            return r0.currentMapping;
        }

        private void addMapping(int r5, RegisterSpec r6) {
            int r0 = r6.getReg();
            LocalItem r1 = r6.getLocalItem();
            RegisterSpec[] r2 = this.currentMapping;
            r2[r5] = r6;
            int r52 = r2.length - 1;
        L3:
            if (r52 < 0) goto L8;
            if (r0 != this.currentMapping[r52].getReg()) goto L7;
            this.currentMapping[r52] = r6;
        L7:
            r52 = r52 - 1;
            goto L3
        L8:
            if (r1 == null) goto L18;
            SsaRenamer.access$400(this.this$0, r6);
            int r53 = this.currentMapping.length - 1;
        L11:
            if (r53 < 0) goto L26;
            RegisterSpec r62 = this.currentMapping[r53];
            if (r0 == r62.getReg()) goto L17;
            if (r1.equals(r62.getLocalItem()) == false) goto L17;
            this.currentMapping[r53] = r62.withLocalItem(null);
        L17:
            r53 = r53 - 1;
            goto L11
        L26:
            return;
        }

        private void updateSuccessorPhis() {
            AnonymousClass1 r0 = new AnonymousClass1(this);
            BitSet r1 = this.block.getSuccessors();
            int r2 = r1.nextSetBit(0);
        L3:
            if (r2 < 0) goto L5;
            SsaRenamer.access$1100(this.this$0).getBlocks().get(r2).forEachPhiInsn(r0);
            r2 = r1.nextSetBit(r2 + 1);
            goto L3
        }

        public void process() {
            this.block.forEachInsn(this);
            updateSuccessorPhis();
            ArrayList<SsaInsn> r0 = this.block.getInsns();
            boolean r2 = true;
            int r1 = r0.size() - 1;
        L3:
            if (r1 < 0) goto L13;
            SsaInsn r3 = r0.get(r1);
            SsaInsn r4 = this.insnsToReplace.get(r3);
            if (r4 == null) goto L8;
            r0.set(r1, r4);
        L12:
            r1 = r1 - 1;
            goto L3
        L8:
            if (r3.isNormalMoveInsn() == false) goto L12;
            if (this.movesToKeep.contains(r3) == true) goto L12;
            r0.remove(r1);
            goto L12
        L13:
            ArrayList<SsaBasicBlock> r02 = this.block.getDomChildren();
            int r12 = r02.size();
            int r42 = 0;
        L14:
            if (r42 >= r12) goto L22;
            SsaBasicBlock r5 = r02.get(r42);
            r42 = r42 + 1;
            SsaBasicBlock r52 = r5;
            if (r52 == this.block) goto L14;
            RegisterSpec[] r6 = this.currentMapping;
            if (r2 == true) goto L21;
            r6 = SsaRenamer.access$300(r6);
        L21:
            SsaRenamer.access$000(this.this$0)[r52.getIndex()] = r6;
            r2 = false;
            goto L14
        }

        public void processResultReg(SsaInsn r3) {
            RegisterSpec r0 = r3.getResult();
            if (r0 == null) goto L10;
            int r02 = r0.getReg();
            if (SsaRenamer.access$800(this.this$0, r02) == false) goto L8;
            return;
        L8:
            r3.changeResultReg(SsaRenamer.access$100(this.this$0));
            addMapping(r02, r3.getResult());
            SsaRenamer.access$108(this.this$0);
            return;
        }

        @Override // com.android.dx.ssa.SsaInsn.Visitor
        public void visitMoveInsn(NormalSsaInsn r8) {
            RegisterSpec r0 = r8.getResult();
            int r1 = r0.getReg();
            boolean r3 = false;
            int r2 = r8.getSources().get(0).getReg();
            r8.mapSourceRegisters(this.mapper);
            int r4 = r8.getSources().get(0).getReg();
            LocalItem r22 = this.currentMapping[r2].getLocalItem();
            LocalItem r5 = r0.getLocalItem();
            if (r5 != null) goto L5;
            r5 = r22;
        L5:
            LocalItem r6 = SsaRenamer.access$500(this.this$0, r4);
            if (r6 == null) goto L10;
            if (r5 == null) goto L10;
            if (r5.equals(r6) == true) goto L10;
        L11:
            RegisterSpec r02 = RegisterSpec.makeLocalOptional(r4, r0.getType(), r5);
            if (Optimizer.getPreserveLocals() == false) goto L27;
            if (r3 == true) goto L15;
        L19:
            if (r3 == false) goto L25;
            if (r22 != null) goto L25;
            if (SsaRenamer.access$700(this.this$0) != 0) goto L25;
            RegisterSpecList r23 = RegisterSpecList.make(RegisterSpec.make(r02.getReg(), r02.getType(), r5));
            SsaInsn r24 = SsaInsn.makeFromRop(new PlainInsn(Rops.opMarkLocal(r02), SourcePosition.NO_INFO, null, r23), this.block);
            this.insnsToReplace.put(r8, r24);
            addMapping(r1, r02);
            return;
        L25:
            processResultReg(r8);
            this.movesToKeep.add(r8);
            return;
        L15:
            if (SsaRenamer.access$600(r5, r22) == false) goto L19;
            if (SsaRenamer.access$700(this.this$0) != 0) goto L19;
        L27:
            addMapping(r1, r02);
            return;
        L10:
            r3 = true;
            goto L11
        }

        @Override // com.android.dx.ssa.SsaInsn.Visitor
        public void visitNonMoveInsn(NormalSsaInsn r2) {
            r2.mapSourceRegisters(this.mapper);
            processResultReg(r2);
        }

        @Override // com.android.dx.ssa.SsaInsn.Visitor
        public void visitPhiInsn(PhiInsn r1) {
            processResultReg(r1);
        }
    }

    public SsaRenamer(SsaMethod r4) {
        int r0 = r4.getRegCount();
        this.ropRegCount = r0;
        this.ssaMeth = r4;
        this.nextSsaReg = r0;
        int r1 = 0;
        this.threshold = 0;
        this.startsForBlocks = new RegisterSpec[r4.getBlocks().size()][];
        this.ssaRegToLocalItems = new ArrayList();
        RegisterSpec[] r02 = new RegisterSpec[r0];
    L4:
        if (r1 >= this.ropRegCount) goto L6;
        r02[r1] = RegisterSpec.make(r1, Type.VOID);
        r1 = r1 + 1;
        goto L4
    L6:
        this.startsForBlocks[r4.getEntryBlockIndex()] = r02;
    }

    public SsaRenamer(SsaMethod r1, int r2) {
        this(r1);
        this.threshold = r2;
    }

    public static /* synthetic */ RegisterSpec[][] access$000(SsaRenamer r0) {
        return r0.startsForBlocks;
    }

    public static /* synthetic */ int access$100(SsaRenamer r0) {
        return r0.nextSsaReg;
    }

    public static /* synthetic */ int access$108(SsaRenamer r2) {
        int r0 = r2.nextSsaReg;
        r2.nextSsaReg = r0 + 1;
        return r0;
    }

    public static /* synthetic */ SsaMethod access$1100(SsaRenamer r0) {
        return r0.ssaMeth;
    }

    public static /* synthetic */ RegisterSpec[] access$300(RegisterSpec[] r0) {
        return dupArray(r0);
    }

    public static /* synthetic */ void access$400(SsaRenamer r0, RegisterSpec r1) {
        r0.setNameForSsaReg(r1);
    }

    public static /* synthetic */ LocalItem access$500(SsaRenamer r0, int r1) {
        return r0.getLocalForNewReg(r1);
    }

    public static /* synthetic */ boolean access$600(Object r0, Object r1) {
        return equalsHandlesNulls(r0, r1);
    }

    public static /* synthetic */ int access$700(SsaRenamer r0) {
        return r0.threshold;
    }

    public static /* synthetic */ boolean access$800(SsaRenamer r0, int r1) {
        return r0.isBelowThresholdRegister(r1);
    }

    public static /* synthetic */ boolean access$900(SsaRenamer r0, int r1) {
        return r0.isVersionZeroRegister(r1);
    }

    private static RegisterSpec[] dupArray(RegisterSpec[] r3) {
        RegisterSpec[] r0 = new RegisterSpec[r3.length];
        System.arraycopy(r3, 0, r0, 0, r3.length);
        return r0;
    }

    private static boolean equalsHandlesNulls(Object r0, Object r1) {
        if (r0 == r1) goto L9;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.equals(r1) == true) goto L12;
        return false;
    L12:
        return true;
    L9:
        return true;
    }

    private LocalItem getLocalForNewReg(int r2) {
        if (r2 < this.ssaRegToLocalItems.size()) goto L5;
        return null;
    L5:
        return this.ssaRegToLocalItems.get(r2);
    }

    private boolean isBelowThresholdRegister(int r1) {
        if (r1 >= this.threshold) goto L6;
        return true;
    L6:
        return false;
    }

    private boolean isVersionZeroRegister(int r1) {
        if (r1 >= this.ropRegCount) goto L6;
        return true;
    L6:
        return false;
    }

    private void setNameForSsaReg(RegisterSpec r4) {
        int r0 = r4.getReg();
        LocalItem r42 = r4.getLocalItem();
        this.ssaRegToLocalItems.ensureCapacity(r0 + 1);
    L3:
        int r1 = this.ssaRegToLocalItems.size();
        ArrayList<LocalItem> r2 = this.ssaRegToLocalItems;
        if (r1 > r0) goto L6;
        r2.add(null);
        goto L3
    L6:
        r2.set(r0, r42);
    }

    @Override // java.lang.Runnable
    public void run() {
        this.ssaMeth.forEachBlockDepthFirstDom(new AnonymousClass1(this));
        this.ssaMeth.setNewRegCount(this.nextSsaReg);
        this.ssaMeth.onInsnsChanged();
    }
}
