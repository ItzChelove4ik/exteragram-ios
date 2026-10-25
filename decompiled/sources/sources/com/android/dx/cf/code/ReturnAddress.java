package com.android.dx.cf.code;

import c.f$$ExternalSyntheticBUOutline1;
import com.android.dx.rop.type.Type;
import com.android.dx.rop.type.TypeBearer;
import com.android.dx.util.Hex;

/* loaded from: classes4.dex */
public final class ReturnAddress implements TypeBearer {
    private final int subroutineAddress;

    public ReturnAddress(int r1) {
        if (r1 < 0) goto L6;
        this.subroutineAddress = r1;
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("subroutineAddress < 0");
        throw null;
    }

    public boolean equals(Object r3) {
        if ((r3 instanceof ReturnAddress) == true) goto L6;
        return false;
    L6:
        if (this.subroutineAddress != ((ReturnAddress) r3).subroutineAddress) goto L9;
        return true;
    L9:
        return false;
    }

    @Override // com.android.dx.rop.type.TypeBearer
    public int getBasicFrameType() {
        return Type.RETURN_ADDRESS.getBasicFrameType();
    }

    @Override // com.android.dx.rop.type.TypeBearer
    public int getBasicType() {
        return Type.RETURN_ADDRESS.getBasicType();
    }

    @Override // com.android.dx.rop.type.TypeBearer
    public TypeBearer getFrameType() {
        return this;
    }

    public int getSubroutineAddress() {
        return this.subroutineAddress;
    }

    @Override // com.android.dx.rop.type.TypeBearer
    public Type getType() {
        return Type.RETURN_ADDRESS;
    }

    public int hashCode() {
        return this.subroutineAddress;
    }

    @Override // com.android.dx.rop.type.TypeBearer
    public boolean isConstant() {
        return false;
    }

    @Override // com.android.dx.util.ToHuman
    public String toHuman() {
        return toString();
    }

    public String toString() {
        return "<addr:" + Hex.u2(this.subroutineAddress) + ">";
    }
}
