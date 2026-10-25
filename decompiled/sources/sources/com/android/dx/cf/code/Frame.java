package com.android.dx.cf.code;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dex.util.ExceptionWithContext;
import com.android.dx.rop.cst.CstType;
import com.android.dx.rop.type.StdTypeList;
import com.android.dx.rop.type.Type;
import com.android.dx.util.IntList;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public final class Frame {
    private final LocalsArray locals;
    private final ExecutionStack stack;
    private final IntList subroutines;

    public Frame(int r2, int r3) {
        this(new OneLocalsArray(r2), new ExecutionStack(r3));
    }

    private Frame(LocalsArray r2, ExecutionStack r3) {
        this(r2, r3, IntList.EMPTY);
    }

    private Frame(LocalsArray r2, ExecutionStack r3, IntList r4) {
        if (r2 == null) goto L9;
        if (r3 == null) goto L7;
        r4.throwIfMutable();
        this.locals = r2;
        this.stack = r3;
        this.subroutines = r4;
        return;
    L7:
        f$$ExternalSyntheticBUOutline2.m("stack == null");
        throw null;
    L9:
        f$$ExternalSyntheticBUOutline2.m("locals == null");
        throw null;
    }

    private static LocalsArray adjustLocalsForSubroutines(LocalsArray r1, IntList r2) {
        if ((r1 instanceof LocalsArraySet) == true) goto L5;
        return r1;
    L5:
        LocalsArraySet r12 = (LocalsArraySet) r1;
        if (r2.size() == 0) goto L8;
        return r12;
    L8:
        return r12.getPrimary();
    }

    private IntList mergeSubroutineLists(IntList r7) {
        if (this.subroutines.equals(r7) == true) goto L5;
        IntList r0 = new IntList();
        int r1 = this.subroutines.size();
        int r2 = r7.size();
        int r3 = 0;
    L7:
        if (r3 >= r1) goto L12;
        if (r3 >= r2) goto L12;
        if (this.subroutines.get(r3) != r7.get(r3)) goto L12;
        r0.add(r3);
        r3 = r3 + 1;
    L12:
        r0.setImmutable();
        return r0;
    L5:
        return this.subroutines;
    }

    public void annotate(ExceptionWithContext r2) {
        this.locals.annotate(r2);
        this.stack.annotate(r2);
    }

    public Frame copy() {
        return new Frame(this.locals.copy(), this.stack.copy(), this.subroutines);
    }

    public LocalsArray getLocals() {
        return this.locals;
    }

    public ExecutionStack getStack() {
        return this.stack;
    }

    public IntList getSubroutines() {
        return this.subroutines;
    }

    public void initializeWithParameters(StdTypeList r6) {
        int r0 = r6.size();
        int r1 = 0;
        int r2 = 0;
    L3:
        if (r1 >= r0) goto L5;
        Type r3 = r6.get(r1);
        this.locals.set(r2, r3);
        r2 = r2 + r3.getCategory();
        r1 = r1 + 1;
        goto L3
    }

    public Frame makeExceptionHandlerStartFrame(CstType r3) {
        ExecutionStack r0 = getStack().copy();
        r0.clear();
        r0.push(r3);
        return new Frame(getLocals(), r0, this.subroutines);
    }

    public void makeInitialized(Type r2) {
        this.locals.makeInitialized(r2);
        this.stack.makeInitialized(r2);
    }

    public Frame makeNewSubroutineStartFrame(int r5, int r6) {
        this.subroutines.mutableCopy().add(r5);
        return new Frame(this.locals.getPrimary(), this.stack, IntList.makeImmutable(r5)).mergeWithSubroutineCaller(this, r5, r6);
    }

    public Frame mergeWith(Frame r4) {
        LocalsArray r0 = getLocals().merge(r4.getLocals());
        ExecutionStack r1 = getStack().merge(r4.getStack());
        IntList r42 = mergeSubroutineLists(r4.subroutines);
        LocalsArray r02 = adjustLocalsForSubroutines(r0, r42);
        if (r02 != getLocals()) goto L10;
        if (r1 != getStack()) goto L10;
        if (this.subroutines != r42) goto L10;
        return this;
    L10:
        return new Frame(r02, r1, r42);
    }

    public Frame mergeWithSubroutineCaller(Frame r7, int r8, int r9) {
        LocalsArraySet r92 = getLocals().mergeWithSubroutineCaller(r7.getLocals(), r9);
        ExecutionStack r0 = getStack().merge(r7.getStack());
        IntList r72 = r7.subroutines.mutableCopy();
        r72.add(r8);
        r72.setImmutable();
        if (r92 == getLocals()) goto L5;
    L9:
        boolean r82 = this.subroutines.equals(r72);
        IntList r1 = this.subroutines;
        if (r82 == true) goto L24;
        int r83 = r1.size();
        int r12 = r72.size();
        IntList r6 = this.subroutines;
        if (r83 <= r12) goto L15;
        r72 = r6;
        r6 = r72;
    L15:
        int r84 = r72.size();
        int r13 = r6.size();
        int r2 = r13 - 1;
    L16:
        if (r2 < 0) goto L22;
        if (r6.get(r2) != r72.get((r84 - r13) + r2)) goto L20;
        r2 = r2 - 1;
        goto L16
    L20:
        GlShader$$ExternalSyntheticBUOutline1.m("Incompatible merged subroutines");
        return null;
    L22:
        r1 = r72;
    L24:
        return new Frame(r92, r0, r1);
    L5:
        if (r0 != getStack()) goto L9;
        if (this.subroutines.equals(r72) == false) goto L9;
        return this;
    }

    public void setImmutable() {
        this.locals.setImmutable();
        this.stack.setImmutable();
    }

    public Frame subFrameForLabel(int r5, int r6) {
        LocalsArray r1 = this.locals;
        if ((r1 instanceof LocalsArraySet) == false) goto L5;
        LocalsArray r62 = ((LocalsArraySet) r1).subArrayForLabel(r6);
    L19:
        IntList r12 = this.subroutines.mutableCopy();     // Catch: NullPointerException -> L15 IndexOutOfBoundsException -> L17
        if (r12.pop() != r5) goto L14;
        r12.setImmutable();     // Catch: NullPointerException -> L15 IndexOutOfBoundsException -> L17
        if (r62 != null) goto L12;
        return null;
    L12:
        return new Frame(r62, this.stack, r12);
    L14:
        throw new RuntimeException("returning from invalid subroutine");     // Catch: NullPointerException -> L15 IndexOutOfBoundsException -> L17
    L17:
        GlShader$$ExternalSyntheticBUOutline1.m("returning from invalid subroutine");
        return null;
    L15:
        f$$ExternalSyntheticBUOutline2.m("can't return from non-subroutine");
        return null;
    L5:
        r62 = null;
        goto L19
    }
}
