package com.android.dx.dex.code;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.code.RegisterSpec;
import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.rop.code.RegisterSpecSet;
import com.android.dx.rop.code.SourcePosition;
import com.android.dx.ssa.RegisterMapper;

/* loaded from: classes.dex */
public final class LocalSnapshot extends ZeroSizeInsn {
    private final RegisterSpecSet locals;

    public LocalSnapshot(SourcePosition r1, RegisterSpecSet r2) {
        super(r1);
        if (r2 == null) goto L6;
        this.locals = r2;
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("locals == null");
        throw null;
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public String argString() {
        return this.locals.toString();
    }

    public RegisterSpecSet getLocals() {
        return this.locals;
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public String listingString0(boolean r5) {
        int r52 = this.locals.size();
        int r0 = this.locals.getMaxSize();
        StringBuilder r1 = new StringBuilder((r52 * 40) + 100);
        r1.append("local-snapshot");
        int r53 = 0;
    L3:
        if (r53 >= r0) goto L9;
        RegisterSpec r2 = this.locals.get(r53);
        if (r2 == null) goto L7;
        r1.append("\n  ");
        r1.append(LocalStart.localString(r2));
    L7:
        r53 = r53 + 1;
        goto L3
    L9:
        return r1.toString();
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public DalvInsn withMapper(RegisterMapper r3) {
        return new LocalSnapshot(getPosition(), r3.map(this.locals));
    }

    @Override // com.android.dx.dex.code.ZeroSizeInsn, com.android.dx.dex.code.DalvInsn
    public DalvInsn withRegisterOffset(int r3) {
        return new LocalSnapshot(getPosition(), this.locals.withOffset(r3));
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public DalvInsn withRegisters(RegisterSpecList r2) {
        return new LocalSnapshot(getPosition(), this.locals);
    }
}
