package com.android.dx.dex.code;

import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.rop.code.SourcePosition;

/* loaded from: classes.dex */
public final class CodeAddress extends ZeroSizeInsn {
    private final boolean bindsClosely;

    public CodeAddress(SourcePosition r2) {
        this(r2, false);
    }

    public CodeAddress(SourcePosition r1, boolean r2) {
        super(r1);
        this.bindsClosely = r2;
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public String argString() {
        return null;
    }

    public boolean getBindsClosely() {
        return this.bindsClosely;
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public String listingString0(boolean r1) {
        return "code-address";
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public final DalvInsn withRegisters(RegisterSpecList r1) {
        return new CodeAddress(getPosition());
    }
}
