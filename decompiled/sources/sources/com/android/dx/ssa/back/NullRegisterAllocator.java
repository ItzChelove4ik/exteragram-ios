package com.android.dx.ssa.back;

import com.android.dx.ssa.BasicRegisterMapper;
import com.android.dx.ssa.RegisterMapper;
import com.android.dx.ssa.SsaMethod;

/* loaded from: classes4.dex */
public class NullRegisterAllocator extends RegisterAllocator {
    public NullRegisterAllocator(SsaMethod r1, InterferenceGraph r2) {
        super(r1, r2);
    }

    @Override // com.android.dx.ssa.back.RegisterAllocator
    public RegisterMapper allocateRegisters() {
        int r4 = this.ssaMeth.getRegCount();
        BasicRegisterMapper r0 = new BasicRegisterMapper(r4);
        int r1 = 0;
    L3:
        if (r1 >= r4) goto L5;
        r0.addMapping(r1, r1 * 2, 2);
        r1 = r1 + 1;
        goto L3
    L5:
        return r0;
    }

    @Override // com.android.dx.ssa.back.RegisterAllocator
    public boolean wantsParamsMovedHigh() {
        return false;
    }
}
