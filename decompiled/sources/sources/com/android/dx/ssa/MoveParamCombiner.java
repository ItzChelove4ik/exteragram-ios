package com.android.dx.ssa;

import com.android.dx.rop.code.CstInsn;
import com.android.dx.rop.code.LocalItem;
import com.android.dx.rop.code.RegisterSpec;
import com.android.dx.rop.cst.CstInteger;
import com.android.dx.ssa.SsaInsn;
import java.util.HashSet;
import java.util.List;

/* loaded from: classes4.dex */
public class MoveParamCombiner {
    private final SsaMethod ssaMeth;

    /* renamed from: com.android.dx.ssa.MoveParamCombiner$1, reason: invalid class name */
    public class AnonymousClass1 implements SsaInsn.Visitor {
        final /* synthetic */ MoveParamCombiner this$0;
        final /* synthetic */ HashSet val$deletedInsns;
        final /* synthetic */ RegisterSpec[] val$paramSpecs;

        /* renamed from: com.android.dx.ssa.MoveParamCombiner$1$1, reason: invalid class name and collision with other inner class name */
        public class C00121 extends RegisterMapper {
            final /* synthetic */ AnonymousClass1 this$1;
            final /* synthetic */ RegisterSpec val$specA;
            final /* synthetic */ RegisterSpec val$specB;

            public C00121(AnonymousClass1 r1, RegisterSpec r2, RegisterSpec r3) {
                this.this$1 = r1;
                this.val$specB = r2;
                this.val$specA = r3;
            }

            @Override // com.android.dx.ssa.RegisterMapper
            public int getNewRegisterCount() {
                return MoveParamCombiner.access$100(this.this$1.this$0).getRegCount();
            }

            @Override // com.android.dx.ssa.RegisterMapper
            public RegisterSpec map(RegisterSpec r3) {
                if (r3.getReg() == this.val$specB.getReg()) goto L5;
                return r3;
            L5:
                return this.val$specA;
            }
        }

        public AnonymousClass1(MoveParamCombiner r1, RegisterSpec[] r2, HashSet r3) {
            this.this$0 = r1;
            this.val$paramSpecs = r2;
            this.val$deletedInsns = r3;
        }

        @Override // com.android.dx.ssa.SsaInsn.Visitor
        public void visitMoveInsn(NormalSsaInsn r1) {
        }

        @Override // com.android.dx.ssa.SsaInsn.Visitor
        public void visitNonMoveInsn(NormalSsaInsn r6) {
            if (r6.getOpcode().getOpcode() != 3) goto L23;
            int r0 = MoveParamCombiner.access$000(this.this$0, r6);
            RegisterSpec[] r1 = this.val$paramSpecs;
            RegisterSpec r2 = r1[r0];
            if (r2 != null) goto L9;
            r1[r0] = r6.getResult();
            return;
        L9:
            RegisterSpec r02 = r6.getResult();
            LocalItem r12 = r2.getLocalItem();
            LocalItem r3 = r02.getLocalItem();
            if (r12 != null) goto L12;
            r12 = r3;
        L16:
            MoveParamCombiner.access$100(this.this$0).getDefinitionForRegister(r2.getReg()).setResultLocal(r12);
            C00121 r13 = new C00121(this, r02, r2);
            List<SsaInsn> r03 = MoveParamCombiner.access$100(this.this$0).getUseListForRegister(r02.getReg());
            int r22 = r03.size() - 1;
        L17:
            if (r22 < 0) goto L19;
            r03.get(r22).mapSourceRegisters(r13);
            r22 = r22 - 1;
            goto L17
        L19:
            this.val$deletedInsns.add(r6);
            return;
        L12:
            if (r3 == null) goto L16;
            if (r12.equals(r3) == true) goto L16;
            return;
        }

        @Override // com.android.dx.ssa.SsaInsn.Visitor
        public void visitPhiInsn(PhiInsn r1) {
        }
    }

    private MoveParamCombiner(SsaMethod r1) {
        this.ssaMeth = r1;
    }

    public static /* synthetic */ int access$000(MoveParamCombiner r0, NormalSsaInsn r1) {
        return r0.getParamIndex(r1);
    }

    public static /* synthetic */ SsaMethod access$100(MoveParamCombiner r0) {
        return r0.ssaMeth;
    }

    private int getParamIndex(NormalSsaInsn r1) {
        return ((CstInteger) ((CstInsn) r1.getOriginalRopInsn()).getConstant()).getValue();
    }

    public static void process(SsaMethod r1) {
        new MoveParamCombiner(r1).run();
    }

    private void run() {
        RegisterSpec[] r0 = new RegisterSpec[this.ssaMeth.getParamWidth()];
        HashSet r1 = new HashSet();
        this.ssaMeth.forEachInsn(new AnonymousClass1(this, r0, r1));
        this.ssaMeth.deleteInsns(r1);
    }
}
