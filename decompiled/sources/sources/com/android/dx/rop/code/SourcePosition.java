package com.android.dx.rop.code;

import c.f$$ExternalSyntheticBUOutline1;
import com.android.dx.rop.cst.CstString;
import com.android.dx.util.Hex;

/* loaded from: classes.dex */
public final class SourcePosition {
    public static final SourcePosition NO_INFO = null;
    private final int address;
    private final int line;
    private final CstString sourceFile;

    static {
        NO_INFO = new SourcePosition(null, -1, -1);
    }

    public SourcePosition(CstString r2, int r3, int r4) {
        if (r3 < (-1)) goto L9;
        if (r4 < (-1)) goto L7;
        this.sourceFile = r2;
        this.address = r3;
        this.line = r4;
        return;
    L7:
        f$$ExternalSyntheticBUOutline1.m("line < -1");
        throw null;
    L9:
        f$$ExternalSyntheticBUOutline1.m("address < -1");
        throw null;
    }

    public boolean equals(Object r5) {
        if ((r5 instanceof SourcePosition) == true) goto L6;
        return false;
    L6:
        if (this != r5) goto L8;
        return true;
    L8:
        SourcePosition r52 = (SourcePosition) r5;
        if (this.address == r52.address) goto L11;
    L13:
        return false;
    L11:
        if (sameLineAndFile(r52) == false) goto L13;
        return true;
    }

    public int getAddress() {
        return this.address;
    }

    public int getLine() {
        return this.line;
    }

    public CstString getSourceFile() {
        return this.sourceFile;
    }

    public int hashCode() {
        return (this.sourceFile.hashCode() + this.address) + this.line;
    }

    public boolean sameLine(SourcePosition r1) {
        if (this.line != r1.line) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean sameLineAndFile(SourcePosition r3) {
        if (this.line != r3.line) goto L11;
        CstString r2 = this.sourceFile;
        CstString r32 = r3.sourceFile;
        if (r2 == r32) goto L9;
        if (r2 != null) goto L8;
        return false;
    L8:
        if (r2.equals(r32) == false) goto L15;
        return true;
    L15:
        return false;
    L9:
        return true;
    L11:
        return false;
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder(50);
        CstString r1 = this.sourceFile;
        if (r1 == null) goto L5;
        r0.append(r1.toHuman());
        r0.append(":");
    L5:
        int r12 = this.line;
        if (r12 < 0) goto L8;
        r0.append(r12);
    L8:
        r0.append('@');
        int r2 = this.address;
        if (r2 >= 0) goto L11;
        r0.append("????");
    L13:
        return r0.toString();
    L11:
        r0.append(Hex.u2(r2));
        goto L13
    }
}
