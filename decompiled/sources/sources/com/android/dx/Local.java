package com.android.dx;

import com.android.dx.rop.code.RegisterSpec;
import okio.AsyncTimeout$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
public final class Local<T> {
    private final Code code;
    private int reg;
    private RegisterSpec spec;
    final TypeId<T> type;

    private Local(Code r2, TypeId<T> r3) {
        this.reg = -1;
        this.code = r2;
        this.type = r3;
    }

    public static <T> Local<T> get(Code r1, TypeId<T> r2) {
        return new Local(r1, r2);
    }

    public TypeId getType() {
        return this.type;
    }

    public int initialize(int r2) {
        this.reg = r2;
        this.spec = RegisterSpec.make(r2, this.type.ropType);
        return size();
    }

    public int size() {
        return this.type.ropType.getCategory();
    }

    public RegisterSpec spec() {
        if (this.spec != null) goto L10;
        this.code.initializeLocals();
        if (this.spec != null) goto L10;
        AsyncTimeout$$ExternalSyntheticBUOutline0.m();
        return null;
    L10:
        return this.spec;
    }

    public String toString() {
        return RegisterSpec.PREFIX + this.reg + "(" + this.type + ")";
    }
}
