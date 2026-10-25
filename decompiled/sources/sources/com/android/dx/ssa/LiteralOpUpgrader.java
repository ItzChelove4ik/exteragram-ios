package com.android.dx.ssa;

import com.android.dx.rop.code.Insn;
import com.android.dx.rop.code.PlainCstInsn;
import com.android.dx.rop.code.PlainInsn;
import com.android.dx.rop.code.RegOps;
import com.android.dx.rop.code.RegisterSpec;
import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.rop.code.Rop;
import com.android.dx.rop.code.Rops;
import com.android.dx.rop.code.TranslationAdvice;
import com.android.dx.rop.cst.Constant;
import com.android.dx.rop.cst.CstLiteralBits;
import com.android.dx.rop.type.TypeBearer;
import com.android.dx.ssa.SsaInsn;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class LiteralOpUpgrader {
    private final SsaMethod ssaMeth;

    /* renamed from: com.android.dx.ssa.LiteralOpUpgrader$1, reason: invalid class name */
    public class AnonymousClass1 implements SsaInsn.Visitor {
        final /* synthetic */ LiteralOpUpgrader this$0;
        final /* synthetic */ TranslationAdvice val$advice;

        public AnonymousClass1(LiteralOpUpgrader r1, TranslationAdvice r2) {
            this.this$0 = r1;
            this.val$advice = r2;
        }

        @Override // com.android.dx.ssa.SsaInsn.Visitor
        public void visitMoveInsn(NormalSsaInsn r1) {
        }

        @Override // com.android.dx.ssa.SsaInsn.Visitor
        public void visitNonMoveInsn(NormalSsaInsn r8) {
            Rop r0 = r8.getOriginalRopInsn().getOpcode();
            RegisterSpecList r1 = r8.getSources();
            if (LiteralOpUpgrader.access$000(this.this$0, r8) == false) goto L6;
            return;
        L6:
            if (r1.size() == 2) goto L9;
            return;
        L9:
            if (r0.getBranchingness() != 4) goto L19;
            if (LiteralOpUpgrader.access$100(r1.get(0)) == false) goto L15;
            LiteralOpUpgrader.access$200(this.this$0, r8, r1.withoutFirst(), RegOps.flippedIfOpcode(r0.getOpcode()), null);
            return;
        L15:
            if (LiteralOpUpgrader.access$100(r1.get(1)) == false) goto L28;
            LiteralOpUpgrader.access$200(this.this$0, r8, r1.withoutLast(), r0.getOpcode(), null);
            return;
        L28:
            return;
        L19:
            if (this.val$advice.hasConstantOperation(r0, r1.get(0), r1.get(1)) == false) goto L23;
            r8.upgradeToLiteral();
            return;
        L23:
            if (r0.isCommutative() == true) goto L25;
            return;
        L25:
            if (this.val$advice.hasConstantOperation(r0, r1.get(1), r1.get(0)) == false) goto L30;
            r8.setNewSources(RegisterSpecList.make(r1.get(1), r1.get(0)));
            r8.upgradeToLiteral();
            return;
        }

        @Override // com.android.dx.ssa.SsaInsn.Visitor
        public void visitPhiInsn(PhiInsn r1) {
        }
    }

    private LiteralOpUpgrader(SsaMethod r1) {
        this.ssaMeth = r1;
    }

    public static /* synthetic */ boolean access$000(LiteralOpUpgrader r0, NormalSsaInsn r1) {
        return r0.tryReplacingWithConstant(r1);
    }

    public static /* synthetic */ boolean access$100(RegisterSpec r0) {
        return isConstIntZeroOrKnownNull(r0);
    }

    public static /* synthetic */ void access$200(LiteralOpUpgrader r0, NormalSsaInsn r1, RegisterSpecList r2, int r3, Constant r4) {
        r0.replacePlainInsn(r1, r2, r3, r4);
    }

    private static boolean isConstIntZeroOrKnownNull(RegisterSpec r6) {
        TypeBearer r62 = r6.getTypeBearer();
        if ((r62 instanceof CstLiteralBits) == true) goto L5;
    L8:
        return false;
    L5:
        if (((CstLiteralBits) r62).getLongBits() != 0) goto L8;
        return true;
    }

    public static void process(SsaMethod r1) {
        new LiteralOpUpgrader(r1).run();
    }

    private void replacePlainInsn(NormalSsaInsn r9, RegisterSpecList r10, int r11, Constant r12) {
        Insn r0 = r9.getOriginalRopInsn();
        Rop r3 = Rops.ropFor(r11, r9.getResult(), r10, r12);
        if (r12 != null) goto L5;
        Insn r112 = new PlainInsn(r3, r0.getPosition(), r9.getResult(), r10);
    L6:
        NormalSsaInsn r102 = new NormalSsaInsn(r112, r9.getBlock());
        ArrayList<SsaInsn> r113 = r9.getBlock().getInsns();
        this.ssaMeth.onInsnRemoved(r9);
        r113.set(r113.lastIndexOf(r9), r102);
        this.ssaMeth.onInsnAdded(r102);
        return;
    L5:
        r112 = new PlainCstInsn(r3, r0.getPosition(), r9.getResult(), r10, r12);
        goto L6
    }

    private void run() {
        TranslationAdvice r0 = Optimizer.getAdvice();
        this.ssaMeth.forEachInsn(new AnonymousClass1(this, r0));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean tryReplacingWithConstant(NormalSsaInsn r7) {
        Rop r0 = r7.getOriginalRopInsn().getOpcode();
        RegisterSpec r1 = r7.getResult();
        if (r1 != null) goto L5;
    L16:
        return false;
    L5:
        if (this.ssaMeth.isRegALocal(r1) == true) goto L16;
        if (r0.getOpcode() == 5) goto L16;
        TypeBearer r12 = r7.getResult().getTypeBearer();
        if (r12.isConstant() == false) goto L16;
        if (r12.getBasicType() != 6) goto L16;
        RegisterSpecList r4 = RegisterSpecList.EMPTY;
        replacePlainInsn(r7, r4, 5, (Constant) r12);
        if (r0.getOpcode() != 56) goto L15;
        int r72 = r7.getBlock().getPredecessors().nextSetBit(0);
        ArrayList<SsaInsn> r73 = this.ssaMeth.getBlocks().get(r72).getInsns();
        replacePlainInsn((NormalSsaInsn) r73.get(r73.size() - 1), r4, 6, null);
    L15:
        return true;
    }
}
