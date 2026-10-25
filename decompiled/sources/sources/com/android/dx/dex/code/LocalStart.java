package com.android.dx.dex.code;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.code.RegisterSpec;
import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.rop.code.SourcePosition;
import com.android.dx.ssa.RegisterMapper;

/* loaded from: classes.dex */
public final class LocalStart extends ZeroSizeInsn {
    private final RegisterSpec local;

    public LocalStart(SourcePosition r1, RegisterSpec r2) {
        super(r1);
        if (r2 == null) goto L6;
        this.local = r2;
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("local == null");
        throw null;
    }

    public static String localString(RegisterSpec r2) {
        return r2.regString() + ' ' + r2.getLocalItem().toString() + ": " + r2.getTypeBearer().toHuman();
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public String argString() {
        return this.local.toString();
    }

    public RegisterSpec getLocal() {
        return this.local;
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public String listingString0(boolean r2) {
        return "local-start " + localString(this.local);
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public DalvInsn withMapper(RegisterMapper r3) {
        return new LocalStart(getPosition(), r3.map(this.local));
    }

    @Override // com.android.dx.dex.code.ZeroSizeInsn, com.android.dx.dex.code.DalvInsn
    public DalvInsn withRegisterOffset(int r3) {
        return new LocalStart(getPosition(), this.local.withOffset(r3));
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public DalvInsn withRegisters(RegisterSpecList r2) {
        return new LocalStart(getPosition(), this.local);
    }
}
