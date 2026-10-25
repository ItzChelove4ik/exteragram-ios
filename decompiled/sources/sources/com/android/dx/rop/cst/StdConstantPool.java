package com.android.dx.rop.cst;

import c.f$$ExternalSyntheticBUOutline1;
import com.android.dex.util.ExceptionWithContext;
import com.android.dx.util.Hex;
import com.android.dx.util.MutabilityControl;

/* loaded from: classes4.dex */
public final class StdConstantPool extends MutabilityControl implements ConstantPool {
    private final Constant[] entries;

    public StdConstantPool(int r3) {
        if (r3 <= 1) goto L5;
        boolean r1 = true;
    L6:
        super(r1);
        if (r3 < 1) goto L10;
        this.entries = new Constant[r3];
        return;
    L10:
        f$$ExternalSyntheticBUOutline1.m("size < 1");
        throw null;
    L5:
        r1 = false;
        goto L6
    }

    private static Constant throwInvalid(int r3) {
        throw new ExceptionWithContext("invalid constant pool index " + Hex.u2(r3));
    }

    @Override // com.android.dx.rop.cst.ConstantPool
    public Constant get(int r1) {
        Constant r0 = this.entries[r1];     // Catch: IndexOutOfBoundsException -> L6
        if (r0 != null) goto L5;
        throwInvalid(r1);     // Catch: IndexOutOfBoundsException -> L6
    L5:
        return r0;
    L7:
        return throwInvalid(r1);
    }

    @Override // com.android.dx.rop.cst.ConstantPool
    public Constant get0Ok(int r1) {
        if (r1 != 0) goto L6;
        return null;
    L6:
        return get(r1);
    }

    @Override // com.android.dx.rop.cst.ConstantPool
    public Constant[] getEntries() {
        return this.entries;
    }

    @Override // com.android.dx.rop.cst.ConstantPool
    public Constant getOrNull(int r1) {
        return this.entries[r1];
    L5:
        return throwInvalid(r1);
    }

    public void set(int r5, Constant r6) {
        throwIfImmutable();
        if (r6 != null) goto L5;
    L7:
        boolean r1 = false;
    L8:
        if (r5 >= 1) goto L10;
        f$$ExternalSyntheticBUOutline1.m("n < 1");
        return;
    L10:
        if (r1 == false) goto L16;
        Constant[] r12 = this.entries;
        if (r5 == (r12.length - 1)) goto L14;
        r12[r5 + 1] = null;
        goto L16
    L14:
        f$$ExternalSyntheticBUOutline1.m("(n == size - 1) && cst.isCategory2()");
        return;
    L16:
        if (r6 == null) goto L24;
        Constant[] r0 = this.entries;
        if (r0[r5] != null) goto L24;
        int r13 = r5 - 1;
        Constant r02 = r0[r13];
        if (r02 == null) goto L24;
        if (r02.isCategory2() == false) goto L24;
        this.entries[r13] = null;
    L24:
        this.entries[r5] = r6;
        return;
    L5:
        if (r6.isCategory2() == false) goto L7;
        r1 = true;
        goto L8
    }

    @Override // com.android.dx.rop.cst.ConstantPool
    public int size() {
        return this.entries.length;
    }
}
