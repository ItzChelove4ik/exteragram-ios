package com.android.dx.cf.code;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import com.android.dex.util.ExceptionWithContext;
import com.android.dx.rop.type.Type;
import com.android.dx.rop.type.TypeBearer;
import com.android.dx.util.Hex;
import com.android.dx.util.MutabilityControl;

/* loaded from: classes4.dex */
public final class ExecutionStack extends MutabilityControl {
    private final boolean[] local;
    private final TypeBearer[] stack;
    private int stackPtr;

    public ExecutionStack(int r3) {
        if (r3 == 0) goto L5;
        boolean r1 = true;
    L6:
        super(r1);
        this.stack = new TypeBearer[r3];
        this.local = new boolean[r3];
        this.stackPtr = 0;
        return;
    L5:
        r1 = false;
        goto L6
    }

    private static String stackElementString(TypeBearer r0) {
        if (r0 != null) goto L6;
        return "<invalid>";
    L6:
        return r0.toString();
    }

    private static TypeBearer throwSimException(String r3) {
        throw new SimException("stack: " + r3);
    }

    public void annotate(ExceptionWithContext r6) {
        int r0 = this.stackPtr - 1;
        int r1 = 0;
    L3:
        if (r1 > r0) goto L8;
        if (r1 != r0) goto L6;
        String r2 = "top0";
    L7:
        r6.addContext("stack[" + r2 + "]: " + stackElementString(this.stack[r1]));
        r1 = r1 + 1;
        goto L3
    L6:
        r2 = Hex.u2(r0 - r1);
        goto L7
    }

    public void change(int r4, TypeBearer r5) {
        throwIfImmutable();
        TypeBearer r52 = r5.getFrameType();     // Catch: NullPointerException -> L11
        int r0 = (this.stackPtr - r4) - 1;
        TypeBearer r42 = this.stack[r0];
        if (r42 != null) goto L7;
    L8:
        throwSimException("incompatible substitution: " + stackElementString(r42) + " -> " + stackElementString(r52));
    L9:
        this.stack[r0] = r52;
        return;
    L7:
        if (r42.getType().getCategory() == r52.getType().getCategory()) goto L9;
    L11:
        f$$ExternalSyntheticBUOutline2.m("type == null");
    }

    public void clear() {
        throwIfImmutable();
        int r1 = 0;
    L4:
        if (r1 >= this.stackPtr) goto L6;
        this.stack[r1] = null;
        this.local[r1] = false;
        r1 = r1 + 1;
        goto L4
    L6:
        this.stackPtr = 0;
    }

    public ExecutionStack copy() {
        ExecutionStack r0 = new ExecutionStack(this.stack.length);
        TypeBearer[] r1 = this.stack;
        System.arraycopy(r1, 0, r0.stack, 0, r1.length);
        boolean[] r12 = this.local;
        System.arraycopy(r12, 0, r0.local, 0, r12.length);
        r0.stackPtr = this.stackPtr;
        return r0;
    }

    public int getMaxStack() {
        return this.stack.length;
    }

    public void makeInitialized(Type r5) {
        if (this.stackPtr == 0) goto L12;
        throwIfImmutable();
        Type r0 = r5.getInitializedType();
        int r1 = 0;
    L7:
        if (r1 >= this.stackPtr) goto L16;
        TypeBearer[] r2 = this.stack;
        if (r2[r1] != r5) goto L11;
        r2[r1] = r0;
    L11:
        r1 = r1 + 1;
        goto L7
    L16:
        return;
    }

    public ExecutionStack merge(ExecutionStack r3) {
        return Merger.mergeStack(this, r3);
    L4:
        e = move-exception;
        e.addContext("underlay stack:");
        this.annotate(e);
        e.addContext("overlay stack:");
        r3.annotate(e);
        throw e;
    }

    public TypeBearer peek(int r2) {
        if (r2 >= 0) goto L4;
        f$$ExternalSyntheticBUOutline1.m("n < 0");
        return null;
    L4:
        if (r2 < this.stackPtr) goto L8;
        return throwSimException("underflow");
    L8:
        return this.stack[(r0 - r2) - 1];
    }

    public boolean peekLocal(int r2) {
        if (r2 >= 0) goto L4;
        f$$ExternalSyntheticBUOutline1.m("n < 0");
        return false;
    L4:
        if (r2 >= this.stackPtr) goto L8;
        return this.local[(r0 - r2) - 1];
    L8:
        throw new SimException("stack: underflow");
    }

    public Type peekType(int r1) {
        return peek(r1).getType();
    }

    public TypeBearer pop() {
        throwIfImmutable();
        TypeBearer r1 = peek(0);
        TypeBearer[] r2 = this.stack;
        int r3 = this.stackPtr;
        r2[r3 - 1] = null;
        this.local[r3 - 1] = false;
        this.stackPtr = r3 - r1.getType().getCategory();
        return r1;
    }

    public void push(TypeBearer r6) {
        throwIfImmutable();
        TypeBearer r62 = r6.getFrameType();     // Catch: NullPointerException -> L13
        int r0 = r62.getType().getCategory();     // Catch: NullPointerException -> L13
        int r1 = this.stackPtr;
        int r2 = r1 + r0;
        TypeBearer[] r3 = this.stack;
        if (r2 <= r3.length) goto L9;
        throwSimException("overflow");
        return;
    L9:
        if (r0 != 2) goto L11;
        r3[r1] = null;
        this.stackPtr = r1 + 1;
    L11:
        int r02 = this.stackPtr;
        r3[r02] = r62;
        this.stackPtr = r02 + 1;
        return;
    L13:
        f$$ExternalSyntheticBUOutline2.m("type == null");
    }

    public void setLocal() {
        throwIfImmutable();
        this.local[this.stackPtr] = true;
    }

    public int size() {
        return this.stackPtr;
    }
}
