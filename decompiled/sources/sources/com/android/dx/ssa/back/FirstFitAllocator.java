package com.android.dx.ssa.back;

import com.android.dx.rop.code.CstInsn;
import com.android.dx.rop.cst.CstInteger;
import com.android.dx.ssa.BasicRegisterMapper;
import com.android.dx.ssa.NormalSsaInsn;
import com.android.dx.ssa.RegisterMapper;
import com.android.dx.ssa.SsaMethod;
import com.android.dx.util.BitIntSet;
import java.util.BitSet;

/* loaded from: classes4.dex */
public class FirstFitAllocator extends RegisterAllocator {
    private static final boolean PRESLOT_PARAMS = true;
    private final BitSet mapped;

    public FirstFitAllocator(SsaMethod r1, InterferenceGraph r2) {
        super(r1, r2);
        this.mapped = new BitSet(r1.getRegCount());
    }

    private int paramNumberFromMoveParam(NormalSsaInsn r1) {
        return ((CstInteger) ((CstInsn) r1.getOriginalRopInsn()).getConstant()).getValue();
    }

    @Override // com.android.dx.ssa.back.RegisterAllocator
    public RegisterMapper allocateRegisters() {
        int r0 = this.ssaMeth.getRegCount();
        BasicRegisterMapper r1 = new BasicRegisterMapper(r0);
        int r2 = this.ssaMeth.getParamWidth();
        int r4 = 0;
    L3:
        if (r4 >= r0) goto L30;
        if (this.mapped.get(r4) == true) goto L29;
        int r5 = getCategoryForSsaReg(r4);
        BitIntSet r6 = new BitIntSet(r0);
        this.interference.mergeInterferenceSet(r4, r6);
        if (isDefinitionMoveParam(r4) == false) goto L10;
        int r7 = paramNumberFromMoveParam((NormalSsaInsn) this.ssaMeth.getDefinitionForRegister(r4));
        r1.addMapping(r4, r7, r5);
        boolean r8 = true;
    L11:
        int r9 = r4 + 1;
    L12:
        BitSet r10 = this.mapped;
        if (r9 >= r0) goto L26;
        if (r10.get(r9) == true) goto L25;
        if (isDefinitionMoveParam(r9) == true) goto L25;
        if (r6.has(r9) == true) goto L25;
        if (r8 == true) goto L23;
    L24:
        this.interference.mergeInterferenceSet(r9, r6);
        r5 = Math.max(r5, getCategoryForSsaReg(r9));
        r1.addMapping(r9, r7, r5);
        this.mapped.set(r9);
        goto L25
    L23:
        if (r5 >= getCategoryForSsaReg(r9)) goto L24;
    L25:
        r9 = r9 + 1;
        goto L12
    L26:
        r10.set(r4);
        if (r8 == true) goto L29;
        r2 = r2 + r5;
        goto L29
    L10:
        r1.addMapping(r4, r2, r5);
        r7 = r2;
        r8 = false;
    L29:
        r4 = r4 + 1;
        goto L3
    L30:
        return r1;
    }

    @Override // com.android.dx.ssa.back.RegisterAllocator
    public boolean wantsParamsMovedHigh() {
        return true;
    }
}
