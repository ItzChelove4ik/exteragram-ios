package com.android.dx.util;

/* loaded from: classes.dex */
public class MutabilityControl {
    private boolean mutable;

    public MutabilityControl() {
        this.mutable = true;
    }

    public MutabilityControl(boolean r1) {
        this.mutable = r1;
    }

    public final boolean isImmutable() {
        return !this.mutable;
    }

    public final boolean isMutable() {
        return this.mutable;
    }

    public void setImmutable() {
        this.mutable = false;
    }

    public final void throwIfImmutable() {
        if (this.mutable == false) goto L6;
        return;
    L6:
        throw new MutabilityException("immutable instance");
    }

    public final void throwIfMutable() {
        if (this.mutable == true) goto L6;
        return;
    L6:
        throw new MutabilityException("mutable instance");
    }
}
