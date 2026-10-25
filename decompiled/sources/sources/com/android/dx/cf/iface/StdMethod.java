package com.android.dx.cf.iface;

import com.android.dx.rop.code.AccessFlags;
import com.android.dx.rop.cst.CstNat;
import com.android.dx.rop.cst.CstType;
import com.android.dx.rop.type.Prototype;

/* loaded from: classes4.dex */
public final class StdMethod extends StdMember implements Method {
    private final Prototype effectiveDescriptor;

    public StdMethod(CstType r1, int r2, CstNat r3, AttributeList r4) {
        super(r1, r2, r3, r4);
        this.effectiveDescriptor = Prototype.intern(getDescriptor().getString(), r1.getClassType(), AccessFlags.isStatic(r2), r3.isInstanceInit());
    }

    @Override // com.android.dx.cf.iface.Method
    public Prototype getEffectiveDescriptor() {
        return this.effectiveDescriptor;
    }
}
