package com.android.dx.rop.code;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.util.MutabilityControl;

/* loaded from: classes.dex */
public final class RegisterSpecSet extends MutabilityControl {
    public static final RegisterSpecSet EMPTY = null;
    private int size;
    private final RegisterSpec[] specs;

    static {
        EMPTY = new RegisterSpecSet(0);
    }

    public RegisterSpecSet(int r3) {
        if (r3 == 0) goto L5;
        boolean r1 = true;
    L6:
        super(r1);
        this.specs = new RegisterSpec[r3];
        this.size = 0;
        return;
    L5:
        r1 = false;
        goto L6
    }

    public boolean equals(Object r6) {
        if ((r6 instanceof RegisterSpecSet) == true) goto L5;
        return false;
    L5:
        RegisterSpecSet r62 = (RegisterSpecSet) r6;
        RegisterSpec[] r0 = r62.specs;
        int r2 = this.specs.length;
        if (r2 == r0.length) goto L8;
    L23:
        return false;
    L8:
        if (size() != r62.size()) goto L23;
        int r63 = 0;
    L11:
        if (r63 >= r2) goto L21;
        RegisterSpec r3 = this.specs[r63];
        Object r4 = r0[r63];
        if (r3 == r4) goto L19;
        if (r3 == null) goto L20;
        if (r3.equals(r4) == true) goto L19;
    L20:
        return false;
    L19:
        r63 = r63 + 1;
        goto L11
    L21:
        return true;
    }

    public RegisterSpec findMatchingLocal(RegisterSpec r5) {
        int r0 = this.specs.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L11;
        RegisterSpec r2 = this.specs[r1];
        if (r2 == null) goto L10;
        if (r5.matchesVariable(r2) == false) goto L10;
        return r2;
    L10:
        r1 = r1 + 1;
        goto L3
    L11:
        return null;
    }

    public RegisterSpec get(int r1) {
        return this.specs[r1];
    L4:
        f$$ExternalSyntheticBUOutline1.m("bogus reg");
        return null;
    }

    public RegisterSpec get(RegisterSpec r1) {
        return get(r1.getReg());
    }

    public int getMaxSize() {
        return this.specs.length;
    }

    public int hashCode() {
        int r0 = this.specs.length;
        int r2 = 0;
        int r3 = 0;
    L3:
        if (r2 >= r0) goto L9;
        RegisterSpec r4 = this.specs[r2];
        if (r4 != null) goto L7;
        int r42 = 0;
    L8:
        r3 = (r3 * 31) + r42;
        r2 = r2 + 1;
        goto L3
    L7:
        r42 = r4.hashCode();
        goto L8
    L9:
        return r3;
    }

    public void intersect(RegisterSpecSet r6, boolean r7) {
        throwIfImmutable();
        RegisterSpec[] r62 = r6.specs;
        int r0 = this.specs.length;
        int r1 = Math.min(r0, r62.length);
        this.size = -1;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L11;
        RegisterSpec r3 = this.specs[r2];
        if (r3 == null) goto L10;
        RegisterSpec r4 = r3.intersect(r62[r2], r7);
        if (r4 == r3) goto L10;
        this.specs[r2] = r4;
    L10:
        r2 = r2 + 1;
    L11:
        if (r1 >= r0) goto L13;
        this.specs[r1] = null;
        r1 = r1 + 1;
        goto L11
    }

    public RegisterSpec localItemToSpec(LocalItem r5) {
        int r0 = this.specs.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L10;
        RegisterSpec r2 = this.specs[r1];
        if (r2 == null) goto L9;
        if (r5.equals(r2.getLocalItem()) == false) goto L9;
        return r2;
    L9:
        r1 = r1 + 1;
        goto L3
    L10:
        return null;
    }

    public RegisterSpecSet mutableCopy() {
        int r0 = this.specs.length;
        RegisterSpecSet r1 = new RegisterSpecSet(r0);
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L8;
        RegisterSpec r3 = this.specs[r2];
        if (r3 == null) goto L7;
        r1.put(r3);
    L7:
        r2 = r2 + 1;
        goto L3
    L8:
        r1.size = this.size;
        return r1;
    }

    public void put(RegisterSpec r6) {
        throwIfImmutable();
        if (r6 == null) goto L18;
        this.size = -1;
        int r0 = r6.getReg();     // Catch: ArrayIndexOutOfBoundsException -> L16
        RegisterSpec[] r1 = this.specs;     // Catch: ArrayIndexOutOfBoundsException -> L16
        r1[r0] = r6;     // Catch: ArrayIndexOutOfBoundsException -> L16
        if (r0 <= 0) goto L13;
        int r4 = r0 - 1;
        RegisterSpec r12 = r1[r4];     // Catch: ArrayIndexOutOfBoundsException -> L16
        if (r12 == null) goto L13;
        if (r12.getCategory() != 2) goto L13;
        this.specs[r4] = null;     // Catch: ArrayIndexOutOfBoundsException -> L16
    L13:
        if (r6.getCategory() != 2) goto L22;
        this.specs[r0 + 1] = null;     // Catch: ArrayIndexOutOfBoundsException -> L16
        return;
    L22:
        return;
    L16:
        f$$ExternalSyntheticBUOutline1.m("spec.getReg() out of range");
        return;
    L18:
        f$$ExternalSyntheticBUOutline2.m("spec == null");
    }

    public void putAll(RegisterSpecSet r4) {
        int r0 = r4.getMaxSize();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        RegisterSpec r2 = r4.get(r1);
        if (r2 == null) goto L7;
        put(r2);
    L7:
        r1 = r1 + 1;
        goto L3
    }

    public void remove(RegisterSpec r3) {
        this.specs[r3.getReg()] = null;     // Catch: ArrayIndexOutOfBoundsException -> L4
        this.size = -1;     // Catch: ArrayIndexOutOfBoundsException -> L4
        return;
    L4:
        f$$ExternalSyntheticBUOutline1.m("bogus reg");
    }

    public int size() {
        int r0 = this.size;
        if (r0 >= 0) goto L12;
        int r02 = this.specs.length;
        int r1 = 0;
        int r2 = 0;
    L5:
        if (r1 >= r02) goto L10;
        if (this.specs[r1] == null) goto L9;
        r2 = r2 + 1;
    L9:
        r1 = r1 + 1;
        goto L5
    L10:
        this.size = r2;
        return r2;
    L12:
        return r0;
    }

    public String toString() {
        int r0 = this.specs.length;
        StringBuilder r1 = new StringBuilder(r0 * 25);
        r1.append('{');
        int r2 = 0;
        boolean r3 = false;
    L3:
        if (r2 >= r0) goto L11;
        RegisterSpec r4 = this.specs[r2];
        if (r4 == null) goto L10;
        if (r3 == false) goto L8;
        r1.append(", ");
    L9:
        r1.append(r4);
        goto L10
    L8:
        r3 = true;
    L10:
        r2 = r2 + 1;
        goto L3
    L11:
        r1.append('}');
        return r1.toString();
    }

    public RegisterSpecSet withOffset(int r5) {
        int r0 = this.specs.length;
        RegisterSpecSet r1 = new RegisterSpecSet(r0 + r5);
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L8;
        RegisterSpec r3 = this.specs[r2];
        if (r3 == null) goto L7;
        r1.put(r3.withOffset(r5));
    L7:
        r2 = r2 + 1;
        goto L3
    L8:
        r1.size = this.size;
        if (isImmutable() == false) goto L11;
        r1.setImmutable();
    L11:
        return r1;
    }
}
