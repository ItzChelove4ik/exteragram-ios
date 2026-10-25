package org.mvel2.util;

import org.mvel2.ScriptRuntimeException;
import org.mvel2.math.MathProcessor;

/* loaded from: classes5.dex */
public class ExecutionStack {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private StackElement element;
    private int size;

    static {
    }

    public ExecutionStack() {
        this.size = 0;
    }

    public void add(Object r3) {
        this.size++;
        StackElement r0 = this.element;
        if (r0 == null) goto L9;
    L4:
        StackElement r2 = r0.next;
        if (r2 == null) goto L7;
        r0 = r2;
        goto L4
    L7:
        r0.next = new StackElement(null, r3);
        return;
    L9:
        this.element = new StackElement(null, r3);
    }

    public void clear() {
        this.size = 0;
        this.element = null;
    }

    public void copy2(ExecutionStack r5) {
        this.element = new StackElement(new StackElement(this.element, r5.element.value), r5.element.next.value);
        r5.element = r5.element.next.next;
        this.size += 2;
        r5.size -= 2;
    }

    public void copyx2(ExecutionStack r5) {
        this.element = new StackElement(new StackElement(this.element, r5.element.next.value), r5.element.value);
        r5.element = r5.element.next.next;
        this.size += 2;
        r5.size -= 2;
    }

    public int deepCount() {
        StackElement r1 = this.element;
        if (r1 != null) goto L6;
        return 0;
    L6:
        int r0 = 1;
    L7:
        r1 = r1.next;
        if (r1 == null) goto L10;
        r0 = r0 + 1;
        goto L7
    L10:
        return r0;
    }

    public void discard() {
        int r0 = this.size;
        if (r0 == 0) goto L6;
        this.size = r0 - 1;
        this.element = this.element.next;
        return;
    }

    public void dup() {
        this.size++;
        StackElement r1 = this.element;
        this.element = new StackElement(r1, r1.value);
    }

    public boolean isEmpty() {
        if (this.size != 0) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isReduceable() {
        if (this.size <= 1) goto L5;
        return true;
    L5:
        return false;
    }

    public void op() {
        StackElement r1 = this.element;
        StackElement r2 = r1.next.next;
        this.element = new StackElement(r2.next, MathProcessor.doOperations(r2.value, ((Integer) r1.value).intValue(), this.element.next.value));
        this.size -= 2;
    }

    public void op(int r5) {
        StackElement r1 = this.element;
        StackElement r2 = r1.next;
        this.element = new StackElement(r2.next, MathProcessor.doOperations(r2.value, r5, r1.value));
        this.size--;
    }

    public Object peek() {
        if (this.size != 0) goto L7;
        return null;
    L7:
        return this.element.value;
    }

    public Object peek2() {
        return this.element.next.value;
    }

    public Boolean peekBoolean() {
        if (this.size != 0) goto L6;
        return null;
    L6:
        Object r2 = this.element.value;
        if ((r2 instanceof Boolean) == false) goto L11;
        return (Boolean) r2;
    L11:
        if (r2 != null) goto L13;
        String r22 = "null";
    L15:
        throw new ScriptRuntimeException("expected Boolean; but found: ".concat(r22));
    L13:
        r22 = r2.getClass().getName();
        goto L15
    }

    public Object pop() {
        int r0 = this.size;
        if (r0 != 0) goto L13;
        return null;
    L13:
        this.size = r0 - 1;     // Catch: Throwable -> L10
        StackElement r02 = this.element;     // Catch: Throwable -> L10
        Object r1 = r02.value;     // Catch: Throwable -> L10
        this.element = r02.next;
        return r1;
    L10:
        th = move-exception;
        this.element = this.element.next;
        throw th;
    }

    public Object pop2() {
        this.size -= 2;
        StackElement r0 = this.element;     // Catch: Throwable -> L5
        Object r1 = r0.value;     // Catch: Throwable -> L5
        this.element = r0.next.next;
        return r1;
    L5:
        th = move-exception;
        this.element = this.element.next.next;
        throw th;
    }

    public Boolean popBoolean() {
        int r1 = this.size;
        this.size = r1 - 1;
        if (r1 != 0) goto L21;
        return null;
    L21:
        StackElement r12 = this.element;     // Catch: Throwable -> L11
        Object r2 = r12.value;     // Catch: Throwable -> L11
        if ((r2 instanceof Boolean) == false) goto L14;
        Boolean r22 = (Boolean) r2;     // Catch: Throwable -> L11
        this.element = r12.next;
        return r22;
    L14:
        if (r2 != null) goto L16;
        String r23 = "null";
    L18:
        throw new ScriptRuntimeException("expected Boolean; but found: ".concat(r23));     // Catch: Throwable -> L11
    L16:
        r23 = r2.getClass().getName();     // Catch: Throwable -> L11
    L11:
        th = move-exception;
        this.element = this.element.next;
        throw th;
    }

    public void push(Object r3) {
        this.size++;
        this.element = new StackElement(this.element, r3);
    }

    public void push(Object r4, Object r5) {
        this.size += 2;
        this.element = new StackElement(new StackElement(this.element, r4), r5);
    }

    public void push(Object r5, Object r6, Object r7) {
        this.size += 3;
        this.element = new StackElement(new StackElement(new StackElement(this.element, r5), r6), r7);
    }

    public int size() {
        return this.size;
    }

    public String toString() {
        StackElement r2 = this.element;
        if (r2 != null) goto L6;
        return "<EMPTY>";
    L6:
        StringBuilder r0 = new StringBuilder("[");
    L7:
        r0.append(String.valueOf(r2.value));
        if (r2.next == null) goto L10;
        r0.append(", ");
    L10:
        r2 = r2.next;
        if (r2 != null) goto L7;
        r0.append("]");
        return r0.toString();
    }

    public void xswap() {
        StackElement r0 = this.element;
        StackElement r1 = r0.next;
        StackElement r2 = r1.next;
        r1.next = r0;
        this.element = r1;
        r0.next = r2;
    }

    public void xswap2() {
        StackElement r0 = this.element;
        StackElement r1 = r0.next;
        StackElement r2 = r1.next;
        r1.next = r0;
        r0.next = r2.next;
        this.element = r2;
        r2.next = r1;
    }

    public void xswap_op() {
        StackElement r1 = this.element.next;
        StackElement r2 = r1.next;
        this.element = new StackElement(r2.next, MathProcessor.doOperations(r2.value, ((Integer) r1.value).intValue(), this.element.value));
        this.size -= 2;
    }
}
